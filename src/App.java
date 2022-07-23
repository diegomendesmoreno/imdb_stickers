import java.io.InputStream;
import java.net.URL;

public class App {

    public static void main(String[] args) throws Exception {

        // final String API = "IMDb";
        final String API = "NASA";

        // Get API URL
        var apiUrl = new ApiUrl();
        String url = apiUrl.getApiUrl(API);

        // Establish a HTTP conection and get JSON from API
        var httpClient = new MyHttpClient();
        String body = httpClient.getData(url);

        // Extract only necessary data (title and image)
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
