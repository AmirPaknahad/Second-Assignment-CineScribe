import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Movie {
    public static final String API_KEY = "7d00edc5";
    int ImdbVotes;
    ArrayList<String> actorsList;
    String rating;

    public Movie(ArrayList<String> actorsList, String rating, int ImdbVotes){
        this.actorsList = actorsList;
        this.rating = rating;
        this.ImdbVotes = ImdbVotes;
    }

    @SuppressWarnings("deprecation")
    /*
     * Retrieves data for the specified movie.
     *
     * @param title the name of the title for which MovieData should be retrieved
     * @return a string representation of the MovieData, or null if an error occurred
     */

    public String getMovieData(String title) throws IOException {
        URL url = new URL("https://www.omdbapi.com/?t="+title+"&apikey="+API_KEY);
        URLConnection Url = url.openConnection();
        Url.setRequestProperty("Authorization", "Key" + API_KEY);
        BufferedReader reader = new BufferedReader(new InputStreamReader(Url.getInputStream()));
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = reader.readLine())!=null) {
            stringBuilder.append(line);
        }
        reader.close();
        //handle an error if the chosen movie is not found
        return stringBuilder.toString();
    }
    public int getImdbVotesViaApi(String moviesInfoJson){
        JSONObject object = new JSONObject(moviesInfoJson);


        int ImdbVotes = object.getInt("imdbVotes");
        return ImdbVotes;
    }

    public String getRatingViaApi(String moviesInfoJson){
        JSONObject object = new JSONObject(moviesInfoJson);
        JSONArray arr = object.getJSONArray("Ratings");
        String rating = "";
        for (int i = 0; i < arr.length(); i++){
            JSONObject object2 = new JSONObject(arr.get(i));
            if (object2.getString("Sourse").equals("Internet Movie Database")){
                rating = object2.getString("Value");
                break;
            }
        }
        return rating;
    }

    public void getActorListViaApi(String movieInfoJson){
        JSONObject object = new JSONObject(movieInfoJson);



    }
}