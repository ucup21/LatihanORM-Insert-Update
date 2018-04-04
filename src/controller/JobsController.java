/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import dao.JobsDAO;
import entities.Jobs;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dbayu
 */
public class JobsController {
    
    public JobsDAO jdao = new JobsDAO();
    
    private void bindingTabels(JTable table, String[] header, List<Object> datas){
    DefaultTableModel model = new  DefaultTableModel(header, 0);
        
        for (Object data : datas) {
            Jobs jobs = (Jobs) data;
            Object[] data1 = {
            jobs.getJobId(),
                jobs.getJobTitle(),
                jobs.getMinSalary(),
                jobs.getMaxSalary()
            };
            model.addRow(data1);
        }
        table.setModel(model);
    }

     public void bindingall(JTable table, String[] header){
        bindingTabels(table, header, jdao.getAll());
    }
     
     public boolean insert(String job_id, String job_title, int minsal, int maxsal){
        Jobs jobs = new Jobs();
        jobs.setJobId(job_id);
        jobs.setJobTitle(job_title);
        jobs.setMinSalary(minsal);
        jobs.setMaxSalary(maxsal);
        
        return jdao.insert(jobs);
    }
     
     public boolean delete(String id) {
        return jdao.delete(id);
    }
     
     public boolean update(String job_id, String job_title, int minsal, int maxsal){
        Jobs jobs = new Jobs();
        jobs.setJobId(job_id);
        jobs.setJobTitle(job_title);
        jobs.setMinSalary(minsal);
        jobs.setMaxSalary(maxsal);
                
        return jdao.update(jobs);
    
}
     public  void bindingsearch(JTable table, String[] header,
            String category, String search){
        bindingTabels(table, header, jdao.search(category, search));
        
    }
}
