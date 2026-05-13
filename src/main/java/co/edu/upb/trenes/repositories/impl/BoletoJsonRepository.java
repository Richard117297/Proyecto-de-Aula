package co.edu.upb.trenes.repositories.impl;

import co.edu.upb.trenes.models.boletos.Boleto;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class BoletoJsonRepository extends AbstractJsonRepository<Boleto> {
    public BoletoJsonRepository() {
        super("boletos.json", new TypeReference<List<Boleto>>() {}, Boleto::getId);
    }
}
