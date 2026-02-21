package seminars;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import seminars.repository.ConstellationRepository;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Mock-тесты для ConstellationRepository")
public class ConstellationRepositoryMockTest {

    private static final String CONSTELLATION_NAME_1 = "testConstellation1";
    private static final String CONSTELLATION_NAME_2 = "testConstellation2";
    private static final String CONSTELLATION_NAME_3 = "testConstellation3";

    @Mock
    private ConstellationRepository repository;

    private SatelliteConstellation constellation1;
    private SatelliteConstellation constellation2;
    private SatelliteConstellation constellation3;

    @BeforeEach
    void setUp() {
        constellation1 = new SatelliteConstellation(CONSTELLATION_NAME_1);
        constellation2 = new SatelliteConstellation(CONSTELLATION_NAME_2);
        constellation3 = new SatelliteConstellation(CONSTELLATION_NAME_3);
    }

    // ТЕСТ ПРЕПОДАВАТЕЛЯ (оставляем как есть)
    @Test
    @DisplayName("Добавление нескольких группировок должно сохранять их все")
    void testRepository() {
        Map<String, SatelliteConstellation> constellations = Map.of(
                CONSTELLATION_NAME_1, constellation1,
                CONSTELLATION_NAME_2, constellation2
        );

        when(repository.containsConstellation(CONSTELLATION_NAME_1)).thenReturn(true);
        when(repository.containsConstellation(CONSTELLATION_NAME_2)).thenReturn(true);
        when(repository.getAllConstellations()).thenReturn(constellations);

        assertTrue(repository.containsConstellation(CONSTELLATION_NAME_1));
        assertTrue(repository.containsConstellation(CONSTELLATION_NAME_2));
        assertEquals(2, repository.getAllConstellations().size());
    }

    @Test
    @DisplayName("addConstellation - проверка сохранения группировки")
    void testAddConstellation() {
        // Arrange
        doNothing().when(repository).addConstellation(constellation1);

        // Act
        repository.addConstellation(constellation1);

        // Assert
        verify(repository, times(1)).addConstellation(constellation1);
    }

    @Test
    @DisplayName("getConstellation должен возвращать группировку по имени")
    void testGetConstellation() {
        // Arrange
        when(repository.getConstellation(CONSTELLATION_NAME_1)).thenReturn(constellation1);

        // Act
        SatelliteConstellation result = repository.getConstellation(CONSTELLATION_NAME_1);

        // Assert
        assertNotNull(result);
        assertEquals(CONSTELLATION_NAME_1, result.getConstellationName());
        verify(repository, times(1)).getConstellation(CONSTELLATION_NAME_1);
    }

    @Test
    @DisplayName("getConstellation должен выбрасывать исключение для несуществующей группировки")
    void testGetConstellationThrowsException() {
        // Arrange
        when(repository.getConstellation("NonExistent"))
                .thenThrow(new RuntimeException("Группировка не найдена: NonExistent"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> repository.getConstellation("NonExistent"));

        assertEquals("Группировка не найдена: NonExistent", exception.getMessage());
        verify(repository, times(1)).getConstellation("NonExistent");
    }

    @Test
    @DisplayName("updateConstellation должен вызываться с правильными параметрами")
    void testUpdateConstellation() {
        // Arrange
        SatelliteConstellation updatedConstellation =
                new SatelliteConstellation(CONSTELLATION_NAME_1 + "_UPDATED");
        doNothing().when(repository).updateConstellation(CONSTELLATION_NAME_1, updatedConstellation);

        // Act
        repository.updateConstellation(CONSTELLATION_NAME_1, updatedConstellation);

        // Assert
        verify(repository, times(1))
                .updateConstellation(CONSTELLATION_NAME_1, updatedConstellation);
    }

    @Test
    @DisplayName("containsConstellation должен возвращать true для существующей группировки")
    void testContainsConstellationTrue() {
        // Arrange
        when(repository.containsConstellation(CONSTELLATION_NAME_1)).thenReturn(true);

        // Act
        boolean result = repository.containsConstellation(CONSTELLATION_NAME_1);

        // Assert
        assertTrue(result);
        verify(repository, times(1)).containsConstellation(CONSTELLATION_NAME_1);
    }

    @Test
    @DisplayName("containsConstellation должен возвращать false для несуществующей группировки")
    void testContainsConstellationFalse() {
        // Arrange
        when(repository.containsConstellation("NonExistent")).thenReturn(false);

        // Act
        boolean result = repository.containsConstellation("NonExistent");

        // Assert
        assertFalse(result);
        verify(repository, times(1)).containsConstellation("NonExistent");
    }

    @Test
    @DisplayName("getAllConstellations должен возвращать Map со всеми группировками")
    void testGetAllConstellations() {
        // Arrange
        Map<String, SatelliteConstellation> expected = Map.of(
                CONSTELLATION_NAME_1, constellation1,
                CONSTELLATION_NAME_2, constellation2,
                CONSTELLATION_NAME_3, constellation3
        );
        when(repository.getAllConstellations()).thenReturn(expected);

        // Act
        Map<String, SatelliteConstellation> result = repository.getAllConstellations();

        // Assert
        assertNotNull(result);
        assertEquals(3, result.size());
        assertTrue(result.containsKey(CONSTELLATION_NAME_1));
        assertTrue(result.containsKey(CONSTELLATION_NAME_2));
        assertTrue(result.containsKey(CONSTELLATION_NAME_3));
        verify(repository, times(1)).getAllConstellations();
    }

    @Test
    @DisplayName("getAllConstellations для пустого репозитория должен возвращать пустую Map")
    void testGetAllConstellationsEmpty() {
        // Arrange
        Map<String, SatelliteConstellation> empty = Map.of();
        when(repository.getAllConstellations()).thenReturn(empty);

        // Act
        Map<String, SatelliteConstellation> result = repository.getAllConstellations();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        assertEquals(0, result.size());
        verify(repository, times(1)).getAllConstellations();
    }

}