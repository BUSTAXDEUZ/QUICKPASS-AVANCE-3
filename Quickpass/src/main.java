/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author Fabia
 */
import javax.swing.JOptionPane; // Asegúrate de que esta línea esté presente

public class main {

    public static void main(String[] args) {

        GestorQuickpass gestor = new GestorQuickpass(100);
        ArchivoHistorial registro = new ArchivoHistorial();

        boolean continuar = true;

        while (continuar) {
            String[] opciones = {"Agregar Quickpass", "Eliminar Quickpass por Código", "Eliminar Quickpass por Placa",
                "Visualizar Quickpasses", "Visualizar Quickpasses Eliminados",
                "Consultar Quickpass", "Consultar por Filial", "Consultar por Rango de Fechas",
                "Consultar por Código o Placa", "Cambiar Estado Quickpass", "Total de accesos registrados", " Total de accesos por filial",
                " Total de quickpass registrados", "Total de quickpass Activos e Inactivos ", "Total de quickpass eliminados ", "Salir"};
            int opcion = JOptionPane.showOptionDialog(null, "Seleccione una opción", "Gestión Quickpass",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

            switch (opcion) {
                case 0:
                    String filial = JOptionPane.showInputDialog("Ingrese la Filial:");
                    String codigo = JOptionPane.showInputDialog("Ingrese el Código (debe iniciar con 101 y tener 10 dígitos):");
                    String placa = JOptionPane.showInputDialog("Ingrese la Placa:");
                    if (gestor.agregarQuickpass(filial, codigo, placa)) {
                        JOptionPane.showMessageDialog(null, "Quickpass agregado exitosamente.");
                        ArchivoHistorial.registrarOperacion("Agregar", codigo, placa, filial, "Aceptado");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al agregar Quickpass. Verifique el código.");
                        ArchivoHistorial.registrarOperacion("Agregar", codigo, placa, filial, "Rechazado");
                    }
                    break;

                case 1:
                    String codigoEliminar = JOptionPane.showInputDialog("Ingrese el Código a eliminar:");
                    if (gestor.eliminarQuickpassPorCodigo(codigoEliminar)) {
                        JOptionPane.showMessageDialog(null, "Quickpass eliminado exitosamente.");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró el Quickpass con ese código.");
                    }
                    break;

                case 2:
                    String placaEliminar = JOptionPane.showInputDialog("Ingrese la Placa a eliminar:");
                    if (gestor.eliminarQuickpassPorPlaca(placaEliminar)) {
                        JOptionPane.showMessageDialog(null, "Quickpass eliminado exitosamente.");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró el Quickpass con esa placa.");
                    }
                    break;

                case 3:
                    gestor.visualizarQuickpasses();
                    break;

                case 4:
                    gestor.visualizarQuickpassesEliminados();
                    break;

                case 5:
                    String codigoConsultar = JOptionPane.showInputDialog("Ingrese el Código a consultar:");
                    String resultado = gestor.consultarQuickpass(codigoConsultar);
                    JOptionPane.showMessageDialog(null, resultado);
                    break;

                case 6: // Consultar por filial
                    String filialConsulta = JOptionPane.showInputDialog("Ingrese la Filial a consultar:");
                    ArchivoHistorial.consultarPorFilial(filialConsulta);
                    break;

                case 7: // Consultar por rango de fechas
                    String fechaInicio = JOptionPane.showInputDialog("Ingrese la fecha de inicio (formato: dd/MM/yyyy):");
                    String fechaFin = JOptionPane.showInputDialog("Ingrese la fecha de fin (formato: dd/MM/yyyy):");
                    ArchivoHistorial.consultarPorRangoDeFechas(fechaInicio, fechaFin);
                    break;

                case 8: // Consultar por código o placa
                    String codigoOPlaca = JOptionPane.showInputDialog("Ingrese el Código o Placa a consultar:");
                    ArchivoHistorial.consultarPorCodigoOPlaca(codigoOPlaca);
                    break;

                case 9: // Cambiar Estado Quickpass
                    String codigoEstado = JOptionPane.showInputDialog("Ingrese el Código del Quickpass:");
                    String[] estados = {"Activo", "Inactivo"};
                    int estadoSeleccionado = JOptionPane.showOptionDialog(null, "Seleccione el nuevo estado:", "Cambiar Estado",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, estados, estados[0]);
                    boolean nuevoEstado = (estadoSeleccionado == 0); // Activo si 0, Inactivo si 1

                    if (gestor.cambiarEstadoQuickpass(codigoEstado, nuevoEstado)) {
                        JOptionPane.showMessageDialog(null, "Estado cambiado exitosamente.");
                        ArchivoHistorial.registrarOperacion("Cambiar Estado", codigoEstado, "", "", nuevoEstado ? "Aceptado" : "Rechazado");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró el Quickpass con ese código.");
                    }
                    break;

                case 10: // total de accesos registrados

                    ArchivoHistorial.visualizarQuickpass();

                    break;

                case 11: // total de accesos por filial
                    String Filial = JOptionPane.showInputDialog("Ingrese el Código o Placa a consultar:");
                    ArchivoHistorial.consultarPorFilial(Filial);
                    break;

                case 12: // total de quickpass registrados
                    ArchivoHistorial.visualizarQuickpass();
                    break;

                case 13: // Total de Quickpass Activos e Inactivos
                    int activos = gestor.contarQuickpassesActivos();   // Método que cuenta los activos
                    int inactivos = gestor.contarQuickpassesInactivos(); // Método que cuenta los inactivos
                    String mensajeActivosInactivos = "Total de Quickpasses Activos: " + activos + "\n"
                            + "Total de Quickpasses Inactivos: " + inactivos;
                    JOptionPane.showMessageDialog(null, mensajeActivosInactivos);
                    break;

                case 14: // Total de Quickpass Eliminados
                    int eliminados = gestor.contarQuickpassesEliminados(); // Método que cuenta los eliminados
                    String mensajeEliminados = "Total de Quickpasses Eliminados: " + eliminados;
                    JOptionPane.showMessageDialog(null, mensajeEliminados);
                    break;

                case 15: // salida
                    System.exit(0);
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
                    break;
            }
        }
    }
}
