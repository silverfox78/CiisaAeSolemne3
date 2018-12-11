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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaCursos() {
        return new Curso().listar();
        /*EntityManagerFactory emf = Persistence.createEntityManagerFactory(DBCURSO);
        EntityManager em = emf.createEntityManager();
        Response retorno = null;

        try {
            List<DBCurso> lista = em.createNamedQuery(DBCURSO + ".findAll").getResultList();
            if (lista.size() <= 0) {
                retorno = Response.noContent().build();
            } else {
                retorno = Response.ok().entity(lista).build();
            }
        } catch (Exception e) {
            retorno = Response.serverError().entity(MensajeError.listaCursos.getMensaje() + e.getMessage()).build();
        } finally {
            em.close();
        }
        return retorno;*/
        //return Response.serverError().entity(MetodoNoImplementado).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarCursoPorId(@PathParam("id") String id) {
        /*EntityManagerFactory emf = Persistence.createEntityManagerFactory(DBCURSO);
        EntityManager em = emf.createEntityManager();
        Response retorno = null;

        try {
            DBCurso curso = em.find(DBCurso.class, Integer.parseInt(id));
            retorno = Response.ok(curso).build();
        } catch (Exception e) {
            retorno = Response.serverError().entity(MensajeError.buscarCursoPorId.getMensaje() + e.getMessage()).build();
        } finally {
            em.close();
        }

        return retorno;*/
        return Response.serverError().entity(MetodoNoImplementado).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardarCurso(DBCurso curso) {
        /*EntityManagerFactory emf = Persistence.createEntityManagerFactory(DBCURSO);
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Response retorno = null;

        try {
            tx.begin();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            curso.setCrtdFecha(date);
            em.persist(curso);
            tx.commit();
            
            retorno = Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            tx.rollback();
            retorno = Response.serverError().entity(MensajeError.guardarCurso.getMensaje() + e.getMessage()).build();
        } finally {
            em.close();
        }
        return retorno;*/
        return Response.serverError().entity(MetodoNoImplementado).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizaCurso(DBCurso curso) {
        /*EntityManagerFactory emf = Persistence.createEntityManagerFactory(DBCURSO);
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Response retorno = null;

        try {
            tx.begin();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            curso.setLupdFecha(date);
            curso = em.merge(curso);
            tx.commit();
            
            retorno = Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            tx.rollback();
            retorno = Response.serverError().entity(MensajeError.actualizaCurso.getMensaje() + e.getMessage()).build();
        } finally {
            em.close();
        }
        return retorno;*/
        return Response.serverError().entity(MetodoNoImplementado).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminaCurso(@PathParam("id") String id) {
        /*EntityManagerFactory emf = Persistence.createEntityManagerFactory(DBCURSO);
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Response retorno = null;

        try {
            tx.begin();
            DBCurso curso = em.getReference(DBCurso.class, Integer.parseInt(id));
            em.remove(curso);
            tx.commit();
            retorno = Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            tx.rollback();
            retorno = Response.serverError().entity(MensajeError.eliminaCurso.getMensaje() + e.getMessage()).build();
        } finally {
            em.close();
        }

        return retorno;*/
        return Response.serverError().entity(MetodoNoImplementado).build();
    }
}