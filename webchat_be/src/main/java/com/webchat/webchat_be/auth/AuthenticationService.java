package com.webchat.webchat_be.auth;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webchat.webchat_be.config.JwtService;
import com.webchat.webchat_be.entity.User;
import com.webchat.webchat_be.enums.UserRole;
import com.webchat.webchat_be.repository.UserRepository;
import com.webchat.webchat_be.service.UserService;
import com.webchat.webchat_be.utilities.Utilities;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
//    @Autowired
//    RedisTemplate<String, RegisterRequest> redisTemplate; // Inject RedisTemplate bean here
    private final  ObjectMapper objectMapper = new ObjectMapper();

    private final Map<String, RegisterRequest> hashMap = new HashMap<>();
    public void authenticationEmail(RegisterRequest request) throws JsonProcessingException {
        String key = Utilities.generateRandomNumber();

        Utilities.sendMail(request.getEmail(),
                "SS-"+key + " là mã xác nhận Social Sphere của bạn",
                "Hi " + request.getLastName() + ",\n" +
                        " \n" +
                        "You need to confirm your Social Sphere account.\n" +
                        " \n" +
                        "Open Social Sphere and enter this code: " + key);

//        String json = objectMapper.writeValueAsString(request);
//        redisTemplate.opsForValue().set(key, request);
        hashMap.put(key ,request);
    }
    public AuthenticationResponse register(String code) throws JsonProcessingException {
//        Object object = redisTemplate.opsForValue().get(code);
//        if (object == null) {
//            throw new RuntimeException("Không tìm thấy dữ liệu cho mã xác nhận này: " + code);
//        }

//        RegisterRequest request = new RegisterRequest();
//        BeanUtils.copyProperties(object , request);
//        redisTemplate.delete(code);

        RegisterRequest request = hashMap.get(code);
        if (request == null) {
            throw new RuntimeException("Không tìm thấy dữ liệu cho mã xác nhận này: " + code);
        }
        hashMap.remove(code);


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
