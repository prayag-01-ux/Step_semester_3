import java.util.*;

interface Capability {

    String getName();

    boolean supports(String capability);

    void setValue(Object value);

    Object getValue();
}

class PowerCapability implements Capability {

    private boolean on = false;

    public String getName() {
        return "Power";
    }

    public boolean supports(String capability) {
        return capability.equalsIgnoreCase("Power");
    }

    public void setValue(Object value) {

        if (!(value instanceof Boolean)) {
            System.out.println("Invalid power value.");
            return;
        }

        on = (Boolean) value;
    }

    public Object getValue() {
        return on;
    }
}

class BrightnessCapability implements Capability {

    private int brightness = 0;

    public String getName() {
        return "Brightness";
    }

    public boolean supports(String capability) {
        return capability.equalsIgnoreCase("Brightness");
    }

    public void setValue(Object value) {

        int valueInt = (Integer) value;

        if (valueInt < 0 || valueInt > 100) {
            System.out.println(
                    "Brightness must be between 0 and 100.");
            return;
        }

        brightness = valueInt;
    }

    public Object getValue() {
        return brightness;
    }
}

class TemperatureCapability implements Capability {

    private int temperature = 24;

    public String getName() {
        return "Temperature";
    }

    public boolean supports(String capability) {
        return capability.equalsIgnoreCase("Temperature");
    }

    public void setValue(Object value) {

        int temp = (Integer) value;

        if (temp < 16 || temp > 30) {
            throw new IllegalArgumentException(
                    "Temperature must be between 16°C and 30°C.");
        }

        temperature = temp;
    }

    public Object getValue() {
        return temperature;
    }
}

class Device {

    String name;

    Map<String, Capability> capabilities = new HashMap<>();

    Device(String name) {
        this.name = name;
    }

    void addCapability(Capability capability) {

        capabilities.put(
                capability.getName(),
                capability);

        System.out.println(
                name + ": " +
                        capability.getName() +
                        " capability added.");
    }

    boolean hasCapability(String name) {
        return capabilities.containsKey(name);
    }

    void setCapability(String name, Object value) {

        Capability capability = capabilities.get(name);

        if (capability == null) {
            return;
        }

        try {

            capability.setValue(value);

            System.out.println(
                    name.equals("Power")
                            ? this.name + ": " +
                                    ((Boolean) value ? "ON" : "OFF")
                            : this.name + ": " +
                                    name.toLowerCase() +
                                    " set to " + value +
                                    (name.equals("Temperature")
                                            ? "°C"
                                            : name.equals("Brightness")
                                                    ? "%"
                                                    : ""));

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Rejected: " +
                            this.name + " " +
                            name.toLowerCase() +
                            " " + e.getMessage());
        }
    }
}

class SceneStep {

    String capability;
    Object value;

    SceneStep(String capability, Object value) {
        this.capability = capability;
        this.value = value;
    }

    int execute(List<Device> devices) {

        int count = 0;

        for (Device device : devices) {

            if (device.hasCapability(capability)) {

                device.setCapability(
                        capability,
                        value);

                count++;
            }
        }

        return count;
    }
}

class Scene {

    String name;
    List<SceneStep> steps = new ArrayList<>();

    Scene(String name) {
        this.name = name;
    }

    void addStep(SceneStep step) {
        steps.add(step);
    }

    void execute(List<Device> devices) {

        System.out.println(
                "Scene '" + name + "' started.");

        int actions = 0;

        for (SceneStep step : steps) {
            actions += step.execute(devices);
        }

        System.out.println(
                "Scene '" + name +
                        "' completed: " +
                        actions +
                        " actions applied.");
    }
}

public class Q3_SmartLab {

    public static void main(String[] args) {

        Device ac = new Device("Lab AC");

        ac.addCapability(
                new PowerCapability());

        ac.addCapability(
                new TemperatureCapability());

        Device lights = new Device("Ceiling Lights");

        lights.addCapability(
                new PowerCapability());

        lights.addCapability(
                new BrightnessCapability());

        Device projector = new Device("Projector");

        projector.addCapability(
                new PowerCapability());

        List<Device> devices = Arrays.asList(
                ac,
                lights,
                projector);

        Scene lectureMode = new Scene("Lecture Mode");

        lectureMode.addStep(
                new SceneStep("Power", true));

        lectureMode.addStep(
                new SceneStep("Brightness", 40));

        lectureMode.addStep(
                new SceneStep("Temperature", 24));

        lectureMode.execute(devices);

        ac.setCapability(
                "Temperature",
                12);

        projector.addCapability(
                new BrightnessCapability());

        projector.setCapability(
                "Brightness",
                70);
    }
}