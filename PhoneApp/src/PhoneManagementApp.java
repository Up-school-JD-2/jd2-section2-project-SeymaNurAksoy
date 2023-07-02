import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PhoneManagementApp {
    private Map<String, Phone> phones; // veritabanı
    Map<String, App> appList;

    public PhoneManagementApp() {
        phones = new HashMap<>();
        appList = new HashMap<>();
    }

    public void add(Phone phone) {
        phones.put(phone.getSerialNumber(), phone);
        System.out.println("Phone eklendi.");
    }

    public boolean search(String serialNumber) {
        phones.forEach((s, phone) -> System.out.println(phone.getSerialNumber()));
        Optional<Phone> foundPhone = phones.values().stream().
                filter(phone -> phone.getSerialNumber().equalsIgnoreCase(serialNumber)).findFirst();
        if (foundPhone.isPresent()) {
            System.out.println("Telefon bulundu: " + foundPhone.get());
            foundPhone.get().getApps();
        } else {
            System.out.println("Telefon bulunamadı.");
        }
        return foundPhone.isPresent();
    }

    public Map<String,Phone> list() {
        System.out.println("Telefonlar Listesi");
        phones.forEach((s, phone) -> System.out.println(phone.toString()));

        for (Phone phone : phones.values()) {
            System.out.println("Telefon: " + phone.getModel());
            for (App app : phone.getApps().values()) {
                System.out.println("--> " + app.getAppName());
            }
        }

        return phones;
    }

    public void restoreList(Map<String, Phone> phonesV2) {
        phones = phonesV2;
    }

    public void addApp(Map<String, App> apps) {
        phones.forEach((s, phone) -> phone.setApps(apps));
        appList = apps;
        System.out.println(" Telefonlara Uygulamalar Eklendi.");
    }

    public void calculateStorage() {
        System.out.println(" Telefonlara Uygulamalar Eklendi.");
        double sum = appList.values().stream()
                .mapToDouble(w -> Double.parseDouble(w.getSize())).sum();

        System.out.println("Uygulamaların Toplam Alanı: " + sum);
        double phoneStorage;
        for (Phone phone : phones.values()) {
            phoneStorage = Double.parseDouble(phone.getStorageSpace()) - sum;
            System.out.println("Telefon Seri Numarası: " + phone.getSerialNumber() + " " + "Kalan Depolama Alanı: " + phoneStorage);
        }


    }
    public void deleteApp(String serialNumber, App app) {
        for (Phone phone : phones.values()) {
            System.out.println("Telefon: " + phone.getModel());
            phone.getApps().remove(serialNumber);
        }
        phones.values().stream().forEach(phone -> phone.getApps().remove(app));
        System.out.println(" App Phone silindi.");

    }



}
