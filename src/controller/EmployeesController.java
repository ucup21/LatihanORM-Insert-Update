/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.EmployeesDAO;
import dao.JobsDAO;
import entities.Departments;
import entities.Employees;
import entities.Jobs;
import entities.Locations;
import java.math.BigDecimal;
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
            String HIRE_DATE, String jobId, Long salary, Float commissionPct, String managerId, String departmentId) {

        Employees d = new Employees();
        d.setEmployeeId(employeeId);
        d.setFirstName(firstName);
        d.setLastName(lastName);
        d.setEmail(email);
        d.setPhoneNumber(phoneNumber);
        d.setHireDate(new java.sql.Date(new Long(HIRE_DATE)));
        d.setJobId(new Jobs(jobId));
        d.setSalary(new BigDecimal(salary));
        d.setCommissionPct(BigDecimal.valueOf(commissionPct));
        d.setManagerId(new Employees(employeeId));
        d.setDepartmentId(new Departments(Short.parseShort(departmentId)));
        
        
        return edao.insert(d);
//        Employees employees = new Employees(employeeId, lastName, email,
//                new java.sql.Date(new Long(HIRE_DATE)), (Jobs) new JobsDAO().getById(jobId));
//        return edao.insert(employees);
    }

    public boolean update(Integer employeeId, String firstName, String lastName, String email, String phoneNumber,
            String HIRE_DATE, String jobId, Long salary, Float commissionPct, String managerId, String departmentId) {

        Employees d = new Employees();
        d.setEmployeeId(employeeId);
        d.setFirstName(firstName);
        d.setLastName(lastName);
        d.setEmail(email);
        d.setPhoneNumber(phoneNumber);
        d.setHireDate(new java.sql.Date(new Long(HIRE_DATE)));
        d.setJobId(new Jobs(jobId));
        d.setSalary(new BigDecimal(salary));
        d.setCommissionPct(BigDecimal.valueOf(commissionPct));
        d.setManagerId(new Employees(employeeId));
        d.setDepartmentId(new Departments(Short.parseShort(departmentId)));
        
        
        return edao.update(d);
//        Employees employees = new Employees(employeeId, lastName, email,
//                new java.sql.Date(new Long(HIRE_DATE)), (Jobs) new JobsDAO().getById(jobId));
//        return edao.update(employees);
    }

}
