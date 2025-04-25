package com.example.demo.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {

    @NotBlank(message = "숙소 주소는 필수입니다.")
    private String address;

    @NotBlank(message = "제목은 필수입니다.")
    @Size(min = 2, max=200, message = "제목은 2~200자여야 합니다.")
    private String title;

    @NotBlank(message = "내용은 필수입니다.")
    private String content;
}
