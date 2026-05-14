package co.edu.upb.trenes.controllers.pasajero;

import co.edu.upb.trenes.models.boletos.TipoBoleto;
import co.edu.upb.trenes.models.rutas.Estacion;
import co.edu.upb.trenes.models.rutas.ResultadoRuta;
import co.edu.upb.trenes.models.trenes.Tren;
import co.edu.upb.trenes.services.BoletoService;
import co.edu.upb.trenes.services.EquipajeService;
import co.edu.upb.trenes.services.RutaService;
import co.edu.upb.trenes.services.TrenService;
import co.edu.upb.trenes.utils.AlertUtils;
import co.edu.upb.trenes.utils.DateTimeUtils;
import co.edu.upb.trenes.utils.NavigationUtils;
import co.edu.upb.trenes.utils.SessionManager;
import co.edu.upb.trenes.utils.ValidationUtils;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.StringConverter;

public class ComprarBoletoController {
    @FXML private ComboBox<Estacion> origenCombo;
    @FXML private ComboBox<Estacion> destinoCombo;
    @FXML private ComboBox<Tren> trenCombo;
    @FXML private ComboBox<TipoBoleto> categoriaCombo;
    @FXML private ComboBox<String> pagoCombo;
    @FXML private DatePicker fechaSalidaPicker;
    @FXML private ComboBox<String> horaSalidaCombo;
    @FXML private TextField nombreField;
    @FXML private TextField apellidoField;
    @FXML private TextField documentoField;
    @FXML private TextField telefonoField;
    @FXML private TextField contactoNombreField;
    @FXML private TextField contactoApellidoField;
    @FXML private TextField contactoTelefonoField;
    @FXML private TextField contactoRelacionField;
    @FXML private TextField cantidadMaletasField;
    @FXML private TextField pesoMaletaUnoField;
    @FXML private TextField pesoMaletaDosField;
    @FXML private Label rutaLabel;
    @FXML private Label distanciaLabel;
    @FXML private Label intermediasLabel;
    @FXML private Label trenDetalleLabel;
    @FXML private Label preboletoLabel;
    @FXML private Label errorLabel;
    @FXML private VBox rutaCard;

    private final RutaService rutaService = new RutaService();
    private final BoletoService boletoService = new BoletoService();
    private final EquipajeService equipajeService = new EquipajeService();
    private final TrenService trenService = new TrenService();
    private ResultadoRuta rutaActual;

    @FXML private void initialize() {
        origenCombo.setItems(FXCollections.observableArrayList(rutaService.listarEstaciones()));
        destinoCombo.setItems(FXCollections.observableArrayList(rutaService.listarEstaciones()));
        StringConverter<Estacion> estacionConverter = new StringConverter<>() {
            @Override public String toString(Estacion estacion) {
                return estacion == null ? "" : estacion.getId() + " - " + estacion.getNombre();
            }
            @Override public Estacion fromString(String string) { return null; }
        };
        origenCombo.setConverter(estacionConverter);
        destinoCombo.setConverter(estacionConverter);
        trenCombo.setItems(FXCollections.observableArrayList(trenService.listarTrenesActivos()));
        trenCombo.setConverter(new StringConverter<>() {
            @Override public String toString(Tren tren) {
                return tren == null ? "" : tren.getTipoTren() + " - " + tren.getId() + " - " + tren.getEstado();
            }
            @Override public Tren fromString(String string) { return null; }
        });
        categoriaCombo.getItems().setAll(TipoBoleto.values());
        pagoCombo.getItems().setAll("EFECTIVO", "TARJETA");
        horaSalidaCombo.getItems().setAll(DateTimeUtils.generarHorasOperacion());
        if (!origenCombo.getItems().isEmpty()) origenCombo.getSelectionModel().selectFirst();
        if (destinoCombo.getItems().size() > 7) destinoCombo.getSelectionModel().select(7);
        if (!trenCombo.getItems().isEmpty()) trenCombo.getSelectionModel().selectFirst();
        categoriaCombo.getSelectionModel().select(TipoBoleto.ESTANDAR);
        pagoCombo.getSelectionModel().select("EFECTIVO");
        horaSalidaCombo.getSelectionModel().select("08:00");
        fechaSalidaPicker.setPromptText("Seleccione fecha de salida");
        nombreField.setText(SessionManager.nombre());
        aplicarFormatters();
        origenCombo.valueProperty().addListener((obs, oldValue, value) -> recalcularRuta());
        destinoCombo.valueProperty().addListener((obs, oldValue, value) -> recalcularRuta());
        trenCombo.valueProperty().addListener((obs, oldValue, tren) -> mostrarDetalleTren(tren));
        categoriaCombo.valueProperty().addListener((obs, oldValue, value) -> actualizarPreboleto());
        pagoCombo.valueProperty().addListener((obs, oldValue, value) -> actualizarPreboleto());
        agregarListenersPreboleto();
        recalcularRuta();
        mostrarDetalleTren(trenCombo.getValue());
    }

    @FXML private void recomendarRuta() {
        recalcularRuta();
    }

    @FXML private void actualizarPreboleto() {
        if (rutaActual == null) {
            preboletoLabel.setText("Seleccione origen y destino para generar el preboleto.");
            return;
        }
        Tren tren = trenCombo.getValue();
        preboletoLabel.setText("Pasajero: " + valor(nombreField.getText()) + " " + valor(apellidoField.getText())
                + "\nOrigen: " + origenCombo.getValue().getNombre()
                + "\nDestino: " + destinoCombo.getValue().getNombre()
                + "\nRuta: " + String.join(" -> ", rutaActual.getEstaciones())
                + "\nTren: " + (tren == null ? "Sin seleccionar" : tren.getTipoTren() + " - " + tren.getId())
                + "\nCategoria: " + categoriaCombo.getValue()
                + "\nFecha y hora: " + (fechaSalidaPicker.getValue() == null ? "Pendiente" : fechaSalidaPicker.getValue())
                + " " + valor(horaSalidaCombo.getValue())
                + "\nForma de pago: " + valor(pagoCombo.getValue())
                + "\nDistancia: " + rutaActual.getDistanciaTotalKm() + " km"
                + "\nEquipaje: " + valor(cantidadMaletasField.getText()) + " maleta(s)"
                + "\nContacto de emergencia: " + valor(contactoNombreField.getText()) + " " + valor(contactoApellidoField.getText())
                + "\nEstado inicial: Pendiente de validacion");
    }

    @FXML private void comprar() {
        if (!validarFormulario()) {
            return;
        }
        var boleto = boletoService.comprarBoleto(SessionManager.idUsuario(), nombreField.getText(), documentoField.getText(),
                telefonoField.getText(), origenCombo.getValue().getId(), destinoCombo.getValue().getId(), trenCombo.getValue().getId(), categoriaCombo.getValue(),
                pagoCombo.getValue(), contactoNombreField.getText() + " " + contactoApellidoField.getText(), contactoTelefonoField.getText());
        int cantidadMaletas = cantidadMaletasField.getText().isBlank() ? 0 : Integer.parseInt(cantidadMaletasField.getText());
        if (cantidadMaletas >= 1) {
            equipajeService.registrar(SessionManager.idUsuario(), boleto.getId(), ValidationUtils.parseDecimal(pesoMaletaUnoField.getText()));
        }
        if (cantidadMaletas == 2) {
            equipajeService.registrar(SessionManager.idUsuario(), boleto.getId(), ValidationUtils.parseDecimal(pesoMaletaDosField.getText()));
        }
        AlertUtils.success("Pago registrado correctamente", "El boleto queda pendiente de validacion por un empleado.");
    }

    @FXML private void limpiarFormulario() {
        apellidoField.clear();
        documentoField.clear();
        telefonoField.clear();
        contactoNombreField.clear();
        contactoApellidoField.clear();
        contactoTelefonoField.clear();
        contactoRelacionField.clear();
        cantidadMaletasField.clear();
        pesoMaletaUnoField.clear();
        pesoMaletaDosField.clear();
        fechaSalidaPicker.setValue(null);
        horaSalidaCombo.getSelectionModel().select("08:00");
        pagoCombo.getSelectionModel().select("EFECTIVO");
        errorLabel.setText("");
        actualizarPreboleto();
    }

    @FXML private void volver() { NavigationUtils.goDashboard((Stage) origenCombo.getScene().getWindow()); }

    private void aplicarFormatters() {
        nombreField.setTextFormatter(ValidationUtils.soloLetrasFormatter());
        apellidoField.setTextFormatter(ValidationUtils.soloLetrasFormatter());
        documentoField.setTextFormatter(ValidationUtils.soloNumerosFormatter());
        telefonoField.setTextFormatter(ValidationUtils.soloNumerosFormatter());
        contactoNombreField.setTextFormatter(ValidationUtils.soloLetrasFormatter());
        contactoApellidoField.setTextFormatter(ValidationUtils.soloLetrasFormatter());
        contactoRelacionField.setTextFormatter(ValidationUtils.soloLetrasFormatter());
        contactoTelefonoField.setTextFormatter(ValidationUtils.soloNumerosFormatter());
        cantidadMaletasField.setTextFormatter(ValidationUtils.soloNumerosFormatter());
        pesoMaletaUnoField.setTextFormatter(ValidationUtils.decimalFormatter());
        pesoMaletaDosField.setTextFormatter(ValidationUtils.decimalFormatter());
    }

    private void recalcularRuta() {
        if (origenCombo.getValue() == null || destinoCombo.getValue() == null) {
            return;
        }
        rutaActual = rutaService.recomendarRuta(origenCombo.getValue().getId(), destinoCombo.getValue().getId());
        rutaLabel.setText("Ruta recomendada:\n" + String.join("  --  ", rutaActual.getEstaciones()));
        distanciaLabel.setText("Distancia total: " + rutaActual.getDistanciaTotalKm() + " km");
        intermediasLabel.setText("Estaciones intermedias: " + estacionesIntermedias());
        actualizarPreboleto();
        FadeTransition fade = new FadeTransition(Duration.millis(250), rutaCard);
        fade.setFromValue(0.45);
        fade.setToValue(1.0);
        fade.play();
    }

    private void agregarListenersPreboleto() {
        nombreField.textProperty().addListener((obs, oldValue, value) -> actualizarPreboleto());
        apellidoField.textProperty().addListener((obs, oldValue, value) -> actualizarPreboleto());
        cantidadMaletasField.textProperty().addListener((obs, oldValue, value) -> actualizarPreboleto());
        contactoNombreField.textProperty().addListener((obs, oldValue, value) -> actualizarPreboleto());
        contactoApellidoField.textProperty().addListener((obs, oldValue, value) -> actualizarPreboleto());
        fechaSalidaPicker.valueProperty().addListener((obs, oldValue, value) -> actualizarPreboleto());
        horaSalidaCombo.valueProperty().addListener((obs, oldValue, value) -> actualizarPreboleto());
    }

    private String estacionesIntermedias() {
        if (rutaActual == null || rutaActual.getEstaciones().size() <= 2) {
            return "Sin estaciones intermedias";
        }
        return String.join(", ", rutaActual.getEstaciones().subList(1, rutaActual.getEstaciones().size() - 1));
    }

    private void mostrarDetalleTren(Tren tren) {
        if (tren == null) {
            trenDetalleLabel.setText("Seleccione un tren disponible.");
            return;
        }
        trenDetalleLabel.setText("Tipo: " + tren.getTipoTren()
                + "\nIdentificador: " + tren.getId()
                + "\nCapacidad: " + tren.totalVagones() + " vagones"
                + "\nEstado: " + tren.getEstado()
                + "\nRuta asignada: " + (tren.getRutaAsignadaId() == null ? "Disponible" : tren.getRutaAsignadaId()));
        actualizarPreboleto();
    }

    private boolean validarFormulario() {
        errorLabel.setText("");
        if (!ValidationUtils.validarSeleccionCombo(origenCombo) || !ValidationUtils.validarSeleccionCombo(destinoCombo)) {
            return error("Debe seleccionar una estacion de origen y destino.");
        }
        if (!ValidationUtils.validarSeleccionCombo(trenCombo)) {
            return error("Debe seleccionar un tren disponible.");
        }
        if (fechaSalidaPicker.getValue() == null || horaSalidaCombo.getValue() == null) {
            return error("Debe seleccionar fecha y hora de salida.");
        }
        if (!ValidationUtils.esTextoValido(nombreField.getText()) || !ValidationUtils.esTextoValido(apellidoField.getText())) {
            return error("Los nombres y apellidos no deben contener numeros.");
        }
        if (!ValidationUtils.esNumeroValido(documentoField.getText())) {
            return error("El numero de documento solo debe contener numeros.");
        }
        if (!ValidationUtils.esTelefonoValido(telefonoField.getText()) || !ValidationUtils.esTelefonoValido(contactoTelefonoField.getText())) {
            return error("Los telefonos solo deben contener numeros y minimo 7 digitos.");
        }
        if (!ValidationUtils.esTextoValido(contactoNombreField.getText()) || !ValidationUtils.esTextoValido(contactoApellidoField.getText())
                || !ValidationUtils.esTextoValido(contactoRelacionField.getText())) {
            return error("Los datos del contacto de emergencia solo deben contener letras.");
        }
        int cantidad = cantidadMaletasField.getText().isBlank() ? 0 : Integer.parseInt(cantidadMaletasField.getText());
        if (cantidad < 0 || cantidad > 2) {
            return error("Puedes registrar maximo 2 maletas.");
        }
        if (cantidad >= 1 && (!ValidationUtils.esDecimalValido(pesoMaletaUnoField.getText())
                || !ValidationUtils.esPesoEquipajeValido(ValidationUtils.parseDecimal(pesoMaletaUnoField.getText())))) {
            return error("El peso de la maleta 1 debe ser mayor a 0 y maximo 80 kg.");
        }
        if (cantidad == 2 && (!ValidationUtils.esDecimalValido(pesoMaletaDosField.getText())
                || !ValidationUtils.esPesoEquipajeValido(ValidationUtils.parseDecimal(pesoMaletaDosField.getText())))) {
            return error("El peso de la maleta 2 debe ser mayor a 0 y maximo 80 kg.");
        }
        return true;
    }

    private boolean error(String mensaje) {
        errorLabel.setText(mensaje);
        AlertUtils.warning("Revise el formulario", mensaje);
        return false;
    }

    private String valor(String texto) {
        return texto == null || texto.isBlank() ? "Pendiente" : texto;
    }
}
