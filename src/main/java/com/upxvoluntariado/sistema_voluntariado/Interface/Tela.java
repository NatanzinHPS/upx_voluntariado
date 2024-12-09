package com.upxvoluntariado.sistema_voluntariado.Interface;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Tela extends JFrame {
    public Tela(){
        setTitle("Bem vindo!");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout());

        JButton inscricaoButton = new JButton("Inscrever-se em OSC");
        JButton desconectarButton = new JButton("Desconectar");

        inscricaoButton.addActionListener(e -> abrirInscricao());
        desconectarButton.addActionListener(e -> {new TelaInicial().setVisible(true);dispose();});
    
        panel.add(inscricaoButton);
        panel.add(desconectarButton);

        add(panel);
    }

    private void abrirInscricao(){
        TelaInscricao telaInscricao = new TelaInscricao();
        telaInscricao.setVisible(true);
        dispose();
    }
}
