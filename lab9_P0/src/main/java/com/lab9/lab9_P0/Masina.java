package com.lab9.lab9_P0;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="masini")
public class Masina {

    @Id
    @Column(name="numarInmatriculare")
    private String numarInmatriculare;
    private String marca;
    private int anulFabricatiei;
    private String culoare;
    private int nrKM;
    public Masina() {}
    public Masina(String numarInmatriculare, String marca, int anulFabricatiei, String culoare, int nrKM)
    {
        this.numarInmatriculare = numarInmatriculare;
        this.marca = marca;
        this.anulFabricatiei = anulFabricatiei;
        this.culoare = culoare;
        this.nrKM = nrKM;
    }

    public String getNumarInmatriculare() {
        return numarInmatriculare;
    }

    public void setNumarInmatriculare(String numarInmatriculare) {
        this.numarInmatriculare = numarInmatriculare;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getAnulFabricatiei() {
        return anulFabricatiei;
    }

    public void setAnulFabricatiei(int anulFabricatiei) {
        this.anulFabricatiei = anulFabricatiei;
    }

    public String getCuloare() {
        return culoare;
    }

    public void setCuloare(String culoare) {
        this.culoare = culoare;
    }

    public int getNrKM() {
        return nrKM;
    }

    public void setNrKM(int nrKM) {
        this.nrKM = nrKM;
    }
    @Override
    public String toString()
    {
        return "Numar inmatriculare: " + numarInmatriculare+", Marca: "+marca
                +", An fabricatie: "+anulFabricatiei+", Culoare: "+ culoare +", Nr Km: "+nrKM;
    }
}
