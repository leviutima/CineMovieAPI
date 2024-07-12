package cinemovie.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cinemovie.api.usuario.DadosCadastrados;
import cinemovie.api.usuario.Usuario;
import cinemovie.api.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioRepository repository;


    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody DadosCadastrados dados) {
        repository.save(new Usuario(dados));
    }
}
