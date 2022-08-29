package com.usage.tracker.app.controller;


import com.usage.tracker.app.dto.request.UsageRequest;
import com.usage.tracker.app.dto.response.BaseResponse;
import com.usage.tracker.app.model.Usage;
import com.usage.tracker.app.service.UsageService;
import com.usage.tracker.app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/v1/usage")
public class UsageController {

    @Autowired
    private UsageService usageService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<BaseResponse<Usage>> trackUsage(@Valid @RequestBody UsageRequest usageRequest){
        BaseResponse<Usage> response = usageService.trackUsage(usageRequest);
        return ResponseEntity.ok(response);
    }
}
