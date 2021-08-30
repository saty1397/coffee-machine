package dunzo.assignment.model;

/***
 * Model class for machine.
 */
public class Machine {
    private int outlets;
    private static Machine machine;

    public static Machine getMachineInstance() throws AssertionError{
        if(machine == null) {
            throw new AssertionError("Please call init first");
        }
        return machine;
    }

    public static void initMachineInstance(int outlets) {
        if(machine == null) {
            machine = new Machine(outlets);
        }
        return;
    }

    private Machine(int outlets) {
        this.outlets = outlets;
    }

    public int getOutlets() {
        return outlets;
    }

    public void setOutlets(int outlets) {
        this.outlets = outlets;
    }

    public static void resetMachineInstance() {
        machine = null;
    }
}
