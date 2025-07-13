package ru.academy.myapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.academy.myapp.entity.Fact;

import org.springframework.http.ResponseEntity;


import ru.academy.myapp.service.FactService;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/myApp")
public class FactsController {

    private final String baseUrl = "https://catfact.ninja/fact";
    private final FactService factService;


    @Autowired
    public FactsController(FactService factService) {

        this.factService = factService;
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<Fact>> getAllFacts() {
        List<Fact> facts;
        facts = factService.findAll();
        return ResponseEntity.ok(facts);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Fact> getFactById(@PathVariable("id") Long id) {
        Optional<Fact> fact;
        fact = factService.findById(id);
        if (fact.isPresent()) {
            return ResponseEntity.ok(fact.get());
        }
        return ResponseEntity.notFound().build();

    }

    @GetMapping("/fact/{id}")
    public ResponseEntity<?> getfactById(@PathVariable("id") Long id) {
        Optional<Fact> fact;
        fact = factService.findById(id);
        if (fact.isPresent()) {
            String factNew = fact.get().getFact();
            return ResponseEntity.ok(factNew);
        }
        return ResponseEntity.notFound().build();

    }

    @GetMapping("/add")
    public ResponseEntity<Fact> createFact() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Fact> response = restTemplate.getForEntity(baseUrl, Fact.class);
        System.out.println(response.getBody());

        if (response.getStatusCode().is2xxSuccessful()) {

            Optional<Fact> fact = factService.save(response.getBody());

            return ResponseEntity.ok(fact.get());
        }
        return ResponseEntity.badRequest().build();

    }

    @PostMapping("/saveList/")
    public ResponseEntity<?> saveList(@RequestBody List<Fact> facts) {

        System.out.println(facts);
        if (factService.saveList(facts)) {
            return ResponseEntity.ok(facts);
        }
        return ResponseEntity.badRequest().build();


    }

    @PostMapping("/save/")
    public ResponseEntity<Fact> saveFact(@RequestBody String fact) {
        System.out.println(fact.toString());
        Fact fact1 = new Fact();
        fact1.setFact(fact.toString());
        fact1.setLength(fact.length());
        factService.save(fact1);
        return ResponseEntity.ok(fact1);


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFactById(@PathVariable("id") Long id) {
        factService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> existsById(@PathVariable("id") Long id) {
        Optional<Boolean> b = factService.existsById(id);
        return b.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search/{idOne}&{idTwo}")
    public ResponseEntity<List<Fact>> listFacts(@PathVariable("idOne") Long idOne, @PathVariable("idTwo") Long idTwo) {
        List facts = factService.search(idOne, idTwo);
        if (facts.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(facts);


    }

    @DeleteMapping("/delete/{year}")
    public ResponseEntity<?> deleteFact(@PathVariable Date year) {

        if (factService.deleteByDate(year))
            return ResponseEntity.ok().build();
        else

            return ResponseEntity.badRequest().build();

    }

    @GetMapping("/size")
    public ResponseEntity<Integer> listFacts() {

        return ResponseEntity.ok(factService.size());
    }

    @DeleteMapping("/fact/")
    public ResponseEntity<Boolean> deleteFact(@RequestBody String fact) {
        System.out.println(fact);

        if (factService.deleteByFact(fact))
            return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }

    @GetMapping("/sort")
    public ResponseEntity<List<Fact>> sortFacts() {
        int page = 1;
        if (factService.sortByFact(page).isEmpty()) {
            return ResponseEntity.badRequest().build();
        } else

            return ResponseEntity.ok(factService.sortByFact(page));
    }

    @GetMapping("/pages/{page}&{size}")
    public Iterable<Fact> pagedFacts(@PathVariable int page, @PathVariable int size) {
        return factService.findAllFactsPagesAndSorted(page, size);

    }

    @GetMapping("/pageDate")
    public Iterable<Fact> pagedDate() {
        return factService.findFiveFactFromDate(2);
    }

    @GetMapping("/sort/{str}")
    public ResponseEntity<List<Fact>> sortFactsAll(@PathVariable String str) {

        List<Fact> facts = factService.findAllFactsAndSorted(str);
        if (facts.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(facts);
    }

    @GetMapping("/sort/{str}/{strTwo}")
    public ResponseEntity<List<Fact>> sortAllFactsAndSortedForTwoParametrs(@PathVariable String str, @PathVariable String strTwo) {

        List<Fact> facts = factService.sortAllFactsAndSortedForTwoParametrs(str, strTwo);
        if (facts.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(facts);
    }

    @GetMapping("/filterChar/{ch}")
    public ResponseEntity<List<Fact>> filterFactsByChar(@PathVariable char ch) {
        if (factService.findFactFromChar(ch).isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(factService.findFactFromChar(ch));

    }











/*
    @GetMapping("/get")
    public ResponseEntity<?> getFacts() {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<FactApiResponse> response = restTemplate.getForEntity(baseUrl, FactApiResponse.class);


        if (response.getStatusCode().is2xxSuccessful()) {
            FactApiResponse resp = response.getBody();

            assert resp != null;
            Fact fact = new Fact(resp);
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
    }*/


}
