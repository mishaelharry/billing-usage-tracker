package com.usage.tracker.app.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UsageRequest {

    @NotBlank(message = "Username is required")
    private String username;

}
