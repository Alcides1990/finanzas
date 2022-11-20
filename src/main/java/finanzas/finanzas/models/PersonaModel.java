package finanzas.finanzas.models;

import java.util.Date;

public class PersonaModel {
    private int id;
    private String cod_persona;
    private String per_nombre;
    private Date per_registro;
    private Boolean estado;
    public PersonaModel() {
    }
    public PersonaModel(int id, String cod_persona, String per_nombre, Date per_registro, Boolean estado) {
        this.id = id;
        this.cod_persona = cod_persona;
        this.per_nombre = per_nombre;
        this.per_registro = per_registro;
        this.estado = estado;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCod_persona() {
        return cod_persona;
    }
    public void setCod_persona(String cod_persona) {
        this.cod_persona = cod_persona;
    }
    public String getPer_nombre() {
        return per_nombre;
    }
    public void setPer_nombre(String per_nombre) {
        this.per_nombre = per_nombre;
    }
    public Date getPer_registro() {
        return per_registro;
    }
    public void setPer_registro(Date per_registro) {
        this.per_registro = per_registro;
    }
    public Boolean getEstado() {
        return estado;
    }
    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    
    
    
    
}
