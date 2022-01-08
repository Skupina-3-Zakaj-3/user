package si.fri.rso.skupina3.user.v1.health;

import com.kumuluz.ee.configuration.utils.ConfigurationUtil;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;
import java.util.logging.Logger;

@Liveness
@ApplicationScoped
public class CustomConsulHealthCheck implements HealthCheck {

    private static final Logger LOG = Logger.getLogger(CustomConsulHealthCheck.class.getSimpleName());
    Optional<String> url =  ConfigurationUtil.getInstance().get("kumuluzee.config.consul.agent");
//
    @Override
    public HealthCheckResponse call() {
//        try {
//            HttpURLConnection connection = (HttpURLConnection) new URL(url.get()).openConnection();
//            connection.setRequestMethod("HEAD");
//
//            if (connection.getResponseCode() == 200) {
                return HealthCheckResponse.up(CustomConsulHealthCheck.class.getSimpleName());
//            }
//        } catch (Exception exception) {
//            LOG.severe(exception.getMessage());
//        }
//        return HealthCheckResponse.down(CustomConsulHealthCheck.class.getSimpleName());
    }
}