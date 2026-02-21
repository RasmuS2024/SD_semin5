package seminars;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import seminars.repository.ConstellationRepository;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тесты для выполнения ConstellationRepository")
class ConstellationRepositoryTest {

    private static final String CONSTELLATION_NAME_1 = "testConstellation1";

    private static final String CONSTELLATION_NAME_2 = "testConstellation2";

    private static final String CONSTELLATION_NAME_3 = "testConstellation3";

    private static final String NON_EXIST_NAME = "nonExist";

    private ConstellationRepository repository;

    @BeforeEach
    void setup() {
        repository = new ConstellationRepository();
    }

    @Test
    @DisplayName("Добавление нескольких группировок должно сохранять их все")
    void testRepository() {
        SatelliteConstellation constellation1 = new SatelliteConstellation(CONSTELLATION_NAME_1);
        SatelliteConstellation constellation2 = new SatelliteConstellation(CONSTELLATION_NAME_2);
        SatelliteConstellation constellation3 = new SatelliteConstellation(CONSTELLATION_NAME_3);

        repository.addConstellation(constellation1);
        repository.addConstellation(constellation2);
        repository.addConstellation(constellation3);

        assertTrue(repository.containsConstellation(CONSTELLATION_NAME_1));
        assertTrue(repository.containsConstellation(CONSTELLATION_NAME_2));
        assertTrue(repository.containsConstellation(CONSTELLATION_NAME_3));
        assertEquals(3, repository.getAllConstellations().size());
    }

    @Test
    @DisplayName("Добавление группировки и получение по имени должно возвращать ту же группировку")
    void addAndGetConstellation_ShouldReturnSameConstellation() {
        // given
        SatelliteConstellation constellation = new SatelliteConstellation(CONSTELLATION_NAME_1);

        // when
        repository.addConstellation(constellation);

        // then
        SatelliteConstellation retrieved = repository.getConstellation(CONSTELLATION_NAME_1);
        assertSame(constellation, retrieved);
        assertTrue(repository.containsConstellation(CONSTELLATION_NAME_1));
    }

    @Test
    @DisplayName("Добавление двух группировок с одинаковым именем заменяет предыдущую")
    void addDuplicateConstellation_ShouldReplaceOldOne() {
        // given
        SatelliteConstellation first = new SatelliteConstellation(CONSTELLATION_NAME_1);
        SatelliteConstellation second = new SatelliteConstellation(CONSTELLATION_NAME_1);

        // when
        repository.addConstellation(first);
        repository.addConstellation(second);

        // then
        SatelliteConstellation retrieved = repository.getConstellation(CONSTELLATION_NAME_1);
        assertSame(second, retrieved);
        assertEquals(1, repository.getAllConstellations().size());
    }

    @Test
    @DisplayName("Получение несуществующей группировки должно выбрасывать исключение с правильным сообщением")
    void getNonExistentConstellation_ShouldThrowException() {
        // when & then
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> repository.getConstellation(NON_EXIST_NAME));
        assertEquals("Группировка не найдена: " + NON_EXIST_NAME, exception.getMessage());
    }

    @Test
    @DisplayName("Обновление существующей группировки должно изменить данные")
    void updateExistingConstellation_ShouldUpdateData() {
        // given
        SatelliteConstellation original = new SatelliteConstellation(CONSTELLATION_NAME_1);
        repository.addConstellation(original);
        SatelliteConstellation updated = new SatelliteConstellation(CONSTELLATION_NAME_1); // новое состояние

        // when
        repository.updateConstellation(CONSTELLATION_NAME_1, updated);

        // then
        SatelliteConstellation retrieved = repository.getConstellation(CONSTELLATION_NAME_1);
        assertSame(updated, retrieved);
    }

    @Test
    @DisplayName("Обновление несуществующей группировки не должно добавлять новую")
    void updateNonExistentConstellation_ShouldNotAddNew() {
        // given
        SatelliteConstellation constellation = new SatelliteConstellation(CONSTELLATION_NAME_1);

        // when
        repository.updateConstellation(CONSTELLATION_NAME_1, constellation);

        // then
        assertFalse(repository.containsConstellation(CONSTELLATION_NAME_1));
        assertTrue(repository.getAllConstellations().isEmpty());
    }

    @Test
    @DisplayName("Удаление существующей группировки должно убрать её из репозитория")
    void deleteExistingConstellation_ShouldRemoveIt() {
        // given
        SatelliteConstellation constellation = new SatelliteConstellation(CONSTELLATION_NAME_1);
        repository.addConstellation(constellation);

        // when
        repository.deleteConstellation(CONSTELLATION_NAME_1);

        // then
        assertFalse(repository.containsConstellation(CONSTELLATION_NAME_1));
        assertTrue(repository.getAllConstellations().isEmpty());
    }

    @Test
    @DisplayName("Удаление несуществующей группировки не должно влиять на состояние")
    void deleteNonExistentConstellation_ShouldNotChangeState() {
        // given
        SatelliteConstellation constellation = new SatelliteConstellation(CONSTELLATION_NAME_1);
        repository.addConstellation(constellation);

        // when
        repository.deleteConstellation(NON_EXIST_NAME);

        // then
        assertTrue(repository.containsConstellation(CONSTELLATION_NAME_1));
        assertEquals(1, repository.getAllConstellations().size());
    }

    @Test
    @DisplayName("Метод getAllConstellations возвращает копию, не затрагивающую исходную")
    void getAllConstellations_ShouldReturnCopy() {
        // given
        SatelliteConstellation constellation = new SatelliteConstellation(CONSTELLATION_NAME_1);
        repository.addConstellation(constellation);

        // when
        Map<String, SatelliteConstellation> all = repository.getAllConstellations();
        all.clear(); // изменяем копию

        // then
        assertTrue(repository.containsConstellation(CONSTELLATION_NAME_1));
        assertEquals(1, repository.getAllConstellations().size());
    }

    @Test
    @DisplayName("Метод containsConstellation возвращает true для существующей и false для несуществующей")
    void containsConstellation_ShouldReturnCorrectBoolean() {
        // given
        SatelliteConstellation constellation = new SatelliteConstellation(CONSTELLATION_NAME_1);
        repository.addConstellation(constellation);

        // when & then
        assertTrue(repository.containsConstellation(CONSTELLATION_NAME_1));
        assertFalse(repository.containsConstellation(CONSTELLATION_NAME_2));
        assertFalse(repository.containsConstellation(NON_EXIST_NAME));
    }

}