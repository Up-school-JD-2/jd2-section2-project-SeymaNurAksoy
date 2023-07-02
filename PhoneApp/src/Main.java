import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        App app;
        AppManagement managementApp = new AppManagement();
        PhoneManagementApp phoneManagementApp = new PhoneManagementApp();
        PersonManagement personManagement = new PersonManagement();
        FileManagement fileManagement = new FileManagement();
        printMenu();
        do {
            System.out.print("Seçiminiz: ");
            choice = sc.nextInt(); // \n
            switch (choice) {
                case 0 -> {
                    System.out.println("--------------------------------");
                    System.out.println("Enter Serial Number (search phone:");
                    String serialNumber = sc.next();
                    phoneManagementApp.search(serialNumber);
                    System.out.println("--------------------------------");
                }
                case 1 -> {
                    System.out.println("--------------------------------");
                    System.out.println("Enter Phone Number (search person:");
                    String sphoneNumber = sc.next();
                    //güncellenecek kişi bu
                    Optional<Person> findPerson = personManagement.searchPersonPhoneNumber(sphoneNumber);
                    // bilgileri set ediliyor
                    System.out.println("Person Name : ");
                    String firstName = sc.next();
                    System.out.println("Last Name :");
                    String lastName = sc.next();
                    System.out.println("Phone Number :");
                    String phoneNumber = sc.next();
                    System.out.println("Email :");
                    String eMail = sc.next();
                    Person updatePerson = new Person(findPerson.get().getId(), firstName, lastName, phoneNumber, eMail);
                    List<Optional<Person>> findPersons = new ArrayList<>();
                    findPersons.add(findPerson);
                    personManagement.update(updatePerson, sphoneNumber);
                    System.out.println("--------------------------------");
                }
                case 2 -> {
                    System.out.println("--------------------------------");
                    phoneManagementApp.list();
                    System.out.println("--------------------------------");
                }
                case 3 -> {
                    System.out.println("--------------------------------");
                    System.out.println("Brand:");
                    String brandName = sc.next();
                    System.out.println("Model :");
                    String model = sc.next();
                    System.out.println("serial number:");
                    UUID uuid = UUID.randomUUID();
                    String serialNumber = sc.next();
                    System.out.println("Storage Space");
                    String storageSpace = sc.next();
                    System.out.println("OS");
                    String os = sc.next();
                    Phone phone = new Phone(brandName, model, serialNumber, storageSpace, os);
                    phoneManagementApp.add(phone);
                    phoneManagementApp.list();
                    System.out.println("--------------------------------");
                }
                case 4 -> {
                    System.out.println("--------------------------------");
                    System.out.println("App Name : ");
                    String appName = sc.next();
                    System.out.println("App Serial Number : ");
                    String appSNumber = sc.next();
                    System.out.println("Version :");
                    sc.nextLine();
                    String version = sc.next();
                    System.out.println("Size");
                    String size = sc.next();
                    app = new App(appName, version, size, appSNumber);
                    managementApp.add(app, appSNumber);
                    // phoneManagementApp.addApp(app);
                    System.out.println("--------------------------------");

                }
                case 5 -> {
                    System.out.println("--------------------------------");
                    UUID uuid = UUID.randomUUID();
                    String uuidAsString = uuid.toString();
                    System.out.println("Person Name : ");
                    String firstName = sc.next();
                    System.out.println("Last Name :");
                    String lastName = sc.next();
                    System.out.println("Phone Number :");
                    String phoneNumber = sc.next();
                    System.out.println("Email :");
                    String eMail = sc.next();
                    Person person = new Person(uuidAsString, firstName, lastName, phoneNumber, eMail);
                    personManagement.add(person);
                    personManagement.list();
                    System.out.println("--------------------------------");
                }
                case 6 -> {
                    System.out.println("--------------------------------");
                    personManagement.list();
                    System.out.println("--------------------------------");
                }
                case 7 -> {
                    System.out.println("--------------------------------");
                    managementApp.list();
                    System.out.println("--------------------------------");
                }
                case 8 -> {
                    System.out.println("--------------------------------");
                    System.out.println("Silinecek kişinin telefon numarasını giriniz");
                    System.out.println("Phone Number :");
                    String phoneNumber = sc.next();
                    Optional<Person> person = personManagement.searchPersonPhoneNumber(phoneNumber);
                    personManagement.delete(person.get());
                    System.out.println("--------------------------------");
                }
                case 9 -> {
                    System.out.println("--------------------------------");
                    System.out.println("Enter App Serial Number : ");
                    String appSerialNumber = sc.next();
                    Optional<App> foundApp = managementApp.search(appSerialNumber);
                    phoneManagementApp.deleteApp(appSerialNumber, foundApp.get());
                    System.out.println("--------------------------------");
                }
                case 10 -> {
                    System.out.println("--------------------------------");
                    System.out.println("Güncelleyeceğiniz Uygulamanın Seri Numarasını Giriniz:");
                    String appSerialNumber = sc.next();
                    System.out.println("Güncelleyeceğiniz Uygulamanın Versiyon Numarasını Giriniz:");
                    String appVersionNumber = sc.next();
                    managementApp.updateApp(appSerialNumber, appVersionNumber);
                    System.out.println("--------------------------------");
                }
                case 11 -> {
                    System.out.println("--------------------------------");
                    System.out.println("Veri Yedekleme Başladı");
                    fileManagement.dataBackup(phoneManagementApp.list(), managementApp.list(), personManagement.list());
                    System.out.println("--------------------------------");
                }
                case 12 -> {
                    System.out.println("--------------------------------");
                    System.out.println("Veri Geri Yükleme Başladı");

                    fileManagement.restore(phoneManagementApp.list(), managementApp.list(), personManagement.list());

                    System.out.println("--------------------------------");
                }
                case 13 -> {
                    System.out.println("--------------------------------");
                    Map<String, App> apps = managementApp.list();
                    phoneManagementApp.addApp(apps);
                    phoneManagementApp.calculateStorage();
                    System.out.println("--------------------------------");
                }
            }


            } while (choice != -1) ;
        }

        private static void printMenu () {
            System.out.println("##### Menu #####");
            System.out.println("0: Telefon Arama");
            System.out.println("1: Kişileri Güncelle");
            System.out.println("2: Telefonları Listele");
            System.out.println("3: Telefon Ekle");
            System.out.println("4: App  ekle");
            System.out.println("5: Kişileri Ekle");
            System.out.println("6: Kişileri Listele");
            System.out.println("7: Uygulama Listesi");
            System.out.println("8: Kişi Sil");
            System.out.println("9: Uygulama Sil");
            System.out.println("10: Uygulama Güncelle");
            System.out.println("11: Veri Yedekle");
            System.out.println("12: Verileri Geri Yükle");
            System.out.println("14: Depolama Alanı Kontrolü");
            System.out.println("-1: Çıkış \n\n");
        }
    }
