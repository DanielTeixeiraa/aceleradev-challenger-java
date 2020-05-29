package com.daniel.aceleradev.daniel.service;

import org.springframework.stereotype.Service;

@Service
public class CriptografiaService {

	public String criptografar(int chave, String json) {
		StringBuffer jsonFinal = new StringBuffer();

		for (int i = 0; i < json.length(); i++) {
			if (Character.isLetter(json.charAt(i))) {
				if (Character.isUpperCase(json.charAt(i))) {
					char ch = (char) (((int) json.charAt(i) + chave - 65) % 26 + 65);
					jsonFinal.append(ch);
				} else {
					char ch = (char) (((int) json.charAt(i) + chave - 97) % 26 + 97);
					jsonFinal.append(ch);
				}
			} else {
				jsonFinal.append(json.charAt(i));
			}
		}
		return jsonFinal.toString();
	}

	public String decriptografia(int chave, String json) {
		chave = 26 - chave;
		return criptografar(chave, json);
	}
}
