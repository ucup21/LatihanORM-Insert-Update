package tools;

import dao.CountriesDAO;
import dao.EmployeesDAO;
import dao.JobsDAO;
import entities.Countries;
import entities.Employees;
import entities.Jobs;
import java.util.Date;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ignatius
 */
public class LathianORM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        System.out.println();
//        List<Object> data = new EmployeesDAO()
//                .getAll();
//        data = new EmployeesDAO().search(
//                "lastName", "King");
//        for (Object object : data) {
//            Employees emp = (Employees) object;
//            System.out.println(emp.getFirstName()
//            +" "+emp.getLastName());
//        }
//        Employees emp = (Employees) 
//                new EmployeesDAO()
//                        .getById("100");
//        System.out.println(emp.getFirstName()
//        +" "+emp.getLastName()+" ");
//        +emp.getDepartmentId().getDepartmentName());
        
//        emp = new Employees
//        (207, "Santosa", "joekelir", new Date());
//        emp.setJobId(new Jobs("AD_PRES"));
//        System.out.println(new EmployeesDAO()
//                .insert(emp));
//        System.out.println(new EmployeesDAO()
//                .delete(207));
//        emp.setFirstName("Ignatius");
//        System.out.println(new EmployeesDAO()
//                .update(emp));
//        
//        System.out.println(Employees.class);

        for (Object object : new JobsDAO().getAll()) {
            Jobs jobs = (Jobs) object;
            System.out.println(jobs.getJobId()+" "+jobs.getJobTitle()+" "
                    +jobs.getMaxSalary()+" "+jobs.getMinSalary()+" ");
        }
        
        Countries countries = new Countries("NS", "Nusantara");
        System.out.println(new CountriesDAO().insert(countries));
    }
    
}
