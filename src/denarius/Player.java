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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Player {

    private String name;
    private boolean housing = false;
    private int balance = 0;
    private int position = 1;
    private Insurance[] insurance = new Insurance[4];
    private Assets oAssets;

    public Player() {
        name = "No Name";
        // Initialise Variables
        iInsurance();
        oAssets = new Assets("src/denarius/startingAssets.txt");

    }

    public Player(String name) {
        this.name = name;
        // Initialise Variables
        iInsurance();
        oAssets = new Assets("src/denarius/startingAssets.txt");
    }

    public String getName() {
        return name;
    }

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
    public Assets getAssets() {
        return oAssets;
    }

    public int assetSize() {
        return oAssets.getAssets(1).size();
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
