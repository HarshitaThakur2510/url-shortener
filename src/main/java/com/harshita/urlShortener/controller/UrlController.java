package com.harshita.urlShortener.controller;
import java.net.URI;

import com.harshita.urlShortener.dto.UrlDetailsResponse;
import com.harshita.urlShortener.dto.UrlRequest;
import com.harshita.urlShortener.dto.UrlResponse;
import com.harshita.urlShortener.service.UrlService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController 
@RequestMapping("/api/urls")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping
    public ResponseEntity<UrlResponse> shortenUrl(
            @Valid @RequestBody UrlRequest request) { 
        UrlResponse response = urlService.createShortUrl(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED); 
    }


    @GetMapping("/details/{shortCode}") 
    public ResponseEntity<UrlDetailsResponse> getUrlDetails(
            @PathVariable String shortCode) {

        System.out.println("Details API called: " + shortCode);

        UrlDetailsResponse response =
                urlService.getUrlDetails(shortCode);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{shortCode}")
    public ResponseEntity<Void> deleteUrl(
            @PathVariable String shortCode){

        urlService.deleteUrl(shortCode);

        return ResponseEntity.noContent().build();

    }

}

