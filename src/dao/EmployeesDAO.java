/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Employees;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import tools.HibernateUtil;

/**
 *
 * @author Ignatius
 */
public class EmployeesDAO implements InterfaceDAO {

    public SessionFactory factory;
    private Session session;
    private Transaction transaction;
    public FunctionsDAO fdao;

    public EmployeesDAO() {
        this.factory = HibernateUtil
                .getSessionFactory();
    }

    /**
     *
     * @param object Object berupa class Employees
     * @return
     */
    @Override
    public boolean insert(Object object) {
        boolean flag = false;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            Employees emp = (Employees) object;
//            session.persist(emp);
            session.save(emp);
            transaction.commit();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return flag;
    }

    @Override
    public boolean update(Object object) {
        boolean flag = false;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            Employees employees = (Employees) object;
            Employees emp = (Employees) session
                    .get(Employees.class,
                            employees.getEmployeeId());
            emp.setFirstName(employees.getFirstName());
//            emp.setPhoneNumber(employees.getPhoneNumber());
//            emp.setSalary(employees.getSalary());
//            emp.setManagerId(employees.getManagerId());
//            emp.setDepartmentId(employees.getDepartmentId());
            session.update(emp);
            transaction.commit();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return flag;
    }

    @Override
    public boolean delete(Object object) {
        boolean flag = false;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            Employees emp = (Employees) session
                    .get(Employees.class,
                            Integer.parseInt(object + ""));
            session.delete(emp);
            transaction.commit();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return flag;
    }

    @Override
    public List<Object> getAll() {
        List<Object> data = new ArrayList<>();
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            data = session
                    .createQuery("FROM Employees")
                    .list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return data;
    }

    @Override
    public List<Object> search(String category, String search) {
        List<Object> data = new ArrayList<>();
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            data = session
                    .createQuery("FROM Employees"
                            + " WHERE " + category
                            + " LIKE '%" + search + "%'")
                    .list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return data;
    }

    @Override
    public Object getById(String id) {
        return fdao.getById("from Employees where employeeId='" + id + "'");
    }

}
