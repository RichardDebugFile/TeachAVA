package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrincipalGUI {
    private JPanel PrincipalGUI;
    private JButton btnConceptos;
    private JButton btnHolaMundo;
    private JButton btnBucles;
    private JButton btnDeclaracion;
    private JButton btnRobertIA;
    private JButton btnExamen;

    public PrincipalGUI(JFrame frame) {
        btnConceptos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new PrincipiosBasicos(frame).getPanel());
                frame.revalidate();
            }
        });

        btnHolaMundo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Aún en construcción");
            }
        });

        btnBucles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Aún en construcción");
            }
        });

        btnDeclaracion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Aún en construcción");
            }
        });

        btnRobertIA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new Robert(frame).getPanel());
                frame.revalidate();
            }
        });

        btnExamen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Aún en construcción");
            }
        });

    }

    public JPanel getPanel() {
        return PrincipalGUI;
    }
}
