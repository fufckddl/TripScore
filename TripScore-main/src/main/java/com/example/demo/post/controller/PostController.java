package com.example.demo.post.controller;

import com.example.demo.post.dto.PostDto;
import com.example.demo.post.service.PostService;
import com.example.demo.user.security.SiteUserDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/post")
public class PostController {
    private final PostService mapService;
    @Value("${kakao.js-key}")
    private String kakaoJsKey;

    @GetMapping("/list")
    public String map(Model model) {
        model.addAttribute("appKey", kakaoJsKey);

        // db에서 숙조 전부 마커로 표시할거임
        List<String> addresses = List.of(
                "서울특별시 중구 세종대로 110"
        );
        model.addAttribute("addresses", addresses);

        return "post/list";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("appKey", kakaoJsKey);

        // 초기 위치
        List<String> addresses = List.of(
                "서울특별시 중구 세종대로 110"
        );
        model.addAttribute("addresses", addresses);

        return "post/register";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute PostDto postDto, BindingResult bindingResult,
                         @AuthenticationPrincipal SiteUserDetails siteUserDetails, Model model)
    {
        if(bindingResult.hasErrors()){
            List<String> addresses = List.of(
                    "서울특별시 중구 세종대로 110"
            );
            model.addAttribute("appKey", kakaoJsKey);
            model.addAttribute("addresses", addresses);

            return "post/register";
        }
        if(siteUserDetails == null)
            return "redirect:/user/login";
        mapService.create(postDto, siteUserDetails.getUser());
        return "redirect:/";
    }

}

