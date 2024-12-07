package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Robert {
    private JButton btnConsultar;
    private JTextArea txtPeticion;
    private JTextArea txtRespuesta;
    private JLabel lblCargando;
    private JPanel Robert;
    private JButton btnVolver;
    private JScrollPane scrollPaneRespuesta; // Usar el JScrollPane ya definido en el .form

    public Robert(JFrame frame) {
        // Configura el área de texto de respuesta
        txtRespuesta.setLineWrap(true); // Ajuste de línea habilitado para evitar texto horizontal muy largo
        txtRespuesta.setWrapStyleWord(true); // Ajuste de línea por palabra para mejor legibilidad
        txtRespuesta.setEditable(false); // Hace que el área de texto sea solo de lectura
        scrollPaneRespuesta.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPaneRespuesta.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        btnConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mostrar mensaje de carga
                lblCargando.setText("Pensando, espere un momento por favor...");

                // Capturar la pregunta del usuario
                String preguntaUsuario = txtPeticion.getText();
                Personalidad personalidad = new Personalidad();

                // Crear una instancia de PeticionesOllama para hacer la consulta
                PeticionesOllama peticiones = new PeticionesOllama();

                // Hilo para realizar la consulta sin bloquear la interfaz gráfica
                new Thread(() -> {
                    // Realizar la consulta y obtener la respuesta
                    String respuesta = peticiones.realizarConsulta(personalidad.getDescripcion(), preguntaUsuario);

                    // Actualizar el área de respuesta en el hilo de la interfaz
                    SwingUtilities.invokeLater(() -> {
                        txtRespuesta.setText(respuesta != null ? respuesta : "No se pudo obtener una respuesta.");
                        lblCargando.setText(""); // Limpiar el mensaje de carga
                    });
                }).start();
            }
        });

        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new PrincipalGUI(frame).getPanel());
                frame.revalidate();
            }
        });
    }

    public JPanel getPanel() {
        return Robert;
    }
}
