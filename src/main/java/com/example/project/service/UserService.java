package com.example.project.service;

import com.example.project.dto.UserDto;
import com.example.project.dto.UserJoinDto;
import com.example.project.entity.User;
import com.example.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserDto createUser(UserJoinDto dto) {
        User user = userRepository.save(User.from(dto));
        return UserDto.from(user);
    }
}
