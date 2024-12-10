/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Fabia
 */
public class Quickpass {
    private String filial;
    private String codigo; 
    private String placa;
    private boolean estado; 
    private boolean eliminado; 

    public Quickpass(String filial, String codigo, String placa) {
        this.filial = filial;
        this.codigo = codigo;
        this.placa = placa;
        this.estado = true; 
        this.eliminado = false; 
    }

    public String getFilial() {
        return filial;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getPlaca() {
        return placa;
    }

    public boolean isEstado() {
        return estado;
    }

    public boolean isEliminado() {
        return eliminado; 
    }

    public void inactivar() {
        this.estado = false; 
    }

    public void activar() {
        this.estado = true; 
    }

    public void eliminar() {
        this.eliminado = true;
    }

    public void restaurar() {
        this.eliminado = false; 
    }

    @Override
    public String toString() {
        return "Quickpass [filial=" + filial + ", codigo=" + codigo + ", placa=" + placa + ", estado=" + (estado ? "Activo" : "Inactivo") + "]";
    }
}
