package co.edu.upb.trenes.models.rutas;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class GrafoEstaciones {
    private final Map<String, NodoEstacion> nodos = new HashMap<>();

    public void agregarEstacion(Estacion estacion) {
        nodos.putIfAbsent(estacion.getId(), new NodoEstacion(estacion));
    }

    public void conectar(String origenId, String destinoId, int distanciaKm) {
        nodos.get(origenId).getDistancias().put(destinoId, distanciaKm);
        nodos.get(destinoId).getDistancias().put(origenId, distanciaKm);
    }

    public Optional<Integer> distanciaMinima(String origenId, String destinoId) {
        Map<String, Integer> distancias = new HashMap<>();
        Set<String> visitados = new HashSet<>();
        nodos.keySet().forEach(id -> distancias.put(id, Integer.MAX_VALUE));
        distancias.put(origenId, 0);

        while (visitados.size() < nodos.size()) {
            String actual = distancias.entrySet().stream()
                    .filter(entry -> !visitados.contains(entry.getKey()))
                    .min(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .orElse(null);
            if (actual == null || distancias.get(actual) == Integer.MAX_VALUE) {
                break;
            }
            visitados.add(actual);
            for (Map.Entry<String, Integer> vecino : nodos.get(actual).getDistancias().entrySet()) {
                int nuevaDistancia = distancias.get(actual) + vecino.getValue();
                if (nuevaDistancia < distancias.get(vecino.getKey())) {
                    distancias.put(vecino.getKey(), nuevaDistancia);
                }
            }
        }
        Integer distancia = distancias.get(destinoId);
        return distancia == null || distancia == Integer.MAX_VALUE ? Optional.empty() : Optional.of(distancia);
    }
}
