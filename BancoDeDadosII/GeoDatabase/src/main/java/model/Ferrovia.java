package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.locationtech.jts.geom.Geometry;

@Entity
@Table(name = "ferrovias_2014")

public class Ferrovia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gid;
    @Column(name = "objectid")
    private Integer objectid;
    @Column(name = "ferrovia")
    private String ferrovia;
    @Column(name = "operadora")
    private String operadora;
    @Column(name = "codpnvsimp")
    private String codpnvsimp;    
    @Column(name = "cod_pnv")
    private String cod_pnv;
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

    public String getFerrovia() {
        return ferrovia;
    }

    public void setFerrovia(String ferrovia) {
        this.ferrovia = ferrovia;
    }

    public String getOperadora() {
        return operadora;
    }

    public void setOperadora(String operadora) {
        this.operadora = operadora;
    }

    public String getCodpnvsimp() {
        return codpnvsimp;
    }

    public void setCodpnvsimp(String codpnvsimp) {
        this.codpnvsimp = codpnvsimp;
    }

    public String getCod_pnv() {
        return cod_pnv;
    }

    public void setCod_pnv(String cod_pnv) {
        this.cod_pnv = cod_pnv;
    }

    public Geometry getGeometria() {
        return geometria;
    }

    public void setGeometria(Geometry geometria) {
        this.geometria = geometria;
    }

    @Override
    public String toString() {
        return "Ferrovia{" + "gid=" + gid + ", objectid=" + objectid + ", ferrovia=" + ferrovia + ", operadora=" + operadora + ", codpnvsimp=" + codpnvsimp + ", cod_pnv=" + cod_pnv + ", geometria=" + geometria + '}';
    }
    
    
}
