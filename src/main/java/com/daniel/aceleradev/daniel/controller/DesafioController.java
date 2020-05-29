package com.daniel.aceleradev.daniel.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daniel.aceleradev.daniel.service.FileService;
import com.daniel.aceleradev.daniel.service.DesafioService;

@RestController
@RequestMapping(value = "aceleradev", produces = MediaType.APPLICATION_JSON_VALUE)
public class DesafioController {

	@Value("${token}")
	private String token;

	@Autowired
	private DesafioService desafioService;
	@Autowired
	private FileService fileService;

	@PostMapping(value = "/enviarjson")
	public String desafio() throws IOException, NoSuchAlgorithmException {
		return fileService.desafio(token);
	}

	@GetMapping(value = "/pegarjson")
	public String getChallengeData() throws IOException {
		return desafioService.pegarJson(token);
	}

}
