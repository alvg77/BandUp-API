package com.bandup.api.dto.auth;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
public class LoginRequest {
    @NonNull
    private String email;
    @NonNull
    private String password;
}
