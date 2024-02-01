package com.example.contestbackend.controller;

import com.example.contestbackend.dto.UserDto;
import com.example.contestbackend.service.EmailVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/emails-verification")
public class EmailVerificationController {
    private final EmailVerificationService emailVerificationService;

    @PostMapping("/send")
    public ResponseEntity<String> sendVerificationEmail(@RequestBody UserDto userDto) throws MessagingException {
        try {
            if (userDto.getEmail() != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("User with email " + userDto.getEmail() + " already exists");
            }
            String verificationCode = emailVerificationService.generateVerificationToken();
            emailVerificationService.sendVerificationEmail(userDto, userDto.getEmail(), verificationCode);
            return ResponseEntity.ok("Verification email sent successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred.");
        }


    }

    @GetMapping("/verify")
    public ResponseEntity<String> verifyToken(@RequestParam String email, @RequestParam String token) {
        if (emailVerificationService.verifyToken(email, token)) {
            emailVerificationService.setEnableTrue(email);
            return ResponseEntity.ok("<div><h1>Weryfikacja została zakończona pomyślnie.</h1></div>" +
                    "<style>div {display: flex; text-align: center; justify-content: center; height: 100%;\n" +
                    "    align-items: center;} </style>");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("<div><h1>Weryfikacja NIE została zakończona pomyślnie.</h1></div>" +
                    "<style>div {display: flex; align-items: center; justify-content: center; height: 100%;\n" +
                    "    align-items: center;} </style>");
        }
    }

}
