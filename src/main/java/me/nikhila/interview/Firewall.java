package me.nikhila.interview;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Firewall {

    List<Rule> rulesList = new ArrayList<Rule>();

    /*

    * main.java.com.illumino.interview.Firewall Constructor
    * */
    public Firewall(String path){

        BufferedReader br = null;
        String line = "";


        try {

            br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] ruleString = line.split(",");

                if(ruleString.length != 4)
                    continue;

                String [] ports = ruleString[2].split("-");

                String startPort = ports[0];
                String endPort = ports[0];
                if(ports.length == 2)
                    endPort = ports[1];

                String [] ips = ruleString[3].split("-");

                String startIP = ips[0];
                String endIP = ips[0];

                if(ips.length == 2)
                    endIP = ips[1];

                rulesList.add(new Rule(ruleString[0], ruleString[1], startPort, endPort,
                        startIP, endIP));

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public List<Rule> getRulesList() {
        return rulesList;
    }


    /*
    * Acceot Packet
    * */
    public boolean accept_packet(String direction, String protocol, int port, String ip){

        for (Rule r: rulesList) {

            if(r.checkRule(direction, protocol, port, ip))
                return true;
        }


        return false;
    }
}
