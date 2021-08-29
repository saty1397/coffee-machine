package dunzo.assingment;

import dunzo.assignment.io.IOProcessor;
import dunzo.assignment.model.Machine;
import dunzo.assignment.process.MachineProcessor;
import dunzo.assignment.store.InventoryStore;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;

import static dunzo.assingment.utils.Utils.readFileAsString;

public class CoffeeMachineDemoAllSuccessfulTest {
    @Test
    public void testFourOutletsWithFourSuccessfulA() throws Exception {
        String filePath = "input_2.txt";
        URL url = Machine.class.getClassLoader().getResource(filePath);
        Path scriptPath = new File(url.getPath()).toPath();
        String data = readFileAsString(scriptPath);
        IOProcessor.JSONProcessor(data);
        MachineProcessor.getMachineProcessorInstance().process();
        MachineProcessor.getMachineProcessorInstance().reset();
        //Waiting for the process to complete. A hack.
        Thread.sleep(2000);
        Assert.assertEquals(4, InventoryStore.getInventoryStoreInstance().getServedBeverages().size());
        Assert.assertEquals(0,InventoryStore.getInventoryStoreInstance().getRejectedBeverages().size());
    }
}
