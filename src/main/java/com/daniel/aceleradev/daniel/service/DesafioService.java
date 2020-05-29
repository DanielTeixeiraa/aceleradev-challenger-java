package com.daniel.aceleradev.daniel.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.daniel.aceleradev.daniel.model.Desafio;

@Service
public class DesafioService {

	private static final String request = "C:\\Users\\User\\Documents\\workspace-spring-tool-suite-4-4.6.1.RELEASE\\aceleradev.daniel\\src\\main\\file\\request\\answer.json";
	private static final String answerFilePath = "C:\\Users\\User\\Documents\\workspace-spring-tool-suite-4-4.6.1.RELEASE\\aceleradev.daniel\\src\\main\\file\\answer\\answer.json";

	public String pegarJson(String token) {

		Desafio client = new Desafio(token);
		String json;
		File file = new File(request);
		json = client.pegarJson();

		if (!file.exists()) {
			modificar(json, file);
		}
		return json;
	}

	private void salvar(MultipartFile answer) throws IOException {
		File newFile = new File(answerFilePath);
		newFile.createNewFile();
		FileOutputStream fileOut = new FileOutputStream(newFile);
		fileOut.write(answer.getBytes());
		fileOut.close();
	}

	private void modificar(String json, File file) {
		FileOutputStream newFile;
		try {
			newFile = new FileOutputStream(file);
			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(newFile));
			bufferedWriter.write(json);
			bufferedWriter.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String enviar(String token, MultipartFile answer) throws IOException {
		Desafio client = new Desafio(token);
		salvar(answer);
		return client.enviarJson(answerFilePath);
	}

}
