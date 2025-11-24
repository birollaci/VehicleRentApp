package mytests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.*;
import java.util.*;

public class OfflineExecutor {

    private WebClient client = WebClient.create("http://localhost:8085");
    private String token;

    public void runSteps(File gwOutput) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<JsonNode> steps = mapper.readValue(gwOutput, List.class);

        for (JsonNode node : steps) {
            String step = node.get("currentElementName").asText();
            System.out.println("Executing: " + step);
            execute(step);
        }
    }

    private void execute(String step) {
        switch (step) {

            case "e_LoginSuccess":
                var resp = client.post()
                        .uri("/login")
                        .bodyValue(new LoginRequest("admin", "admin"))
                        .retrieve()
                        .bodyToMono(TokenResponse.class)
                        .block();
                token = resp.getToken();
                break;

            case "e_GetVehicles":
                client.get()
                        .uri("/vehicle")
                        .header("Authorization", "Bearer " + token)
                        .retrieve()
                        .bodyToMono(String.class)
                        .block();
                break;

            case "e_ViewDetails":
                client.get()
                        .uri("/vehicle/1")
                        .header("Authorization", "Bearer " + token)
                        .retrieve()
                        .bodyToMono(String.class)
                        .block();
                break;
            default:
                break;
        }
    }
}

