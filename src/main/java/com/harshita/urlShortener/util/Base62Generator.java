package com.harshita.urlShortener.util;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class Base62Generator {

    private static final String CHARACTERS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    private static final int SHORT_CODE_LENGTH = 6;

    private final SecureRandom random = new SecureRandom(); 
    public String generateShortCode() {

        StringBuilder shortCode = new StringBuilder();

        for (int i = 0; i < SHORT_CODE_LENGTH; i++) {

            int index = random.nextInt(CHARACTERS.length()); 

            shortCode.append(CHARACTERS.charAt(index));
        }

        return shortCode.toString();
    }

}
