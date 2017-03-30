/*  
 *******************************************************************************
 *  Denarius
 *  BoardTile.java
 *  Landing on a tile has a possible or negative effect on the player
 *  Author: Jared Kwok
 *******************************************************************************
 */
package denarius;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class BoardTile {
    // Tile Value 

    private HashMap<Integer, ArrayList<String>> tileVal = new HashMap<>();

    public BoardTile(String file) {
        
        int key = 0;    // Remainder
        String[] splitText;     // Splits input from reader to be put into Hashmap
        String value;   //  Text in 3 sections that is split later
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String tileValue = "";
            while ((tileValue = br.readLine()) != null) {
                if (tileValue.contains("//")) {
                    // Ignores line 
                } else {
                    splitText = tileValue.split("-");
                    key = Integer.parseInt(splitText[0]);
                    value = splitText[1];

                    if (tileVal.containsKey(key)) {
                        tileVal.get(key).add(value);
                    } else {
                        tileVal.put(key, new ArrayList<String>());
                        tileVal.get(key).add(value);
                    }

                }
            }
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found - Tiles");
        } catch (IOException ex) {
            System.out.println("Read line Error - Tiles");
        }
    }

    public ArrayList<String> getTile(int rem) {
        return tileVal.get(rem);
    }
}
