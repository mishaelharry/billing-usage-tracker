package com.usage.tracker.app.service;

import com.usage.tracker.app.model.User;
import com.usage.tracker.app.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void whenCreateUser(){
        User user = new User();
        user.setUsername("bigmish");
        user.setStatus(true);

        when(userRepository.save(ArgumentMatchers.any())).thenReturn(user);

        User result = userService.createUser("bigmish");
        assertThat(result).isEqualTo(user);
    }

}
