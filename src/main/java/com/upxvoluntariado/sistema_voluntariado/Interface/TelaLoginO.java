package com.upxvoluntariado.sistema_voluntariado.Interface;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class TelaLoginO extends JFrame {
    private JTextField emailField;
    private JPasswordField senhaField;

    public TelaLoginO() {
        setTitle("Login");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel lvpanel = new JPanel(new GridLayout(3, 2));

        lvpanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        lvpanel.add(emailField);

        lvpanel.add(new JLabel("Senha:"));
        senhaField = new JPasswordField();
        lvpanel.add(senhaField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(this::realizarLogin);
        lvpanel.add(loginButton);

        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(e -> {
            new TelaLogin().setVisible(true);
            dispose();
        });
        lvpanel.add(voltarButton);

        add(lvpanel);
    }

    private void realizarLogin(ActionEvent e) {
        String email = emailField.getText();
        String senha = new String(senhaField.getPassword());

        if (email.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios!");
            return;
        }

        try {
            URL url = new URL("http://localhost:8080/auth/login/osc");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String jsonInputString = String.format(
                "{\"email\": \"%s\", \"senha\": \"%s\"}",
                email, senha
            );

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                JOptionPane.showMessageDialog(this, "Login bem-sucedido!");
                new Tela().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Email ou senha incorretos.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao se conectar com o servidor!");
        }
    }
}