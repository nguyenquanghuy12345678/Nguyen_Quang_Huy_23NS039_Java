package View;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class test extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtFsbsdbds;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test frame = new test();
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
	public test() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1278, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtFsbsdbds = new JTextField();
		txtFsbsdbds.setBounds(401, 98, 514, 19);
		txtFsbsdbds.setText("fsbsdbds");
		txtFsbsdbds.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(txtFsbsdbds);
		txtFsbsdbds.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(189, 287, 45, 13);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(167, 310, 96, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(205, 353, 29, 21);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(156, 384, 85, 21);
		contentPane.add(btnNewButton);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("New radio button");
		rdbtnNewRadioButton.setBounds(156, 461, 103, 21);
		contentPane.add(rdbtnNewRadioButton);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("New check box");
		chckbxNewCheckBox.setBounds(156, 432, 93, 21);
		contentPane.add(chckbxNewCheckBox);
		
		JToggleButton tglbtnNewToggleButton = new JToggleButton("New toggle button");
		tglbtnNewToggleButton.setBounds(126, 488, 115, 21);
		contentPane.add(tglbtnNewToggleButton);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(174, 531, 42, 48);
		contentPane.add(textArea);
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(187, 604, 7, 19);
		contentPane.add(formattedTextField);
		
		table = new JTable();
		table.setRowSelectionAllowed(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"vvv", "bdsbsdb", "bdsbdsb", null, null},
				{null, null, null, null, null},
				{null, null, "helllllhelllli", null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "vcl", "New column"
			}
		));
		table.setBounds(301, 560, 436, 233);
		contentPane.add(table);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(399, 353, 436, 112);
		contentPane.add(progressBar);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(555, 461, 182, 48);
		contentPane.add(scrollBar);
		
		JProgressBar progressBar_1 = new JProgressBar();
		progressBar_1.setBounds(359, 216, 207, 105);
		contentPane.add(progressBar_1);
	}
}
