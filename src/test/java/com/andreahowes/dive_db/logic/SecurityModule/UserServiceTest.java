package com.andreahowes.dive_db.logic.SecurityModule;

import com.andreahowes.dive_db.data.SecurityData.RoleRepository;
import com.andreahowes.dive_db.data.SecurityData.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

public class UserServiceTest{


    @Autowired
    private UserService userService;

    private User user;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setName("Bob");
        user.setEmail("Bob.bob@bob.com");
    }

    @Test
    public void whenUserServiceFindByEmail_returnUserByEmail() {
        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(user);

        User userByEmail = userService.findUserByEmail("Bob.bob@bob.com");

        assertThat(userByEmail).isEqualTo(user);
    }


    //creates an instance of UserService at runtime.
    //then use mockito to go through to userrepository

    @TestConfiguration
    static class UserServiceConfiguration {
        @Bean
        public UserService userService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
            return new UserService(userRepository, roleRepository, bCryptPasswordEncoder);
        }
    }

}
