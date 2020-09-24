package com.andreahowes.dive_db.bootstrap;

import com.andreahowes.dive_db.data.SecurityData.RoleRepository;
import com.andreahowes.dive_db.data.SecurityData.TokenRepository;
import com.andreahowes.dive_db.data.SecurityData.UserRepository;
import com.andreahowes.dive_db.data.dives.DiveRepository;
import com.andreahowes.dive_db.logic.SecurityModule.Role;
import com.andreahowes.dive_db.logic.SecurityModule.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
@Profile("default")
public class BootstrapDefault implements ApplicationListener<ContextRefreshedEvent> {

    private final DiveRepository diveRepository;
    private final RoleRepository roleRepository;
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;

    public BootstrapDefault(DiveRepository diveRepository, RoleRepository roleRepository, TokenRepository tokenRepository, UserRepository userRepository) {
        this.diveRepository = diveRepository;
        this.roleRepository = roleRepository;
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
    }


    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
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

        Role roleGroupAdmin = new Role();
        roleGroupAdmin.setDescription("Group-Admin");
        roleRepository.save(roleGroupAdmin);

        Role roleUser = new Role();
        roleUser.setDescription("User");
        roleRepository.save(roleUser);
    }

    private void loadUsers(){
        User adminUser = new User();
        adminUser.setFirstName("Jochen");
        adminUser.setLastName("Gebsattel");
        Set<Role> rolesSet = new HashSet<>();
        rolesSet.add(roleRepository.findOneByDescription("Admin").orElse(null));
        rolesSet.add(roleRepository.findOneByDescription("User").orElse(null));
        adminUser.setRoles(rolesSet);
        userRepository.save(adminUser);

        User loggedInUser = new User();
        loggedInUser.setFirstName("Test");
        loggedInUser.setLastName("Diver");
        rolesSet.remove(roleRepository.findOneByDescription("Admin").orElse(null));
        loggedInUser.setRoles(rolesSet);
        userRepository.save(loggedInUser);

    }

    // TODO: add example data for all repositories in in-memory h2-test-database

}
