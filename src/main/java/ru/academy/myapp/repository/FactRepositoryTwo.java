package ru.academy.myapp.repository;




import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.academy.myapp.entity.Fact;

@Repository
public interface FactRepositoryTwo extends PagingAndSortingRepository<Fact, Long> {


}