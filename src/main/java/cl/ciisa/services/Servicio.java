package cl.ciisa.services;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import cl.ciisa.data.DBCurso;
import cl.ciisa.negocio.Curso;
import cl.ciisa.transfer.TrAlumno;
import cl.ciisa.transfer.TrCurso;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityTransaction;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;

@Path("/curso")
public class Servicio {
    public static final String MetodoNoImplementado = "{ \"Error\" : \"Este metodo aun no se implementa.\" }";
    public static final String ErrorEnMetodo = "{ \"Error\" : \"Este metodo aun no se implementa.\" }";

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaCursos() {
        try {
            return new Curso().listar();
        } catch (Exception e) {
            return Response.serverError().entity("{ \"Error\" : \"" + e.getMessage() + "\" }").build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarCursoPorId(@PathParam("id") String id) {
        try {
            return new Curso().buscar(id);
        } catch (Exception e) {
            return Response.serverError().entity("{ \"Error\" : \"" + e.getMessage() + "\" }").build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardarCurso(TrCurso curso) {
        try {
            return new Curso().guardarCurso(curso);
        } catch (Exception e) {
            return Response.serverError().entity("{ \"Error\" : \"" + e.getMessage() + "\" }").build();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizaCurso(TrCurso curso) {
        try {
            return new Curso().actualizaCurso(curso);
        } catch (Exception e) {
            return Response.serverError().entity("{ \"Error\" : \"" + e.getMessage() + "\" }").build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminaCurso(@PathParam("id") String id) {
        try {
            return new Curso().eliminaCurso(id);
        } catch (Exception e) {
            return Response.serverError().entity("{ \"Error\" : \"" + e.getMessage() + "\" }").build();
        }
    }
    
    @GET
    @Path("/{id}/Alumno")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarAlumnosPorIdCurso(@PathParam("id") String id) {
        try {
            return new Curso().listarAlumnos(id);
        } catch (Exception e) {
            return Response.serverError().entity("{ \"Error\" : \"" + e.getMessage() + "\" }").build();
        }
    }
    
    @GET
    @Path("/Alumno/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarAlumnosPorId(@PathParam("id") String id) {
        try {
            return new Curso().buscaAlumno(id);
        } catch (Exception e) {
            return Response.serverError().entity("{ \"Error\" : \"" + e.getMessage() + "\" }").build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}/Alumno")
    public Response guardarAlumno(@PathParam("id") String id, TrAlumno alumno) {
        try {
            return new Curso().guardarAlumno(id, alumno);
        } catch (Exception e) {
            return Response.serverError().entity("{ \"Error\" : \"" + e.getMessage() + "\" }").build();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}/Alumno")
    public Response actualizaCurso(@PathParam("id") String id, TrAlumno alumno) {
        try {
            return new Curso().actualizaAlumno(id, alumno);
        } catch (Exception e) {
            return Response.serverError().entity("{ \"Error\" : \"" + e.getMessage() + "\" }").build();
        }
    }

    @DELETE
    @Path("/Alumno/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminaAlumnosPorId(@PathParam("id") String id) {
        try {
            return new Curso().eliminaAlumno(id);
        } catch (Exception e) {
            return Response.serverError().entity("{ \"Error\" : \"" + e.getMessage() + "\" }").build();
        }
    }

}