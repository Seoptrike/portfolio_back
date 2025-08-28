package com.backend.controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/imagekit")
public class ImageKitController {
    @Value("${IMAGEKIT_PUBLIC_KEY}")  private String publicKey;
    @Value("${IMAGEKIT_URL_ENDPOINT}") private String urlEndpoint;
    @Value("${IMAGEKIT_PRIVATE_KEY}") private String privateKey;

    @GetMapping("/auth")
    public Map<String, String> auth() {
        String token = java.util.UUID.randomUUID().toString();

        long nowSec = java.time.Instant.now().getEpochSecond(); // ★ 초(s)
        long expire = nowSec + 5 * 60; // 5분 유효 (1시간 미만)

        String signature = hmacSha1(privateKey, token + expire); // ★ token + expire(초)

        Map<String, String> resp = new HashMap<>();
        resp.put("token", token);
        resp.put("expire", String.valueOf(expire)); // ★ 초 단위로 내려줌
        resp.put("signature", signature);           // ★ 위와 같은 값으로 계산한 서명
        resp.put("publicKey", publicKey);
        resp.put("urlEndpoint", urlEndpoint);
        return resp;
    }

    private static String hmacSha1(String key, String msg) {
        try {
            javax.crypto.Mac mac = javax.crypto.Mac.getInstance("HmacSHA1");
            mac.init(new javax.crypto.spec.SecretKeySpec(
                    key.getBytes(java.nio.charset.StandardCharsets.UTF_8), "HmacSHA1"));
            byte[] raw = mac.doFinal(msg.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : raw) sb.append(String.format("%02x", b)); // lower-case hex
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
