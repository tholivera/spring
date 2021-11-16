package br.org.generation.lojagames.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.generation.lojagames.model.Usuario;
import br.org.generation.lojagames.model.UsuarioLogin;
import br.org.generation.lojagames.repository.UsuarioRepository;
import br.org.generation.lojagames.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class UsuarioController {
	
		@Autowired
	    UsuarioService usuarioService;
	    @Autowired
	    UsuarioRepository usuarioRepository;

	    @GetMapping("/all")
	    public ResponseEntity<List<Usuario>>getAll(){
	        return ResponseEntity.ok(usuarioRepository.findAll());
	    }

	    @PostMapping("/cadastrar")
	    public ResponseEntity<Usuario> postUsuario(@Valid @RequestBody Usuario usuario){
	        return usuarioService.cadastrarUsuario(usuario)
	                .map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
	                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	    }

	    @PostMapping("/logar")
	    public ResponseEntity<UsuarioLogin> login(@RequestBody Optional<UsuarioLogin> usuarioLogin){
	        return usuarioService.autenticarUsuario(usuarioLogin)
	                .map(resposta -> ResponseEntity.ok().body(resposta))
	                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	    }
	    @PutMapping("/atualizar")
	    public ResponseEntity<Usuario> putUsuario(@Valid @RequestBody Usuario usuario){
	        return usuarioService.atualizarUsuario(usuario)
	                .map(resposta -> ResponseEntity.ok().body(resposta))
	                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	    }
	}


