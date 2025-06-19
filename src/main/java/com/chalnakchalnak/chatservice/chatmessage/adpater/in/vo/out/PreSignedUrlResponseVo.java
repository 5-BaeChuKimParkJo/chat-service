package com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo.out;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class PreSignedUrlResponseVo {

    private String url;
    private Map<String, String> fields;

    @Builder
    public PreSignedUrlResponseVo(String url, Map<String, String> fields) {
        this.url = url;
        this.fields = fields;
    }
}
