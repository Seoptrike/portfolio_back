package com.backend.domain.about;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AboutMeCreateRequestDTO {
  private String username;
  private String title;
  private String content;
}
