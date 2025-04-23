package com.example.demo.map;

import com.example.demo.user.entity.SiteUser;
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

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MapController {
    private final MapService mapService;
    @Value("${kakao.js-key}")
    private String kakaoJsKey;

    @GetMapping("/map/kakaoMap")
    public String map(Model model) {
        model.addAttribute("appKey", kakaoJsKey);

        // db에서 숙조 전부 마커로 표시할거임
        List<String> addresses = List.of(
                "서울특별시 중구 세종대로 110"
        );
        model.addAttribute("addresses", addresses);

        return "map/kakaoMap";
    }

    @GetMapping("/post/register")
    public String register(Model model) {
        model.addAttribute("appKey", kakaoJsKey);

        // 초기 위치
        List<String> addresses = List.of(
                "서울특별시 중구 세종대로 110"
        );
        model.addAttribute("addresses", addresses);

        return "post/register";
    }

    @PostMapping("/post/create")
    public String create(@Valid @ModelAttribute MapDto mapDto, BindingResult bindingResult,
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
        mapService.create(mapDto, siteUserDetails.getUser());
        return "redirect:/";
    }

}

