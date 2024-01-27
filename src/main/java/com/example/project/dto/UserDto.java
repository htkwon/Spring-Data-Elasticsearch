package com.example.project.dto;

import com.example.project.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String username;

    public UserDto(Long id, String userName) {
        this.id = id;
        this.username = userName;
    }


    public static UserDto of(Long id, String username){
        return new UserDto(
                id,
                username
        );
    }

    public static UserDto from(User user){
        return new UserDto(
                user.getId(),
                user.getUserName()
        );
    }



}
