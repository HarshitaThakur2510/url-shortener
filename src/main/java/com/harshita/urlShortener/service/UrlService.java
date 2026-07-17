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

@Service 
public class UrlService {

    private final UrlRepository urlRepository; 
    private final Base62Generator base62Generator;

    public UrlService(UrlRepository urlRepository,  Base62Generator base62Generator) { 
        this.urlRepository = urlRepository;
        this.base62Generator = base62Generator;
    }

    public UrlResponse createShortUrl(UrlRequest request) {

        String shortCode = generateUniqueShortCode();
        Url url = Url.builder()
                .originalUrl(request.getOriginalUrl())  
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

    private String generateUniqueShortCode() {

        String shortCode;

        do {
            shortCode = base62Generator.generateShortCode();
        } while (urlRepository.findByShortCode(shortCode).isPresent()); 
        return shortCode;
    }

    public String getOriginalUrl(String shortCode) {

        Url url = urlRepository.findByShortCode(shortCode) 
                .orElseThrow(() ->
                        new UrlNotFoundException("Short URL not found"));

        url.setClickCount(url.getClickCount() + 1);

        urlRepository.save(url); 
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

    public void deleteUrl(String shortCode){

        Url url = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() ->
                        new UrlNotFoundException("Short URL not found"));

        urlRepository.delete(url);

    }
}
