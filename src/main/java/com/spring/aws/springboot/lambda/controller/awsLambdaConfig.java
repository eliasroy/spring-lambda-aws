package com.spring.aws.springboot.lambda.controller;


import com.spring.aws.springboot.lambda.domain.CharacterDomain;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Configuration
public class awsLambdaConfig {

    @RestController
    public static class LambdaController {

       @GetMapping("/api/hello")
        public ResponseEntity<String> hello() {
            return ResponseEntity.ok(helloSupplier().get());
        }

       @PostMapping("/api/print")
        public ResponseEntity<Void> prints(@RequestBody String message) {
            print().accept(message);
            return ResponseEntity.ok().build();
        }

        @PostMapping("/api/recibe")
        public ResponseEntity<String> recibes(@RequestBody String input) {
            return ResponseEntity.ok(recibe().apply(input));
        }

        @GetMapping("/api/create-characters")
        public ResponseEntity<Map<String, Object>> createCharacterss() {
            return ResponseEntity.ok(createCharacters().get());
        }

        @PostMapping("/api/receive-characters")
        public ResponseEntity<String> receiveCharacterss(@RequestBody Map<String, Object> characters) {
            return ResponseEntity.ok(receiveCharacters().apply(characters));
        }

        @PostMapping("/api/receive-object")
        public ResponseEntity<CharacterDomain> receiveObjects(@RequestBody CharacterDomain character) {
            return ResponseEntity.ok(receiveObject().apply(character));
        }

        @PostMapping("/api/process")
        public ResponseEntity<Map<String, Object>> processs(@RequestBody Map<String, Object> characters) {
            return ResponseEntity.ok(process().apply(characters));
        }
    }

    @Bean(name = "hello")
    public static Supplier<String> helloSupplier() {
        return () -> "Hello world!!!";
    }

    @Bean
    public static Consumer<String> print() {
        return System.out::println;
    }

    @Bean
    public static Function<String, String> recibe() {
        return String::toUpperCase;
    }

    @Bean
    public static Supplier<Map<String, Object>> createCharacters() {
        return () -> {
            Map<String, Object> characters = new HashMap<>();
            characters.put("name", "Skywalker");
            characters.put("last name", "Vader");
            characters.put("nickname", "Organa");
            return characters;
        };
    }

    @Bean
    public static Function<Map<String, Object>, String> receiveCharacters() {
        return (characters) -> {
            characters.forEach((key, value) -> System.out.println(key + " " + value));
            return "characters receive";
        };
    }

    @Bean
    public static Function<CharacterDomain, CharacterDomain> receiveObject() {
        return (character) -> character;
    }

    @Bean
    public static Function<Map<String, Object>, Map<String, Object>> process() {
        return (characters) -> {
            characters.forEach((key, value) -> System.out.println(key + " " + value));
            Map<String, Object> newCharacters = new HashMap<>();
            newCharacters.put("name", "Luke");
            newCharacters.put("last name", "Skywalker");
            newCharacters.put("nickname", "Luke");
            return newCharacters;
        };
    }
}