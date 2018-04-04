/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.EmployeesDAO;
import dao.JobsDAO;
import entities.Employees;
import entities.Jobs;
import java.util.Date;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TAMU
 */
public class EmployeesController {

    public EmployeesController() {
        this.edao = new EmployeesDAO();
    }

    public EmployeesDAO edao;

    public void BindingAll(JTable table, String[] header) {
        BindingTabel(table, header, edao.getAll());
    }

    private void BindingTabel(JTable table, String[] header, List<Object> datas) {
        DefaultTableModel model = new DefaultTableModel(header, 0);
        for (Object data : datas) {
            Employees emp = (Employees) data;
            Object[] data1 = {
                emp.getEmployeeId(),
                emp.getFirstName(),
                emp.getLastName(),
                emp.getEmail(),
                emp.getPhoneNumber(),
                emp.getHireDate(),
                emp.getJobId(),
                emp.getSalary(),
                emp.getCommissionPct(),
                emp.getManagerId(),
                emp.getDepartmentId()
            };
            model.addRow(data1);
        }
        table.setModel(model);
    }

    public boolean insert(Integer employeeId, String firstName, String lastName, String email, String phoneNumber,
            String HIRE_DATE, String jobId, String salary, String commissionPct, String managerId, String departmentId) {

        Employees employees = new Employees(employeeId, lastName, email,
                new java.sql.Date(new Long(HIRE_DATE)), (Jobs) new JobsDAO().getById(jobId));
        return edao.insert(employees);
    }

    public boolean update(Integer employeeId, String lastName, String email, Date hireDate) {
        Employees emp = new Employees(employeeId, lastName, email, hireDate);
        return edao.update(emp);
    }

}
