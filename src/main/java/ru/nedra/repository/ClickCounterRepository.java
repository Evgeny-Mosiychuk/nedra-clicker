package ru.nedra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;
import ru.nedra.model.ClickCounter;

import javax.persistence.LockModeType;
import java.util.List;

@Repository
public interface ClickCounterRepository extends JpaRepository<ClickCounter, Integer> {

    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<ClickCounter> findAll();
}
