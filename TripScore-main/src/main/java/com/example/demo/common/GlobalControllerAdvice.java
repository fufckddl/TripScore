package com.example.demo.common;


import com.example.demo.user.entity.SiteUser;
import com.example.demo.user.security.SiteUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

//모든 컨트롤로에게 공통적으로 적용되는 설정
@ControllerAdvice
public class GlobalControllerAdvice {
    @ModelAttribute
    public void addLoggedInUserToModel(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("auth = " + auth); // 로그 확인

        if (auth != null && auth.getPrincipal() instanceof SiteUserDetails userDetails) {
            SiteUser user = userDetails.getUser();
            System.out.println("✅ yes: " + user.getUsername());
            model.addAttribute("loggedInUser", user);
        } else {
            System.out.println("⚠️ no");
        }
    }

}