package mytests;

import org.graphwalker.core.machine.ExecutionContext;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

public class VehicleRentModelImpl extends ExecutionContext implements VehicleRentModel {

    private final WebClient client = WebClient.builder()
            .baseUrl("http://localhost:8085")
            .build();

    private String token = null;

    private void authHeader(WebClient.RequestHeadersSpec<?> req) {
        if (token != null) {
            req.header("Authorization", "Bearer " + token);
        }
    }

    /* =============================================================
       LOGIN FLOW
       ============================================================= */

    @Override
    public void e_LoginSuccess() {
        try {
            TokenResponse resp = client.post()
                .uri("/login")
                .bodyValue(new LoginRequest("admin", "admin"))
                .retrieve()
                .bodyToMono(TokenResponse.class)
                .block();

            token = resp.getToken();
        } catch (Exception e) {
            throw new RuntimeException("LoginSuccess API failed", e);
        }
    }

    @Override
    public void e_LoginFail() {
        try {
            client.post()
                .uri("/login")
                .bodyValue(new LoginRequest("wrong", "wrong"))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        } catch (WebClientResponseException ex) {
            // expected 401
        }
    }

    @Override
    public void e_RetryLogin() {
        // No API call, user returns to login page
    }

    @Override
    public void v_LoggedOut() {}
    @Override
    public void v_LoggedIn() {}
    @Override
    public void v_LoginFailed() {}

    /* =============================================================
       REGISTER FLOW
       ============================================================= */

    @Override
    public void e_OpenRegister() {}

    @Override
    public void e_RegisterSuccess() {
        try {
            client.post()
                .uri("/register")
                .bodyValue(new RegisterRequest("test", "test", "test@example.com", "Test", "User", "1234567890"))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        } catch (Exception e) {
            throw new RuntimeException("RegisterSuccess failed", e);
        }
    }

    @Override
    public void e_RegisterFail() {
        try {
            client.post()
                .uri("/register")
                .bodyValue(new RegisterRequest("", "", "bad"))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        } catch (WebClientResponseException ex) {
            // expected 400
        }
    }

    @Override
    public void e_BackToLogin() {}

    @Override
    public void e_ContinueAfterRegister() {
        // goes back to login
    }

    @Override
    public void v_RegisterPage() {}
    @Override
    public void v_RegisterSuccess() {}
    @Override
    public void v_RegisterError() {}

    /* =============================================================
       VEHICLE LIST / DETAILS
       ============================================================= */

    @Override
    public void e_GetVehicles() {
        try {
            client.get()
                .uri("/vehicle")
                .headers(h -> h.setBearerAuth(token))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        } catch (Exception e) {
            throw new RuntimeException("GetVehicles failed", e);
        }
    }

    @Override
    public void e_ViewDetails() {
        try {
            client.get()
                .uri("/vehicle/1")
                .headers(h -> h.setBearerAuth(token))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        } catch (Exception e) {
            throw new RuntimeException("ViewDetails failed", e);
        }
    }

    @Override
    public void e_BackToList() {}

    @Override
    public void v_VehicleList() {}
    @Override
    public void v_VehicleDetails() {}

    /* =============================================================
       CONTRACT (TEMP RENT)
       ============================================================= */

    @Override
    public void e_OpenContract() {
        
    }

    @Override
    public void e_AddVehicle() {
        try {
            client.post()
                .uri("/contract/add/1")
                .headers(h -> h.setBearerAuth(token))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        } catch (Exception e) {
            throw new RuntimeException("AddVehicle failed", e);
        }
    }

    @Override
    public void e_RemoveVehicle() {
        try {
            client.delete()
                .uri("/contract/remove/1")
                .headers(h -> h.setBearerAuth(token))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        } catch (Exception e) {
            throw new RuntimeException("RemoveVehicle failed", e);
        }
    }

    @Override
    public void v_ContractEmpty() {}
    @Override
    public void v_ContractNonEmpty() {}

    /* =============================================================
       RENT PAGE + RENT ACTIONS
       ============================================================= */

    @Override
    public void e_OpenRentPage() {}

    @Override
    public void e_RentSuccess() {
        try {
            client.post()
                .uri("/contract/rent")
                .headers(h -> h.setBearerAuth(token))
                .bodyValue("{\"start\":\"2030-01-01\",\"end\":\"2030-01-05\"}")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        } catch (Exception e) {
            throw new RuntimeException("RentSuccess failed", e);
        }
    }

    @Override
    public void e_RentInvalidDate() {
        try {
            client.post()
                .uri("/contract/rent")
                .headers(h -> h.setBearerAuth(token))
                .bodyValue("{\"start\":\"2030-01-10\",\"end\":\"2030-01-05\"}")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        } catch (WebClientResponseException ex) {
            // expected
        }
    }

    @Override
    public void e_RentEmptyContract() {
        try {
            client.post()
                .uri("/contract/rent")
                .headers(h -> h.setBearerAuth(token))
                .bodyValue("{\"start\":\"2030-01-01\",\"end\":\"2030-01-05\"}")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        } catch (WebClientResponseException ex) {
            // expected 400
        }
    }

    @Override
    public void e_RetryRent() {}

    @Override
    public void v_RentPage() {}
    @Override
    public void v_RentError() {}
    @Override
    public void v_RentSuccess() {}

    /* =============================================================
       EMAIL SEND
       ============================================================= */

    @Override
    public void e_SendEmail() {
        try {
            client.post()
                .uri("/email/send")
                .headers(h -> h.setBearerAuth(token))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        } catch (Exception e) {
            throw new RuntimeException("SendEmail failed", e);
        }
    }

    @Override
    public void v_EmailSent() {}

    /* =============================================================
       LOGOUT + TOKEN ERRORS
       ============================================================= */

    @Override
    public void e_Logout() {
        token = null;
    }

    @Override
    public void e_InvalidToken() {
        token = "invalid.token.value";
    }

    @Override
    public void e_TokenExpired() {
        token = "expired.token.simulated";
    }

    @Override
    public void e_FinishFlow() {}

}
