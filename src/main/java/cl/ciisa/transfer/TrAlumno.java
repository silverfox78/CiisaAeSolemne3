package cl.ciisa.transfer;

import cl.ciisa.data.DBAlumno;

public class TrAlumno {
    private Integer idAlumno;
    private String rut;
    private String nombres;
    private String apellidos;

    public TrAlumno(Integer idAlumno, String rut, String nombres, String apellidos) {
        this.idAlumno = idAlumno;
        this.rut = rut;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }
    
    public TrAlumno(DBAlumno alumno){
        this.idAlumno = alumno.getIdAlumno();
        this.rut = alumno.getRut();
        this.nombres = alumno.getNombres();
        this.apellidos = alumno.getApellidos();
    }

    public Integer getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Integer idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
}
