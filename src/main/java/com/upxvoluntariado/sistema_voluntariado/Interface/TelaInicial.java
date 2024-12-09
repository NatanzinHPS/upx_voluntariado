package com.upxvoluntariado.sistema_voluntariado.Interface;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class TelaInicial extends JFrame {
    public TelaInicial(){
        setTitle("Tela Inicial!");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout());

        JButton cadastroButton = new JButton("Cadastro");
        JButton loginButton = new JButton("Login");
        JButton sairButton = new JButton("Sair");

        cadastroButton.addActionListener(e -> abrirCadastro());
        loginButton.addActionListener(e -> abrirLogin());
        sairButton.addActionListener(e -> System.exit(0));

        panel.add(cadastroButton);
        panel.add(loginButton);
        panel.add(sairButton);

        add(panel);
    }

    private void abrirCadastro(){
        TelaCadastro telaCadastro = new TelaCadastro();
        telaCadastro.setVisible(true);
        dispose();
    }

    private void abrirLogin(){
        TelaLogin telaLogin = new TelaLogin();
        telaLogin.setVisible(true);
        dispose();
    }
}
