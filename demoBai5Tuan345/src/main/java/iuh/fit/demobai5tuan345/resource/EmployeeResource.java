package iuh.fit.demobai5tuan345.resource;

import iuh.fit.demobai5tuan345.dao.DepartmentDAO;
import iuh.fit.demobai5tuan345.dao.EmployeeDAO;
import iuh.fit.demobai5tuan345.entity.Department;
import iuh.fit.demobai5tuan345.entity.Employee;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import javax.sql.DataSource;
import java.util.List;

@Path("/employees")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeResource {

    private EmployeeDAO employeeDAO;
    private DepartmentDAO departmentDAO;
    @Resource(name="jdbc/quanlynhanvienbai5")
    private DataSource dataSource;

    @PostConstruct
    public void init(){
        employeeDAO= new EmployeeDAO(dataSource);
        departmentDAO= new DepartmentDAO(dataSource);
    }

    @GET
    public Response getAll(){
        List<Employee> ds= employeeDAO.getAllNhanVien();
        if(ds==null){
            return Response.status(Response.Status.NOT_FOUND).entity("Khong tim thay nv").build();
        }
        for(Employee e: ds){
            Department d =departmentDAO.getPhongBanTheoID(e.getDepartment().getId());
            e.setDepartment(d);
        }
        return Response.ok(ds).build();
    }

    @GET
    @Path("/{pb}")
    public Response getNVTheoPB(@PathParam("pb") String tenpb){
        List<Employee> ds= employeeDAO.getNhanVienTheoPhongBan(tenpb);
        if(ds==null){
            return Response.status(Response.Status.NOT_FOUND).entity("Khong tim thay nv").build();
        }
        for(Employee e: ds){
            Department d =departmentDAO.getPhongBanTheoID(e.getDepartment().getId());
            e.setDepartment(d);
        }
        return Response.ok(ds).build();
    }

    @POST
    @Path("/add/{tenpb}")
    public Response themNV(@PathParam("tenpb") String tenpb,Employee e){
        Department d =null;
        List<Department> ds= departmentDAO.timPBTheoTen(tenpb);
        if(ds!=null){
            d=ds.get(0);
        }
        e.setDepartment(d);
        if(employeeDAO.themNhanVienMoiTheoPhongBan(e)){
            return Response.ok(e).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Lỗi thêm nhân viên").build();
    }

    @PUT
    @Path("/{id}")
    public Response suaNV(@PathParam("id") int id, Employee e){
        e.setId(id);
        if(employeeDAO.suaNhanVien(e)){
            return Response.ok(e).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Lỗi sửa nhân viên").build();
    }

    @DELETE
    @Path("/{id}")
    public Response xoaNV(@PathParam("id") int id){
        if(employeeDAO.xoaNhanVien(id)){
            return Response.ok("Xóa thành công").build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Lỗi xóa nhân viên").build();
    }
}
