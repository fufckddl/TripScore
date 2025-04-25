package com.example.demo.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneUpdateDto {

    @NotBlank(message = "전화번호는 필수입니다.")
    @Pattern(
            regexp = "^(010)(\\d{4})(\\d{4})$|^(010)-(\\d{4})-(\\d{4})$",
            message = "전화번호는 01012345678 또는 010-1234-5678 형식이어야 합니다."
    )
    private String phone;
}
