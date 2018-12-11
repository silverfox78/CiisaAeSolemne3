package cl.ciisa.negocio;

import cl.ciisa.data.DBCurso;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Response;

public class Curso {
    public static final String DBCURSO = "DBCurso";

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
                retorno = Response.ok().entity(lista).build();
            }
        } catch (Exception e) {
            retorno = Response.serverError().entity(MensajeError.listaCursos.getMensaje() + e.getMessage()).build();
        } finally {
            em.close();
        }
        return retorno;
    }
}
