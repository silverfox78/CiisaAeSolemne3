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
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
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
    @Basic(optional = false)
    @Column(name = "id_alumno")
    private int idAlumno;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_curso")
    private Integer idCurso;
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
    @JoinColumn(name = "id_curso", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    private DBCurso dBCurso;

    public DBAlumno() {
    }

    public DBAlumno(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public DBAlumno(Integer idCurso, int idAlumno, String rut, String nombres, String apellidos) {
        this.idCurso = idCurso;
        this.idAlumno = idAlumno;
        this.rut = rut;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
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

    public DBCurso getDBCurso() {
        return dBCurso;
    }

    public void setDBCurso(DBCurso dBCurso) {
        this.dBCurso = dBCurso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCurso != null ? idCurso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DBAlumno)) {
            return false;
        }
        DBAlumno other = (DBAlumno) object;
        if ((this.idCurso == null && other.idCurso != null) || (this.idCurso != null && !this.idCurso.equals(other.idCurso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.ciisa.data.DBAlumno[ idCurso=" + idCurso + " ]";
    }
    
}
