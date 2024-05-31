import java.awt.*;
import java.util.*;
import java.lang.Math;

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

        Stack<Integer> currentBoatsSunk;

        Stack<Integer> previousBoatsSunk;
        
        int[] totalBoatsSunk = {0, 0, 0, 0};

        String name;

        // Constructor
        public Player(String label) {
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

        public int getHowManySunk() {
            return currentBoatsSunk.size();
        }

        public int getRecentBoatSunk() { return currentBoatsSunk.peek(); }

        public int getPreviousBoatSunk() { return previousBoatsSunk.peek(); }

        public int[] getTotalBoatsSunk() {
            return totalBoatsSunk;
        }

        public void addNewBoatSunk(int boat) { currentBoatsSunk.add(boat); }

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


        public void didISinkABoat(Player opp) {
            for (int boat = 0; boat < 4; boat++) {
                int boat_check = 0;
                for (int section = 0; section < opp.getCoords()[boat].length; section++) {
                    if (opp.getBoard()[opp.getCoords()[boat][section][0] - 1]
                            [opp.getCoords()[boat][section][0] - 1].equals("B")) boat_check++;
                }
                if (boat_check == 0 && opp.getTotalBoatsSunk()[boat] != 1) {
                    if (boat == 0) {
                        opp.getTotalBoatsSunk()[boat] = 1;
                        opp.addNewBoatSunk(boat);
                        System.out.println("You sank your opponent's 5 unit boat\n");
                    } else if (boat == 1) {
                        opp.getTotalBoatsSunk()[boat] = 1;
                        opp.addNewBoatSunk(boat);
                        System.out.println("You sank your opponent's 4 unit boat\n");
                    } else if (boat == 2) {
                        opp.getTotalBoatsSunk()[boat] = 1;
                        opp.addNewBoatSunk(boat);
                        System.out.println("You sank your opponent's 3 unit boat\n");
                    } else {
                        opp.getTotalBoatsSunk()[boat] = 1;
                        opp.addNewBoatSunk(boat);
                        System.out.println("You sank your opponent's 2 unit boat\n");
                    }
                }
            }
        }


        public void didMyBoatsGetSunk(Player player) {
            if (player.getRecentBoatSunk() != player.getPreviousBoatSunk()) {
                if (player.getRecentBoatSunk() == 0) System.out.println("Your 5 unit boat was sunk!\n");
                if (player.getRecentBoatSunk() == 1) System.out.println("Your 4 unit boat was sunk!\n");
                if (player.getRecentBoatSunk() == 2) System.out.println("Your 3 unit boat was sunk!\n");
                else System.out.println("Your 2 unit boat was sunk!\n");
            }
        }


        public void populateBoard(Player player) {
            
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
