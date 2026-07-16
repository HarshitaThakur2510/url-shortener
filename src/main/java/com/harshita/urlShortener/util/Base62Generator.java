package com.harshita.urlShortener.util;
//we used Base62 because if we used only no. then it would become very long so we use A-Z a-z 0-9 that is 62 characters
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class Base62Generator {

    private static final String CHARACTERS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    private static final int SHORT_CODE_LENGTH = 6;

    private final SecureRandom random = new SecureRandom(); // used to generate random values and it is better that random

    public String generateShortCode() {

        StringBuilder shortCode = new StringBuilder();

        for (int i = 0; i < SHORT_CODE_LENGTH; i++) {

            int index = random.nextInt(CHARACTERS.length()); //random.nextInt(62): a random value between 0-61 will be stored in index

            shortCode.append(CHARACTERS.charAt(index));
        }

        return shortCode.toString();
    }

}
