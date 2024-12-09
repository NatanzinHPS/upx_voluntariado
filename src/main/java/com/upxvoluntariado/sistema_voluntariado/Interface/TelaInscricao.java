package com.upxvoluntariado.sistema_voluntariado.Interface;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TelaInscricao extends JFrame {

        private JTextField voluntarioField;
        private JTextField oscField;

        public TelaInscricao(){
            setTitle("Inscrição de Voluntário");
            setSize(400, 320);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            JPanel panel = new JPanel(new GridLayout(3, 2));

            panel.add(new JLabel("ID do Voluntário:"));
            voluntarioField = new JTextField();
            panel.add(voluntarioField);

            panel.add(new JLabel("ID da OSC:"));
            oscField = new JTextField();
            panel.add(oscField);

            JButton cadastrarButton = new JButton("Inscrever");
            cadastrarButton.addActionListener(this::realizarCadastro);
            panel.add(cadastrarButton);

            JButton voltarButton = new JButton("Voltar");
            voltarButton.addActionListener(e -> {
            new Tela().setVisible(true);
            dispose();
            });
            panel.add(voltarButton);

            add(panel);
        }

        private void realizarCadastro(ActionEvent e) {
        String idVoluntario = voluntarioField.getText();
        String idOsc = oscField.getText();

        if (idVoluntario.isEmpty() || idOsc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios!");
            return;
        }

        try {
            URL url = new URL("http://localhost:8080/inscricao/" + idVoluntario + "/" + idOsc);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
                JOptionPane.showMessageDialog(this, "Inscrição realizada com sucesso!");
                new Tela().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao se inscrever: " + responseCode);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao se conectar com o servidor ou dados inválidos!");
        }
    }
}
