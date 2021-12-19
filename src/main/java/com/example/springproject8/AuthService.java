package com.example.springproject8;

import com.nimbusds.srp6.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();

    static String salt = "182734259874530153";
    static String verifier = "1126862591047450237142310987036198698724316528837293067888331927568064580205309707203572813515134802640244450220403751454432104647210036306042549886206769";public static SRP6CryptoParams config;
    public static SRP6ServerSession serverSession;

    private static final Map<String, Map> database = new HashMap<>() {
        {
            put("newuser", new HashMap<String, BigInteger>() {{
                put("salt", new BigInteger(salt));
                put("verifier", new BigInteger(verifier));
            }});
        }
    };

    public static String username = "newuser";
    public static String password = "newuser248";
    public static BigInteger s = new BigInteger(salt);
    public static BigInteger v = new BigInteger(verifier);


    public String step1(String username) {
        if (!database.containsKey(username)) {
            return "";
        }
        Map user = database.get(username);
        config = SRP6CryptoParams.getInstance();
        serverSession = new SRP6ServerSession(config);
        return serverSession.step1(username, (BigInteger) user.get("salt"), (BigInteger) user.get("verifier")).toString();
    }

    public String step2(BigInteger A, BigInteger M1) throws SRP6Exception {
        try{
            return serverSession.step2(A, M1).toString();
        } catch (SRP6Exception e) {
            return "";
        }
    }

    public String simulation() {
        SRP6ClientSession client = new SRP6ClientSession();
        client.step1(username, password);

        HttpEntity<String> step1Entity = new HttpEntity<>(username, headers);
        String response = restTemplate.exchange(
                "http://localhost:8080/step1", HttpMethod.POST, step1Entity, String.class).getBody();
        assert response != null;
        BigInteger B = new BigInteger(response);

        SRP6CryptoParams config = SRP6CryptoParams.getInstance();

        SRP6ClientCredentials cred = null;

        try {
            cred = client.step2(config, s, B);
        } catch (SRP6Exception e) {
            return "";
        }
        assert cred != null;
        Step2Body step2Body = new Step2Body(cred.A, cred.M1);
        HttpEntity<Step2Body> step2Entity = new HttpEntity<>(step2Body, headers);

        return restTemplate.postForEntity(
                "http://localhost:8080/step2", step2Entity, String.class).getBody();
    }
}