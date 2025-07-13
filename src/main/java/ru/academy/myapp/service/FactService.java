package ru.academy.myapp.service;

import ru.academy.myapp.entity.Fact;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface FactService {

    Optional<Fact> save(Fact fact);


    Optional<Fact> findById(long id);

    List<Fact> findAll();

    boolean saveList(List<Fact> facts);

    void deleteById(long id);

    Optional<Boolean> existsById(long id);

    List<Fact> search(Long idOne, Long idTwo);

    Boolean deleteByDate(Date date);

    int size();

    Boolean deleteByFact(String fact);

    List<Fact> sortByFact(int page);


    Iterable<Fact> findAllFactsPagesAndSorted(int page, int size);

    Iterable<Fact> findFiveFactFromDate(int page);

    List<Fact> findAllFactsAndSorted(String column);

    List<Fact> sortAllFactsAndSortedForTwoParametrs(String column, String columnTwo);

    List<Fact> findFactFromChar(char ch);
}
