package si.fri.rso.skupina3.user.v1;

import com.kumuluz.ee.discovery.annotations.RegisterService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/v1")
@RegisterService(value="user-service", environment="dev", version = "1.0.0")
public class UserApplication extends Application {
}
