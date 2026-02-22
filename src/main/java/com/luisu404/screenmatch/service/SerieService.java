package com.luisu404.screenmatch.service;

import com.luisu404.screenmatch.dto.EpisodioDTO;
import com.luisu404.screenmatch.dto.SerieDTO;
import com.luisu404.screenmatch.model.Categoria;
import com.luisu404.screenmatch.model.Episodio;
import com.luisu404.screenmatch.model.Serie;
import com.luisu404.screenmatch.repository.ISerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SerieService {

    @Autowired
    private ISerieRepository repository;

    public List<SerieDTO> obtenerTodasLasSeries() {
        return convertirDatosSerie(repository.findAll());
    }

    public List<SerieDTO> obtenerTop5Series() {
        return convertirDatosSerie(repository.findTop5ByOrderByEvaluacionDesc());
    }

    public List<SerieDTO> obtenerUltimosLanzamientos() {
        return convertirDatosSerie(repository.ultimosLanzamientos());
    }

    public SerieDTO obtenerPorId(Long id) {
        Optional<Serie> serie = repository.findById(id);
        if (serie.isPresent()) {
            Serie s = serie.get();
            return new SerieDTO(s.getId(), s.getTitulo(), s.getTotalTemporadas(), s.getEvaluacion(), s.getPoster(),
                    s.getGenero(), s.getActores(), s.getSinopsis(), s.getDirector());
        } else {
            return null;
        }
    }

    public List<EpisodioDTO> obtenerTodasTemporadas(Long id) {
        Optional<Serie> serie = repository.findById(id);
        if (serie.isPresent()) {
            Serie s = serie.get();
            return s.getEpisodios().stream().map(e -> new EpisodioDTO(
                            e.getTemporada(), e.getTitulo(), e.getNumeroEpisodio()))
                    .collect(Collectors.toList());
        }
        return null;
    }
    public List<SerieDTO> obtenerPorCategoria(String nombreGenero) {
        Categoria categoria = Categoria.fromEspanol(nombreGenero);
        return convertirDatosSerie(repository.findByGenero(categoria));
    }


    public List<EpisodioDTO> obtenerTemporadasPorNumero(Long id, Long numeroTemporada) {
        return repository.obtenerTemporadasPorNumero(id, numeroTemporada).stream()
                .map (e -> new EpisodioDTO(e.getTemporada(), e.getTitulo(),
                        e.getNumeroEpisodio())).collect(Collectors.toList());
    }

    private List<SerieDTO> convertirDatosSerie(List<Serie> serie) {
        return serie.stream()
                .map(s -> new SerieDTO(s.getId(), s.getTitulo(), s.getTotalTemporadas(), s.getEvaluacion(), s.getPoster(),
                        s.getGenero(), s.getActores(), s.getSinopsis(), s.getDirector()))
                .collect(Collectors.toList());
    }


    private List<EpisodioDTO> convertirDatosEpisodio(List<Episodio> episodio) {
        return episodio.stream()
                .map(e -> new EpisodioDTO(
                        e.getTemporada(), e.getTitulo(), e.getNumeroEpisodio()))
                .collect(Collectors.toList());
    }



}
