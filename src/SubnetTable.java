
/**
 * Thimo van Velzen | version 1.3 | 8-12-2020
 */

public class SubnetTable {

    private final IPAddress IP;
    private IPAddress subnetMask;
    private int count = 0;
    private final int subnetAmount;
    private final int maskBitsAmount;

    public SubnetTable(IPAddress IP, IPAddress subnetMask, int subnetAmount) {
        this.IP = IP;
        this.subnetMask = subnetMask;
        this.subnetAmount = subnetAmount;
        this.maskBitsAmount = getMaskBitsAmount();
        getSubnetMaskLength();
        changeSubnetMask();
        makeSubnets();
    }

    private int getMaskBitsAmount() {
        return (subnetAmount == 1) ? 1 : (int) Math.ceil(Math.log(subnetAmount) / Math.log(2));
    }

    private void getSubnetMaskLength() {
        for (int bit = 0; bit < subnetMask.getBinary().length(); bit++) {
            count += (subnetMask.getBinary().charAt(bit) == '1') ? 1 : 0;
        }
    }

    private void changeSubnetMask() {
        subnetMask = new IPAddress("1".repeat(count + maskBitsAmount) +
                "0".repeat(32 - (count + maskBitsAmount)));
    }

    private void makeSubnets() {
        for (int subnet = 0; subnet < subnetAmount; subnet++) {
            String maskBits = Integer.toBinaryString(subnet);
            maskBits = "0".repeat(maskBitsAmount - maskBits.length()) + maskBits;
            String oldIP = this.IP.getBinary();
            String newIP = oldIP.substring(0, count) + maskBits +
                    oldIP.substring(count + maskBits.length());
            showTable(new Subnet(new IPAddress(newIP), subnetMask, subnet));
        }
    }

    public void showTable(Subnet subnet) {
        showHeader(subnet);
        subnet.show();
        System.out.println("IP amount: " + (int) Math.pow(2, 32 - count - maskBitsAmount) + "\n");
    }

    private void showHeader(Subnet subnet) {
        System.out.print("SUBNET " + subnet.getSubnetNumber());
        System.out.print(subnet.getPad("SUBNET " + subnet.getSubnetNumber()));
        System.out.print("DECIMAL ");
        System.out.print(subnet.getPad("DECIMAL "));
        System.out.println("BINARY ");
    }

}
