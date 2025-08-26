import java.util.Scanner;
public class AbsoluteValue {
    public static void main(String[] args) {
        Scanner abs = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int number = abs.nextInt();
        int absoluteValue = (number < 0) ? -number : number;
        System.out.println("Absolute value: " + absoluteValue);
        abs.close();
    }
}
