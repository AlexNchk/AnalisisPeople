import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Honor", "Garry", "Georgy", "Samuel", "John");
        List<String> surnames = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    surnames.get(new Random().nextInt(surnames.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();
        persons.stream()
                .filter(x -> x.getAge() >= 18 && x.getAge() <= 27 && x.getSex() == Sex.MAN)
                .map(Person::getSurname)
                .collect(Collectors.toList());
        persons.stream()
                .filter(x -> x.getAge() >= 18 && x.getEducation() == Education.HIGHER)
                .filter(x -> x.getSex() == Sex.WOMAN && x.getAge() < 60
                        || x.getSex() == Sex.MAN && x.getAge() < 65)
                .sorted(Comparator.comparing(Person::getSurname))
                .collect(Collectors.toList());
    }
}
