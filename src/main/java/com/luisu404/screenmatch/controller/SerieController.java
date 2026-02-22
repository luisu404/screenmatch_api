package com.luisu404.screenmatch.controller;

import com.luisu404.screenmatch.dto.EpisodioDTO;
import com.luisu404.screenmatch.dto.SerieDTO;

import com.luisu404.screenmatch.model.Episodio;
import com.luisu404.screenmatch.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/series")
public class SerieController {
    @Autowired
    private SerieService service;

    @GetMapping()
    public List<SerieDTO> obtenerTodasLasSeries() {
        return service.obtenerTodasLasSeries();
    }

    @GetMapping("/top5")
    public List<SerieDTO> obtenerTop5Series() {
        return service.obtenerTop5Series();
    }

    @GetMapping("/lanzamientos")
    public List<SerieDTO> obtenerUltimosLanzamientos() {
        return service.obtenerUltimosLanzamientos();
    }

    @GetMapping("/{id}")
    public SerieDTO obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @GetMapping("/{id}/temporadas/todas")
    public List<EpisodioDTO> obtenerTodasTemporadas(@PathVariable Long id) {
        return service.obtenerTodasTemporadas(id);
    }

    @GetMapping("/{id}/temporadas/{numeroTemporada}")
    public List<EpisodioDTO> obtenerTemporadasPorNumero(@PathVariable Long id,
                                                        @PathVariable Long numeroTemporada) {
        return service.obtenerTemporadasPorNumero(id, numeroTemporada);
    }

    @GetMapping("/categoria/{nombreGenero}")
    public List<SerieDTO> obtenerPorCategoria(@PathVariable String nombreGenero){
        return service.obtenerPorCategoria(nombreGenero);
    }
}
