package ru.nedra.service;

import ru.nedra.model.ClickCounter;
import ru.nedra.repository.ClickCounterRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClickCounterServiceImplTest {

    private ClickCounterServiceImpl service;

    private static ClickCounterRepository clickCounterRepository;

    private static final List<ClickCounter> clickCounters = new ArrayList<>();

    @BeforeAll
    static void init() {
        clickCounterRepository = Mockito.mock(ClickCounterRepository.class);
        clickCounters.add(new ClickCounter(1, 42L));
        Mockito.when(clickCounterRepository.findAll()).thenReturn(clickCounters);
    }

    @BeforeEach
    void initService() {
        service = new ClickCounterServiceImpl(clickCounterRepository);
    }

    @Test
    void testCounterExists() {
        Long counter = service.getCounter();
        assertNotNull(counter);
    }

    @Test
    void testCounterNonNegative() {
        Long counter = service.getCounter();
        assertTrue(counter >= 0);
    }

    @Test
    @Transactional
    void testCounterIncrement() {
        Long before = service.getCounter();
        service.click();
        Long after = service.getCounter();
        assertEquals(before + 1, (long) after);
    }
}