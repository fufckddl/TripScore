package com.example.demo.user.service;

import com.example.demo.user.dto.NicknameUpdateDto;
import com.example.demo.user.dto.PasswordUpdateDto;
import com.example.demo.user.dto.PhoneUpdateDto;
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

    public SiteUser updatePhoneNumber(Long id, PhoneUpdateDto dto)
    {
        SiteUser user = getUserId(id);
        user.setPhone(dto.getPhone());
        return userRepository.save(user);
    }

    public SiteUser updateNickname(Long id, NicknameUpdateDto dto)
    {
        SiteUser user = getUserId(id);
        user.setNickname(dto.getNickname());
        return userRepository.save(user);
    }

    public void updatePassword(Long userId, String rawNewPassword) {
        SiteUser user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다."));

        String encodedPassword = passwordEncoder.encode(rawNewPassword);
        user.setPassword(encodedPassword);
        userRepository.save(user);  // JPA로 저장
    }

    public boolean isUsernameTaken(String username)
    {
        return userRepository.findByUsername(username).isPresent();
    }
    public SiteUser findByUsername(String username)
    {
        return userRepository.findByUsername(username).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 사용자"));
    }
    public SiteUser getUserId(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
    }
}
