package org.example;


/** класс работает как с комплексными, как и с действительными числами */
public class ComplexNumber {

    // действительная часть числа
    private double real;
    /** мнимая часть числа */
    private double imag;

    public double getReal() {
        return real;
    }

    public double getImag() {
        return imag;
    }

    ComplexNumber () {
        real = 0;
        imag = 0;
    }


    ComplexNumber (double real, double imag) {
        this.real = real;
        this.imag = imag;
    }


    ComplexNumber (double real) {
        this.real = real;
        this.imag = 0;
    }
    ComplexNumber (String str) {
        real = 0;
        imag = 0;
        String[] mas = str.split("[-+]");
        int realIndex = 1;
        if (str.indexOf("-") == 0) {
            if (mas[1].contains("i")) {
                imag = - Double.parseDouble(mas[1].substring(0, mas[1].length() - 1));
                return;
            }
            real = - Double.parseDouble(mas[1]);
            realIndex++;
        }
        else if (str.indexOf("-") != 0) {
            if (mas[0].contains("i")) {
                imag = Double.parseDouble(mas[0].substring(0, mas[0].length() - 1));
                return;
            }
            real = Double.parseDouble(mas[0]);
        }
        if (str.indexOf("-", 1) != -1) {
            imag = - Double.parseDouble(mas[realIndex].substring(0, mas[realIndex].length() - 1));
        }
        if (str.contains("+")) {
            imag = Double.parseDouble(mas[realIndex].substring(0, mas[realIndex].length() - 1));
        }
    }



    ComplexNumber add(ComplexNumber another){
        return new ComplexNumber(real + another.real,imag + another.imag);
    }


    ComplexNumber decr(ComplexNumber another){
        return new ComplexNumber(real - another.real,imag - another.imag);
    }


    ComplexNumber mul(ComplexNumber another){
        return new ComplexNumber(real * another.real - imag * another.imag,
                real * another.imag + imag * another.real);
    }


    ComplexNumber div(ComplexNumber another){
        if (another.real * another.real + another.imag * another.imag == 0)
            throw new RuntimeException("Нельзя делить на 0");

        return new ComplexNumber( (real * another.real + imag * another.imag)
                / (another.real * another.real + another.imag * another.imag),
                (imag * another.real - real * another.imag)
                        / (another.real * another.real + another.imag * another.imag));
    }

    String algebraicForm() {
        if (imag != 0 && real == 0) return String.format("%fi", imag);
        if (imag > 0) return String.format("%f+%fi", real, imag);
        if (imag == 0) return String.format("%f", real);
        return String.format("%f%fi", real, imag);
    }


}