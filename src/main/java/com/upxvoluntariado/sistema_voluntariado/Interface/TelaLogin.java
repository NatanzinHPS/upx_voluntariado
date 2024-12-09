package com.upxvoluntariado.sistema_voluntariado.Interface;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

public class TelaLogin extends JFrame {
        public TelaLogin(){
        setTitle("Escolha o tipo de Login!");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel lpanel = new JPanel(new GridLayout());

        JButton loginvButton = new JButton("Voluntario");
        JButton loginoButton = new JButton("OSC");
        JButton voltarButton = new JButton("Voltar");

        loginvButton.addActionListener(e -> abrirloginV());
        loginoButton.addActionListener(e -> abrirloginO());
        voltarButton.addActionListener(e -> {new TelaInicial().setVisible(true);dispose();});

        lpanel.add(loginvButton);
        lpanel.add(loginoButton);
        lpanel.add(voltarButton);

        add(lpanel);
    }

    private void abrirloginV(){
        TelaLoginV telaloginV = new TelaLoginV();
        telaloginV.setVisible(true);
        dispose();
    }

    private void abrirloginO(){
        TelaLoginO telaloginO = new TelaLoginO();
        telaloginO.setVisible(true);
        dispose();
    }
}
