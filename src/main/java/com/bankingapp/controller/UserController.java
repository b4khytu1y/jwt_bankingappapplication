package com.bankingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapp.model.User;
import com.bankingapp.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Register a new user", description = "Registers a new user by providing username and password.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User registered successfully"),
        @ApiResponse(responseCode = "400", description = "Username is already taken"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "User registration details: username and password",
            required = true,
            content = @Content(
                schema = @Schema(implementation = User.class)
            )
        ) @RequestBody User user) {

        if (userService.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username is already taken");
        }

        userService.registerNewUser(user.getUsername(), user.getPassword());
        return ResponseEntity.ok("User registered successfully");
    }
}
