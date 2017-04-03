/*  
 *******************************************************************************
 *  Denarius
 *  Player.java
 *  Player profile 
 *  Name, Housing, Balance, Position, Insurance, and Assets
 *  Author: Jared Kwok
 *******************************************************************************
 */
package denarius;

import java.util.HashMap;

public class Player {

    private String name;
    private boolean housing = false; // Owning or Renting a house
    private int balance = 0; // Amount of money player has
    private int position = 1; // Current Day/Position on Board
    private Insurance[] insurance = new Insurance[4]; // Insurance Owned
    private Assets oAssets; // Player Assets    
    private ConstantCost[] constC; // Array of constant cost for deposit/credit card
    private ConstantCost constIncome; // constant cost just for income
    private final int maxConstCost = 5; // Max number of constant cost possible in a game

    public Player() {
        name = "No Name";
        // Initialise Variables
        iInsurance();
        oAssets = new Assets("src/denarius/gameValues/startingAssets.txt");
        constIncome = new ConstantCost("Income", 0);
        constC = new ConstantCost[maxConstCost];
    }

    public Player(String name) {
        this.name = name;
        // Initialise Variables
        iInsurance();
        oAssets = new Assets("src/denarius/gameValues/startingAssets.txt");
        constIncome = new ConstantCost("Income", 0);
        constC = new ConstantCost[maxConstCost];
    }

    public String getName() {
        return name;
    }
    // Constant Costs Section
    
    public ConstantCost getIncome(){
        return constIncome;
    }
    
    public int getConstEffectSize(){
        return constC.length;
    }

    public ConstantCost[] getConstEffect() {
        return constC;
    }
    // End Constant Costs Section
    
    // Housing Section
    // True - Own, False - Rent
    public void changeHousing() {
        housing = !housing;
    }

    public String getHousing() {
        if (housing) {
            return "Own";
        } else {
            return "Rent";
        }
    }
    // End Housing Section

    // Player Position Section
    public void movePos(int p) {
        position += p;
    }

    public int getPos() {
        return position;
    }

    // Bank Balance Section
    public int getBalance() {
        return balance;
    }

    public void addBalance(int x) {
        balance += x;
    }

    public void subtractBalance(int x) {
        balance -= x;
    }

    // x is the amount to change the balance
    public void setBalance(int x) {
        balance = x;
    }
    // End Balance Section

    // Asset Section
    public HashMap<String, String> getAssets() {
        return oAssets.getAssets();
    }

    // x is the position of insurance in array
    public Assets removeAsset(String k) {
        return oAssets;
    }
    // End Asset Section

    // Insurance Section
    // Initialise Insurance
    private void iInsurance() {
        for (int i = 0; i < 4; i++) {
            try {
                insurance[i] = new Insurance(i);
            } catch (Exception ex) {
                System.out.println("ERROR: Creating Insurance");
            }
        }
    }
    // x is the position of insurance in array

    public Insurance getInsurance(int x) {
        return insurance[x];
    }
}
