/*  
 *******************************************************************************
 *  Denarius
 *  Assets.java
 *  Assets which players can obtain during the game
 *  Author: Jared Kwok
 *******************************************************************************
 */
package denarius;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Assets {
    // Assets given to players at the start
    // private HashMap<String, String> sAssets = new HashMap<>();
    // Assets gain while playing
    private HashMap<String, String> assets = new HashMap<>();

    public Assets() {
        String[] splitText;     // Splits input from reader to be put into Hashmap
        String key;             // Name of asset
        String value;           // Value of assest and FSV
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/denarius/assets.txt"));
            String assetValue;
            while ((assetValue = br.readLine()) != null) {
                if (assetValue.contains("//")) {
                } else {
                    splitText = assetValue.split("-");
                    key = splitText[0];
                    value = splitText[1];
                    assets.put(key, value);
                }
            }
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        } catch (IOException ex) {
            System.out.println("Read line Error");
        }
    }
    
    public String getAsset(String k){
        return assets.get(k);
    }
    
}
