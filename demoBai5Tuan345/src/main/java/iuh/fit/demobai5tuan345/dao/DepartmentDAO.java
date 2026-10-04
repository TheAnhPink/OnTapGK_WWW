package iuh.fit.demobai5tuan345.dao;

import iuh.fit.demobai5tuan345.entity.Department;
import iuh.fit.demobai5tuan345.util.DBUtil;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.checkerframework.checker.units.qual.A;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {
    private DBUtil dbUtil;

    public DepartmentDAO(DataSource dataSource) {
        this.dbUtil = new DBUtil(dataSource);
    }

    public List<Department> getDSPhongBan(){
        List<Department> dspb= new ArrayList<>();
        String sql="select *from departments";
        try {
            Connection conn= DBUtil.getConnection();
            Statement st= conn.createStatement();
            ResultSet rs= st.executeQuery(sql);
            while(rs.next()){
                int id= rs.getInt(1);
                String name= rs.getString(2);

                Department d= new Department(id,name);
                dspb.add(d);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dspb;
    }


    public Department getPhongBanTheoID(int idtim){
        String sql= "select* from departments where id= ?";
        Department d= null;
        try {
            Connection conn= DBUtil.getConnection();
            PreparedStatement st= conn.prepareStatement(sql);
            st.setInt(1,idtim);
            ResultSet rs= st.executeQuery();
            while(rs.next()){
                int id= rs.getInt(1);
                String name= rs.getString(2);

                d= new Department(id,name);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return d;
    }

    public boolean themPhongBan(Department newD){
        int n=0;
        String sql= "insert into departments (id, name) values (?,?)";

        try {
            Connection conn= DBUtil.getConnection();
            PreparedStatement st= conn.prepareStatement(sql);
            st.setInt(1,newD.getId());
            st.setString(2,newD.getName());
            n=st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return n>0;
    }

    public boolean capNhatPhongBan(int id, Department pbmoi){
        int n= 0;
        String sql= "update departments set name= ? where id=?";
        try {
            Connection conn= DBUtil.getConnection();
            PreparedStatement st= conn.prepareStatement(sql);
            st.setString(1,pbmoi.getName());
            st.setInt(2,id);
            n=st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return n>0;
    }
    public boolean xoaPhongBan(int id){
        int n= 0;
        String sql= "delete from departments where id=?";
        try {
            Connection conn= DBUtil.getConnection();
            PreparedStatement st= conn.prepareStatement(sql);
            st.setInt(1,id);
            n= st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return n>0;
    }

    public List<Department> timPBTheoTen(String ten){
        List<Department> list = new ArrayList<>();
        String sql= "select * from departments where name like ?";

        try {
            Connection conn= dbUtil.getConnection();
            PreparedStatement st= conn.prepareStatement(sql);
            st.setString(1,"%"+ten+"%");
            ResultSet rs= st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                list.add(new Department(id, name));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }



}
