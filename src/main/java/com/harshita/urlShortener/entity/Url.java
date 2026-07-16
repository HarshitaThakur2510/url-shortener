package com.harshita.urlShortener.entity;

import jakarta.persistence.*;
import lombok.*; //for cleaner code lombok is used

import java.time.LocalDateTime;

@Entity // converts java class into a database table
@Table(name = "urls") // explicitly telling to create a table called urls
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Url {

    @Id //SQL equivalent to PRIMARY KEY
    @GeneratedValue(strategy = GenerationType.IDENTITY) // automatically generates IDs
    private Long id;

    @Column(nullable = false) // clm cannot contain null value
    private String originalUrl;

    @Column(nullable = false, unique = true) //clm cam not have null and duplicate values
    private String shortCode;

    @Column(nullable = false)
    private Integer clickCount; // used Integer instead of int because int doesnt stores null values, so mostly Integer is used

    @Column(nullable = false)
    private LocalDateTime createdAt; // time when url is created
}