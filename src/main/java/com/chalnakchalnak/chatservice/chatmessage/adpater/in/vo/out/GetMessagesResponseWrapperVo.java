package com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo.out;

import com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo.NextCursorVo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class GetMessagesResponseWrapperVo {

    private List<GetMessagesResponseVo> items;
    private NextCursorVo nextCursor;

    @Builder
    public GetMessagesResponseWrapperVo(List<GetMessagesResponseVo> items, NextCursorVo nextCursor) {
        this.items = items;
        this.nextCursor = nextCursor;
    }
}