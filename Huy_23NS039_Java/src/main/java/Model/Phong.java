package Model ; 
import java.util.Date;

public class Phong {
    private int soPhong;
    private String loaiPhong;
    private double gia;
    private String tinhTrang;
    private boolean daDat ; 
    // Thuộc tính mới
    private String tenKhach;
    private String diaChiKhach;
    private String soDienThoaiKhach;
    private int soNguoi;
    private Date ngayDat;
    private Date ngayTra;

    // Constructor   // có thể là hàm khởi tạo giá trị ban đầu mỗi khi gọi đối tượng
    public Phong(int soPhong, String loaiPhong, double gia, String tinhTrang) { // bỏ tinh trang //
        this.soPhong = soPhong;
        this.loaiPhong = loaiPhong;
        this.gia = gia;
        this.tinhTrang = tinhTrang;
//      this.tinhTrang = "Trống" ;
        this.daDat = false ; 
    }

    // Getters và Setters (Thêm các thuộc tính mới)  lấy và đặt 

    public int getSoPhong() {
        return soPhong;
    }

    public String getLoaiPhong() {
        return loaiPhong;
    }

    public double getGia() {
        return gia;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public String getTenKhach() {
        return tenKhach;
    }

    public void setTenKhach(String tenKhach) {
        this.tenKhach = tenKhach;
    }

    public String getDiaChiKhach() {
        return diaChiKhach;
    }

    public void setDiaChiKhach(String diaChiKhach) {
        this.diaChiKhach = diaChiKhach;
    }

    public String getSoDienThoaiKhach() {
        return soDienThoaiKhach;
    }

    public void setSoDienThoaiKhach(String soDienThoaiKhach) {
        this.soDienThoaiKhach = soDienThoaiKhach;
    }

    public int getSoNguoi() {
        return soNguoi;
    }

    public void setSoNguoi(int soNguoi) {
        this.soNguoi = soNguoi;
    }

    public Date getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(Date ngayDat) {
        this.ngayDat = ngayDat;
    }

    public Date getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
    }

    // Phương thức tính tổng tiền
    public double tinhTongTien() {
        // Tính tổng tiền dựa trên giá phòng và số ngày ở
        // (Giả sử ngayTra và ngayDat đã được thiết lập)
        long khoangCach = ngayTra.getTime() - ngayDat.getTime();
        int soNgay = (int) (khoangCach / (24 * 60 * 60 * 1000));
        return gia * soNgay;
    }

    // Phương thức đặt phòng
    public void datPhong(String tenKhach, String diaChiKhach, String soDienThoaiKhach, int soNguoi, Date ngayDat, Date ngayTra) {
        this.tinhTrang = "Đã Đặt";
        this.tenKhach = tenKhach;
        this.diaChiKhach = diaChiKhach;
        this.soDienThoaiKhach = soDienThoaiKhach;
        this.soNguoi = soNguoi;
        this.ngayDat = ngayDat;
        this.ngayTra = ngayTra;
        this.daDat = true ; 
    }

    // Override toString để hiển thị thông tin phòng khi cần
    @Override
    public String toString() {
        return String.format("Phòn"
        		+ "g %d - %s - %.2f VND - Tình trạng: %s",
                soPhong, loaiPhong, gia, tinhTrang);
    }

	public boolean isDaDat() {
		// TODO Auto-generated method stub
		return daDat;
	}
}
