import java.awt.*;
import java.util.*;

public class OOBattleship {
    public class Player {
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

        Graphics display;
        
        int[][][] coords = {{{-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}},
                {{-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}},
                {{-1, -1}, {-1, -1}, {-1, -1}},
                {{-1, -1}, {-1, -1}}};

        Stack<Integer> boatsSunk;
        
        int[] previousBoatsSunk = {0, 0, 0, 0};

        int name;

        // Contructor
        public Player(int label) {
            name = label;
        }

        //Getters and Setters
        public String[][] getBoard() {
            return board;
        }

        public String[][] getTargetGrid() {
            return targetGrid;
        }

        public int[][][] getCoords() {
            return coords;
        }

        public Stack<Integer> getBoatsSunk() {
            return boatsSunk;
        }

        public int getRecentBoatSunk() {
            return boatsSunk.peek();
        }

        // Methods
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

    public static void main(String[] args) {
        //Main gameplay here
    }


    //public static String modeSelect() {
        // Copy directly from previous Battleship code
        // Make improvements where you see fit
    //}


    public static void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }


    public static void populateComBoard() {
        // Copy from previous Battleship code but use Player class for updating board & coordinates
        // Might be able to incorporate this into the Player class
    }


    public static void populatePlayerBoard() {
        // Copy from previous Battleship code but use Player class for updating board & coordinates
        // Might be able to incorporate this into the Player class
    }


    public static void comVsPlayerRound() {
        // Copy from previous Battleship code but use Player class for updating board
        // Might be able to incorporate this into the Player class
    }


    public static void playerVsComRound() {
        // Copy from previous Battleship code but use Player class for updating board
        // Might be able to incorporate this into the Player class
    }


    public static void playerVsPlayerRound(int player) {
        // Copy from previous Battleship code but use Player class for updating board
        // Might be able to incorporate this into the Player class
    }


    public static void oppBoatSinkCheck(String[][] board, int[][][] coords, Stack<Integer> boatsSunk, int player) {
        // I'll do this one since I have an idea of how I want to implement the Stack for boatsSunk
        // Might be able to incorporate this into the Player class
    }


    public static void playerBoatSinkCheck(int[] previousBoatsSunk, Stack<Integer> boatsSunk) {
        // I'll do this one since I have an idea of how I want to implement the Stack for boatsSunk
        // Might be able to incorporate this into the Player class
    }


    //public static boolean checkBoardStatus(String[][] board, String checkChar) {
        // Copy directly from previous Battleship code because board will be provided by Player class as a parameter
        // Might be able to incorporate this into the Player class
    //}


    //public static boolean winConditionCheck(String mode) {
        // Copy directly from previous Battleship code but use Player class for accessing board
        // Might update this one due to how the boards will be accessed in main method
    //}


    //public static String winnerCheck(String mode) {
        // Copy directly from previous Battleship code but use Player class for accessing board
        // Might update this one due to how the boards will be accessed in main method
    //}
}
