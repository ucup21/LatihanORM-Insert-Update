/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Regions;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import tools.HibernateUtil;

/**
 *
 * @author Asus
 */
public class RegionsDAO implements InterfaceDAO {

    public SessionFactory factory;
    private Session session;
    private Transaction transaction;
    public FunctionsDAO fdao;

    public RegionsDAO() {
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

    /**
     * fungsi delete pada regions
     *
     * @param object
     * @return
     */
    @Override
    public boolean delete(Object object) {
        return false;
    }

    @Override
    public List<Object> getAll() {
        return fdao.getAll("FROM Regions");
    }

    /**
     * Fungsi untuk menampilkan data yang dicari berdasarkan kategori(nama
     * kolom) pada regions
     *
     * @param category
     * @param search
     * @return
     */
    @Override
    public List<Object> search(String category, String search) {
        return fdao.getAll("FROM Regions WHERE " + category + " LIKE '%" + search + "%'");
    }

    /**
     * Fungsi untuk menampilkan Region berdasarkan Id
     *
     * @param id
     * @return a
     */
    @Override
    public Object getById(String id) {
        return fdao.getById("from Regions where regionId ='" + id + "'");
    }

}
