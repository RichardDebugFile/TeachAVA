package org.example;

import java.io.IOException;

public class PeticionesOllama {
    private ConexionOllama conexion;
    private String modelName;

    public PeticionesOllama() {
        this.conexion = new ConexionOllama();
        this.modelName = "llama3.2"; // Define el modelo aquí o permite configurarlo en el constructor
    }

    public String realizarConsulta(String personalidadDescripcion, String preguntaUsuario) {
        // Construir el prompt concatenando la personalidad con la pregunta
        String promptText = personalidadDescripcion + " Pregunta del usuario: " + preguntaUsuario;

        try {
            // Obtener la respuesta de la IA usando el prompt completo
            return conexion.obtenerRespuesta(modelName, promptText);
        } catch (IOException e) {
            e.printStackTrace();
            return "Error al comunicarse con Robert.";
        }
    }
}
