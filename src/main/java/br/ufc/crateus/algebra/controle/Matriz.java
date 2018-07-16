package br.ufc.crateus.algebra.controle;

public class Matriz {

	public double[][] soma(double[][] matr1, double[][] matr2) {
		double[][] soma = new double[matr1.length][matr1[0].length];

		for (int i = 0; i < matr1.length; i++) {
			for (int j = 0; j < matr1[0].length; j++) {
				soma[i][j] = matr1[i][j] + matr2[i][j];
			}
		}
		return soma;
	}

	public double[][] subtracao(double[][] matr1, double[][] matr2) {
		double[][] sub = new double[matr1.length][matr1[0].length];

		for (int i = 0; i < matr1.length; i++) {
			for (int j = 0; j < matr1[0].length; j++) {
				sub[i][j] = matr1[i][j] - matr2[i][j];
			}
		}
		return sub;
	}

	public double[][] multiplicarEscalar(double[][] matr, double escalar) {
		for (int i = 0; i < matr.length; i++) {
			for (int j = 0; j < matr[0].length; j++) {
				matr[i][j] = escalar * matr[i][j];
			}
		}
		return matr;
	}

	public double[][] multiplicacao(double[][] matr1, double[][] matr2) {
		double soma = 0, produto[][] = new double[matr1.length][matr2[0].length];

		for (int i = 0; i < matr1.length; i++) {
			for (int j = 0; j < matr2[0].length; j++) {
				produto[i][j] = 0;
				for (int aux = 0; aux < matr2.length; aux++) {
					soma += matr1[i][aux] * matr2[aux][j];
				}
				produto[i][j] = soma;
				soma = 0;
			}
		}
		return produto;
	}

	public double[][] identidade(double[][] matr) {
		for (int i = 0; i < matr.length; i++) {
			for (int j = 0; j < matr[0].length; j++) {
				matr[i][j] = 0;
				if (i == j) {
					matr[i][j] = 1;
				}
			}
		}
		return matr;
	}

	public double[][] potencia(double[][] matr, int exp) {
		int i = 0;
		double[][] aux;
		aux = multiplicacao(matr, matr);

		if (exp == 0)
			return identidade(matr);
		else if (exp == 1)
			return matr;
		else if (exp == 2)
			return aux;
		else if (exp > 2) {
			while (i < (exp - 2)) {
				aux = multiplicacao(aux, matr);
				i++;
			}
		}
		return aux;
	}

	public double determinante(int i, double[][] mtr) {

		if (mtr.length == 2) {
			double deter = mtr[0][0] * mtr[1][1] - mtr[0][1] * mtr[1][0];

			return deter;
		}

		else {
			double deter = 0;

			for (int j = 0; j < mtr.length; j++) {
				double[][] temp = this.SubMatriz(i, j, mtr);

				deter = deter + Math.pow(-1, i + j) * mtr[i][j] * this.determinante(0, temp);

			}

			return deter;
		}

	}

	private double[][] SubMatriz(int i, int j, double[][] matriz) {

		double[][] temp = new double[matriz.length - 1][matriz.length - 1];

		int count1 = 0;
		int count2 = 0;

		for (int k = 0; k < matriz.length; k++) {
			if (k != i) {
				count2 = 0;
				for (int l = 0; l < matriz.length; l++) {
					if (l != j) {
						temp[count1][count2] = matriz[k][l];

						count2++;
					}

				}

				count1++;
			}

		}

		return temp;

	}

	public double[][] matrizAdjunta(double[][] mtr) {

		double[][] tempAdjunta = new double[mtr.length][mtr.length];

		for (int i = 0; i < tempAdjunta.length; i++) {
			for (int j = 0; j < tempAdjunta.length; j++) {
				double[][] temp = this.SubMatriz(i, j, mtr);
				double elementoAdjunto = Math.pow(-1, i + j) * this.determinante(0, temp);

				tempAdjunta[i][j] = elementoAdjunto;
			}

		}
		return tempAdjunta;

	}

	public double[][] transposta(double[][] mtr) {

		double[][] tempTransposta = new double[mtr[0].length][mtr.length];

		for (int i = 0; i < tempTransposta.length; i++) {
			for (int j = 0; j < tempTransposta[0].length; j++) {
				tempTransposta[i][j] = mtr[j][i];

			}

		}

		return tempTransposta;

	}

	public double[][] inversa(double[][] mtr) {

		if (this.determinante(0, mtr) == 0) {
			double[][] d = null;
			return d;
		} else {
			double deter = this.determinante(0, mtr);
			double[][] adjunta = this.matrizAdjunta(mtr);
			double[][] transp = this.transposta(adjunta);
			double[][] tempInv = new double[transp.length][transp[0].length];

			for (int i = 0; i < transp.length; i++) {
				for (int j = 0; j < transp[0].length; j++) {
					tempInv[i][j] = transp[i][j] / deter;

				}

			}
			return tempInv;

		}
	}

	public void auxExibir(double[][] mtr) {
		for (int i = 0; i < mtr.length; i++) {
			for (int j = 0; j < mtr[0].length; j++) {
				System.out.print(mtr[i][j]);

			}
			System.out.println();
		}
	}
}
