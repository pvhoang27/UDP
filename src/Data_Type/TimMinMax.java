//[Mã câu hỏi (qCode): ScLTRjBZ].  Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2207. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
//a.	Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng “;studentCode;qCode”. Ví dụ: “;B15DCCN001;DC73CA2E”
//b.	Nhận thông điệp là một chuỗi từ server theo định dạng “requestId;a1,a2,...,a50” 
//-	requestId là chuỗi ngẫu nhiên duy nhất
//-	a1 -> a50 là 50 số nguyên ngẫu nhiên
//c.	Thực hiện tìm giá trị lớn nhất và giá trị nhỏ nhất thông điệp trong a1 -> a50 và gửi thông điệp lên lên server theo định dạng “requestId;max,min”
//d.	Đóng socket và kết thúc chương trình
package Data_Type;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class TimMinMax {
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        int sP =2207;
        
        String request = ";B22DCCN008;ScLTRjBZ";
        DatagramPacket gui = new DatagramPacket(request.getBytes(), request.length(), sA, sP);
        socket.send(gui);
        
        byte[] buffer = new byte[1024];
        DatagramPacket nhan = new DatagramPacket(buffer, buffer.length);
        socket.receive(nhan);
        
        String a = new String(nhan.getData());
        System.out.println(a);
        String [] part = a.split(";");
        String [] num = part[1].trim().split(",");
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        
        for(String b : num){
            int c = Integer.parseInt(b);
            if(c > max ) max  = c;
            if(c < min) min = c;
        }
        System.out.println(max +" " + min);
        
        String kq = part[0]+";"+max+","+min;
        DatagramPacket gui2 = new DatagramPacket(kq.getBytes(), kq.length(), sA, sP);
        socket.send(gui2);
    }
}
