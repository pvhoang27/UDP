/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//[Mã câu hỏi (qCode): IvY70pDN].  Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2207. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
//
//a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng ";studentCode;qCode". Ví dụ: ";B15DCCN011;A1F3D5B7".
//
//b. Nhận thông điệp là một chuỗi từ server theo định dạng "requestId;num", với:
//   - requestId là chuỗi ngẫu nhiên duy nhất.
//   - num là một số nguyên lớn.
//
//c. Tính tổng các chữ số trong num và gửi lại tổng này về server theo định dạng "requestId;sumDigits".
//
//d. Đóng socket và kết thúc chương trình.
package luyentap;
import java.net.*;
import java.util.*;
import java.io.*;
import java.math.BigInteger;

/**
 *
 * @author hoang
 */
public class B21DCCN017_IvY70pDN_TinhTongChuSo {
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        int sP =2207;
        
        String request = ";B21DCCN017;IvY70pDN";
        DatagramPacket dpGui = new DatagramPacket(request.getBytes(), request.length(), sA, sP);
        socket.send(dpGui);
        
        byte[] buffer = new byte[1024];
        DatagramPacket dpNhan = new DatagramPacket(buffer, buffer.length);
        socket.receive(dpNhan);
        String data = new String(dpNhan.getData()).trim();
        System.out.println(data);
        String [] part = data.split(";");
        String id = part[0];
        String  num = part[1];
        
        long  tong = 0 ;
//        while(so ){
//            int chuso = (int) (so % 10) ;
//            tong += chuso;
//            so = so  / 10 ;     
//        }

        for(int i = 0; i < num.length(); i++){
            char c = num.charAt(i);
            tong += (c -'0');
        }
        System.out.println(tong);
        
        String result = id + ";" + tong;
        DatagramPacket dpGui2 = new DatagramPacket(result.getBytes(), result.length(), sA, sP);
        socket.send(dpGui2);
        
    }
}
