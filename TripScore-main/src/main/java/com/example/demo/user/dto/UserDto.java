package com.example.demo.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    @NotBlank(message = "아이디는 필수입니다.")
    @Size(min = 3, max = 20, message = "아이디는 3자 이상 20자 이하로 입력하세요.")
    private String username;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 3, message = "비밀번호는 최소 3자 이상이어야 합니다.")
    private String password;

    @NotBlank(message = "닉네임은 필수입니다.")
    @Size(min = 3, max = 20, message = "닉네임은 3자 이상 20자 이하로 입력하세요.")
    private String nickname;

    @NotBlank(message = "전화번호는 필수입니다.")
    @Pattern(
            regexp = "^(010)(\\d{4})(\\d{4})$|^(010)-(\\d{4})-(\\d{4})$",
            message = "전화번호는 01012345678 또는 010-1234-5678 형식이어야 합니다."
    )
    private String phone;
}
