package seminars;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import seminars.repository.ConstellationRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Тесты для выполнения ConstellationRepository")
class ConstellationRepositoryTest {

    private static final String CONSTELLATION_1 = "testConstellation1";

    private static final String CONSTELLATION_2 = "testConstellation2";

    private ConstellationRepository repository;

    @BeforeEach
    void setup() {
        repository = new ConstellationRepository();
    }

    @Test
    @DisplayName("Добавление нескольких группировок должно сохранять их все")
    void testRepository() {
        SatelliteConstellation constellation1 = new SatelliteConstellation(CONSTELLATION_1);
        SatelliteConstellation constellation2 = new SatelliteConstellation(CONSTELLATION_2);

        repository.addConstellation(constellation1);
        repository.addConstellation(constellation2);

        assertTrue(repository.containsConstellation(CONSTELLATION_1));
        assertTrue(repository.containsConstellation(CONSTELLATION_2));
        assertEquals(3, repository.getAllConstellations().size());
    }
}