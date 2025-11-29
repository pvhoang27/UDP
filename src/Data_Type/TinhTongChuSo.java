//[Mã câu hỏi (qCode): CqMFeT91].  Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2207. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
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
package Data_Type;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class TinhTongChuSo {
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        int sP = 2207;
        
        String request = ";B22DCCN090;CqMFeT91";
        DatagramPacket gui1 = new DatagramPacket(request.getBytes(), request.length(), sA, sP);
        socket.send(gui1);
        
        byte[] buffer = new byte[1024];
        DatagramPacket nhan = new DatagramPacket(buffer, buffer.length);
        socket.receive(nhan);
        String a = new String(nhan.getData());
        String [] part = a.split(";");
        System.out.println(part[1]);
        int sum = 0; 
        for(int i = 0 ; i < part[1].trim().length(); i++){
            char c = part[1].trim().charAt(i);
            sum +=  c - '0';
        }
        
        String kq = part[0] +";" +sum;
        System.out.println(kq);
        DatagramPacket gui2 = new DatagramPacket(kq.getBytes(), kq.length(), sA, sP);
        socket.send(gui2);
        socket.close();
    }
}
