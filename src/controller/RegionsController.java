/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.InterfaceDAO;
import dao.RegionsDAO;
import entities.Regions;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Fitriany Chairunnisa
 */
public class RegionsController {

    public RegionsDAO regDAO = new RegionsDAO();

    public RegionsController() {
        this.regDAO = new RegionsDAO();

    }

    public void bindingAll(JTable table, String[] header) {
        bindingTable(table, header, regDAO.getAll());
    }
    

    
    public boolean insert(BigDecimal region_id, String region_name)
    {
        Regions r = new Regions(region_id, region_name);
        return regDAO.insert(r);
    }
    
//    public boolean update(BigDecimal region_id, String region_name) {
//        Regions rr = new Regions(region_id, region_name);
//        return regDAO.update(rr);
//    }
    
    public boolean update(Long region_id, String region_name)
    {
        Regions gradee = new Regions(BigDecimal.valueOf(region_id), region_name);
        return regDAO.update(gradee);
    }
    
    public boolean delete(String region_id)
    {
        Regions reg2 = new Regions(new BigDecimal(region_id+""));
        return regDAO.delete(reg2.getRegionId());
    }

    public void bindingTable(JTable table, String[] header, List<Object> datas) {
        DefaultTableModel model = new DefaultTableModel(header, 0);
        Regions reg;
        for (Object data : datas) {
            reg = (Regions) data;
            Object[] data1 = {
                reg.getRegionId(),
                reg.getRegionName()

            };
            model.addRow(data1);
        }
        table.setModel(model);

    }
    
     public void bindingSearch(JTable table, String[]header, String category, String search){
        bindingTable(table, header, regDAO.search(category, search));
    }
}
