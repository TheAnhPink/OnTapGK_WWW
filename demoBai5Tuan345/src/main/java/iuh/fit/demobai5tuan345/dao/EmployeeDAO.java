package iuh.fit.demobai5tuan345.dao;

import iuh.fit.demobai5tuan345.entity.Department;
import iuh.fit.demobai5tuan345.entity.Employee;
import iuh.fit.demobai5tuan345.util.DBUtil;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private DBUtil dbUtil;

    public EmployeeDAO(DataSource dataSource) {
        this.dbUtil = new DBUtil(dataSource);
    }

    public List<Employee> getNhanVienTheoPhongBan(String pb){
        String sql= "select * from employees e join departments d on e.department_id=d.id where " +
                "d.name like ?";

        List<Employee> dsnv= new ArrayList<>();

        try {
            Connection conn= dbUtil.getConnection();
            PreparedStatement st= conn.prepareStatement(sql);
            st.setString(1,"%"+pb+"%");
            ResultSet rs= st.executeQuery();
            while(rs.next()){
                int id= rs.getInt(1);
                String name= rs.getString(2);
                Department d= new Department();
                d.setId(rs.getInt(3));
                double sa= rs.getDouble(4);
                Employee e= new Employee(id,name,d,sa);

                dsnv.add(e);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dsnv;
    }

    public List<Employee> getAllNhanVien(){
        String sql= "select * from employees";

        List<Employee> dsnv= new ArrayList<>();

        try {
            Connection conn= dbUtil.getConnection();
            Statement st= conn.createStatement();
            ResultSet rs= st.executeQuery(sql);
            while(rs.next()){
                int id= rs.getInt(1);
                String name= rs.getString(2);
                Department d= new Department();
                d.setId(rs.getInt(3));
                double sa= rs.getDouble(4);
                Employee e= new Employee(id,name,d,sa);

                dsnv.add(e);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dsnv;
    }

    public boolean themNhanVienMoiTheoPhongBan(Employee e){
        int n=0;
        String sql= "insert into employees  (name,department_id,salary) values (?,?,?)";
        try {
            Connection conn= dbUtil.getConnection();
            PreparedStatement st= conn.prepareStatement(sql);
            st.setString(1,e.getName());
            st.setInt(2,e.getDepartment().getId());
            st.setDouble(3,e.getSalary());
            n= st.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return n>0;
    }

    public boolean suaNhanVien(Employee e){
        int n=0;
        String sql= "update employees set name=?, department_id=?, salary=? where id=?";
        try {
            Connection conn= dbUtil.getConnection();
            PreparedStatement st= conn.prepareStatement(sql);
            st.setString(1,e.getName());
            st.setInt(2,e.getDepartment().getId());
            st.setDouble(3,e.getSalary());
            st.setInt(4,e.getId());
            n= st.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return n>0;
    }

    public boolean xoaNhanVien(int id){
        int n=0;
        String sql= "delete from employees where id=?";
        try {
            Connection conn= dbUtil.getConnection();
            PreparedStatement st= conn.prepareStatement(sql);
            st.setInt(1,id);
            n= st.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return n>0;
    }
}
