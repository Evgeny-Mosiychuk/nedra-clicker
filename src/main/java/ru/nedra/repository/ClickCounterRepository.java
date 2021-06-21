package ru.nedra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nedra.model.ClickCounter;

@Repository
public interface ClickCounterRepository extends JpaRepository<ClickCounter, Integer> {
}
