package com.amadeus.amadeus.flights.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.amadeus.amadeus.flights.auth.JwtUtil;
import com.amadeus.amadeus.flights.model.User;
import com.amadeus.amadeus.flights.request.LoginRequest;
import com.amadeus.amadeus.flights.response.ErrorResponse;
import com.amadeus.amadeus.flights.response.LoginResponse;

@Controller
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;

    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody LoginRequest loginReq) {
        try {
            String emailString = loginReq.getEmail();
            String passwordString = loginReq.getPassword();
            System.out.println("email : " + emailString);
            System.out.println("password : " + passwordString);
    
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(emailString, passwordString));
    
            if (authentication != null && authentication.isAuthenticated()) {
                String email = authentication.getName();
                User user = new User(email, "");
                String token = jwtUtil.createToken(user);
                LoginResponse loginRes = new LoginResponse(email, token);
    
                return ResponseEntity.ok(loginRes);
            } else {
                ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Authentication failed");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
            }
        } catch (BadCredentialsException e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Invalid username or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
    
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
    
}