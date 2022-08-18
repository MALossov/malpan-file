package com.malpan.file.util;

import java.util.Random;

public class CodeUtil {

    public static String CodeGenerate(int length) {

        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(length);

        for (int i = 1; i <= length; i++) {
            char baseCharacter = (char) random.nextInt(2) % 2 == 0 ? 'A' : 'a';
            char character = (char) (random.nextInt(2) % 2 == 0 ? (baseCharacter + random.nextInt(26)) : ('0' + random.nextInt(10)));
            stringBuilder.append(character);
        }
        return stringBuilder.toString();
    }
}
