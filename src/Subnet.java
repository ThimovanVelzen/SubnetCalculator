
/**
 * Thimo van Velzen | version 1.3 | 8-12-2020
 */

public class Subnet {

    private final int subnetNumber;
    private int count = 0;

    private final IPAddress subnetMask;
    private IPAddress networkAddress;
    private IPAddress broadcastAddress;

    private final String[][] subnetInfo = new String[6][3];

    public Subnet(IPAddress IP, IPAddress superMask, int subnetNumber) {
        subnetMask = superMask;
        subnetInfo[0] = new String[]{"SubnetMask: ",
                subnetMask.getDecimalIP(),
                subnetMask.getBinaryIP()};
        this.subnetNumber = subnetNumber;
        setAddresses(IP);
    }

    private void setAddresses(IPAddress IP) {
        calculateNetworkAddress(IP);
        getSubnetMaskLength();
        calculateBroadcastAddress();
        calculateRestAddresses();
    }

    private void calculateRestAddresses() {
        IPAddress firstRouterAddress = networkAddress.getNextIP();
        IPAddress firstHostAddress = firstRouterAddress.getNextIP();
        IPAddress lastHostAddress = broadcastAddress.getPreviousIP();
        subnetInfo[3] = new String[]{"First Router IP: ",
                firstRouterAddress.getDecimalIP(),
                firstRouterAddress.getBinaryIP()};
        subnetInfo[4] = new String[]{"First Host IP: ",
                firstHostAddress.getDecimalIP(),
                firstHostAddress.getBinaryIP()};
        subnetInfo[5] = new String[]{"Last Host IP: ",
                lastHostAddress.getDecimalIP(),
                lastHostAddress.getBinaryIP()};
    }

    private void calculateNetworkAddress(IPAddress IP) {
        String mask = subnetMask.getBinary();
        String IPString = IP.getBinary();
        StringBuilder network = new StringBuilder();

        for (int bit = 0; bit < 32; bit++) {
            network.append((mask.charAt(bit) == '1' && IPString.charAt(bit) == '1') ? '1' : '0');
        }

        networkAddress = new IPAddress(network.toString());
        subnetInfo[1] = new String[]{"Network Address: ",
                networkAddress.getDecimalIP(),
                networkAddress.getBinaryIP()};
    }

    private void getSubnetMaskLength() {
        for (int letter = 0; letter < subnetMask.getBinaryIP().length(); letter++) {
            count += (subnetMask.getBinaryIP().charAt(letter) == '1') ? 1 : 0;
        }
    }

    private void calculateBroadcastAddress() {
        String IP = networkAddress.getBinary();
        IP = IP.substring(0, count);
        String broadcastString = IP + "1".repeat(Math.max(0, 32 - count));

        broadcastAddress = new IPAddress(broadcastString);
        subnetInfo[2] = new String[]{"Broadcast Address: ",
                broadcastAddress.getDecimalIP(),
                broadcastAddress.getBinaryIP()};
    }

    public void show() {
        for (String[] row : subnetInfo) {
            String cidr = (row[0].equals("SubnetMask: ")) ? "/" + count : "";
            System.out.print(row[0] + getPad(row[0]));
            System.out.print(row[1] + cidr + getPad(row[1] + cidr));
            System.out.println(row[2]);
        }
    }

    public String getPad(String s) {
        return " ".repeat(Math.max(0, (20 - s.length()))) + " | ";
    }

    public int getSubnetNumber() {
        return subnetNumber;
    }
}
