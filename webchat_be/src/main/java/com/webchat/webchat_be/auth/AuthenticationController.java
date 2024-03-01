package com.webchat.webchat_be.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.webchat.webchat_be.exception.UserException;
import com.webchat.webchat_be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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




  @PostMapping("/register/{code}")
  public ResponseEntity<?> registerUser(@PathVariable("code") String code) {
      try{
          return ResponseEntity.ok(service.register(code));
      }catch (Exception e){
          return ResponseEntity.badRequest().body(new UserException("The confirmation code you entered is invalid or has expired. Please ensure that you have entered the correct confirmation code."));
      }
  }


    @PostMapping("/authenticationEmail")
    public ResponseEntity<?> authenticationEmail(
            @RequestBody RegisterRequest request
    ) throws JsonProcessingException {
        boolean emailAreadyExisted = userService.checkGmailAlreadyExisted(request.getEmail());
        if(emailAreadyExisted){
            return ResponseEntity.badRequest().body(new UserException("Email already exist"));
        }
        service.authenticationEmail(request);
        return null;
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
