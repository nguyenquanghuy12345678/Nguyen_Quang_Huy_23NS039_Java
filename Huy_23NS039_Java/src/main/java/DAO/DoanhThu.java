package DAO;


import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "doanh_thu")
public class DoanhThu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ma_dat_phong")
    private Integer maDatPhong;

    @Column(name = "tong_tien")
    private BigDecimal tongTien;

    @Column(name = "created_at")
    private Timestamp createdAt;

    // Các getter và setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getMaDatPhong() {
        return maDatPhong;
    }

    public void setMaDatPhong(Integer maDatPhong) {
        this.maDatPhong = maDatPhong;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
