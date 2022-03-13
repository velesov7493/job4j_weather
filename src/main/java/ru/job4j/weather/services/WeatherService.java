package ru.job4j.weather.services;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.job4j.weather.domains.Weather;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class WeatherService {

    private final Map<Integer, Weather> weathers;

    public WeatherService() {
        weathers = new ConcurrentHashMap<>();
        weathers.put(1, new Weather(1, "Moscow", 20));
        weathers.put(2, new Weather(2, "Saint-Petersburg", 15));
        weathers.put(3, new Weather(3, "Bryansk", 15));
        weathers.put(4, new Weather(4, "Smolensk", 15));
        weathers.put(5, new Weather(5, "Kiev", 15));
        weathers.put(6, new Weather(6, "Minsk", 15));
    }

    public Mono<Weather> findById(Integer id) {
        return Mono.justOrEmpty(weathers.get(id));
    }

    public Flux<Weather> findAll() {
        return Flux.fromIterable(weathers.values());
    }

    public Mono<Weather> findHottest() {
        return
                Flux.fromIterable(weathers.values())
                .reduce((w1, w2) -> w1.getTemperature() > w2.getTemperature() ? w1 : w2);
    }

    public Flux<Weather> findAllGreater(int temperature) {
        return
                Flux.fromIterable(weathers.values())
                .filter((w) -> w.getTemperature() > temperature);
    }
}
