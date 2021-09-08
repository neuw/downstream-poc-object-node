package in.n2w.poc.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    @PostMapping("/v1/mock/upstream")
    public ObjectNode postObject(@RequestBody final ObjectNode requestBody) {
        return requestBody;
    }

    // wrong approach
    @GetMapping("/v1/mock/upstream/wrong")
    public void getResponseViaUpstream(final HttpServletResponse response, final HttpServletRequest request) throws IOException {
        response.addHeader("content-type", "application/json");
        response.getWriter().print(restTemplate.getForObject(URI.create(DOWNSTREAM_URL), String.class));
        response.getWriter().flush();
        response.getWriter().close();
        //return restTemplate.getForObject(URI.create(DOWNSTREAM_URL), ObjectNode.class);
    }

}
