import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
public class Actors {
    public static final String API_KEY = "d1ZGXgEvRD2aHcXSlk3SCg==bkNRy9kCHxXfbs7Y";   // TODO --> add your api key about Actors here
    double netWorth;
    Boolean isAlive;
    String Gender;
    String Nationality;
    double Height;
    String Birthday;
    int Age;
    String DateOfDeath;

    public Actors(double netWorth, boolean isAlive){
        this.netWorth = netWorth;
        this.isAlive = isAlive;
    }

    @SuppressWarnings({"deprecation"})
    /*
     * Retrieves data for the specified actor.
     * @param name for which Actor should be retrieved
     * @return a string representation of the Actors info or null if an error occurred
     */
    public String getActorData(String name) {
        try {
            URL url = new URL("https://api.api-ninjas.com/v1/celebrity?name="+
                    name.replace(" ", "+")+"&apikey="+API_KEY);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("X-Api-Key", API_KEY);
            System.out.println(connection);
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();
                return response.toString();
            } else {
                return "Error: " + connection.getResponseCode() + " " + connection.getResponseMessage();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public double getNetWorthViaApi(String actorsInfoJson){
        String str = actorsInfoJson.substring(1 , actorsInfoJson.length());
        JSONObject object = new JSONObject(str);


        netWorth = object.getDouble("net_worth");
        return netWorth;
    }
    public double getHeightViaApi(String actorsInfoJson){
        String str = actorsInfoJson.substring(1 , actorsInfoJson.length());
        JSONObject object = new JSONObject(str);


        Height = object.getDouble("height");
        return Height;
    }
    public double getAgeViaApi(String actorsInfoJson) {
        String str = actorsInfoJson.substring(1, actorsInfoJson.length());
        JSONObject object = new JSONObject(str);


        Age = object.getInt("age");
        return Age;
    }
    public String getGenderViaApi(String actorsInfoJson) {
        String str = actorsInfoJson.substring(1, actorsInfoJson.length());
        JSONObject object = new JSONObject(str);


        Gender = object.getString("gender");
        return Gender;
    }
    public String getNationalityViaApi(String actorsInfoJson) {
        String str = actorsInfoJson.substring(1, actorsInfoJson.length());
        JSONObject object = new JSONObject(str);


        Nationality = object.getString("nationality");
        return Nationality;
    }
    public String getBirthdayViaApi(String actorsInfoJson) {
        String str = actorsInfoJson.substring(1, actorsInfoJson.length());
        JSONObject object = new JSONObject(str);


        Birthday = object.getString("birthday");
        return Birthday;
    }
    public boolean getisAliveViaApi(String actorsInfoJson){
        String str = actorsInfoJson.substring(1 , actorsInfoJson.length());
        JSONObject object = new JSONObject(str);

        isAlive = object.getBoolean("is_alive");

        return isAlive;
    }
    public String getDateOfDeathViaApi(String actorsInfoJson){
        String str = actorsInfoJson.substring(1 , actorsInfoJson.length());
        JSONObject object = new JSONObject(str);
        DateOfDeath = "";
        if (!getisAliveViaApi(actorsInfoJson)){
            DateOfDeath = object.getString("death");
        }
        return DateOfDeath;
    }
}