import java.io.FileInputStream;

public class FindPath {

    Map pyramidMap;

    // Constructor
    public FindPath(String fileName) {
        try {
            pyramidMap = new Map(fileName);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public DLStack path() {

    }

    public Map getMap() {
        return pyramidMap;
    }

    public boolean isDim(Chamber currentChamber) {

    }

    public Chamber bestChamber (Chamber currentChamber) {

    }



}
