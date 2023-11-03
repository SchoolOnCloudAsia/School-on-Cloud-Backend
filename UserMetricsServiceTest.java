import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserMetricsServiceTest {

    @Autowired
    private UserMetricsService userMetricsService;

    @Mock
    private UserMetricsRepository userMetricsRepository;

    @Test
    public void testGetUserMetricsByUserID() {
        // Create a UserMetrics object
        UserMetrics userMetrics = new UserMetrics();
        userMetrics.setUserID("testUser");
        userMetrics.setV(1.0f);
        userMetrics.setA(2.0f);
        userMetrics.setK(3.0f);

        when(userMetricsRepository.findByID("testUser")).thenReturn(Optional.of(userMetrics));

        // Retrieve it using the service and assert its values
        UserMetrics retrievedUserMetrics = userMetricsService.getUserMetricsByUserID("testUser");
        assertNotNull(retrievedUserMetrics);
        assertEquals("testUser", retrievedUserMetrics.getUserID());
        assertEquals(1.0f, retrievedUserMetrics.getV());
        assertEquals(2.0f, retrievedUserMetrics.getA());
        assertEquals(3.0f, retrievedUserMetrics.getK());
    }
}
