package iuh.fit.demobai5tuan345.resource;


import iuh.fit.demobai5tuan345.dao.DepartmentDAO;
import iuh.fit.demobai5tuan345.entity.Department;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import javax.sql.DataSource;
import java.util.List;

@Path("/departments")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DepartmentResource {
    private DepartmentDAO departmentDAO;
    @Resource(name="jdbc/quanlynhanvienbai5")
    private DataSource dataSource;

    @PostConstruct
    public void init(){
        departmentDAO= new DepartmentDAO(dataSource);
    }

    @GET
    public Response getDSPhongBan(){
        List<Department> dspb= departmentDAO.getDSPhongBan();
        if(dspb!=null){
            return Response.ok(dspb).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Không tìm thấy phòng ban").build();
    }
    @GET
    @Path("/{id}")
    public Response getPhongBantheoID(@PathParam("id") int id){
        Department d= departmentDAO.getPhongBanTheoID(id);
        if(d!=null){
            return Response.ok(d).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Không tìm thấy phòng ban").build();
    }

    @POST
    @Path("/add")
    public Response themPhongBan(Department department){
        if(departmentDAO.themPhongBan(department)){
            return Response.ok(department).build();
        }
        return Response.status(Response.Status.CONFLICT).entity("Thêm thất bại").build();
    }

    @DELETE
    @Path("/{id}")
    public Response xoaPhongBan(@PathParam("id") int id){
        if(departmentDAO.xoaPhongBan(id)){
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Xóa thất bại").build();
    }

    @PUT
    @Path("/{id}")
    public Response capNhatPhongBan(@PathParam("id") int id, Department d){
        if(departmentDAO.capNhatPhongBan(id,d)){
            return Response.ok(d).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Cập nhật thất bại").build();

    }

    @GET
    @Path("/tim")
    public Response timPBTheoTen(@QueryParam("name") String ten){
        List<Department> d= departmentDAO.timPBTheoTen(ten);
        if(d==null){
            return Response.status(Response.Status.NOT_FOUND).entity("Khong tim thay").build();
        }
        return Response.ok(d).build();
    }

}
