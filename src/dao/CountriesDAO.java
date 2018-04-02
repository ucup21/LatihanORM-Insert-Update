/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Countries;
import entities.Employees;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import tools.HibernateUtil;

/**
 *
 * @author Ignatius
 */
public class CountriesDAO {

    public Session session;
    private SessionFactory factory;
    public Transaction transaction;
    
    public FunctionsDAO fdao;

    public CountriesDAO() {
        this.fdao = new FunctionsDAO(HibernateUtil.getSessionFactory());
    }

    public boolean insert(Object object) {
        Countries countries = (Countries) object;
        return fdao.insert(countries);
    }

    /**
     * Fungsi Untuk Delete di table Employees
     *
     * @param object Object berupa class employees yang di Delete
     * @return flag
     */
    public boolean delete(Object object) {
        boolean flag = false;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            Countries emp = (Countries) session.get(Countries.class, flag);
            session.delete(emp);
            transaction.commit();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return flag;
    }
    
     /**
    * Fungsi search untuk mencari data pada tabel Countries
    * @param String category berupa kategori yang ingin dicari, search berupa nilai yang ingin dicari
    * @return list data di dalam tabel Countries berdasarkan parameter yang dicari
    */
    public List<Object> search(String category, String search) {
        List<Object> data = new ArrayList<>();
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            data = session.createQuery("FROM Countries WHERE "+category+" LIKE '%"+search+"%'").list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction!=null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return data;
    }
    
    public Object getById(String Id) {
      Object obj = new Object();
       try {
            session = factory.openSession();
            transaction = session.beginTransaction();
          // obj = session.createQuery("from  Countries where  country_id='" +Id+ "'").uniqueResult();
             obj = session.createQuery("from Countries where  country_id=:id")
                     .setInteger("id", Integer.parseInt(Id)).uniqueResult();
            transaction.commit();  
        }catch (Exception e) {
            if (transaction!=null) transaction.rollback(); {
                
            }
        }finally {
            session.close();
        }
        return  obj; 
}
        public List<Object> getAll() {
        List<Object> data1 = new ArrayList<>();
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            data1 = session.createQuery("FROM Countries").list(); //list dari employee dibungkus session dan di masukkan dalam data
            transaction.commit(); //commit fungsinya untuk menyimpan data
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction!=null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return data1;
    }
}
