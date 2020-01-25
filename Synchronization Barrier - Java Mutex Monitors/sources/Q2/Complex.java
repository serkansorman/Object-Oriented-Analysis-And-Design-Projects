package Q2;

import java.util.Objects;

/**
 * Represent complex number in matrices
 */
public class Complex {
    public double re;   // the real part
    public double im;   // the imaginary part

    public Complex() {
        this.re = 0;
        this.im = 0;
    }

    // create a new object with the given real and imaginary parts
    public Complex(double real, double imag) {
        re = real;
        im = imag;
    }


    public String toString() {
        return String.format("%.1f+%.1fi ",re,im);
    }

    /**
     * Return sum of two complex number
     * @param b other complex number that will be added
     * @return
     */
    public Complex plus(Complex b) {
        Complex a = this;
        double real = a.re + b.re;
        double imag = a.im + b.im;
        return new Complex(real, imag);
    }

    /**
     * Check two complex numbers are equal
     * @param x
     * @return
     */
    public boolean equals(Object x) {
        if (x == null) return false;
        if (this.getClass() != x.getClass()) return false;
        Complex that = (Complex) x;
        return (this.re == that.re) && (this.im == that.im);
    }

}