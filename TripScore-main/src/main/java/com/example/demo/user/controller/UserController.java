package com.example.demo.user.controller;

import com.example.demo.user.dto.NicknameUpdateDto;
import com.example.demo.user.dto.PasswordUpdateDto;
import com.example.demo.user.dto.PhoneUpdateDto;
import com.example.demo.user.dto.UserDto;
import com.example.demo.user.entity.SiteUser;
import com.example.demo.user.security.SiteUserDetails;
import com.example.demo.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.SecurityConfig;

import javax.naming.Binding;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final UserService userService;

    @GetMapping("/signup")
    public String signup(Model model)
    {
        model.addAttribute("userDto", new UserDto());
        return "user/signup";
    }
    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute UserDto userDto, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()) //userDto 위배되는 값이 있나?
        {
            return "user/signup";//그럼 다시 회원가입 시킴
        }
        if(userService.isUsernameTaken(userDto.getUsername()))
        {
            bindingResult.rejectValue("username", "duplicate", "이미 사용중인 아이디 입니다.");
            return "user/signup";
        }
        userService.createUser(userDto);
        return "redirect:/user/login";
    }
    @GetMapping("/login")
    public String login(){
        return "user/login";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        return "user/profile";
    }


    @PostMapping("/edit/phone")
    public String updatePhone(@Valid @ModelAttribute PhoneUpdateDto phoneUpdateDto, BindingResult bindingResult, @AuthenticationPrincipal SiteUserDetails siteUserDetails){
        if(bindingResult.hasErrors())
        {
            System.out.println(bindingResult.getAllErrors());
            return "/user/edit/phone";
        }
        Long id = siteUserDetails.getUser().getId();

        userService.updatePhoneNumber(id, phoneUpdateDto);
        return "redirect:/user/profile";
    }
    @GetMapping("/edit/phone")
    public String updatePhone(Model model){
        model.addAttribute("phoneUpdateDto", new PhoneUpdateDto());
        return "/user/edit/phone";
    }

    @PostMapping("/edit/password")
    public String updatePassword(@Valid @ModelAttribute PasswordUpdateDto passwordUpdateDto,
                                 BindingResult bindingResult,
                                 @AuthenticationPrincipal SiteUserDetails siteUserDetails,
                                 Model model){
        if(bindingResult.hasErrors())
        {
            System.out.println(bindingResult.getAllErrors());
            return "/user/edit/password";
        }
        SiteUser user = siteUserDetails.getUser();

        // 현재 비밀번호 검증
        if (!passwordEncoder.matches(passwordUpdateDto.getPassword(), user.getPassword())) {
            model.addAttribute("error", "현재 비밀번호가 일치하지 않습니다.");
            return "user/change-password";
        }
        String encodedNewPassword = passwordEncoder.encode(passwordUpdateDto.getNewPassword());
        user.setPassword(encodedNewPassword);
        userService.updatePassword(user.getId(), passwordUpdateDto.getNewPassword());

        return "redirect:/user/profile";
    }
    @GetMapping("/edit/password")
    public String updatePassword(Model model){
        model.addAttribute("passwordUpdateDto", new PasswordUpdateDto());
        return "/user/edit/password";
    }

    @PostMapping("/edit/nickname")
    public String updateNickname(@Valid @ModelAttribute NicknameUpdateDto nicknameUpdateDto,
                                 BindingResult bindingResult,
                                 @AuthenticationPrincipal SiteUserDetails siteUserDetails){
        if(bindingResult.hasErrors())
        {
            System.out.println(bindingResult.getAllErrors());
            return "/user/edit/nickname";
        }
        Long id = siteUserDetails.getUser().getId();

        userService.updateNickname(id, nicknameUpdateDto);
        return "redirect:/user/profile";
    }
    @GetMapping("/edit/nickname")
    public String updateNickname(Model model){
        model.addAttribute("nicknameUpdateDto", new NicknameUpdateDto());
        return "/user/edit/nickname";
    }
}
