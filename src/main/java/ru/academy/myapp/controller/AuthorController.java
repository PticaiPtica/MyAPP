package ru.academy.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.academy.myapp.entity.Author;
import ru.academy.myapp.repository.AuthorRepository;

import java.util.List;

@Controller
@RequestMapping("/api/author")
public class AuthorController {
    @Autowired
    AuthorRepository authorRepository;


    @GetMapping("/")
    public ResponseEntity<List<Author>> getAll() {

        List<Author> authors = authorRepository.findAll();
        if (authors.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(authors);
    }


    //Read One
    @GetMapping("/{id}")
    public ResponseEntity<Author> getById(@PathVariable Long id) {
        return authorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Create

    @PostMapping("/")
    public ResponseEntity<Author> create(@RequestBody Author author) {
        Author newAuthor = authorRepository.save(author);
        return ResponseEntity.ok(newAuthor);
    }


    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        return authorRepository.findById(id).map(author -> {
                    authorRepository.delete(author);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());

    }


}
