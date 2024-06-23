package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import Model.Phong;

public class KhachSan {
    private ArrayList<Phong> danhSachPhong;
    private Connection connection;

    public KhachSan(Connection connection) {
        this.danhSachPhong = new ArrayList<>();
        this.connection = connection;
    }

    public void themPhong(Phong phong) {
        danhSachPhong.add(phong);
        
        // Thêm phòng vào cơ sở dữ liệu
        try {
            String query = "INSERT INTO phong (so_phong, loai_phong, gia, tinh_trang) VALUES (?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, phong.getSoPhong());
                preparedStatement.setString(2, phong.getLoaiPhong());
                preparedStatement.setDouble(3, phong.getGia());
                preparedStatement.setString(4, phong.getTinhTrang());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Phong> layDanhSachPhong() {
        return danhSachPhong;
    }
    
    public ArrayList<Phong> layPhongTrong() {
        ArrayList<Phong> phongTrong = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM phong");

            while (resultSet.next()) {
                int soPhong = resultSet.getInt("so_phong");
                String loaiPhong = resultSet.getString("loai_phong");
                double gia = resultSet.getDouble("gia");
                String tinhTrang = resultSet.getString("tinh_trang");

                Phong phong = new Phong(soPhong, loaiPhong, gia, tinhTrang);
                phongTrong.add(phong);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phongTrong;
    }

    public ArrayList<Phong> layDanhSachDatPhong() {
        ArrayList<Phong> danhSachDatPhong =new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM thong_tin_dat_phong JOIN phong ON thong_tin_dat_phong.so_phong = phong.so_phong");

            while (resultSet.next()) {
                // Thông tin từ bảng thong_tin_dat_phong
                String tenKhach = resultSet.getString("ten_khach");
                String diaChiKhach = resultSet.getString("dia_chi_khach");
                String soDienThoaiKhach = resultSet.getString("so_dien_thoai_khach");
                int soNguoi = resultSet.getInt("so_nguoi");
                Date ngayDat = resultSet.getDate("ngay_dat");
                Date ngayTra = resultSet.getDate("ngay_tra");

                // Thông tin từ bảng phong
                int soPhong = resultSet.getInt("phong.so_phong");
                String loaiPhong = resultSet.getString("loai_phong");
                double gia = resultSet.getDouble("gia");
                String tinhTrang = resultSet.getString("tinh_trang");

                // Tạo đối tượng Phong với thông tin từ cả hai bảng
                Phong phong = new Phong(soPhong, loaiPhong, gia, tinhTrang);   // Bỏ tình trạng nếu muốn trở về cũ //
                phong.datPhong(tenKhach, diaChiKhach, soDienThoaiKhach, soNguoi, ngayDat, ngayTra);

                danhSachDatPhong.add(phong);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return danhSachDatPhong;
    }

    private void themThongTinDatPhong(String tenKhach, String diaChiKhach, String soDienThoaiKhach, int soNguoi, Date ngayDat, Date ngayTra, Phong phong) {
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
        }
    }
}
