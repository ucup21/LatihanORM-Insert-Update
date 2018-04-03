/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Countries;
import entities.Departments;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import tools.HibernateUtil;

/**
 *
 * @author TAMU
 */
public class DepartmentsDAO implements InterfaceDAO {

    public SessionFactory factory;
    public Session session;
    public Transaction transaksi;
    public FunctionsDAO fdao;

    public DepartmentsDAO() {
       this.fdao = new FunctionsDAO(HibernateUtil.getSessionFactory());
    }

    @Override
    public boolean insert(Object object) {
        return fdao.insert(object);
    }

    @Override
    public boolean update(Object object) {
        return fdao.insert(object);
    }

    @Override
    public boolean delete(Object object) {
        return fdao.delete(Departments.class,Integer.parseInt(object+""));
    }

    @Override
    public List<Object> getAll() {
        return fdao.getAll("FROM Departments");
    }

    @Override
    public List<Object> search(String category, String search) {
        return fdao.getAll("FROM Deparments WHERE " + category + " LIKE '%" + search + "%'");
    }

    /**
     * menampilkan data berdasarkan id di departments
     *
     * @param id
     * @return
     */
    @Override
    public Object getById(String id) {
        return fdao.getById("from Departments where departmentId='" + id + "'");
    }
}
