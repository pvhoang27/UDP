//[Mã câu hỏi (qCode): K91T9dJA].  Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2208. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
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
package String;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class TinhTong2SoNhiPhan {
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        int sP = 2208;
        String request = ";B22DCCN067;K91T9dJA";
        DatagramPacket gui1 = new DatagramPacket(request.getBytes(), request.length(), sA, sP);
        socket.send(gui1);
        
        byte[] buffer = new byte[1024];
        DatagramPacket nhan = new DatagramPacket(buffer, buffer.length);
        socket.receive(nhan);
        
        String a = new String(nhan.getData()).trim();
        System.out.println(a);
        
        String [] part = a.split(";");
        String [] num = part[1].split(",");
        // nhớ là thêm chỉ số 2 
        int sum  = Integer.parseInt(num[0],2) + Integer.parseInt(num[1],2);
        String tong  = "" + sum;
        String kq = part[0] +";"+tong;
        
        System.out.println(kq);
        
        DatagramPacket gui2 = new DatagramPacket(kq.getBytes(), kq.length(), sA, sP);
        socket.send(gui2);
    }
}
