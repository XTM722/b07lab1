import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Polynomial {

    double [] coefficients;
    int [] exponents;

    public Polynomial(){
        this.coefficients = new double[]{0};
        this.exponents = new int[] {0};
    }
    public Polynomial (File file) throws FileNotFoundException{
        HashMap<Integer, Double> Map = new HashMap<>();
        Scanner sc = new Scanner(file);
        String text = sc.nextLine();
        sc.close();

        String [] terms = text.split("(?=[+-])");
        for(String term : terms){
            term = term.trim();
            if(term.contains("x")){
                String [] coeff_and_exponent_parts = term.split("x");
                double coefficient;
                if(coeff_and_exponent_parts[0].equals("") || coeff_and_exponent_parts[0].equals("+")){
                    coefficient = 1.0;
                }
                else if (coeff_and_exponent_parts[0].equals("-")){
                    coefficient = -1.0;
                }else{
                    coefficient = Double.parseDouble(coeff_and_exponent_parts[0]);
                }
                int exponent = (coeff_and_exponent_parts.length > 1) ? Integer.parseInt(coeff_and_exponent_parts[1]) : 1;
                Map.put(exponent, coefficient);
            }
            else{
                Map.put(0, Double.parseDouble(term));
            }
        }
        convertMaptoArrays(Map);
    }
    public void saveToFile(String fileName) throws IOException{
        FileWriter writer = new FileWriter(fileName);
        StringBuilder sBuilder = new StringBuilder();
        for(int i = 0 ; i < this.coefficients.length; i++){
            double coefficient = this.coefficients[i];
            int exponent = this.exponents[i];
            if(coefficient == 0) continue;
            
            if(sBuilder.length() > 0 && coefficient > 0){
                sBuilder.append('+');
            }
            if(exponent == 0 || Math.abs(coefficient) !=1) {
                sBuilder.append(coefficient);
            }else if(coefficient == -1){
                sBuilder.append('-');
            }
            if(exponent >0){
                sBuilder.append('x');
                if(exponent > 1){
                    sBuilder.append(exponent);
                }
            }
        }
        writer.write(sBuilder.toString());
        writer.close();
    }
    public void convertMaptoArrays (HashMap<Integer, Double> Map){
        this.coefficients = new double[Map.size()];
        this.exponents = new int [Map.size()];

        List<Integer> sortedExponents = new ArrayList<>(Map.keySet());
        Collections.sort(sortedExponents);
        int index = 0;
        for(int exponent: sortedExponents){
            this.coefficients[index] = Map.get(exponent);
            this.exponents[index] = exponent;
            index++;
        }
    }

    public Polynomial(double [] coefficients, int [] exponents){
        this.coefficients = coefficients;
        this.exponents = exponents;

    }

    public Polynomial add(Polynomial p){
        HashMap<Integer, Double> Map = new HashMap<>();
        for(int i = 0; i < this.coefficients.length; i++){
            int thisExponent = this.exponents[i];
            double thisCoefficient = this.coefficients[i];
            if(Map.containsKey(thisExponent)){
                Map.put(thisExponent, Map.get(thisExponent) + thisCoefficient);
            }
            else{
                Map.put(thisExponent, thisCoefficient);
            }
        }

        for(int i = 0; i < p.coefficients.length; i++){
            int pExponent = p.exponents[i];
            double pCoefficient = p.coefficients[i];
            if(Map.containsKey(pExponent)){
                Map.put(pExponent, Map.get(pExponent) + pCoefficient);
            }else{
                Map.put(pExponent, pCoefficient);
            }
        }
        return mapConvertPolynomial(Map);
    }
    private Polynomial mapConvertPolynomial(HashMap<Integer, Double> Map){
        int [] newExponents = new int[Map.size()];
        double [] newCoefficients = new double[Map.size()];
        //sort using List

        List<Integer> sortedExponents = new ArrayList<>(Map.keySet());
        Collections.sort(sortedExponents);
        int index = 0;
        for(Integer exponent : sortedExponents){
            newExponents[index] = exponent;
            newCoefficients[index] = Map.get(exponent);
            index++;
        }
        return new Polynomial(newCoefficients, newExponents);
    }

    public Polynomial multiply(Polynomial p){
        HashMap<Integer, Double> Map = new HashMap<>();
        for(int i = 0; i < this.coefficients.length; i++){
            for(int j = 0; j < p.coefficients.length; j++){
                int newExponent = this.exponents[i] + p.exponents[j];
                double newCoefficient = this.coefficients[i] + p.coefficients [j];
                if(Map.containsKey(newExponent)){
                    Map.put(newExponent, Map.get(newExponent) + newCoefficient);
                }
                else{
                    Map.put(newExponent, newCoefficient);
                }
            }
        }
        return mapConvertPolynomial(Map);
    }

    public double evaluate(double x){
        double sum = 0;
        for(int i = 0; i < this.coefficients.length; i++){
            sum += this.coefficients[i] * Math.pow(x, this.exponents[i]);
        }
        return sum;
    }

    public boolean hasRoot(double x){
        return evaluate(x) == 0;
    }
    public void displayCoefficientsAndExponents() {
        System.out.println("Coefficients: " + Arrays.toString(coefficients));
        System.out.println("Exponents: " + Arrays.toString(exponents));
    }
}