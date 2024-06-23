package DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Hibernate.HibernateUtil;

public class DoanhThuDAO {

    public void saveOrUpdate(DoanhThu doanhThu) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(doanhThu);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    public List<DoanhThu> getAllDoanhThu() {
        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery("FROM DoanhThu", DoanhThu.class).getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void deleteDoanhThu(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            DoanhThu doanhThu = session.get(DoanhThu.class, id);
            if (doanhThu != null) {
                session.delete(doanhThu);
            }
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }
}
