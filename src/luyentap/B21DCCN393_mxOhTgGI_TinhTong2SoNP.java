/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//[Mã câu hỏi (qCode): mxOhTgGI].  Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2208. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
//a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng “;studentCode;qCode”. Ví dụ: “;B15DCCN000;XbYdNZ3”.
//
//b. Nhận thông điệp là một chuỗi từ server theo định dạng “requestId;b1,b2”, trong đó:
//    requestId là chuỗi ngẫu nhiên duy nhất.
//    b1 là số nhị phân thứ nhất
//    b2 là số nhị phân thứ hai.
//Ví dụ: requestId;0100011111001101,1101000111110101
//c. Thực hiện tính tổng hai số nhị phân nhận được, chuyển về dạng thập phân và gửi lên server theo định dạng “requestId;sum”
//Kết quả: requestId;72130 
//d. Đóng socket và kết thúc chương trình.
package luyentap;
import java.util.*;
import java.net.*;
import java.io.*;
/**
 *
 * @author hoang
 */
public class B21DCCN393_mxOhTgGI_TinhTong2SoNP {
    public static void main(String[] args) throws Exception{
        // khai báo socket + host + port
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        int sP = 2208;
        // gửi lên sv msv + qcode + host và port
        String request  = ";B21DCCN393;mxOhTgGI";
        DatagramPacket dpGui = new DatagramPacket(request.getBytes(), request.length(), sA, sP);
        socket.send(dpGui);
        // tạo ra mảng byte để nhận 
        byte[] buffer = new byte[1024];
        DatagramPacket dpNhan = new DatagramPacket(buffer, buffer.length);
        socket.receive(dpNhan);
        
        //chuyển dữ liệu nhận thành string
        String data = new String(dpNhan.getData()).trim();
        System.out.println(data);
        // đâu tiên tách ID và 2 cái xâu ra
        String[] part = data.split(";");
        System.out.println(part[0]);
        // tiếp theo tách thành 2 string a và b 
        String[] bai = part[1].split(",");
        
        
        
        int a = Integer.parseInt(bai[0],2);
        int b = Integer.parseInt(bai[1], 2);
        
        int sum = a + b;
        String sumNp = Integer.toBinaryString(sum);
        System.out.println(sumNp);
        
        String result  = part[0] +";"+ sumNp;
        
        DatagramPacket dpGui2 = new DatagramPacket(result.getBytes(), result.length(), sA, sP);
        socket.send(dpGui2);
        
        socket.close();
                
        
        
    }
}
