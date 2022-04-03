package br.com.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.model.Usuario;
import br.com.springboot.repository.UsuarioRepository;


@RestController
public class GreetingsController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;


    @GetMapping("/listatodos")
    @ResponseBody //Retorna o resultado para o corpo da resposta
    public ResponseEntity<List<Usuario>> listaUsuarios() {
    	List<Usuario> lista = usuarioRepository.findAll();
    	
    	if (lista.isEmpty()) {
    		return new ResponseEntity<List<Usuario>>(lista, HttpStatus.NOT_FOUND);
    	}
    	
    	return new ResponseEntity<List<Usuario>>(lista, HttpStatus.OK);
    }  
    
    @PostMapping("/salvar")
    @ResponseBody
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){
    	Usuario user = usuarioRepository.save(usuario);
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    }
    
    @DeleteMapping("/deleta")
    @ResponseBody
    public ResponseEntity<String> delete(@RequestParam Long id) {
    	usuarioRepository.deleteById(id);
    	
    	return new ResponseEntity<String>("Usuário deletado com sucesso", HttpStatus.OK);
    }
    
    @GetMapping("/buscaruserid")
    @ResponseBody
    public ResponseEntity<Usuario> buscarUserId(@RequestParam(name = "idUser") Long idUser) {
    	Usuario user = usuarioRepository.findById(idUser).get();

    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    }
    
    @PutMapping("/atualizar")
    @ResponseBody
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario){
    	
    	if (usuario.getId() == null) {
    		return new ResponseEntity<String>("Id não informado", HttpStatus.NOT_FOUND);
    	}
    	
    	Usuario user = usuarioRepository.saveAndFlush(usuario);
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    }
    
    @GetMapping("/buscarPorNome")
    @ResponseBody
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name = "nome") String nome) {
    	List<Usuario> usuario = usuarioRepository.buscarPorNome(nome.trim().toUpperCase());

    	return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
    }
    
}
