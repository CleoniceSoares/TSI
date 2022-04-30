package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.locationtech.jts.geom.Geometry;

@Entity
@Table(name = "rodovia_2014")

public class Rodovia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gid;
    @Column(name = "obejectid ")
    private Integer objectid;
    @Column(name = "descseg")
    private String descseg;
    @Column(name = "tipopnv")
    private String tipopnv;
    @Column(name = "codigo")
    private String codigo;    
    @Column(name = "geom")
    private Geometry geometria;

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public Integer getObjectid() {
        return objectid;
    }

    public void setObjectid(Integer objectid) {
        this.objectid = objectid;
    }

    public String getDescseg() {
        return descseg;
    }

    public void setDescseg(String descseg) {
        this.descseg = descseg;
    }

    public String getTipopnv() {
        return tipopnv;
    }

    public void setTipopnv(String tipopnv) {
        this.tipopnv = tipopnv;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Geometry getGeometria() {
        return geometria;
    }

    public void setGeometria(Geometry geometria) {
        this.geometria = geometria;
    }

    @Override
    public String toString() {
        return "Rodovia{" + "gid=" + gid + ", objectid=" + objectid + ", descseg=" + descseg + ", tipopnv=" + tipopnv + ", codigo=" + codigo + ", geometria=" + geometria + '}';
    }
    
    
}
