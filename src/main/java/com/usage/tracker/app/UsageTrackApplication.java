package com.usage.tracker.app;

import com.usage.tracker.app.service.PriceService;
import com.usage.tracker.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class UsageTrackApplication {

	@Autowired
	private PriceService priceService;

	public static void main(String[] args) {
		SpringApplication.run(UsageTrackApplication.class, args);
	}

	@EventListener
	public void seed(ContextRefreshedEvent event){
		priceService.createPrice(0, 1000000, 5.0);
		priceService.createPrice(1000001, 10000000, 4.2);
		priceService.createPrice(10000000, Integer.MAX_VALUE, 3.5);
	}
}
