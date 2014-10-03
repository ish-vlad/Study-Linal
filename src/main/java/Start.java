package main.java;

public class Start {

	public static void main(String[] args) {
		Matrix a = new Matrix(	"1.3333 111.2 1111\n" +
								"1919 0 12.222222\n" +
								"1212.121212121212 19 3"
							);
		a.changeRows(0, 1);
		a.print();
		
		a = new Matrix(new double[][]{
			{1, 1, 1},
			{23,32,23},
			{23.12122222, 12, 0}
		});
		a.changeRows(2, 1);
		a.print();
		
		
		a = new Matrix(3, new MatrixFunction() {
			@Override
			public double get(int i, int j) {
				return 1.0/(i*3 + j + 1);
			}
		});
		
		a.print();
	}

}
