public class Polynomial {

    double [] coefficients;

    public Polynomial(){
        this.coefficients = new double[]{0};
    }

    public Polynomial(double [] coefficients){
        this.coefficients = coefficients;

    }

    public Polynomial add(Polynomial p){
        int size1 = this.coefficients.length;
        int size2 = p.coefficients.length;
        int maxSize = Math.max(size1, size2);

        double [] new_coefficients = new double[maxSize];
        for(int i = 0; i < maxSize; i++){
            if(i < size1){
                new_coefficients[i] += this.coefficients[i];
            }
            if(i < size2){
                new_coefficients[i] += p.coefficients[i];
            }
        }
        Polynomial newPolynomial = new Polynomial(new_coefficients);
        return newPolynomial;
    }

    public double evaluate(double x){
        double sum = 0;
        for(int i = 0; i < this.coefficients.length; i++){
            sum += this.coefficients[i] * Math.pow(x, i);
        }
        return sum;
    }

    public boolean hasRoot(double x){
        return evaluate(x) == 0;
    }
}