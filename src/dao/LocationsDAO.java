/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import tools.HibernateUtil;

/**
 *
 * @author TAMU
 */
public class LocationsDAO implements InterfaceDAO{

    public SessionFactory factory;
    public Session session;
    public Transaction transaksi;
    
    public LocationsDAO(){
        this.factory = HibernateUtil.getSessionFactory();
    }
    @Override
    public boolean insert(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Object> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Object> search(String category, String search) {
        List<Object> data = new ArrayList<>();
        try {
            session = factory.openSession();
            transaksi = session.beginTransaction();
            data = session.createQuery("From Departments where " + category + " like '% " + search + " %'").list();
            transaksi.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if(transaksi!=null)transaksi.rollback();
        }finally{
            session.close();
        }
        return data;
    }

    @Override
    public Object getById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
