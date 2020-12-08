import java.util.Scanner;

/**
 * Thimo van Velzen | version 1.3 | 8-12-2020
 */

public class SubnetCalculator {
    private String[] IPBlock;
    private IPAddress subnetMask;
    private int subnetAmount;

    public SubnetCalculator() {
        getUserInput();
        makeSubnetMask();
        new SubnetTable(new IPAddress(IPBlock[0]), subnetMask, subnetAmount);
    }

    private void getUserInput() {
        System.out.println("enter IP BLOCK:  (example: '192.168.0.3/20')");
        Scanner input = new Scanner(System.in);
        IPBlock = input.nextLine().split("/");
        System.out.println("How many subnets would you like to have? ");
        subnetAmount = input.nextInt();
        input.close();
    }

    private void makeSubnetMask() {
        StringBuilder mask = new StringBuilder();
        for (int bit = 0; bit < 32; bit++) {
            mask.append((bit < Integer.parseInt(IPBlock[1])) ? "1" : "0");
        }

        subnetMask = new IPAddress(mask.toString());
    }

    public static void main(String[] args) {
        new SubnetCalculator();
    }
}
