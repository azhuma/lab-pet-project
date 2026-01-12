package kz.lab.petproject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HelloServiceImpl implements HelloService {

    private final RestTemplate restTemplate;

    @Override
    public String callExternalService(int id) {
        //return restTemplate.getForObject("https://api.restful-api.dev/objects/" + id, String.class);

        Map map = restTemplate.getForObject("https://api.restful-api.dev/objects/" + id, Map.class);
        return Optional
                .ofNullable(map)
                .map(x -> x.get("name"))
                .stream()
                .findFirst()
                .map(Object::toString)
                .orElse(null);
    }
}
