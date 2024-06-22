package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UpdatePassword {
    private String password;
    @JsonProperty("confirm_password")
    private String confirmPassword;
}
