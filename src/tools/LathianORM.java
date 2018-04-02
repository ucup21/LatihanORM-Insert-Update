package tools;

import dao.CountriesDAO;
import dao.EmployeesDAO;
import dao.JobsDAO;
import dao.DepartmentsDAO;
import dao.LocationsDAO;
import dao.RegionsDAO;
import entities.Countries;
import entities.Locations;
import entities.Departments;
import entities.Employees;
import entities.Jobs;
import entities.Regions;
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
//        for (Object object : new JobsDAO().getAll()) {
//            Jobs jobs = (Jobs) object;
//            System.out.println(jobs.getJobId()+" "+jobs.getJobTitle()+" "
//                    +jobs.getMaxSalary()+" "+jobs.getMinSalary()+" ");
//        }
//        
//        Countries countries = new Countries("NS", "Nusantara");
//        System.out.println(new CountriesDAO().insert(countries));
        Departments dep = (Departments) new DepartmentsDAO().getById("15");
        System.out.println(dep.getDepartmentName());

        List<Object> datas = new LocationsDAO().getAll();
        datas = new LocationsDAO().search("CITY", "Roma");
        for (Object data : datas) {
            Locations loc = (Locations) data;
            System.out.println(" Alamat Jalan: " + loc.getStreetAddress() + "| Kode Pos: " + loc.getPostalCode());
        }

        //getById() berdasarkan employee Id
        Jobs jb = (Jobs) new JobsDAO().getById("PR_REP");;
        System.out.println(jb.getJobTitle() + " " + jb.getMaxSalary());

        /**
         * update jobs
         */
        jb.setJobId(new Jobs("AD_PRES") + "");
        jb.setJobTitle("Pembantu Presiden");
        System.out.println(new JobsDAO().update(jb));
        /*
        Insert Countries
         */
        Countries countries = new Countries("NS", "Nusantara");
        System.out.println(new CountriesDAO().insert(countries));
        /**
         * Delete Countries
         */
        countries = new Countries("NS");
        System.out.println(new CountriesDAO().delete("NS"));

        /**
         * delete from departmens
         */
        System.out.println(new DepartmentsDAO().delete(10));

        /**
         * get city by id from locations
         */
        Locations loc = (Locations) new LocationsDAO().getById("1000");
        System.out.println(loc.getCity());

        Regions reg = (Regions) new RegionsDAO().getById("1");
        System.out.println(new RegionsDAO().delete(reg));

        /*Insert Jobs
         */
        System.out.println("");
        System.out.println("Menambahkan data Table Jobs");
        Jobs jbs = new Jobs("AD_PRE", "President");
        System.out.println(new JobsDAO().insert(jbs));

        /*GetAll Regions
         */
        System.out.println("Menampilkan semua data Region");
        List<Object> data = new RegionsDAO().getAll();
        for (Object object : data) {
            Regions regs = (Regions) object;
            System.out.println(regs.getRegionName());
        }
        
        /**
         * menampilan semua nama depatments
         */

        System.out.println("");
        System.out.println("Menampilkan Semua Nama Departments");
        List<Object> obj = new DepartmentsDAO().getAll();

        for (Object depart : obj) {
            Departments dept = (Departments) depart; //casting
            System.out.println(dept.getDepartmentName());
        }
        
        
        /////////////////////////////////////////////////////////////////////////////////////////////
       
       // COUNTRIES
       // Memanggil fungsi search Countries
        List<Object> search = new CountriesDAO().search("regionId", "1");
        for (Object datasearch : search) { // Untuk setiap objek di dalam list data
            Countries coun = (Countries) datasearch;
            System.out.println(coun.getCountryId() +" "+ coun.getCountryName());
        }
        
        
        ////////////////////////////////////////////////////////////////////////////////////////////
        
        //LOCATIONS
        //Memanggil fungsi update Locations
        Locations locu = (Locations) new LocationsDAO().getById("1100");
        locu.setCity("Venice");
        System.out.println(new LocationsDAO().update(locu));

        /**
         * delete from jobs
         */
        System.out.println(new JobsDAO().delete(1));
        
         /**
         * searchById from Regions
         */
        Regions reg1 = (Regions) new RegionsDAO().getById("2");
         System.out.println(reg1.getRegionName());
         
        //GET ALL COUNTRIES
        System.out.println("--- Menampilkan data pada Countries ---");
        List<Object> datas1 = new CountriesDAO().getAll();
        for (Object data1 : datas1) {
            Countries cou = (Countries) data1;
            System.out.println("Country ID : "+cou.getCountryId()+ ", Country Name : " +cou.getCountryName());
        }
        
        //INSERT LOCATION
        System.out.println("--- Insert data pada tabel Locations ---");
        loc = new Locations((short)2100, "2121 Bwi");
       // loc.setcountryId(new Countries("IT"));
        System.out.println(new LocationsDAO().insert(loc));
        
        //SEARCH JOBS
        System.out.println("Mencari Data Job");
        List<Object> data1 = new JobsDAO().getAll();
        data = new JobsDAO().search("jobId", "AD_PRES");
        for (Object object : data1) {
            Jobs j = (Jobs) object;
            System.out.println("Pegawai dengan Job ID tersebut mempunyai Job Title : "+j.getJobTitle() + " dan Max Salary sebesar : " + j.getMaxSalary());
        }
        System.out.println("");
    
        
        //UPDATE COUNTRIES
         Countries co = (Countries) new CountriesDAO().getById("SG");
        System.out.println("Country dengan ID tersebut adalah " + co.getCountryName());
        co.setCountryName("Singapura");
        System.out.println(new CountriesDAO().update(co));

        /**
         * search region
         */
        System.out.println("search region id");
        datas =  new RegionsDAO().search("regionid", "2");
        for (Object rr : datas) {
            Regions x = (Regions) rr;
            System.out.println(x.getRegionId()+ " " + x.getRegionName());
    }
        
        //Search() berdasarkan departmentName = IT
        List<Object> datasearch = new DepartmentsDAO().search("departmentName", "IT");
        for (Object object : datasearch) {
            Departments depart = (Departments) obj;
            System.out.println(depart.getManagerId() + " " + depart.getLocationId());
        }
        
        //Update () RegionName menjadi Southeast Asia pada tabel tabel Regions yang memiliki Id = 4
        Regions regs = (Regions) new RegionsDAO().getById("4");
        regs.setRegionName("Southeast Asia");
        System.out.println(new RegionsDAO().update(regs));
        /*
        Insert Department
        */
        Departments departments =  new Departments(Short.parseShort("105"), "Pemasaran");
        System.out.println(new DepartmentsDAO().insert(departments));
}
}
