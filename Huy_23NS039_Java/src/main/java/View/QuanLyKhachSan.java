package View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import Chat.ButtonChat;
import Controller.KhachSan;
import Model.Phong;

import client.Client;
import Chat.ClientStarter;
import View.DangNhapDialog;
import Server.ServerGUI;
import Server.LoginServer;
import View.Book_Food;
public class QuanLyKhachSan extends JFrame {

	private Connection connection;
	private JTable table;
	private DefaultTableModel tableModel;
	private Container danhSachDatPhong;
	private JTextField idTextField;
	private int maDatPhong;
	private Icon icom;
	private JTextField txttDng;
	private String username;
	

	private boolean chatButtonClicked = false; // Khai báo biến ở mức lớp _ để chỉ được bấm 1 lần ok //
//	private String usernameFromLogin;
//	private String usernameFromLogin;
	private JLabel userInfoLabel;

	public JLabel UserInfoFrame(String username) {
		return userInfoLabel = new JLabel("Logged in user: " + username);
	}

	public QuanLyKhachSan() {
		// CỰC KÌ QUAN TRỌNG -> DO MÌNH THAM CHIẾU GIÁ TRỊ THẬT VÀO //
		
//		this.username = username;
		
		connectToDatabase();
		setTitle("Quản Lý Thuê Đặt Phòng Khách Sạn");
		setSize(1377, 882);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(new Color(255, 255, 255));
		mainPanel.setLayout(null);

		JButton btnNewButton = new JButton("Order vvvv");
		btnNewButton.setBounds(202, 513, 190, 33);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dattaxi dt = new dattaxi();
				dt.setVisible(true);
			}
		});
		// hiện class BieuDO_PhanTich
		JButton btnNewButton_4 = new JButton("Phân Tích");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BieuDo_PhanTich bieudo = new BieuDo_PhanTich();
				bieudo.setVisible(true);
			}
		});

		// Nút chat tào lao nếu không được có thể bỏ _ được dùng để gọi class ButtonChat
		JButton btnNewButton_6 = new JButton("Chat");
		btnNewButton_6.setBounds(973, 682, 146, 21);
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						if (!chatButtonClicked) {
							chatButtonClicked = true;
							ClientStarter.startClient();
						} else {
							JOptionPane.showMessageDialog(mainPanel, "Chat client already started.");
						}
					}
				});
			}
		});

		
		
		
		
		// phần panel chứa THÔNG TIN NGƯỜI DÙNG
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(37, 584, 488, 158);
		mainPanel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_12 = new JLabel("");
		lblNewLabel_12.setIcon(new ImageIcon(
				"C:\\Users\\This PC\\eclipse-workspace\\khachsangpt_2\\huy\\src\\main\\resources\\anh.jpg.jpg"));
		lblNewLabel_12.setBounds(0, 0, 488, 158);
		panel_1.add(lblNewLabel_12);

		JLabel lblNewLabel_11 = new JLabel("");
		lblNewLabel_11.setBounds(178, 43, 82, 66);
		panel_1.add(lblNewLabel_11);
		lblNewLabel_11.setIcon(new ImageIcon("D:\\ICONS\\403022_business man_male_user_avatar_profile_icon.png"));

		// Để có thể truy cập getUsername(), cần phải có đối tượng DangNhapDialog đã
		// hiển thị
		// Lấy tên người dùng từ DangNhapDialog

		userInfoLabel = new JLabel();
		userInfoLabel.setText("Logged in user: " + DangNhapDialog.getInstance().getUsername());
		System.out.print(DangNhapDialog.getInstance().getUsername());
		userInfoLabel.setBounds(255, 63, 233, 46);
		panel_1.add(userInfoLabel);
		
		
//		JLabel viewusername = new JLabel("Xin chao "); // gọi lại phương thức ); //
//		viewusername.setBounds(291, 33, 140, 25);
//		panel_1.add(viewusername);

		JLabel lblNewLabel_10 = new JLabel("");
		lblNewLabel_10.setIcon(new ImageIcon("D:\\ICONS\\5296520_bubble_chat_mobile_whatsapp_whatsapp logo_icon.png"));
		lblNewLabel_10.setBounds(1151, 678, 51, 48);
		mainPanel.add(lblNewLabel_10);
		// PHẦN PANEL CHỨA THÔNG TIN NGƯỜI DÙNG //

		
		
		
		
		
		
		
		
		
		
		
		mainPanel.add(btnNewButton_6);
		// ----------------------------------------Phần riêng thôi _ không quan trọng do
		// import từ project khác //
		JLabel lblNewLabel_9 = new JLabel("");
		lblNewLabel_9.setIcon(new ImageIcon("D:\\15718_connect_network_to_icon.png"));
		lblNewLabel_9.setBounds(1151, 620, 61, 48);
		mainPanel.add(lblNewLabel_9);

		// gắn sự kiện cho nút 5 để mở class Info_Hotel
		JButton btnNewButton_5 = new JButton("Information");
		btnNewButton_5.setBounds(973, 633, 146, 21);
		mainPanel.add(btnNewButton_5);
		btnNewButton_5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Xử lý sự kiện khi nút được nhấn
				// Mở ra class Info_Hotel
				Info_Hotel infoHotel = new Info_Hotel();
				infoHotel.setVisible(true); // Hiển thị frame của Info_Hotel
			}
		});

		JLabel lblNewLabel_8 = new JLabel("New label");
		lblNewLabel_8.setIcon(new ImageIcon("D:\\229119_table_icon.png"));
		lblNewLabel_8.setBounds(1177, 134, 45, 35);
		mainPanel.add(lblNewLabel_8);

		JLabel lblNewLabel_6 = new JLabel("New label");
		lblNewLabel_6.setIcon(new ImageIcon("D:\\5027817_analysis_analytics_conference_presentation_icon.png"));
		lblNewLabel_6.setBounds(1151, 575, 45, 35);
		mainPanel.add(lblNewLabel_6);

		JLabel lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setIcon(new ImageIcon("D:\\299107_money_icon.png"));
		lblNewLabel_5.setBounds(1151, 505, 45, 48);
		mainPanel.add(lblNewLabel_5);

		JLabel lblNewLabel_4_6 = new JLabel("New label");
		lblNewLabel_4_6.setIcon(new ImageIcon("D:\\6071826_delivery_food_meal_order_food delivery_icon.png"));
		lblNewLabel_4_6.setBounds(121, 504, 59, 51);
		mainPanel.add(lblNewLabel_4_6);

		JLabel lblNewLabel_4_5 = new JLabel("New label");
		lblNewLabel_4_5.setIcon(new ImageIcon("D:\\4698581_find_info_information_magnifier_search_icon.png"));
		lblNewLabel_4_5.setBounds(121, 449, 45, 48);
		mainPanel.add(lblNewLabel_4_5);

		JLabel lblNewLabel_4_4 = new JLabel("New label");
		lblNewLabel_4_4.setIcon(new ImageIcon("D:\\2290850_clean_delete_garbage_recycle bin_trash_icon.png"));
		lblNewLabel_4_4.setBounds(121, 392, 45, 52);
		mainPanel.add(lblNewLabel_4_4);

		JLabel lblNewLabel_4_3 = new JLabel("New label");
		lblNewLabel_4_3
				.setIcon(new ImageIcon("D:\\2890587_ai_artificial intelligence_electronics_eye_robotics_icon.png"));
		lblNewLabel_4_3.setBounds(121, 347, 58, 35);
		mainPanel.add(lblNewLabel_4_3);

		JLabel lblNewLabel_4_2 = new JLabel("New label");
		lblNewLabel_4_2.setIcon(new ImageIcon("D:\\1622833_checkmark_done_exam_list_pencil_icon.png"));
		lblNewLabel_4_2.setBounds(121, 290, 45, 32);
		mainPanel.add(lblNewLabel_4_2);

		JLabel lblNewLabel_4_1 = new JLabel("New label");
		lblNewLabel_4_1.setIcon(new ImageIcon("D:\\3011627_couple_lesbian_love_icon.png"));
		lblNewLabel_4_1.setBounds(121, 228, 45, 46);
		mainPanel.add(lblNewLabel_4_1);

		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setIcon(new ImageIcon("D:\\5452468_buildings_holidays_hotel_vacations_icon.png"));
		lblNewLabel_4.setBounds(121, 170, 45, 46);
		mainPanel.add(lblNewLabel_4);
		btnNewButton_4.setBounds(975, 574, 144, 21);
		mainPanel.add(btnNewButton_4);

		JButton btnNewButton_3 = new JButton("Doanh Thu");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QuanLyDoanhThu doanhthu = new QuanLyDoanhThu();
				doanhthu.setVisible(true);
			}
		});
		btnNewButton_3.setBounds(975, 519, 144, 21);
		mainPanel.add(btnNewButton_3);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(217, 255, 217));
		panel.setBounds(552, 149, 350, 621);
		mainPanel.add(panel);
		panel.setLayout(null);

		JButton btnNewButton_1 = new JButton("Đặt Ăn Uống ");
		btnNewButton_1.setBounds(54, 330, 112, 21);
		btnNewButton_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Khởi tạo và hiển thị JFrame từ một class khác
		        Book_Food food = new Book_Food(connection) ; // Thay thế TenCuaClassKhac bằng tên thực của class bạn muốn hiển thị
		        food.setVisible(true);
		    }
		});

		panel.add(btnNewButton_1);

		txttDng = new JTextField();
		txttDng.setBounds(76, 10, 207, 19);
		txttDng.setHorizontalAlignment(SwingConstants.CENTER);
		txttDng.setText("Phục vu nhu cầu của khách ");
		panel.add(txttDng);
		txttDng.setColumns(10);

		// Theo Eclipse thì Các mục Jtree sẽ được mặc định là 3 và không chỉnh sửa trên
		// code source
		// , khi ta thay thế thì mục sẽ đổi và không hiện mặc định nếu ta chưa chạy //
		// Tạo cây thứ nhất
//		JTree tree = new JTree();
//		tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("Food") {
//			{
//				DefaultMutableTreeNode node_1;
//				node_1 = new DefaultMutableTreeNode("Ngũ cốc");
//				node_1.add(new DefaultMutableTreeNode("Bánh mì: 15$"));
//				node_1.add(new DefaultMutableTreeNode("Mì: 20$"));
//				node_1.add(new DefaultMutableTreeNode("Bún: 10$"));
//				node_1.add(new DefaultMutableTreeNode("Ngô: 5$"));
//				panel.add(node_1); // Thêm node_1 vào gốc
//
//				node_1 = new DefaultMutableTreeNode("Rau_Trái cây");
//				node_1.add(new DefaultMutableTreeNode("Cải bó xôi: 5$"));
//				node_1.add(new DefaultMutableTreeNode("Cà rốt: 7$"));
//				node_1.add(new DefaultMutableTreeNode("Táo: 4$"));
//				node_1.add(new DefaultMutableTreeNode("Cam: 6$"));
//				getContentPane().add(node_1); // Thêm node_1 vào gốc
//
//				node_1 = new DefaultMutableTreeNode("Giàu Protein");
//				node_1.add(new DefaultMutableTreeNode("Thịt gà: 35$"));
//				node_1.add(new DefaultMutableTreeNode("Cá: 22$"));
//				node_1.add(new DefaultMutableTreeNode("Trứng: 9$"));
//				node_1.add(new DefaultMutableTreeNode("Đậu nành: 5.5$"));
//				getContentPane().add(node_1); // Thêm node_1 vào gốc
//			}
//		}));
//		tree.setBounds(44, 52, 127, 268);
//		panel.add(tree);
//
//		// Tạo cây thứ hai
//		JTree tree_1 = new JTree();
//		tree_1.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("Entertainment") {
//			{
//				DefaultMutableTreeNode node_1;
//				node_1 = new DefaultMutableTreeNode("Movies");
//				node_1.add(new DefaultMutableTreeNode("Inception"));
//				node_1.add(new DefaultMutableTreeNode("Interstellar"));
//				node_1.add(new DefaultMutableTreeNode("The Matrix"));
//				node_1.add(new DefaultMutableTreeNode("The Dark Knight"));
//				getContentPane().add(node_1); // Thêm node_1 vào gốc
//
//				node_1 = new DefaultMutableTreeNode("Music");
//				node_1.add(new DefaultMutableTreeNode("Rock"));
//				node_1.add(new DefaultMutableTreeNode("Pop"));
//				node_1.add(new DefaultMutableTreeNode("Jazz"));
//				node_1.add(new DefaultMutableTreeNode("Classical"));
//				getContentPane().add(node_1); // Thêm node_1 vào gốc
//
//				node_1 = new DefaultMutableTreeNode("Games");
//				node_1.add(new DefaultMutableTreeNode("Chess"));
//				node_1.add(new DefaultMutableTreeNode("Monopoly"));
//				node_1.add(new DefaultMutableTreeNode("Scrabble"));
//				node_1.add(new DefaultMutableTreeNode("Sudoku"));
//				getContentPane().add(node_1); // Thêm node_1 vào gốc
//
//				node_1 = new DefaultMutableTreeNode("Books");
//				node_1.add(new DefaultMutableTreeNode("1984"));
//				node_1.add(new DefaultMutableTreeNode("Brave New World"));
//				node_1.add(new DefaultMutableTreeNode("Moby Dick"));
//				node_1.add(new DefaultMutableTreeNode("War and Peace"));
//				getContentPane().add(node_1); // Thêm node_1 vào gốc
//			}
//		}));
//		tree_1.setBounds(181, 52, 129, 268);
//		panel.add(tree_1);
		JTree tree1 = new JTree();
		DefaultMutableTreeNode foodRoot = new DefaultMutableTreeNode("Food");
		DefaultMutableTreeNode nguCocNode = new DefaultMutableTreeNode("Ngũ cốc");
		nguCocNode.add(new DefaultMutableTreeNode("Bánh mì: 15$"));
		nguCocNode.add(new DefaultMutableTreeNode("Mì: 20$"));
		nguCocNode.add(new DefaultMutableTreeNode("Bún: 10$"));
		nguCocNode.add(new DefaultMutableTreeNode("Ngô: 5$"));
		foodRoot.add(nguCocNode);

		DefaultMutableTreeNode rauTraiCayNode = new DefaultMutableTreeNode("Rau_Trái cây");
		rauTraiCayNode.add(new DefaultMutableTreeNode("Cải bó xôi: 5$"));
		rauTraiCayNode.add(new DefaultMutableTreeNode("Cà rốt: 7$"));
		rauTraiCayNode.add(new DefaultMutableTreeNode("Táo: 4$"));
		rauTraiCayNode.add(new DefaultMutableTreeNode("Cam: 6$"));
		foodRoot.add(rauTraiCayNode);

		DefaultMutableTreeNode giauProteinNode = new DefaultMutableTreeNode("Giàu Protein");
		giauProteinNode.add(new DefaultMutableTreeNode("Thịt gà: 35$"));
		giauProteinNode.add(new DefaultMutableTreeNode("Cá: 22$"));
		giauProteinNode.add(new DefaultMutableTreeNode("Trứng: 9$"));
		giauProteinNode.add(new DefaultMutableTreeNode("Đậu nành: 5.5$"));
		foodRoot.add(giauProteinNode);

		tree1.setModel(new DefaultTreeModel(foodRoot));
		tree1.setBounds(50, 50, 116, 259);
		panel.add(tree1);

		// Create the second JTree for "Entertainment"
		JTree tree2 = new JTree();
		DefaultMutableTreeNode entertainmentRoot = new DefaultMutableTreeNode("Entertainment");
		DefaultMutableTreeNode moviesNode = new DefaultMutableTreeNode("Movies");
		moviesNode.add(new DefaultMutableTreeNode("Inception"));
		moviesNode.add(new DefaultMutableTreeNode("Interstellar"));
		moviesNode.add(new DefaultMutableTreeNode("The Matrix"));
		moviesNode.add(new DefaultMutableTreeNode("The Dark Knight"));
		entertainmentRoot.add(moviesNode);

		DefaultMutableTreeNode musicNode = new DefaultMutableTreeNode("Music");
		musicNode.add(new DefaultMutableTreeNode("Rock"));
		musicNode.add(new DefaultMutableTreeNode("Pop"));
		musicNode.add(new DefaultMutableTreeNode("Jazz"));
		musicNode.add(new DefaultMutableTreeNode("Classical"));
		entertainmentRoot.add(musicNode);

		DefaultMutableTreeNode gamesNode = new DefaultMutableTreeNode("Games");
		gamesNode.add(new DefaultMutableTreeNode("Chess"));
		gamesNode.add(new DefaultMutableTreeNode("Monopoly"));
		gamesNode.add(new DefaultMutableTreeNode("Scrabble"));
		gamesNode.add(new DefaultMutableTreeNode("Sudoku"));
		entertainmentRoot.add(gamesNode);

		DefaultMutableTreeNode booksNode = new DefaultMutableTreeNode("Books");
		booksNode.add(new DefaultMutableTreeNode("1984"));
		booksNode.add(new DefaultMutableTreeNode("Brave New World"));
		booksNode.add(new DefaultMutableTreeNode("Moby Dick"));
		booksNode.add(new DefaultMutableTreeNode("War and Peace"));
		entertainmentRoot.add(booksNode);

		tree2.setModel(new DefaultTreeModel(entertainmentRoot));
		tree2.setBounds(190, 50, 123, 259);
		panel.add(tree2);

		JButton btnNewButton_2 = new JButton("Đặt Nơi Giải Trí");
		btnNewButton_2.setBounds(190, 329, 112, 21);
		panel.add(btnNewButton_2);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(102, 557, 37, 22);
		panel.add(textArea);

		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(165, 557, 37, 22);
		panel.add(textArea_1);

		JTextArea textArea_2 = new JTextArea();
		textArea_2.setBounds(224, 557, 31, 22);
		panel.add(textArea_2);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("D:\\285661_star_icon.png"));
		lblNewLabel_1.setBounds(44, 419, 45, 50);
		panel.add(lblNewLabel_1);

		JLabel label = new JLabel("New label");
		label.setIcon(new ImageIcon("D:\\285661_star_icon.png"));
		label.setBounds(102, 423, 45, 43);
		panel.add(label);

		JLabel label_1 = new JLabel("New label");
		label_1.setIcon(new ImageIcon("D:\\285661_star_icon.png"));
		label_1.setBounds(165, 424, 45, 40);
		panel.add(label_1);

		JLabel label_2 = new JLabel("New label");
		label_2.setIcon(new ImageIcon("D:\\285661_star_icon.png"));
		label_2.setBounds(224, 424, 45, 40);
		panel.add(label_2);

		JLabel label_3 = new JLabel("New label");
		label_3.setIcon(new ImageIcon("D:\\285661_star_icon.png"));
		label_3.setBounds(279, 423, 45, 43);
		panel.add(label_3);

		JLabel lblNewLabel_7 = new JLabel("New label");
		lblNewLabel_7.setIcon(new ImageIcon("D:\\3558104_ball_barbecue_food_meat_sausage_icon.png"));
		lblNewLabel_7.setBounds(76, 361, 45, 50);
		panel.add(lblNewLabel_7);

		JLabel lblNewLabel_7_1 = new JLabel("");
		lblNewLabel_7_1.setIcon(new ImageIcon("D:\\4927747_emoji_entertainment_glasses_icon.png"));
		lblNewLabel_7_1.setBounds(224, 361, 65, 53);
		panel.add(lblNewLabel_7_1);

		JLabel lblNewLabel_3 = new JLabel("Chức Năng");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setForeground(new Color(255, 255, 0));
		lblNewLabel_3.setBackground(new Color(255, 128, 128));
		lblNewLabel_3.setBounds(108, 87, 353, 51);
		mainPanel.add(lblNewLabel_3);
		mainPanel.add(btnNewButton);

		JButton xoaTheoIDButton = new JButton("Xóa Theo ID");
		xoaTheoIDButton.setBounds(202, 407, 190, 37);
		xoaTheoIDButton.setForeground(new Color(0, 0, 0));
		mainPanel.add(xoaTheoIDButton);
		xoaTheoIDButton.addActionListener(e -> {
			xoaTheoID();
			capNhatDuLieuBangPhong();
		});

		JButton timKiemButton = new JButton("Tìm Kiếm");
		timKiemButton.setBounds(202, 465, 190, 32);
		timKiemButton.setForeground(new Color(255, 0, 128));
		mainPanel.add(timKiemButton);
		timKiemButton.addActionListener(e -> showSearchResults());

		JButton xemToanBoDuLieuButton = new JButton("Xem Toàn Bộ Dữ Liệu");
		xemToanBoDuLieuButton.setBackground(new Color(0, 255, 255));
		xemToanBoDuLieuButton.setBounds(202, 346, 190, 37);
		xemToanBoDuLieuButton.setForeground(new Color(0, 0, 0));
		mainPanel.add(xemToanBoDuLieuButton);

		xemToanBoDuLieuButton.addActionListener(e -> {

			xemToanBoDuLieu();
			capNhatDuLieuBangPhong();
		});

		JButton xemDanhSachDatPhongButton = new JButton("Xem DS Đặt Phòng");
		xemDanhSachDatPhongButton.setBackground(new Color(0, 255, 255));
		xemDanhSachDatPhongButton.setBounds(202, 288, 190, 36);
		xemDanhSachDatPhongButton.setForeground(new Color(0, 0, 0));
		mainPanel.add(xemDanhSachDatPhongButton);

		xemDanhSachDatPhongButton.addActionListener(e -> {
			JFrame frame = new JFrame();
			new QuanLyKhachSan().hienThiDanhSachDatPhong(frame);
			frame.setVisible(true);
		});

		JButton datPhongButton = new JButton("Đặt Phòng");
		datPhongButton.setBounds(198, 237, 190, 33);
		datPhongButton.setForeground(new Color(0, 0, 0));
		mainPanel.add(datPhongButton);

		datPhongButton.addActionListener(e -> {
			datPhong();
			capNhatDuLieuBangPhong();
		});

		JButton xemPhongButton = new JButton("Đánh Giá Độ OK");
		xemPhongButton.setBackground(new Color(127, 255, 212));
		xemPhongButton.setBounds(198, 179, 190, 37);
		xemPhongButton.setForeground(new Color(0, 0, 0));
		mainPanel.add(xemPhongButton);
		xemPhongButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Danhgia danhgia = new Danhgia(connection);
				danhgia.showBookingOptions();
			}
		});

//        JLabel lblNewLabel_1 = new JLabel("");
//        lblNewLabel_1.setBounds(43, 118, 374, 524);
//        mainPanel.add(lblNewLabel_1);
//        // thiet lap icon ơ duoi 
//        lblNewLabel_1.setIcon(icom);
		JLabel hinhnho = new JLabel("");
		hinhnho.setHorizontalAlignment(SwingConstants.CENTER);
		hinhnho.setBounds(37, 149, 487, 414);
		hinhnho.setIcon(new ImageIcon("D:\\SLIDE\\istockphoto-1409304190-612x612.jpg"));
		mainPanel.add(hinhnho);

		tableModel = new DefaultTableModel();
		tableModel.addColumn("Số Phòng");
		tableModel.addColumn("Loại Phòng");
		tableModel.addColumn("Giá");
		tableModel.addColumn("Tình Trạng");
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblNewLabel = new JLabel("QUẢN LÍ KHÁCH SẠN");
		lblNewLabel.setBackground(new Color(128, 255, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(451, 35, 531, 46);
		mainPanel.add(lblNewLabel);
		lblNewLabel.setForeground(new Color(0, 128, 128));
		lblNewLabel.setFont(new Font("Segoe UI Light", Font.BOLD, 40));

		table = new JTable(tableModel);
		table.setToolTipText("Hello xin chào");
		table.setForeground(new Color(255, 255, 255));
		table.setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		table.setBackground(new Color(91, 115, 164));
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(953, 179, 273, 315);
		mainPanel.add(scrollPane);
		getContentPane().add(mainPanel);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(953, 149, 214, 20);
		mainPanel.add(progressBar);
		progressBar.setStringPainted(true);
		progressBar.setIndeterminate(true);
		progressBar.setValue(101);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBackground(new Color(0, 128, 255));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setIcon(new ImageIcon("D:\\—Pngtree—vibrant abstract art colorful paint_13609343.jpg"));
		lblNewLabel_2.setBounds(0, 0, 1363, 828);
		mainPanel.add(lblNewLabel_2);

		// trang trí //
		ImageIcon icom = new ImageIcon("D:\\Laptrinhweb\\tải xuống.jpg");
	}

	public void connectToDatabase() { // tạo cái này để khai báo bên class khác và có quyền truy cập
		String url = "jdbc:mysql://localhost:3306/quanlykhachsan";
		String user = "root";
		String password = "";

		try {
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("Kết nối đến MySQL thành công!");
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Không thể kết nối đến cơ sở dữ liệu!", "Lỗi Kết Nối",
					JOptionPane.ERROR_MESSAGE);
			System.exit(1); // tên của mã lỗi để mình biết về địa chỉ lỗi trong bài , do mình tự đặt //
		}

	}

	public Connection getConnection() {
		return connection;
	}

	private void hienThiDanhSachPhong() {
		StringBuilder thongTinPhong = new StringBuilder("Danh Sách Phòng:\n");
		try (Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery("SELECT * FROM phong");

			while (resultSet.next()) {
				int soPhong = resultSet.getInt("so_phong");
				String loaiPhong = resultSet.getString("loai_phong");
				double gia = resultSet.getDouble("gia");
				String tinhTrang = resultSet.getString("tinh_trang");

				thongTinPhong.append(String.format("Phòng %d - %s - %.2f VND - Tình trạng: %s\n", soPhong, loaiPhong,
						gia, tinhTrang));
			}

			if (thongTinPhong.length() > 0) {
				JOptionPane.showMessageDialog(this, thongTinPhong.toString(), "Danh Sách Phòng",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "Không có phòng nào trong cơ sở dữ liệu!", "Danh Sách Phòng",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Lỗi khi lấy danh sách phòng từ cơ sở dữ liệu!", "Lỗi",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void datPhong() {
		JTextField tenField = new JTextField();
		JTextField diaChiField = new JTextField();
		JTextField soDienThoaiField = new JTextField();
		JTextField soNguoiField = new JTextField();
		JTextField ngayDatField = new JTextField("YYYY-MM-DD");
		JTextField ngayTraField = new JTextField("YYYY-MM-DD");

		JPanel panel = new JPanel(new GridLayout(6, 2));
		panel.add(new JLabel("Tên Khách:"));
		panel.add(tenField);
		panel.add(new JLabel("Địa Chỉ Khách:"));
		panel.add(diaChiField);
		panel.add(new JLabel("Số Điện Thoại Khách:"));
		panel.add(soDienThoaiField);
		panel.add(new JLabel("Số Người:"));
		panel.add(soNguoiField);
		panel.add(new JLabel("Ngày Đặt (YYYY-MM-DD):"));
		panel.add(ngayDatField);
		panel.add(new JLabel("Ngày Trả (YYYY-MM-DD):"));
		panel.add(ngayTraField);

		int ketQua = JOptionPane.showConfirmDialog(this, panel, "Đặt Phòng", JOptionPane.OK_CANCEL_OPTION);
		if (ketQua == JOptionPane.OK_OPTION) {
			try {
				String tenKhach = tenField.getText();
				String diaChiKhach = diaChiField.getText();
				String soDienThoaiKhach = soDienThoaiField.getText();
				int soNguoi = Integer.parseInt(soNguoiField.getText());

				// Chuyển đổi chuỗi thành kiểu Date
				SimpleDateFormat dinhDangNgay = new SimpleDateFormat("yyyy-MM-dd");
				Date ngayDat = dinhDangNgay.parse(ngayDatField.getText());
				Date ngayTra = dinhDangNgay.parse(ngayTraField.getText());

				// Lấy kết nối từ lớp DatabaseConnection
				Connection conn = getConnection();
				if (conn != null) {
					ArrayList<Phong> phongTrong = new KhachSan(conn).layPhongTrong();
					Object[] luaChonPhong = phongTrong.toArray();

					if (!phongTrong.isEmpty()) {
						Phong phongChon = (Phong) JOptionPane.showInputDialog(this, "Chọn Phòng để Đặt:", "Chọn Phòng",
								JOptionPane.QUESTION_MESSAGE, null, luaChonPhong, luaChonPhong[0]);

						if (phongChon != null) {
							if (phongChon.isDaDat()) {
								JOptionPane.showMessageDialog(this, "Phòng đã được đặt trước đó!", "Lỗi Đặt Phòng",
										JOptionPane.ERROR_MESSAGE);
							} else {
								phongChon.datPhong(tenKhach, diaChiKhach, soDienThoaiKhach, soNguoi, ngayDat, ngayTra);

								// Truyền kiểu Date vào phương thức
								themThongTinDatPhong(tenKhach, diaChiKhach, soDienThoaiKhach, soNguoi, ngayDat, ngayTra,
										phongChon, ngayDatField, ngayTraField);

								double tongTien = phongChon.tinhTongTien();
								try {
									// Lấy mã đặt phòng mới nhất từ cơ sở dữ liệu
									int maDatPhong = 0;
									PreparedStatement stmt = conn
											.prepareStatement("SELECT MAX(ma_dat_phong) FROM thong_tin_dat_phong");
									ResultSet rs = stmt.executeQuery();
									if (rs.next()) {
										maDatPhong = rs.getInt(1) + 1; // Tăng mã đặt phòng lên một
									}

									// Insert dữ liệu vào bảng danh thu
									stmt = conn.prepareStatement(
											"INSERT INTO doanh_thu (ma_dat_phong, tong_tien) VALUES (?, ?)");
									stmt.setInt(1, maDatPhong);
									stmt.setDouble(2, tongTien);
									stmt.executeUpdate();

									JOptionPane.showMessageDialog(this,
											"Cảm ơn bạn đã đặt phòng!\nTổng Tiền: $" + tongTien, "Đặt Phòng Thành Công",
											JOptionPane.INFORMATION_MESSAGE);
								} catch (SQLException ex) {
									ex.printStackTrace();
									JOptionPane.showMessageDialog(this, "Lỗi khi lưu tổng tiền vào bảng danh thu!",
											"Lỗi Lưu Dữ Liệu", JOptionPane.ERROR_MESSAGE);
								}
							}
						} else {
							JOptionPane.showMessageDialog(this, "Không có phòng nào được chọn!", "Lỗi Đặt Phòng",
									JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(this, "Không có phòng trống!", "Lỗi Đặt Phòng",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(this, "Không thể kết nối đến cơ sở dữ liệu!", "Lỗi Kết Nối",
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this, "Nhập không hợp lệ! Hãy nhập thông tin hợp lệ.", "Lỗi Đặt Phòng",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	// Thêm ngoặc này để đóng phương thức datPhong
//	private void Themcaitongtien() {
//		  double tongTien = phongChon.tinhTongTien();
//	}
	private void xoaTheoID() {
		try {
			// Prompt user for the ID to delete
			String idToDelete = JOptionPane.showInputDialog(this, "Nhập ID cần xóa:");

			if (idToDelete != null && !idToDelete.isEmpty()) {
				// Convert the ID to integer
				int idToDeleteInt = Integer.parseInt(idToDelete);

				// Delete the record with the specified ID from the "thong_tin_dat_phong" table
				String query = "DELETE FROM thong_tin_dat_phong WHERE ma_dat_phong = ?";
				try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
					preparedStatement.setInt(1, idToDeleteInt); // truyen cho em no la tham so dau tien de truyen vao ?
																// thực thi cau lenh xóa
					int rowCount = preparedStatement.executeUpdate(); // hm nay dùn de dem va tra ve so luong dong bi
																		// anh huong
					// vay trong ham gt tra ve la cac dong da bị xia , cũng có nghĩa là nếu gt của
					// iddeleteInt giống nhau thì có trả về tất cả dòng bị xoas
					// va dung nó trong xử ls ngoại lệ
					if (rowCount > 0) {
						JOptionPane.showMessageDialog(this, "Dữ liệu có ID " + idToDeleteInt + " đã được xóa!",
								"Xóa Theo ID", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(this,
								"Không có dữ liệu có ID " + idToDeleteInt + " trong cơ sở dữ liệu!", "Xóa Theo ID",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập ID cần xóa.", "Xóa Theo ID",
						JOptionPane.WARNING_MESSAGE);
			}
		} catch (NumberFormatException | SQLException ex) { // dăt biet có ngoại le exception kà numberformat xu lí
			// trong trương hop loi khi trong cau lenh chuyên doi int cua chuoi nêu chuoi
			// nhap ko hop le de chuyen doi
			// vd: abc ko the chuyen thanh so dc
			// cung nhu ex sql kia : ghi loi khi sai trong ket nôi , xu lí sql , ...
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, "Lỗi khi xóa dữ liệu theo ID!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void showSearchResults() {
		JTextField searchField = new JTextField();

		JPanel panel = new JPanel(new GridLayout(1, 2));
		panel.add(new JLabel("Nhập thông tin tìm kiếm:"));
		panel.add(searchField);

		int result = JOptionPane.showConfirmDialog(this, panel, "Tìm Kiếm", JOptionPane.OK_CANCEL_OPTION);
		// biến trả về từ hộp thoại xác nhận
		if (result == JOptionPane.OK_OPTION) {
			String searchTerm = searchField.getText().trim();
			// chuoi co gia tri la sT kia = gt nhập vào
			// trim loại bỏ các khoảng trắng
			// từ đối tượng là của Jtextfield
			if (!searchTerm.isEmpty()) { // kt rỗng
				String query = "SELECT * FROM thong_tin_dat_phong WHERE ma_dat_phong = ? OR " + "ten_khach = ? OR "
						+ "dia_chi_khach = ? OR " + "so_dien_thoai_khach = ? OR " + "so_nguoi = ? OR "
						+ "ngay_dat = ? OR " + "ngay_tra = ? OR " + "so_phong = ?";

				try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
					preparedStatement.setString(1, searchTerm);
					preparedStatement.setString(2, searchTerm);
					preparedStatement.setString(3, searchTerm);
					preparedStatement.setString(4, searchTerm);
					preparedStatement.setString(5, searchTerm);

					// Xử lý ngày, chắc chắn rằng searchTerm có định dạng ngày hợp lệ

					preparedStatement.setString(6, searchTerm);
					preparedStatement.setString(7, searchTerm);

					// Ngày không hợp lệ, bạn có thể xử lý nó theo ý của mình

					preparedStatement.setString(8, searchTerm);

					ResultSet resultSet = preparedStatement.executeQuery();

					// Tạo DefaultTableModel với tên cột
					DefaultTableModel tableModel = new DefaultTableModel();
					tableModel.addColumn("Mã Đặt Phòng");
					tableModel.addColumn("Tên Khách");
					tableModel.addColumn("Địa Chỉ");
					tableModel.addColumn("SĐT");
					tableModel.addColumn("Số Người");
					tableModel.addColumn("Ngày Đặt");
					tableModel.addColumn("Ngày Trả");
					tableModel.addColumn("Số Phòng");

					// Đổ dữ liệu từ ResultSet vào table model
					while (resultSet.next()) {
						int maDatPhong = resultSet.getInt("ma_dat_phong");
						String tenKhach = resultSet.getString("ten_khach");
						String diaChiKhach = resultSet.getString("dia_chi_khach");
						String soDienThoaiKhach = resultSet.getString("so_dien_thoai_khach");
						int soNguoi = resultSet.getInt("so_nguoi");
						Date ngayDat = resultSet.getDate("ngay_dat");
						Date ngayTra = resultSet.getDate("ngay_tra");
						int soPhong = resultSet.getInt("so_phong");

						// Thêm một dòng vào table model
						tableModel.addRow(new Object[] { maDatPhong, tenKhach, diaChiKhach, soDienThoaiKhach, soNguoi,
								ngayDat, ngayTra, soPhong });
					}

					// Tạo JTable với table model
					JTable searchResultTable = new JTable(tableModel);

					// Hiển thị JTable trong một JScrollPane để hỗ trợ cuộn nếu có nhiều dòng
					JScrollPane scrollPane = new JScrollPane(searchResultTable);

					// Hiển thị dữ liệu trong một JFrame thay vì sử dụng JOptionPane
					JFrame resultFrame = new JFrame("Kết Quả Tìm Kiếm");
					resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					resultFrame.getContentPane().add(scrollPane);
					resultFrame.pack();
					resultFrame.setLocationRelativeTo(this);
					resultFrame.setVisible(true);

				} catch (SQLException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin tìm kiếm.", "Tìm Kiếm",
						JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	private void themThongTinDatPhong(String tenKhach, String diaChiKhach, String soDienThoaiKhach, int soNguoi,
			Date ngayDat, Date ngayTra, Phong phong, JTextField ngayDatField, JTextField ngayTraField) {
		try {
			String query = "INSERT INTO thong_tin_dat_phong (ten_khach, dia_chi_khach, so_dien_thoai_khach, so_nguoi, ngay_dat, ngay_tra, so_phong) VALUES (?, ?, ?, ?, ?, ?, ?)";

			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				preparedStatement.setString(1, tenKhach);
				preparedStatement.setString(2, diaChiKhach);
				preparedStatement.setString(3, soDienThoaiKhach);
				preparedStatement.setInt(4, soNguoi);
				preparedStatement.setDate(5, new java.sql.Date(ngayDat.getTime()));
				preparedStatement.setDate(6, new java.sql.Date(ngayTra.getTime()));
				preparedStatement.setInt(7, phong.getSoPhong());

				preparedStatement.executeUpdate();

			}

		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Lỗi khi thêm thông tin đặt phòng vào cơ sở dữ liệu!", "Lỗi Đặt Phòng",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void hienThiDanhSachDatPhong(JFrame frame) {
		// Tạo JTable
		JTable bangDuLieu = new JTable();

		// Tạo JScrollPane để chứa JTable
		JScrollPane jScrollPane = new JScrollPane(bangDuLieu);

		// Thêm JScrollPane vào JFrame của giao diện chính
		frame.getContentPane().add(jScrollPane); // Sử dụng getContentPane() thay vì add trực tiếp trên JFrame

		// Cài đặt thuộc tính JFrame của giao diện chính
		// frame.setDefaultCloseOperation(JFrame); //.EXIT_ON_CLOSE//
		frame.pack(); // ham tron pt jfreame dong goi beuatiful
		frame.setLocationRelativeTo(null);

		// Lấy danh sách phòng
		ArrayList<Phong> danhSachDatPhong = new KhachSan(connection).layDanhSachDatPhong();

		// Tạo DefaultTableModel cho JTable
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Số Phòng");
		model.addColumn("Loại Phòng");
		model.addColumn("Giá");
		model.addColumn("Tình Trạng");

		for (Phong phong : danhSachDatPhong) {
			if (phong.isDaDat()) {
				// Thêm dòng mới vào model
				model.addRow(new Object[] { phong.getSoPhong(), phong.getLoaiPhong(), phong.getGia(),
						phong.getTinhTrang() });
			}
		}

		// Set model cho JTable
		bangDuLieu.setModel(model);
	}

	private void xemToanBoDuLieu() {

		HashSet<Integer> daHienThi = new HashSet<>();
		StringBuilder thongTinDatPhong = new StringBuilder("Danh Sách Đặt Phòng:\n");

		try (Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery("SELECT * FROM thong_tin_dat_phong");

			// Tạo DefaultTableModel với tên cột
			DefaultTableModel tableModel = new DefaultTableModel();
			tableModel.addColumn("Mã Đặt Phòng");
			tableModel.addColumn("Tên Khách");
			tableModel.addColumn("Địa Chỉ");
			tableModel.addColumn("Số Điện Thoại");
			tableModel.addColumn("Số Người");
			tableModel.addColumn("Ngày Đặt");
			tableModel.addColumn("Ngày Trả");
			tableModel.addColumn("Số Phòng");

			// Đổ dữ liệu từ ResultSet vào table model
			while (resultSet.next()) {
				int maDatPhong = resultSet.getInt("ma_dat_phong");
				String tenKhach = resultSet.getString("ten_khach");
				String diaChiKhach = resultSet.getString("dia_chi_khach");
				String soDienThoaiKhach = resultSet.getString("so_dien_thoai_khach");
				int soNguoi = resultSet.getInt("so_nguoi");
				Date ngayDat = resultSet.getDate("ngay_dat");
				Date ngayTra = resultSet.getDate("ngay_tra");
				int soPhong = resultSet.getInt("so_phong");

				// Thêm một dòng vào table model
				tableModel.addRow(new Object[] { maDatPhong, tenKhach, diaChiKhach, soDienThoaiKhach, soNguoi, ngayDat,
						ngayTra, soPhong });
			}

			// Tạo JTable với table model
			JTable dataTable = new JTable(tableModel);

			// Hiển thị JTable trong một JScrollPane để hỗ trợ cuộn nếu có nhiều dòng
			JScrollPane scrollPane = new JScrollPane(dataTable);

			// Hiển thị dữ liệu trong một hộp thoại
			JOptionPane.showMessageDialog(this, scrollPane, "Danh Sách Đặt Phòng", JOptionPane.INFORMATION_MESSAGE);

		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Lỗi khi lấy danh sách đặt phòng từ cơ sở dữ liệu!", "Lỗi",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	private void capNhatDuLieuBangPhong() {
		// Xóa dữ liệu cũ trong tableModel
		while (tableModel.getRowCount() > 0) {
			tableModel.removeRow(0);
		}

		// Lấy dữ liệu mới từ cơ sở dữ liệu và thêm vào tableModel
		try (Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery("SELECT * FROM phong");

			while (resultSet.next()) {
				int soPhong = resultSet.getInt("so_phong");
				String loaiPhong = resultSet.getString("loai_phong");
				double gia = resultSet.getDouble("gia");
				String tinhTrang = resultSet.getString("tinh_trang");

				// Thêm dòng mới vào tableModel
				tableModel.addRow(new Object[] { soPhong, loaiPhong, gia, tinhTrang });
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, "Lỗi khi lấy danh sách phòng từ cơ sở dữ liệu!", "Lỗi",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void dongKetNoi() {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
				System.out.println("Đã đóng kết nối đến MySQL.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e) {
				e.printStackTrace();
			}

			new QuanLyKhachSan().setVisible(true); // dặt trang thái hiển thị của GUI
		});
	}
}