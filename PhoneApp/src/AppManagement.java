import java.util.*;

public class AppManagement {

    public Map<String, App> apps;


    public AppManagement() {
        apps = new HashMap<>();
    }

    public void add(App app, String appSerialNumber) {
            apps.put(appSerialNumber, app);
            System.out.println("App eklendi.");
    }

    public Optional<App> search(String appSerialNumber) {
        Optional<App> foundApp = apps.values().stream().
                filter(app -> app.getAppSerialNumber().equalsIgnoreCase(appSerialNumber)).findFirst();
        if (foundApp.isPresent()) {
            System.out.println("Bulundu: " + foundApp.get());
            foundApp.get().getAppName();
        } else {
            System.out.println("Bulunamadı.");
        }
        return foundApp;
    }

    public Map<String, App> list() {
        apps.forEach((s, app) -> System.out.println("App Name:" + app.getAppName() + "  App Serial Number: " + app.getAppSerialNumber() +
                " App Version: "+app.getVersion()));
        return  apps;

    }
    public void restoreList(Map<String, App> appsV2) {
        apps =appsV2;
    }
    public void updateApp(String serialNumber, String newVersion) {
        apps.computeIfPresent(serialNumber, (key, app) -> {
            app.setVersion(newVersion);
            return app;
        });
        System.out.println("Güncellendi");
        list();

    }
}
