package com.harshita.urlShortener.repository;

import com.harshita.urlShortener.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// we used interface instead of class becoz spring generates functions automatically like save() delete().... JpaRepository<Url, Long> creates url entity whose primary key is long
public interface UrlRepository extends JpaRepository<Url, Long> {

    Optional<Url> findByShortCode(String shortCode);

}