import java.io.InputStream;
import java.net.URL;

public class App {

    public static void main(String[] args) throws Exception {

        // Get API information
        var apiUrl = new ApiUrl();
        String api = apiUrl.getApi();
        String url = apiUrl.getApiUrl();

        // Establish a HTTP conection and get JSON
        var httpClient = new MyHttpClient();
        String body = httpClient.getData(url);

        // Extract only necessary data (title and image)
        ContentExtractor contentExtractor;
        if(new String("IMDb").equals(api)) {
            contentExtractor = new ContentImdbExtractor();
        } else if(new String("NASA").equals(api)) {
            contentExtractor = new ContentNasaExtractor();
        } else {
            contentExtractor = new ContentLanguagesExtractor();
        }
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
