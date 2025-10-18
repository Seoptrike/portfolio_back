package com.backend.domain.stack;

import lombok.Data;

@Data
public class UserStackResponseDTO {
  private Long stackId;
  private String name;
  private Integer usageCount;
  private Integer categoryId;
}
