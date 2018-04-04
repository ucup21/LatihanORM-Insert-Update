/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.LocationsDAO;
import entities.Countries;
import entities.Locations;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Evi
 */
public class LocationsController {

    public LocationsDAO lcdao = new LocationsDAO();

    public LocationsController() {
        this.lcdao = new LocationsDAO();
    }

    public void bindingAll(JTable table, String[] header) {
        bindingTabels(table, header, lcdao.getAll());
    }

    public boolean insert(Short locationId, String streetAddress, String postalCode, String city, String stateProvince, String countryId) {
        Locations loca = new Locations(locationId, streetAddress, postalCode, city, stateProvince, new Countries(countryId));
        return lcdao.insert(loca);
    }

   public boolean update(Short locationId, String streetAddress, String postalCode, String city, String stateProvince, String countryId) {
        Locations loca = new Locations(locationId, streetAddress, postalCode, city, stateProvince, new Countries(countryId));
        return lcdao.update(loca);
    }
    public boolean delete(Short id) {
        return lcdao.delete(id);
    }
    
    public void bindingSearch(JTable table, String[] header, String category, String cari){
        bindingTabels(table, header, lcdao.search(category, cari));
    }

    private void bindingTabels(JTable tblLocation, String[] header, List<Object> datas) {
        //  List<Locations> datas = new LocationsDAO().getAll();
        // Object[][] data = new Object[datas.size()][3];
        Locations l;
        DefaultTableModel model = new DefaultTableModel(header, 0);
        for (Object data : datas) {
            l = (Locations) data;
            Object[] data1 = {
                l.getLocationId(),
                l.getStreetAddress(),
                l.getPostalCode(),
                l.getCity(),
                l.getStateProvince(),
                l.getCountryId()
            };
            model.addRow(data1);
        }
        tblLocation.setModel(model);
    }
}

