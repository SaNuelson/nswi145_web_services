package saajcalc;

public class Main {

    public static void main(String[] args) {
        try {
            System.out.println("5 + 17 = " + SaajCalculator.Add(5, 17));
        }
        catch (Exception e) {
            System.out.println("Unable to create soap sender." + e);
        }
    }
}
