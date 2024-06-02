import java.awt.*;
import java.util.*;
import java.lang.Math;

public class OOBattleship {
    public static class Player {
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

        Graphics boardDisplay;

        Graphics targetGridDisplay;

        int[][][] coords = {{{-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}},
                {{-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}},
                {{-1, -1}, {-1, -1}, {-1, -1}},
                {{-1, -1}, {-1, -1}}};

        Stack<Integer> currentBoatsSunk;

        Stack<Integer> previousBoatsSunk;

        int[] totalBoatsSunk = {0, 0, 0, 0};

        String name;

        // Constructor
        public Player(int label) {
            if (label == 0) name = "com";
            else if (label == 1) name = "player1";
            else name = "player2";
            boardDisplay = createBoardDisplay();
            if (!name.equals("com")) targetGridDisplay = createTargetGridDisplay();
        }

        //Getters and Setters
        public String getName() { return name; }

        public String[][] getBoard() { return board; }

        public Graphics getBoardDisplay() { return boardDisplay; }

        public Graphics getTargetGridDisplay() { return targetGridDisplay; }

        public String[][] getTargetGrid() { return targetGrid; }

        public int[][][] getCoords() { return coords; }

        public int getHowManySunk() { return currentBoatsSunk.size(); }

        public int getRecentBoatSunk() { return currentBoatsSunk.peek(); }

        public int getPreviousBoatSunk() { return previousBoatsSunk.peek(); }

        public int[] getTotalBoatsSunk() { return totalBoatsSunk; }

        public void addNewBoatSunk(int boat) { currentBoatsSunk.add(boat); }

        // Methods
        private void placeBoatHorizontally(int startRow, int startColumn, int boatLength) {
            int boat_section = 0;
            for (int column = startColumn; column < (startColumn + boatLength); column++) {
                board[startRow - 1][column - 1] = "B";
                coords[5 - boatLength][boat_section][0] = startRow;
                coords[5 - boatLength][boat_section][1] = column;
                boat_section++;
            }
        }

        private void placeBoatVertically(int startRow, int startColumn, int boatLength) {
            int boat_section = 0;
            for (int row = startRow; row < (startRow + boatLength); row++) {
                board[row - 1][startColumn - 1] = "B";
                coords[5 - boatLength][boat_section][0] = row;
                coords[5 - boatLength][boat_section][1] = startColumn;
                boat_section++;
            }
        }

        private int chooseBoatDirection(int boat_len) {
            Scanner input = new Scanner(System.in);
            int boat_direction;
            // Ask player to input a direction for their current boat placement
            System.out.println("Choose direction of your " + boat_len + " unit long boat");

            // Ask for input until player enters a 0 or a 1
            do {
                System.out.print("Input 0 for left->right and 1 for top->bottom: ");
                while (!input.hasNextInt()) {
                    System.out.println("\nInvalid input\n");
                    System.out.print("Input 0 for left->right and 1 for top->bottom: ");
                    input.next();
                }
                boat_direction = input.nextInt();
                if (boat_direction != 0 && boat_direction != 1) System.out.println("\nInvalid input\n");
            } while (boat_direction != 0 && boat_direction != 1);
            input.close();
            return boat_direction;
        }

        private int chooseStartingRow(int max_row) {
            Scanner input = new Scanner(System.in);
            int boat_row;
            do {
                System.out.print("Choose a starting row (1-" + max_row + "): ");
                while (!input.hasNextInt()) {
                    System.out.println("\nInvalid input\n");
                    System.out.print("Choose a starting row (1-" + max_row + "): ");
                    input.next();
                }
                boat_row = input.nextInt();
                if (boat_row < 1 || boat_row > max_row) System.out.println("\nInvalid input\n");
            } while (boat_row < 1 || boat_row > max_row);
            input.close();
            return boat_row;
        }

        private int chooseStartingColumn(int max_column) {
            Scanner input = new Scanner(System.in);
            int boat_column;
            do {
                System.out.print("Choose a starting column (1-" + max_column + "): ");
                while (!input.hasNextInt()) {
                    System.out.println("\nInvalid input\n");
                    System.out.print("Choose a starting column (1-" + max_column + "): ");
                    input.next();
                }
                boat_column = input.nextInt();
                if (boat_column < 1 || boat_column > max_column) System.out.println("\nInvalid input\n");
            } while (boat_column < 1 || boat_column > max_column);
            input.close();
            return boat_column;
        }

        private boolean collisionCheck(int boat_direction, int boat_len, int boat_row, int boat_column) {
            boolean collision_check = false;
            if (boat_direction == 0) {
                int check_column = boat_column;
                while (!collision_check && check_column < (boat_column + boat_len)) {
                    if (board[boat_row - 1][check_column - 1].equals("B")) {
                        collision_check = true;
                    } else {
                        check_column++;
                    }
                }
            } else {
                int check_row = boat_row;
                while (!collision_check && check_row < (boat_row + boat_len)) {
                    if (board[check_row - 1][boat_column - 1].equals("B")) {
                        collision_check = true;
                    } else {
                        check_row++;
                    }
                }
            }
            return collision_check;
        }

        private Graphics createBoardDisplay() {
            // Create Drawing Panel with a size of 330 x 330
            DrawingPanel battlefield = new DrawingPanel(330, 330);

            // Set the background to CYAN, which represents water
            battlefield.setBackground(Color.CYAN);

            // Create the graphics object to be used in setting up the board and making any updates
            Graphics g = battlefield.getGraphics();

            // Set font for the grid labels
            g.setFont(new Font("Monospaced", Font.BOLD, 15));

            // Use a for loop to create the gridlines and place the grid labels
            for (int i = 1; i <= 10; i++) {
                g.drawLine(i * 30, 0, i * 30, 329);
                g.drawLine(0, i * 30, 329, i * 30);
                g.drawString(String.valueOf(i), i * 30 + 10, 19);
                g.drawString(String.valueOf(i), 10, i * 30 + 19);
            }
            if (name.equals("com")) {
                g.setFont(new Font("Monospaced", Font.BOLD, 10));
                g.drawString("COM", 2, 19);
            }
            else if (name.equals("player1")) g.drawString("P1", 5, 19);
            else g.drawString("P2", 5, 19);

            // return the graphics object to be used for updates by other methods
            return g;
        }

        private Graphics createTargetGridDisplay() {
            // Create Drawing Panel with a size of 330 x 330
            DrawingPanel battlefield = new DrawingPanel(330, 330);

            // Set the background to CYAN, which represents water
            battlefield.setBackground(Color.CYAN);

            // Create the graphics object to be used in setting up the board and making any updates
            Graphics g = battlefield.getGraphics();

            // Set font for the grid labels
            g.setFont(new Font("Monospaced", Font.BOLD, 15));

            // Use a for loop to create the gridlines and place the grid labels
            for (int i = 1; i <= 10; i++) {
                g.drawLine(i * 30, 0, i * 30, 329);
                g.drawLine(0, i * 30, 329, i * 30);
                g.drawString(String.valueOf(i), i * 30 + 10, 19);
                g.drawString(String.valueOf(i), 10, i * 30 + 19);
            }
            g.setFont(new Font("Monospaced", Font.BOLD, 10));
            if (name.equals("player1")) g.drawString("OPP1", 5, 19);
            else g.drawString("OPP2", 5, 19);

            // return the graphics object to be used for updates by other methods
            return g;
        }

        private void drawBoat(int boat_len) {
            // Set color to LIGHT GRAY for the inner shading of each boat
            boardDisplay.setColor(Color.LIGHT_GRAY);

            // Create a variable that helps index the coordinate list properly
            int current_boat_index = 5 - boat_len;

            // Get the starting coordinates from the coordinate list for the current boat
            int start_row = coords[current_boat_index][0][0], start_column = coords[current_boat_index][0][1];

            // If first section row is less than second section row, boat is vertical
            // Otherwise, the boat is horizontal --> Print boat in the proper direction and outline it in DARK GRAY
            if (coords[current_boat_index][0][0] < coords[current_boat_index][1][0]) {
                boardDisplay.fillOval(start_column * 30, start_row * 30, 30, boat_len * 30);
                boardDisplay.setColor(Color.DARK_GRAY);
                boardDisplay.drawOval(start_column * 30, start_row * 30, 30, boat_len * 30);
            } else {
                boardDisplay.fillOval(start_column * 30, start_row * 30, boat_len * 30, 30);
                boardDisplay.setColor(Color.DARK_GRAY);
                boardDisplay.drawOval(start_column * 30, start_row * 30, boat_len * 30, 30);
            }
        }

        public void redrawBoardDisplay(Player opp) {
            // use player's boardDisplay and opponent's targetGrid to update board display between rounds
        }

        public void redrawTargetGridDisplay() {
            // use player's targetGridDisplay and player's targetGrid to update target display between rounds
        }

        public void hideBoardDisplay() {
            // draw rectangle to cover player's boardDisplay so opponent can't see it during their turn
        }

        public void hideTargetGridDisplay() {
            // draw rectangle to cover player's targetGridDisplay so opponent can't see it during their turn
        }

        public void populateBoard() { // Still need to add code for populating COM board
            // Initialize necessary variables for boat placement
            int boat_row = 0, boat_column = 0, boat_direction;

            // Loop through each boat
            for (int boat_len = 5; boat_len > 1; boat_len--) {
                boat_direction = this.chooseBoatDirection(boat_len);
                System.out.println();

                // Set up boolean flag for looping coordinate input
                boolean collision = true;

                // Place boat horizontally if boat_direction == 0
                if (boat_direction == 0) {

                    // Initialize the upper bound for the values the player can input
                    int max_column = 11 - boat_len, max_row = 10;

                    // Loop coordinate selection until a collision isn't detected when placing new boat
                    while (collision) {

                        // Have the player input a starting row for the new boat
                        boat_row = this.chooseStartingRow(max_row);
                        System.out.println();

                        // Have the user input a starting column for the new boat
                        boat_column = this.chooseStartingColumn(max_column);
                        System.out.println();

                        // Check to see if these coordinates make the new boat collide with any other boats
                        // If a collision is detected, redo coordinate selection
                        // If no collision, end the input loop and stick with current starting coords
                        collision = this.collisionCheck(boat_direction, boat_len, boat_row, boat_column);
                        if (collision) System.out.println("""
                            Collision detected, try different coordinates
                            Remember, you're placing a boat left->right
                            """);
                    }

                    // Add new boat to the player's board array & boat coords to the player's coord array
                    this.placeBoatHorizontally(boat_row, boat_column, boat_len);

                } else { // The boat is placed vertically if boat_direction == 1
                    int max_column = 10, max_row = 11 - boat_len;
                    while (collision) {
                        boat_row = this.chooseStartingRow(max_row);
                        System.out.println();
                        boat_column = this.chooseStartingColumn(max_column);
                        System.out.println();
                        collision = this.collisionCheck(boat_direction, boat_len, boat_row, boat_column);
                        if (collision) System.out.println("""
                            Collision detected, try different coordinates
                            Remember, you're placing a boat left->right
                            """);
                    }
                    this.placeBoatVertically(boat_row, boat_column, boat_len);
                }

                // Print current boat onto DrawingPanel
                drawBoat(boat_len);
                //clearScreen();
            }
        }


        public void didOneOfMyBoatsGetSunk() {
            if (this.getRecentBoatSunk() != this.getPreviousBoatSunk()) {
                if (this.getRecentBoatSunk() == 0) System.out.println("Your 5 unit boat was sunk!\n");
                if (this.getRecentBoatSunk() == 1) System.out.println("Your 4 unit boat was sunk!\n");
                if (this.getRecentBoatSunk() == 2) System.out.println("Your 3 unit boat was sunk!\n");
                else System.out.println("Your 2 unit boat was sunk!\n");
            }
        }
    }

    public static void main(String[] args) {
        //Main gameplay here
        System.out.println("hello world");
    }


    // This function is used to get the player to select the mode they wish to play
    public static String modeSelect() {
        // Open a scanner so the user can input their preferred mode
        Scanner scanner = new Scanner(System.in);

        // Create a variable to assist in input verification
        String mode = "";

        // Prompt the user to input their preferred mode and repeat until a mode is properly selected
        System.out.println("Type \"vs\" to play another player\nor\nType \"com\" to play the computer");
        while (!mode.equals("com") && !mode.equals("vs")) {
            System.out.print("Select your preferred mode: ");
            mode = scanner.nextLine();

            if (!mode.equals("com") && !mode.equals("vs")) {
                System.out.println("\nInvalid input, neither mode selected\n");
            }
        }
        return mode;
    }


    public static void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }


    public static void populateComBoard() {
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


    public static void didISinkABoat(Player opp) {
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


    public static boolean winConditionCheck(Player p1, Player p2) {
        return (p1.getHowManySunk() < 4 && p2.getHowManySunk() < 4);
    }


    public static String winnerCheck(Player p1, Player p2) {
        if (p1.getHowManySunk() == 4) return p1.getName();
        else return p2.getName();
    }
}
