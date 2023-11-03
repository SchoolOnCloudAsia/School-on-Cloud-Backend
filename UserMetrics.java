import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ID;

@Entity
public class UserMetrics {
    @ID
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private String userID;
    private float V;
    private float A;
    private float K;

    // Constructors, getters, and setters
}