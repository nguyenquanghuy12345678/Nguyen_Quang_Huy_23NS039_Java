package View;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Book;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import org.hibernate.Session;
import org.hibernate.query.Query;

import DAO.PhieuNhap;
import Hibernate.HibernateUtil;

public class QuanLyDoanhThu extends JFrame {
    private JLabel titleLabel;
    private JEditorPane resultPane; // Thay vì JTextArea
    private JButton calculateRevenueButton;
    private JButton viewInvoiceButton;
    private JButton viewRevenueByMonthButton;
    private JButton inventoryManagementButton;
    private ArrayList<Book> bookings;
    private ArrayList<Choice> invoices;
    private JPanel mainPanel;

    public QuanLyDoanhThu() {
        setTitle("Quản Lý Doanh Thu");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        bookings = new ArrayList<>();
        invoices = new ArrayList<>();

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());//  thay layout cho khoi khoang trong
        getContentPane().add(mainPanel);

        titleLabel = new JLabel("Bảng Doanh Thu");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        resultPane = new JEditorPane();
        resultPane.setEditable(false);
        resultPane.setBackground(new Color(224, 255, 255));
        JScrollPane scrollPane = new JScrollPane(resultPane);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        calculateRevenueButton = new JButton("Tính Doanh Thu");
        calculateRevenueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayRevenueFromDatabase();
            }
        });
//        mainPanel.add(calculateRevenueButton, BorderLayout.SOUTH); dòng dùng cho phần mã trước 

        viewInvoiceButton = new JButton("Xem Hóa Đơn");
        viewInvoiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewInvoices();
            }
        });

        viewRevenueByMonthButton = new JButton("Doanh Thu Theo Tháng");
        viewRevenueByMonthButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewRevenueByMonth();
            }
        });

        inventoryManagementButton = new JButton("Quản Lý Nhập Kho");
        inventoryManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manageInventory();
            }
        });

        JPanel buttonPanel = new JPanel(new BorderLayout());
        JPanel buttonGridPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        
        
        buttonGridPanel.add(calculateRevenueButton); // Thêm nút tính doanh thu vào panel nút
        buttonGridPanel.add(viewInvoiceButton);
        buttonGridPanel.add(viewRevenueByMonthButton);
        buttonGridPanel.add(inventoryManagementButton);

        buttonPanel.add(buttonGridPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

//    private void calculateRevenue() {
//        double totalRevenue = 0;
//        for (Book booking : bookings) {
//            totalRevenue += ((Object) booking).getTotalPrice();
//        }
//        resultPane.setText("Doanh thu tổng cộng: $" + totalRevenue); // Sử dụng resultPane thay vì resultTextArea
//    }

    private void viewInvoices() {
	    try (Session session = HibernateUtil.getSession()) {
	        // Sử dụng HQL để truy vấn tất cả các phiếu nhập từ bảng phieunhap
	        Query<PhieuNhap> query = session.createQuery("FROM PhieuNhap", PhieuNhap.class);
	        List<PhieuNhap> phieuNhapList = query.getResultList();

	        // Hiển thị thông tin từ danh sách phiếu nhập
	        StringBuilder invoiceDetails = new StringBuilder();
	        invoiceDetails.append("Danh sách phiếu nhập:\n");
	        for (PhieuNhap phieuNhap : phieuNhapList) {
	            invoiceDetails.append("Mã phiếu nhập: ").append(phieuNhap.getMaPN()).append(", ").append("Ngày lập: ")
	                    .append(phieuNhap.getNgayLap()).append(", ").append("Tổng tiền: ")
	                    .append(phieuNhap.getTongTien()).append("\n");
	        }

	        JOptionPane.showMessageDialog(this, invoiceDetails.toString(), "Danh Sách Phiếu Nhập",
	                JOptionPane.INFORMATION_MESSAGE);
	    } catch (Exception ex) {
	        ex.printStackTrace();
	        JOptionPane.showMessageDialog(this, "Lỗi khi truy vấn cơ sở dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	    }
	}

    private void viewRevenueByMonth() {
        JOptionPane.showMessageDialog(this, "Chức năng chưa được triển khai.");
    }

    private void manageInventory() {
        JOptionPane.showMessageDialog(this, "Chức năng quản lý nhập kho chưa được triển khai.");
    }

    // Phương thức để thêm đặt phòng vào danh sách
    public void addBooking(Book booking) {
        bookings.add(booking);
    }

    public void addInvoice(Choice invoice) {
        invoices.add(invoice);
    }

    private void displayRevenueFromDatabase() {
        String query = "SELECT id, ma_dat_phong, tong_tien, created_at FROM doanh_thu";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlykhachsan", "root", "");
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            // Xây dựng chuỗi HTML để hiển thị bảng dữ liệu
            StringBuilder htmlResult = new StringBuilder();
            htmlResult.append("<html>");
            htmlResult.append("<table border='1'>");
            htmlResult.append("<tr><th>ID</th><th>Mã Đặt Phòng</th><th>Tổng Tiền ($)</th><th>Ngày Tạo</th></tr>");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String maDatPhong = resultSet.getString("ma_dat_phong");
                double tongTien = resultSet.getDouble("tong_tien");
                String createdAt = resultSet.getString("created_at");

                // Thêm dòng mới vào bảng HTML
                htmlResult.append("<tr><td>").append(id).append("</td><td>").append(maDatPhong).append("</td><td>")
                        .append(tongTien).append("</td><td>").append(createdAt).append("</td></tr>");
            }

            htmlResult.append("</table>");
            htmlResult.append("</html>");

            // Hiển thị kết quả trong JEditorPane bằng HTML
            resultPane.setContentType("text/html");
            resultPane.setText(htmlResult.toString());

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi truy vấn cơ sở dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                QuanLyDoanhThu quanLyDoanhThu = new QuanLyDoanhThu();
                quanLyDoanhThu.setVisible(true);
            }
        });
    }
}
