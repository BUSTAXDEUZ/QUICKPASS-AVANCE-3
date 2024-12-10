/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Fabia
 */
import javax.swing.JOptionPane;

public class GestorQuickpass {
    private Quickpass[] quickpasses;
    private Quickpass[] quickpassesEliminados;
    private int contador;
    private int contadorEliminados;

    public GestorQuickpass(int capacidad) {
        quickpasses = new Quickpass[capacidad];
        quickpassesEliminados = new Quickpass[capacidad];
        contador = 0;
        contadorEliminados = 0;
    }

    public boolean agregarQuickpass(String filial, String codigo, String placa) {
        if (contador < quickpasses.length && validarCodigo(codigo)) {
            quickpasses[contador++] = new Quickpass(filial, codigo, placa);
            return true;
        }
        return false;
    }

    public boolean eliminarQuickpassPorCodigo(String codigo) {
        for (int i = 0; i < contador; i++) {
            if (quickpasses[i].getCodigo().equals(codigo)) {
                quickpassesEliminados[contadorEliminados++] = quickpasses[i];
                quickpasses[i] = quickpasses[--contador]; // Mover el último al lugar del eliminado
                return true;
            }
        }
        return false;
    }

    public boolean eliminarQuickpassPorPlaca(String placa) {
        for (int i = 0; i < contador; i++) {
            if (quickpasses[i].getPlaca().equals(placa)) {
                quickpassesEliminados[contadorEliminados++] = quickpasses[i];
                quickpasses[i] = quickpasses[--contador]; // Mover el último al lugar del eliminado
                return true;
            }
        }
        return false;
    }

    public boolean validarCodigo(String codigo) {
        return codigo.matches("101\\d{7}");
    }

    public void visualizarQuickpasses() {
        StringBuilder sb = new StringBuilder("Quickpasses:\n");
        for (int i = 0; i < contador; i++) {
            sb.append(quickpasses[i].getCodigo()).append(" - ").append(quickpasses[i].isEstado() ? "Activo" : "Inactivo").append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    public void visualizarQuickpassesEliminados() {
        StringBuilder sb = new StringBuilder("Quickpasses Eliminados:\n");
        for (int i = 0; i < contadorEliminados; i++) {
            sb.append(quickpassesEliminados[i].getCodigo()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    public String consultarQuickpass(String codigo) {
        for (int i = 0; i < contador; i++) {
            if (quickpasses[i].getCodigo().equals(codigo)) {
                return quickpasses[i].isEstado() ? "Aceptado" : "Rechazado";
            }
        }
        return "Rechazado";
    }

    public boolean cambiarEstadoQuickpass(String codigo, boolean nuevoEstado) {
        for (int i = 0; i < contador; i++) {
            if (quickpasses[i].getCodigo().equals(codigo)) {
                if (nuevoEstado) {
                    quickpasses[i].activar();
                } else {
                    quickpasses[i].inactivar();
                }
                return true;
            }
        }
        return false;
    }

    // Métodos agregados para contar Quickpasses activos, inactivos y eliminados

    public int contarQuickpassesActivos() {
        int count = 0;
        for (int i = 0; i < contador; i++) {
            if (quickpasses[i].isEstado()) { // Si está activo
                count++;
            }
        }
        return count;
    }

    public int contarQuickpassesInactivos() {
        int count = 0;
        for (int i = 0; i < contador; i++) {
            if (!quickpasses[i].isEstado()) { // Si no está activo, está inactivo
                count++;
            }
        }
        return count;
    }

    public int contarQuickpassesEliminados() {
        return contadorEliminados; // Cuenta los Quickpasses eliminados
    }
}
