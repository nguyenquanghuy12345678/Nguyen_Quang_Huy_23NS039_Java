
package DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Hibernate.HibernateUtil;

public class PhieuNhapDAO {
    public void savePhieuNhap(PhieuNhap phieuNhap) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(phieuNhap);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<PhieuNhap> getPhieuNhaps() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from PhieuNhap", PhieuNhap.class).list();
        }
    }

    public PhieuNhap getPhieuNhap(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(PhieuNhap.class, id);
        }
    }

    public void updatePhieuNhap(PhieuNhap phieuNhap) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(phieuNhap);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deletePhieuNhap(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            PhieuNhap phieuNhap = session.get(PhieuNhap.class, id);
            if (phieuNhap != null) {
                session.delete(phieuNhap);
                System.out.println("Phiếu nhập is deleted");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}









//import org.hibernate.Session;
//import org.hibernate.query.Query;
//import java.util.List;
//
//public class PhieuNhapDAO {
//  public List<PhieuNhap> getAllPhieuNhap() {
//      try (Session session = HibernateUtil.getSession()) {
//          // Sử dụng HQL để truy vấn tất cả các phiếu nhập
//          Query<PhieuNhap> query = session.createQuery("FROM PhieuNhap", PhieuNhap.class);
//          return query.getResultList();
//      }
//  }
//  
//  public void saveOrUpdatePhieuNhap(PhieuNhap phieuNhap) {
//      try (Session session = HibernateUtil.getSession()) {
//          // Lưu hoặc cập nhật một phiếu nhập vào cơ sở dữ liệu
//          session.beginTransaction();
//          session.saveOrUpdate(phieuNhap);
//          session.getTransaction().commit();
//      }
//  }
//  
//  public void deletePhieuNhap(PhieuNhap phieuNhap) {
//      try (Session session = HibernateUtil.getSession()) {
//          // Xóa một phiếu nhập khỏi cơ sở dữ liệu
//          session.beginTransaction();
//          session.delete(phieuNhap);
//          session.getTransaction().commit();
//      }
//  }
//}
