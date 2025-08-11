package com.example.demo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.util.List;

@RestController
public class FruitsController {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping(value = "/public/fruits", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Fruit> getFruits() {
        try {
            ClassPathResource resource = new ClassPathResource("fruits.json");
            InputStream inputStream = resource.getInputStream();
            return objectMapper.readValue(inputStream, new TypeReference<List<Fruit>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Failed to load fruits.json", e);
        }
    }
}
