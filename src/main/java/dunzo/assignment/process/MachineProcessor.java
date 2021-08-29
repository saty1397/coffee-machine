package dunzo.assignment.process;

import dunzo.assignment.model.Machine;
import dunzo.assignment.store.BeverageList;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/***
 * Actual class which mimics the behaviour of the coffee machine.
 * Creates a thread pool with the number of outlets in the machine as the number of threads.
 */
public class MachineProcessor {
    private static MachineProcessor machineProcessor;
    private BeverageList beverageList;
    private ExecutorService pool;

    private MachineProcessor(){};

    public static MachineProcessor getMachineProcessorInstance() {
        if(machineProcessor == null) {
            machineProcessor = new MachineProcessor();
        }
        return machineProcessor;
    }

    public void process() {
        pool = Executors.newFixedThreadPool(Machine.getMachineInstance().getOutlets());
        beverageList = BeverageList.getBeverageListInstance();
        beverageList.getBeverages().stream().forEach(beverage -> {
            pool.execute(beverage);
        });
    }

    public void reset() {
        pool.shutdown();
    }
}
