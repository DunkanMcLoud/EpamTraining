
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Program for solving quadratic equations of view:
 * a*x^2+b*x+c=0 where a,b,c represent polynomial coefficients
 * Checks correctness of arguments ,then prints out roots or root of equation.
 */
public class quadraticEquation {
    private Double discriminant;
    private Double a;
    private Double b;
    private Double c;
    private String equationType;


    public static void main(String[] args) {
        quadraticEquation equationToSolve = createEquation(-7, -6, -9);
        equationToSolve.solveEquation();
    }


    //constructors
    public quadraticEquation(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.discriminant = evaluateDiscriminant();
        this.equationType = determineType();
    }


    public static quadraticEquation createEquation(double a, double b, double c) {
        return new quadraticEquation(a, b, c);
    }

    //other methods
    private void solveEquation() {
        switch (equationType) {
            case "No roots":
                System.out.println("This equation has no solution!");
                break;
            case "One root":
                double answer = -b / (2 * a);
                System.out.println("Equation has only one solution " + answer);
                break;
            case "Two roots":
                double x1 = ((-b) + sqrt(discriminant)) / (2 * a);
                double x2 = ((-b) - sqrt(discriminant)) / (2 * a);
                System.out.println("Equation has two solutions " + x1 + " " + x2);
                break;
            default:
        }
    }


    private String determineType() {
        if (discriminant == 0) {
            return "One root";
        } else if (discriminant < 0) {
            return "No roots";
        } else {
            return "Two roots";
        }
    }


    private Double evaluateDiscriminant() {
        double discriminant = pow(b, 2) - 4 * a * c;
        return discriminant;
    }

}
