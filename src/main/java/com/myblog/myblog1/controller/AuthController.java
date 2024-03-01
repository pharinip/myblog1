package com.myblog.myblog1.controller;


import com.myblog.myblog1.entity.Role;
import com.myblog.myblog1.exception.UnsupportedException;
import com.myblog.myblog1.payload.LoginDto;
import com.myblog.myblog1.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.myblog.myblog1.payload.SignUpDto;
import com.myblog.myblog1.repository.UserRepository;
import com.myblog.myblog1.entity.User;

import java.util.*;
import javax.validation.*;

@RestController
@RequestMapping("/api/auth")

public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
   private PasswordEncoder passwordEncoder;

    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto){

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword());
        System.out.println("authenticationManagerauthenticationManager"+authenticationManager);
        Authentication authentication= authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed-in successfully!.",
                HttpStatus.OK);

    }


        @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) {
        // add check for username exists in a DB
        if(userRepository.existsByUsername(signUpDto.getUsername())){
            return new ResponseEntity<>("Username is already taken!",
                    HttpStatus.BAD_REQUEST);
        }
        // add check for email exists in DB
        if(userRepository.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>("Email is already taken!",
                    HttpStatus.BAD_REQUEST);
        }
        // create user object
        User user = new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

       Role role = roleRepository.findByName(signUpDto.getRoleType()).orElseThrow(
                () -> new UnsupportedException("No such role exists "+signUpDto.getRoleType())
        );

        Set<Role> covertRoleToSet=new HashSet<>();
        covertRoleToSet.add(role);

        user.setRoles(covertRoleToSet);


//        Role role = roleRepository.findByName(signUpDto.getRoleType()).get();
//        Set<Role> covertRoleToSet=new HashSet<>();
//         covertRoleToSet.add(role);
//        user.setRoles(covertRoleToSet);

        userRepository.save(user);
        return new ResponseEntity<>("User registered successfully",
                HttpStatus.OK);

    }
}
