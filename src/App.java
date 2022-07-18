import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class App {
    
    public static Properties getProp() throws IOException {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream("./properties/data.properties");
		props.load(file);
		return props;
	}

    public static void main(String[] args) throws Exception {
        
        // Get API KEY in the properties file
        Properties prop = getProp();
        String apiKey = prop.getProperty("prop.api.key");

        // Establish a HTTP conection and search the top 250 movies in IMDB
        String endpoint = "https://imdb-api.com/en/API/Top250Movies/";
        URI address = URI.create(endpoint + apiKey);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(address).GET().build();
        var response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        System.out.println(body);
        System.out.println();

        // Extract only necessary data (title, image, etc.)
        var parser = new JsonParser();
        List<Map<String, String>> movieList = parser.parse(body);
        System.out.println(movieList.size());
        System.out.println();

        // Manipulate and display data
        for (Map<String,String> movie : movieList) {
            System.out.println("Title : " + movie.get("title"));
            System.out.println("Poster: " + movie.get("image"));
            System.out.println("Rating: " + movie.get("imDbRating"));
            System.out.println();
        }
    }
}
