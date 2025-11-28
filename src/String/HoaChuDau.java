//[Mã câu hỏi (qCode): NprWQUhM].  Một chương trình server cho phép kết nối qua giao thức UDP tại cổng 2208. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản dưới đây:
//a.	Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng ";studentCode;qCode". Ví dụ: ";B15DCCN001;5B35BCC1"
//b.	Nhận thông điệp từ server theo định dạng "requestId;data" 
//-	requestId là một chuỗi ngẫu nhiên duy nhất
//-	data là chuỗi dữ liệu cần xử lý
//c.	Xử lý chuẩn hóa chuỗi đã nhận thành theo nguyên tắc 
//i.	Ký tự đầu tiên của từng từ trong chuỗi là in hoa
//ii.	Các ký tự còn lại của chuỗi là in thường
//Gửi thông điệp chứa chuỗi đã được chuẩn hóa lên server theo định dạng "requestId;data"
//d.	Đóng socket và kết thúc chương trình
package String;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class HoaChuDau {
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        int sP = 2208;
        
        String request = ";B22DCCN019;NprWQUhM";
        DatagramPacket gui = new DatagramPacket(request.getBytes(), request.length(), sA, sP);
        socket.send(gui);
        
        byte[] buffer = new byte[1024];
        DatagramPacket nhan = new DatagramPacket(buffer, buffer.length);
        socket.receive(nhan);
        String a = new String(buffer, 0, buffer.length);
        System.out.println(a);
        String [] part = a.split(";");
        String [] b = part[1].toLowerCase().trim().split(" ");
        String d = "";
        for(String c : b){
            String dauHoa =Character.toUpperCase(c.charAt(0)) + c.substring(1);
            System.out.println(dauHoa);
            d+= dauHoa+" ";
            
        }
        System.out.println(d);
        String kq = part[0] +";" +d.trim();
        
        DatagramPacket gui2 = new DatagramPacket(kq.getBytes(), kq.length(), sA, sP);
        socket.send(gui2);
    }
}
