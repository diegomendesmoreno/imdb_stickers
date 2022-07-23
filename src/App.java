import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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

        // Establish a HTTP conection and get JSON from API
        var httpClient = new MyHttpClient();
        String body = httpClient.getData(endpoint + apiKey);

        // Extract only necessary data (title, image, etc.)
        var parser = new JsonParser();
        List<Map<String, String>> contentList = parser.parse(body);

        // Manipulate and display data
        for(int i = 0; i < 5; i++) {
            Map<String,String> content = contentList.get(i);

            // Get URL of the bigger image
            String imageUrl = content.get("image").replaceAll("(@+)(.*).jpg$", "$1.jpg");

            // List Movies in the Terminal
            System.out.println("Title : " + content.get("title"));
            System.out.println("Rank  : " + content.get("rank"));
            System.out.println("Rating: " + content.get("imDbRating"));
            System.out.println("Poster: " + imageUrl);
            System.out.println();

            // Generate Sticker Images
            InputStream inputStream = new URL(imageUrl).openStream();
            String movieComment = "IRADO!";
            String imageFileName = content.get("rank") + " - " + content.get("title") + ".png";
            
            StickerGenerator generator = new StickerGenerator();
            generator.create(inputStream, imageFileName, movieComment);
        }
    }
}
