/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Departments;
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
public class DepartmentsDAO implements InterfaceDAO{
    public SessionFactory factory;
    public Session session;
    public Transaction transaksi;
    public FunctionsDAO fdao;
    
    public DepartmentsDAO(){
        this.factory = HibernateUtil.getSessionFactory();
    }

    @Override
    public boolean insert(Object object) {
        Departments departments = (Departments) object;
      return fdao.insert(departments);
    }

    @Override
    public boolean update(Object object) {
        boolean flag = false;
        try {
            session = factory.openSession();
            transaksi = session.beginTransaction();
            Departments departments = (Departments) object;
            Departments dep = (Departments) session.get(Departments.class, departments);
            dep.setDepartmentId(departments.getDepartmentId());
            dep.setDepartmentName(departments.getDepartmentName());
            session.update(dep);
            transaksi.commit();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            if(transaksi!=null)transaksi.rollback();
        }finally{
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
            Departments dept = (Departments) session
                    .get(Departments.class, 
                            object.toString());
            session.delete(dept);
            transaksi.commit();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            if(transaksi!=null)transaksi.rollback();
        } finally {
            session.close();
        }
        return flag;}

    @Override
    public List<Object> getAll() {
        List<Object> data = new ArrayList<>();
        try {
            session = factory.openSession();
            transaksi = session.beginTransaction();
            data = session.createQuery("FROM Departments").list(); //list dibungkus oleh session dimasukan ke data
            transaksi.commit(); //commit untuk menyimpan data ke database
        } catch (Exception e) {
            if (transaksi != null) 
                transaksi.rollback();
            
        } finally {
            session.close();
        }
        return data;
    }

    @Override
    public List<Object> search(String category, String search) {
    List<Object> datasearch = new ArrayList<>();
        try {
            session = factory.openSession();
            transaksi = session.beginTransaction();
            datasearch = session
                    .createQuery("FROM Departments WHERE " +category+" LIKE '%"+search+"%'")
                    .list();
        transaksi.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaksi!=null) transaksi.rollback();
        } finally {
            session.close();
        }
        return datasearch;
    }

    /**
     * menampilkan data berdasarkan id di departments
     * @param id
     * @return 
     */
    @Override
    public Object getById(String id) {
       Object obj = new Object();
        try {
            session = factory.openSession();
            transaksi = session.beginTransaction();
              obj = session.createQuery("From Departments where department_id = "+id+"").uniqueResult();
            transaksi.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if(transaksi!=null)transaksi.rollback();
        }finally{
            session.close();
        }
        return obj;
    }
}
