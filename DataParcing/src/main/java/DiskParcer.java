import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;


public class DiskParcer {

//    private final URL DISK_URL = new URL("https://yadi.sk/d/QHMh7I0f7yyp0A");
    private final String CLIENT_ID = "d348b043a99644ed8b9575500f93ee4b";
    private static final String token = "AQAAAAAHNZSkAAWmPFjkWjrHm0p6ouHzWbRvIUo";
    private final String AUTH_URL = "https://oauth.yandex.ru/authorize?response_type=token&client_id=" + CLIENT_ID;
    private HttpURLConnection connection = null;
    private CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
    private final static String DISK_URL = "http://webdav.yandex.ru";
    private HttpClient httpClient = HttpClients.createSystem();
    private String mainPath = "Downloads/HSE-OOP-HW4-Data/";

//    private static ArrayList<String> parcedTextes = new ArrayList<>();
    private String[] allLinksToData;



    public DiskParcer(String login, String password) throws MalformedURLException {
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(login, password);
        credentialsProvider.setCredentials(AuthScope.ANY, credentials);
        this.allLinksToData  = new String[]{"ip_data_M/ipDataM.txt",
                "user_data_M/userDataM", "user_logs_M/logsLM.txt"};

    }


    private InputStream doGet(String path) throws IOException {
        HttpGet request = null;
        try {
            URIBuilder builder = new URIBuilder(DISK_URL);
            builder.setPath("/"+path);
            request = new HttpGet(builder.build());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        HttpClientContext context = HttpClientContext.create();
        context.setCredentialsProvider(credentialsProvider);
        HttpResponse response = httpClient.execute(request, context);
        return response.getEntity().getContent();
    }

    private void saveTxtFile(String content, String filename) throws IOException {
        FileWriter fileWriter = new FileWriter("src/main/resources/" + filename);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print("");
        printWriter.print(content);
        printWriter.close();
    }

    public void parceDisk() throws IOException {
//        Properties properties = new Properties();
//        properties.load(new FileInputStream("src/main/resources/yandex-l_p.properties"));

        for (var i = 0;i<this.allLinksToData.length;i++){
            var data = this.doGet(this.mainPath + this.allLinksToData[i]);
            var splitedPath = this.allLinksToData[i].split("/");
            System.out.println(Arrays.asList(splitedPath).get(splitedPath.length - 1) + " file was parced");
            this.saveTxtFile(IOUtils.toString(data, "UTF-8"), splitedPath[splitedPath.length-1]);
        }
    }


}
