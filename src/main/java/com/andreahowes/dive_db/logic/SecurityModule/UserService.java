package com.andreahowes.dive_db.logic.SecurityModule;

import com.andreahowes.dive_db.data.SecurityData.RoleRepository;
import com.andreahowes.dive_db.data.SecurityData.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }

    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Optional<Role> userRole = roleRepository.findOneByDescription("Admin");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole.get())));
        userRepository.save(user);
    }

    public Boolean checkCredentials(String email, String password){
        Optional<User> user = userRepository.findByEmail(email);
        boolean passwordMatches = bCryptPasswordEncoder.matches(password, user.get().getPassword());
        if(passwordMatches){
            return true;
        } else {
            return false;
        }
    }

    public void deleteUser(User user){
        userRepository.delete(user);
    }



}
