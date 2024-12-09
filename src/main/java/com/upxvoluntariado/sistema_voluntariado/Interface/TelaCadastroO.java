package com.upxvoluntariado.sistema_voluntariado.Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class TelaCadastroO extends JFrame {
    private JTextField nomeField;
    private JTextField cnpjField;
    private JTextField emailField;
    private JPasswordField senhaField;

    public TelaCadastroO() {
        setTitle("Cadastro de OSC");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 2));

        panel.add(new JLabel("Nome da OSC: "));
        nomeField = new JTextField();
        panel.add(nomeField);

        panel.add(new JLabel("CNPJ:"));
        cnpjField = new JTextField();
        panel.add(cnpjField);

        panel.add(new JLabel("Email:"));
        emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("Senha:"));
        senhaField = new JPasswordField();
        panel.add(senhaField);

        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.addActionListener(this::realizarCadastro);
        panel.add(cadastrarButton);

        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(e -> {
            new TelaCadastro().setVisible(true);
            dispose();
        });
        panel.add(voltarButton);

        add(panel);
    }

    private void realizarCadastro(ActionEvent e) {
        String nome = nomeField.getText();
        String cnpj = cnpjField.getText();
        String email = emailField.getText();
        String senha = new String(senhaField.getPassword());

        if (nome.isEmpty() || cnpj.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios!");
            return;
        }

        try {
            URL url = new URL("http://localhost:8080/auth/cadastro/osc"); 
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String jsonInputString = String.format(
                "{\"nome\": \"%s\", \"cnpj\": \"%s\", \"email\": \"%s\", \"senha\": \"%s\"}",
                nome, cnpj, email, senha
            );

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
                JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!");
                new TelaLoginO().setVisible(true);  
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar: " + responseCode);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao se conectar com o servidor ou dados inválidos!");
        }
    }
}
