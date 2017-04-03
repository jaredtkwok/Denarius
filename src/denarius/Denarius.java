/*  
 *******************************************************************************
 *  Denarius
 *  Denarius.java
 *  Text Based Version
 *  Author: Jared Kwok
 *******************************************************************************
 */
package denarius;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Scanner;

public class Denarius {

    private boolean gameRunning;
    private Dice dice;
    private Player p1; // Player
    private int prevPos; // previous playr position
    private int d; // Dice roll value
    private BoardTile tile14, tile30; // Tile effects
    private Assets gameAssets; // Not images but player obtainable valuables
    private Cards drawACard; // Game's card pack

    public Denarius() {
        dice = new Dice();
        tile14 = new BoardTile("src/denarius/gameValues/boardTileValues14.txt");
        tile30 = new BoardTile("src/denarius/gameValues/boardTileValues30.txt");
        drawACard = new Cards();
        drawACard.shuffleCards();
        gameAssets = new Assets("src/denarius/gameValues/assets.txt");

    }

    public void startGame() {
        Scanner scan = new Scanner(System.in);
        gameRunning = true;
        boolean setupInput = true;
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
        while (setupInput) {
            input = scan.nextLine();
            if (input.compareToIgnoreCase("OWN") == 0) {
                p1.changeHousing();
                p1.setBalance(1000);
                setupInput = false;
            } else if (input.compareToIgnoreCase("RENT") == 0) {
                p1.setBalance(25000);
                setupInput = false;
            } else {
                System.out.println("Please put Own or Rent");
            }
        }
        // What insurances you have
        for (int i = 0; i < 4; i++) {
            setupInput = true;
            System.out.println("Do you have " + p1.getInsurance(i).getInsurance()
                    + " Insurance? Y/N");
            while (setupInput) {
                input = scan.nextLine();
                if (input.compareToIgnoreCase("YES") == 0
                        || input.compareToIgnoreCase("Y") == 0) {
                    p1.getInsurance(i).setInsurance(true);
                    setupInput = false;
                } else if (input.compareToIgnoreCase("NO") == 0
                        || input.compareToIgnoreCase("N") == 0) {
                    // Does nothing here as by default you don't have insurance
                    setupInput = false;
                } else {
                    System.out.println("Please put Yes or No");
                }
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
        } else if (command.toLowerCase().contains("sell")) {
            String[] splitText = command.split(" ");
            if (!splitText[1].isEmpty()) {
                String key = "";
                for (int i = 1; i < splitText.length; i++) {
                    key = key + " " + splitText[i];
                }
                key = key.substring(1, key.length());
                if (p1.getAssets().containsKey(key)) {
                    String[] saleVal = p1.getAssets().remove(key).split(" ");
                    int costVal = Integer.parseInt(saleVal[0]);
                    int sellVal = (int) (costVal * 0.8);
                    p1.addBalance(sellVal);
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
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
            balanceCalculator(modDay, i, 14);
            modDay = (prevPos + i) % 30;
            balanceCalculator(modDay, i, 30);
        }
        // Do Later after text based is finished
        //constCostCalc();
    }

    // Set is either 1 (mod14) or 2 (mod30)
    /* mod14 - Pay, Groceries, Rent/Mortgage  
     * mod30 - Phone, Utilities, Insurance
     */
    private void balanceCalculator(int rem, int tileInc, int mod) {
        ArrayList<String> section = new ArrayList<>();
        String[] output = new String[5];
        String[] splitText;
        int curTile = prevPos + tileInc;    // Current Tile
        try {
            // Chance for Null Pointer due to empty tiles
            switch (mod) {
                case 14:
                    section = tile14.getTile(rem);
                    break;
                case 30:
                    section = tile30.getTile(rem);
                    break;
                default:
                    break;
            }
            for (int i = 0; i < section.size(); i++) {
                output[i] = section.get(i);
            }

            if (!output[0].isEmpty()) {
                // Each tile has a String which split into sections of 3
                // [0]Inc/Dec - [1]Type - [2]value
                splitText = output[0].split(" ");
                String tileString = splitText[1]; // Name/Type
                int tileValue = Integer.parseInt(splitText[2]);// Value 
                if (tileString.contains("Insurance")) {
                    for (int i = 0; i < 4; i++) {
                        if (tileString.contains(p1.getInsurance(i).getInsurance())
                                && p1.getInsurance(i).getOwned().contains("Yes")) {
                            p1.subtractBalance(tileValue);
                            System.out.println(curTile + ": " + tileString + " -$"
                                    + tileValue);
                        }
                    }
                } else if (splitText[0].contains("Increase")) {
                    if (tileString.contains("Income") && p1.getIncome().getDuration() >= 1) {
                        // No Income
                        p1.getIncome().reduceDuration(d);
                    } else {
                        p1.addBalance(tileValue);
                        System.out.println(curTile + ": " + tileString + " $"
                                + tileValue);
                    }
                } else if ((p1.getHousing().contains("Rent") && mod == 14)
                        || (p1.getHousing().contains("Own") && mod == 30)) {
                    p1.subtractBalance(tileValue);
                    System.out.println(curTile + ": " + tileString + " -$"
                            + tileValue);
                } else {
                    p1.subtractBalance(tileValue);
                    System.out.println(curTile + ": " + tileString + " -$"
                            + tileValue);
                }
            }
        } catch (Exception e) {
            // Catches Null Pointer
        }
    }

    private void constCostCalc() {
        int subVal = 0; // Subtracted Value
        for(int i = 0; i < p1.getConstCostSize(); i++){
            subVal = p1.getConstCost()[i].getValue();
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
            System.out.println(p1.getInsurance(i).getInsurance() + ": \t"
                    + p1.getInsurance(i).getOwned());
        }
        System.out.println("Assets - Value - FSV: ");
        for (Entry<String, String> hm : p1.getAssets().entrySet()) {
            System.out.println(hm.getKey() + ": " + hm.getValue());
        }
    }

    public static void main(String[] args) {
        Denarius game = new Denarius();
        game.startGame();
    }
}
