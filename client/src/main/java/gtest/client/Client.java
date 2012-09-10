package gtest.client;

import gtest.dto.ClientData;
import gtest.dto.Player;
import gtest.dto.TransactionRequest;
import gtest.dto.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriTemplate;

import java.math.BigDecimal;
import java.math.MathContext;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

/**
 * @author Vitalii Tymchyshyn
 */
public class Client {
    private static final Logger LOG = Logger.getLogger(Client.class.getName());
    private final RestOperations restOperations;
    private final URI serverURL;
    private final String userName;
    private final Random random = new Random();

    @Autowired
    public Client(RestOperations restOperations,
                  @Value("#{args.serverLocation}") String serverURL,
                  @Value("#{args.userName}") String userName) throws URISyntaxException {
        this.restOperations = restOperations;
        this.serverURL = new URI(serverURL + "/").resolve("api");
        this.userName = userName;
    }

    public void run() throws InterruptedException {
        ensureUserExists();
        ClientData session = openSession();
        runSession(session.getSessionNumber(), session.getStartingTransaction());
    }

    private void runSession(long sessionNumber, long transaction) throws InterruptedException {
        URI url = getURL("session/{number}/transaction", sessionNumber);
        for (;;) {
            BigDecimal value = new BigDecimal(Math.round(random.nextGaussian() * 10000))
                    .divide(BigDecimal.valueOf(100));
            TransactionResponse resp = restOperations.postForObject(url,
                    new TransactionRequest(userName, transaction++, value), TransactionResponse.class
            );
            LOG.info("Transaction processed: " + resp);
            //Thread.sleep((long) Math.min(5000, Math.max(1,random.nextGaussian() * 100 + 50)));
        }
    }

    private ClientData openSession() {
        ClientData sessionData = restOperations.postForObject(getURL("session"), null, ClientData.class);
        LOG.info("Registered session: " + sessionData);
        return sessionData;
    }

    private void ensureUserExists() {
        URI userUrl = getURL("player/{name}", userName);
        restOperations.put(userUrl, null);
        LOG.info("Starting player data: " + restOperations.getForObject(userUrl, Player.class));
    }

    private URI getURL(String path, Object... vars) {
        return new UriTemplate(serverURL.toString() + "/" + path).expand(vars);
    }
}
