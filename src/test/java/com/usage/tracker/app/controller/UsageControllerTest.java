package com.usage.tracker.app.controller;

import com.usage.tracker.app.dto.request.UsageRequest;
import com.usage.tracker.app.dto.response.BaseResponse;
import com.usage.tracker.app.model.Usage;
import com.usage.tracker.app.service.UsageService;
import com.usage.tracker.app.service.UserService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class UsageControllerTest {

    @Mock
    private UsageService usageService;

    @InjectMocks
    private UsageController usageController;

    @Test
    public void whenTrackUsage(){

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        BaseResponse<Usage> response = new BaseResponse<>();
        response.setStatus(true);
        response.setMessage("Success");

        when(usageService.trackUsage(any())).thenReturn(response);

        UsageRequest usageRequest = new UsageRequest();
        usageRequest.setUsername("bigmish");
        ResponseEntity<BaseResponse<Usage>> responseEntity = usageController.trackUsage(usageRequest);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);

    }
}
