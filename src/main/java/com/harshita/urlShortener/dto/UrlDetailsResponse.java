package com.harshita.urlShortener.dto;
// created another response to give all details of url
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UrlDetailsResponse {

    private String originalUrl;
    private String shortCode;
    private Integer clickCount;
    private LocalDateTime createdAt;

}