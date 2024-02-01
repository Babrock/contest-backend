package com.example.contestbackend.service;

import com.example.contestbackend.dto.UserDto;
import com.example.contestbackend.model.User;
import com.example.contestbackend.model.VerificationToken;
import com.example.contestbackend.repository.UserRepository;
import com.example.contestbackend.repository.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.UUID;

import static org.springframework.web.util.HtmlUtils.htmlEscape;


@Service
@RequiredArgsConstructor
public class EmailVerificationService {
    private  final VerificationTokenRepository verificationTokenRepository;
    private final EmailService emailService;
    private final UserRepository userRepository;
    private final UserService userService;

    public String generateVerificationToken(){
        return UUID.randomUUID().toString();
    }
    public void sendVerificationEmail(UserDto userDto, String email, String verificationToken) throws MessagingException {
        String from = "damian.rocha00@gmail.com";

        if (userService.getUserByEmail(email) != null) {
            throw new RuntimeException("User with email " + email + " already exists");
        }

        String verificationLink = "<a href=\"http://localhost:8080/verify?email=" + email + "&token=" + verificationToken + "\">link weryfikacyjny.</a>";

        String subject = "Weryfikacja e-mail";

        String text = "Zweryfikuj swój adres e-mail.<br />" + verificationLink;

        emailService.sendHtmlMail(from, email, subject, text);

        User user = userService.createUser(userDto);

        VerificationToken codeEntity = new VerificationToken(verificationToken, user);
        verificationTokenRepository.save(codeEntity);
    }

    public void setEnableTrue(String email) {
        User user = userRepository.findUserByEmail(email);
        if (user != null) {
            user.setEnabled(true);
            userRepository.save(user);
        } else {
            // Handle the case where the user is not found
        }
    }

    public boolean verifyToken(String email, String verificationToken) {
        VerificationToken storedToken = verificationTokenRepository.findByUserEmail(email);
        if (storedToken != null && storedToken.getToken().equals(verificationToken)) {
            verificationTokenRepository.delete(storedToken);
            return true;
        }
        return false;
    }

}

