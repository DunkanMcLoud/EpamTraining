
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Program for solving quadratic equations of view:
 * a*x^2+b*x+c=0 where a,b,c represent polynomial coefficients
 * Checks correctness of arguments ,then prints out roots or root of equation.
 *@author Andrei Dunai
 */
public class quadraticEquation {
    private Double discriminant;
    private Double a;
    private Double b;
    private Double c;
    private String equationType;


    public static void main(String[] args) {
        quadraticEquation equationToSolve = new quadraticEquation(1, 6, 9);
        equationToSolve.displaySolution();
    }

    private void displaySolution() {
        System.out.println(toString());
    }


    //constructors
    public quadraticEquation(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.discriminant = evaluateDiscriminant();
        this.equationType = determineType();
    }


    //other methods
    private String solveEquation() {
        switch (equationType) {
            case "no roots":
                return "";
            case "one root":
                double answer = -b / (2 * a);
                return String.valueOf(answer);
            case "two roots":
                double x1 = ((-b) + sqrt(discriminant)) / (2 * a);
                double x2 = ((-b) - sqrt(discriminant)) / (2 * a);
                return (x1 + " and " + x2);
            default:
        }
        return "";
    }


    private String determineType() {
        if (discriminant == 0) {
            return "one root";
        } else if (discriminant < 0) {
            return "no roots";
        } else {
            return "two roots";
        }
    }


    private Double evaluateDiscriminant() {
        double discriminant = pow(b, 2) - 4 * a * c;
        return discriminant;
    }

    @Override
    public String toString() {
        return "(" + a + "x^2" + ") + " + "(" +
                b + "x) + " + "(" +
                c + ")" + " = 0  has " + determineType() + "\n" + solveEquation();

    }
}
