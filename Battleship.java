import java.awt.*;
import java.lang.Math;
import java.util.Random;
import java.util.Arrays;
import java.util.Scanner;
public class Battleship {
    // These arrays represent each player's board. This is where their boats will be stored
    // These boards also store the player's opponent's hits and misses
    public static String[][] COM_BOARD = {{"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
        {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
        {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
        {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
        {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
        {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
        {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
        {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
        {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
        {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"}};

    public static String[][] P1_BOARD = {{"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"}};

    public static String[][] P2_BOARD = {{"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"}};

    // These arrays represent the player's target grid
    // This is where the player's hits and misses are stored
    public static String[][] P1_OPP_BOARD = {{"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"}};

    public static String[][] P2_OPP_BOARD = {{"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"}};

    // These arrays represent the players' boat coordinates
    // The coordinate pairs for each section of each boat are stored here
    public static int[][][] COM_COORDS = {{{-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}},
            {{-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}},
            {{-1, -1}, {-1, -1}, {-1, -1}},
            {{-1, -1}, {-1, -1}}};

    public static int[][][] P1_COORDS = {{{-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}},
            {{-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}},
            {{-1, -1}, {-1, -1}, {-1, -1}},
            {{-1, -1}, {-1, -1}}};

    public static int[][][] P2_COORDS = {{{-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}},
            {{-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}},
            {{-1, -1}, {-1, -1}, {-1, -1}},
            {{-1, -1}, {-1, -1}}};

    // These arrays represent the complete list of boats that each player has sunk
    // Each index is set to 0, then incremented to 1 if the corresponding boat has been sunk
    public static int[] COM_BOATS_SUNK = {0, 0, 0, 0};

    public static int[] P1_BOATS_SUNK = {0, 0, 0, 0};

    public static int[] P2_BOATS_SUNK = {0, 0, 0, 0};

    // These arrays represent the incomplete list of boats that each player has sunk
    // These arrays are copies of the corresponding boats_sunk lists from before the opponent's most recent turn
    public static int[] P1_PREVIOUS_BOATS_SUNK = {0, 0, 0, 0};

    public static int[] P2_PREVIOUS_BOATS_SUNK = {0, 0, 0, 0};


    public static void main(String[] args) {
        // Welcome player(s) to the game and have them select a mode
        System.out.println("Welcome to Battleship!");
        String mode = modeSelect();
        clearScreen();

        // Initialize Scanner object and variables needed for retreat functionality
        Scanner input = new Scanner(System.in);
        String retreat_check, default_winner = "";

        // This section of code is for the com game mode
        if (mode.equals("com")) {

            // Create DisplayPanels representing the player's own board and their target grid
            Graphics boat_grid = createBattlefield();
            Graphics opp_board = createBattlefield();
            redrawBoard(opp_board, 3);

            // Place the computers boats and have the player place their boats, then start the game
            populateComBoard();
            populatePlayerBoard(boat_grid, 1, P1_BOARD, P1_COORDS);
            System.out.print("Type anything to start the game -> ");
            input.nextLine();
            clearScreen();

            // Initialize variables needed for the game loop, then begin the game loop
            int round_num = 1;
            int player = 0;

            // This loop ends when either the player or the computer has no boats left on their board
            while (winConditionCheck(mode)) {

                // Print the round number and show the results of the computer's turn
                System.out.println("(Round " + round_num +")\n");
                System.out.println("Computer's turn:");
                P1_PREVIOUS_BOATS_SUNK = Arrays.copyOf(P1_BOATS_SUNK, P1_BOATS_SUNK.length);
                comVsPlayerRound();
                oppBoatSinkCheck(P1_BOARD, P1_COORDS, P1_BOATS_SUNK, player);
                drawOppBoard(boat_grid, P1_BOARD);
                playerBoatSinkCheck(P1_PREVIOUS_BOATS_SUNK, P1_BOATS_SUNK);

                // If the computer won during its turn, end the game loop
                if (!winConditionCheck(mode)) continue;
                player = 1;

                // Tell the player it is their turn, then have them fire their rocket
                System.out.println("\nYour turn:");
                retreat_check = playerVsComRound();

                // If the player chose to retreat during their turn, end the game loop and have the com win by default
                if (retreat_check.equals("retreat")) {
                    default_winner = "com";
                    break;
                }

                // Check to see if the player sunk one of the computer's boats
                oppBoatSinkCheck(COM_BOARD, COM_COORDS, COM_BOATS_SUNK, player);

                // Update the player's target grid with a hit or a miss
                drawOppBoard(opp_board, P1_OPP_BOARD);

                // Switch players and have the player input something to start the next round
                player = 0;
                System.out.print("Type \"next\" to start the next round -> ");
                input.nextLine();
                clearScreen();
                round_num++;
            }
        // This section of code is for the vs game mode
        } else {
            // Create the boat grid to be used by both players
            Graphics boat_grid = createBattlefield();

            // Have player 1 place their boats, then reset the board for player 2 once player 1 is done
            System.out.println("Player 1 place your boats\n");
            populatePlayerBoard(boat_grid, 1, P1_BOARD, P1_COORDS);
            System.out.print("Type anything to continue: ");
            input.nextLine();
            clearScreen();
            redrawBoard(boat_grid, 2);

            // Have player 2 place their boats, then auto-select the starting player and begin the game
            System.out.println("Player 2 place your boats\n");
            populatePlayerBoard(boat_grid, 2, P2_BOARD, P2_COORDS);
            System.out.print("Type anything to start the game: ");
            input.nextLine();
            int round_num = 0;
            Random player_selection = new Random();
            int player = player_selection.nextInt(2) + 1;

            // Reset the boat grid with the player who is starting the game
            redrawBoard(boat_grid, player);

            // Create the target grid to be used by both players and label it as the starting player's opp board
            Graphics opp_board = createBattlefield();
            redrawBoard(opp_board, player + 2);
            clearScreen();

            // Have the game loop infinitely until one player has no boats remaining
            while (winConditionCheck(mode)) {

                // Print the current round number and the player whose turn it is
                System.out.println("(Round " + (round_num / 2 + 1) + ")\n");
                System.out.print("Player " + player + ": Type anything to start your turn ->");
                input.nextLine();
                System.out.println();

                // This section of code is for player 1's turn
                if (player == 1) {
                    switchPlayerBoards(boat_grid, P1_COORDS, P1_BOARD, player);
                    switchOppBoards(opp_board, P1_OPP_BOARD, player);
                    P2_PREVIOUS_BOATS_SUNK = Arrays.copyOf(P2_BOATS_SUNK, P2_BOATS_SUNK.length);
                    playerBoatSinkCheck(P1_PREVIOUS_BOATS_SUNK, P1_BOATS_SUNK);
                    retreat_check = playerVsPlayerRound(player);
                    if (retreat_check.equals("retreat")) {
                        default_winner = "player2";
                        break;
                    }
                    oppBoatSinkCheck(P2_BOARD, P2_COORDS, P2_BOATS_SUNK, player);
                    drawOppBoard(opp_board, P1_OPP_BOARD);
                    player = 2;
                // This section of code is for player 2's turn
                } else {
                    switchPlayerBoards(boat_grid, P2_COORDS, P2_BOARD, player);
                    switchOppBoards(opp_board, P2_OPP_BOARD, player);
                    P1_PREVIOUS_BOATS_SUNK = Arrays.copyOf(P1_BOATS_SUNK, P1_BOATS_SUNK.length);
                    playerBoatSinkCheck(P2_PREVIOUS_BOATS_SUNK, P2_BOATS_SUNK);
                    retreat_check = playerVsPlayerRound(player);
                    if (retreat_check.equals("retreat")) {
                        default_winner = "player1";
                        break;
                    }
                    oppBoatSinkCheck(P1_BOARD, P1_COORDS, P1_BOATS_SUNK, player);
                    drawOppBoard(opp_board, P2_OPP_BOARD);
                    player = 1;
                }

                // Tell the player to input anything to end the turn
                System.out.print("Type anything to end your turn -> ");
                input.nextLine();

                // clear both displays and console so players can't screen peek
                clearScreen();
                redrawBoard(boat_grid, player);
                redrawBoard(opp_board, player + 2);
                round_num++;
            }
        }

        // Once the game ends clear the screen and declare the winner
        clearScreen();

        // If a player retreated at any point during the game, their opponent wins by default
        if (!default_winner.isEmpty()) {
            if (default_winner.equals("com")) System.out.println("You retreated, the computer won by default!");
            else if (default_winner.equals("player1")) System.out.println("Player 2 retreated, Player 1 wins by default!");
            else System.out.println("Player 1 retreated, Player 2 wins by default!");

        // Otherwise, the winner will be whoever has boats remaining on their board
        } else {
            Graphics player_or_com = createBattlefield();
            Graphics player1_or_player2 = createBattlefield();
            String win = winnerCheck(mode);
            if (mode.equals("com")) {
                redrawBoard(player_or_com, 5);
                for (int boat_len = 5; boat_len >= 2; boat_len--) {
                    drawBoat(player_or_com, COM_COORDS, boat_len);
                }
                drawOppBoard(player_or_com , COM_BOARD);
                redrawBoard(player1_or_player2, 1);
                for (int boat_len = 5; boat_len >= 2; boat_len--) {
                    drawBoat(player1_or_player2, P1_COORDS, boat_len);
                }
                drawOppBoard(player1_or_player2, P1_BOARD);
                if (win.equals("com")) System.out.println("Game over, the computer won!");
                else System.out.println("Congratulations, you won!");
            } else {
                redrawBoard(player_or_com, 1);
                for (int boat_len = 5; boat_len >= 2; boat_len--) {
                    drawBoat(player_or_com, P1_COORDS, boat_len);
                }
                drawOppBoard(player_or_com , P1_BOARD);
                redrawBoard(player1_or_player2, 2);
                for (int boat_len = 5; boat_len >= 2; boat_len--) {
                    drawBoat(player1_or_player2, P2_COORDS, boat_len);
                }
                drawOppBoard(player1_or_player2, P2_BOARD);
                if (win.equals("player1")) System.out.println("Player 1 wins!");
                else System.out.println("Player 2 wins!");
            }
        }
    }


    // This function is used to create the DrawingPanel objects that will be used to display the player's boards
    public static Graphics createBattlefield() {
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

        // return the graphics object so it can be used for updates by other functions
        return g;
    }


    // This function is used to reset the DrawingPanel to its initial state with gridlines and the proper label
    public static void redrawBoard(Graphics g, int player) {
        // Reset DrawingPanel to its initial state
        g.setColor(Color.CYAN);
        g.fillRect(0, 0, 329, 329);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Monospaced", Font.BOLD, 15));
        for (int i = 1; i <= 10; i++) {
            g.drawLine(i * 30, 0, i * 30, 329);
            g.drawLine(0, i * 30, 329, i * 30);
            g.drawString(String.valueOf(i), i * 30 + 10, 19);
            g.drawString(String.valueOf(i), 10, i * 30 + 19);
        }

        // Label the board according to which player it is currently representing
        if (player == 1) g.drawString("P1", 5, 19);
        else if (player == 2) g.drawString("P2", 5, 19);
        else if (player == 3) {
            g.setFont(new Font("Monospaced", Font.BOLD, 10));
            g.drawString("OPP1", 2, 19);
        }
        else if (player == 4) {
            g.setFont(new Font("Monospaced", Font.BOLD, 10));
            g.drawString("OPP2", 2, 19);
        } else {
            g.setFont(new Font("Monospaced", Font.BOLD, 10));
            g.drawString("COM", 2, 19);
        }
    }


    // This function is used to print the hits and misses on the DrawingPanel that represents the player's opp board
    public static void drawOppBoard(Graphics g, String[][] board) {
        // Set font to represent hits and misses on the opp board
        g.setFont(new Font("Monospaced", Font.BOLD, 48));

        // Loop though the opp board array and print any hits or misses at their corresponding coordinates
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++) {
                if (board[row][column].equals("X")) {
                    g.setColor(Color.RED);
                    g.drawString("X", 30 * column + 31, 30 * row + 60);
                }
                else if (board[row][column].equals("O")) {
                    g.setColor(Color.WHITE);
                    g.drawString("O", 30 * column + 32, 30 * row + 59);
                }
            }
        }
    }


    // This function is used to print boats on the DrawingPanel that represents the player's board
    public static void drawBoat(Graphics g, int[][][] coords, int boat_len) {
        // Set color to LIGHT GRAY for the inner shading of each boat
        g.setColor(Color.LIGHT_GRAY);

        // Create a variable that helps index the coordinate list properly
        int current_boat_index = 5 - boat_len;

        // Get the starting coordinates from the coordinate list for the current boat
        int start_row = coords[current_boat_index][0][0], start_column = coords[current_boat_index][0][1];

        // If first section row is less than second section row, boat is vertical
        // Otherwise, the boat is horizontal --> Print boat in the proper direction and outline it in DARK GRAY
        if (coords[current_boat_index][0][0] < coords[current_boat_index][1][0]) {
            g.fillOval(start_column * 30, start_row * 30, 30, boat_len * 30);
            g.setColor(Color.DARK_GRAY);
            g.drawOval(start_column * 30, start_row * 30, 30, boat_len * 30);
        } else {
            g.fillOval(start_column * 30, start_row * 30, boat_len * 30, 30);
            g.setColor(Color.DARK_GRAY);
            g.drawOval(start_column * 30, start_row * 30, boat_len * 30, 30);
        }
    }


    // This function is used to switch which player board is displayed during the vs mode
    // This function ensures that the players can't see the other players' boats during the game
    public static void switchPlayerBoards(Graphics g, int[][][] player_coords, String[][] player_board, int player) {
        // Reset board and label it with the new player
        redrawBoard(g, player);

        // Print all the player's boats
        for (int boat_len = 5; boat_len >= 2; boat_len--) {
            drawBoat(g, player_coords, boat_len);
        }

        // Print all the opponent's hits or misses
        drawOppBoard(g, player_board);
    }


    // This function is used to switch which opp board is displayed in the vs mode
    // This ensures that the players are only seeing their own hits and misses on their opponent
    public static void switchOppBoards(Graphics g, String[][] player_opp_board, int player) {
        // Reset board and label it as the new player's opp board
        redrawBoard(g, player + 2);

        // Print all the player's hits and misses
        drawOppBoard(g, player_opp_board);
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


    // This function is used to clear the console when necessary
    public static void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }


    // This function is used to place the computer's boats into its board and coordinate arrays to set up the game
    public static void populateComBoard() {
        // Initialize necessary variables for boat placement
        int boat_row = 0, boat_column = 0;

        // Loop through all boats (5, 4, 3, and 2 length boats)
        for (int boat_len = 5; boat_len > 1; boat_len--) {

            // Randomly select a direction for the boat (left->right or top->bottom)
            int boat_direction = (int)Math.round(Math.random());

            // Initialize a boolean flag for the while loop that contains the boat placement process
            boolean collision = true;

            // Place boat horizontally if boat_direction == 0
            if (boat_direction == 0) {

                // Randomly select coordinates until new boat doesn't cause collision
                while (collision) {

                    // Randomly pick coordinates (making sure that the boat doesn't go out of bounds)
                    boat_row = (int)(Math.random() * 10);
                    boat_column = (int)(Math.random() * (10 - boat_len + 1));

                    // Check for collision
                    int collision_check = 0;
                    for (int check_column = boat_column; check_column < (boat_column + boat_len); check_column++) {
                        if (COM_BOARD[boat_row][check_column].equals("C")) {
                            collision_check++;
                            break;
                        }
                    }

                    // If no collision, keep current starting coordinates for new boat
                    if (collision_check == 0) {
                        collision = false;
                    }
                }

                // Add new boat to COM_BOARD & boat coords to COM_COORDS
                int boat_section = 0;
                for (int column = boat_column; column < (boat_column + boat_len); column++) {
                    COM_BOARD[boat_row][column] = "C";
                    COM_COORDS[5 - boat_len][boat_section][0] = boat_row + 1;
                    COM_COORDS[5 - boat_len][boat_section][1] = column + 1;
                    boat_section++;
                }
            }

            // Same process as a horizontal boat, except boat will be vertical if boat_direction == 1
            else {
                while (collision) {
                    boat_row = (int)(Math.random() * (10 - boat_len + 1));
                    boat_column = (int)(Math.random() * 10);
                    int collision_check = 0;
                    for (int check_row = boat_row; check_row < (boat_row + boat_len); check_row++) {
                        if (COM_BOARD[check_row][boat_column].equals("C")) {
                            collision_check++;
                            break;
                        }
                    }
                    if (collision_check == 0) {
                        collision = false;
                    }
                }
                // Add boat to COM_BOARD & boat coords to COM_COORDS
                int boat_section = 0;
                for (int row = boat_row; row < (boat_row + boat_len); row++) {
                    COM_BOARD[row][boat_column] = "C";
                    COM_COORDS[5 - boat_len][boat_section][0] = row + 1;
                    COM_COORDS[5 - boat_len][boat_section][1] = boat_column + 1;
                    boat_section++;
                }
            }
        }
    }


    // This function is used to place the player's boats into their board and coordinate arrays to set up the game
    public static void populatePlayerBoard(Graphics g, int player, String[][] board, int[][][] coords) {
        // Initialize necessary variables for boat placement
        int boat_row = 0, boat_column = 0, boat_direction;

        // Reset DrawingPanel to represent current player
        redrawBoard(g, player);

        // Loop through each boat
        for (int boat_len = 5; boat_len > 1; boat_len--) {
            Scanner input = new Scanner(System.in);

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
                    do {
                        System.out.print("Choose a starting row (1-10): ");
                        while (!input.hasNextInt()) {
                            System.out.println("\nInvalid input\n");
                            System.out.print("Choose a starting row (1-10): ");
                            input.next();
                        }
                        boat_row = input.nextInt();
                        if (boat_row < 1 || boat_row > max_row) System.out.println("\nInvalid input\n");
                    } while (boat_row < 1 || boat_row > max_row);
                    System.out.println();

                    // Have the user input a starting column for the new boat
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
                    System.out.println();

                    // Check to see if these coordinates make the new boat collide with any other boats
                    // If a collision is detected, redo coordinate selection
                    int collision_check = 0;
                    for (int check_column = boat_column; check_column < (boat_column + boat_len); check_column++) {
                        if (board[boat_row - 1][check_column - 1].equals("U")) {
                            collision_check++;
                            break;
                        }
                    }

                    // If no collision, end the input loop and stick with current starting coords
                    if (collision_check == 0) collision = false;
                    else System.out.println("""
                            Collision detected, try different coordinates
                            Remember, you're placing a boat left->right
                            """);
                }

                // Add new boat to the player's board array & boat coords to the player's coord array
                int boat_section = 0;
                for (int column = boat_column; column < (boat_column + boat_len); column++) {
                    board[boat_row - 1][column - 1] = "U";
                    coords[5 - boat_len][boat_section][0] = boat_row;
                    coords[5 - boat_len][boat_section][1] = column;
                    boat_section++;
                }

            // The boat is placed vertically if boat_direction == 1
            } else {
                int max_column = 10, max_row = 11 - boat_len;
                while (collision) {

                    // Have the player input a starting row for their new boat
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
                    System.out.println();

                    // Have the player input a starting column for their new boat
                    do {
                        System.out.print("Choose a starting column (1-10): ");
                        while (!input.hasNextInt()) {
                            System.out.println("\nInvalid input\n");
                            System.out.print("Choose a starting column (1-10): ");
                            input.next();
                        }
                        boat_column = input.nextInt();
                        if (boat_column < 1 || boat_column > max_column) System.out.println("\nInvalid input\n");
                    } while (boat_column < 1 || boat_column > max_column);
                    System.out.println();

                    // Check to see if these coordinates make the new boat collide with any other boats
                    // If a collision is detected, redo coordinate selection
                    int collision_check = 0;
                    for (int check_row = boat_row; check_row < (boat_row + boat_len); check_row++) {
                        if (board[check_row - 1][boat_column - 1].equals("U")) {
                            collision_check++;
                            break;
                        }
                    }

                    // If no collision, end the input loop and stick with current starting coords
                    if (collision_check == 0) collision = false;
                    else System.out.println("""
                            Collision detected, try different coordinates
                            Remember, you're placing a boat top->bottom
                            """);
                }

                // Add new boat to the player's board & boat coords to the player's coord array
                int boat_section = 0;
                for (int row = boat_row; row < (boat_row + boat_len); row++) {
                    board[row - 1][boat_column - 1] = "U";
                    coords[5 - boat_len][boat_section][0] = row;
                    coords[5 - boat_len][boat_section][1] = boat_column;
                    boat_section++;
                }
            }

            // Print current boat onto DrawingPanel
            drawBoat(g, coords, boat_len);
            clearScreen();
        }
    }


    // This function allows the computer to fire a rocket at the player's board in the com mode
    // The coordinate selection from the computer is entirely random, with no encoded strategy
    public static void comVsPlayerRound() {
        // Initialize variables necessary for tracking coordinate selection
        int rocket_row, rocket_column;

        // Randomly choose coordinate until a unique coordinate has been found
        do {
            rocket_row = (int)(Math.random() * 10);
            rocket_column = (int)(Math.random() * 10);
        } while (P1_BOARD[rocket_row][rocket_column].equals("O") || P1_BOARD[rocket_row][rocket_column].equals("X"));

        // Update the player's board with a hit or a miss and tell the player where the computer hit or missed them
        if (P1_BOARD[rocket_row][rocket_column].equals("U")) {
            P1_BOARD[rocket_row][rocket_column] = "X";
            System.out.printf("The computer hit you at the coordinate [%d, %d]!\n", rocket_row + 1, rocket_column + 1);
        } else {
            P1_BOARD[rocket_row][rocket_column] = "O";
            System.out.printf("The computer missed you at the coordinate [%d, %d]!\n",
                    rocket_row + 1, rocket_column + 1);
        }
    }


    // This function allows the player to fire a rocket at the computer's board in the com mode
    public static String playerVsComRound() {
        // Initialize necessary variables and a Scanner for user input
        int rocket_row, rocket_column;
        Scanner input = new Scanner(System.in);
        String user_choice = "";

        // Ask the player if they wish to attack or retreat until they choose one of the two options
        while (!user_choice.equals("retreat") && !user_choice.equals("a")) {
            System.out.print("Type \"retreat\" to forfeit the game or type \"a\" to continue the game: ");
            user_choice = input.next();
            if (!user_choice.equals("retreat") && !user_choice.equals("a")) System.out.println("\nInvalid input\n");
        }

        // End function here if the player chose to retreat
        if (user_choice.equals("retreat")) return user_choice;

        // If not continue and have the player choose coordinates to fire upon
        else {

            // Prompt player to input coordinates to fire their rocket
            System.out.println("\n__Pick coordinates to fire your rocket__");

            // Repeat rocket coordinate selection until a unique coordinate has been chosen
            do {

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
                if (COM_BOARD[rocket_row - 1][rocket_column - 1].equals("O") ||
                        COM_BOARD[rocket_row - 1][rocket_column - 1].equals("X"))
                    System.out.println("\nYou've already fired a rocket at this coordinate. Pick a new coordinate\n");
            } while (COM_BOARD[rocket_row - 1][rocket_column - 1].equals("O") ||
                    COM_BOARD[rocket_row - 1][rocket_column - 1].equals("X"));

            // Update the computer's board & player's opp board with a hit or a miss
            if (COM_BOARD[rocket_row - 1][rocket_column - 1].equals("C")) {
                COM_BOARD[rocket_row - 1][rocket_column - 1] = "X";
                P1_OPP_BOARD[rocket_row - 1][rocket_column - 1] = "X";
                System.out.println("\nHit!\n");
            } else {
                COM_BOARD[rocket_row - 1][rocket_column - 1] = "O";
                P1_OPP_BOARD[rocket_row - 1][rocket_column - 1] = "O";
                System.out.println("\nMiss!\n");
            }
        }
        return user_choice;
    }


    // This function is used as the main game function for the vs mode
    // The current player fires a rocket at the opposing player, and updates are made to the corresponding board arrays
    public static String playerVsPlayerRound(int player) {
        // Initialize necessary variables and Scanner object for input
        Scanner input = new Scanner(System.in);
        String user_choice = "";
        int rocket_row, rocket_column;

        // Ask player if they wish to attack or retreat until they choose one of the two options
        while (!user_choice.equals("retreat") && !user_choice.equals("a")) {
            System.out.print("Type \"retreat\" to forfeit the game or type \"a\" to continue the game: ");
            user_choice = input.next();
            if (!user_choice.equals("retreat") && !user_choice.equals("a")) System.out.println("\nInvalid input\n");
        }

        // End the function here if the player chooses to retreat
        if (user_choice.equals("retreat")) return user_choice;
        else {

            // Prompt player to input coordinates to fire their rocket
            System.out.println("\n__Pick coordinates to fire your rocket__");

            // This section of code is used if player 1 is currently firing at player 2
            if (player == 1) {
                // Loop coordinate selection until a unique coordinate is chosen
                do {
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
                    if (P2_BOARD[rocket_row - 1][rocket_column - 1].equals("O") ||
                            P2_BOARD[rocket_row - 1][rocket_column - 1].equals("X"))
                        System.out.println("\nYou've already fired a rocket at this coordinate. Pick a new coordinate\n");
                } while (P2_BOARD[rocket_row - 1][rocket_column - 1].equals("O") ||
                        P2_BOARD[rocket_row - 1][rocket_column - 1].equals("X"));

            // Same process but this section is for when player 2 is firing at player 1
            } else {
                do {
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
                    if (P1_BOARD[rocket_row - 1][rocket_column - 1].equals("O") ||
                            P1_BOARD[rocket_row - 1][rocket_column - 1].equals("X"))
                        System.out.println("\nYou've already fired a rocket at this coordinate. Pick a new coordinate\n");
                } while (P1_BOARD[rocket_row - 1][rocket_column - 1].equals("O") ||
                        P1_BOARD[rocket_row - 1][rocket_column - 1].equals("X"));
            }
            // Update the opponent's board & player's opp board with a hit or a miss
            if (player == 1) {
                if (P2_BOARD[rocket_row - 1][rocket_column - 1].equals("U")) {
                    P2_BOARD[rocket_row - 1][rocket_column - 1] = "X";
                    P1_OPP_BOARD[rocket_row - 1][rocket_column - 1] = "X";
                    System.out.println("\nHit!\n");
                } else {
                    P2_BOARD[rocket_row - 1][rocket_column - 1] = "O";
                    P1_OPP_BOARD[rocket_row - 1][rocket_column - 1] = "O";
                    System.out.println("\nMiss!\n");
                }
            } else {
                if (P1_BOARD[rocket_row - 1][rocket_column - 1].equals("U")) {
                    P1_BOARD[rocket_row - 1][rocket_column - 1] = "X";
                    P2_OPP_BOARD[rocket_row - 1][rocket_column - 1] = "X";
                    System.out.println("\nHit!\n");
                } else {
                    P1_BOARD[rocket_row - 1][rocket_column - 1] = "O";
                    P2_OPP_BOARD[rocket_row - 1][rocket_column - 1] = "O";
                    System.out.println("\nMiss!\n");
                }
            }
            return user_choice;
        }
    }


    // This function is used to update the current player if they sank one of their opponent's boats during their turn
    public static void oppBoatSinkCheck(String[][] board, int[][][] coords, int[] boats_sunk, int player) {
        // Loop through opponent's coord array and use coordinates to check opponent's board for remaining boats
        // Loop through each boat in the coordinate array
        for (int boat = 0; boat < 4; boat++) {
            // Reset boat_check to 0 at the beginning of each boat
            int boat_check = 0;

            // Loop though each section of the current boat in the coordinate array
            for (int section = 0; section < coords[boat].length; section++) {

                // If the current section of the current boat still remains on opponent's board, increment boat_check
                if (board[coords[boat][section][0] - 1][coords[boat][section][1] - 1].equals("C") ||
                        board[coords[boat][section][0] - 1][coords[boat][section][1] - 1].equals("U")) boat_check++;
            }

            // If no sections of the current boat remain, it has been sunk. Notify the player accordingly
            if (boat_check == 0) {
                if (boat == 0 && boats_sunk[boat] != 1) {
                    if (player != 0) System.out.println("You sank your opponent's 5 unit boat\n");
                    boats_sunk[boat] = 1;
                } else if (boat == 1 && boats_sunk[boat] != 1) {
                    if (player != 0) System.out.println("You sank your opponent's 4 unit boat\n");
                    boats_sunk[boat] = 1;
                } else if (boat == 2 && boats_sunk[boat] != 1) {
                    if (player != 0) System.out.println("You sank your opponent's 3 unit boat\n");
                    boats_sunk[boat] = 1;
                } else if (boat == 3 && boats_sunk[boat] != 1) {
                    if (player != 0) System.out.println("You sank your opponent's 2 unit boat\n");
                    boats_sunk[boat] = 1;
                }
            }
        }
    }


    // This function is used to update the current player if one of their boats was sunk by their opponent last round
    public static void playerBoatSinkCheck(int[] previous_boats_sunk, int[] current_boats_sunk) {
        // Loop through each boat in the arrays
        for (int boat = 0; boat < 4; boat++) {

            // If boat has been sunk on the most recent round, notify the player which boat was sunk
            if (previous_boats_sunk[boat] != current_boats_sunk[boat]) {
                if (boat == 0) System.out.println("Your 5 unit boat was sunk!\n");
                else if (boat == 1) System.out.println("Your 4 unit boat was sunk!\n");
                else if (boat == 2) System.out.println("Your 3 unit boat was sunk!\n");
                else System.out.println("Your 2 unit boat was sunk!\n");
            }
        }
    }


    // This function operates like the any() built-in funciton in Python, except it's designed just for this game
    // Returns true if any boat sections remain on the board array passed into the function
    public static boolean checkBoardStatus(String[][] board, String status_check) {
        // Initialize a variable to track if any boat sections remain
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


    // This function acts as a boolean flag which keeps the game loop going while both players remain in the game.
    // Once one of the players no longer has any boats on their board, end the game.
    public static boolean winConditionCheck(String mode) {
        // Initialize a variable to track if each player remains in the game
        int check = 0;

        // Check the proper boards based on the mode selected
        if (mode.equals("com")) {

            // If a player remains in the game, increment check
            if (checkBoardStatus(COM_BOARD, "C")) check++;
            if (checkBoardStatus(P1_BOARD, "U")) check++;
        } else {
            if (checkBoardStatus(P1_BOARD, "U")) check++;
            if (checkBoardStatus(P2_BOARD, "U")) check++;
        }

        // Return true if both players remain in the game. Return false if not
        return check == 2;
    }


    // This function returns the name of the player who won the game after the game loop has ended.
    public static String winnerCheck(String mode) {
        // Initialize a String to be reassigned the value of the winner of the game
        String winner = "";

        // Check proper boards based on the mode selected
        if (mode.equals("com")) {

            // Set winner String to the player who has boats remaining
            if (checkBoardStatus(COM_BOARD, "C")) winner = "com";
            if (checkBoardStatus(P1_BOARD, "U")) winner = "player";
        } else {
            if (checkBoardStatus(P1_BOARD, "U")) winner = "player1";
            if (checkBoardStatus(P2_BOARD, "U")) winner = "player2";
        }

        // Return the name of the winner
        return winner;
    }
}

