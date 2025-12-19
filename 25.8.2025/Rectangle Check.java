import java.util.Scanner;
public class RectangleChecker {
    public static void main(String[] args) {
        Scanner RC = new Scanner(System.in);

        System.out.print("Enter the length of the rectangle: ");
        double length = RC.nextDouble();
        System.out.print("Enter the breadth of the rectangle: ");
        double breadth = RC.nextDouble();
        if (length == breadth) {
            System.out.println("It is a square.");
        } else {
            System.out.println("It is not a square.");
        }
        RC.close();
    }
}
