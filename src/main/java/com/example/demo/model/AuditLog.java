package com.example.demo.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Entity
@Table(name = "audit_logs")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "actor_keycloak_id")
    private String actorKeycloakId;

    @Column(name = "actor_username", nullable = false)
    private String actorUsername;

    @Column(nullable = false, length = 50)
    private String action;

    @Column(name = "target_type", nullable = false)
    private String targetType;

    @Column(name = "target_id")
    private String targetId;

    @Column(name = "target_username")
    private String targetUsername;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    protected AuditLog() {}

    public AuditLog(
            String actorKeycloakId,
            String actorUsername,
            String action,
            String targetType,
            String targetId,
            String targetUsername) {

        this.actorKeycloakId = actorKeycloakId;
        this.actorUsername = actorUsername;
        this.action = action;
        this.targetType = targetType;
        this.targetId = targetId;
        this.targetUsername = targetUsername;
    }

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now(ZoneOffset.UTC);
    }

    public Long getId() { return id; }
    public String getActorKeycloakId() { return actorKeycloakId; }
    public String getActorUsername() { return actorUsername; }
    public String getAction() { return action; }
    public String getTargetType() { return targetType; }
    public String getTargetId() { return targetId; }
    public String getTargetUsername() { return targetUsername; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}