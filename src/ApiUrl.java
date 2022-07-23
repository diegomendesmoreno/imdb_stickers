import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ApiUrl {

    private final String propertiesFilePath = "./data.properties";
    
    public String getApiUrl(String API) throws IOException {

        // Get API KEY and API ENDPOINT in the properties file
        Properties prop = this.getProp();

        String url = "";
        String apiKey = prop.getProperty("prop.api.key");
        String endpoint = prop.getProperty("prop.api.endpoint");

        switch(API) {
            case "IMDb":
                url = endpoint + apiKey;
                break;

            case "NASA":
                String start_date = prop.getProperty("prop.api.start_date");
                String end_date = prop.getProperty("prop.api.end_date");
                
                url = endpoint + "?api_key=" + apiKey + "&start_date=" + start_date + "&end_date=" + end_date;
                break;

            default:
                System.out.println("Unknown API");
                break;
        }

        return url;
    }

    private Properties getProp() throws IOException {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream(this.propertiesFilePath);
		props.load(file);
		return props;
	}
}
