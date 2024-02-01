package com.example.contestbackend.controller;

import com.example.contestbackend.dto.UserDto;
import com.example.contestbackend.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/emails")
public class EmailController {
    private final EmailService emailService;

    @PostMapping("/send")
    public void sendVerificationEmail(Principal principal,@RequestParam String to,@RequestParam String subject,@RequestParam String text) throws MessagingException {
        emailService.sendHtmlMail(principal.getName(), to, subject, text);
    }
}
