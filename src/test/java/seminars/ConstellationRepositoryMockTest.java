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
    private static final String NON_EXIST_NAME = "nonExist";

    @Mock
    private ConstellationRepository repository;

    @Mock
    private SatelliteConstellation mockConstellation1;

    @Mock
    private SatelliteConstellation mockConstellation2;

    @Test
    @DisplayName("Добавление группировки должно вызывать getConstellationName ровно один раз")
    void addConstellation_ShouldCallGetNameOnce() {
        // When
        repository.addConstellation(mockConstellation1);

        // Then
        verify(mockConstellation1, times(1)).getConstellationName();
    }

    @Test
    @DisplayName("Получение существующей группировки должно возвращать тот же mock-объект")
    void getConstellation_WithExistingName_ShouldReturnMock() {
        // Given
        repository.addConstellation(mockConstellation1);

        // When
        SatelliteConstellation result = repository.getConstellation(CONSTELLATION_NAME_1);

        // Then
        assertSame(mockConstellation1, result);
        verify(mockConstellation1, atLeastOnce()).getConstellationName();
    }

    @Test
    @DisplayName("Получение несуществующей группировки должно выбрасывать исключение")
    void getConstellation_WithNonExistentName_ShouldThrowException() {
        // Given
        repository.addConstellation(mockConstellation1);

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> repository.getConstellation(NON_EXIST_NAME));

        assertEquals("Группировка не найдена: " + NON_EXIST_NAME, exception.getMessage());
        verify(mockConstellation1, never()).getConstellationName(); // метод не должен вызываться
    }

    @Test
    @DisplayName("Обновление существующей группировки должно использовать новый mock")
    void updateConstellation_WithExistingName_ShouldUseNewMock() {
        // Given
        repository.addConstellation(mockConstellation1);
        SatelliteConstellation newMock = mock(SatelliteConstellation.class);
        when(newMock.getConstellationName()).thenReturn(CONSTELLATION_NAME_1);

        // When
        repository.updateConstellation(CONSTELLATION_NAME_1, newMock);

        // Then
        SatelliteConstellation result = repository.getConstellation(CONSTELLATION_NAME_1);
        assertSame(newMock, result);
        assertNotSame(mockConstellation1, result);
    }

    @Test
    @DisplayName("Обновление несуществующей группировки не должно добавлять новый mock")
    void updateConstellation_WithNonExistentName_ShouldNotAddMock() {
        // Given
        repository.addConstellation(mockConstellation1);

        // When
        repository.updateConstellation(NON_EXIST_NAME, mockConstellation2);

        // Then
        assertTrue(repository.containsConstellation(CONSTELLATION_NAME_1));
        assertFalse(repository.containsConstellation(CONSTELLATION_NAME_2));
        assertEquals(1, repository.getAllConstellations().size());

        verify(mockConstellation2, never()).getConstellationName();
    }

    @Test
    @DisplayName("Удаление существующей группировки должно убрать mock из репозитория")
    void deleteConstellation_WithExistingName_ShouldRemoveMock() {
        // Given
        repository.addConstellation(mockConstellation1);
        repository.addConstellation(mockConstellation2);

        // When
        repository.deleteConstellation(CONSTELLATION_NAME_1);

        // Then
        assertFalse(repository.containsConstellation(CONSTELLATION_NAME_1));
        assertTrue(repository.containsConstellation(CONSTELLATION_NAME_2));
        assertEquals(1, repository.getAllConstellations().size());
    }

    @Test
    @DisplayName("Удаление несуществующей группировки не должно влиять на существующие mock-и")
    void deleteConstellation_WithNonExistentName_ShouldNotAffectExisting() {
        // Given
        repository.addConstellation(mockConstellation1);
        repository.addConstellation(mockConstellation2);

        // When
        repository.deleteConstellation(NON_EXIST_NAME);

        // Then
        assertTrue(repository.containsConstellation(CONSTELLATION_NAME_1));
        assertTrue(repository.containsConstellation(CONSTELLATION_NAME_2));
        assertEquals(2, repository.getAllConstellations().size());
    }

    @Test
    @DisplayName("containsConstellation должен возвращать true для добавленного mock-а")
    void containsConstellation_WithExistingMock_ShouldReturnTrue() {
        // Given
        repository.addConstellation(mockConstellation1);

        // When & Then
        assertTrue(repository.containsConstellation(CONSTELLATION_NAME_1));
        assertFalse(repository.containsConstellation(CONSTELLATION_NAME_2));
        assertFalse(repository.containsConstellation(NON_EXIST_NAME));
    }

    @Test
    @DisplayName("getAllConstellations должен возвращать Map со всеми mock-ами")
    void getAllConstellations_ShouldReturnMapWithAllMocks() {
        // Given
        repository.addConstellation(mockConstellation1);
        repository.addConstellation(mockConstellation2);

        // When
        Map<String, SatelliteConstellation> result = repository.getAllConstellations();

        // Then
        assertEquals(2, result.size());
        assertTrue(result.containsKey(CONSTELLATION_NAME_1));
        assertTrue(result.containsKey(CONSTELLATION_NAME_2));
        assertSame(mockConstellation1, result.get(CONSTELLATION_NAME_1));
        assertSame(mockConstellation2, result.get(CONSTELLATION_NAME_2));
    }

    @Test
    @DisplayName("getAllConstellations возвращает копию - изменение копии не влияет на оригинал")
    void getAllConstellations_ShouldReturnCopy_NotAffectingOriginal() {
        // Given
        repository.addConstellation(mockConstellation1);

        // When
        Map<String, SatelliteConstellation> result = repository.getAllConstellations();
        result.clear();

        // Then
        assertTrue(repository.containsConstellation(CONSTELLATION_NAME_1));
        assertEquals(1, repository.getAllConstellations().size());
    }

    @Test
    @DisplayName("Добавление двух группировок с одинаковым именем заменяет первый mock на второй")
    void addDuplicateConstellation_ShouldReplaceOldMock() {
        // Given
        SatelliteConstellation mockDuplicate = mock(SatelliteConstellation.class);
        when(mockDuplicate.getConstellationName()).thenReturn(CONSTELLATION_NAME_1);

        repository.addConstellation(mockConstellation1);

        // When
        repository.addConstellation(mockDuplicate);

        // Then
        SatelliteConstellation result = repository.getConstellation(CONSTELLATION_NAME_1);
        assertSame(mockDuplicate, result);
        assertNotSame(mockConstellation1, result);
        assertEquals(1, repository.getAllConstellations().size());
    }

}