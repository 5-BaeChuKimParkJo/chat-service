package com.chalnakchalnak.chatservice.chatmessage.adpater.out.s3;

import com.chalnakchalnak.chatservice.chatmessage.adpater.out.util.PreSignedUrlUtil;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.PreSignedUrlDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.out.PreSignedUrlResponseDto;
import com.chalnakchalnak.chatservice.chatmessage.application.mapper.PreSignedUrlMapper;
import com.chalnakchalnak.chatservice.chatmessage.application.port.out.GeneratePreSignedUrlPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
@RequiredArgsConstructor
public class PreSignedUrlGenerator implements GeneratePreSignedUrlPort {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

    private final String algorithm = "AWS4-HMAC-SHA256";
    private final String service = "s3";

    private final PreSignedUrlUtil preSignedUrlUtil;
    private final PreSignedUrlMapper preSignedUrlMapper;

    @Override
    public PreSignedUrlResponseDto generatePreSignedUrl(PreSignedUrlDto preSignedUrlDto) {
        Date now = new Date();

        String date = formatDate(now, "yyyyMMdd");
        String dateTime = formatDate(now, "yyyyMMdd'T'HHmmss'Z'");
        String credential = accessKey + "/" + date + "/" + region + "/" + service + "/aws4_request";

        String policyDoc = PreSignedUrlUtil.generateDoc(bucket, algorithm, credential, dateTime, preSignedUrlDto.getContentType());
        String policy = Base64.getEncoder().encodeToString(policyDoc.getBytes(StandardCharsets.UTF_8));
        byte[] signingKey = preSignedUrlUtil.getSignatureKey(secretKey, date, region, service);
        String signature = preSignedUrlUtil.bytesToHex(preSignedUrlUtil.hmacSha256(signingKey, policy));

        Map<String, String> fields = new LinkedHashMap<>();
        fields.put("key", preSignedUrlDto.getKey());
        fields.put("Content-Type", preSignedUrlDto.getContentType());
        fields.put("bucket", bucket);
        fields.put("X-Amz-Algorithm", algorithm);
        fields.put("X-Amz-Credential", credential);
        fields.put("X-Amz-Date", dateTime);
        fields.put("Policy", policy);
        fields.put("X-Amz-Signature", signature);

        String url = "https://" + bucket + ".s3." + region + ".amazonaws.com/";

        return preSignedUrlMapper.toPreSignedUrlResponseDto(url, fields);
    }

    private String formatDate(Date date, String pattern) {
        SimpleDateFormat fmt = new SimpleDateFormat(pattern);
        fmt.setTimeZone(TimeZone.getTimeZone("UTC"));
        return fmt.format(date);
    }
}
