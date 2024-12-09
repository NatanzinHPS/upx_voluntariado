package com.upxvoluntariado.sistema_voluntariado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.upxvoluntariado.sistema_voluntariado.Interface.TelaInicial;

@SpringBootApplication
public class SistemaVoluntariadoApplication {

	public static void main(String[] args) {
		new Thread(() -> SpringApplication.run(SistemaVoluntariadoApplication.class, args)).start();

		java.awt.EventQueue.invokeLater(() -> {
			new TelaInicial().setVisible(true);
		});
	}

	
}
