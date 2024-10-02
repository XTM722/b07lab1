import java.io.IOException;

public class Driver {
    public static void main(String[] args) throws IOException{
        //test 1
        double [] coefficients1 = {5, -3,  7};
        int [] exponents1 = {0, 2, 8};
        Polynomial polynomial1 = new Polynomial(coefficients1, exponents1);
        System.out.println("First Polynomial:");
        polynomial1.displayCoefficientsAndExponents();

        //test 2 add method

        double [] coefficients2 = {1, 2, 3};
        int [] exponents2 = {1, 2, 3};

        double [] coefficients3 = {4, 5, 6};
        int [] exponents3 = {4, 5, 6};

        Polynomial polynomial2 = new Polynomial(coefficients2, exponents2);
        System.out.println("Second polynomial:");
        polynomial2.displayCoefficientsAndExponents();
        Polynomial polynomial3 = new Polynomial(coefficients3, exponents3);
        System.out.println("Third polynomial:");
        polynomial3.displayCoefficientsAndExponents();

        System.out.println("-------TESTING ADD METHOD--------");
        Polynomial addPolynomial = polynomial2.add(polynomial3);
        System.out.println("We added the second polynomial and the third polynomial:");
        addPolynomial.displayCoefficientsAndExponents();

        //test 3 evaluate with a x value

        System.out.println("------TESTING FOR EVALUATE METHOD WITH VALUE X------");
        double x = 2;
        double evalueResult = polynomial1.evaluate(x);
        System.out.println("We are evaluating value x = " + x + ", for the polynomial 1 is: " + evalueResult);

        //test 4 testing the multiply method
        System.out.println("------TESTING FOR MULTIPLY METHOD WITH POLYNOMIAL 2 AND POLYNOMIAL 3");
        Polynomial multiplyPolynomial = polynomial2.multiply(polynomial3);
        multiplyPolynomial.displayCoefficientsAndExponents();

        //test 5, testing for root
        System.out.println("------TESTING FOR CHECKING ROOTS WITH VALUE X = 2 FOR THE POLYNOMIAL 2------");
        polynomial2.hasRoot(x);
        System.out.println("Polynomial 2 has root at x = "+ x + " ," + polynomial2.hasRoot(x));

        //test 6 saving file
        System.out.println("------TESTING FOR METHOD saveToFile FOR POLYNOMIAL 3");
        try {
            polynomial3.saveToFile("output_polynomial3.txt");
            System.out.println("Polynomial 3 has been saved to output_polynomial3.txt");
        } catch (Exception e) {
            System.out.println("Error saving the polynomial: " + e.getMessage());
        }


    }
}