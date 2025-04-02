package utils;

import java.util.Random;
import java.util.stream.IntStream;

public class DataGenerator {

    public String postCodeGenerator() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        IntStream.range(0, 10).forEach(x -> sb.append(random.nextInt(10)));
        return sb.toString();
    }

    public String firstNameGenerator(String postCode){
        long count = Long.parseLong(postCode);
        int length = 100;
        int[] massive = new int[5];

        for (int i = 0; i < massive.length; i++) {
            massive[massive.length - i - 1] = (int) (count % 100);
            count = (count / length);
        }

        StringBuilder name = new StringBuilder();
        for (int j : massive) {
            char symbol = (char) ('a' + (j % 26));
            name.append(symbol);
        }

        return name.toString();
    }
}
