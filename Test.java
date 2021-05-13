package homework9;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Test {

    public static String getOddNamesList(List<String> namesList) {
        int[] countMyNames = {0};
        return namesList.stream().reduce("",(a, b) -> {
            if (countMyNames[0]++ % 2 == 0) {
                return a + countMyNames[0] + ". " + b + ", ";
            }
                return a;
        });
    }


    public static LongStream genRandoms(long a, int c, long m, long seed) {

        return Stream.iterate(seed, n -> (a * n + c) % m).mapToLong(n -> n);
    }

    public static void main(String[] args) {

        List<String> names = new ArrayList<>();
        List<Integer> values = new ArrayList<>();

        names.add("Иванов");
        names.add("Петров");
        names.add("Сидоров");
        names.add("Антонов");
        names.add("Васев");
        names.add("Жиглов");
        names.add("Васнецов");

        System.out.println(getOddNamesList(names));

        Iterator<String> myIterator = names.stream().iterator();
        StringBuilder result = new StringBuilder("1. " + myIterator.next());

        int i = 2;
        while (myIterator.hasNext()) {
            if (i++ % 2 == 0) {
                myIterator.next();
            } else {
                result
                        .append(", ")
                        .append(i - 1)
                        .append(". ")
                        .append(myIterator.next());
            }
        }
        System.out.println(result.toString());


        values.add(1);
        values.add(3);
        values.add(17);
        values.add(21);
        values.add(11);
        values.add(16);

        ArrayList<Double> sqrtValues = values.parallelStream().map(Math::sqrt).collect(ArrayList::new,ArrayList::add,ArrayList::addAll);
        System.out.println(sqrtValues);


    }
}
