
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PersonManagement {
    private Map<String, Person> persons; // veritabanı


    public PersonManagement() {
        persons = new HashMap<>();
    }

    public void add(Person person) {
        persons.put(person.getId(), person);
        System.out.println("Person eklendi.");
    }

    public Map<String, Person> list() {
        persons.forEach((s, person) -> System.out.println("  Adı Soyadı " + person.getFirstName() + person.getLastName() + "  Telefon Numarası " + person.getPhoneNumber() + "  Rehber Kimliği " + person.getId()));
        return persons;
    }

    public void restoreList(Map<String, Person> personsV2) {
        persons = personsV2;
    }

    public void delete(Person person) {
        persons.remove(person.getId());
        System.out.println("Silindi " + person.getPhoneNumber());
    }

    public Optional<Person> searchPersonPhoneNumber(String phoneNumber) {
        Optional<Person> foundPerson = persons.values().stream().filter(person -> person.getPhoneNumber().equalsIgnoreCase(phoneNumber))
                .findFirst();

        // Bulunan kişiyi kontrol et ve sonucu yazdır
        if (foundPerson.isPresent()) {
            System.out.println("Kişi bulundu: " + foundPerson.get());
        } else {
            System.out.println("Kişi bulunamadı.");
        }

        return foundPerson;
    }


    public void update(Person updatePerson, String phoneNumber) {
        try {
            List<Person> updatedPersons = persons.values().stream().filter(person -> person.getPhoneNumber().equalsIgnoreCase(phoneNumber) )
                    .peek(person -> {
                        // İstenilen güncellemeleri yap
                        person.setFirstName(updatePerson.getFirstName());
                        person.setLastName(updatePerson.getLastName());
                        person.setPhoneNumber(updatePerson.getPhoneNumber());
                        person.seteMail(updatePerson.geteMail());
                    }).toList();
            System.out.println("Telefon numarısına ait kişi güncellendi.");
            updatedPersons.forEach(person -> person.toString());
        } catch (Exception e) {
            System.out.println("Telefon numarısına ait kişi bulunmamaktadır.");
        }

    }
}
