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
    int Year;
    String Released;
    String Genre;
    String Director;






    public Movie(ArrayList<String> actorsList, String rating, int ImdbVotes) {
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
        URL url = new URL("https://www.omdbapi.com/?t=" + title + "&apikey=" + API_KEY);
        URLConnection Url = url.openConnection();
        Url.setRequestProperty("Authorization", "Key" + API_KEY);
        BufferedReader reader = new BufferedReader(new InputStreamReader(Url.getInputStream()));
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        reader.close();
        //handle an error if the chosen movie is not found
        return stringBuilder.toString();
    }

    public int getYearViaApi(String moviesInfoJson) {
        JSONObject object = new JSONObject(moviesInfoJson);

        Year = object.getInt("Year");
        return Year;
    }

    public String getReleaseTimeViaApi(String moviesInfoJson) {
        JSONObject object = new JSONObject(moviesInfoJson);

        Released = object.getString("Released");
        return Released;
    }

    public String getGenreViaApi(String moviesInfoJson) {
        JSONObject object = new JSONObject(moviesInfoJson);

        Genre = object.getString("Genre");
        return Genre;
    }

    public String getDirectorViaApi(String moviesInfoJson) {
        JSONObject object = new JSONObject(moviesInfoJson);

        Director = object.getString("Director");
        return Director;
    }

    public int getImdbVotesViaApi(String moviesInfoJson) {
        JSONObject object = new JSONObject(moviesInfoJson);


        ImdbVotes = Integer.parseInt(object.getString("imdbVotes").replace(",", ""));
        return ImdbVotes;
    }

    public String getRatingViaApi(String moviesInfoJson) {
        JSONObject object = new JSONObject(moviesInfoJson);
        JSONArray arr = object.getJSONArray("Ratings");
        rating = "";
        for (int i = 0; i < arr.length(); i++) {
            JSONObject object2 = arr.getJSONObject(i);
            if (object2.getString("Source").equals("Internet Movie Database")) {
                rating = object2.getString("Value");
                break;
            }
        }
        return rating;
    }

    public ArrayList<String> getActorListViaApi(String movieInfoJson) {
        JSONObject object = new JSONObject(movieInfoJson);

        String str = object.getString("Actors");
        String[] arr = str.split(", ");
        for (String i : arr) {
            actorsList.add(i);
        }
        return actorsList;
    }
}