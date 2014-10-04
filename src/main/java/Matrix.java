package main.java;

public class Matrix {
	private double[][] matrix;
	public Matrix(String data) {
		int n = data.split("\n").length;
		matrix = new double[n][n];
		
		String[] rows = data.split("\n");
		if (rows.length != n) 
			inputError(-1);
		
		for (int i = 0; i < n; i++) {
			String[] items = rows[i].split(" ");
			
			if (items.length != n) 
				inputError(i);
			
			for (int j = 0; j < n; j++)
				matrix[i][j] = Double.parseDouble(items[j]);	
		}
	}
	
	public Matrix(double[][] matr) {
		int n = matr.length;
		
		for(int i = 0; i < matr.length; i++) 
			if (matr[i].length != n) 
				inputError(i);
		
		matrix = matr;
	}
	
	public Matrix(int n, MatrixFunction function) {
		matrix = new double[n][n];
		
		for (int i = 0; i < n; i++) 
			for (int j = 0; j < n; j++) 
				matrix[i][j] = function.get(i, j);
	}
	
	public Matrix inverse() {
		int n = matrix.length;
		double[][] data = new double[n][n];
		
		//inverse - ��������� �������
		for(int i = 0; i < n; i++)
			for(int j = 0; j < n; j++) 
				data[i][j] = i == j ? 1 : 0;
		
		Matrix inverse = new Matrix(data);
		/*
		 * Start GAUSS 
		 */
		
		//������ ���
		for(int column = 0; column < n; column++) {
			int k = column;
			//�������� ������ ����� ������� �������, � ������� ���� ���� ���� �������� �� ���� ��������.
			for( ; k < n; k++) 
				if (matrix[k][column] != 0) 
					break;
			
			if (k == n) continue;
			
			//���� ����� �� ��������� ����, �� ����� ��� ������ � ������ �������, ��� � ���� ������� ��� ����.
			if (k != column) {
				changeRows(column, k);
				inverse.changeRows(column, k);
			}
			
			//��� �������� ������ ������� �� ������������ ������� ���������� �������.
			double mainElement = matrix[k][column];
			rowDiv(column, mainElement);
			inverse.rowDiv(column, mainElement);
			
			
			//�� ���������� ����� �������� ������ ������, ���������� �� ������������ ������� ������ ������.
			for(int i = column + 1; i < n; i++) {
				double mulKoeff =  matrix[i][column];
				rowSub(i, column, mulKoeff);
				inverse.rowSub(i, column, mulKoeff);
			}
		}
		//�������� ���
		for(int i = n-1; i >=0 ; i--) 
			for(int j = i-1; j >= 0; j--) 
				inverse.rowSub(j, i, matrix[j][i]);
		
		/*
		 * END GAUSS
		 */
		return inverse;
	}
	
	public void print() {
		for (double[] row:matrix) {
			for (double item:row) 
				System.out.printf("%.2f\t", item);
			System.out.println();
		}
	}
	
	public double getCond() {
		return getNorm() * inverse().getNorm();
	}
	
	
	/*
	 * 1-�����
	 * 
	 * ||M|| = MAX_i( 
	 * 				SUM_j( |M_ij| ) 
	 * 			)
	 */
	private double getNorm() {
		double result = -1;
		
		for(double[] row:matrix) {
			double sum = 0.0;
			for (double item:row) 
				sum += Math.abs(item);
			result = Math.max(result, sum);
		}
			
		return result;
	}
	
	private void rowDiv(int row, double div) {
		for(int i = 0; i < matrix.length; i++) 
			matrix[row][i] /= div;
	}
	
	//left = left - a*right
	private void rowSub(int left, int right, double a) {
		for (int i = 0; i < matrix.length; i++) 
			matrix[left][i] -= a*matrix[right][i];
	}
	
	private void inputError(int where) {
		System.err.println("Row #" + where + ":\tIncorrect input data");
		System.exit(-1);
	}
	
	private void changeRows(int i, int j) {
		if (i == j) return;
		
		double[] buf = matrix[i].clone();
		matrix[i] =  matrix[j];
		matrix[j] = buf;
	}
}
