package ru.nedra.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nedra.model.ClickCounter;
import ru.nedra.repository.ClickCounterRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ClickCounterServiceImpl implements ClickCounterService {

    private final ClickCounterRepository repository;

    @Override
    public void click(){
        Optional<ClickCounter> clickCounterOptional = repository.findAll().stream().findAny();

        if (clickCounterOptional.isEmpty()) {
            throw new IllegalArgumentException();
        }

        ClickCounter clickCounter = clickCounterOptional.get();
        clickCounter.setClicksCount(clickCounter.getClicksCount() + 1);
        repository.save(clickCounter);
    }

    @Override
    public Long getCounter() {
        return repository.findAll().stream().findAny().map(ClickCounter::getClicksCount).orElseThrow(RuntimeException::new);
    }
}
