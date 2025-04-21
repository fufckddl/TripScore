package com.example.demo.user.controller;

import com.example.demo.user.dto.UserDto;
import com.example.demo.user.entity.SiteUser;
import com.example.demo.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/signup")
    public String signup(Model model)
    {
        model.addAttribute("userDto", new UserDto());
        return "/user/signup";
    }
    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute UserDto userDto, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()) //userDto 위배되는 값이 있나?
        {
            return "/user/signup";//그럼 다시 회원가입 시킴
        }
        if(userService.isUsernameTaken(userDto.getUsername()))
        {
            bindingResult.rejectValue("username", "duplicate", "이미 사용중인 아이디 입니다.");
            return "/user/signup";
        }
        userService.createUser(userDto);
        return "redirect:/user/login";
    }
    @GetMapping("/login")
    public String login(){
        return "/user/login";
    }
}
