package es.home.example.config;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.wso2.msf4j.MicroservicesRunner;
import org.wso2.msf4j.analytics.metrics.MetricsInterceptor;

import es.home.example.dao.impl.BookDaoImpl;
import es.home.example.exception.ServiceExceptionMapper;
import es.home.example.service.impl.BookServiceImpl;
import es.home.example.service.impl.OAuthValidatorServiceImpl;
import es.home.example.websocket.GroupChatWebsocket;

public class Application {
    private final static String AUTH_SERVER_URL_S = "AUTH_SERVER_URL";
    private final static String AUTH_SERVER_URL = "http://localhost:9090/oauth2/validate";

    @SuppressWarnings("deprecation")
    public static void main(final String[] args) {
        System.setProperty(AUTH_SERVER_URL_S, AUTH_SERVER_URL);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("es.home.example.jpa.config");
        MetricsInterceptor mInterceptor = new MetricsInterceptor();
        new MicroservicesRunner().addInterceptor(mInterceptor).addExceptionMapper(new ServiceExceptionMapper())
                .deploy(getDeployService(emf)).deployWebSocketEndpoint(deployWSockets()).start();
    }

    private static Object deployWSockets() {
        return new GroupChatWebsocket();
    }

    private static Object[] getDeployService(final EntityManagerFactory emf) {
        List<Object> services = new ArrayList<>();
        services.add(new BookServiceImpl(new BookDaoImpl(emf)));
        services.add(new OAuthValidatorServiceImpl());
        return services.toArray(new Object[services.size()]);
    }
}
