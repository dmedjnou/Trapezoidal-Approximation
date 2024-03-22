import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<Double> valuesOfX;
    private static List<Double> valuesOfF = new ArrayList<>();
    private static List<Double> BnActualValues = new ArrayList<>();
    private static Double deltaX;
    private static final double R = Math.PI/2;

    public static void main(String[] args) {
        generateValuesOfX(0, Math.PI, 8);
        setValuesofF();
        setActualValuesOfBn();

        //Uncomment the print statements to see the intermediate values
        //printValues("x", valuesOfX);
        //printValues("f(x)", valuesOfF);

        //Find values of sin(kx) for each value of k
        List<Double> k1 = getSinKX(1);
        List<Double> k2 = getSinKX(2);
        List<Double> k3 = getSinKX(3);
        List<Double> k4 = getSinKX(4);
        List<Double> k5 = getSinKX(5);

//        printValues("sin(x)", k1);
//        printValues("sin(2x)", k2);
//        printValues("sin(3x)", k3);
//        printValues("sin(4x)", k4);
//        printValues("sin(5x)", k5);

        //Find values of f(x) * sin(kx) for each value of k, and put them into the same list
        multiplyByFx(k1);
        multiplyByFx(k2);
        multiplyByFx(k3);
        multiplyByFx(k4);
        multiplyByFx(k5);

        //Find values of Bn^trap
        List<Double> BnTrap = new ArrayList<>();

        BnTrap.add(trapezoidallyApproximate(k1));
        BnTrap.add(trapezoidallyApproximate(k2));
        BnTrap.add(trapezoidallyApproximate(k3));
        BnTrap.add(trapezoidallyApproximate(k4));
        BnTrap.add(trapezoidallyApproximate(k5));

        printValues("the Bn approximation", BnTrap);
    }

    private static void setActualValuesOfBn() {
        BnActualValues.add(72.0);
        BnActualValues.add(101.0);
        BnActualValues.add(108.0);
        BnActualValues.add(108.0);
        BnActualValues.add(111.0);
    }

    private static void printValues(String name, List<Double> values) {
        System.out.print("Values of " + name + ": ");
        System.out.println("[");
        for (Double value : values) {
            System.out.print("\t");
            System.out.println(value);
        }
        System.out.println("]");
        System.out.println();
    }

    private static Double trapezoidallyApproximate(List<Double> functionResults) {
        int lastIndex = functionResults.size() - 1;
        return 1/R * deltaX * (0.5*functionResults.get(0) + sum(functionResults.subList(1, lastIndex)) + 0.5*functionResults.get(lastIndex));
    }

    private static double sum(List<Double> values) {
        double result = 0;
        for (Double value : values) {
            result += value;
        }

        return result;
    }

    private static void multiplyByFx(List<Double> sinKX) {
        for (int i = 0; i < sinKX.size(); i++) {
            Double v =  sinKX.get(i);
            sinKX.set(i, valuesOfF.get(i) * v);
        }
    }

    private static List<Double> getSinKX(int K) {
        List<Double> results = new ArrayList<>();

        for (Double x : valuesOfX) {
            results.add(Math.sin(K * x));
        }

        return results;
    }

    private static void setValuesofF() {
        valuesOfF.add(0.0);
        valuesOfF.add(409.3);
        valuesOfF.add(149.8);
        valuesOfF.add(-53.9);
        valuesOfF.add(75.0);
        valuesOfF.add(19.3);
        valuesOfF.add(-52.2);
        valuesOfF.add(50.5);
        valuesOfF.add(0.0);
    }

    private static void generateValuesOfX(double leftBound, double rightBound, int subintervals) {
        valuesOfX = new ArrayList<Double>();
        deltaX = (rightBound - leftBound)/subintervals;

        valuesOfX.add(leftBound);
        for (int i = 1; i < subintervals; i++) {
            valuesOfX.add(leftBound + deltaX*i);
        }
        valuesOfX.add(rightBound);
    }
}