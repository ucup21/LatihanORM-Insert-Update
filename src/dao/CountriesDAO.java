/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Countries;
import entities.Jobs;
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
        return fdao.insert(object);
    }

    /**
     * Fungsi Untuk Delete di table Employees
     *
     * @param object Object berupa class employees yang di Delete
     * @return flag
     */
    public boolean delete(Object object) {
        return fdao.delete(Countries.class,object.toString());
    }

    /**
     * Fungsi search untuk mencari data pada tabel Countries
     *
     * @param String category berupa kategori yang ingin dicari, search berupa
     * nilai yang ingin dicari
     * @return list data di dalam tabel Countries berdasarkan parameter yang
     * dicari
     */
    public List<Object> search(String category, String search) {
        return fdao.getAll("FROM Countries WHERE " + category + " LIKE '%" + search + "%'");
    }

    public Object getById(String Id) {
        return fdao.getById("from Countries where countryId='" + Id + "'");
    }

    /**
     * fungsi insert untuk mengedit data pada tabel Countries
     *
     * @param object Object Berupa Kelas Countries
     * @return mengembalikan nilai true jika berhasil mengupdate data
     */
    public boolean update(Object object) {
        return fdao.insert(object);
    }

    public List<Object> getAll() {
        return fdao.getAll("FROM Countries");
    }
}
