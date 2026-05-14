package co.edu.upb.trenes.services;

import co.edu.upb.trenes.exceptions.ValidationException;
import co.edu.upb.trenes.models.usuarios.Usuario;
import co.edu.upb.trenes.repositories.impl.UsuarioJsonRepository;

import java.util.Optional;

public class AuthService {
    private final UsuarioJsonRepository usuarioRepository;

    public AuthService() {
        this(new UsuarioJsonRepository());
    }

    public AuthService(UsuarioJsonRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Optional<Usuario> login(String usuario, String contrasena) {
        if (usuario == null || usuario.isBlank() || contrasena == null || contrasena.isBlank()) {
            throw new ValidationException("Usuario y contrasena son obligatorios.");
        }
        return usuarioRepository.findByUsuario(usuario)
                .filter(Usuario::isActivo)
                .filter(item -> contrasena.equals(item.getContrasena()));
    }
}
