//package com.Event.auth;
//
//import com.Event.Vendor.Vendor;
//import com.Event.Repositories.VendorRepository;
//import com.Event.config.JwtService;
//import com.Event.user.RestUser;
//import com.Event.user.Role;
//import com.Event.user.User;
//import com.Event.user.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class AuthenticationService {
//    private final UserRepository userRepository;
//    private final VendorRepository vendorRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtService jwtService;
//    private final AuthenticationManager authenticationManager;
//
//    public AuthenticationResponse register(RegisterRequest request) {
//        var user = User.builder()
//                .email(request.getEmail())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .role(Role.valueOf(request.getRole()))
//                .build();
//        userRepository.save(user);
//        Vendor vendor = null;
//        if (Role.valueOf(request.getRole()).equals(Role.VENDOR)) {
//            vendor = Vendor.builder()
//                    .companyName(request.getCompanyName())
//                    .user(user)
//                    .typesOfServices(request.getTypesOfServices())
//                    .build();
//        }
//        if (vendor != null) {
//            vendorRepository.save(vendor);
//        }
//        var jwtToken = jwtService.generateToken(user);
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();
//
//    }
//
//    public AuthenticationResponse authenticate(AuthenticationRequest request) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getEmail(),
//                        request.getPassword()
//                )
//        );
//        var user = userRepository.findByEmail(request.getEmail())
//                .orElseThrow();
//        var jwtToken = jwtService.generateToken(user);
//        RestUser restUser = RestUser.builder()
//                .userId(user.getId())
//                .userName(user.getUsername())
//                .role(user.getRole()).build();
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .user(restUser)
//                .build();
//    }
//}
