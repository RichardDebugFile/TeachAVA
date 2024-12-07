package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class PrincipiosBasicos {
    private JTextArea txtRobert;
    private JTextArea txtRespuesta;
    private JButton btnOpcion1;
    private JButton btnOpcion2;
    private JButton btnOpcion3;
    private JButton btnOpcion4;
    private JButton btnPrueba;
    private JPanel panelPrincipiosBasicos;
    private JButton btnSalir;

    // Mapa de respuestas reales y pila para almacenar respuestas consultadas
    private Map<String, String> respuestasReales;
    private Stack<String> respuestasConsultadas;
    private PeticionesOllama peticiones;
    private Personalidad personalidad;

    public PrincipiosBasicos(JFrame frame) {
        // Inicializar las respuestas reales y la pila para almacenar respuestas consultadas
        respuestasReales = new HashMap<>();
        respuestasConsultadas = new Stack<>();
        peticiones = new PeticionesOllama();
        personalidad = new Personalidad();

        // Respuestas reales para cada opción
        respuestasReales.put("Opción 1", "Las variables en Java son contenedores para almacenar valores de datos.");
        respuestasReales.put("Opción 2", "Los métodos en Java son bloques de código que realizan tareas específicas.");
        respuestasReales.put("Opción 3", "Las clases en Java son plantillas para crear objetos.");
        respuestasReales.put("Opción 4", "La herencia permite que una clase derive las propiedades de otra clase.");

        // Configurar acciones para los botones de opciones
        btnOpcion1.addActionListener(e -> mostrarRespuestaYOpinar("Opción 1"));
        btnOpcion2.addActionListener(e -> mostrarRespuestaYOpinar("Opción 2"));
        btnOpcion3.addActionListener(e -> mostrarRespuestaYOpinar("Opción 3"));
        btnOpcion4.addActionListener(e -> mostrarRespuestaYOpinar("Opción 4"));

        // Acción para el botón de prueba
        btnPrueba.addActionListener(e -> {
            if (respuestasConsultadas.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay respuestas consultadas para crear una prueba.");
            } else {
                JOptionPane.showMessageDialog(null, "Generando prueba basada en las respuestas consultadas...");
                generarPrueba();
            }
        });
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new PrincipalGUI(frame).getPanel());
                frame.revalidate();
            }
        });
    }

    // Metodo para mostrar respuesta real y obtener la opinión de Robert
    private void mostrarRespuestaYOpinar(String opcion) {
        // Obtener y mostrar la respuesta real en txtRespuesta
        String respuesta = respuestasReales.get(opcion);
        txtRespuesta.setText(respuesta != null ? respuesta : "No hay respuesta para esta opción.");

        // Almacenar la respuesta en la pila de respuestas consultadas
        if (respuesta != null) {
            respuestasConsultadas.push(respuesta);
        }

        // Enviar la respuesta a la IA para obtener la opinión de Robert
        txtRobert.setText("Robert está pensando en una opinión...");
        new Thread(() -> {
            String opinionRobert = peticiones.realizarConsulta(personalidad.getDescripcion(), "¿Qué opinas sobre esta explicación? " + respuesta);

            // Mostrar la opinión de Robert en txtRobert en el hilo de la interfaz
            SwingUtilities.invokeLater(() -> {
                txtRobert.setText(opinionRobert != null ? opinionRobert : "Robert no tiene una opinión en este momento.");
            });
        }).start();
    }

    // Metodo para obtener el panel de la interfaz gráfica
    public JPanel getPanel() {
        return panelPrincipiosBasicos;
    }

    // Metodo para generar la prueba basada en las respuestas consultadas
    private void generarPrueba() {
        // Aquí puedes implementar la lógica para generar una prueba utilizando las respuestas en `respuestasConsultadas`
        while (!respuestasConsultadas.isEmpty()) {
            String respuesta = respuestasConsultadas.pop();
            System.out.println("Pregunta basada en la respuesta: " + respuesta); // Ejemplo; reemplázalo con tu lógica para generar la prueba
        }
    }
}
