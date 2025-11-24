package mytests;

import java.io.IOException;

import org.graphwalker.java.test.TestExecutor;
import org.junit.jupiter.api.Test;

public class VehicleRentModelTest {

    @Test
    public void runGraphwalkerModel() {
        TestExecutor executor = null;
        try {
            executor = new TestExecutor(VehicleRentModel.class);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        executor.execute();
    }
}
