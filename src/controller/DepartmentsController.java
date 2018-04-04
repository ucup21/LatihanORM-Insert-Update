/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DepartmentsDAO;
import dao.FunctionsDAO;
import entities.Departments;
import entities.Employees;
import entities.Locations;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Toshiba
 */
public class DepartmentsController {

    public DepartmentsDAO ddao;

    public DepartmentsController() {
        this.ddao = new DepartmentsDAO();
    }

    public void bindingALL(JTable table, String[] header) {
        bindingTables(table, header, ddao.getAll());
    }

    public void bindingSearch(JTable table, String[] header, String category, String cari) {
        bindingTables(table, header, ddao.search(category, cari));
    }

    private void bindingTables(JTable table, String[] header, List<Object> datas) {
        DefaultTableModel model = new DefaultTableModel(header, 0);
        for (Object data : datas) {
            Departments d = (Departments) data;
            Object[] data1 = {
                d.getDepartmentId(),
                d.getDepartmentName(),
                d.getManagerId(),
                d.getLocationId().getCity()
            };
            model.addRow(data1);
        }
        table.setModel(model);

//           Object[][] data = new Object[datas.size()][4];
//           for (int i = 0; i < datas.size(); i++) {
//               data[i][0] = datas.get(i).getD
//            Object[] objects = data[i];
    }

    public boolean insert(short depId, String depName, int manId, short locId) {
        Departments d = new Departments();
        d.setDepartmentId(depId);
        d.setDepartmentName(depName);
        d.setManagerId(new Employees(manId));
        d.setLocationId(new Locations(locId));
        return ddao.insert(d);
    }
    
    public boolean update(short depId, String depName, int manId, short locId) {
        Departments d = new Departments();
        d.setDepartmentId(depId);
        d.setDepartmentName(depName);
        d.setManagerId(new Employees(manId));
        d.setLocationId(new Locations(locId));
        return ddao.update(d);
    }
    
    public boolean delete(short id){
        return ddao.delete(id);
    }
}


