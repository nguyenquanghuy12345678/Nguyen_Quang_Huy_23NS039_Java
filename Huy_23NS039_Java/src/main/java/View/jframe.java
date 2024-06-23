package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class jframe extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtChngTrnhQun;
	private JList list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					jframe frame = new jframe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public jframe() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 846, 735);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setForeground(Color.RED);
		contentPane.setBorder(new EmptyBorder(10, 5, 15, 5));

		setContentPane(contentPane);
		
		list = new JList();
		contentPane.add(list);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setVerticalAlignment(SwingConstants.BOTTOM);
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(btnNewButton);
		
		txtChngTrnhQun = new JTextField();
		txtChngTrnhQun.setHorizontalAlignment(SwingConstants.CENTER);
		txtChngTrnhQun.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtChngTrnhQun.setText("Chương trình quản lí thuê đặt khách sạn");
		contentPane.add(txtChngTrnhQun);
		txtChngTrnhQun.setColumns(23);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
	}

}
