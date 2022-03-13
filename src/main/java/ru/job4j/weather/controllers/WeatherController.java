package ru.job4j.weather.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import ru.job4j.weather.domains.Weather;
import ru.job4j.weather.services.WeatherService;

import java.time.Duration;

@RestController
public class WeatherController {

    private final WeatherService weathers;

    public WeatherController(WeatherService weathers) {
        this.weathers = weathers;
    }

    @GetMapping(value = "/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Weather> all() {
        Flux<Weather> data = weathers.findAll();
        Flux<Long> delay = Flux.interval(Duration.ofSeconds(3));
        return Flux.zip(data, delay).map(Tuple2::getT1);
    }

    @GetMapping(value = "/weather/{id}")
    public Mono<Weather> get(@PathVariable Integer id) {
        return weathers.findById(id);
    }

    @GetMapping("/hottest")
    public Mono<Weather> getHottest() {
        return weathers.findHottest();
    }

    @GetMapping(value = "/greater/{temperature}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Weather> allGreatThen(@PathVariable("temperature") int t) {
        return weathers.findAllGreater(t);
    }
}
