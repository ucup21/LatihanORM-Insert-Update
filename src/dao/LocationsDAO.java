/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Locations;
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

     /**
    * Fungsi update untuk tabel Locations
    * @param object Object berupa class Locations
    * @return flag berhasil/gagal diupdate
    */
    @Override
    public boolean update(Object object) {
        boolean flag = false;
        try {
            session = factory.openSession();
            transaksi = session.beginTransaction();
            Locations locations = (Locations) object;
            Locations loc = (Locations) session.get(Locations.class, locations.getLocationId());
//            loc.setStreetAddress(locations.getStreetAddress());
//            loc.setPostalCode(locations.getPostalCode());
            loc.setCity(locations.getCity());
//            loc.setStateProvince(locations.getStateProvince());
//            loc.setCountryId(locations.getCountryId());
            session.update(loc);
            transaksi.commit();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaksi!=null) {
                transaksi.rollback();
            }
        } finally {
            session.close();
        }
        return flag;
    }

    @Override
    public boolean delete(Object object) {
       boolean flag = false;
        try {
            session = factory.openSession();
            transaksi = session.beginTransaction();
            Locations loc = (Locations) session.get(Locations.class, flag);
            session.delete(loc);
            transaksi.commit();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            if(transaksi!=null)transaksi.rollback();
        }finally{
            session.close();
        }
        return flag;
//To change body of generated methods, choose Tools | Templates.
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
    Object loc = new Object();
        try {
            session = factory.openSession();
            transaksi = session.beginTransaction();
            loc = session
                    .createQuery("FROM Locations"
                            + " WHERE locationid=:id")
//                    .setInteger("id",Integer.parseInt(id))
                    .setParameter("id",id)
                    .uniqueResult();
            transaksi.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if(transaksi!=null)transaksi.rollback();
        } finally {
            session.close();
        }
        return loc;}
    
}
