package com.auth.jwt.service;

import com.auth.jwt.dto.AuthUserDto;
import com.auth.jwt.dto.TokenDto;
import com.auth.jwt.entity.AuthUser;
import com.auth.jwt.repository.AuthUserRepsitory;
import com.auth.jwt.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private AuthUserRepsitory authUserRepsitory;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;


    public AuthUser save(AuthUserDto authUserDto) {
        Optional<AuthUser> user = authUserRepsitory.findByUserName(authUserDto.getUserName());

        if (user.isPresent()) {
            return null;
        }
        String password = passwordEncoder.encode(authUserDto.getPassword());

        AuthUser authUser = AuthUser.builder()
                .userName(authUserDto.getUserName())
                .password(password)
                .build();

        return authUserRepsitory.save(authUser);

    }

    public TokenDto login(AuthUserDto dto) {
        Optional<AuthUser> user = authUserRepsitory.findByUserName(dto.getUserName());

        if (user.isEmpty()) {
            return null;
        }

        if (passwordEncoder.matches(dto.getPassword(), user.get().getPassword())) {
            return new TokenDto(jwtProvider.createToken(user.get()));
        }
        return null;
    }

    public TokenDto validate(String token) {
        if(!jwtProvider.validate(token)) {
            return null;
        }

        String username = jwtProvider.getUsernameFromToken(token);

        if(authUserRepsitory.findByUserName(username).isEmpty()) {
            return null;
        }
        return new TokenDto(token);
    }
}
