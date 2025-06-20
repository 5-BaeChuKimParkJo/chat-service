package com.chalnakchalnak.chatservice.chatmessage.adpater.in.vo.in;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetReadCheckPointRequestVo {

    @NotBlank(message = "chatRoomUuid는 필수 값입니다.")
    private String chatRoomUuid;

    @NotBlank(message = "memberUuid는 필수 값입니다.")
    private String memberUuid;
}
