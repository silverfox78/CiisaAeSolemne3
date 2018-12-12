/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.ciisa.data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Samuel Barrera
 */
@Entity
@Table(name = "tbl_curso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DBCurso.findAll", query = "SELECT d FROM DBCurso d")
    , @NamedQuery(name = "DBCurso.findById", query = "SELECT d FROM DBCurso d WHERE d.id = :id")
    , @NamedQuery(name = "DBCurso.findByNombre", query = "SELECT d FROM DBCurso d WHERE d.nombre = :nombre")
    , @NamedQuery(name = "DBCurso.findByProfesor", query = "SELECT d FROM DBCurso d WHERE d.profesor = :profesor")
    , @NamedQuery(name = "DBCurso.findByDescripcion", query = "SELECT d FROM DBCurso d WHERE d.descripcion = :descripcion")
    , @NamedQuery(name = "DBCurso.findByNivel", query = "SELECT d FROM DBCurso d WHERE d.nivel = :nivel")
    , @NamedQuery(name = "DBCurso.findByBasica", query = "SELECT d FROM DBCurso d WHERE d.basica = :basica")
    , @NamedQuery(name = "DBCurso.findByDiurno", query = "SELECT d FROM DBCurso d WHERE d.diurno = :diurno")
    , @NamedQuery(name = "DBCurso.findByCrtdFecha", query = "SELECT d FROM DBCurso d WHERE d.crtdFecha = :crtdFecha")
    , @NamedQuery(name = "DBCurso.findByLupdFecha", query = "SELECT d FROM DBCurso d WHERE d.lupdFecha = :lupdFecha")})
public class DBCurso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    private String profesor;
    @Size(max = 1000)
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    private int nivel;
    @Basic(optional = false)
    @NotNull
    private boolean basica;
    @Basic(optional = false)
    @NotNull
    private boolean diurno;
    @Column(name = "crtd_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date crtdFecha;
    @Column(name = "lupd_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lupdFecha;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCurso")
    private List<DBAlumno> dBAlumnoList;

    public DBCurso() {
    }

    public DBCurso(Integer id) {
        this.id = id;
    }

    public DBCurso(Integer id, String nombre, String profesor, int nivel, boolean basica, boolean diurno) {
        this.id = id;
        this.nombre = nombre;
        this.profesor = profesor;
        this.nivel = nivel;
        this.basica = basica;
        this.diurno = diurno;
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

    public boolean getBasica() {
        return basica;
    }

    public void setBasica(boolean basica) {
        this.basica = basica;
    }

    public boolean getDiurno() {
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

    @XmlTransient
    public List<DBAlumno> getDBAlumnoList() {
        return dBAlumnoList;
    }

    public void setDBAlumnoList(List<DBAlumno> dBAlumnoList) {
        this.dBAlumnoList = dBAlumnoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DBCurso)) {
            return false;
        }
        DBCurso other = (DBCurso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.ciisa.data.DBCurso[ id=" + id + " ]";
    }
    
}
