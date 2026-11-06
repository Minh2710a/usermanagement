package com.example.demo.controller;

import com.example.demo.model.AuditLog;
import com.example.demo.service.AuditLogService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/audit-logs")
public class AuditLogController {

    private final AuditLogService auditLogService;

    public AuditLogController(AuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('user_create')")
    public Page<AuditLog> getAuditLogs(
            @PageableDefault(
                size = 20,
                sort = "createdAt",
                direction = Sort.Direction.DESC
            ) Pageable pageable) {

        return auditLogService.getAuditLogs(pageable);
    }
}