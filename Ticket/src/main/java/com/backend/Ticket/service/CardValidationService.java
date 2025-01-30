//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backend.Ticket.service;

import org.springframework.stereotype.Service;

@Service
public class CardValidationService {
    public CardValidationService() {
    }

    public boolean validateCard(String number) {
        int s1 = 0;
        int s2 = 0;
        String reverse = (new StringBuffer(number)).reverse().toString();

        for(int i = 0; i < reverse.length(); ++i) {
            int digit = Character.digit(reverse.charAt(i), 10);
            if (i % 2 == 0) {
                s1 += digit;
            } else {
                s2 += 2 * digit;
                if (digit >= 5) {
                    s2 -= 9;
                }
            }
        }

        if ((s1 + s2) % 10 == 0) {
            System.out.println("true");
        } else {
            System.out.println("FALSE");
        }

        return (s1 + s2) % 10 == 0;
    }
}
