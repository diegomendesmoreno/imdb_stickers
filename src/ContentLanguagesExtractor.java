import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContentLanguagesExtractor implements ContentExtractor {
    
    public List<Content> contentExtractor(String json) {
        var parser = new JsonParser();
        List<Map<String, String>> attributeList = parser.parse(json);

        List<Content> contents = new ArrayList<>();

        // Populate contents
        for (Map<String, String> attributes : attributeList) {

            String imageUrlAttribute = attributes.get("image");
            String titleAttribute = attributes.get("title");

            contents.add(new Content(titleAttribute, imageUrlAttribute));
        }

        return contents;
    }
}
