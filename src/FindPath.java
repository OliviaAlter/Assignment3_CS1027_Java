public class FindPath {

    // Instance variables
    Map pyramidMap;

    /**
     * Constructor for FindPath
     * @param fileName the name of the file to be read
     * @throws Exception if fileName is not found
     * @author Anos176 a.k.a. Anh Duc Vu
     */
    public FindPath(String fileName) {
        try {
            pyramidMap = new Map(fileName);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Find the path from the start chamber to treasure chamber
     * @return the path from the start chamber to treasure chamber
     * @author Anos176 a.k.a. Anh Duc Vu
     */
    public DLStack<Chamber> path() {
        DLStack<Chamber> path = new DLStack<>();
        Chamber start = pyramidMap.getEntrance();
        int treasureTotal = pyramidMap.getNumTreasures();
        int treasureFound = 0;

        path.push(start);
        start.markPushed();

        while (!path.isEmpty()) {
            Chamber currentChamber = path.peek();
            if (currentChamber.isTreasure()) {
                treasureFound++;
                if (treasureFound == treasureTotal) {
                    return path;
                }
            } else {
                Chamber c = bestChamber(currentChamber);
                if (c != null) {
                    path.push(c);
                    c.markPushed();
                } else {
                    path.pop();
                    currentChamber.markPopped();
                }
            }
        }

        return path;
    }

    /**
     * Return the value of pyramidMap
     * @return the value of pyramidMap
     * @author Anos176 a.k.a. Anh Duc Vu
     */
    public Map getMap() {
        return pyramidMap;
    }

    /**
     * Check if the current chamber is the dimmed
     * @param currentChamber the current chamber
     * @return true if the current chamber is the dimmed, false otherwise.
     * @author Anos176 a.k.a. Anh Duc Vu
     */
    public boolean isDim(Chamber currentChamber) {
        boolean self = false;
        boolean neighbor = false;
        self = !currentChamber.isSealed() && !currentChamber.isLighted();
        for (int i = 0; i <= 5; i++) {
            if (currentChamber.getNeighbour(i) != null) {
                neighbor = currentChamber.getNeighbour(i).isLighted();
                if (neighbor) {
                    break;
                }
            }
        }
        return self && neighbor;
    }

    /**
     * Select best chamber to move from current chamber
     * @param currentChamber current chamber
     * @return the best chamber to move from current chamber
     * @author Anos176 a.k.a. Anh Duc Vu
     */
    public Chamber bestChamber (Chamber currentChamber) {
        boolean treasureFound = false;
        for (int i = 0; i <= 5; i++) {
            // Check if neighbor chamber is treasure and not marked
            if(currentChamber.getNeighbour(i) != null) {
                if (currentChamber.getNeighbour(i).isTreasure() && !currentChamber.getNeighbour(i).isMarked()) {
                    treasureFound = true;
                    return currentChamber.getNeighbour(i);
                }
            }
        }
        if (!treasureFound) {
            // Treasure not found, check if neighbor chamber is lighted and not marked
            for (int i = 0; i <= 5; i++) {
                if(currentChamber.getNeighbour(i) != null) {
                    if (currentChamber.getNeighbour(i).isLighted() && !currentChamber.getNeighbour(i).isMarked()) {
                        return currentChamber.getNeighbour(i);
                    } else if (isDim(currentChamber.getNeighbour(i)) && !currentChamber.getNeighbour(i).isMarked()) {
                        return currentChamber.getNeighbour(i);
                    }
                }
            }
        }
        return null;
    }

}
