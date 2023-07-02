import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class FileManagement {
    public void dataBackup(Map<String,Phone> phones, Map<String,App> apps, Map<String,Person> persons) {
        try (FileWriter writer = new FileWriter("veri_yedekleme.txt")) {
            // Telefonları dosyaya yazma
            writer.write("----- Telefonlar -----\n");
            for (Phone phone : phones.values()) {
                writer.write(phone.getBrand() + "\n");
                writer.write( phone.getModel() + "\n");
                writer.write( phone.getSerialNumber() + "\n");
                writer.write( phone.getStorageSpace() + "\n");
                writer.write( phone.getOs() + "\n");
                writer.write("-----------------------\n");
            }

            // Uygulamaları dosyaya yazma
            writer.write("----- Uygulamalar -----\n");
            for (App app : apps.values()) {
                writer.write( app.getAppName() + "\n");
                writer.write(app.getVersion() + "\n");
                writer.write( app.getSize() + "\n");
                writer.write(app.getAppSerialNumber() + "\n");

                writer.write("-----------------------\n");
            }

            // Kişileri dosyaya yazma
            writer.write("----- Kişiler -----\n");
            for (Person person : persons.values()) {
                writer.write(person.getId() + "\n");
                writer.write(person.getFirstName() + "\n");
                writer.write( person.getLastName() + "\n");
                writer.write(person.getPhoneNumber() + "\n");
                writer.write( person.geteMail() + "\n");
                writer.write("-----------------------\n");
            }

            System.out.println("Veriler dosyaya yedeklendi: veri_yedekleme.txt");
        } catch (IOException e) {
            System.out.println("Dosya yazma hatası: " + e.getMessage());
        }
    }

    public void restore(Map<String,Phone> phones, Map<String,App> apps, Map<String,Person> persons) {
        try (Scanner scanner = new Scanner(new File("veri_yedekleme.txt"))) {
            String line;
            Map<String,Phone>  telefonList = new HashMap<>();
            Map<String,App> uygulamaList = new HashMap<>();
            Map<String,Person> kisiList = new HashMap<>();
            while (scanner.hasNextLine()) {
                line = scanner.nextLine().trim();
                if (line.equals("----- Telefonlar -----")) {
                    // Telefon bilgilerini oku
                    String marka = scanner.nextLine();
                    String model = scanner.nextLine();
                    String seriNumarasi = scanner.nextLine();
                    String depolamaAlani = scanner.nextLine();
                    String isletimSistemi = scanner.nextLine();

                    Phone telefon = new Phone(marka, model, seriNumarasi, depolamaAlani, isletimSistemi);
                    telefonList.put(seriNumarasi,telefon);
                } else if (line.equals("----- Uygulamalar -----")) {
                    // Uygulama bilgilerini oku
                    String ad = scanner.nextLine();
                    String surum = scanner.nextLine();
                    String boyut = scanner.nextLine();
                    String serialNumber = scanner.nextLine();

                    App uygulama = new App(ad, surum, boyut,serialNumber);
                    uygulamaList.put(serialNumber,uygulama);
                } else if (line.equals("----- Kişiler -----")) {
                    // Kişi bilgilerini oku
                    String id = scanner.nextLine();
                    String ad = scanner.nextLine();
                    String soyad = scanner.nextLine();
                    String telefonNumarasi = scanner.nextLine();
                    String epostaAdresi = scanner.nextLine();

                    Person kisi = new Person(id ,ad, soyad, telefonNumarasi, epostaAdresi);
                    kisiList.put(id,kisi);
                }
            }

            phones = telefonList;
            apps = uygulamaList;
            persons = kisiList;

            PhoneManagementApp phoneManagementApp = new PhoneManagementApp();
            PersonManagement personManagement = new PersonManagement();
            AppManagement managementApp= new AppManagement();

            phoneManagementApp.restoreList(phones);
            personManagement.restoreList(persons);
            managementApp.restoreList(apps);

            managementApp.list();
            personManagement.list();
            phoneManagementApp.list();

            System.out.println("Veriler dosyadan geri yüklendi.");
        } catch (FileNotFoundException e) {
            System.out.println("Dosya bulunamadı: " + e.getMessage());
        }
    }
}
