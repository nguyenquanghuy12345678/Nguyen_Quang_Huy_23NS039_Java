 package View     ;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import Controller.dattaxituychinh;
/* public DangNhapDialog(Frame parent) {
        super(parent, "Đăng Nhập", true);
        setSize(630, 642);
        setLocationRelativeTo(parent);*/
public class dattaxi extends JFrame {
    private JTable table;
    private JTextField txtPhngTn;
    private JTextField txtDanhScht;
 
     public dattaxi() {
    	 
    		setTitle("Quản Lý Thuê Đặt Phòng Khách Sạn");   // khởi tạo biên đầu kết nối data và một số phần đầu tiên : tên tab ,... //
		setSize(700, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
        // Kết nối đến cơ sở dữ liệu trong constructor
    

        getContentPane().setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setBackground(new Color(175, 238, 238));
        panel.setForeground(new Color(127, 255, 212));
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);
        
                JButton datTaxiButton = new JButton("Đặt Taxi");
                datTaxiButton.setBounds(0, 376, 220, 36);
                panel.add(datTaxiButton);
                datTaxiButton.setBackground(new Color(255, 255, 153));
                
                        JLabel lblNewLabel = new JLabel("");
                        lblNewLabel.setBounds(0, 0, 686, 532);
                        lblNewLabel.setIcon(new ImageIcon("D:\\tải xuống (3).jpg"));
                        panel.add(lblNewLabel);
                        
                        table = new JTable();
                        table.setBounds(326, 106, 339, 356);
                        panel.add(table);
                        
                        txtPhngTn = new JTextField();
                        txtPhngTn.setForeground(new Color(0, 0, 102));
                        txtPhngTn.setBackground(new Color(204, 255, 204));
                        txtPhngTn.setHorizontalAlignment(SwingConstants.CENTER);
                        txtPhngTn.setText("ĐẶT PHƯƠNG TIỆN KHÁCH SẠN");
                        txtPhngTn.setBounds(182, 29, 285, 19);
                        panel.add(txtPhngTn);
                        txtPhngTn.setColumns(10);
                        
                        txtDanhScht = new JTextField();
                        txtDanhScht.setBackground(new Color(230, 230, 250));
                        txtDanhScht.setHorizontalAlignment(SwingConstants.CENTER);
                        txtDanhScht.setText("Danh sách đặt ");
                        txtDanhScht.setBounds(443, 77, 96, 19);
                        panel.add(txtDanhScht);
                        txtDanhScht.setColumns(10);
                        
                        JButton btnNewButton = new JButton("Xem danh sách");
                        btnNewButton.setBounds(0, 426, 220, 36);
                        panel.add(btnNewButton);
                        
                        JLabel lblNewLabel_1 = new JLabel("");
                        lblNewLabel_1.setIcon(new ImageIcon("D:\\5452472_automobile_cab_car_taxi_transportation_icon.png"));
                        lblNewLabel_1.setBounds(238, 364, 66, 48);
                        panel.add(lblNewLabel_1);
                        
                        JLabel lblNewLabel_2 = new JLabel("");
                        lblNewLabel_2.setIcon(new ImageIcon("D:\\4698592_eye_find_view_vision_zoom_icon.png"));
                        lblNewLabel_2.setBounds(238, 422, 45, 40);
                        panel.add(lblNewLabel_2);
                        btnNewButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                hienThiDanhSachDatTaxi(); // Gọi phương thức để hiển thị danh sách
                            }
                        });
                       
                        datTaxiButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                dattaxituychinh a = new dattaxituychinh();
                                a.datTaxi()       ; 
                            }
                        });
        JPanel buttonPanel = new JPanel();
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
    
     }
     private void hienThiDanhSachDatTaxi() {
         try {
             // Kết nối đến cơ sở dữ liệu
             QuanLyKhachSan quanLyKhachSan = new QuanLyKhachSan();
             quanLyKhachSan.connectToDatabase();
             Connection connection = quanLyKhachSan.getConnection();

             // Thực hiện truy vấn SQL
             String query = "SELECT * FROM dat_taxi";
             try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                  ResultSet resultSet = preparedStatement.executeQuery()) {
                 // Tạo DefaultTableModel để chứa dữ liệu cho JTable
                 DefaultTableModel model = new DefaultTableModel();

                 // Lấy metadata từ ResultSet để lấy tên cột
                 for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                     model.addColumn(resultSet.getMetaData().getColumnName(i));
                 }

                 // Thêm dữ liệu từ ResultSet vào DefaultTableModel
                 while (resultSet.next()) {
                     Object[] rowData = new Object[resultSet.getMetaData().getColumnCount()];
                     for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                         rowData[i - 1] = resultSet.getObject(i);
                     }
                     model.addRow(rowData);
                 }

                 // Hiển thị dữ liệu trên JTable
                 table.setModel(model);
             }
         } catch (SQLException ex) {
             ex.printStackTrace();
             JOptionPane.showMessageDialog(this, "Lỗi khi hiển thị danh sách đặt taxi!", "Lỗi", JOptionPane.ERROR_MESSAGE);
         }
     }

    // ... (phần khác giữ nguyên)

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            dattaxi taxi = new dattaxi(); 
            taxi.setVisible(true);
        });
    }
}