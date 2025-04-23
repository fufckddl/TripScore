package com.example.demo.user.security;

import com.example.demo.user.entity.SiteUser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@RequiredArgsConstructor
public class SiteUserDetails implements UserDetails {

    private final SiteUser user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_" + user.getRole().name())
        );
    }

    @Override
    public String getPassword(){
        return user.getPassword();
    }

    @Override
    public String getUsername(){
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired(){ //계정 만료 여부
        return true;
    }

    @Override
    public boolean isAccountNonLocked(){ //계정 잠금 여부
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){ //비밀번호 만료 여부
        return true;
    }

    @Override
    public boolean isEnabled(){ //계정 활성화 여부
        return true;
    }
}
