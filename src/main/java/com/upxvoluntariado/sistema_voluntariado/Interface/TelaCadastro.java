package com.upxvoluntariado.sistema_voluntariado.Interface;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class TelaCadastro extends JFrame {
    public TelaCadastro(){
        setTitle("Escolha o tipo de Cadastro!");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel cpanel = new JPanel(new GridLayout());

        JButton cadastrovButton = new JButton("Voluntario");
        JButton cadastrooButton = new JButton("OSC");
        JButton voltarButton = new JButton("Voltar");

        cadastrovButton.addActionListener(e -> abrirCadastroV());
        cadastrooButton.addActionListener(e -> abrirCadastroO());
        voltarButton.addActionListener(e -> {new TelaInicial().setVisible(true);dispose();});

        cpanel.add(cadastrovButton);
        cpanel.add(cadastrooButton);
        cpanel.add(voltarButton);

        add(cpanel);
    }

    private void abrirCadastroV(){
        TelaCadastroV telaCadastroV = new TelaCadastroV();
        telaCadastroV.setVisible(true);
        dispose();
    }

    private void abrirCadastroO(){
        TelaCadastroO telaCadastroO = new TelaCadastroO();
        telaCadastroO.setVisible(true);
        dispose();
    }
}
