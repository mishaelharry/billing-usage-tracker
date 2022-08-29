package com.usage.tracker.app.service;

import com.usage.tracker.app.dto.request.UsageRequest;
import com.usage.tracker.app.dto.response.BaseResponse;
import com.usage.tracker.app.model.Price;
import com.usage.tracker.app.model.Usage;
import com.usage.tracker.app.model.User;
import com.usage.tracker.app.repository.PriceRepository;
import com.usage.tracker.app.repository.UsageRepository;
import com.usage.tracker.app.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UsageServiceTest {

    @Mock
    private UsageRepository usageRepository;

    @Mock
    private UserService userService;

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private UsageService usageService;


    @Test
    public void whenTrackUsageLessThan1Million(){
        UsageRequest request = new UsageRequest();
        request.setUsername("bighmish");

        User user = new User();
        user.setUsername(request.getUsername());
        user.setStatus(true);

        Usage usage = new Usage();
        usage.setUnit(0);
        usage.setCount(1000);
        usage.setAmount(0.0);
        usage.setUser(user);

        Price price = new Price();
        price.setAmount(5.0);
        price.setMax(1000000);
        price.setMin(0);
        price.setQuota(1000);

        when(userService.findByUsername(any())).thenReturn(user);
        when(usageRepository.findByUser(any())).thenReturn(usage);
        when(priceRepository.findByMinGreaterThanAndMaxLessThan(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt())).thenReturn(price);
        when(usageRepository.save(any())).thenReturn(usage);

        BaseResponse<Usage> response = usageService.trackUsage(request);

        assertThat(response.getStatus()).isEqualTo(true);

        assertThat(response.getResult().getAmount()).isEqualTo(5.0);

    }

    @Test
    public void whenTrackUsageLessThan10Million(){
        UsageRequest request = new UsageRequest();
        request.setUsername("bighmish");

        User user = new User();
        user.setUsername(request.getUsername());
        user.setStatus(true);

        Usage usage = new Usage();
        usage.setUnit(0);
        usage.setCount(1000002);
        usage.setAmount(0.0);
        usage.setUser(user);

        Price price = new Price();
        price.setAmount(4.2);
        price.setMax(10000000);
        price.setMin(1000001);
        price.setQuota(1000);

        when(userService.findByUsername(any())).thenReturn(user);
        when(usageRepository.findByUser(any())).thenReturn(usage);
        when(priceRepository.findByMinGreaterThanAndMaxLessThan(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt())).thenReturn(price);
        when(usageRepository.save(any())).thenReturn(usage);

        BaseResponse<Usage> response = usageService.trackUsage(request);

        assertThat(response.getStatus()).isEqualTo(true);

        assertThat(response.getResult().getAmount()).isEqualTo(4200.0);

    }

    @Test
    public void whenTrackUsageGreaterThan10Million(){
        UsageRequest request = new UsageRequest();
        request.setUsername("bighmish");

        User user = new User();
        user.setUsername(request.getUsername());
        user.setStatus(true);

        Usage usage = new Usage();
        usage.setUnit(0);
        usage.setCount(10000002);
        usage.setAmount(0.0);
        usage.setUser(user);

        Price price = new Price();
        price.setAmount(3.5);
        price.setMax(Integer.MAX_VALUE);
        price.setMin(1000000);
        price.setQuota(1000);

        when(userService.findByUsername(any())).thenReturn(user);
        when(usageRepository.findByUser(any())).thenReturn(usage);
        when(priceRepository.findByMinGreaterThanAndMaxLessThan(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt())).thenReturn(price);
        when(usageRepository.save(any())).thenReturn(usage);

        BaseResponse<Usage> response = usageService.trackUsage(request);

        assertThat(response.getStatus()).isEqualTo(true);

        assertThat(response.getResult().getAmount()).isEqualTo(35000.0);

    }
}
