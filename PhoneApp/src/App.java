public class App extends Base{
    private String appName;
    private String version;
    private String size;
    private String appSerialNumber;

    public App(String appName, String version, String size, String appSerialNumber) {
        this.appName = appName;
        this.version = version;
        this.size = size;
        this.appSerialNumber = appSerialNumber;
    }

    public String getAppSerialNumber() {
        return appSerialNumber;
    }

    public void setAppSerialNumber(String appSerialNumber) {
        this.appSerialNumber = appSerialNumber;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
