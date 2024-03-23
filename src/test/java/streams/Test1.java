package streams;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test1 {
    // Count the number of names starts with letter A in list
    //@Test
    public void regular() {
        ArrayList<String> names = new ArrayList<String>();
        names.add("Adam");
        names.add("David");
        names.add("Amelia");
        names.add("Ruslan");
        names.add("Alex");
        int count = 0;

        for (int i = 0; i < names.size(); i++) {
            String actualName = names.get(i);
            if (actualName.startsWith("A")) {
                count++;
            }
        }
        System.out.println("There are " + count + " names, starts with letter A");
    }

    //@Test
    public void streamFilter() {
        ArrayList<String> names = new ArrayList<String>();
        names.add("Adam");
        names.add("David");
        names.add("Amelia");
        names.add("Ruslan");
        names.add("Alexa");

        //  Terminal operation will be executed if only intermediate operation(filter) returns "true"

        Long namesOnLetterA = names.stream().filter(s -> s.startsWith("A")).count();
        System.out.println("There are " + namesOnLetterA + " names, starts with letter A");

        Long filteredNames = Stream.of("Adam", "David", "Amelia", "Ruslan", "Alex").filter(s ->
        {
            return s.startsWith("A");
        }).count();
        System.out.println(filteredNames);
        //names.stream().filter(s -> s.length() > 4).forEach(s -> System.out.println(s));
        names.stream().filter(s -> s.length() > 4).limit(1).forEach(s -> System.out.println(s));
    }

    @Test
    public void streamMap() {
        ArrayList<String> names = new ArrayList<String>();
        names.add("Dog");
        names.add("Cat");
        names.add("Alexa");
        //  print names which end with "a" with uppercase
        Stream.of("Adam", "David", "Amelia", "Ruslan", "Alexa").filter(s -> s.endsWith("a"))
                .map(s -> s.toUpperCase()).forEach(s -> System.out.println(s));
        System.out.println();
        //  print names which start with "a" with uppercase
        List<String> names1 = Arrays.asList("Adam", "David", "Amelia", "Ruslan", "Alexa");
        names1.stream().filter(s -> s.startsWith("A")).sorted().map(s -> s.toUpperCase()).forEach(s -> System.out.println(s));
        System.out.println();

        //  Merging two different lists
        Stream<String> mergeLists = Stream.concat(names.stream(), names1.stream());

        //mergeLists.sorted().forEach(s -> System.out.println(s));
        Assert.assertTrue(mergeLists.anyMatch(s -> s.equalsIgnoreCase("Ruslan")));
    }

    @Test
    public void collectStream() {
        List<String> namesLs = Stream.of("Adam", "David", "Amelia", "Ruslan", "Alexa").filter(s -> s.endsWith("a"))
                .map(s -> s.toUpperCase()).collect(Collectors.toList());
        System.out.println(namesLs.get(0));

        List<Integer> numbers = Arrays.asList(3, 2, 2, 7, 5, 1, 9, 7);
        //  print only unique numbers from the list in sorted way
        numbers.stream().distinct().sorted().forEach(s -> System.out.print(s));
        System.out.println();
        //  sort the array and print the 3rd element
        List<Integer> li = numbers.stream().distinct().sorted().collect(Collectors.toList());
        System.out.println(li.get(2));
    }
}
