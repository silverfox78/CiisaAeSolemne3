package cl.ciisa.negocio;

import cl.ciisa.data.DBAlumno;
import cl.ciisa.data.DBCurso;
import cl.ciisa.transfer.TrAlumno;
import cl.ciisa.transfer.TrCurso;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Response;

public class Curso {
    public static final String DBCURSO = "DBCurso";
    public static final String DBALUMNO = "DBAlumno";

    private enum MensajeError{
        listaCursos("Error al listar los curso - "),
        buscarCursoPorId("Error, no se encontro el curso - "),
        guardarCurso("Error al guardar el curso - "),
        actualizaCurso("Error al actualizar el curso - "),
        eliminaCurso("Error al eliminar el curso - ");

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
    
    private List<TrCurso> generaListaCurso(List<DBCurso> lista) throws Exception{
        List<TrCurso> retorno = new ArrayList<>();
        for (DBCurso curso : lista) {
            retorno.add(this.generaCurso(curso));
        }
        return retorno;
    }
    
    private TrCurso generaCurso(DBCurso curso) throws Exception{
        TrCurso retorno = new TrCurso(curso);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DBCURSO);
        EntityManager em = emf.createEntityManager();
        List<TrAlumno> alumnos = new ArrayList<TrAlumno>();
        
        try {
            List<DBAlumno> lista = em.createNamedQuery("DBAlumno.findByIdCurso").setParameter("idCurso", curso.getId()).getResultList();
            
            if (lista.size() > 0) {
                for (DBAlumno alumno : lista) {
                    alumnos.add(new TrAlumno(alumno));    
                }
            }
        } catch (Exception e) {
            throw new Exception("Ha ocurrido un error al listar los Alumnos.");
        } finally {
            em.close();
            retorno.setAlumnos(alumnos);            
        }
        return retorno;
    }
}
