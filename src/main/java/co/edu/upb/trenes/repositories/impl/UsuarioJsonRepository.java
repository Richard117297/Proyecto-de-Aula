package co.edu.upb.trenes.repositories.impl;

import co.edu.upb.trenes.models.usuarios.Usuario;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;
import java.util.Optional;

public class UsuarioJsonRepository extends AbstractJsonRepository<Usuario> {
    public UsuarioJsonRepository() {
        super("usuarios.json", new TypeReference<List<Usuario>>() {}, Usuario::getId);
    }

    public Optional<Usuario> findByUsuario(String usuario) {
        return findAll().stream()
                .filter(item -> item.getUsuario() != null && item.getUsuario().equalsIgnoreCase(usuario))
                .findFirst();
    }
}
