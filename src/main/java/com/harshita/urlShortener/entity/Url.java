package com.harshita.urlShortener.entity;

import jakarta.persistence.*;
import lombok.*; 

import java.time.LocalDateTime;

@Entity 
@Table(name = "urls") 
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Url {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @Column(nullable = false) 
    private String originalUrl;

    @Column(nullable = false, unique = true) 
    private String shortCode;

    @Column(nullable = false)
    private Integer clickCount; 
    @Column(nullable = false)
    private LocalDateTime createdAt; 
