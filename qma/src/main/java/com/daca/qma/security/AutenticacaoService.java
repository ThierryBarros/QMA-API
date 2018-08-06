package com.daca.qma.security;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import com.daca.qma.aluno.Aluno;
import com.daca.qma.aluno.AlunoService;
import com.daca.qma.exceptions.FailedAuthenticationException;
import com.daca.qma.security.UserCredentials;

@Service
public class AutenticacaoService {
    
    
    @Autowired
    private AlunoService alunoService;

    public User authenticate(UserCredentials credentials) {
        Aluno aluno = alunoService.encontrarAluno(credentials.getUsername());
        
        if (BCrypt.checkpw(credentials.getPassword(), aluno.getPassword()))
        	return  new User(aluno.getUsername(),aluno.getPassword(),getAuthority(aluno));
        
        throw new FailedAuthenticationException();
    }
    
 
	private Set<SimpleGrantedAuthority> getAuthority(Aluno user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRoles().forEach(role -> {
		
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		});
		return authorities;
	
	}

    public String tokenFor(User user) {
    	ZonedDateTime expiration = ZonedDateTime.now(ZoneOffset.UTC).plus(SecurityConstants.EXPIRATION_TIME,ChronoUnit.MILLIS);
        String token = Jwts.builder()
                .setSubject(user.getUsername())
                .setExpiration(Date.from(expiration.toInstant()))
                .signWith(SignatureAlgorithm.HS256,SecurityConstants.SECRET)
                .compact();
        return token;
		
    }

    public User getUserFromToken(String token) {
         Jws<Claims> claims = Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws(token);
         Aluno aluno = alunoService.encontrarAluno((claims.getBody().getSubject().toString()));
         return  new User(aluno.getUsername(),aluno.getPassword(),getAuthority(aluno));
        
    }


	public UserDetails getUser(String username) {
		Aluno aluno = alunoService.encontrarAluno(username);
		
        return  new User(aluno.getUsername(),aluno.getPassword(),getAuthority(aluno));
	}

}
