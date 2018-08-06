package com.daca.qma.security;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daca.qma.security.AuthenticatedUser;

@CrossOrigin
@RestController
@RequestMapping()
public class AutenticacaoController {
	private final static Logger logger = LoggerFactory.getLogger(AutenticacaoController.class);

    @Autowired
    private AutenticacaoService service;

    @PostMapping(value = "/logar")
    public ResponseEntity<AuthenticatedUser> logar(@Valid @RequestBody UserCredentials credentials) {
    	logger.info(String.format("User with the %s registration is trying to authenticate.", credentials.getUsername()));
    	
        User user = this.service.authenticate(credentials);
        String token = this.service.tokenFor(user);

        return ResponseEntity.ok(new AuthenticatedUser(token, user));
    }
    
    @PreAuthorize("hasRole('ALUNO')")
    @PostMapping(value = "/deslogar")
    public String deslogar(@PathVariable(value = "id") Long id){
        return "sair";
    }
}
