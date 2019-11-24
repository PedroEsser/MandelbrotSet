package utils;
import java.awt.Color;

import Graphics.GUI;
import Graphics.RGBImage;

public class Complex {

	static final int RANGE = 4;
	public double a, b;
	
	public Complex(double a, double b){
		this.a = a;
		this.b = b;
	}
	
	public void add(Complex c) {
		a += c.a;
		b += c.b;
	}
	
	public Complex added(Complex c) {
		return new Complex(a + c.a, b + c.b);
	}
	
	Complex multiply(Complex c) {
		return new Complex(a*c.a - b*c.b, a*c.b + b*c.a);
	}
	
	Complex squared() {
		return new Complex(a*a - b*b, 2*a*b);
	}
	
	void square() {
		double aux = a;
		a = a*a - b*b;
		b = 2*aux*b;
	}
	
	double getDistSquared() {
		return a*a + b*b;
	}
	
	int outOnIteration(int limit) {
		Complex aux = new Complex(a, b);
		int iterations = 0;
		while(iterations<limit && aux.getDistSquared() < RANGE) {
			aux.square();
			aux.add(this);
			iterations++;
		}
		return iterations;
	}
	
	public double getPercent(int limit) {
		int out = outOnIteration(limit);
		if(out==limit)
			return 0;
		return (double)out/limit;
	}

	@Override
	public String toString() {
		return "Complex [a=" + a + ", b=" + b + "]";
	}
	
}
