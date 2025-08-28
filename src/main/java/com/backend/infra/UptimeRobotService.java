package com.backend.infra;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class UptimeRobotService {
    private final WebClient webClient = WebClient.builder()
            .baseUrl("https://api.uptimerobot.com/v2")
            .build();

    @Value("${uptimerobot.apiKey}")
    private String apiKey;

    public JsonNode fetchMonitors(boolean includeLogs, boolean includeResponseTimes) {
        return webClient.post()
                .uri("/getMonitors")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromFormData("api_key", apiKey)
                        .with("format", "json")
                        .with("logs", includeLogs ? "1" : "0")
                        .with("response_times", includeResponseTimes ? "1" : "0")
                        .with("response_times_average", "30")     // 30분 평균(대시보드와 유사)
                        .with("custom_uptime_ratios", "7-30-90")) // 7/30/90일 업타임 비율
                .retrieve()
                .bodyToMono(JsonNode.class)
                .timeout(Duration.ofSeconds(15))
                .block();
    }
}
