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
public class CustomRvParkCatalogHealthCheck implements HealthCheck {

//    private static final Logger LOG = Logger.getLogger(CustomRvParkCatalogHealthCheck.class.getSimpleName());
//    String url =  "http://rv-park-catalog:8089/v1/parks";

    @Override
    public HealthCheckResponse call() {
//        try {
//            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
//            connection.setRequestMethod("HEAD");
//
//            if (connection.getResponseCode() == 200) {
                return HealthCheckResponse.up(CustomRvParkCatalogHealthCheck.class.getSimpleName());
//            }
//        } catch (Exception exception) {
//            LOG.severe(exception.getMessage());
//        }
//        return HealthCheckResponse.down(CustomRvParkCatalogHealthCheck.class.getSimpleName());
    }
    
}