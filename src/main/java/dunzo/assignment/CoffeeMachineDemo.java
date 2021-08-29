package dunzo.assignment;

import dunzo.assignment.io.IOProcessor;
import dunzo.assignment.process.MachineProcessor;

import java.nio.file.Files;
import java.nio.file.Paths;

/***
 * Main class which does the takes in file as input and initialises other moving pieces of the project.
 */
public class CoffeeMachineDemo {

    public static String readFileAsString(String fileName)throws Exception {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data;
    }


    public static void main(String[] args) throws Exception {
        String data = readFileAsString("C:\\dunzoTest\\json.txt");
        IOProcessor.JSONProcessor(data);
        MachineProcessor.getMachineProcessorInstance().process();
        MachineProcessor.getMachineProcessorInstance().reset();
    }
}
