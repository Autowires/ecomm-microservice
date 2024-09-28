package com.microservices.authentication.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.microservices.authentication.entity.MyUser.UserStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

public interface UserResponseProjection {
    long getId();
    String getUsername();
    UserStatus getStatus();
    String getEmail();
    String getUserType();
    Integer getAge();
    String getContactNo();
    LocalDateTime getCreatedAt();
    LocalDateTime getLastSeen();
}
