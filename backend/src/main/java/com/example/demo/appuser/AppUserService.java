package com.example.demo.appuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    private List<AppUser> users = new ArrayList<>(Arrays.asList(

    ));

    public List<AppUser> getUsers() {
        return appUserRepository.findAll();
    }

    public AppUser findByUserName(String username) {
        return appUserRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Could not find user with username: " + username));
    }



    public Optional<AppUser> getUser(Long id) {
        return appUserRepository.findById(id);
    }

    public void registerNewUser(AppUser user) {
        Optional<AppUser> appUserOptionalByEmail = appUserRepository.findAppUserByEmail(user.getEmail());

        if (appUserOptionalByEmail.isPresent()) {
            throw new IllegalStateException("Email taken");
        }
        appUserRepository.save(user);
    }

    public void deleteUser(Long id) {
        boolean exists = appUserRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("User with id: " + id + " does not exist!");
        }
        appUserRepository.deleteById(id);
    }

    public AppUser updateUser(Long id, String username, AppUser appUser) {
        AppUser user = validateNewUserNameAndEmail(id, username, appUser.getUsername(), appUser.getEmail());
        user.setUsername(appUser.getUsername());
        user.setEmail(appUser.getEmail());
        user.setPassword(appUser.getPassword());
        user.setJoinedDate(appUser.getJoinedDate());
        user.setRoles(appUser.getRoles());
        appUserRepository.save(user);
        return user;
    }

    public AppUser validateNewUserNameAndEmail(Long id, String currentUsername, String newUsername, String newEmail) {
        AppUser userByNewUsername = appUserRepository.findUserByUsername(newUsername);
        AppUser userByNewEmail = appUserRepository.findUserByEmail(newEmail);
        if (StringUtils.isNotBlank(currentUsername)) {
            AppUser currentUser = findByUserName(currentUsername);
            if (currentUser == null) {
                throw new IllegalArgumentException("No user found by username: " + currentUsername + "!");
            }
            if (userByNewUsername != null && !currentUser.getId().equals(userByNewUsername.getId())) {
                throw new IllegalArgumentException("Username already exists!");
            }
            if (userByNewEmail != null && !currentUser.getId().equals(userByNewEmail.getId())) {
                throw new IllegalArgumentException("Email already exists!");
            }
            return currentUser;
        } else {
            if (userByNewUsername != null) {
                throw new IllegalArgumentException("Username already exists!");
            }
            if (userByNewEmail != null) {
                throw new IllegalArgumentException("Email already exists!");
            }
            return null;

        }
    }
}
