import java.util.Arrays;

public class Task1_EqualityTest {
    public static void main(String[] args) {
        char[] chars1 = {'a', 'b', 'a'};
        char[] chars2 = {'a', 'b', 'a'};

        System.out.println(Arrays.equals(chars1, chars2)); // Now correctly prints: true
    }
}

