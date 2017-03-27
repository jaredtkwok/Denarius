/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package denarius;

/**
 *
 * @author Jared
 */
public class Insurance {

    private String[] insuranceTypes = {"Life", "Medical", "House/Content", "Car"};
    private boolean owned;
    private int insurNum;

    public Insurance(int insurNum) throws Exception {
        if (insurNum < 0 || insurNum > 4) {
            throw new Exception("Insurance type not found");
        }
        this.insurNum = insurNum;
        owned = true;
    }

    public void setInsurance(boolean owned) {
        this.owned = owned;
    }

    public String getInsurance() {
        return insuranceTypes[insurNum];
    }

    public String getOwned() {
        if (owned) {
            return "Yes";
        } else {
            return "No";
        }
    }
}
