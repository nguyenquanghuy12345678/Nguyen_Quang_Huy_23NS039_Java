package Controller;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import View.QuanLyKhachSan;
public class dattaxituychinh extends JDialog {
	public dattaxituychinh() {
		
		
	}
	
	
/*	public dattaxituychinh() {
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(92, 51, 403, 325);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(0, 5, 393, 320);
		panel.add(lblNewLabel);
	} */
	
	
 /*	public dattaxituychinh() {
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 592, 438);
		getContentPane().add(panel);
	} */   // bởi vì Jfream khác với dialog nên khác nhau
	
	   private JTextField tenKhachHangField;
	    private JTextField diaChiField;
	    private JTextField thoiGianDonField;
	    private JTextField soNguoiField;
	    private JTextField viTriMuonDenField;
	    private JButton datTaxiConfirmButton;
            
	  
public void datTaxi()  {
        // Mở một JDialog mới để nhập thông tin đặt taxi
	
	 JDialog datTaxiDialog = new JDialog(this, "Đặt Taxi", true);
	    datTaxiDialog.setSize(400, 200);
	    datTaxiDialog.setLocationRelativeTo(this);
	    // Sử dụng getContentPane() để lấy Container của JDialog
	    Container contentPane = datTaxiDialog.getContentPane();
	    JPanel datTaxiPanel = new JPanel(new GridLayout(6, 2));
        datTaxiDialog.add(datTaxiPanel);

        tenKhachHangField = new JTextField();
        diaChiField = new JTextField();
        thoiGianDonField = new JTextField();
        soNguoiField = new JTextField();
        viTriMuonDenField = new JTextField();
        datTaxiConfirmButton = new JButton("Xác nhận");

        datTaxiPanel.add(new JLabel("Tên khách hàng:"));
        datTaxiPanel.add(tenKhachHangField);
        datTaxiPanel.add(new JLabel("Địa chỉ đón:"));
        datTaxiPanel.add(diaChiField);
        datTaxiPanel.add(new JLabel("Thời gian đón:"));
        datTaxiPanel.add(thoiGianDonField);
        datTaxiPanel.add(new JLabel("Số người:"));
        datTaxiPanel.add(soNguoiField);
        datTaxiPanel.add(new JLabel("Vị trí muốn đến:"));
        datTaxiPanel.add(viTriMuonDenField);

        datTaxiPanel.add(new JLabel());
        datTaxiPanel.add(datTaxiConfirmButton);

	    datTaxiConfirmButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            String tenKhachHang = tenKhachHangField.getText();
	            String diaChi = diaChiField.getText();
	            String thoiGianDon = thoiGianDonField.getText();
                int soNguoi = Integer.parseInt(soNguoiField.getText());
                String viTriMuonDen = viTriMuonDenField.getText();
            luuDatTaxi(tenKhachHang, diaChi, thoiGianDon, soNguoi, viTriMuonDen);
                datTaxiDialog.dispose();
	        }
	    });

	    // Thêm JPanel vào getContentPane()

	    contentPane.add(datTaxiPanel);

	    datTaxiDialog.setVisible(true);
}
       public String gettenkhachhang() {
    	   return  tenKhachHangField.getText() ;
       } // tại hàm để lấy tên khách hàng khi nhập tên khách hàng vào textfield thì từ nó.lấy(chữ)//



	private void luuDatTaxi(String tenKhachHang, String diaChi, String thoiGianDon, int soNguoi, String viTriMuonDen) {
		  if (tenKhachHang.isEmpty() || diaChi.isEmpty() || thoiGianDon.isEmpty() || viTriMuonDen.isEmpty()  ) {
		            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
        // Thực hiện lưu thông tin đặt taxi vào cơ sở dữ liệu
    	QuanLyKhachSan a = new QuanLyKhachSan() ; 
    	a.connectToDatabase(); 
    	Connection connection = a.getConnection();  // phải mở rồi nhận đối tượng connection của lớp QLKS rồi mới kết nôi được
    	String query = "INSERT INTO dat_taxi (ten_khach_hang, dia_chi, thoi_gian_don, so_nguoi, vi_tri_muon_den) VALUES (?, ?, ?, ?, ?)";
  
		try (PreparedStatement preparedStatement =  connection.prepareStatement(query)) {
            preparedStatement.setString(1, tenKhachHang);
            preparedStatement.setString(2, diaChi);
            preparedStatement.setString(3, thoiGianDon);
            preparedStatement.setInt(4, soNguoi);
            preparedStatement.setString(5, viTriMuonDen);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "ĐẶT THÀNH CÔNG!", "RẤT VUI ĐƯỢC PHỤC BẠN  "+ gettenkhachhang(),JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Lỗi khi đặt taxi!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi đặt taxi!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    } 

    
	public static void main(String[] args) {
	    // Ví dụ về cách sử dụng: tạo một phiên bản của dattaxituychinh và gọi phương thức datTaxi()
	   dattaxituychinh dialog = new dattaxituychinh();
	        dialog.datTaxi();
	}



	
	
}
 


