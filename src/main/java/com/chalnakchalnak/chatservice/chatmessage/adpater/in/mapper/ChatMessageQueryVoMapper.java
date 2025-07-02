package com.chalnakchalnak.chatservice.chatmessage.adpater.in.mapper;

import com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo.NextCursorVo;
import com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo.ReplyPreviewVo;
import com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo.in.GetMessagesRequestVo;
import com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo.in.GetReadCheckPointRequestVo;
import com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo.out.GetMessagesResponseVo;
import com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo.out.GetMessagesResponseWrapperVo;
import com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo.out.GetReadCheckPointResponseVo;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.GetMessagesRequestDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.in.GetReadCheckPointRequestDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.out.GetMessagesResponseDto;
import com.chalnakchalnak.chatservice.chatmessage.application.dto.out.GetReadCheckPointResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChatMessageQueryVoMapper {

    public GetMessagesRequestDto toGetMessagesRequestDto(GetMessagesRequestVo getMessagesRequestVo, String memberUuid) {
        return GetMessagesRequestDto.builder()
                .chatRoomUuid(getMessagesRequestVo.getChatRoomUuid())
                .memberUuid(memberUuid)
                .lastMessageUuid(getMessagesRequestVo.getLastMessageUuid())
                .lastMessageSentAt(getMessagesRequestVo.getLastMessageSentAt())
                .limit(getMessagesRequestVo.getLimit() != null ? getMessagesRequestVo.getLimit() : 20)
                .build();
    }

    public GetMessagesResponseVo toGetMessagesResponseVo(GetMessagesResponseDto getMessagesResponseDto) {
        return GetMessagesResponseVo.builder()
                .messageUuid(getMessagesResponseDto.getMessageUuid())
                .chatRoomUuid(getMessagesResponseDto.getChatRoomUuid())
                .senderUuid(getMessagesResponseDto.getSenderUuid())
                .message(getMessagesResponseDto.getMessage())
                .messageType(getMessagesResponseDto.getMessageType())
                .sentAt(getMessagesResponseDto.getSentAt().toString())
                .replyToMessageUuid(getMessagesResponseDto.getReplyToMessageUuid())
                .replyPreview(getMessagesResponseDto.getReplyPreview() != null ?
                        ReplyPreviewVo.builder()
                                .senderUuid(getMessagesResponseDto.getReplyPreview().getSenderUuid())
                                .message(getMessagesResponseDto.getReplyPreview().getMessage())
                                .messageType(getMessagesResponseDto.getReplyPreview().getMessageType())
                                .build()
                        : null)
                .build();
    }

    public GetReadCheckPointRequestDto toGetReadCheckPointRequestDto(GetReadCheckPointRequestVo getReadCheckPointRequestVo) {
        return GetReadCheckPointRequestDto.builder()
                .chatRoomUuid(getReadCheckPointRequestVo.getChatRoomUuid())
                .memberUuid(getReadCheckPointRequestVo.getMemberUuid())
                .build();
    }

    public GetReadCheckPointResponseVo toGetReadCheckPointResponseVo(GetReadCheckPointResponseDto getReadCheckPointResponseDto) {
        return GetReadCheckPointResponseVo.builder()
                .lastReadMessageSentAt(getReadCheckPointResponseDto.getLastReadMessageSentAt().toString())
                .build();
    }

    public GetMessagesResponseWrapperVo toGetMessagesResponseWrapperVo(List<GetMessagesResponseDto> dtos) {
        final List<GetMessagesResponseVo> items = dtos.stream()
                .map(this::toGetMessagesResponseVo)
                .toList();

        final NextCursorVo nextCursor =
                dtos.isEmpty()
                        ? null :
                        NextCursorVo.builder()
                        .lastMessageUuid(dtos.get(dtos.size() - 1).getMessageUuid())
                        .lastMessageSentAt(dtos.get(dtos.size() - 1).getSentAt().toString())
                        .build();

        return GetMessagesResponseWrapperVo.builder()
                .items(items)
                .nextCursor(nextCursor)
                .build();
    }
}
