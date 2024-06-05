import java.awt.*;
import java.util.*;

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

        int[] boatsSunk = {0, 0, 0, 0};

        String name;

        // Constructor
        public Player(String label) {
            name = label;
            currentBoatsSunk = new Stack<>();
            previousBoatsSunk = new Stack<>();
            boardDisplay = createBoardDisplay();
            if (!name.equals("com")) targetGridDisplay = createTargetGridDisplay();
        }

        //Getters and Setters
        public String getName() { return name; }

        public String[][] getBoard() { return board; }

        public String[][] getTargetGrid() { return targetGrid; }

        public int[][][] getCoords() { return coords; }

        public int getHowManySunk() { return currentBoatsSunk.size(); }

        public int getRecentBoatSunk() {
            if (currentBoatsSunk.isEmpty()) return -1;
            else return currentBoatsSunk.peek();
        }

        public int getPreviousBoatSunk() {
            if (previousBoatsSunk.isEmpty()) return -1;
            else return previousBoatsSunk.peek();
        }

        public void addPreviousBoatSunk(int boat) { previousBoatsSunk.add(boat); }

        // Methods
        private void placeBoatHorizontally(int startRow, int startColumn, int boatLength) {
            int boat_section = 0;
            for (int column = startColumn; column < (startColumn + boatLength); column++) {
                board[startRow][column] = "B";
                coords[5 - boatLength][boat_section][0] = startRow + 1;
                coords[5 - boatLength][boat_section][1] = column + 1;
                boat_section++;
            }
        }

        private void placeBoatVertically(int startRow, int startColumn, int boatLength) {
            int boat_section = 0;
            for (int row = startRow; row < (startRow + boatLength); row++) {
                board[row][startColumn] = "B";
                coords[5 - boatLength][boat_section][0] = row + 1;
                coords[5 - boatLength][boat_section][1] = startColumn + 1;
                boat_section++;
            }
        }

        public int chooseBoatDirection(int boat_len) {
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
            clearScreen();
            return boat_direction;
        }

        public int chooseStartingRow(int max_row) {
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
            return boat_row;
        }

        public int chooseStartingColumn(int max_column) {
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
            clearScreen();
            return boat_column;
        }

        private boolean collisionCheck(int boat_direction, int boat_len, int boat_row, int boat_column) {
            boolean collision_check = false;
            if (boat_direction == 0) {
                int check_column = boat_column;
                while (!collision_check && check_column < (boat_column + boat_len)) {
                    if (board[boat_row][check_column].equals("B")) {
                        collision_check = true;
                    } else {
                        check_column++;
                    }
                }
            } else {
                int check_row = boat_row;
                while (!collision_check && check_row < (boat_row + boat_len)) {
                    if (board[check_row][boat_column].equals("B")) {
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
            // Set font for the grid labels
            boardDisplay.setFont(new Font("Monospaced", Font.BOLD, 15));
            boardDisplay.setColor(Color.BLACK);
            // Use a for loop to create the gridlines and place the grid labels
            for (int i = 1; i <= 10; i++) {
                boardDisplay.drawLine(i * 30, 0, i * 30, 329);
                boardDisplay.drawLine(0, i * 30, 329, i * 30);
                boardDisplay.drawString(String.valueOf(i), i * 30 + 10, 19);
                boardDisplay.drawString(String.valueOf(i), 10, i * 30 + 19);
            }
            // Label the display based on the which player it is
            if (name.equals("com")) {

                boardDisplay.setFont(new Font("Monospaced", Font.BOLD, 10));
                boardDisplay.drawString("COM", 2, 19);
            }
            else if (name.equals("player1")) boardDisplay.drawString("P1", 5, 19);
            else boardDisplay.drawString("P2", 5, 19);

            for (int boat_len = 5; boat_len > 1; boat_len--) {
                drawBoat(boat_len);
            }

            // Set font to represent hits and misses on the opp board
            boardDisplay.setFont(new Font("Monospaced", Font.BOLD, 48));

            // Loop though the opp targetGrid and print any hits or misses at their corresponding coordinates
            for (int row = 0; row < 10; row++) {
                for (int column = 0; column < 10; column++) {
                    if (opp.getTargetGrid()[row][column].equals("X")) {
                        boardDisplay.setColor(Color.RED);
                        boardDisplay.drawString("X", 30 * column + 31, 30 * row + 60);
                    }
                    else if (opp.getTargetGrid()[row][column].equals("O")) {
                        boardDisplay.setColor(Color.WHITE);
                        boardDisplay.drawString("O", 30 * column + 32, 30 * row + 59);
                    }
                }
            }
        }

        public void redrawTargetGridDisplay() {
            // use player's targetGridDisplay and player's targetGrid to update target display between rounds
            // Set font for the grid labels
            targetGridDisplay.setFont(new Font("Monospaced", Font.BOLD, 15));
            targetGridDisplay.setColor(Color.BLACK);
            // Use a for loop to create the gridlines and place the grid labels
            for (int i = 1; i <= 10; i++) {
                targetGridDisplay.drawLine(i * 30, 0, i * 30, 329);
                targetGridDisplay.drawLine(0, i * 30, 329, i * 30);
                targetGridDisplay.drawString(String.valueOf(i), i * 30 + 10, 19);
                targetGridDisplay.drawString(String.valueOf(i), 10, i * 30 + 19);
            }
            // Label the display based on the which player it is
            targetGridDisplay.setFont(new Font("Monospaced", Font.BOLD, 10));
            if (name.equals("player1")) targetGridDisplay.drawString("OPP1", 5, 19);
            else targetGridDisplay.drawString("OPP2", 5, 19);

            // Set font to represent hits and misses on the opp board
            targetGridDisplay.setFont(new Font("Monospaced", Font.BOLD, 48));

            // Loop though the player's targetGrid and print any hits or misses at their corresponding coordinates
            for (int row = 0; row < 10; row++) {
                for (int column = 0; column < 10; column++) {
                    if (targetGrid[row][column].equals("X")) {
                        targetGridDisplay.setColor(Color.RED);
                        targetGridDisplay.drawString("X", 30 * column + 31, 30 * row + 60);
                    }
                    else if (targetGrid[row][column].equals("O")) {
                        targetGridDisplay.setColor(Color.WHITE);
                        targetGridDisplay.drawString("O", 30 * column + 32, 30 * row + 59);
                    }
                }
            }
        }

        public void hideBoardDisplay() {
            // draw rectangle to cover player's boardDisplay so opponent can't see it during their turn
            boardDisplay.setColor(Color.CYAN);
            boardDisplay.fillRect(0, 0, 329, 329);
        }

        public void hideTargetGridDisplay() {
            // draw rectangle to cover player's targetGridDisplay so opponent can't see it during their turn
            targetGridDisplay.setColor(Color.CYAN);
            targetGridDisplay.fillRect(0, 0, 329, 329);
        }

        public void populateBoard() {
            // Initialize necessary variables for boat placement
            int boat_row = 0, boat_column = 0, boat_direction;
            if (!name.equals("com")) {
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
                            boat_row = this.chooseStartingRow(max_row) - 1;
                            System.out.println();

                            // Have the user input a starting column for the new boat
                            boat_column = this.chooseStartingColumn(max_column) - 1;
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
                            boat_row = this.chooseStartingRow(max_row) - 1;
                            System.out.println();
                            boat_column = this.chooseStartingColumn(max_column) - 1;
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
                }
            } else {
                Random rand = new Random();
                for (int boat_len = 5; boat_len > 1; boat_len--) {
                    boat_direction = rand.nextInt(2);
                    boolean collision = true;
                    if (boat_direction == 0) {
                        while (collision) {
                            boat_row = rand.nextInt(10);
                            boat_column = rand.nextInt(10 - boat_len + 1);
                            collision = this.collisionCheck(boat_direction, boat_len, boat_row, boat_column);
                        }
                        this.placeBoatHorizontally(boat_row, boat_column, boat_len);
                    } else {
                        while (collision) {
                            boat_row = rand.nextInt(10 - boat_len + 1);
                            boat_column = rand.nextInt(10);
                            collision = this.collisionCheck(boat_direction, boat_len, boat_row, boat_column);
                        }
                        this.placeBoatVertically(boat_row, boat_column, boat_len);
                    }
                }

            }
        }


        public void didOneOfMyBoatsGetSunk(Player opp) {
            if (opp.getRecentBoatSunk() != opp.getPreviousBoatSunk()) {
                if (opp.getRecentBoatSunk() == 0) System.out.println("Your 5 unit boat was sunk!\n");
                else if (opp.getRecentBoatSunk() == 1) System.out.println("Your 4 unit boat was sunk!\n");
                else if (opp.getRecentBoatSunk() == 2) System.out.println("Your 3 unit boat was sunk!\n");
                else System.out.println("Your 2 unit boat was sunk!\n");
            }
        }

        public void didISinkABoat(Player opp) {
            for (int boat = 0; boat < 4; boat++) {
                int boat_check = 0;
                for (int section = 0; section < opp.getCoords()[boat].length; section++) {
                    if (opp.getBoard()[opp.getCoords()[boat][section][0] - 1]
                            [opp.getCoords()[boat][section][1] - 1].equals("B")) boat_check++;
                }
                if (boat_check == 0 && boatsSunk[boat] != 1) {
                    if (boat == 0) {
                        boatsSunk[boat] = 1;
                        currentBoatsSunk.add(boat);
                        if (!name.equals("com")) System.out.println("You sank your opponent's 5 unit boat\n");
                    } else if (boat == 1) {
                        boatsSunk[boat] = 1;
                        currentBoatsSunk.add(boat);
                        if (!name.equals("com")) System.out.println("You sank your opponent's 4 unit boat\n");
                    } else if (boat == 2) {
                        boatsSunk[boat] = 1;
                        currentBoatsSunk.add(boat);
                        if (!name.equals("com")) System.out.println("You sank your opponent's 3 unit boat\n");
                    } else {
                        boatsSunk[boat] = 1;
                        currentBoatsSunk.add(boat);
                        if (!name.equals("com")) System.out.println("You sank your opponent's 2 unit boat\n");
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        //Main gameplay here
        System.out.println("Welcome to Battleship!");
        String mode = modeSelect();
        clearScreen();

        Scanner gameInput = new Scanner(System.in);
        String retreatCheck, defaultWinner = "";
        if (mode.equals("com")) {
            Player computer = new Player("com");
            Player P1 = new Player("player1");

            computer.populateBoard();
            computer.hideBoardDisplay();
            P1.populateBoard();

            clearScreen();
            System.out.print("Type anything to start the game -> ");
            gameInput.nextLine();
            clearScreen();

            int round = 1;
            while (winConditionCheck(computer, P1) && defaultWinner.isEmpty()) {
                System.out.println("(Round " + round +")\n");
                System.out.println("Computer's turn:");
                if (P1.getRecentBoatSunk() != P1.getPreviousBoatSunk()) {
                    P1.addPreviousBoatSunk(P1.getRecentBoatSunk());
                }
                pvp(computer, P1);
                computer.didISinkABoat(P1);
                P1.redrawBoardDisplay(computer);
                P1.didOneOfMyBoatsGetSunk(computer);

                if (!winConditionCheck(computer, P1)) continue;

                System.out.println("\nYour turn:");
                retreatCheck = retreatOrAttack();
                if (retreatCheck.equals("retreat")) {
                    defaultWinner = "com";
                    continue;
                }
                if (computer.getRecentBoatSunk() != computer.getPreviousBoatSunk()) {
                    computer.addPreviousBoatSunk(computer.getRecentBoatSunk());
                }
                pvp(P1, computer);
                P1.didISinkABoat(computer);
                P1.redrawTargetGridDisplay();

                System.out.print("Type anything to start the next round -> ");
                gameInput.nextLine();
                clearScreen();
                round++;
            }
            // com mode endgame stuff
            clearScreen();
            if (!defaultWinner.isEmpty()) System.out.println("You retreated, the computer won by default!");
            else {
                String winner = winnerCheck(P1, computer);
                if (winner.equals("com")) System.out.println("Game over, the computer wins!");
                else System.out.println("Congratulations, you won!");
            }
            P1.redrawBoardDisplay(computer);
            P1.hideTargetGridDisplay();
            computer.redrawBoardDisplay(P1);
        } else {
            Player P1 = new Player("player1");
            Player P2 = new Player("player2");

            System.out.println("Player 1 place your boats\n");
            P1.populateBoard();
            System.out.println("Type anything to continue -> ");
            gameInput.nextLine();
            P1.hideBoardDisplay();
            P1.hideTargetGridDisplay();
            clearScreen();

            System.out.println("Player 2 place your boats\n");
            P2.populateBoard();
            System.out.println("Type anything to start the game -> ");
            gameInput.nextLine();
            P2.hideBoardDisplay();
            P2.hideTargetGridDisplay();
            clearScreen();

            int round = 0;
            Random startingPlayer = new Random();
            int attacker = startingPlayer.nextInt(2) + 1;

            while (winConditionCheck(P1, P2) && defaultWinner.isEmpty()) {
                System.out.println("(Round " + (round / 2 + 1) + ")\n");
                System.out.print("Player " + attacker + ": Type anything to start your turn ->");
                gameInput.nextLine();
                System.out.println();

                if (attacker == 1) {
                    P1.redrawBoardDisplay(P2);
                    P1.redrawTargetGridDisplay();

                    if (P1.getPreviousBoatSunk() != P1.getRecentBoatSunk()) {
                        P1.addPreviousBoatSunk(P1.getRecentBoatSunk());
                    }

                    P1.didOneOfMyBoatsGetSunk(P2);

                    retreatCheck = retreatOrAttack();
                    if (retreatCheck.equals("retreat")) {
                        defaultWinner = "player2";
                        continue;
                    }

                    pvp(P1, P2);
                    P1.redrawTargetGridDisplay();
                    P1.didISinkABoat(P2);

                    System.out.print("Type anything to end your turn -> ");
                    gameInput.nextLine();
                    clearScreen();
                    attacker = 2;
                    P1.hideBoardDisplay();
                    P1.hideTargetGridDisplay();
                } else {
                    P2.redrawBoardDisplay(P1);
                    P2.redrawTargetGridDisplay();

                    if (P2.getRecentBoatSunk() != P2.getPreviousBoatSunk()) {
                        P2.addPreviousBoatSunk(P2.getRecentBoatSunk());
                    }

                    P2.didOneOfMyBoatsGetSunk(P1);

                    retreatCheck = retreatOrAttack();
                    if (retreatCheck.equals("retreat")) {
                        defaultWinner = "player1";
                        continue;
                    }

                    pvp(P2, P1);
                    P2.redrawTargetGridDisplay();
                    P2.didISinkABoat(P1);

                    System.out.print("Type anything to end your turn -> ");
                    gameInput.nextLine();
                    clearScreen();
                    attacker = 1;
                    P2.hideBoardDisplay();
                    P2.hideTargetGridDisplay();
                }
                round++;
            }

            //vs endgame stuff
            clearScreen();
            if (!defaultWinner.isEmpty()) {
                if (defaultWinner.equals("player1")) {
                    System.out.println("Player 2 retreated, Player 1 wins by default!");
                } else {
                    System.out.println("Player 1 retreated, Player 2 wins by default!");
                }
            } else {
                String winner = winnerCheck(P1, P2);
                if (winner.equals("player1")) System.out.println("Player 1 wins!");
                else System.out.println("Player 2 wins!");
            }
            P1.redrawBoardDisplay(P2);
            P1.hideTargetGridDisplay();
            P2.redrawBoardDisplay(P1);
            P2.hideTargetGridDisplay();
        }
        gameInput.close();
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


    public static String retreatOrAttack() {
        Scanner input = new Scanner(System.in);
        String user_choice = "";
        // Ask the player if they wish to attack or retreat until they choose one of the two options
        while (!user_choice.equals("retreat") && !user_choice.equals("a")) {
            System.out.print("Type \"retreat\" to forfeit the game or type \"a\" to continue the game: ");
            user_choice = input.next();
            if (!user_choice.equals("retreat") && !user_choice.equals("a")) System.out.println("\nInvalid input\n");
        }
        return user_choice;
    }


    public static int selectRocketRow() {
        Scanner input = new Scanner(System.in);
        int rocket_row;
        // Have player input a row coordinate
        do {
            System.out.print("Pick a row (1-10): ");
            while (!input.hasNextInt()) {
                System.out.println("\nInvalid input\n");
                System.out.print("Pick a row (1-10): ");
                input.next();
            }
            rocket_row = input.nextInt();
            if (rocket_row < 1 || rocket_row > 10) System.out.println("\nInvalid input\n");
        } while (rocket_row < 1 || rocket_row > 10);
        return rocket_row;
    }


    public static int selectRocketColumn() {
        Scanner input = new Scanner(System.in);
        int rocket_column;
        // Have player input a column coordinate
        do {
            System.out.print("Pick a column (1-10): ");
            while (!input.hasNextInt()) {
                System.out.println("\nInvalid input\n");
                System.out.print("Pick a column (1-10): ");
                input.next();
            }
            rocket_column = input.nextInt();
            if (rocket_column < 1 || rocket_column > 10) System.out.println("\nInvalid input\n");
        } while (rocket_column < 1 || rocket_column > 10);
        return rocket_column;
    }


    public static void pvp(Player attacker, Player defender) {
        int rocket_row, rocket_column;
        if (attacker.getName().equals("com")) {
            Random rand = new Random();
            do {
                rocket_row = rand.nextInt(10);
                rocket_column = rand.nextInt(10);
            } while (defender.getBoard()[rocket_row][rocket_column].equals("O") ||
                    defender.getBoard()[rocket_row][rocket_column].equals("X"));
        } else {
            do {
                rocket_row = selectRocketRow() - 1;
                rocket_column = selectRocketColumn() - 1;
                if (defender.getBoard()[rocket_row][rocket_column].equals("O") ||
                        defender.getBoard()[rocket_row][rocket_column].equals("X"))
                    System.out.println("\nYou've already fired a rocket at this coordinate. Pick a new coordinate\n");
            } while (defender.getBoard()[rocket_row][rocket_column].equals("O") ||
                    defender.getBoard()[rocket_row][rocket_column].equals("X"));
        }
        // Update attacker's targetGrid and defender's board with hit or miss
        if (defender.getBoard()[rocket_row][rocket_column].equals("B")) {
            defender.getBoard()[rocket_row][rocket_column] = "X";
            attacker.getTargetGrid()[rocket_row][rocket_column] = "X";
            if (!attacker.getName().equals("com")) System.out.println("\nHit!\n");
            else System.out.printf("The computer hit you at the coordinate [%d, %d]!\n",
                    rocket_row + 1, rocket_column + 1);
        } else {
            defender.getBoard()[rocket_row][rocket_column] = "O";
            attacker.getTargetGrid()[rocket_row][rocket_column] = "O";
            if (!attacker.getName().equals("com")) System.out.println("\nMiss!\n");
            else System.out.printf("The computer missed you at the coordinate [%d, %d]!\n",
                    rocket_row + 1, rocket_column + 1);
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