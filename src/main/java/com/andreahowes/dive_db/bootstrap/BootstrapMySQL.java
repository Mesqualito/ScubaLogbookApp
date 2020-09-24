package com.andreahowes.dive_db.bootstrap;

import com.andreahowes.dive_db.data.SecurityData.RoleRepository;
import com.andreahowes.dive_db.data.SecurityData.UserRepository;
import com.andreahowes.dive_db.logic.SecurityModule.Role;
import com.andreahowes.dive_db.logic.SecurityModule.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
@Profile({"dev", "prod"})
public class BootstrapMySQL implements ApplicationListener<ContextRefreshedEvent> {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public BootstrapMySQL(UserRepository userRepository,
                          RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        if (roleRepository.count() == 0L){
            log.debug("Loading Roles");
            loadRoles();
        }

        if (userRepository.count() == 0L){
            log.debug("Loading Users");
            loadUsers();
        }
    }

    private void loadRoles(){
        Role roleAdmin = new Role();
        roleAdmin.setDescription("Admin");
        roleRepository.save(roleAdmin);

        Role roleUser = new Role();
        roleUser.setDescription("User");
        roleRepository.save(roleUser);
    }

    private void loadUsers(){
        User adminUser = new User();
        adminUser.setName("Administrator");
        Set<Role> rolesSet = new HashSet<Role>();
        rolesSet.add(roleRepository.findOneByDescription("Admin"));
        rolesSet.add(roleRepository.findOneByDescription("User"));
        adminUser.setRoles(rolesSet);
        userRepository.save(adminUser);

        User loggedInUser = new User();
        loggedInUser.setName("User");
        rolesSet.remove(roleRepository.findOneByDescription("Admin"));
        loggedInUser.setRoles(rolesSet);
        userRepository.save(loggedInUser);
    }
}
