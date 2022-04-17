package com.company;
import java.util.*;
;import static com.company.Education.HIGHER;
import static com.company.Sex.MAN;
import static com.company.Sex.WOMAN;

public class Main {

    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");

        Collection<Person> persons = new ArrayList<>();

        for (int i = 0; i < 10_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        // Список сколько несовершеннолетних

        long count = persons.stream()
                .filter(person -> person.getAge() < 18)
                .peek(System.out::println)
                .count();
        System.out.println("Колличество несовершенно летних: " + count);


        // Список фамилий призывников

        List<String> recruitList = persons.stream()
                .filter(person -> person.getAge() >= 18)
                .filter(person -> person.getAge() <= 27)
                .map(Person::getFamily)
                .toList();
        System.out.println("Список призывников: ");
        recruitList.forEach(System.out::println);


        // Список работоспособных

        List<Person> potentialWorkerMan = persons.stream()

                .filter(person -> person.getEducation() == HIGHER)
                .filter(person -> person.getAge() > 17)
                .filter(person -> (person.getSex() == WOMAN && person.getAge() <= 60) ||
                        (person.getSex() == MAN && person.getAge() <= 65))
                .sorted(Comparator.comparing(Person::getFamily))
                .toList();
        System.out.println("Список работоспособных людей с высшим образованием:");
        potentialWorkerMan.forEach(System.out::println);
    }
}
















