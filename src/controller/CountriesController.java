/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CountriesDAO;
import dao.RegionsDAO;
import entities.Countries;
import entities.Regions;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.plaf.synth.Region;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hp
 */
public class CountriesController {

    private final CountriesDAO cDAO;
    private final RegionsDAO rDAO;

    public CountriesController() {
        this.cDAO = new CountriesDAO();
        this.rDAO = new RegionsDAO();
    }
    
    

    public boolean save(String countryId, String countryName, String regionId, boolean isSave) {
        Countries co = new Countries(countryId, countryName);
        String[] rId = regionId.split(" ");
        co.setRegionId((Regions) rDAO.getById(rId[0]));
        if (isSave) {
            return cDAO.insert(co);
        }
        return cDAO.update(co);
    }

    public boolean delete(String countryId) {
        return cDAO.delete(countryId);
    }

//    public boolean update(String countryId, String countryName, Long regionId) {
//        Countries co = new Countries(countryId, countryName, new Regions(BigDecimal.valueOf(regionId)));
//        return cDAO.update(co);
//    }
    public void bindingAll(JTable table, String[] header) {
        bindingTable(table, header, cDAO.getAll());
    }

    public void bindingSearch(JTable table, String[] header, String category, String cari) {
//        bindingTable(table, header, cDAO.search(category, cari)); {
        String search = cari;
            if (category.equalsIgnoreCase("countryId")) {
                Countries countries = (Countries) cDAO.search("countryName", cari).get(0);
                search = countries.getCountryId().toString();
            } else if (category.equalsIgnoreCase("regionId")) {
                Regions r = (Regions) rDAO.search("regionName", cari).get(0);                
                search = r.getRegionId().toString();
            }
            bindingTable(table, header, cDAO.search(category, search));
        }

    

    public void bindingTable(JTable table, String[] header, List<Object> datas) {
        DefaultTableModel model = new DefaultTableModel(header, 0);
        int i = 1;
        for (Object data : datas) {
            Countries c = (Countries) data;
            Object[] data1 = {
                i++,
                c.getCountryId(),
                c.getCountryName(),
                c.getRegionId().getRegionName()
            };
            model.addRow(data1);
        }
        table.setModel(model);
    }
    
    public void loadRegion(JComboBox jComboBox) {
        rDAO.getAll().stream().map((object) -> (Regions) object).forEachOrdered((regions) -> {
            jComboBox.addItem(regions.getRegionId()+" - "
                    +regions.getRegionName());
        });
    }
}
