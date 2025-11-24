package mytests;

import org.graphwalker.java.annotation.Model;
import org.graphwalker.java.annotation.Vertex;
import org.graphwalker.java.annotation.Edge;
import org.graphwalker.core.machine.ExecutionContext;

@Model(file = "VehicleRentModel.json")
public interface VehicleRentModel {

    // ---- VERTICES ----

    @Vertex
    void v_LoggedOut();

    @Vertex
    void v_LoggedIn();

    @Vertex
    void v_VehicleList();

    @Vertex
    void v_VehicleDetails();

    @Vertex
    void v_ContractEmpty();

    @Vertex
    void v_ContractNonEmpty();

    @Vertex
    void v_RentPage();

    @Vertex
    void v_RentSuccess();

    @Vertex
    void v_EmailSent();

    @Vertex
    void v_LoginFailed();

    @Vertex
    void v_RentError();

    @Vertex
    void v_RegisterPage();

    @Vertex
    void v_RegisterSuccess();

    @Vertex
    void v_RegisterError();


    // ---- EDGES ----

    @Edge
    void e_LoginSuccess();

    @Edge
    void e_GetVehicles();

    @Edge
    void e_ViewDetails();

    @Edge
    void e_BackToList();

    @Edge
    void e_OpenContract();

    @Edge
    void e_AddVehicle();

    @Edge
    void e_RemoveVehicle();

    @Edge
    void e_OpenRentPage();

    @Edge
    void e_RentSuccess();

    @Edge
    void e_SendEmail();

    @Edge
    void e_LoginFail();

    @Edge
    void e_RetryLogin();

    @Edge
    void e_RentInvalidDate();

    @Edge
    void e_RentEmptyContract();

    @Edge
    void e_RetryRent();

    @Edge
    void e_FinishFlow();

    @Edge
    void e_Logout();

    @Edge
    void e_InvalidToken();

    @Edge
    void e_TokenExpired();

    @Edge
    void e_OpenRegister();

    @Edge
    void e_RegisterSuccess();

    @Edge
    void e_RegisterFail();

    @Edge
    void e_BackToLogin();

    @Edge
    void e_ContinueAfterRegister();
}
