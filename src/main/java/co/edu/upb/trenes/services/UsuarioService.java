package co.edu.upb.trenes.services;

import co.edu.upb.trenes.models.usuarios.Usuario;
import co.edu.upb.trenes.repositories.impl.UsuarioJsonRepository;

public class UsuarioService {
    private final UsuarioJsonRepository usuarioRepository = new UsuarioJsonRepository();

    public Usuario actualizarPerfilBasico(Usuario usuario, String nombre) {
        usuario.setNombre(nombre);
        return usuarioRepository.update(usuario);
    }
}
