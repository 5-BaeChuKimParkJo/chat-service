package com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo.out;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class GetMessagesResponseWrapperVo {

    private List<GetMessagesResponseVo> items;
    private String nextCursor;

    @Builder
    public GetMessagesResponseWrapperVo(List<GetMessagesResponseVo> items, String nextCursor) {
        this.items = items;
        this.nextCursor = nextCursor;
    }
}