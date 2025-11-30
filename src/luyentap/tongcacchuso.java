/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package luyentap;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author hoang
 */
public class tongcacchuso {
    public static void main(String[] args) throws Exception{
        DatagramSocket socket= new DatagramSocket();
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        int sP = 2207;
        String request = ";B21DCCN414;RG2zAOvT";
        DatagramPacket gui1 = new DatagramPacket(request.getBytes(), request.length(), sA, sP);
        socket.send(gui1);
        
        byte[] buffer = new byte[1024];
        DatagramPacket nhan = new DatagramPacket(buffer, buffer.length);
        socket.receive(nhan);
        String a = new String(nhan.getData());
        System.out.println(a);
        
        String [] part = a.split(";");
        String num = part[1].trim();
        System.out.println(num);
        int sum = 0 ; 
        for(int i = 0 ; i < num.length(); i++){
            char c = num.charAt(i);
            sum += (int) (c -'0');
        }
        System.out.println(sum);
        String kqf = part[0] +";" +sum;
        DatagramPacket gui2 = new DatagramPacket(kqf.getBytes(), kqf.length(), sA, sP);
        socket.send(gui2);
             
    }
}
