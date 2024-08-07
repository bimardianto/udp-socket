package amibotnaidra.demo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) throws IOException {
        try (Scanner sc = new Scanner(System.in); DatagramSocket serverSocket = new DatagramSocket(9876)) {
            while (true) {
                byte[] sendBuffer;
                byte[] receiveBuffer = new byte[1024];
                DatagramPacket receivePkt = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                serverSocket.receive(receivePkt);
                InetAddress IP = receivePkt.getAddress();
                int portNum = receivePkt.getPort();
                String dataClient = new String(receivePkt.getData());
                System.out.println("\nClient :" + dataClient);
                System.out.println("\nServer :");
                String serverData = sc.nextLine();
                sendBuffer = serverData.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, IP, portNum);
                serverSocket.send(sendPacket);
                if (serverData.equalsIgnoreCase("bye")) {
                    System.out.println("Connection ended by server");
                    break;
                }
            }
        }
    }
}
