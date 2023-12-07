public class Rat {
    private int	row, col;

    // Move the Rat to the given position
    public void moveTo(int r, int c) {
        row = r; col = c;
    }

    public boolean canFindCheeseIn(Maze m) {
        //m.display(row, col);
        // Return true if there is cheese at the rat's (row,col) in the maze
        if (m.cheeseAt(row, col))
            return true;

        // Return false if there is a wall at the rat's (row,col) in the maze
        if (m.wallAt(row, col) || m.hasBeenVisited(row, col))
            return false;

        // Mark this location as having been visited
        m.markVisited(row, col);

        // Move up in the maze and recursively check
        moveTo(row-1, col);
        if (canFindCheeseIn(m)) {
            moveTo(row+1, col);   // Move back down before marking
            m.markUnVisited(row, col); // Unmark the visited location
            return true;
        }
        moveTo(row+1, col);   // Move back down before marking

        // Move below in the maze and recursively check
        moveTo(row+1, col);
        if (canFindCheeseIn(m)) {
            moveTo(row-1, col);   // Move back up before marking
            m.markUnVisited(row, col); // Unmark the visited location
            return true;
        }
        moveTo(row-1, col);   // Move back up before marking

        // Move left in the maze and recursively check
        moveTo(row, col-1);
        if (canFindCheeseIn(m)) {
            moveTo(row, col+1);   // Move back right before marking
            m.markUnVisited(row, col); // Unmark the visited location
            return true;
        }
        moveTo(row, col+1);   // Move back right before marking

        // Move right in the maze and recursively check
        moveTo(row, col+1);
        if (canFindCheeseIn(m)) {
            moveTo(row, col-1);   // Move back left before marking
            m.markUnVisited(row, col); // Unmark the visited location
            return true;
        }
        moveTo(row, col-1);   // Move back left before marking

        // We tried all directions and did not find the cheese, so quit
        return false;
    }

    // 1 + to include the starting location
    public int freeSpaces(Maze m) {
        // If the starting location is free
        if (!(m.wallAt(row,col) || m.cheeseAt(row, col))) {
            return 1 + freeSpacesRecursive(m, row, col);
        }
        return freeSpacesRecursive(m, row, col);
    }

    private int freeSpacesRecursive(Maze m, int x, int y) {
        int total = 0;
        m.markVisited(x,y);

        // Check North, East, South, and West to see if their locations are walls or have been visited
        // Check North
        if (!(m.wallAt(x,y + 1) || m.hasBeenVisited(x, y + 1))) {
            total += 1 + freeSpacesRecursive(m, x, y + 1);
        }
        // Check East
        if (!(m.wallAt(x + 1,y) || m.hasBeenVisited(x + 1, y))) {
            total += 1 + freeSpacesRecursive(m, x + 1, y);
        }
        // Check South
        if (!(m.wallAt(x,y - 1) || m.hasBeenVisited(x, y - 1))) {
            total += 1 + freeSpacesRecursive(m, x, y - 1);
        }
        // Check West
        if (!(m.wallAt(x - 1,y) || m.hasBeenVisited(x - 1, y))) {
            total += 1 + freeSpacesRecursive(m, x - 1, y);
        }
        return total;
    }
}
