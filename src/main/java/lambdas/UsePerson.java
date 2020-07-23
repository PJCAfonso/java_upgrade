package lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UsePerson {
    @SuppressWarnings({"UnusedAssignment", "Convert2MethodRef"})
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Paul", "George", "Ringo");
        // List<String> names = List.of("John", "Paul", "George", "Ringo");

        // Old style loop for converting to a List<Person>
        List<Person> beatles = new ArrayList<>();  // Shared mutable state
        for (String name : names) {
            beatles.add(new Person(name));
        }
        System.out.println(beatles);

        // Uses streams to create the beatles collection
        List<Person> people = names.stream()    // Stream<String>
                .map(name -> new Person(name))  // Stream<Person>
                .collect(Collectors.toList());  // Converts Stream<Person> to List<Person>
        System.out.println(people);

        // Uses constructor reference to instantiate Person
        // Note: no local variables at all
        people = names.stream()
                .map(Person::new)  // Which constructor? One-arg ctor of type String
                .collect(Collectors.toList());

        System.out.println(people);

        Person[] peopleArray = names.stream()
                .map(Person::new)
                // .toArray(Person[]::new);
                .toArray((int count) -> new Person[count]);

        System.out.println(Arrays.toString(peopleArray));
    }
}
