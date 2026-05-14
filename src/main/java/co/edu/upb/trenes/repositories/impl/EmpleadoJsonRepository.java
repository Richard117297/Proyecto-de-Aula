package co.edu.upb.trenes.repositories.impl;

import co.edu.upb.trenes.models.usuarios.Empleado;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class EmpleadoJsonRepository extends AbstractJsonRepository<Empleado> {
    public EmpleadoJsonRepository() {
        super("empleados.json", new TypeReference<List<Empleado>>() {}, Empleado::getId);
    }
}
