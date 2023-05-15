import java.io.Serializable;
import java.util.ArrayList;

public class Shoe implements Serializable {
    //declare variables
    private String name, brand, style;
    private double newPrice, usedPrice;

    //constructor
    public Shoe(String name, String brand, String style, double newPrice, double usedPrice) {
        this.name = name;
        this.brand = brand;
        this.style = style;
        this.newPrice = newPrice;
        this.usedPrice = usedPrice;
    }

    //getters
    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getStyle() {
        return style;
    }

    public double getNewPrice() {
        return newPrice;
    }

    public double getUsedPrice() {
        return usedPrice;
    }

    //setters
    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }

    public void setUsedPrice(double usedPrice) {
        this.usedPrice = usedPrice;
    }

    public static String deleteShoe(ArrayList<Shoe> shoes, String deleteName) {
        String msg = null;
        for(int i=0; i<shoes.size(); i++) {
            if(shoes.get(i).getName().equals(deleteName)) {
                shoes.remove(i);
                msg = "The item has been removed from list.";
            } else {
                msg = "There is not item of that name.";
            }
        }
        return msg;
    }

    public static String searchList(ArrayList<Shoe> shoes, String searchName) {
        String result = "Item not found.";
        for(int i=0; i<shoes.size(); i++) {
            if(shoes.get(i).getName().equals(searchName)) {
                result = shoes.get(i).toString();
            }
        }
        return result;
    }

    public static int numOfItems(ArrayList<Shoe> shoes) {
        int counter = 0;
        for(int i=0; i<shoes.size(); i++) {
            counter++;
        }
        return counter;
    }

    public static double totalNewValue(ArrayList<Shoe> shoes) {
        double totalNew = 0;
        for(int i=0; i<shoes.size(); i++) {
            totalNew = totalNew + shoes.get(i).getNewPrice();
        }
        return totalNew;
    }

    public static double totalUsedValue(ArrayList<Shoe> shoes) {
        double totalUsed = 0;
        for(int i=0; i<shoes.size(); i++) {
            totalUsed = totalUsed + shoes.get(i).getUsedPrice();
        }
        return totalUsed;
    }

    public static double totalValue(ArrayList<Shoe> shoes) {
        double total = 0;
        for(int i=0; i<shoes.size(); i++) {
            total = total + (shoes.get(i).getUsedPrice() + shoes.get(i).getNewPrice());
        }
        return total;
    }

    //toString method
    @Override
    public String toString() {
        return "Shoe name: " + name + " | Brand: " + brand + " | Style: " + style + " | Price(new): $" + newPrice + " | Price(used): $" + usedPrice;
    }

}
