package org.example;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ConexionOllama {

    // Metodo para realizar la conexión y devolver la respuesta
    public String obtenerRespuesta(String modelName, String promptText) throws IOException {
        String apiUrl = "http://localhost:11434/api/generate";
        HttpURLConnection conn = null;
        StringBuilder response = new StringBuilder();

        try {
            URL url = new URL(apiUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);

            // Crear el cuerpo JSON de la solicitud
            String jsonInputString = String.format(
                    "{ \"model\": \"%s\", \"prompt\": \"%s\", \"stream\": false }",
                    modelName, promptText
            );

            // Escribir el cuerpo JSON en la solicitud
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Obtener el código de respuesta
            int responseCode = conn.getResponseCode();
            System.out.println("Código de respuesta: " + responseCode);

            // Verificar que la respuesta es exitosa (código 200)
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Leer el cuerpo de la respuesta
                try (BufferedReader in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8)
                )) {
                    String line;
                    while ((line = in.readLine()) != null) {
                        response.append(line);
                    }
                }

                // Analizar la respuesta JSON y extraer el campo "response"
                JSONObject jsonResponse = new JSONObject(response.toString());
                return jsonResponse.optString("response", "Campo 'response' no encontrado.");
            } else {
                System.err.println("Error en la solicitud a Ollama, código de respuesta: " + responseCode);
                return null;
            }

        } catch (IOException e) {
            System.err.println("Excepción de IO al intentar conectar con Ollama: " + e.getMessage());
            e.printStackTrace();
            return null;

        } finally {
            // Cerrar la conexión
            if (conn != null) {
                conn.disconnect();
            }
        }
    }
}