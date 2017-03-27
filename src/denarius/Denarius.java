/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package denarius;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Jared
 */
public class Denarius {

    private boolean gameRunning;
    private Dice dice;
    private Player p1;
    private int prevPos;
    private int d;
    private BoardTile tile;

    public Denarius() {
        dice = new Dice();
        tile = new BoardTile();
    }

    public void startGame() {
        Scanner scan = new Scanner(System.in);
        gameRunning = true;
        // Starting information about how to play
        System.out.println("----------INSTRUCTIONS----------");
        System.out.println("To continue with the game type 'ROLL' ");
        System.out.println("To quit type 'Quit' ");
        //System.out.println("For help during the game type 'Help' ");

        // Setup Profile
        System.out.println("Enter your name: ");
        String input = scan.nextLine();
        if (input.compareToIgnoreCase("QUIT") == 0) {
            gameRunning = false;
        } else {
            p1 = new Player(input);
        }
        // Own/Rent and starting Balance
        System.out.println("Own or Rent a House: ");
        input = scan.nextLine();
        if (input.compareToIgnoreCase("OWN") == 0) {
            p1.changeHousing();
            p1.setBalance(1000);
        } else {
            p1.setBalance(25000);
        }
        for (int i = 0; i < 4; i++) {

            switch (i) {
                case 0:
            }
        }

        // End Setup Profile
        showPlayerProfile();

        while (gameRunning) {
            if (p1.getPos() >= 365) {
                gameRunning = false;
            }
            if (execute(scan.nextLine())) {
                if (gameRunning) {
                    showBalanceUpdate();
                    showPlayerProfile();
                }
            }
        }
    }

    // Executable Commands
    private boolean execute(String command) {
        if (command.compareToIgnoreCase("QUIT") == 0) {
            gameRunning = false;
            return true;
        } else if (command.compareToIgnoreCase("SELL") == 0) {
            return true;
        } else if (command.compareToIgnoreCase("ROLL") == 0) {
            prevPos = p1.getPos();
            d = dice.roll();
            p1.movePos(d);
            System.out.println("----------Update Section----------");
            System.out.println("You Rolled a " + d);
            return true;
        }

        return false;
    }

    // Updates player's current balance
    private void showBalanceUpdate() {
        int modDay = 0;
        for (int i = 0; i < d; i++) {
            modDay = (prevPos + i) % 14;
            balanceCalculator(modDay, 1);
            modDay = (prevPos + i) % 30;
            balanceCalculator(modDay, 2);
        }
    }

    // Set is either 1 (mod14) or 2 (mod30)
    /* mod14 - Pay, Groceries, Rent/Mortgage  
     * mod30 - Phone, Utilities, Insurance
     */
    private void balanceCalculator(int rem, int set) {
        ArrayList<String> section = new ArrayList<>();
        String[] output = new String[5];
        String[] splitText;
        int curTile = prevPos + rem - 1;    // Current Tile
        try {
            // Chance for Null Pointer due to empty tiles
            section = tile.getTile(rem, set);
            for (int i = 0; i < section.size(); i++) {
                output[i] = section.get(i);
            }

            if (!output[0].isEmpty()) {
                // Each tile has a String which split into sections of 3
                // Inc/Dec - Type - value
                if (p1.getHousing().contains("Rent") && rem == 2) {
                    splitText = output[1].split(" ");
                } else {
                    splitText = output[0].split(" ");
                }

                String tileString = splitText[1];
                int tileValue = Integer.parseInt(splitText[2]);
                if (tileString.contains("Insurance")) {
                    for (int i = 0; i < 4; i++) {
                        if (tileString.contains(p1.insurance[i].getInsurance())
                                && p1.getInsurance(i).getOwned().contains("Yes")) {
                            p1.subtractBalance(tileValue);
                            System.out.println(curTile + ": " + tileString + " -$" + tileValue);
                        }
                    }
                } else if (splitText[0].compareTo("Increase") == 0) {
                    p1.addBalance(tileValue);
                    System.out.println(curTile + ": " + tileString + " $" + tileValue);
                } else {
                    p1.subtractBalance(tileValue);
                    System.out.println(curTile + ": " + tileString + " -$" + tileValue);
                }
            }
        } catch (Exception e) {
            // Catches Null Pointer
        }
    }

    // Player Profile
    private void showPlayerProfile() {
        /* Player Profile Layout
         * - Name
         * - Position
         * - Balance
         * - Housing
         * - Insurance
         * - Assets
         */
        System.out.println("----------Current Profile----------");
        System.out.println("Name: " + p1.getName());
        System.out.println("Position: " + p1.getPos());
        System.out.println("Balance: $" + p1.getBalance());
        System.out.println("Housing: " + p1.getHousing());
        System.out.println("Insurance: ");
        for (int i = 0; i < 4; i++) {
            System.out.println(p1.getInsurance(i).getInsurance() + ": \t" + p1.getInsurance(i).getOwned());
        }


    }

    public static void main(String[] args) {
        Denarius game = new Denarius();
        game.startGame();
    }
}
