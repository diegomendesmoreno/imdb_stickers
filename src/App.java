import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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
        // var contentExtractor = new ContentImdbExtractor();
        var contentExtractor = new ContentNasaExtractor();
        var contentList = contentExtractor.contentExtractor(body);

        // Manipulate and display data
        for(int i = 0; i < 3; i++) {
            var content = contentList.get(i);

            // List content in the Terminal
            System.out.println("Title : " + content.getTitle());
            System.out.println("Poster: " + content.getImageUrl());
            System.out.println();

            // Generate Sticker Images
            InputStream inputStream = new URL(content.getImageUrl()).openStream();
            String imageFileName = content.getTitle() + ".png";
            String comment = "IRADO!";
            
            StickerGenerator generator = new StickerGenerator();
            generator.create(inputStream, imageFileName, comment);
        }
    }
}
