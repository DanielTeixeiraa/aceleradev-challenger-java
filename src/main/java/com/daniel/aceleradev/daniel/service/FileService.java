package com.daniel.aceleradev.daniel.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.aceleradev.daniel.model.Desafio;

@Service
public class FileService {
	public static final String answerFilePath = "C:\\Users\\User\\Documents\\workspace-spring-tool-suite-4-4.6.1.RELEASE\\aceleradev.daniel\\src\\main\\file\\answer\\answer.json";

	@Autowired
	private CriptografiaService cript;
	@Autowired
	private ShaService sha1;
	@Autowired
	private DesafioService desafioService;

	private JSONObject pegarJson(String token) throws IOException, NoSuchAlgorithmException {
		String requestText = desafioService.pegarJson( token );
		JSONObject jsonObject = new JSONObject(requestText);
		String decryptText = cript.decriptografia( jsonObject.getInt( "numero_casas" ), jsonObject.getString( "cifrado" ) );
		String sha1Code = sha1.transform( decryptText );
		jsonObject.put( "decifrado", decryptText );
		jsonObject.put( "resumo_criptografico", sha1Code );
		return jsonObject;
	}

	private void salvar(JSONObject jsonObject) throws IOException {
		File newFile = new File(
				answerFilePath );
		newFile.createNewFile();
		FileOutputStream fos = new FileOutputStream( newFile );
		fos.write( jsonObject.toString().getBytes() );
		fos.close();
	}
	
	
	private String enviarJson(String token) {
		Desafio client = new Desafio(token);
		return client.enviarJson( answerFilePath );
	}


	public String desafio(String token) throws IOException, NoSuchAlgorithmException {
		JSONObject jsonObject = pegarJson(token);		
		salvar( jsonObject );	
		return enviarJson( token );
	}
}
