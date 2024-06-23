package View;

import Model.Food;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Book_Food extends JFrame {

    private Connection connection;
    private JTable table;
    private DefaultTableModel tableModel;
    private ArrayList<Food> foodList;
    private JTextField idOrderField, nameField, idPhongField, nameFoodField, amountField;
    private JButton orderButton, editButton, deleteButton, printButton;

    public Book_Food(Connection connection) {
        this.connection = connection;
        this.foodList = new ArrayList<>();

        setTitle("Chức năng quản lý");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1248, 759);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 1234, 722);
        getContentPane().add(panel);
        panel.setLayout(null);

        // Tạo bảng với mô hình
        tableModel = new DefaultTableModel(
                new Object[][]{},
                new String[]{
                        "ID_order", "Name", "ID_Phong", "Name_Food", "Amount", "Thanh_toan"
                }) {
            Class[] columnTypes = new Class[]{
                    Integer.class, String.class, String.class, String.class, Integer.class, Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        };

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(300, 113, 934, 609);
        panel.add(scrollPane);

        JLabel lblNewLabel = new JLabel("Hãy đặt món ăn bạn yêu thích");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 27));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(362, 29, 465, 51);
        panel.add(lblNewLabel);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(128, 255, 255));
        panel_1.setBounds(0, 113, 303, 609);
        panel.add(panel_1);
        panel_1.setLayout(null);

        orderButton = new JButton("Đặt đồ ăn");
        orderButton.setBounds(105, 310, 153, 53);
        panel_1.add(orderButton);
        editButton = new JButton("Sửa phiếu");
        editButton.setBounds(105, 373, 153, 53);
        panel_1.add(editButton);
        deleteButton = new JButton("Xóa");
        deleteButton.setBounds(105, 438, 153, 59);
        panel_1.add(deleteButton);
        printButton = new JButton("In bill");
        printButton.setBounds(105, 507, 153, 53);
        panel_1.add(printButton);

        // Xử lý sự kiện cho các nút
        orderButton.addActionListener(e -> orderFood());
        editButton.addActionListener(e -> editFood());
        deleteButton.addActionListener(e -> deleteFood());
        printButton.addActionListener(e -> exportToExcel());

        // Thêm các trường nhập dữ liệu
        idOrderField = new JTextField();
        idOrderField.setBounds(95, 23, 100, 30);
        panel_1.add(idOrderField);
        nameField = new JTextField();
        nameField.setBounds(95, 63, 100, 30);
        panel_1.add(nameField);
        idPhongField = new JTextField();
        idPhongField.setBounds(95, 102, 100, 30);
        panel_1.add(idPhongField);
        nameFoodField = new JTextField();
        nameFoodField.setBounds(95, 143, 100, 30);
        panel_1.add(nameFoodField);
        amountField = new JTextField();
        amountField.setBounds(95, 183, 100, 30);
        panel_1.add(amountField);

        JLabel idOrderLabel = new JLabel("ID Order:");
        idOrderLabel.setBounds(10, 10, 200, 30);
        panel_1.add(idOrderLabel);
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(10, 50, 200, 30);
        panel_1.add(nameLabel);
        JLabel idPhongLabel = new JLabel("ID Phòng:");
        idPhongLabel.setBounds(10, 90, 200, 30);
        panel_1.add(idPhongLabel);
        JLabel nameFoodLabel = new JLabel("Name Food:");
        nameFoodLabel.setBounds(10, 130, 200, 30);
        panel_1.add(nameFoodLabel);
        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(10, 183, 200, 30);
        panel_1.add(amountLabel);

        setVisible(true);
    }

    // Phương thức thêm đối tượng Food vào bảng
    public void addFoodToTable(Food food) {
        foodList.add(food);
        tableModel.addRow(new Object[]{
                food.getIdOrder(),
                food.getName(),
                food.getIdPhong(),
                food.getNameFood(),
                food.getAmount(),
                food.isThanhToan()
        });
    }

    // Phương thức đặt món ăn
    private void orderFood() {
        try {
            int idOrder = Integer.parseInt(idOrderField.getText());
            String name = nameField.getText();
            String idPhong = idPhongField.getText();
            String nameFood = nameFoodField.getText();
            int amount = Integer.parseInt(amountField.getText());
            boolean thanhToan = false; // Mặc định là chưa thanh toán

            Food food = new Food(idOrder, name, idPhong, nameFood, amount, thanhToan);
            addFoodToTable(food);

            JOptionPane.showMessageDialog(this, "Đặt món thành công!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng số cho ID Order và Amount.");
        }
    }

    // Phương thức sửa phiếu
    private void editFood() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            try {
                int idOrder = Integer.parseInt(idOrderField.getText());
                String name = nameField.getText();
                String idPhong = idPhongField.getText();
                String nameFood = nameFoodField.getText();
                int amount = Integer.parseInt(amountField.getText());
                boolean thanhToan = false; // Mặc định là chưa thanh toán

                Food food = new Food(idOrder, name, idPhong, nameFood, amount, thanhToan);
                foodList.set(selectedRow, food);

                tableModel.setValueAt(food.getIdOrder(), selectedRow, 0);
                tableModel.setValueAt(food.getName(), selectedRow, 1);
                tableModel.setValueAt(food.getIdPhong(), selectedRow, 2);
                tableModel.setValueAt(food.getNameFood(), selectedRow, 3);
                tableModel.setValueAt(food.getAmount(), selectedRow, 4);
                tableModel.setValueAt(food.isThanhToan(), selectedRow, 5);

                JOptionPane.showMessageDialog(this, "Sửa phiếu thành công!");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng số cho ID Order và Amount.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một phiếu để sửa.");
        }
    }

    // Phương thức xóa phiếu
    private void deleteFood() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            foodList.remove(selectedRow);
            tableModel.removeRow(selectedRow);
            JOptionPane.showMessageDialog(this, "Xóa phiếu thành công!");
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một phiếu để xóa.");
        }
    }

    // Phương thức xuất danh sách món ăn ra file Excel
    private void exportToExcel() {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Danh sách món ăn");

        // Header row
        Row headerRow = sheet.createRow(0);
        String[] columns = {"ID_order", "Name", "ID_Phong", "Name_Food", "Amount", "Thanh_toan"};
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
        }

        // Data rows
        for (int i = 0; i < foodList.size(); i++) {
            Food food = foodList.get(i);
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(food.getIdOrder());
            row.createCell(1).setCellValue(food.getName());
            row.createCell(2).setCellValue(food.getIdPhong());
            row.createCell(3).setCellValue(food.getNameFood());
            row.createCell(4).setCellValue(food.getAmount());
            row.createCell(5).setCellValue(food.isThanhToan());
        }

        // Đặt đường dẫn tới nơi lưu file
        String filePath = "C:\\Users\\This PC\\eclipse-workspace\\khachsangpt_2\\huy\\src\\main\\resources\\File\\File_xlsx_monan.xlsx";

        // Đảm bảo thư mục tồn tại
        try {
            Files.createDirectories(Paths.get(filePath).getParent());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Write to file
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
            workbook.close();
            JOptionPane.showMessageDialog(this, "Xuất danh sách món ăn ra file Excel thành công!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi xuất file Excel: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Sử dụng ví dụ (đảm bảo truyền một kết nối thực trong trường hợp sử dụng thực tế)
        Book_Food bookFood = new Book_Food(null);
        bookFood.addFoodToTable(new Food(1, "John Doe", "101", "Pizza", 2, false));
        bookFood.addFoodToTable(new Food(2, "Jane Doe", "102", "Burger", 1, true));
    }
}







//
//   Khi chưa dùng ngay điaj chỉ file
//
//
//
//package View;
//
//import Model.Food;
//import java.awt.Color;
//import java.awt.Font;
//import java.sql.Connection;
//import java.util.ArrayList;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//import javax.swing.JTextField;
//import javax.swing.SwingConstants;
//import javax.swing.table.DefaultTableModel;
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import com.mysql.cj.result.Row;
//
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//public class Book_Food extends JFrame {
//
//scss
//Sao chép mã
//private Connection connection;
//private JTable table;
//private DefaultTableModel tableModel;
//private ArrayList<Food> foodList;
//private JTextField idOrderField, nameField, idPhongField, nameFoodField, amountField;
//private JButton orderButton, editButton, deleteButton, printButton;
//
//public Book_Food(Connection connection) {
//    this.connection = connection;
//    this.foodList = new ArrayList<>();
//
//    setTitle("Chức năng quản lý");
//    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//    setSize(1248, 759);
//    setLocationRelativeTo(null);
//    getContentPane().setLayout(null);
//
//    JPanel panel = new JPanel();
//    panel.setBounds(0, 0, 1234, 722);
//    getContentPane().add(panel);
//    panel.setLayout(null);
//
//    // Tạo bảng với mô hình
//    tableModel = new DefaultTableModel(
//            new Object[][]{},
//            new String[]{
//                    "ID_order", "Name", "ID_Phong", "Name_Food", "Amount", "Thanh_toan"
//            }) {
//        Class[] columnTypes = new Class[]{
//                Integer.class, String.class, String.class, String.class, Integer.class, Boolean.class
//        };
//
//        public Class getColumnClass(int columnIndex) {
//            return columnTypes[columnIndex];
//        }
//    };
//
//    table = new JTable(tableModel);
//    JScrollPane scrollPane = new JScrollPane(table);
//    scrollPane.setBounds(300, 113, 934, 609);
//    panel.add(scrollPane);
//
//    JLabel lblNewLabel = new JLabel("Hãy đặt món ăn bạn yêu thích");
//    lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 27));
//    lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
//    lblNewLabel.setBounds(362, 29, 465, 51);
//    panel.add(lblNewLabel);
//
//    JPanel panel_1 = new JPanel();
//    panel_1.setBackground(new Color(128, 255, 255));
//    panel_1.setBounds(0, 113, 303, 609);
//    panel.add(panel_1);
//    panel_1.setLayout(null);
//
//    orderButton = new JButton("Đặt đồ ăn");
//    orderButton.setBounds(67, 283, 153, 59);
//    panel_1.add(orderButton);
//    editButton = new JButton("Sửa phiếu");
//    editButton.setBounds(67, 352, 153, 49);
//    panel_1.add(editButton);
//    deleteButton = new JButton("Xóa");
//    deleteButton.setBounds(67, 411, 153, 49);
//    panel_1.add(deleteButton);
//    printButton = new JButton("In bill");
//    printButton.setBounds(67, 483, 153, 76);
//    panel_1.add(printButton);
//
//    // Xử lý sự kiện cho các nút
//    orderButton.addActionListener(e -> orderFood());
//    editButton.addActionListener(e -> editFood());
//    deleteButton.addActionListener(e -> deleteFood());
//    printButton.addActionListener(e -> exportToExcel());
//
//    // Thêm các trường nhập dữ liệu
//    idOrderField = new JTextField();
//    idOrderField.setBounds(95, 23, 100, 30);
//    panel_1.add(idOrderField);
//    nameField = new JTextField();
//    nameField.setBounds(67, 63, 100, 30);
//    panel_1.add(nameField);
//    idPhongField = new JTextField();
//    idPhongField.setBounds(95, 102, 100, 30);
//    panel_1.add(idPhongField);
//    nameFoodField = new JTextField();
//    nameFoodField.setBounds(95, 143, 100, 30);
//    panel_1.add(nameFoodField);
//    amountField = new JTextField();
//    amountField.setBounds(95, 243, 100, 30);
//    panel_1.add(amountField);
//
//    JLabel idOrderLabel = new JLabel("ID Order:");
//    idOrderLabel.setBounds(10, 10, 200, 30);
//    panel_1.add(idOrderLabel);
//    JLabel nameLabel = new JLabel("Name:");
//    nameLabel.setBounds(10, 50, 200, 30);
//    panel_1.add(nameLabel);
//    JLabel idPhongLabel = new JLabel("ID Phòng:");
//    idPhongLabel.setBounds(10, 90, 200, 30);
//    panel_1.add(idPhongLabel);
//    JLabel nameFoodLabel = new JLabel("Name Food:");
//    nameFoodLabel.setBounds(10, 130, 200, 30);
//    panel_1.add(nameFoodLabel);
//    JLabel amountLabel = new JLabel("Amount:");
//    amountLabel.setBounds(10, 243, 200, 30);
//    panel_1.add(amountLabel);
//
//    setVisible(true);
//}
//
//// Phương thức thêm đối tượng Food vào bảng
//public void addFoodToTable(Food food) {
//    foodList.add(food);
//    tableModel.addRow(new Object[]{
//            food.getIdOrder(),
//            food.getName(),
//            food.getIdPhong(),
//            food.getNameFood(),
//            food.getAmount(),
//            food.isThanhToan()
//    });
//}
//
//// Phương thức đặt món ăn
//private void orderFood() {
//    try {
//        int idOrder = Integer.parseInt(idOrderField.getText());
//        String name = nameField.getText();
//        String idPhong = idPhongField.getText();
//        String nameFood = nameFoodField.getText();
//        int amount = Integer.parseInt(amountField.getText());
//        boolean thanhToan = false; // Mặc định là chưa thanh toán
//
//        Food food = new Food(idOrder, name, idPhong, nameFood, amount, thanhToan);
//        addFoodToTable(food);
//
//        JOptionPane.showMessageDialog(this, "Đặt món thành công!");
//    } catch (NumberFormatException e) {
//        JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng số cho ID Order và Amount.");
//    }
//}
//
//// Phương thức sửa phiếu
//private void editFood() {
//    int selectedRow = table.getSelectedRow();
//    if (selectedRow >= 0) {
//        try {
//            int idOrder = Integer.parseInt(idOrderField.getText());
//            String name = nameField.getText();
//            String idPhong = idPhongField.getText();
//            String nameFood = nameFoodField.getText();
//            int amount = Integer.parseInt(amountField.getText());
//            boolean thanhToan = false; // Mặc định là chưa thanh toán
//
//            Food food = new Food(idOrder, name, idPhong, nameFood, amount, thanhToan);
//            foodList.set(selectedRow, food);
//
//            tableModel.setValueAt(food.getIdOrder(), selectedRow, 0);
//            tableModel.setValueAt(food.getName(), selectedRow, 1);
//            tableModel.setValueAt(food.getIdPhong(), selectedRow, 2);
//            tableModel.setValueAt(food.getNameFood(), selectedRow, 3);
//            tableModel.setValueAt(food.getAmount(), selectedRow, 4);
//
//            tableModel.setValueAt(food.isThanhToan(), selectedRow, 5);
//
//            JOptionPane.showMessageDialog(this, "Sửa phiếu thành công!");
//        } catch (NumberFormatException e) {
//            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng số cho ID Order và Amount.");
//        }
//    } else {
//        JOptionPane.showMessageDialog(this, "Vui lòng chọn một phiếu để sửa.");
//    }
//}
//
//// Phương thức xóa phiếu
//private void deleteFood() {
//    int selectedRow = table.getSelectedRow();
//    if (selectedRow >= 0) {
//        foodList.remove(selectedRow);
//        tableModel.removeRow(selectedRow);
//        JOptionPane.showMessageDialog(this, "Xóa phiếu thành công!");
//    } else {
//        JOptionPane.showMessageDialog(this, "Vui lòng chọn một phiếu để xóa.");
//    }
//}
//
//// Phương thức xuất danh sách món ăn ra file Excel
//private void exportToExcel() {
//    Workbook workbook = new XSSFWorkbook();
//    Sheet sheet = workbook.createSheet("Danh sách món ăn");
//
//    // Header row
//    Row headerRow = sheet.createRow(0);
//    String[] columns = {"ID_order", "Name", "ID_Phong", "Name_Food", "Amount", "Thanh_toan"};
//    for (int i = 0; i < columns.length; i++) {
//        Cell cell = headerRow.createCell(i);
//        cell.setCellValue(columns[i]);
//    }
//
//    // Data rows
//    for (int i = 0; i < foodList.size(); i++) {
//        Food food = foodList.get(i);
//        Row row = sheet.createRow(i + 1);
//        row.createCell(0).setCellValue(food.getIdOrder());
//        row.createCell(1).setCellValue(food.getName());
//        row.createCell(2).setCellValue(food.getIdPhong());
//        row.createCell(3).setCellValue(food.getNameFood());
//        row.createCell(4).setCellValue(food.getAmount());
//        row.createCell(5).setCellValue(food.isThanhToan());
//    }
//
//    // Write to file
//    try {
//        FileOutputStream fileOut = new FileOutputStream("danhsachmonan.xlsx");
//        workbook.write(fileOut);
//        fileOut.close();
//        workbook.close();
//        JOptionPane.showMessageDialog(this, "Xuất danh sách món ăn ra file Excel thành công!");
//    } catch (IOException e) {
//        JOptionPane.showMessageDialog(this, "Lỗi khi xuất file Excel: " + e.getMessage());
//    }
//}
//
//public static void main(String[] args) {
//    // Sử dụng ví dụ (đảm bảo truyền một kết nối thực trong trường hợp sử dụng thực tế)
//    Book_Food bookFood = new Book_Food(null);
//    bookFood.addFoodToTable(new Food(1, "John Doe", "101", "Pizza", 2, false));
//    bookFood.addFoodToTable(new Food(2, "Jane Doe", "102", "Burger", 1, true));
//}
//}
