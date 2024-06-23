package View;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Danhgia extends JFrame {
    private Connection connection;

    // Constructor với tham số Connection
    public Danhgia(Connection connection) {
        this.connection = connection;
    }

    // Constructor không tham số
    public Danhgia() {
    	getContentPane().setLayout(null); }
    	
           // Điều này không làm gì cả hoặc bạn có thể để nó trống nếu không cần thiết
    

    void showBookingOptions() {
        // Create a JFrame for booking options
        JFrame bookingOptionsFrame = new JFrame("Tùy Chọn Đặt Phòng");
        bookingOptionsFrame.setSize(300, 150);
        bookingOptionsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        bookingOptionsFrame.setLocationRelativeTo(null);

        // Create a JPanel with GridLayout
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1));

        JButton danhGiaButton = new JButton("Đánh Giá");
        JButton xemTatCaDanhGiaButton = new JButton("Xem Tất Cả Đánh Giá");

        buttonPanel.add(danhGiaButton);
        buttonPanel.add(xemTatCaDanhGiaButton);

        danhGiaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Gọi hàm danhGia() khi nút Đánh Giá được nhấn
                danhGia();
            }
        });

        xemTatCaDanhGiaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Gọi hàm xemTatCaDanhGia() khi nút Xem Tất Cả Đánh Giá được nhấn
                xemTatCaDanhGia();
            }
        });

        // Add the panel to the frame
        bookingOptionsFrame.getContentPane().add(buttonPanel);

        // Make the frame visible
        bookingOptionsFrame.setVisible(true);
    }


	  private void danhGia() {
		    JTextField tenKhachHangField = new JTextField();
		    JTextField maDatPhongField = new JTextField();
		    JTextField danhGiaField = new JTextField();
		    JTextField lyDoField = new JTextField();

		    JPanel panel = new JPanel(new GridLayout(4, 2));
		    panel.add(new JLabel("Tên Khách Hàng:"));
		    panel.add(tenKhachHangField);
		    panel.add(new JLabel("Mã Đặt Phòng:"));
		    panel.add(maDatPhongField);
		    panel.add(new JLabel("Đánh Giá (1-5):"));
		    panel.add(danhGiaField);
		    panel.add(new JLabel("Lý Do:"));
		    panel.add(lyDoField);

		    int result = JOptionPane.showConfirmDialog(this, panel, "Đánh Giá", JOptionPane.OK_CANCEL_OPTION);

		    if (result == JOptionPane.OK_OPTION) {
		        try {
		            String tenKhachHang = tenKhachHangField.getText();
		            int maDatPhong = Integer.parseInt(maDatPhongField.getText());
		            int danhGia = Integer.parseInt(danhGiaField.getText());
		            String lyDo = lyDoField.getText();

		            // Kiểm tra xem mã đặt phòng và tên khách hàng có tồn tại trong bảng thong_tin_dat_phong không
		            String checkQuery = "SELECT * FROM thong_tin_dat_phong WHERE ma_dat_phong = ? AND ten_khach = ?";
		            try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {
		                checkStatement.setInt(1, maDatPhong);
		                checkStatement.setString(2, tenKhachHang);
		                ResultSet resultSet = checkStatement.executeQuery();

		                if (!resultSet.next()) {
		                    // Nếu không tìm thấy dòng dữ liệu, hiển thị thông báo lỗi
		                    JOptionPane.showMessageDialog(this, "Mã đặt phòng hoặc tên khách hàng không tồn tại!",
		                            "Lỗi", JOptionPane.ERROR_MESSAGE);
		                    return;
		                }
		            }

		            // Nếu kiểm tra thành công, tiến hành thêm đánh giá
		            String insertQuery = "INSERT INTO danh_gia (ma_dat_phong, ten_khach_hang, danh_gia, ly_do) VALUES (?, ?, ?, ?)";
		            try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
		                insertStatement.setInt(1, maDatPhong);
		                insertStatement.setString(2, tenKhachHang);
		                insertStatement.setInt(3, danhGia);
		                insertStatement.setString(4, lyDo);
		                insertStatement.executeUpdate();

		                JOptionPane.showMessageDialog(this, "Đánh giá của bạn đã được ghi nhận!", "Đánh Giá",
		                        JOptionPane.INFORMATION_MESSAGE);
		            }
		        } catch (NumberFormatException | SQLException ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(this, "Nhập đánh giá không hợp lệ hoặc thiếu thông tin khách hàng/mã đặt phòng.", "Lỗi Đánh Giá",
		                    JOptionPane.ERROR_MESSAGE);
		        }
		    }
		}



	private void xemTatCaDanhGia() {
      try (Statement statement = connection.createStatement()) {
          ResultSet resultSet = statement.executeQuery("SELECT * FROM danh_gia");

          // Tạo một DefaultTableModel để sử dụng với JTable
          DefaultTableModel model = new DefaultTableModel();
          model.addColumn("Mã Đặt Phòng");
          model.addColumn("Tên Khách Hàng");
          model.addColumn("Đánh Giá");
          model.addColumn("Lý Do");

          while (resultSet.next()) {
              int maDatPhong = resultSet.getInt("ma_dat_phong");
              String tenKhachHang = resultSet.getString("ten_khach_hang");
              int danhGia = resultSet.getInt("danh_gia");
              String lyDo = resultSet.getString("ly_do");

              // Thêm dữ liệu vào model
              model.addRow(new Object[]{maDatPhong, tenKhachHang, danhGia, lyDo});
          }

          if (model.getRowCount() > 0) {
              // Tạo JTable với model đã tạo
              JTable table = new JTable(model);

              // Hiển thị bảng trong một JScrollPane để có thanh cuộn khi cần
              JScrollPane scrollPane = new JScrollPane(table);

              // Hiển thị thông báo với bảng
              JOptionPane.showMessageDialog(this, scrollPane, "Tất Cả Đánh Giá",
                      JOptionPane.INFORMATION_MESSAGE);
          } else {
              JOptionPane.showMessageDialog(this, "Không có đánh giá nào trong cơ sở dữ liệu!", "Tất Cả Đánh Giá",
                      JOptionPane.INFORMATION_MESSAGE);
          }
      } catch (SQLException e) {
          e.printStackTrace();
          JOptionPane.showMessageDialog(this, "Lỗi khi lấy danh sách đánh giá từ cơ sở dữ liệu!", "Lỗi",
                  JOptionPane.ERROR_MESSAGE);
     
             }
	     }
	}

