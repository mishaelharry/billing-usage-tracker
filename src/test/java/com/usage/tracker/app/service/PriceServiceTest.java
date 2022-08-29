package com.usage.tracker.app.service;

import com.usage.tracker.app.model.Price;
import com.usage.tracker.app.repository.PriceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PriceServiceTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceService priceService;

    @Test
    public void whenCreatePrice(){
        Price price = new Price();
        price.setAmount(5.0);
        price.setMax(1000000);
        price.setMin(0);
        price.setQuota(1000);

        when(priceRepository.save(ArgumentMatchers.any())).thenReturn(price);

        Price result = priceService.createPrice(0, 1000000, 5.0);
        assertThat(result).isEqualTo(price);
    }

}
