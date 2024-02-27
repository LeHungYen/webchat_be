package com.webchat.webchat_be.auth;

import com.webchat.webchat_be.exception.UserException;
import com.webchat.webchat_be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
public class AuthenticationController {
    @Autowired
    private final AuthenticationService service;

    @Autowired
    private UserService userService;
    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest request
    ){
        boolean emailAreadyExisted = userService.checkGmailAlreadyExisted(request.getEmail());
        if(emailAreadyExisted){
            return ResponseEntity.badRequest().body(new UserException("Email already exist"));
        }
        return ResponseEntity.ok(service.register(request));

    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> register(
            @RequestBody AuthenticationRequest request
    ){
        try{
            return ResponseEntity.ok(service.authenticate(request));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new UserException("Invalid username or password"));
        }
    }

    @CrossOrigin
    @PostMapping("/authenticate/google")
    public ResponseEntity<AuthenticationResponse> googleLogin(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(service.googleLogin(request));
    }
}
