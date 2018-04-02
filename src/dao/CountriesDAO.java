/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Countries;
import entities.Employees;
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
}
