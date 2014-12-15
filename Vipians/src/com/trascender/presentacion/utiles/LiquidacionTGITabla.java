package com.trascender.presentacion.utiles;

import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import java.util.Date;

public class LiquidacionTGITabla {

    private String  cuim;
    private String  razonSocial;
    private String  numeroRegistro;
    private Date    fechaVencimiento;
    private String  importeLiquidacion;
    private String  titulo;
    private LiquidacionTasa liquidacionTasa;

    public LiquidacionTGITabla() {
        
    }

    public LiquidacionTasa getLiquidacionTasa() {
        return liquidacionTasa;
    }

    public void setLiquidacionTasa(LiquidacionTasa liquidacionTasa) {
        this.liquidacionTasa = liquidacionTasa;
    }

    public String getCuim() {
        return cuim;
    }

    public void setCuim(String cuim) {
        this.cuim = cuim;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(String numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getImporteLiquidacion() {
        return importeLiquidacion;
    }

    public void setImporteLiquidacion(String importeLiquidacion) {
        this.importeLiquidacion = importeLiquidacion;
    }

}
