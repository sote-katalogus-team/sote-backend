package com.katalogus.project.utility;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RandomCodeGenerator {
    
    public static String codeGenerator(){
        int lengthOfCode=7;
        String characters="abcdefghijklmnopqrstuvwxyz0123456789";
        List<String> list = Arrays.asList(characters.split(""));
        Collections.shuffle(list);
        return list.stream().limit(lengthOfCode).collect(Collectors.joining());
    }
}
