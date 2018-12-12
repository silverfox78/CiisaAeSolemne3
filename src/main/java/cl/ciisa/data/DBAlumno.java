/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.ciisa.data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Samuel Barrera
 */
@Entity
@Table(name = "tbl_alumno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DBAlumno.findAll", query = "SELECT d FROM DBAlumno d")
    , @NamedQuery(name = "DBAlumno.findByIdAlumno", query = "SELECT d FROM DBAlumno d WHERE d.idAlumno = :idAlumno")
    , @NamedQuery(name = "DBAlumno.findByIdCurso", query = "SELECT d FROM DBAlumno d WHERE d.idCurso = :idCurso")
    , @NamedQuery(name = "DBAlumno.findByRut", query = "SELECT d FROM DBAlumno d WHERE d.rut = :rut")
    , @NamedQuery(name = "DBAlumno.findByNombres", query = "SELECT d FROM DBAlumno d WHERE d.nombres = :nombres")
    , @NamedQuery(name = "DBAlumno.findByApellidos", query = "SELECT d FROM DBAlumno d WHERE d.apellidos = :apellidos")})
public class DBAlumno implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_alumno")
    private Integer idAlumno;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_curso")
    private int idCurso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "rut")
    private String rut;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "nombres")
    private String nombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "apellidos")
    private String apellidos;

    public DBAlumno() {
    }

    public DBAlumno(Integer idAlumno) {
        this.idAlumno = idAlumno;
    }

    public DBAlumno(Integer idAlumno, int idCurso, String rut, String nombres, String apellidos) {
        this.idAlumno = idAlumno;
        this.idCurso = idCurso;
        this.rut = rut;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }

    public Integer getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Integer idAlumno) {
        this.idAlumno = idAlumno;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAlumno != null ? idAlumno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DBAlumno)) {
            return false;
        }
        DBAlumno other = (DBAlumno) object;
        if ((this.idAlumno == null && other.idAlumno != null) || (this.idAlumno != null && !this.idAlumno.equals(other.idAlumno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.ciisa.data.DBAlumno[ idAlumno=" + idAlumno + " ]";
    }
    
}
