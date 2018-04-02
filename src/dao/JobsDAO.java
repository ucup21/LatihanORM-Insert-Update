/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import tools.HibernateUtil;

/**
 *
 * @author Ignatius
 */
public class JobsDAO {
    public FunctionsDAO fdao;

    public JobsDAO() {
        this.fdao = new FunctionsDAO(HibernateUtil.getSessionFactory());
    }
    
    public List<Object> getAll(){
        String query = "FROM Jobs";
        return fdao.getAll(query);
    }
    
}
