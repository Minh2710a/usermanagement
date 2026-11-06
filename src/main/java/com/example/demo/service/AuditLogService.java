package com.example.demo.service;

import com.example.demo.model.AuditLog;
import com.example.demo.repository.AuditLogRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuditLogService {

    private final AuditLogRepository auditLogRepository;

    public AuditLogService(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    public void record(
            String action,
            String targetType,
            String targetId,
            String targetUsername) {

        Authentication auth =
                SecurityContextHolder.getContext().getAuthentication();

        String actorId = null;
        String actorUsername = "SYSTEM";

        if (auth instanceof JwtAuthenticationToken) {
            Jwt jwt = ((JwtAuthenticationToken) auth).getToken();

            actorId = jwt.getSubject();
            actorUsername = jwt.getClaimAsString("preferred_username");

            if (actorUsername == null || actorUsername.isBlank()) {
                actorUsername = actorId;
            }
        }

        AuditLog log = new AuditLog(
                actorId,
                actorUsername,
                action,
                targetType,
                targetId,
                targetUsername
        );

        auditLogRepository.save(log);
    }

    @Transactional(readOnly = true)
    public Page<AuditLog> getAuditLogs(Pageable pageable) {
        return auditLogRepository.findAll(pageable);
    }
}