import java.util.Scanner;

/**Program for solving quadratic equations of view:
 * a*x^2+b*x+c=0 where a,b,c represent polynomial coefficients
 * @param double a
 * @param double b
 * @param double c
 * Checks correctness of arguments ,then prints out roots or root(if it is single) of equation.
 * @author DunkanMcLoud
 */
public class EquationSolver {

    private double a;
    private double b;
    private double c;
    private double D; // discriminant


    public EquationSolver(double a,double b,double c){
        this.a=a;
        this.b=b;
        this.c=c;
    }

    public static void main(String[] args) {
        EquationSolver eq = createEquation();
        if(!eq.isEquationCorrect()){
            System.out.println("Equation must be quadratic.\n Please check entered parameters and try again!");
        } else{
                eq.evaluateDiscriminant();
                if (eq.hasRoots()){
                    eq.yieldRoots();
                } else {
                    System.out.println("Particularly this equation has no solution!");
                }
            }
    }

    //roots are defined iff D>=0;
    private boolean hasRoots() {
        return D>=0;
    }

    /**if {@code D>0}
     * Evaluates roots of polynomial using naive formula:
     * x1=(-b-sqrt(D))/2*a
     * x2=(-b+sqrt(D))/2*a
     * if {@code D==0} ---> equation has one root ---> x = -b/2*a
     * Prints out the result in the end
     */
    private void yieldRoots() {
        if (D>0){
                System.out.println(-b);
                System.out.println("discr"+D);
                double x1 = ((-b)+ Math.sqrt(D))/(2*a);
                double x2 = ((-b)- Math.sqrt(D))/(2*a);
                System.out.println("Roots of equation are \n"+"x1="+x1+"\n"+"x2="+x2);
            }
            else if (D==0){
                double x = -b/(2*a);
                System.out.println("Equation has single root \n"+"x="+x);
            }
    }


    /**MODIFIES:{@code D}
     * Function evaluates discriminant with simple formula
     * D=b^2-4*a*c
     */
    private void evaluateDiscriminant(){
        D=b*b-4*a*c;
    }

    /**Decides whether the equation composed properly
     * @return {@param true} if {@code a!=0}
     */
    private boolean isEquationCorrect() {
        return a!=0;
    }

    /**MODIFIES: {@code a,b,c}
     * Collects the data for equation with the help of Scanner
     * @return
     */
    private static EquationSolver createEquation() {
        double A,B,C;
        Scanner scan =new Scanner(System.in);
        System.out.println("This is Quadratic equation solver \n" +
                "which solves equation of type a*x^2+b*x+c=0 \n"+
                "Please enter the first parameter a (where a*x^2) ");
        A=scan.nextDouble();
        System.out.println("Ok, enter next coefficient b - where  (b*x) ");
        B=scan.nextDouble();
        System.out.println("Finally, enter coefficient c ");
        C=scan.nextDouble();
        return new EquationSolver(A,B,C);
    }


}
