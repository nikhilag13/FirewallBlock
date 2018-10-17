package me.nikhila.interview;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Rule {


    private String direction;
    private String protocol;
    private int portStart;
    private int portEnd;
    private long ipAddressStart;
    private long ipAddressEnd;

    Rule(String direction, String protocol, String portStart, String portEnd, String ipAddressStart, String ipAddressEnd) {
        this.direction = direction;
        this.protocol = protocol;
        this.portStart = Integer.parseInt(portStart);
        this.portEnd = Integer.parseInt(portEnd);
        try {
            this.ipAddressStart = ipToLong(InetAddress.getByName(ipAddressStart));
            this.ipAddressEnd = ipToLong(InetAddress.getByName(ipAddressEnd));
        }
        catch (UnknownHostException e){
            e.printStackTrace();
        }
    }

    public String getDirection() {
        return direction;
    }

    public String getProtocol() {
        return protocol;
    }

    public int getPortStart() {
        return portStart;
    }

    public int getPortEnd() {
        return portEnd;
    }

    public long getIpAddressStart() {
        return ipAddressStart;
    }

    public long getIpAddressEnd() {
        return ipAddressEnd;
    }

    /*
    *
    * Helper method to convert IP address into long
    * Source - https://stackoverflow.com/questions/4256438/calculate-whether-an-ip-address-is-in-a-specified-range-in-java
    * */
    private long ipToLong(InetAddress ip) {
        byte[] octets = ip.getAddress();
        long result = 0;
        for (byte octet : octets) {
            result <<= 8;
            result |= octet & 0xff;
        }
        return result;
    }

    /*
    *
    * Check if given packet satisfies rule
    *
    * */
    public boolean checkRule(String direction, String protocol, int port, String ip){

        return direction.equals(this.direction) && protocol.equals(this.protocol) &&
                checkPortRange(port) && checkIPRange(ip);
    }


    /*
    * Check if given IP is in range
    * */
    private   boolean checkIPRange(String ip){

        try {

            long inputIP = ipToLong(InetAddress.getByName(ip));
            return (inputIP >= ipAddressStart && inputIP <= ipAddressEnd);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
    * Check if port falls between range
    * */
    private boolean checkPortRange(int inpPort){
        return (inpPort >= this.portStart) && (inpPort <= this.portEnd);
    }
}
