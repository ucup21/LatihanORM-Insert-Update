/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Employees;
import entities.Regions;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import tools.HibernateUtil;

/**
 *
 * @author Asus
 */
public class RegionsDAO implements InterfaceDAO{
    public SessionFactory factory;
    private Session session;
    private Transaction transaction;
    public FunctionsDAO fdao;
    
    public RegionsDAO() {
        this.factory = HibernateUtil
                .getSessionFactory();
    }

    @Override
    public boolean insert(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Object object) {
    boolean flag = false;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            Regions regions = (Regions) object;
            Regions regs = (Regions) session.get(Regions.class, regions);
            regs.setRegionName(regions.getRegionName());
            session.update(regs);
            transaction.commit();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            if(transaction!=null)transaction.rollback();
        }finally{
            session.close();
        }
        return flag;

    }

    /**
     * fungsi delete pada regions
     * @param object
     * @return 
     */
    @Override
    public boolean delete(Object object) {
        boolean flag = false;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            Regions reg = (Regions) session
                    .get(Regions.class, 
                            Integer.parseInt(object+""));
            session.delete(reg);
            transaction.commit();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            if(transaction!=null)transaction.rollback();
        } finally {
            session.close();
        }
        return flag;
    }

    @Override
    public List<Object> getAll() {
        List<Object> data = new ArrayList<>();
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            data = session.createQuery("FROM Regions").list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return data;
    }


 /**
     * Fungsi untuk menampilkan data yang dicari berdasarkan kategori(nama kolom) pada regions
     * @param category
     * @param search
     * @return 
     */

    @Override
    public List<Object> search(String category, String search) {
        List<Object> data = new ArrayList<>();
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            data = session.createQuery("FROM Regions WHERE "+category+" = "+search).list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if(transaction!=null)transaction.rollback();
        } finally {
            session.close();
        }
        return data;
    }

    
//    @Override
//    public Object getById(String id) {
//        Object reg = new Object();
//        try {
//            session = factory.openSession();
//            transaction = session.beginTransaction();
//            reg = session
//                    .createQuery("FROM Regions"
//                            + " WHERE regionid="+id+"").uniqueResult();
//            transaction.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            if(transaction!=null)transaction.rollback();
//        } finally {
//            session.close();
//        }
//        return reg;
//    }
    
    /**
     * Fungsi untuk menampilkan Region berdasarkan Id
     * @param id
     * @return a
     */
    
    @Override
      public Object getById(String id) {
     return fdao.getById("from Regions where regionId='" + id + "'");
    }


    
}
