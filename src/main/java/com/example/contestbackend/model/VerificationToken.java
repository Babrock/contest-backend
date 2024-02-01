package com.example.contestbackend.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "verification_tokens")
public class VerificationToken {
    private static final int EXPIRATION_TIME = 15;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_verification_token")
    private Integer id;

    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(nullable = false, name = "id_user")
    private User user;

    @Column(name = "expiry_date")
    private Date expiryDate;

    public VerificationToken(String token, User user) {
        this.token = token;
        this.user = user;
        this.expiryDate = getTokenExpirationTime();
    }

    public Date getTokenExpirationTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, EXPIRATION_TIME);
        return new Date(calendar.getTime().getTime());
    }
}