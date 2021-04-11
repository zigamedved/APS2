import java.util.Scanner;

import javax.swing.text.TabableView;


class Matrix {


	private int[][] m;

	public int n; //only square matrices

	public Matrix(int n){

		this.n = n;

		m = new int[n][n];

	}


    //set value at i,j
	public void setV(int i, int j, int val){

		m[i][j] = val;

	}


    // get value at index i,j
	public int v(int i, int j){

		return m[i][j];

	}


    // return a square submatrix from this
	public Matrix getSubmatrix(int startRow, int startCol, int dim){

		Matrix subM = new Matrix(dim);

		for (int i = 0; i<dim ; i++ )

			for (int j=0;j<dim ; j++ )

				subM.setV(i,j, m[startRow+i][startCol+j]);



		return subM;

	}


    // write this matrix as a submatrix from b (useful for the result of multiplication)
	public void putSubmatrix(int startRow, int startCol, Matrix b){

		for (int i = 0; i<b.n ; i++ )

			for (int j=0;j<b.n ; j++ )

				setV(startRow+i,startCol+j, b.v(i,j));

	}


    // matrix addition
	public Matrix sum(Matrix b){

		Matrix c = new Matrix(n);

		for(int i = 0; i< n;i++){

			for(int j = 0; j<n;j++){

				c.setV(i, j, m[i][j]+b.v(i, j));

			}

		}

		return c;

	}





    // matrix subtraction
	public Matrix sub(Matrix b){

		Matrix c = new Matrix(n);

		for(int i = 0; i< n;i++){

			for(int j = 0; j<n;j++){

				c.setV(i, j, m[i][j]-b.v(i, j));

			}

		}

		return c;

	}

	public int sestevek(){
        int sum = 0;
        
        for(int i = 0; i<this.n; i++){
            for(int j = 0; j<this.n; j++){
            	sum += this.v(i, j);
             }
		}

		return sum;
	}

    public void izpisi(Matrix ena, Matrix dva){
        
        
        for(int i = 0; i<ena.n; i++){
			for(int j = 0; j<ena.n; j++){
				System.out.printf("%d ",ena.v(i, j));
			}
			System.out.println();
		}

        for(int i = 0; i<ena.n; i++){
			for(int j = 0; j<ena.n; j++){
				System.out.printf("%d ",dva.v(i, j));
			}
			System.out.println();
		}

		
	}



	//simple multiplication
	public Matrix mult(Matrix b){

        Matrix c = new Matrix(b.n);
        
        for(int i = 0; i<b.n; i++){
            for(int j = 0; j<b.n; j++){
                int val = 0;
                for(int k = 0; k<b.n; k++){
                    val += this.v(i,k)*b.v(k, j);
                }
                c.setV(i, j, val);
            }
        }

        return c;
        
	}


    // Strassen multiplication
	public Matrix multStrassen(Matrix b, int cutoff){
    
		if(this.n==cutoff){
			return this.mult(b);
		}

        Matrix a11 = this.getSubmatrix(0, 0, this.n/2);
        Matrix a12 = this.getSubmatrix(0, this.n/2, this.n/2);
        Matrix a21 = this.getSubmatrix(this.n/2, 0, this.n/2);
        Matrix a22 = this.getSubmatrix(this.n/2, this.n/2, this.n/2);

        Matrix b11 = b.getSubmatrix(0, 0, this.n/2);
        Matrix b12 = b.getSubmatrix(0, this.n/2, this.n/2);
        Matrix b21 = b.getSubmatrix(this.n/2, 0, this.n/2);
        Matrix b22 = b.getSubmatrix(this.n/2, this.n/2, this.n/2);

      
        Matrix m1 = (a11.sum(a22)).multStrassen(b11.sum(b22), cutoff);
        Matrix m2 = (a21.sum(a22)).multStrassen(b11, cutoff);
        Matrix m3 = a11.multStrassen(b12.sub(b22), cutoff);
        Matrix m4 = a22.multStrassen(b21.sub(b11), cutoff);
        Matrix m5 = (a11.sum(a12)).multStrassen(b22, cutoff);
        Matrix m6 = (a21.sub(a11)).multStrassen(b11.sum(b12), cutoff);
        Matrix m7 = (a12.sub(a22)).multStrassen(b21.sum(b22), cutoff);


        Matrix c = new Matrix(this.n);
        
		Matrix c11 = m1.sum(m4).sub(m5).sum(m7);
		Matrix c12 = m3.sum(m5);
		Matrix c21 = m2.sum(m4);
		Matrix c22 = m1.sub(m2).sum(m3).sum(m6);
        

		c.putSubmatrix(0, 0, c11);
		c.putSubmatrix(0, this.n/2, c12);
		c.putSubmatrix(this.n/2, 0, c21);
		c.putSubmatrix(this.n/2, this.n/2, c22);
		
        
		System.out.printf("m1: %d\n",m1.sestevek());
		System.out.printf("m2: %d\n",m2.sestevek());
		System.out.printf("m3: %d\n",m3.sestevek());
		System.out.printf("m4: %d\n",m4.sestevek());
		System.out.printf("m5: %d\n",m5.sestevek());
		System.out.printf("m6: %d\n",m6.sestevek());
		System.out.printf("m7: %d\n",m7.sestevek());
      
		return c;

	}
}



public class Izziv6{



	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int dimenzija,mejnaVrednost;

		dimenzija = sc.nextInt();
		mejnaVrednost = sc.nextInt();


		Matrix a = new Matrix(dimenzija);
		Matrix b = new Matrix(dimenzija);
		
		for(int i = 0; i<dimenzija; i++){
			for(int j = 0; j<dimenzija; j++){
				a.setV(i, j, sc.nextInt());
			}
		}

		for(int i = 0; i<dimenzija; i++){
			for(int j = 0; j<dimenzija; j++){
				b.setV(i, j, sc.nextInt());
			}
		}
		sc.close();
	
		Matrix c = a.multStrassen(b, mejnaVrednost);
		
		for(int i = 0; i<dimenzija; i++){
			for(int j = 0; j<dimenzija; j++){
				System.out.printf("%d ",c.v(i, j));
			}
			System.out.println();
		}
	}
}
