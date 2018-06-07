package com.sensedia.userbets.domain.infra.security;

public interface SecurityConstants {
  // Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiJkNjA2ZjBiNi0xY2I1LTQwN2QtYjc0ZC02MDlhNTczMDZmNjAiLCJ1c2VyTmFtZSI6IkFsZXhhbmRyZSBPemVsbyJ9.oCQi1ieddGx9rxGcMP2iyJzEq5QbMog39q0nQszHlqw
  String SECRET = "52706AFD6378A019D85FE1F90B4ED56DEF56AD13397463272A7014FF75BC6339";
  String TOKEN_PREFIX = "Bearer ";
  String HEADER_STRING = "Authorization";
}