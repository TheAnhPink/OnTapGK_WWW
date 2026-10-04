package iuh.fit.demobai3tuan345voirestapi.resource;

import iuh.fit.demobai3tuan345voirestapi.dao.ProductDao;
import iuh.fit.demobai3tuan345voirestapi.entity.CartBean;
import iuh.fit.demobai3tuan345voirestapi.entity.Product;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.weld.context.http.Http;

import javax.sql.DataSource;

@Path("/cart")
@Produces(MediaType.APPLICATION_JSON) // Đảm bảo toàn bộ class trả về JSON
@Consumes(MediaType.APPLICATION_JSON)
public class CartResource {
    private ProductDao productDao;
    @Resource(name="jdbc/shopdb")
    private DataSource dataSource;

    @PostConstruct
    public void init(){
        productDao= new ProductDao(dataSource);
    }

    public CartBean getCartTrongSession(HttpServletRequest req){
        HttpSession session = req.getSession();
        CartBean cartBean= (CartBean) session.getAttribute("cart");
        if(cartBean==null){
            cartBean= new CartBean();
            session.setAttribute("cart",cartBean);
        }
        return cartBean;
    }

    @GET
    public Response getAllCart(@Context HttpServletRequest req){
        CartBean cartBean= getCartTrongSession(req);
        if(cartBean.getDsCartItem()==null||cartBean.getDsCartItem().isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).entity("Gio hang trong").build();
        }
        return Response.ok(cartBean).build();
    }

    @POST
    @Path("/add")
    public Response themSPVaoGio(@QueryParam("id") int id,
                                 @QueryParam("soLuong") int sl,
                                 @Context HttpServletRequest req
    ){
        Product p= productDao.getProductById(id);
        if (p==null){
            return Response.status(Response.Status.NOT_FOUND).entity("Khong ton tai san pham co id: "+id).build();
        }
        CartBean cartBean= getCartTrongSession(req);
        cartBean.addProduct(p,sl);
        HttpSession session= req.getSession();
        session.setAttribute("cart",cartBean);
        return Response.ok(p).build();
    }

    @POST
    @Path("/suaSL")
    public Response suaSLTheoID(@QueryParam("id") int id, @QueryParam("soLuong") int sl,
                                @Context HttpServletRequest req){
        CartBean cartBean= getCartTrongSession(req);
        cartBean.updateQuantity(id,sl);
        HttpSession session= req.getSession();
        session.setAttribute("cart",cartBean);
        return Response.ok(cartBean).build();
    }

    @DELETE
    @Path("/{id}")
    public Response xoaSPTrongGio(@PathParam("id") int id, @Context HttpServletRequest req){
        CartBean cartBean= getCartTrongSession(req);
        cartBean.removeCartItem(id);
        HttpSession session= req.getSession();
        session.setAttribute("cart",cartBean);
        return Response.ok().build();
    }
}
