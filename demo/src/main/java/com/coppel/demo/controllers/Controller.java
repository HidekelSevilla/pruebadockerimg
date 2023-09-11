package com.coppel.demo.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Value("${oauth.config.json}")
    private String oauthConfigJsonPath;

    @GetMapping("/read-json")
    public String readJson() throws IOException {
        // Cargar el contenido del archivo JSON
        System.out.println("oauthConfigJsonPath: " + oauthConfigJsonPath);
        String jsonContent = loadJsonContent(oauthConfigJsonPath);

        return "Contenido del JSON: " + jsonContent;
    }

    private String loadJsonContent(String jsonFilePath) throws IOException {
        // Leer el contenido del archivo JSON desde el classpath
        ClassPathResource resource = new ClassPathResource(jsonFilePath);
        byte[] bytes = Files.readAllBytes(Paths.get(resource.getURI()));
        return new String(bytes);
    }

    @GetMapping("/")
    public String home() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "${oauth.config.json}";
    }

    @GetMapping("/hola")
    public String homee() {
        return "Hola Mundo";
    }

}
