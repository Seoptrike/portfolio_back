package com.backend.config;

import io.imagekit.sdk.ImageKit;
import io.imagekit.sdk.config.Configuration;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;

@org.springframework.context.annotation.Configuration
public class ImageKitConfig {
  @Value("${IMAGEKIT_PUBLIC_KEY}")
  private String publicKey;

  @Value("${IMAGEKIT_PRIVATE_KEY}")
  private String privateKey;

  @Value("${IMAGEKIT_URL_ENDPOINT}")
  private String urlEndpoint;

  @PostConstruct
  public void init() {
    ImageKit.getInstance().setConfig(new Configuration(publicKey, privateKey, urlEndpoint));
  }
}
