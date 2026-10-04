package iuh.fit.demobai3tuan345voirestapi.resource;

import iuh.fit.demobai3tuan345voirestapi.dao.ProductDao;
import iuh.fit.demobai3tuan345voirestapi.entity.Product;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import javax.sql.DataSource;
import java.util.List;

@Path("/products")
public class ProductResource  {
    private ProductDao productDao;
    @Resource(name = "jdbc/shopdb")
    private DataSource dataSource;

    public ProductResource() {
    }
//    Bảo Java chạy hàm này 1 lần ngay sau khi object được tạo
//    và các dependency đã được khởi tạo.
    @PostConstruct
    public void init(){
        this.productDao = new ProductDao(dataSource);
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProduct(){
        List<Product> ds= productDao.getAllProduct();
        return Response.ok(ds).build();
    }
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductById(@PathParam("id") int id){
        Product p= productDao.getProductById(id);
        return Response.ok(p).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response themSP(Product p){
        if(productDao.themSanPham(p)){
            return Response.ok(p).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Loi them sp").build();
    }

    @DELETE

    public Response xoaSP(@QueryParam("idxoa") int id){
        if(productDao.xoaSanPham(id)){
            return Response.ok().build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Loi xoa sp").build();
    }
}
