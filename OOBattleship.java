import java.util.Arrays;

public class OOBattleship {
    public class player {
        String[][] board = {{"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
                {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
                {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
                {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
                {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
                {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
                {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
                {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
                {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
                {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"}};
        String[][] targetGrid = {{"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
                {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
                {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
                {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
                {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
                {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
                {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
                {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
                {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
                {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"}};
        int[][][] coords = {{{-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}},
                {{-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}},
                {{-1, -1}, {-1, -1}, {-1, -1}},
                {{-1, -1}, {-1, -1}}};
        int[] boatsSunk = {0, 0, 0, 0};
        int[] previousBoatsSunk = {0, 0, 0, 0};
        String name;

        public player(String label) {
            name = label;
        }

        public String[][] getBoard() {
            return board;
        }

        public String[][] getTargetGrid() {
            return targetGrid;
        }

        public int[][][] getCoords() {
            return coords;
        }

        public int[] getBoatsSunk() {
            return boatsSunk;
        }

        public int[] copyBoatsSunk() {
            previousBoatsSunk = Arrays.copyOf(boatsSunk, 4);
            return previousBoatsSunk;
        }

        public void placeBoatHorizontally(int startRow, int startColumn, int boatLength) {
            int boat_section = 0;
            for (int column = startColumn; column < (startColumn + boatLength); column++) {
                board[startRow][column] = "U";
                coords[5 - boatLength][boat_section][0] = startRow + 1;
                coords[5 - boatLength][boat_section][1] = column + 1;
                boat_section++;
            }
        }

        public void placeBoatVertically(int startRow, int startColumn, int boatLength) {
            int boat_section = 0;
            for (int row = startRow; row < (startRow + boatLength); row++) {
                board[row][startColumn] = "U";
                coords[5 - boatLength][boat_section][0] = row + 1;
                coords[5 - boatLength][boat_section][1] = startColumn + 1;
                boat_section++;
            }
        }

        public boolean stillSailing(String status_check) {
            int still_alive_check = 0;

            // Loop through each coordinate in board array
            for (String[] row : board) {
                for (String coord_status : row) {

                    // If a boat section remains, increment still_alive_check
                    if (coord_status.equals(status_check)) still_alive_check++;
                }
            }
            // If any boat section remains, return true. If not return false
            return still_alive_check > 0;
        }
    }
}
