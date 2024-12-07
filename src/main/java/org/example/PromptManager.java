package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class PromptManager {
    private String personalidad;
    private List<String> historialRespuestas;

    // Constructor para cargar la personalidad de la IA desde el archivo de texto
    public PromptManager(String rutaArchivo) {
        this.personalidad = cargarPersonalidadDesdeArchivo(rutaArchivo);
        this.historialRespuestas = new LinkedList<>(); // Inicializamos la lista enlazada
    }

    // Metodo para leer la personalidad de la IA desde un archivo
    private String cargarPersonalidadDesdeArchivo(String rutaArchivo) {
        StringBuilder contenido = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "No se pudo cargar la personalidad de la IA.";
        }
        return contenido.toString().trim();
    }

    // Metodo para construir el prompt completo combinando personalidad y pregunta del usuario
    public String construirPrompt(String promptUsuario) {
        return personalidad + "\n\n" + "Pregunta del usuario: " + promptUsuario;
    }

    // Metodo para almacenar una respuesta de "Robert"
    public void almacenarRespuesta(String respuesta) {
        historialRespuestas.add(respuesta);
    }

    // Metodo para obtener todas las respuestas almacenadas
    public List<String> obtenerHistorialRespuestas() {
        return new LinkedList<>(historialRespuestas); // Devuelve una copia para evitar modificaciones externas
    }

    // Metodo para mostrar el historial en consola (opcional)
    public void mostrarHistorial() {
        System.out.println("Historial de respuestas de Robert:");
        for (String respuesta : historialRespuestas) {
            System.out.println("- " + respuesta);
        }
    }
}
