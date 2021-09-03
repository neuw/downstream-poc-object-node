package in.n2w.poc.mock.server;

import in.n2w.poc.mock.service.MockService;
import in.n2w.poc.mock.util.JsonTransformer;
import spark.Spark;

import static spark.Spark.get;
import static spark.Spark.port;

/**
 * @author Karanbir Singh on 08/19/2021
 */
public class MockServer {

    public static final JsonTransformer TRANSFORMER = new JsonTransformer();

    public static void serverInit() {
        port(63533);
        MockService mockService = new MockService();
        get("/v1/mock/downstream", "application/json", (req, res) -> mockService.handle(req, res), TRANSFORMER);
    }

    public static void main(String[] args) {
        serverInit();
    }

    public static void start() {
        serverInit();
    }

    public static void stop() {
        Spark.stop();
    }

}
