package com.backend.controller;

import com.backend.infra.UptimeRobotService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/uptimerobot")
@RequiredArgsConstructor
public class UptimeRobotController {
    private final UptimeRobotService service;

    @GetMapping("/monitors")
    public JsonNode getMonitors(
            @RequestParam(defaultValue = "true") boolean logs,
            @RequestParam(name = "responseTimes", defaultValue = "true") boolean responseTimes
    ){
        return service.fetchMonitors(logs,responseTimes);
    }
}
