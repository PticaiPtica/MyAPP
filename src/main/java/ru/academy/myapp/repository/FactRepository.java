package ru.academy.myapp.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.academy.myapp.entity.Fact;

@Repository
public interface FactRepository extends CrudRepository<Fact, Long> {


}
