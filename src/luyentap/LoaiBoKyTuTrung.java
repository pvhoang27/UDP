/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package luyentap;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class LoaiBoKyTuTrung {
    public static void main(String[] args) throws Exception{
        DatagramSocket socket= new DatagramSocket();
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        int sP = 2208;
        String request = ";B21DCCN414;7yYKeukj";
        DatagramPacket gui1 = new DatagramPacket(request.getBytes(), request.length(), sA, sP);
        socket.send(gui1);
        
        byte[] buffer = new byte[1024];
        DatagramPacket nhan = new DatagramPacket(buffer, buffer.length);
        socket.receive(nhan);
        String a = new String(nhan.getData());
        System.out.println(a);
        
        String [] chuoi = a.split(";");
        String s1 = chuoi[1].trim();
        String s2 = chuoi[2].trim();
        String kq = "";
        for(int i = 0 ; i < s1.length(); i++){
            char c  = s1.charAt(i);
            if(s2.indexOf(c) == -1 ) kq+= c;
        }
        String kqf = chuoi[0] +";" +kq;
        DatagramPacket gui2 = new DatagramPacket(kqf.getBytes(), kqf.length(), sA, sP);
        socket.send(gui2);
        
   }
}
