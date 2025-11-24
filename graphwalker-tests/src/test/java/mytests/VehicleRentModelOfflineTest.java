package mytests;

import org.junit.jupiter.api.Test;
import java.io.*;

public class VehicleRentModelOfflineTest {

    @Test
    public void runOfflineModel() throws Exception {

        // 1) Futtasd a GraphWalkert kézzel vagy programból
        //    és mentsd el a JSON kimenetet steps.json néven.
        //
        // Példa:
        // java -jar graphwalker-cli.jar offline -m model.json "random(edge_coverage(100))" > steps.json

        File steps = new File("steps.json");

        OfflineExecutor exec = new OfflineExecutor();
        exec.runSteps(steps);
    }
}
