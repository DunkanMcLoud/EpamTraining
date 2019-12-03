
/**Program for solving quadratic equations of view:
 * a*x^2+b*x+c=0 where a,b,c represent polynomial coefficients
 * Checks correctness of arguments ,then prints out roots or root of equation.
 */
public class EquationSolver {

    public static void main(String[] args) {
        solveEquation(1,6,9);}

    private static void solveEquation(double a, double b, double c) {
        if(isEquationIncorrect(a)){
            System.out.println("Equation must be quadratic.\n Please check entered parameters and try again!");
        } else {
            double D=evaluateDiscriminant(a,b,c);
            if (hasRoots(D)) {
                yieldRoots(a,b,c,D);
            } else {
                System.out.println("Particularly this equation has no solution!");
            }
        }
    }

    //roots are defined iff D>=0;
    private static boolean hasRoots(double d) {
        return d>=0;
    }

    private static void yieldRoots(double a, double b, double c, double d) {
        if (d>0){
                double x1 = ((-b)+ Math.sqrt(d))/(2*a);
                double x2 = ((-b)- Math.sqrt(d))/(2*a);
                System.out.println("Roots of equation are \n"+"x1="+x1+"\n"+"x2="+x2);
            }
            else if (d==0){
                double x = -b/(2*a);
                System.out.println("Equation has single root \n"+"x="+x);
            }
    }

    private static double evaluateDiscriminant(double a, double b, double c){
        double D=b*b-4*a*c;
        return D;
    }

    private static boolean isEquationIncorrect(double a) {
        return a==0;
    }

}
