package com.usage.tracker.app.service;

import com.usage.tracker.app.dto.request.UsageRequest;
import com.usage.tracker.app.dto.response.BaseResponse;
import com.usage.tracker.app.model.Price;
import com.usage.tracker.app.model.Usage;
import com.usage.tracker.app.model.User;
import com.usage.tracker.app.repository.PriceRepository;
import com.usage.tracker.app.repository.UsageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Service
public class UsageService {

    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private UsageRepository usageRepository;

    @Autowired
    private UserService userService;

    public BaseResponse<Usage> trackUsage(UsageRequest request){
        User user = userService.findByUsername(request.getUsername());
        if (user == null){
            user = userService.createUser(request.getUsername());
        }

        Usage usage = usageRepository.findByUser(user);
        if (usage != null){
            usage.setCount(usage.getCount() + 1);
            Price price = priceRepository.findByMinGreaterThanAndMaxLessThan(usage.getCount(), usage.getCount());
            if (price != null){
                if(usage.getCount() >= price.getQuota()){
                    int unit = usage.getCount() / price.getQuota();
                    if (unit > 0 && unit != usage.getUnit()){
                        double bill = unit * price.getAmount();
                        usage.setAmount(usage.getAmount() + bill);
                        usage.setUnit(unit);
                    }
                }
            }
        } else {
            usage = new Usage();
            usage.setUser(user);
            usage.setCount(1);
            usage.setAmount(0.0);
            usage.setUnit(0);
        }
        usage = usageRepository.save(usage);
        return new BaseResponse<>(true, "Usage save successfully", usage);
    }

    public String getApiKey(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
}
