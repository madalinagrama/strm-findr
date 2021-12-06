package com.example.demo.appuser;

import com.example.demo.roles.models.ERole;
import com.example.demo.roles.models.Role;
import com.example.demo.payload.request.LoginRequest;
import com.example.demo.payload.request.SignupRequest;
import com.example.demo.payload.response.JwtResponse;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.roles.repository.RoleRepository;
import com.example.demo.security.jwt.JwtUtils;
import com.example.demo.security.services.UserDetailsImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/user")
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
public class AppUserController {

    private final AppUserService userService;
    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    public AppUserController(AppUserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/")
    public List<AppUser> getUsers () {
        return userService.getUsers();
    }


    @GetMapping(path = "/{username}")
    public ResponseEntity<AppUser> getUserProfileByUsername(@PathVariable(value = "username") String username) {
        return new ResponseEntity<>(userService.findByUserName(username), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}/delete")
    @Transactional
    public ResponseEntity<?> deleteUser (@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(new MessageResponse("Deleted successfully!"));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<AppUser> editUserProfile(@PathVariable(value = "id") Long id,
//                                                   @PathVariable(value = "currentUsername") String currentUsername,
                                                   @RequestBody AppUser user) {


        AppUser forResponse = userService.updateUser(id, user.getUsername(), user);
        return new ResponseEntity<>(forResponse, HttpStatus.OK);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping(path = "/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        if (appUserRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error, username is already taken"));
        }
        if (appUserRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error, email is already taken"));
        }

        AppUser user = new AppUser(signupRequest.getUsername(), signupRequest.getEmail(), passwordEncoder.encode(signupRequest.getPassword()));

        Set<String> strRoles = signupRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin" -> {
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                    }
                    case "mod" -> {
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                    }
                    default -> {
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                    }
                }
            });
        }
        user.setRoles(roles);
        user.setJoinedDate(LocalDate.now());
        appUserRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }


    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok(new MessageResponse("Log out successful!"));

    }
}
