package co.edu.upb.trenes.repositories.impl;

import co.edu.upb.trenes.models.usuarios.Administrador;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class AdministradorJsonRepository extends AbstractJsonRepository<Administrador> {
    public AdministradorJsonRepository() {
        super("administradores.json", new TypeReference<List<Administrador>>() {}, Administrador::getId);
    }
}
