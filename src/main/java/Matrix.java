package main.java;

import java.util.ArrayList;

public class Matrix {
	private ArrayList<ArrayList<Double>> matrix;
	public Matrix(String data) {
		int n = data.split("\n").length;
		matrix = new ArrayList<ArrayList<Double>>(n);
		
		String[] rows = data.split("\n");
		if (rows.length != n) 
			inputError(-1);
		
		for (int i = 0; i < n; i++) {
			ArrayList<Double> buffer = new ArrayList<Double>(n);
			for (String item : rows[i].split(" ")) 
				buffer.add(Double.parseDouble(item));
			
			if (buffer.size() != n) 
				inputError(i);
			
			matrix.add(buffer);
		}
	}
	
	public Matrix(double[][] matr) {
		int n = matr.length;
		
		matrix = new ArrayList<ArrayList<Double>>(n);
		for(int i = 0; i < matr.length; i++) {
			if (matr[i].length != n) inputError(i);
			
			ArrayList<Double> buffer = new ArrayList<Double>(n);
			for (double item : matr[i]) 
				buffer.add(item);
			
			matrix.add(buffer);
		}
	}
	
	public Matrix(int n, MatrixFunction function) {
		matrix = new ArrayList<ArrayList<Double>>(n); 
		
		for (int i = 0; i < n; i++) {
			ArrayList<Double> buffer = new ArrayList<Double>(n);
			for (int j = 0; j < n; j++) 
				buffer.add(function.get(i, j));
			
			matrix.add(buffer);
		}
	}
	
	public void print() {
		for (ArrayList<Double> row:matrix) {
			for (double item:row) 
				System.out.printf("%.2f\t", item);
			System.out.println();
		}
	}
	
	private void inputError(int where) {
		System.err.println("Row #" + where + ":\tIncorrect input data");
		System.exit(-1);
	}
	
	public void changeRows(int i, int j) {
		if (i == j) return;
		
		ArrayList<Double> buf = matrix.get(i);
		matrix.set(i, matrix.get(j));
		matrix.set(j, buf);
	}
}
