/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author andyq
 */
import javax.swing.JOptionPane;

public class ArchivoHistorial {

    public static String ARCHIVO_HISTORIAL = "Historial.txt";

    public static void registrarOperacion(String accion, String codigo, String placa, String filial, String resultado) {
        String fechaHora = obtenerFechaHora();
        String registro = "Acción: " + accion + ", Código: " + codigo + ", Placa: " + placa
                + ", Filial: " + filial + ", Resultado: " + resultado + ", Fecha: " + fechaHora + "\n";

        escribirEnArchivo(registro);
    }

    public static void consultarPorFilial(String filial) {
        try {
            String contenido = leerArchivo();
            StringBuilder resultado = new StringBuilder("Accesos para la filial: " + filial + "\n");

            boolean encontrado = false;
            for (String linea : contenido.split("\n")) {
                if (linea.contains("Filial: " + filial)) {
                    resultado.append(linea).append("\n");
                    encontrado = true;
                }
            }

            if (!encontrado) {
                resultado.append("No se encontraron registros para la filial especificada.");
            }

            JOptionPane.showMessageDialog(null, resultado.toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo: " + e.getMessage());
        }
    }

    public static void consultarPorCodigoOPlaca(String codigoOPlaca) {
        try {
            String contenido = leerArchivo();
            StringBuilder resultado = new StringBuilder("Accesos para código/placa: " + codigoOPlaca + "\n");

            boolean encontrado = false;
            for (String linea : contenido.split("\n")) {
                if (linea.contains("Código: " + codigoOPlaca) || linea.contains("Placa: " + codigoOPlaca)) {
                    resultado.append(linea).append("\n");
                    encontrado = true;
                }
            }

            if (!encontrado) {
                resultado.append("No se encontraron registros para el código o placa especificada.");
            }

            JOptionPane.showMessageDialog(null, resultado.toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo: " + e.getMessage());
        }
    }

    public static void consultarPorRangoDeFechas(String fechaInicio, String fechaFin) {
        try {
            String contenido = leerArchivo();
            StringBuilder resultado = new StringBuilder("Accesos entre " + fechaInicio + " y " + fechaFin + "\n");

            boolean encontrado = false;
            for (String linea : contenido.split("\n")) {
                if (linea.contains("Fecha: ")) {
                    String fecha = extraerFechaDeLinea(linea);
                    if (fechaEnRango(fecha, fechaInicio, fechaFin)) {
                        resultado.append(linea).append("\n");
                        encontrado = true;
                    }
                }
            }

            if (!encontrado) {
                resultado.append("No se encontraron registros en el rango de fechas especificado.");
            }

            JOptionPane.showMessageDialog(null, resultado.toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo: " + e.getMessage());
        }
    }

    private static void escribirEnArchivo(String texto) {
        try {
            java.io.FileWriter writer = new java.io.FileWriter(ARCHIVO_HISTORIAL, true);
            writer.write(texto);
            writer.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al escribir en el archivo: " + e.getMessage());
        }
    }

    private static String leerArchivo() {
        StringBuilder contenido = new StringBuilder();
        try {
            java.io.FileReader reader = new java.io.FileReader(ARCHIVO_HISTORIAL);
            int caracter;
            while ((caracter = reader.read()) != -1) {
                contenido.append((char) caracter);
            }
            reader.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo: " + e.getMessage());
        }
        return contenido.toString();
    }

    private static String extraerFechaDeLinea(String linea) {
        int indiceFecha = linea.lastIndexOf("Fecha: ");
        if (indiceFecha != -1) {
            return linea.substring(indiceFecha + 7).trim();
        }
        return "";
    }

    private static boolean fechaEnRango(String fecha, String inicio, String fin) {
        return fecha.compareTo(inicio) >= 0 && fecha.compareTo(fin) <= 0;
    }

    private static String obtenerFechaHora() {
        long millis = System.currentTimeMillis();
        java.util.Date fecha = new java.util.Date(millis);
        return fecha.toString();
    }

    public static void visualizarQuickpass() {
        try {
             String contenido = leerArchivo();
            if (contenido.isEmpty()) {
                JOptionPane.showMessageDialog(null, "El archivo está vacío.");
            } else {
                JOptionPane.showMessageDialog(null, "Contenido del archivo:\n" + contenido);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo: " + e.getMessage());
        }
    }
    
        public static void ssssssssssss() {
        try {
             String contenido = leerArchivo();
            if (contenido.isEmpty()) {
                JOptionPane.showMessageDialog(null, "El archivo está vacío.");
            } else {
                JOptionPane.showMessageDialog(null, "Contenido del archivo:\n" + contenido);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo: " + e.getMessage());
        }
    }

}
