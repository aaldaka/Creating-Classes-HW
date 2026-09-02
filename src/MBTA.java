import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MBTA {
    static HashMap<String, ArrayList<String>> stops = new HashMap<>();
    static ArrayList<String> redStops = new ArrayList<>(Arrays.asList("South Station", "Park Street", "Kendall", "Central", "Harvard", "Porter", "Davis", "Alewife"));
    static ArrayList<String> greenStops = new ArrayList<>(Arrays.asList("Government Cneter", "Park Street", "Bolyston", "Arlington", "Copley", "Hynes", "Kenmore"));
    static ArrayList<String> orangeStops = new ArrayList<>(Arrays.asList("North Station", "Haymarket", "Park Street", "State", "Downtown crossing", "Chinatown", "Back bay", "Forest Hills"));

    static { // so it belongs to the class, no need to create an object to do this
        stops.putAll(Map.of(
                "Red", redStops,
                "Green", greenStops,
                "Orange", orangeStops
        ));
    }

    public static int stopsBetweenStations(String startLine, String startStop, String endLine, String endStop){
        //bonus: checking validity
        if (!stops.containsKey(startLine) || !stops.containsKey(endLine) || !stops.get(startLine).contains(startStop) || !stops.get(endLine).contains(endStop)){
            return -1; //wrong coords/input
        }

        if (!startLine.equals(endLine)){ // different lines
            int dist1 = sameLineStops(stops.get(startLine), startStop, "Park Street"); // first distance from stop1 to parkSt
            int dist2 = sameLineStops(stops.get(endLine), endStop, "Park Street"); // second distance, parkSt to stop2 (end)
            return dist1+dist2;
        }else {
            int dist = sameLineStops(stops.get(startLine), startStop, endStop);
            return dist;
        }
    }

    // Helper method instead of repeating if statements
    public static int sameLineStops(ArrayList<String> colorStops, String stop1, String stop2){
                int distance = Math.abs(colorStops.indexOf(stop2) - colorStops.indexOf(stop1));
                    return distance;
    }

    public static void main(String[] args) {
        System.out.println(stopsBetweenStations("Red", "Alewife", "Red", "Alewife"));
        System.out.println(stopsBetweenStations("Red", "Alewife", "Red", "South Station"));
        System.out.println(stopsBetweenStations("Red", "South Station", "Green", "Kenmore"));
        System.out.println(stopsBetweenStations("Purple", "South Station", "Green", "Kenmore"));
    }


}
