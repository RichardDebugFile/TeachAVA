package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterGUI {
    private JTextField txtUsuarioNuevo;
    private JPasswordField pswContrasena;
    private JPasswordField pswContrasenaConfirmar;
    private JButton btnRegistrarse;
    private JButton btnVolver;
    private JPanel RegisterGUI;

    public RegisterGUI(JFrame frame) {
        // Acción al hacer clic en el botón "Registrarse"
        btnRegistrarse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = txtUsuarioNuevo.getText();
                String contrasena = new String(pswContrasena.getPassword());
                String contrasenaConfirmar = new String(pswContrasenaConfirmar.getPassword());

                // Verificar si las contraseñas coinciden
                if (!contrasena.equals(contrasenaConfirmar)) {
                    JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Insertar el nuevo usuario en la base de datos
                try (Connection connection = DatabaseConnection.getConnection()) {
                    if (connection != null) {
                        String sql = "INSERT INTO usuarios (nombre, contrasena) VALUES (?, ?)";
                        PreparedStatement statement = connection.prepareStatement(sql);
                        statement.setString(1, usuario);
                        statement.setString(2, contrasena);

                        int rowsInserted = statement.executeUpdate();
                        if (rowsInserted > 0) {
                            JOptionPane.showMessageDialog(null, "¡Registro exitoso!");
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Acción al hacer clic en el botón "Volver"
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cambiar al formulario de login
                frame.setContentPane(new LoginGUI(frame).getPanel());
                frame.revalidate();
            }
        });
    }

    // Metodo para obtener el JPanel principal
    public JPanel getPanel() {
        return RegisterGUI;
    }
}
