package com.billy.billyServices.listeners;

import com.billy.billyServices.model.BillyUser;
import com.billy.billyServices.model.Login;
import com.billy.billyServices.model.Role;
import com.billy.billyServices.repository.LoginRepository;
import com.billy.billyServices.repository.RoleRepository;
import com.billy.billyServices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Listener for database related events
 */
@Component
public class DBListener {
    private static final String ROLE_ADMIN = "Admin";
    private static final String ROLE_USER = "User";
    private static final String EMPTY_STRING = "";
    private static final int ZERO = 0;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${default.user.password}")
    private String defaultPassword;

    /**
     * Db seeder
     *
     * @param event {@link ContextRefreshedEvent} event which occurred
     */
    @EventListener
    public void dbSeeder(final ContextRefreshedEvent event) {
        seedRoleTable();
        createDefaultAdminUser();
    }

    /**
     * Method for seeding database Role table at application start
     */
    private void seedRoleTable() {
        final List<Role> roles = roleRepository.findAll();

        if (roles == null || roles.size() <= ZERO) {
            roleRepository.save(new Role(ROLE_ADMIN));
            roleRepository.save(new Role(ROLE_USER));

        } else {
            // Nothing do to here
        }
    }

    /**
     * Method for creating default admin user at application start
     */
    private void createDefaultAdminUser() {
        Login adminLogin = loginRepository.findByUsername(ROLE_ADMIN);

        if (adminLogin == null) {
            final BillyUser defaultAdminUser = new BillyUser(ROLE_ADMIN, ROLE_ADMIN, EMPTY_STRING, EMPTY_STRING);
            adminLogin = new Login(ROLE_ADMIN, defaultPassword);

            userService.createAdmin(defaultAdminUser, adminLogin);
        } else {
            // Nothing to do here
        }
    }
}
