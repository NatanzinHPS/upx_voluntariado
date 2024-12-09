package com.upxvoluntariado.sistema_voluntariado.Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class TelaCadastroV extends JFrame {
    private JTextField nomeField;
    private JTextField cpfField;
    private JTextField emailField;
    private JTextField telefoneField;
    private JPasswordField senhaField;
    private JTextField dataNascimentoField; 

    public TelaCadastroV() {
        setTitle("Cadastro de Voluntário");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(7, 2));

        panel.add(new JLabel("Nome Completo: "));
        nomeField = new JTextField();
        panel.add(nomeField);

        panel.add(new JLabel("CPF:"));
        cpfField = new JTextField();
        panel.add(cpfField);

        panel.add(new JLabel("Email:"));
        emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("Telefone:"));
        telefoneField = new JTextField();
        panel.add(telefoneField);

        panel.add(new JLabel("Senha:"));
        senhaField = new JPasswordField();
        panel.add(senhaField);

        panel.add(new JLabel("Data de Nascimento yyyy-MM-dd:"));
        dataNascimentoField = new JTextField();
        panel.add(dataNascimentoField);

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
        String cpf = cpfField.getText();
        String email = emailField.getText();
        String telefone = telefoneField.getText();
        String senha = new String(senhaField.getPassword());
        String dataNascimento = dataNascimentoField.getText();

        if (nome.isEmpty() || cpf.isEmpty() || email.isEmpty() || telefone.isEmpty() || senha.isEmpty() || dataNascimento.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios!");
            return;
        }

        try {
            URL url = new URL("http://localhost:8080/auth/cadastro");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);


            String jsonInputString = String.format(
                "{\"nome\": \"%s\", \"cpf\": \"%s\", \"email\": \"%s\", \"telefone\": \"%s\", \"senha\": \"%s\", \"dataNascimento\": \"%s\"}",
                nome, cpf, email, telefone, senha, dataNascimento
            );


            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
                JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!");
                new TelaLoginV().setVisible(true);
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


