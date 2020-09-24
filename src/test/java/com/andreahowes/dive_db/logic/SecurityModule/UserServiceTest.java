package com.andreahowes.dive_db.logic.SecurityModule;

import com.andreahowes.dive_db.data.SecurityData.RoleRepository;
import com.andreahowes.dive_db.data.SecurityData.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

public class UserServiceTest {

    @Mock
    User user;

    @Mock
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    RoleRepository roleRepository;

    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @BeforeEach
    public User setUp() {
        MockitoAnnotations.initMocks(this);
        user.setName("Bob");
        user.setEmail("Bob.bob@bob.com");
    }

    @Test
    public void whenUserServiceFindByEmail_returnUserByEmail() {
        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(user);

        User userByEmail = userService.findUserByEmail("Bob.bob@bob.com");

        assertThat(userByEmail).isEqualTo(user);
    }

    // creates an instance of UserService at runtime.
    // then use mockito to go through to userRepository

    @TestConfiguration
    static class UserServiceConfiguration {
        @Bean
        public UserService userService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
            return new UserService(userRepository, roleRepository, bCryptPasswordEncoder);
        }
    }

}
