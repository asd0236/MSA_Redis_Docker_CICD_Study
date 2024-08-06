package com.sparta.msa_exam.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/auth/signIn")
    public ResponseEntity<?> createAuthToken(@RequestParam String user_id){
        AuthResponse authResponse = new AuthResponse(authService.createAccessToken(user_id));

        HttpHeaders headers = new HttpHeaders();
        headers.set("Server-Port", serverPort);

        // 유저 id를 통해 토큰을 넘김
        return new ResponseEntity<>(authResponse, headers, HttpStatus.OK);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class AuthResponse{
        private String access_token;
    }

}
