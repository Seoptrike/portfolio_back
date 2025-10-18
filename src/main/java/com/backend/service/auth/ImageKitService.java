package com.backend.service.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ImageKitService {
  public void deleteFileSafe(String fileId) {
    if (fileId == null || fileId.isBlank()) return;
    try {
      var resp = io.imagekit.sdk.ImageKit.getInstance().deleteFile(fileId);
      log.info("ImageKit delete ok. fileId={}", fileId);
    } catch (Exception e) {
      log.warn("ImageKit delete failed. fileId={}", fileId, e);
    }
  }
}
