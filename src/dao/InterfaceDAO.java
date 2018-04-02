/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author Ignatius
 */
public interface InterfaceDAO {
    public boolean insert(Object object);
    public boolean update(Object object);
    public boolean delete(Object object);
    public List<Object> getAll();
    public List<Object> search(String category, String search);
    public Object getById(String id);
}
