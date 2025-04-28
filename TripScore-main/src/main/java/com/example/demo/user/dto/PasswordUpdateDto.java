package com.example.demo.user.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PasswordUpdateDto {

    @NotBlank(message = "현재 비밀번호는 필수입니다.")
    private String password;

    @NotBlank(message = "새 비밀번호는 필수입니다.")
    @Size(min = 3, message = "비밀번호는 최소 3자 이상이어야 합니다.")
    private String newPassword;
}
