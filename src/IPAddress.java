
/**
 * Thimo van Velzen | version 1.3 | 8-12-2020
 */

public class IPAddress {

    private String[] decimalIP = new String[4];
    private String[] binaryIP = new String[4];

    public IPAddress(String IP) {
        if (IP.length() > 16 && IP.length() < 33) {
            splitStringByEight(IP);
            convertBinaryToDecimal();
        } else if (IP.length() > 32) {
            binaryIP = IP.split("\\.");
            convertBinaryToDecimal();
        } else {
            decimalIP = IP.split("\\.");
            convertDecimalToBinary();
        }
    }

    private void splitStringByEight(String IP) {
        for (int i = 0; i < (IP.length() / 8); i++) {
            binaryIP[i] = IP.substring(i * 8, 8 + (8 * i));
        }
    }

    private void convertBinaryToDecimal() {
        for (int octet = 0; octet < binaryIP.length; octet++) {
            decimalIP[octet] = convertBinaryToInteger(binaryIP[octet]);
        }
    }

    private String convertBinaryToInteger(String binaryString) {
        return Integer.toString(Integer.parseInt(binaryString, 2));
    }

    private void convertDecimalToBinary() {
        for (int octet = 0; octet < decimalIP.length; octet++) {
            binaryIP[octet] = convertIntegerToBinary(decimalIP[octet]);
        }
    }

    private String convertIntegerToBinary(String integerString) {
        String binaryString = Integer.toBinaryString(Integer.parseInt(integerString));
        return "0".repeat(8 - binaryString.length()) + binaryString;
    }

    public IPAddress getNextIP() {
        String[] nextIP = decimalIP.clone();
        for (int octet = nextIP.length - 1; octet > 0; octet--) {
            if (!nextIP[octet].equals("255")) {
                nextIP[octet] = Integer.toString(Integer.parseInt(nextIP[octet]) + 1);
                break;
            }
        }

        return new IPAddress(String.join(".", nextIP));
    }

    public IPAddress getPreviousIP() {
        String[] PreviousIP = decimalIP.clone();
        for (int octet = PreviousIP.length - 1; octet > 0; octet--) {
            if (!PreviousIP[octet].equals("0")) {
                PreviousIP[octet] = Integer.toString(Integer.parseInt(PreviousIP[octet]) - 1);
                break;
            }

            PreviousIP[octet] = "255";
        }

        return new IPAddress(String.join(".", PreviousIP));
    }

    public String getBinary() {
        return String.join("", binaryIP);
    }

    public String getDecimalIP() {
        return String.join(".", decimalIP);
    }

    public String getBinaryIP() {
        return String.join(".", binaryIP);
    }
}
