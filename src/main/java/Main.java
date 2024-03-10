import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true) {
            runMenu();
            Scanner in = new Scanner(System.in);
            String chr = in.next();
            while (!chr.equals("1") && !chr.equals("2") && !chr.equals("3")){
                chr = in.next();
            }
            if (chr.equals("3")){
                break;
            }
            while (true) {
                if (chr.equals("2")) {
                    runActor();
                } else if (chr.equals("1")) {
                    runMovie();
                }
                System.out.print("to return to menu, enter \'q\', otherwise, type anything than press Enter");
                String con = in.next();
                if (con.equals("q")){
                    break;
                }
            }

        }
    }
    public static void runMovie() {
        Movie mov = new Movie(new ArrayList<>(), "", 0);
        try {
            Scanner in = new Scanner(System.in);
            System.out.print("please enter the movie name: ");
            String input = in.nextLine();
            String str = mov.getMovieData(input);
            if (!str.equals("{\"Response\":\"False\",\"Error\":\"Movie not found!\"}")) {
                System.out.println();
                System.out.print("    ");
                System.out.print(input);
                System.out.print(" ");
                System.out.println(mov.getYearViaApi(str));
                System.out.println();
                System.out.print("Director: ");
                System.out.println(mov.getDirectorViaApi(str));
                System.out.print("Genre: ");
                System.out.println(mov.getGenreViaApi(str));
                System.out.print("Actors: ");
                System.out.println(mov.getActorListViaApi(str));
                System.out.print("Rating: ");
                System.out.println(mov.getRatingViaApi(str));
                System.out.print("Votes: ");
                System.out.println(mov.getImdbVotesViaApi(str));
                System.out.print("Released: ");
                System.out.println(mov.getReleaseTimeViaApi(str));
                System.out.println();
                System.out.println();
                System.out.println();
            } else {
                System.out.println("Movie not found, please try again!");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void runActor() {
        Actors act = new Actors(0.0, false);
        Scanner in = new Scanner(System.in);
        System.out.print("please enter the Actor name: ");
        String input = in.nextLine();
        String str = act.getActorData(input);
        if (!str.equals("[]")) {
            System.out.println();
            System.out.print("    ");
            System.out.print(act.getNameViaApi(str));
            System.out.println();
            System.out.print("Gender: ");
            System.out.println(act.getGenderViaApi(str));
            System.out.print("Age: ");
            System.out.println(act.getAgeViaApi(str));
            System.out.print("Height: ");
            System.out.println(act.getHeightViaApi(str));
            System.out.print("Nationality: ");
            System.out.println(act.getNationalityViaApi(str));
            System.out.print("NetWorth: ");
            System.out.println(act.getNetWorthViaApi(str));
            System.out.print("Birthday: ");
            System.out.println(act.getBirthdayViaApi(str));
            if (!act.getisAliveViaApi(str)) {
                System.out.print("Death: ");
                System.out.println(act.getDateOfDeathViaApi(str));
            }
            System.out.println();
            System.out.println();
            System.out.println();
        } else {
            System.out.println("Actor not found, please try again!");
        }
    }
    public static void runMenu(){
        System.out.println("Welcome to our program!");
        System.out.println("Choose one of these options:");
        System.out.println("1- Movie");
        System.out.println("2- Actor");
        System.out.println("3- Exit");
        System.out.println();
        System.out.print("Enter the number: ");
    }
}