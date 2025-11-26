//[Mã câu hỏi (qCode): 78vGdYBV].  Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2209. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản sau:
//Đối tượng trao đổi là thể hiện của lớp UDP.Employee được mô tả:
//    Tên đầy đủ lớp: UDP.Employee
//    Các thuộc tính: id (String), name (String), salary (double), hireDate (String)
//    Hàm khởi tạo:
//        public Employee(String id, String name, double salary, String hireDate)
//    Trường dữ liệu: private static final long serialVersionUID = 20261107L
//
//Thực hiện:
//a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng ";studentCode;qCode". Ví dụ: ";B23DCCN006;ITleSdqV"
//b. Nhận thông điệp chứa: 08 byte đầu chứa chuỗi requestId, các byte còn lại chứa một đối tượng là thể hiện của lớp Employee từ server. Trong đó, các thuộc tính id, name, salary và hireDate đã được thiết lập sẵn.
//c. Thực hiện:
//    Chuẩn hóa name: viết hoa chữ cái đầu của mỗi từ, ví dụ "john doe" thành "John Doe".
//    Tăng salary: tăng x% lương, với x bằng tổng các chữ số của năm sinh.
//    Chuyển đổi hireDate từ định dạng yyyy-mm-dd sang dd/mm/yyyy. Ví dụ: "2023-07-15" thành "15/07/2023".
//    Gửi lại đối tượng đã được chuẩn hóa về server với cấu trúc: 08 byte đầu chứa chuỗi requestId và các byte còn lại chứa đối tượng Employee đã được sửa đổi.
//d. Đóng socket và kết thúc chương trình.
package Object;

import UDP.Employee;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SuaThongTinEmployee {
    public static void main(String[] args)throws Exception{
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        int sP = 2209;
        
        String request = ";B22DCCN008;78vGdYBV";
        DatagramPacket gui = new DatagramPacket(request.getBytes(), request.length(), sA, sP);
        socket.send(gui);
        
        
        byte[] buffer = new byte[1024];
        DatagramPacket nhan = new DatagramPacket(buffer, buffer.length);
        socket.receive(nhan);
        
        String id = new String(nhan.getData(), 0, 8);
        System.out.println(id );
        
        ByteArrayInputStream byte2 =new ByteArrayInputStream(nhan.getData(),8,nhan.getLength()-8);
        ObjectInputStream ois =new ObjectInputStream(byte2);
        
        Employee a = (Employee) ois.readObject();
        System.out.println(a);
        // xu ly de bai
        // xu ly name 
        String name = a.getName().toLowerCase();
        String namemoi = "";
        String [] partName = name.split(" ");
        for(String b : partName){
            String dau = Character.toUpperCase(b.charAt(0)) + b.substring(1,b.length());
            namemoi += dau +" ";
        }
        System.out.println(namemoi);
        a.setName(namemoi.trim());
        // luong
        String ns = a.getHireDate();
        
        // ns 
        String [] partns = ns.split("-");
        String nsmoi = partns[2] +"/" + partns[1] +"/"+partns[0];
        System.out.println(nsmoi);
        
        
        
        a.setHireDate(nsmoi);
        
        // luong
        int sum = 0 ; 
        for(int i = 0 ; i < partns[0].length(); i++){
            char c = partns[0].charAt(i);
            if(Character.isDigit(c)){
                sum += (c - '0');
            }
            
        }
        System.out.println(sum);
        double luongmoi = a.getSalary() * (1 + (sum / 100.0));
        double luongmoi2 = (double) Math.round(luongmoi * 100) / 100;
        System.out.println(luongmoi2);
        a.setSalary(luongmoi2);
        
        ByteArrayOutputStream byte3 =new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(byte3);
        oos.writeObject(a);
        
        byte[] guikq = new byte[8 + byte3.size()];
        System.arraycopy(id.getBytes(), 0, guikq, 0, 8);
        System.arraycopy(byte3.toByteArray(), 0, guikq, 8, byte3.size());
        DatagramPacket gui2 =new DatagramPacket(guikq, guikq.length, sA, sP);
        socket.send(gui2);
                

    }
}
