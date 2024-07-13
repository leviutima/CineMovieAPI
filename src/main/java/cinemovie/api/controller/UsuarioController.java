package cinemovie.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cinemovie.api.loginRequest.LoginRequest;
import cinemovie.api.usuario.DadosCadastrados;
import cinemovie.api.usuario.Usuario;
import cinemovie.api.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody DadosCadastrados dados) {
        logger.info("Tentando cadastrar usuário com email: {}", dados.email());
        try {
            repository.save(new Usuario(dados));
            logger.info("Usuário cadastrado com sucesso.");
            return ResponseEntity.ok("Usuário cadastrado com sucesso.");
        } catch (Exception e) {
            logger.error("Erro ao cadastrar usuário", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar usuário");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        logger.info("Tentando login com email: {}", loginRequest.getEmail());
        Usuario usuario = repository.findByEmail(loginRequest.getEmail());
        if (usuario != null) {
            logger.info("Usuário encontrado: {}", usuario.getEmail());
            if (usuario.getSenha().equals(loginRequest.getSenha())) {
                logger.info("Login bem-sucedido para o email: {}", loginRequest.getEmail());
                return ResponseEntity.ok("Login successful");
            } else {
                logger.warn("Senha incorreta para o email: {}", loginRequest.getEmail());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
            }
        } else {
            logger.warn("Usuário não encontrado para o email: {}", loginRequest.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }
}
