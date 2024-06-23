package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class DangNhapDialog extends JFrame {
//	public void setUsername(String username) {
//		this.username = username;
//	}

	public static DangNhapDialog getInstance() {
		return instance;
	}

	private Connection connection;
	private JPasswordField passwordField;
	private JFormattedTextField usernameField;
	private JTextField txtChoMngBn;
	private String username ;
//	private String username ;
//	public static String usn;
	private static DangNhapDialog instance;
	
	public DangNhapDialog(JFrame parent) {
		
		super("Đăng Nhập");
		instance = this;
//        super(parent, "Đăng Nhập", true);
		setSize(630, 642);
		setLocationRelativeTo(parent);

		// Thêm hình ảnh của bạn
		ImageIcon icon = new ImageIcon("anh.jpg.jpg");
		JLabel imageLabel = new JLabel(new ImageIcon("D:\\pexels-steve-johnson-1269968.jpg"));
		imageLabel.setBounds(0, 0, 616, 606);
		imageLabel.setHorizontalAlignment(JLabel.CENTER);

		// Tạo panel chứa hình ảnh và các thành phần đăng nhập
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(new Color(255, 204, 255));
		mainPanel.setLayout(null);

		JButton btnNewButton = new JButton("Đăng nhập");
		btnNewButton.setBackground(new Color(216, 191, 216));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dangNhap();
			}
		});

		JButton thoatkk = new JButton("Thoát nhé");
		thoatkk.setBackground(new Color(152, 251, 152));
		thoatkk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("D:\\4714994_avatar_people_person_profile_student_icon (1).png"));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(461, 146, 65, 90);
		mainPanel.add(lblNewLabel_2);

		txtChoMngBn = new JTextField();
		txtChoMngBn.setBackground(new Color(0, 255, 127));
		txtChoMngBn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtChoMngBn.setForeground(new Color(128, 0, 255));
		txtChoMngBn.setText("CHÀO MỪNG BẠN ĐẾN VỚI CHƯƠNG TRÌNH QUẢN LÍ kHÁCH SẠN");
		txtChoMngBn.setBounds(50, 47, 499, 43);
		mainPanel.add(txtChoMngBn);
		txtChoMngBn.setColumns(10);

		thoatkk.setBounds(340, 300, 109, 33);
		mainPanel.add(thoatkk);

		btnNewButton.setBounds(182, 300, 98, 33);
		mainPanel.add(btnNewButton);

		passwordField = new JPasswordField();
		passwordField.setBackground(new Color(224, 255, 255));
		passwordField.setBounds(266, 243, 173, 19);
		mainPanel.add(passwordField);

		usernameField = new JFormattedTextField();
		usernameField.setBackground(new Color(255, 235, 205));
		usernameField.setBounds(266, 197, 173, 19);
		mainPanel.add(usernameField);

		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setForeground(new Color(253, 245, 230));
		lblNewLabel_1.setFont(new Font("Segoe UI Light", Font.BOLD, 17));
		lblNewLabel_1.setBounds(141, 240, 98, 19);
		mainPanel.add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel("Đăng_Nhập: ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Segoe UI Light", Font.BOLD, 17));
		lblNewLabel.setForeground(new Color(0, 250, 154));
		lblNewLabel.setBounds(141, 194, 109, 19);
		mainPanel.add(lblNewLabel);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBackground(new Color(255, 255, 0));
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setIcon(new ImageIcon("D:\\5094679_lock_padlock_password_security_icon.png"));
		lblNewLabel_3.setBounds(464, 224, 48, 55);
		mainPanel.add(lblNewLabel_3);
		mainPanel.add(imageLabel);

		getContentPane().add(mainPanel);
		JLabel hình = new JLabel("");
		hình.setBackground(new Color(153, 255, 255));
		hình.setBounds(0, 0, 616, 606);
		hình.setIcon(new ImageIcon("D:\\istockphoto-1637852242-612x612.jpg"));
		mainPanel.add(hình);

		connectToDatabase(); // Thêm hàm kết nối cơ sở dữ liệu
	}

//	public DangNhapDialog() {
//		để cái này thì khỏi sửa nhé cu // 
//	}

	private void connectToDatabase() {
		String url = "jdbc:mysql://localhost:3306/quanlykhachsan";
		String user = "root";
		String password = "";

		try {
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("Đã kết nối đến cơ sở dữ liệu!");
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Không kết nối được đến cơ sở dữ liệu!", "Lỗi",
					JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
	}

	private void dangNhap() {
		String username = usernameField.getText();
//		System.out.println(username);
		// thay vì gọi trực tiếp thì dùng khai báo biến //
//		username = usernameField.getText();
		this.username = username ;
//	     String a = username ;  
		char[] passwordChars = passwordField.getPassword();
		String password = new String(passwordChars);

		try {
			// Kết nối tới server
//  cái của cái này Socket socket = new Socket("localhost", 12345);
			Socket socket = new Socket("127.0.0.1", 12345);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			// Gửi yêu cầu đăng nhập
			out.println(username);
			out.println(password);

			// Nhận kết quả từ server
			String response = in.readLine();

			if (response.equals("SUCCESS")) {
				JOptionPane.showMessageDialog(this, "Đăng nhập thành công!", "Thành công",
						JOptionPane.INFORMATION_MESSAGE);
				dispose(); // Đóng cửa sổ đăng nhập
				new QuanLyKhachSan().setVisible(true); // Hiển thị giao diện QuanLiKhachSan
			} else {
				JOptionPane.showMessageDialog(this, "Tên đăng nhập hoặc mật khẩu không đúng!", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
			}

			// Đóng các luồng và socket
			out.close();
			in.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Lỗi khi đăng nhập!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	  public String getUsername() {
	        return this.username;
	    }
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				DangNhapDialog loginDialog = new DangNhapDialog(null);
				loginDialog.setVisible(true);
			}
		});
	}
}
