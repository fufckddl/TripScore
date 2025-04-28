package com.example.demo.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NicknameUpdateDto {
    @NotBlank(message = "닉네임은 필수입니다.")
    @Size(min = 3, max = 20, message = "닉네임은 3자 이상 20자 이하로 입력하세요.")
    private String nickname;
}
