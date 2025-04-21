package com.example.demo.user.service;

import com.example.demo.user.dto.UserDto;
import com.example.demo.user.entity.SiteUser;
import com.example.demo.user.model.Role;
import com.example.demo.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor //생성자
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public SiteUser createUser(UserDto dto)
    {
        if(isUsernameTaken(dto.getUsername()))
        {
            throw new IllegalArgumentException("이미 사용중인 아이디");
        }
        SiteUser user = new SiteUser();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setNickname(dto.getNickname());
        user.setPhone(dto.getPhone());
        user.setRole(Role.USER);
        return userRepository.save(user);
    }

    public boolean isUsernameTaken(String username)
    {
        return userRepository.findByUsername(username).isPresent();
    }
    public SiteUser findByUsername(String username)
    {
        return userRepository.findByUsername(username).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 사용자"));
    }
}
