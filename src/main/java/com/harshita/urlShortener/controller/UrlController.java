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

@RestController // handles HTTP req and return JSON..Without it, Spring won't recognize this class as a controller
@RequestMapping("/api/urls") // this is base url
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) { //connects controller to services
        this.urlService = urlService;
    }

    @PostMapping // method handles POST /api/urls
    public ResponseEntity<UrlResponse> shortenUrl(
            @Valid @RequestBody UrlRequest request) { // automatically converts this JSON into UrlRequest

        UrlResponse response = urlService.createShortUrl(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED); // helps us control response body, http
    }

//    @GetMapping("/{shortCode}") // /(value of shortcode)
//    // if someone visits site using short code this method open the link to which short code is assigned
//    // this method returns httpStatus code and loc header if url is found then hhtp code is 320 and in loc header org url is passed
//    public ResponseEntity<Void> redirectToOriginalUrl(
//            @PathVariable String shortCode ) { // pathVairable extracts shortcode from link eg:localhost:8080/XYZ99 it will extract XYZ99
//
//        String originalUrl = urlService.getOriginalUrl(shortCode); // looks for orignal url acc to short code
//
//        return ResponseEntity.status(HttpStatus.FOUND)
//                .location(URI.create(originalUrl))// adds org url to location header in hhtp response
//                .build(); //send response to client.. i am done building the response
//    }

    @GetMapping("/details/{shortCode}") // mapping for url details
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

