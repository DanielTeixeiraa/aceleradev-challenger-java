package com.daniel.aceleradev.daniel.model;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class Desafio {
	private String token;

	public Desafio(String token) {
		this.token = token;
	}

	
	public String pegarJson() {
		String url = "https://api.codenation.dev/v1/challenge/dev-ps/generate-data?token=" + token;
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
		return responseEntity.getBody();
	}

	public String enviarJson(String file) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("answer", new FileSystemResource(file));

		HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(body, headers);

		String url = "https://api.codenation.dev/v1/challenge/dev-ps/submit-solution?token=" + token;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, httpEntity, String.class);

		return responseEntity.getBody();
	}


}
