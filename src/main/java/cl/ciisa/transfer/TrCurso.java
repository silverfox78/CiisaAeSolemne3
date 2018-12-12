package cl.ciisa.transfer;

import cl.ciisa.data.DBCurso;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TrCurso implements Serializable{
    private Integer id;
    private String nombre;
    private String profesor;
    private String descripcion;
    private int nivel;
    private boolean basica;
    private boolean diurno;
    private Date crtdFecha;
    private Date lupdFecha;
    private List<TrAlumno> alumnos;
    
    public TrCurso(){
        
    }

    public TrCurso(Integer id, String nombre, String profesor, String descripcion, int nivel, boolean basica, boolean diurno, Date crtdFecha, Date lupdFecha, List<TrAlumno> alumnos) {
        this.id = id;
        this.nombre = nombre;
        this.profesor = profesor;
        this.descripcion = descripcion;
        this.nivel = nivel;
        this.basica = basica;
        this.diurno = diurno;
        this.crtdFecha = crtdFecha;
        this.lupdFecha = lupdFecha;
        this.alumnos = alumnos;
    }
    
    public TrCurso(DBCurso curso)
    {
        this.id = curso.getId();
        this.nombre = curso.getNombre();
        this.profesor = curso.getProfesor();
        this.descripcion = curso.getDescripcion();
        this.nivel = curso.getNivel();
        this.basica = curso.getDiurno();
        this.diurno = curso.getDiurno();
        this.crtdFecha = curso.getCrtdFecha();
        this.lupdFecha = curso.getLupdFecha();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public boolean isBasica() {
        return basica;
    }

    public void setBasica(boolean basica) {
        this.basica = basica;
    }

    public boolean isDiurno() {
        return diurno;
    }

    public void setDiurno(boolean diurno) {
        this.diurno = diurno;
    }

    public Date getCrtdFecha() {
        return crtdFecha;
    }

    public void setCrtdFecha(Date crtdFecha) {
        this.crtdFecha = crtdFecha;
    }

    public Date getLupdFecha() {
        return lupdFecha;
    }

    public void setLupdFecha(Date lupdFecha) {
        this.lupdFecha = lupdFecha;
    }

    public List<TrAlumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<TrAlumno> alumnos) {
        this.alumnos = alumnos;
    }
}
