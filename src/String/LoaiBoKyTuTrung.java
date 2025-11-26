//[Mã câu hỏi (qCode): 6vEs0jGc].  [Loại bỏ ký tự đặc biệt và ký tự trùng giữ nguyên thứ tự xuất hiện]
//Một chương trình server cho phép kết nối qua giao thức UDP tại cổng 2208 . Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản dưới đây:
//a.	Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng ";studentCode;qCode”. Ví dụ: ";B15DCCN001;B34D51E0"
//b.	Nhận thông điệp là một chuỗi từ server theo định dạng "requestId;str1;str2".
//•	requestId là chuỗi ngẫu nhiên duy nhất
//•	str1,str2 lần lượt là chuỗi thứ nhất và chuỗi thứ hai
//c.	Loại bỏ các ký tự trong chuỗi thứ nhất mà xuất hiện trong chuỗi thứ hai, giữ nguyên thứ tự xuất hiện. Gửi thông điệp là một chuỗi lên server theo định dạng "requestId;strOutput", trong đó chuỗi strOutput là chuỗi đã được xử lý ở trên.
//d.	Đóng socket và kết thúc chương trình.
package String;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;

public class LoaiBoKyTuTrung {
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        int sP = 2208;
        
        String request = ";B22DCCN008;6vEs0jGc";
        DatagramPacket gui = new DatagramPacket(request.getBytes(), request.length(), sA, sP);
        socket.send(gui);
        
        byte[] buffer = new byte[1024];
        DatagramPacket nhan = new DatagramPacket(buffer, buffer.length);
        socket.receive(nhan);
        
        String data = new String(nhan.getData());
        System.out.println(data);
        String [] part = data.split(";");
        String id = part[0], s1 = part[1], s2 = part[2];
        
        String kq = "";
        for(int i = 0 ; i < s1.length(); i++){
            char c = s1.charAt(i);
            if(s2.indexOf(c) == -1) kq += c;
        }
        System.out.println(kq);
        String kq2 = id+";" + kq;
        DatagramPacket gui2 = new DatagramPacket(kq2.getBytes(), kq2.length(),sA, sP);
        socket.send(gui2);
        
        socket.close();
        
        
    }
}
