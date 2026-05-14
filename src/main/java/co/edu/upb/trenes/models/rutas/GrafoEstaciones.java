package co.edu.upb.trenes.models.rutas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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

    public void agregarConexion(String origenId, String destinoId, int distanciaKm) {
        conectar(origenId, destinoId, distanciaKm);
    }

    public boolean existeRuta(String origenId, String destinoId) {
        return distanciaMinima(origenId, destinoId).isPresent();
    }

    public Optional<Integer> calcularDistancia(String origenId, String destinoId) {
        return distanciaMinima(origenId, destinoId);
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

    public Optional<ResultadoRuta> obtenerRutaMasCorta(String origenId, String destinoId) {
        if (!nodos.containsKey(origenId) || !nodos.containsKey(destinoId)) {
            return Optional.empty();
        }
        Map<String, Integer> distancias = new HashMap<>();
        Map<String, String> previos = new HashMap<>();
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
            if (actual.equals(destinoId)) {
                break;
            }
            visitados.add(actual);
            for (Map.Entry<String, Integer> vecino : nodos.get(actual).getDistancias().entrySet()) {
                int nuevaDistancia = distancias.get(actual) + vecino.getValue();
                if (nuevaDistancia < distancias.get(vecino.getKey())) {
                    distancias.put(vecino.getKey(), nuevaDistancia);
                    previos.put(vecino.getKey(), actual);
                }
            }
        }

        Integer distancia = distancias.get(destinoId);
        if (distancia == null || distancia == Integer.MAX_VALUE) {
            return Optional.empty();
        }
        List<String> camino = new ArrayList<>();
        String actual = destinoId;
        while (actual != null) {
            camino.add(actual);
            actual = previos.get(actual);
        }
        Collections.reverse(camino);
        return Optional.of(new ResultadoRuta(camino, distancia));
    }

    public List<String> obtenerEstacionesIntermedias(String origenId, String destinoId) {
        return obtenerRutaMasCorta(origenId, destinoId)
                .map(ResultadoRuta::getEstaciones)
                .map(estaciones -> estaciones.size() <= 2 ? List.<String>of() : estaciones.subList(1, estaciones.size() - 1))
                .orElse(List.of());
    }
}
