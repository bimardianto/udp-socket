package amibotnaidra.demo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
        InetAddress IP = InetAddress.getByName("localhost");
        try (Scanner sc = new Scanner(System.in) ; DatagramSocket clientSocket = new DatagramSocket()) {
            while (true) {
                byte[] sendBuffer;
                byte[] receiveBuffer = new byte[1024];
                System.out.print("\nClient: ");
                String clientData = sc.nextLine();
                sendBuffer = clientData.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer,
                        sendBuffer.length, IP, 9876);
                clientSocket.send(sendPacket);
                if (clientData.equalsIgnoreCase("bye")) {
                    System.out.print("Connection ended by client");
                    break;
                }

                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer,
                        receiveBuffer.length);
                clientSocket.receive(receivePacket);
                String serverData = new String(receivePacket.getData());
                System.out.print("\nServer :" + serverData);
            }
        }
    }
}
