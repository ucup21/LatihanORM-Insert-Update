/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CountriesDAO;
import entities.Countries;
import entities.Regions;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.JTable;
import javax.swing.plaf.synth.Region;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hp
 */

public class CountriesController {
    CountriesDAO cDAO = new CountriesDAO();
    
     public boolean insert(String countryId, String countryName, Long regionId) {
        Countries co = new Countries(countryId, countryName, new Regions(BigDecimal.valueOf(regionId)));
        return cDAO.insert(co);
    }
     
     public boolean delete(String countryId) {
        return cDAO.delete(countryId);
    }
    
      public boolean update(String countryId, String countryName, Long regionId) {
        Countries co = new Countries(countryId, countryName, new Regions(BigDecimal.valueOf(regionId)));
        return cDAO.update(co);
    }
    public void bindingAll(JTable table, String[] header)
    {
        bindingTable(table, header, cDAO.getAll());
    }
    
    public void bindingSearch(JTable table, String[] header, String category, String cari){
        bindingTable(table, header, cDAO.search(category, cari));        
    }  
    
  

    public void bindingTable(JTable table, String[] header, List<Object> datas) {       
        DefaultTableModel model = new DefaultTableModel(header,0);
        for (Object data : datas) {
            Countries c = (Countries)data;
            Object[] data1 = {
                c.getCountryId(),
                c.getCountryName(),
                c.getRegionId()
            };
            model.addRow(data1);
        }
        table.setModel(model);
    }
}
