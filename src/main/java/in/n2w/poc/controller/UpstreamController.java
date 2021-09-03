package in.n2w.poc.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * @author Karanbir Singh on 09/03/2021
 */
@RestController
public class UpstreamController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${downstream.url}")
    private String DOWNSTREAM_URL;

    @GetMapping("/v1/mock/upstream")
    public ObjectNode getResponseViaUpstream() {
        return restTemplate.getForObject(URI.create(DOWNSTREAM_URL), ObjectNode.class);
    }

}
