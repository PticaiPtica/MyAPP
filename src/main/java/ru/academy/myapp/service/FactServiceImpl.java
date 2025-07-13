package ru.academy.myapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.academy.myapp.entity.Fact;
import ru.academy.myapp.repository.FactRepository;
import ru.academy.myapp.repository.FactRepositoryTwo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FactServiceImpl implements FactService {
    @Autowired
    private final FactRepository factRepository;
    @Autowired
    private final FactRepositoryTwo pagingAndSortingRepository;


    @Autowired
    public FactServiceImpl(FactRepository factRepository, FactRepositoryTwo pagingAndSortingRepository) {
        this.factRepository = factRepository;
        this.pagingAndSortingRepository = pagingAndSortingRepository;
    }

    @Override
    public Optional<Fact> save(Fact fact) {
        Date date = new Date();

        fact.setDate(date);
        factRepository.save(fact);
        System.out.println(fact.getDate());

        return factRepository.findById(fact.getId());
    }

    @Override
    public boolean saveList(List<Fact> facts) {
        Date date = new Date();
        if (!facts.isEmpty()) {
            for (Fact fact : facts) {
                fact.setDate(date);
                fact.setId(null);
                factRepository.save(fact);
            }
            return true;
        }
        return false;


    }

    @Override
    public Optional<Fact> findById(long id) {
        return factRepository.findById(id);
    }

    @Override
    public List<Fact> findAll() {
        return (List<Fact>) factRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        factRepository.deleteById(id);

    }

    @Override
    public Optional<Boolean> existsById(long id) {

        return Optional.of(factRepository.existsById(id));
    }

    @Override
    public List<Fact> search(Long idOne, Long idTwo) {
        List<Fact> facts = new ArrayList<>();
        List<Fact> allFacts = (List<Fact>) factRepository.findAll();
        for (Fact fact : allFacts) {
            if (fact.getId() >= idOne && fact.getId() <= idTwo) {
                facts.add(fact);
            }
        }
        return facts;
    }

    @Override
    public Boolean deleteByDate(Date date) {

        try {
            List<Fact> facts = (List<Fact>) factRepository.findAll();

            for (Fact fact : facts) {

                if (fact.getDate().equals(date)) {
                    factRepository.deleteById(fact.getId());
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public int size() {
        List<Fact> facts = (List<Fact>) factRepository.findAll();
        return facts.size();
    }

    @Override
    public Boolean deleteByFact(String fact) {
        List<Fact> facts = (List<Fact>) factRepository.findAll();
        for (Fact fact1 : facts) {
            if (fact1.getFact().equals(fact)) {
                factRepository.deleteById(fact1.getId());

                return true;
            }
        }

        return false;
    }

    @Override
    public List<Fact> sortByFact(int page) {

        Sort sort = Sort.by("fact");

        return (List<Fact>) pagingAndSortingRepository.findAll(sort);


    }

    @Override
    public Iterable<Fact> findAllFactsPagesAndSorted(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("fact").ascending());
        return pagingAndSortingRepository.findAll(pageable);
    }


    @Override
    public Iterable<Fact> findFiveFactFromDate(int page) {

        Pageable pageable = PageRequest.of(page, 5, Sort.by("date").ascending());
        return pagingAndSortingRepository.findAll(pageable);

    }

    @Override
    public List<Fact> findAllFactsAndSorted(String column) {
        Sort sort = Sort.by(column);


        return (List<Fact>) pagingAndSortingRepository.findAll(sort);
    }

    @Override
    public List<Fact> sortAllFactsAndSortedForTwoParametrs(String column, String columnTwo) {
        Sort sort = Sort.by(column, columnTwo);


        return (List<Fact>) pagingAndSortingRepository.findAll(sort);
    }

    @Override
    public List<Fact> findFactFromChar(char ch) {
        Sort sort = Sort.by("fact");
        ArrayList<Fact> factsFromChar = new ArrayList<>();
        ArrayList<Fact> allFacts = (ArrayList<Fact>) pagingAndSortingRepository.findAll(sort);
        for (Fact fact : allFacts) {
            char c = fact.getFact().charAt(0);
            if (c == ch) {
                factsFromChar.add(fact);
            }
        }
        return factsFromChar;

    }


}
