/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Countries;
import tools.HibernateUtil;

/**
 *
 * @author Ignatius
 */
public class CountriesDAO {
    public FunctionsDAO fdao;

    public CountriesDAO() {
        this.fdao = new FunctionsDAO(HibernateUtil.getSessionFactory());
    }
    
    public boolean insert(Object object){
        Countries countries = (Countries) object;
        return fdao.insert(countries);
    }
}
