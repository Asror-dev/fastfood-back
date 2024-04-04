package com.example.demo.loaders;


import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.entity.enums.Gender;
import com.example.demo.repository.RoleRepo;
import com.example.demo.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class Loader implements CommandLineRunner {

    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    @Override
    public void run(String... args) throws Exception {
        List<Role> all = roleRepo.findAll();
        if(all.isEmpty()){
            List<Role> roles=List.of(

                    Role.builder()
                            .name("ROLE_ADMIN")
                            .build(),
                    Role.builder()
                            .name("ROLE_USER")
                            .build()
            );
            roleRepo.saveAll(roles);
            Role roleAdmin = roleRepo.findByName("ROLE_ADMIN").orElseThrow();
            User user=new User();
            user.setPhone("998914449192");
            user.setFirstName("Asror");
            user.setLastName("Sattorov");
            user.setPassword(passwordEncoder.encode("0100"));
            user.setGender(Gender.MALE);
            user.setRoles(List.of(roleAdmin));
            User save = userRepo.save(user);

        }

    }

}
