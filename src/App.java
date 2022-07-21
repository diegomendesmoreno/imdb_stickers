import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class App {
    
    public static Properties getProp() throws IOException {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream("./data.properties");
		props.load(file);
		return props;
	}

    public static void main(String[] args) throws Exception {
        
        // Get API KEY and API ENDPOINT in the properties file
        Properties prop = getProp();
        String apiKey = prop.getProperty("prop.api.key");
        String endpoint = prop.getProperty("prop.api.endpoint");

        // Establish a HTTP conection and search the top 250 movies in IMDB
        URI address = URI.create(endpoint + apiKey);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(address).GET().build();
        var response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // Extract only necessary data (title, image, etc.)
        var parser = new JsonParser();
        List<Map<String, String>> movieList = parser.parse(body);

        // Manipulate and display data
        for(int i = 0; i < 10; i++) {
            Map<String,String> movie = movieList.get(i);

            // Get URL of the bigger image
            String imageUrl = movie.get("image").replaceAll("(@+)(.*).jpg$", "$1.jpg");

            // List Movies in the Terminal
            System.out.println("Title : " + movie.get("title"));
            System.out.println("Rank  : " + movie.get("rank"));
            System.out.println("Rating: " + movie.get("imDbRating"));
            System.out.println("Poster: " + imageUrl);
            System.out.println();

            // Generate Sticker Images
            InputStream inputStream = new URL(imageUrl).openStream();
            String movieComment = "IRADO!";
            String imageFileName = movie.get("rank") + " - " + movie.get("title") + ".png";
            
            StickerGenerator generator = new StickerGenerator();
            generator.create(inputStream, imageFileName, movieComment);
        }
    }
}
