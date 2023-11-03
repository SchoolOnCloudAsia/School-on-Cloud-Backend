import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserMetricsRepositoryTest {

    @Autowired
    private UserMetricsRepository userMetricsRepository;

    @Test
    public void testSaveAndFindByID() {
        // Create a UserMetrics object
        UserMetrics userMetrics = new UserMetrics();
        userMetrics.setUserID("testUser");
        userMetrics.setV(1.0f);
        userMetrics.setA(2.0f);
        userMetrics.setK(3.0f);

        // Save the object to the database
        userMetricsRepository.save(userMetrics);

        // Retrieve it and assert its values
        UserMetrics retrievedUserMetrics = userMetricsRepository.findByID("testUser").orElse(null);
        assertNotNull(retrievedUserMetrics);
        assertEquals("testUser", retrievedUserMetrics.getUserID());
        assertEquals(1.0f, retrievedUserMetrics.getV());
        assertEquals(2.0f, retrievedUserMetrics.getA());
        assertEquals(3.0f, retrievedUserMetrics.getK());
    }
}
