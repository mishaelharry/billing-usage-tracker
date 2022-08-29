package com.usage.tracker.app.service;

import com.usage.tracker.app.model.Price;
import com.usage.tracker.app.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceService {

    @Autowired
    PriceRepository priceRepository;

    public Price createPrice(Integer min, Integer max, Double amount){
        Price price = new Price();
        price.setAmount(amount);
        price.setMin(min);
        price.setMax(max);
        price.setQuota(1000);
        return priceRepository.save(price);
    }

}
