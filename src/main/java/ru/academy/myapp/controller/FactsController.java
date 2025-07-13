package ru.academy.myapp.controller;


import org.springframework.web.bind.annotation.*;
import ru.academy.myapp.model.Fact;
import ru.academy.myapp.model.FactApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/myapp")
public class FactsController {

    private final String baseUrl = "https://catfact.ninja/fact";
    private final List<Fact> facts;
    private int count = 1;

    public FactsController() {
        facts = new ArrayList<>();

    }

    @GetMapping("/get")
    public ResponseEntity<?> getFacts() {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<FactApiResponse> response = restTemplate.getForEntity(baseUrl, FactApiResponse.class);


        if (response.getStatusCode().is2xxSuccessful()) {
            FactApiResponse resp = response.getBody();

            assert resp != null;
            Fact fact = new Fact(count++, resp.fact, resp.length);
            System.out.println(fact);
            facts.add(fact);

            return ResponseEntity.ok(resp);
        }

        return ResponseEntity.notFound().build();

    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {

        if (facts.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(facts);


    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFactById(@PathVariable("id") int id) {
        Optional<Fact> fact = facts
                .stream()
                .filter(i -> i.getId() == id)
                .findFirst();
        if (fact.isPresent()) {
            return ResponseEntity.ok(fact.get());
        }
        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFactById(@PathVariable("id") int id) {
        if (facts.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            facts.removeIf(i -> i.getId() == id);
            return ResponseEntity.ok().build();
        }
    }


}
