package main.java;

public class Start {

	public static void main(String[] args) {
		Matrix a = new Matrix(	"1.3333 111.2 1111 \n" +
								"1919 0 12.222222\n" +
								"1212.121212121212 19 3"
							);
		a = new Matrix(2, new MatrixFunction() {
			@Override
			public double get(int i, int j) {
				return 1.0/(i*3 + j + 1);
			}
		});
		
		a = new Matrix(new double[][]{
			{5, 1},
			{9, 2},
		});
		a.print();
		System.out.println("----");
		a.inverse().print();
		System.out.println("----");
		System.out.println("Cond: " + a.getCond());
		
		
	}

}
