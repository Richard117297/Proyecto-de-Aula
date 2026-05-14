package co.edu.upb.trenes.utils;

import co.edu.upb.trenes.models.usuarios.RolUsuario;
import co.edu.upb.trenes.models.usuarios.Usuario;

import java.util.Optional;

public final class SessionManager {
    private static Usuario usuarioActual;

    private SessionManager() {
    }

    public static void iniciarSesion(Usuario usuario) {
        usuarioActual = usuario;
    }

    public static Optional<Usuario> usuarioActual() {
        return Optional.ofNullable(usuarioActual);
    }

    public static String idUsuario() {
        return usuarioActual == null ? "" : usuarioActual.getId();
    }

    public static String nombre() {
        return usuarioActual == null ? "" : usuarioActual.getNombre();
    }

    public static RolUsuario rol() {
        return usuarioActual == null ? null : usuarioActual.getRol();
    }

    public static void cerrarSesion() {
        usuarioActual = null;
    }
}
