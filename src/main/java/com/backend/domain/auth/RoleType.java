package com.backend.domain.auth;

import lombok.Getter;

public enum RoleType {
  USER(1, "ROLE_USER"),
  ADMIN(9, "ROLE_ADMIN"),
  WITHDRAWN(99, "ROLE_WITHDRAWN");

  private final int code;
  @Getter private final String roleName;

  RoleType(int code, String roleName) {
    this.code = code;
    this.roleName = roleName;
  }

  public static RoleType fromCode(int code) {
    for (RoleType r : values()) if (r.code == code) return r;
    throw new IllegalArgumentException("Unknown role code: " + code);
  }
}
