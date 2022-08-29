package com.usage.tracker.app.repository;

import com.usage.tracker.app.model.Usage;
import com.usage.tracker.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsageRepository extends JpaRepository<Usage, Long> {

    Usage findByUser(User user);

}
