package com.webchat.webchat_be.auth;
import com.webchat.webchat_be.config.JwtService;
import com.webchat.webchat_be.entity.User;
import com.webchat.webchat_be.enums.UserRole;
import com.webchat.webchat_be.repository.UserRepository;
import com.webchat.webchat_be.service.UserService;
import com.webchat.webchat_be.utilities.Utilities;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .gender(request.getGender())
                .password(passwordEncoder.encode(request.getPassword()))
                .userRole(UserRole.USER)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }


//    public void forgotPassword (HttpServletRequest request, HttpServletResponse response){
//        String username = request.getParameter("id");
//        String email = request.getParameter("email");
//        User user = repository.getById(username);
//
//        if(user == null || !user.getEmail().equals(email)){
//            request.setAttribute("error" , "Incorrect account or email address");
//        }else {
//            request.setAttribute("success" , "We have sent a new password to your email address");
//            Utils.sendMail(email , "Cấp lại mật khẩu FPT Assignment Application" , "Mật khẩu mới của bạn là 12345");
//            user.setPassword("12345");
//            repository.update(user);
//        }
//    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        }catch (BadCredentialsException ex){
            throw new RuntimeException("Tài khoản hoặc mật khẩu không chính xác");
        }
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
//
//        String email = request.getEmail();
//            Utilities.sendMail("lehungyen.ian@gmail.com" , "Cấp lại mật khẩu FPT Assignment Application" , "Mật khẩu mới của bạn là 12345");
        //
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse googleLogin (RegisterRequest request) {
        boolean emailAlreadyExisted = userService.checkGmailAlreadyExisted(request.getEmail());
        if(emailAlreadyExisted){
            var user = repository.findByEmail(request.getEmail())
                    .orElseThrow();
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }else{
            var user = User.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .phoneNumber(request.getPhoneNumber())
                    .gender(request.getGender())
                    .userRole(UserRole.USER)
                    .build();
            repository.save(user);
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }

    }
}
