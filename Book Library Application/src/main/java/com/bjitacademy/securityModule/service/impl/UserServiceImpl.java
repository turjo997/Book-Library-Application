package com.bjitacademy.securityModule.service.impl;

import com.bjitacademy.securityModule.entity.Role;
import com.bjitacademy.securityModule.entity.UserEntity;
import com.bjitacademy.securityModule.model.AuthenticationResponse;
import com.bjitacademy.securityModule.model.UserRequestModel;
import com.bjitacademy.securityModule.repository.UserRepository;
import com.bjitacademy.securityModule.service.UserService;
import com.bjitacademy.securityModule.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    @Override
    public ResponseEntity<Object> register(UserRequestModel requestModel) {
        UserEntity userEntity = UserEntity.builder()
                .email(requestModel.getEmail())
                .userName(requestModel.getUserName())
                .firstName(requestModel.getFirstName())
                .lastName(requestModel.getLastName())
                .password(passwordEncoder.encode(requestModel.getPassword()))
                .role(Objects.equals(requestModel.getRole(),"ADMIN")?Role.ADMIN : Role.CUSTOMER)
                .build();
        userRepository.save(userEntity);
        AuthenticationResponse authRes = AuthenticationResponse.builder()
                .token(jwtService.generateToken(userEntity))
                .build();
        return new ResponseEntity<>(authRes, HttpStatus.CREATED);
    }
}
