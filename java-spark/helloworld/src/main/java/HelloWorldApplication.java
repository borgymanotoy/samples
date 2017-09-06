import static spark.Spark.get;
import static spark.Spark.port;

public class HelloWorldApplication {

    public static void main(String[] args) {
        port(8081);

        get("/hello", (request, response) -> "Hello World!");
    }

}
