package com.wjgy.UdiMoim.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User implements UserDetails {

    // 회원 번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_num",updatable = false)
    private Long userNum;
    
    // 회원 아이디
    @Column(name = "user_id", updatable = false, unique = true)
    private String userId;

    // 회원 패스워드
    @Column(name = "user_password")
    private String userPassword;

    // 회원 이름
    @Column(name = "user_name")
    private String userName;


    @Builder
    public User(String userId, String userPassword, String userName, String auth) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
    }

    // 권한 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("users"));
    }

    // 사용자 아이디 반환 (고유한 값)
    @Override
    public String getUsername() {
        return userId;
    }

    // 사용자 패스워드 반환
    @Override
    public String getPassword() {
        return userPassword;
    }

    // 계정 만료 여부 반환
    @Override
    public boolean isAccountNonExpired() {
        // 계정 만료 되었는지 확인 하는 로직
        return true;  // true -> 만료 되지 않았음
   }
   
    // 계정 잠금 여부 반환
    @Override
    public boolean isAccountNonLocked() {
        // 계정 잠금 되었는지 확인 하는 로직
        return true;  // true -> 잠금 되지 않았음
    }
   
    // 패스워드 만료 여부 반환
    @Override
    public boolean isCredentialsNonExpired() {
        // 패스워드가 만료 되었는지 확인 하는 로직
        return true;  // true -> 만료 되지 않았음
    }

    // 계정 사용 가능 여부 반환
    @Override
    public boolean isEnabled() {
        // 계정이 사용 가능한지 확인하는 로직
        return true;  // true -> 사용 가능
    }
    
}
