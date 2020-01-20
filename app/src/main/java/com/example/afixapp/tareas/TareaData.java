package com.example.afixapp.tareas;

public class TareaData {
    public String tareaID;
    public String observaciones;
    public String eligirFrequencia;
    public String eligirCategoria;
    public String dataFim;
    public String horaFim;
    public String dataInicio;
    public String horaInicio;

    public TareaData(){

    }

    public TareaData(String tareaID, String observaciones, String eligirFrequencia, String eligirCategoria, String dataFim, String horaFim, String dataInicio, String horaInicio) {
        this.tareaID = tareaID;
        this.observaciones = observaciones;
        this.eligirFrequencia = eligirFrequencia;
        this.eligirCategoria = eligirCategoria;
        this.dataFim = dataFim;
        this.horaFim = horaFim;
        this.dataInicio = dataInicio;
        this.horaInicio = horaInicio;
    }


    public String getTareaID() {
        return tareaID;
    }

    public void setTareaID(String tareaID) {
        this.tareaID = tareaID;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getEligirFrequencia() {
        return eligirFrequencia;
    }

    public void setEligirFrequencia(String eligirFrequencia) {
        this.eligirFrequencia = eligirFrequencia;
    }

    public String getEligirCategoria() {
        return eligirCategoria;
    }

    public void setEligirCategoria(String eligirCategoria) {
        this.eligirCategoria = eligirCategoria;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public String getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(String horaFim) {
        this.horaFim = horaFim;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }
}
