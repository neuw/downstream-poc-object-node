package in.n2w.poc.mock.service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import in.n2w.poc.mock.util.JsonTransformer;
import spark.Request;
import spark.Response;

import static net.andreinc.mockneat.unit.text.Words.words;
import static net.andreinc.mockneat.unit.types.Chars.chars;
import static net.andreinc.mockneat.unit.types.Ints.ints;

/**
 * @author Karanbir Singh on 08/19/2021
 */
public class MockService {

    public ObjectNode handle(Request request, Response response) {
        ObjectNode mockServiceResponse = JsonTransformer.OBJECT_MAPPER.createObjectNode();

        int numberOfProps = ints().range(11, 33).get();

        for (int i = 0; i < numberOfProps; i++) {
            String propertyKey = "PROPERTY_" + chars().upperLetters().get() + chars().upperLetters().get() + chars().upperLetters().get();
            String propertyValue = words().get();
            mockServiceResponse.put(propertyKey, propertyValue);
        }

        mockServiceResponse.put("eld", "test");
        mockServiceResponse.put("eId", "test");
        mockServiceResponse.put("eid", "test");

        ObjectNode subNode = JsonTransformer.OBJECT_MAPPER.createObjectNode();
        subNode.put("eld", "test");
        subNode.put("eId", "test");
        subNode.put("eid", "test");

        mockServiceResponse.set("sub", subNode);
        mockServiceResponse.put("message", "the counts of dynamic PROPERTY_* are - "+numberOfProps);

        response.type("application/json");

        System.out.println("Request -> " + request);
        System.out.println("Response Body -> " + mockServiceResponse);
        System.out.println("Response -> " + response);

        return mockServiceResponse;
    }

}
