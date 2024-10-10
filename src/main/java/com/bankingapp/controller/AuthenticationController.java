package com.bankingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapp.security.AuthenticationRequest;
import com.bankingapp.security.AuthenticationResponse;
import com.bankingapp.security.JwtTokenUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Operation(summary = "Authenticate a user", description = "Authenticate a user and receive a JWT token.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "JWT token generated successfully"),
        @ApiResponse(responseCode = "401", description = "Invalid username or password")
    })
    @PostMapping("/authenticate")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest request) throws Exception {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (AuthenticationException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails.getUsername());
        return new AuthenticationResponse(jwt);
    }
}
