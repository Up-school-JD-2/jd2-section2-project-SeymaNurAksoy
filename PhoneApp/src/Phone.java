import java.util.*;

public class Phone {

    private String brand;
    private  String model;
    private  String serialNumber;
    private String storageSpace;
    private String os;
    private Map<String,App> apps;

    public Phone(String brand, String model, String serialNumber, String storageSpace, String os) {
        this.brand = brand;
        this.model = model;
        this.serialNumber = serialNumber;
        this.storageSpace = storageSpace;
        this.os = os;
        apps = new HashMap<>();
    }
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getStorageSpace() {
        return storageSpace;
    }

    public void setStorageSpace(String storageSpace) {
        this.storageSpace = storageSpace;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public Map<String, App> getApps() {
        return apps;
    }

    public void setApps(Map<String, App> apps) {
        this.apps = apps;
    }

    @Override
    public String toString() {

        return "Phone{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", storageSpace=" + storageSpace +
                ", os='" + os + '\'' +
                '}';
    }
}
