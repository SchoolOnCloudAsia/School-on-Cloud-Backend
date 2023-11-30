import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usermetrics")
public class UserMetricsController {

    private final UserMetricsRepository userMetricsRepository;

    @Autowired
    public UserMetricsController(UserMetricsRepository userMetricsRepository) {
        this.userMetricsRepository = userMetricsRepository;
    }

    @PostMapping
    public UserMetrics createUserMetrics(@RequestBody UserMetrics userMetrics) {
        // Check if a record with the same UserID exists
        UserMetrics existingUserMetrics = userMetricsRepository.findByID(userMetrics.getUserID()).orElse(null);

        if (existingUserMetrics != null) {
            // Update the existing record
            existingUserMetrics.setV(userMetrics.getV());
            existingUserMetrics.setA(userMetrics.getA());
            existingUserMetrics.setK(userMetrics.getK());
            return userMetricsRepository.save(existingUserMetrics);
        } else {
            // Create a new record
            return userMetricsRepository.save(userMetrics);
        }
    }

    @GetMapping("/{userID}")
    public UserMetrics getUserMetrics(@PathVariable String userID) {
        return userMetricsRepository.findByID(userID).orElse(null);
    }
}