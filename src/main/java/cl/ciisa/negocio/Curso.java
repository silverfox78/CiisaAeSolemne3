package cl.ciisa.negocio;

import cl.ciisa.data.DBAlumno;
import cl.ciisa.data.DBCurso;
import cl.ciisa.transfer.TrAlumno;
import cl.ciisa.transfer.TrCurso;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.ws.rs.core.Response;

public class Curso {
    public static final String DBCURSO = "DBCurso";  

    private enum MensajeError{
        listaCursos("Error al listar los curso - "),
        buscarCursoPorId("Error, no se encontro el curso - "),
        guardarCurso("Error al guardar el curso - "),
        actualizaCurso("Error al actualizar el curso - "),
        eliminaCurso("Error al eliminar el curso - "),
        listarAlumnos("Error al listar los alumnos del curso - "),
        buscaAlumno("Error al buscar al alumno - "),
        guardarAlumno("Error al guardar al alumno - "),
        actualizaAlumno("Error al actualizar al alumno - "),
        eliminaAlumno("Error al aliminar al alumno - ");

        private String mensaje;

        MensajeError(String mensaje){
            this.mensaje = mensaje;
        }

        public String getMensaje() {
            return this.mensaje;
        }
    }
    
    public Response listar(){
        Response retorno = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DBCURSO);
        EntityManager em = emf.createEntityManager();

        try {
            List<DBCurso> lista = em.createNamedQuery(DBCURSO + ".findAll").getResultList();
            
            if (lista.size() <= 0) {
                retorno = Response.noContent().build();
            } else {
                retorno = Response.ok().entity(this.generaListaCurso(lista)).build();
            }
        } catch (Exception e) {
            retorno = Response.serverError().entity(MensajeError.listaCursos.getMensaje() + e.getMessage()).build();
        } finally {
            em.close();
        }
        return retorno;
    }
    
    public Response buscar(String id){
        Response retorno = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DBCURSO);
        EntityManager em = emf.createEntityManager();

        try {
            List<DBCurso> lista = em.createNamedQuery("DBCurso.findById").setParameter("id", Integer.parseInt(id)).getResultList();
            
            if (lista.size() <= 0) {
                retorno = Response.noContent().build();
            } else {
                retorno = Response.ok().entity(this.generaListaCurso(lista)).build();
            }
        } catch (Exception e) {
            retorno = Response.serverError().entity(MensajeError.buscarCursoPorId.getMensaje() + e.getMessage()).build();
        } finally {
            em.close();
        }
        return retorno;
    }
    
    public Response guardarCurso(TrCurso curso){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DBCURSO);
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Response retorno = null;

        DBCurso dbCurso = obtieneCursoGuardar(curso);
        try {
            tx.begin();
            em.persist(dbCurso);
            tx.commit();
            
            retorno = Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            tx.rollback();
            retorno = Response.serverError().entity(MensajeError.guardarCurso.getMensaje() + e.getMessage()).build();
        } finally {
            em.close();
        }
        return retorno;
    }
    
    public Response actualizaCurso(TrCurso curso){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DBCURSO);
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Response retorno = null;

        DBCurso dbCurso = obtieneCursoActualizar(curso);
        try {
            tx.begin();
            dbCurso = em.merge(dbCurso);
            tx.commit();
            
            retorno = Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            tx.rollback();
            retorno = Response.serverError().entity(MensajeError.actualizaCurso.getMensaje() + e.getMessage()).build();
        } finally {
            em.close();
        }
        return retorno;
    }
    
    public Response eliminaCurso(String id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DBCURSO);
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Response retorno = null;
        
        try {
            eliminaAlumnos(listaAlumnos(Integer.parseInt(id)));
            DBCurso dbCurso = em.getReference(DBCurso.class, Integer.parseInt(id));            
            tx.begin();
            em.remove(dbCurso);
            tx.commit();            
            retorno = Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            tx.rollback();
            retorno = Response.serverError().entity(MensajeError.eliminaCurso.getMensaje() + e.getMessage()).build();
        } finally {
            em.close();
        }
        return retorno;
    }
    
    public Response listarAlumnos(String idCurso){
        Response retorno = null;
        
        try {
            List<TrAlumno> lista = listaAlumnos(Integer.parseInt(idCurso));
            
            if (lista.size() <= 0) {
                retorno = Response.noContent().build();
            } else {
                retorno = Response.ok().entity(lista).build();
            }
        } catch (Exception e) {
            retorno = Response.serverError().entity(MensajeError.listarAlumnos.getMensaje() + e.getMessage()).build();
        } 
        
        return retorno;
    }
    
    public Response buscaAlumno(String idAlumno){
        Response retorno = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DBCURSO);
        EntityManager em = emf.createEntityManager();

        try {
            DBAlumno alumno = em.find(DBAlumno.class, Integer.parseInt(idAlumno));
            retorno = Response.ok(alumno).build();
        } catch (Exception e) {
            retorno = Response.serverError().entity(MensajeError.buscaAlumno.getMensaje() + e.getMessage()).build();
        } finally {
            em.close();
        }
        return retorno;
    }
    
    public Response guardarAlumno(String idCurso, TrAlumno alumno){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DBCURSO);
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Response retorno = null;

        DBAlumno dbAlumno = obtieneAlumnoGuardar(Integer.parseInt(idCurso), alumno);
        try {
            tx.begin();
            em.persist(dbAlumno);
            tx.commit();
            
            retorno = Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            tx.rollback();
            retorno = Response.serverError().entity(MensajeError.guardarAlumno.getMensaje() + e.getMessage()).build();
        } finally {
            em.close();
        }
        return retorno;
    }
    
    public Response actualizaAlumno(String idCurso, TrAlumno alumno){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DBCURSO);
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Response retorno = null;

        DBAlumno dbAlumno = obtieneAlumnoActualizar(Integer.parseInt(idCurso), alumno);
        try {
            tx.begin();
            dbAlumno = em.merge(dbAlumno);
            tx.commit();
            
            retorno = Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            tx.rollback();
            retorno = Response.serverError().entity(MensajeError.actualizaAlumno.getMensaje() + e.getMessage()).build();
        } finally {
            em.close();
        }
        return retorno;
    }
        
    public Response eliminaAlumno(String idAlumno){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DBCURSO);
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Response retorno = null;
        
        try {
            DBAlumno dbAlumno = em.getReference(DBAlumno.class, Integer.parseInt(idAlumno));            
            tx.begin();
            em.remove(dbAlumno);
            tx.commit();            
            retorno = Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            tx.rollback();
            retorno = Response.serverError().entity(MensajeError.eliminaAlumno.getMensaje() + e.getMessage()).build();
        } finally {
            em.close();
        }
        return retorno;
    }
    
    private DBAlumno obtieneAlumnoGuardar(Integer idCurso, TrAlumno alumno) {
        DBAlumno retorno = new DBAlumno();
        retorno.setIdCurso(idCurso);
        retorno.setRut(alumno.getRut());
        retorno.setNombres(alumno.getNombres());
        retorno.setApellidos(alumno.getApellidos());
        return retorno;
    }
    
    private DBAlumno obtieneAlumnoActualizar(Integer idCurso, TrAlumno alumno) {
        DBAlumno retorno = new DBAlumno();
        retorno.setIdAlumno(alumno.getIdAlumno());
        retorno.setIdCurso(idCurso);
        retorno.setRut(alumno.getRut());
        retorno.setNombres(alumno.getNombres());
        retorno.setApellidos(alumno.getApellidos());
        return retorno;
    }
    
    private List<TrCurso> generaListaCurso(List<DBCurso> lista) throws Exception{
        List<TrCurso> retorno = new ArrayList<>();
        for (DBCurso curso : lista) {
            retorno.add(this.generaCurso(curso));
        }
        return retorno;
    }
    
    private TrCurso generaCurso(DBCurso curso) throws Exception{
        TrCurso retorno = new TrCurso(curso);
        retorno.setAlumnos(this.listaAlumnos(curso.getId()));
        return retorno;
    }
    
    private List<TrAlumno> listaAlumnos(Integer idCurso) throws Exception{
        List<TrAlumno> alumnos = new ArrayList<TrAlumno>();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DBCURSO);
        EntityManager em = emf.createEntityManager();
            
        try {
            List<DBAlumno> lista = em.createNamedQuery("DBAlumno.findByIdCurso").setParameter("idCurso", idCurso).getResultList();
            
            if (lista.size() > 0) {
                for (DBAlumno alumno : lista) {
                    alumnos.add(new TrAlumno(alumno));    
                }
            }
        } catch (Exception e) {
            throw new Exception("Ha ocurrido un error al listar los Alumnos.");
        } finally {
            em.close();           
        }
        
        return alumnos;
    }
    
    private void eliminaAlumnos(List<TrAlumno> lista){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DBCURSO);
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        for (TrAlumno trAlumno : lista) {
            try {
                DBAlumno dbalumno = em.getReference(DBAlumno.class, trAlumno.getIdAlumno());
                tx.begin();
                em.remove(dbalumno);
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
            } finally {
                em.close();
            }
        }
    }
    
    private DBCurso obtieneCursoGuardar(TrCurso curso){
        DBCurso retorno = new DBCurso();
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        retorno.setCrtdFecha(date);
        
        retorno.setBasica(curso.isBasica());
        retorno.setDescripcion(curso.getDescripcion());
        retorno.setDiurno(curso.isDiurno());
        retorno.setNivel(curso.getNivel());
        retorno.setNombre(curso.getNombre());
        retorno.setProfesor(curso.getProfesor());
        
        return retorno;
    }
    
    private DBCurso obtieneCursoActualizar(TrCurso curso){
        DBCurso retorno = new DBCurso();
        
        retorno.setId(curso.getId());
        retorno.setBasica(curso.isBasica());
        retorno.setDescripcion(curso.getDescripcion());
        retorno.setDiurno(curso.isDiurno());
        retorno.setNivel(curso.getNivel());
        retorno.setNombre(curso.getNombre());
        retorno.setProfesor(curso.getProfesor());
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        retorno.setLupdFecha(date);
        
        return retorno;
    }
}
