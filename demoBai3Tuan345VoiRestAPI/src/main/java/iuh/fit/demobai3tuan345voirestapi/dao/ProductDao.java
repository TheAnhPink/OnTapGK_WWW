package iuh.fit.demobai3tuan345voirestapi.dao;

import iuh.fit.demobai3tuan345voirestapi.entity.Product;
import iuh.fit.demobai3tuan345voirestapi.util.DBUtil;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    private DBUtil dbUtil;

    public ProductDao(DataSource dataSource) {
        this.dbUtil = new DBUtil(dataSource);
    }

    public List<Product> getAllProduct(){
        String sql= "Select * from products";
        List<Product> dsP= new ArrayList<>();

        try {
            Connection conn= dbUtil.getConnect();
            Statement st= conn.createStatement();
            ResultSet rs= st.executeQuery(sql);

            while(rs.next()){
                int id= rs.getInt(1);
                String mod= rs.getString(2);
                String des= rs.getString(3);
                int quan= rs.getInt(4);
                double price= rs.getDouble(5);
                String imgurl= rs.getString(6);

                Product p= new Product(id,mod,des,quan,price,imgurl);
                dsP.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dsP;
    }

    public Product getProductById(int idt){
        Product p= null;
        String sql="select * from products where id=?";
        try {
            Connection conn= dbUtil.getConnect();
            PreparedStatement st= conn.prepareStatement(sql);
            st.setInt(1,idt);
            ResultSet rs= st.executeQuery();
            while(rs.next()){
                int id= rs.getInt(1);
                String mod= rs.getString(2);
                String des= rs.getString(3);
                int quan= rs.getInt(4);
                double price= rs.getDouble(5);
                String imgurl= rs.getString(6);

                p= new Product(id,mod,des,quan,price,imgurl);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return p;
    }

    public boolean themSanPham(Product pm){
        int n=0;
        String sql= "insert into products (model,description,quantity,price,imgurl) values (?,?,?,?,?)";
        try {
            Connection conn= dbUtil.getConnect();
            PreparedStatement st= conn.prepareStatement(sql);
            st.setString(1,pm.getModel());
            st.setString(2, pm.getDescription());
            st.setInt(3,pm.getQuantity());
            st.setDouble(4,pm.getPrice());
            st.setString(5,pm.getImgurl());
            n=st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return n>0;
    }
    public boolean xoaSanPham(int id){
        int n=0;
        String sql= "delete from products where id=?";
        try {
            Connection conn= dbUtil.getConnect();
            PreparedStatement st= conn.prepareStatement(sql);
            st.setInt(1,id);
            n=st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return n>0;
    }
}
