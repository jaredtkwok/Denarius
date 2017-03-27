/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package denarius;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Jared
 */
public class Player {

    private String name;
    private boolean housing = false;
    private int balance = 0;
    private int position = 1;
    public Insurance[] insurance = new Insurance[4];
    private ArrayList<String> section = new ArrayList<>();
    public HashMap<String, String> oAssets = new HashMap<>();

    public Player() {
        name = "No Name";
        // Initialise Variables
        iInsurance();
        iAssets();

    }

    public Player(String name) {
        this.name = name;
        // Initialise Variables
        iInsurance();
        iAssets();
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
    private void iAssets() {
        String[] splitText;     // Splits input from reader to be put into Hashmap
        String key;             // Name of asset
        String value;           // Value of assest and FSV
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/denarius/startingAssets.txt"));
            String assetValue;
            while ((assetValue = br.readLine()) != null) {
                if (assetValue.contains("//")) {
                } else {
                    splitText = assetValue.split("-");
                    key = splitText[0];
                    value = splitText[1];
                    oAssets.put(key, value);
                }
            }
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        } catch (IOException ex) {
            System.out.println("Read line Error");
        }
    }
    
    public String getAssets(String k){     
        return oAssets.get(k);
    }
    
    public void addAsset(String k, String v) {
        oAssets.put(k, v);
    }

    // x is the position of insurance in array
    public String removeAsset(String k) {
        return oAssets.remove(k);
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
