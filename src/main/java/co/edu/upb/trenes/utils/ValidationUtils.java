package co.edu.upb.trenes.utils;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public final class ValidationUtils {
    private static final Pattern TEXTO = Pattern.compile("[A-Za-zÁÉÍÓÚáéíóúÑñ ]+");
    private static final Pattern NUMERO = Pattern.compile("\\d+");
    private static final Pattern DECIMAL = Pattern.compile("\\d+([\\.,]\\d+)?");

    private ValidationUtils() {
    }

    public static boolean isBlank(String value) {
        return value == null || value.isBlank();
    }

    public static boolean isPositiveNumber(double value) {
        return value > 0;
    }

    public static boolean inRange(double value, double min, double max) {
        return value >= min && value <= max;
    }

    public static TextFormatter<String> soloLetrasFormatter() {
        UnaryOperator<TextFormatter.Change> filter = change ->
                change.getControlNewText().matches("[A-Za-zÁÉÍÓÚáéíóúÑñ ]*") ? change : null;
        return new TextFormatter<>(filter);
    }

    public static TextFormatter<String> soloNumerosFormatter() {
        UnaryOperator<TextFormatter.Change> filter = change ->
                change.getControlNewText().matches("\\d*") ? change : null;
        return new TextFormatter<>(filter);
    }

    public static TextFormatter<String> decimalFormatter() {
        UnaryOperator<TextFormatter.Change> filter = change ->
                change.getControlNewText().matches("\\d*([\\.,]\\d*)?") ? change : null;
        return new TextFormatter<>(filter);
    }

    public static boolean esTextoValido(String valor) {
        return !isBlank(valor) && TEXTO.matcher(valor.trim()).matches();
    }

    public static boolean esNumeroValido(String valor) {
        return !isBlank(valor) && NUMERO.matcher(valor.trim()).matches();
    }

    public static boolean esDecimalValido(String valor) {
        return !isBlank(valor) && DECIMAL.matcher(valor.trim()).matches();
    }

    public static boolean esTelefonoValido(String valor) {
        return esNumeroValido(valor) && valor.trim().length() >= 7;
    }

    public static boolean esPesoEquipajeValido(double peso) {
        return peso > 0 && peso <= 80;
    }

    public static double parseDecimal(String valor) {
        return Double.parseDouble(valor.replace(",", "."));
    }

    public static boolean validarCampoObligatorio(String valor) {
        return !isBlank(valor);
    }

    public static boolean validarSeleccionCombo(ComboBox<?> comboBox) {
        return comboBox != null && comboBox.getValue() != null;
    }
}
