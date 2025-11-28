//[Mã câu hỏi (qCode): xRvZPmzI].  Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2207. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
//
//a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng ";studentCode;qCode". Ví dụ: ";B15DCCN009;F3E8B2D4".
//
//b. Nhận thông điệp là một chuỗi từ server theo định dạng "requestId;string", với:
//--- requestId là chuỗi ngẫu nhiên duy nhất.
//--- string là một chuỗi chứa các chuỗi con bị thay đổi vị trí. Ví dụ: "veM3xgA1g:4,IPFfgEanY:5,aWXlSzDwe:2,PHupvPc:3,PR3gH8ahN:6,UEEKHLIt:7,M6dpWTE:1"
//
//c. Xử lý chuỗi xáo trộn và gửi về chuỗi sau khi sắp xếp: "requestId;string". Ví dụ chuỗi đã được xử lý: "M6dpWTE,aWXlSzDwe,PHupvPc,veM3xgA1g,IPFfgEanY,PR3gH8ahN,UEEKHLIt"
//
//d. Đóng socket và kết thúc chương trình.
package Data_Type;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SapXepChuoiTheoThuTu {
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        int sP = 2207;
        
        String request = ";B22DCCN032;xRvZPmzI";
        DatagramPacket gui1 = new DatagramPacket(request.getBytes(), request.length(), sA, sP);
        socket.send(gui1);
        
        byte [] buffer = new byte[1024];
        DatagramPacket nhan = new DatagramPacket(buffer, buffer.length);
        socket.receive(nhan);
        
        String a = new String(nhan.getData());
        System.out.println(a);
        String [] part = a.split(";");
        String [] part2 = part[1].split(",");
        String kq ="";
        for(int i = 0 ; i < part2.length; i++){
            for(int j = 0 ;j < part2[i].length(); j++){
                char c = part2[i].charAt(j);
                if(Character.isDigit(c) && c == i - '0' ) kq += part2[i];
            }
        }
        System.out.println(kq);
        
    }
}
