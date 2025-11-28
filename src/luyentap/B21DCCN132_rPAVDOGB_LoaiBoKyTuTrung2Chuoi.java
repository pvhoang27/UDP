/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//[Mã câu hỏi (qCode): rPAVDOGB].  [Loại bỏ ký tự đặc biệt và ký tự trùng giữ nguyên thứ tự xuất hiện]
//Một chương trình server cho phép kết nối qua giao thức UDP tại cổng 2208 . Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản dưới đây:
//a.	Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng ";studentCode;qCode”. Ví dụ: ";B15DCCN001;B34D51E0"
//b.	Nhận thông điệp là một chuỗi từ server theo định dạng "requestId;str1;str2".
//•	requestId là chuỗi ngẫu nhiên duy nhất
//•	str1,str2 lần lượt là chuỗi thứ nhất và chuỗi thứ hai
//c.	Loại bỏ các ký tự trong chuỗi thứ nhất mà xuất hiện trong chuỗi thứ hai, giữ nguyên thứ tự xuất hiện. Gửi thông điệp là một chuỗi lên server theo định dạng "requestId;strOutput", trong đó chuỗi strOutput là chuỗi đã được xử lý ở trên.
//d.	Đóng socket và kết thúc chương trình.
package luyentap;
import java.util.*;
import java.net.*;
import java.io.*;

/**
 *
 * @author hoang
 */
public class B21DCCN132_rPAVDOGB_LoaiBoKyTuTrung2Chuoi {
    public static void main(String[] args) throws  Exception{
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        int sP =2208 ;
        
        //gui msv qcode
        String request =";B21DCCN132;rPAVDOGB";
        DatagramPacket dpGui = new DatagramPacket(request.getBytes(), request.length(), sA, sP);
        socket.send(dpGui);
        
        // nhan du lieu ve 
        byte [] buffer = new byte[1024];
        DatagramPacket dpNhan = new DatagramPacket(buffer, buffer.length);
        socket.receive(dpNhan);
        
        // tach ra de xu ly de bai 
        String data = new String(dpNhan.getData());
        System.out.println(data);
        String[] part  = data.split(";");
        String id = part[0];
        String s1 = part[1];
        String s2 = part[2];
        
        // xu ly de bai 
        String a = "";
        for(int i = 0 ; i < s1.length(); i++){
            char c = s1.charAt(i);
            // neu ky tu ko xuat hien trong s2 thi giu lai 
            if(s2.indexOf(c) == - 1 ) a += c ;
          
        }
        System.out.println(a);
        String result = id+";" + a;
        
        DatagramPacket dpGui2 = new DatagramPacket(result.getBytes(), result.length(), sA, sP);
        socket.send(dpGui2);
        
    }
}
