package com.fooddelivery.login.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "login_audits")
@Data
@Builder
public class LoginAudit {

    @Id
    private String id;
    private String username;
    private boolean success;
    private String ipAddress;
    private LocalDateTime loginTime;
}
