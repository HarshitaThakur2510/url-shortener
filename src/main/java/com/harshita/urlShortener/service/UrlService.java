package com.harshita.urlShortener.service;

import com.harshita.urlShortener.dto.UrlDetailsResponse;
import com.harshita.urlShortener.dto.UrlRequest;
import com.harshita.urlShortener.dto.UrlResponse;
import com.harshita.urlShortener.entity.Url;
import com.harshita.urlShortener.exception.UrlNotFoundException;
import com.harshita.urlShortener.repository.UrlRepository;
import com.harshita.urlShortener.util.Base62Generator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service //tells spring that this class contains business logic
public class UrlService {

    private final UrlRepository urlRepository; // we created variable urlRepository which stores UrlRepository's obj reference
    private final Base62Generator base62Generator;

    public UrlService(UrlRepository urlRepository,  Base62Generator base62Generator) { // Dependency injection ... we are connection service with repository
        this.urlRepository = urlRepository;
        this.base62Generator = base62Generator;
    }

    public UrlResponse createShortUrl(UrlRequest request) {

        String shortCode = generateUniqueShortCode();// generates unique short code

        Url url = Url.builder() // creates new Url obj
                .originalUrl(request.getOriginalUrl())  //equivalent to url.setOriginalUrl(...);
                .shortCode(shortCode)
                .clickCount(0)
                .createdAt(LocalDateTime.now())
                .build();

        urlRepository.save(url);

        return new UrlResponse(
                url.getOriginalUrl(),
                url.getShortCode()
        );
    }

    // method which generates unique short code
    private String generateUniqueShortCode() {

        String shortCode;

        do {
            shortCode = base62Generator.generateShortCode();
        } while (urlRepository.findByShortCode(shortCode).isPresent()); // if code found then it returns true which means value is duplicate so this loop runs and creates value until we get unique value

        return shortCode;
    }

    // returns org url if short code exists or handles error
    public String getOriginalUrl(String shortCode) {

        Url url = urlRepository.findByShortCode(shortCode) // looks for url if exists then return org url
                .orElseThrow(() ->
                        new UrlNotFoundException("Short URL not found"));// throws this msg if url doesnt exists

        url.setClickCount(url.getClickCount() + 1); // inc counter

        urlRepository.save(url); // updates the row ... if entity already has id then it updates else it inserts

        return url.getOriginalUrl();
    }


    public UrlDetailsResponse getUrlDetails(String shortCode) {
        System.out.println("Searching for: " + shortCode);
        Url url = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() ->
                        new UrlNotFoundException("Short URL not found"));

        return new UrlDetailsResponse(

                url.getOriginalUrl(),

                url.getShortCode(),

                url.getClickCount(),

                url.getCreatedAt()

        );

    }

    // Repository finds url if it exists then it is deleted
    public void deleteUrl(String shortCode){

        Url url = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() ->
                        new UrlNotFoundException("Short URL not found"));

        urlRepository.delete(url);

    }
}