package com.katalogus.project.utility;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RandomCodeGenerator {

    public String codeGenerator() {
        int lengthOfCode = 7;
        String characters = "ABCDEFGHIJKLMNPQRSTUWXYZ123456789";
        List<String> list = Arrays.asList(characters.split(""));
        Collections.shuffle(list);
        return list.stream().limit(lengthOfCode).collect(Collectors.joining());
    }
}
