package si.fri.rso.skupina3.user.v1.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

@Liveness
@ApplicationScoped
public class CustomGoogleApiHealthCheck implements HealthCheck {

    private static final Logger LOG = Logger.getLogger(CustomGoogleApiHealthCheck.class.getSimpleName());
    String url =  "http://www.google.si";

    @Override
    public HealthCheckResponse call() {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("HEAD");

            if (connection.getResponseCode() == 200) {
                return HealthCheckResponse.up(CustomGoogleApiHealthCheck.class.getSimpleName());
            }
        } catch (Exception exception) {
            LOG.severe(exception.getMessage());
        }
        return HealthCheckResponse.up(CustomGoogleApiHealthCheck.class.getSimpleName());

//        return HealthCheckResponse.down(CustomGoogleApiHealthCheck.class.getSimpleName());
    }
}