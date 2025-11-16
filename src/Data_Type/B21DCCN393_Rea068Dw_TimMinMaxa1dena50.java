/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//[Mã câu hỏi (qCode): Rea068Dw].  Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2207. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
//a.	Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng “;studentCode;qCode”. Ví dụ: “;B15DCCN001;DC73CA2E”
//b.	Nhận thông điệp là một chuỗi từ server theo định dạng “requestId;a1,a2,...,a50” 
//-	requestId là chuỗi ngẫu nhiên duy nhất
//-	a1 -> a50 là 50 số nguyên ngẫu nhiên
//c.	Thực hiện tìm giá trị lớn nhất và giá trị nhỏ nhất thông điệp trong a1 -> a50 và gửi thông điệp lên lên server theo định dạng “requestId;max,min”
//d.	Đóng socket và kết thúc chương trình
package Data_Type;
import java.util.*;
import java.net.*;
import java.io.*;
/**
 *
 * @author hoang
 */
public class B21DCCN393_Rea068Dw_TimMinMaxa1dena50 {
    public static void main(String[] args) throws  Exception{
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        int sP = 2207;
        // nhớ là phải có dấu ; ở trc , chứ ko phải như tcp
        String request = ";B21DCCN393;Rea068Dw";
        // tạo gói để gửi đi
        DatagramPacket dpGui =  new DatagramPacket(request.getBytes(), request.length(), sA, sP);
        socket.send(dpGui);
        
        //nhận về
        byte[] buffer = new byte[1024];
        DatagramPacket dpNhan = new DatagramPacket(buffer, buffer.length);
        socket.receive(dpNhan);
        //chuyển thành dạng string đã nhé
        String data = new String(dpNhan.getData()).trim();
        System.out.println(data);
        //oke giờ tách id riêng + chuỗi kia riêng
        String[] part = data.split(";");
        System.out.println(part[1]);
  
        String[] num = part[1].split(",");
        // làm cách này thì quá là phải nhớ hàm , ...
//        int max_gt = Integer.MIN_VALUE;
//        int min_gt = Integer.MAX_VALUE;
//        for(int i = 0 ; i < num.length; i++){
               // chuyen thanh kieu int 
//            int a = Integer.parseInt(num[i].trim());
//            max_gt = Math.max(max_gt, a);
//            min_gt = Math.min(min_gt, a);
//        }

        // cách 2 : tôi sẽ sort rồi lấy vị trí đầu và cuối làm min max là xong
        int [] a = new int[num.length];
        for(int i = 0 ; i < num.length; i++){
            // chuyen thanh kieu int thi moi sort dc 
            a[i] = Integer.parseInt(num[i].trim());
        }
        //hàm sort có sẵn
        Arrays.sort(a);
        int max_gt =a[num.length-1];
        int min_gt = a[0];
        String result =  part[0] +";" + max_gt +","+ min_gt;
        System.out.println(result);
        
        DatagramPacket dpGui2 = new DatagramPacket(result.getBytes(), result.length(), sA, sP);
        socket.send(dpGui2);
        
        socket.close();
        
    }
}
