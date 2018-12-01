package br.ufc.crateus.algebra.controle;

import javax.swing.JOptionPane;

import br.ufc.crateus.algebra.util.FatoracaoLU;
import br.ufc.crateus.algebra.util.Gauss;

public class Sistemas {
	Gauss g = new Gauss();
	FatoracaoLU falu = new FatoracaoLU();

	public void gauss(double[][] matr, double[] b) {
		try {
		int m = matr.length;
		int n = matr[0].length;
		double mult = 0, soma = 0;
		double[] x = new double[n];
		String escalonamento ="";


		for (int k = 0; k < n; k++) {
			for (int i = k + 1; i < m; i++) {
				mult = matr[i][k] / matr[k][k];
				for (int j = k; j < n; j++) {
					matr[i][j] = matr[i][j] - mult * matr[k][j];
					
					escalonamento+= Matriz.auxExibir(matr);
					if(isNaN(matr[i][j])) {
						JOptionPane.showMessageDialog(null, "O sistema não possui solução!", "Sistema Impossivel",JOptionPane.ERROR_MESSAGE);
						break;
					}
				}
				b[i] = b[i] - mult * b[k];
			}
		}

		
		x[n - 1] = b[m - 1] / matr[m - 1][n - 1];
		for (int i = n - 1; i >= 0; i--) {
			soma = 0;
			for (int j = i + 1; j < n; j++) {
				soma = soma + matr[i][j] * x[j];
			}
			x[i] = (b[i] - soma) / matr[i][i];
		}
		
		
		g.setMatr(matr);
		g.setB(b);
		g.setX(x);
		JOptionPane.showMessageDialog(null, escalonamento);
		} catch (Exception e) {
		}

	}

	public void gaussJordan(double[][] matr, double[] b) {
		String escalonamento ="";

		for (int i = 0; i <= b.length - 1; i++) {
			double d, c = 0;
			d = matr[i][i];

			for (int s = 0; s <= b.length - 1; s++) {
				matr[i][s] = ((matr[i][s]) / d);
				escalonamento+= Matriz.auxExibir(matr);
				if(isNaN(matr[i][s])) {
					JOptionPane.showMessageDialog(null, "O sistema não possui solução!", "Sistema Impossivel",JOptionPane.ERROR_MESSAGE);
					break;
				}
			}
			b[i] = ((b[i]) / d);

			for (int j = 0; j < b.length; j++) {

				for (int k = 0; k < b.length; k++) {

				}

			}


			for (int l = 0; l <= b.length - 1; l++) {
				if (i != l) {
					c = matr[l][i];
					for (int y = 0; y <= b.length - 1; y++) {
						matr[l][y] = matr[l][y] - c * matr[i][y];
						escalonamento+= Matriz.auxExibir(matr);
						if(isNaN(matr[l][y])) {
							JOptionPane.showMessageDialog(null, "O sistema não possui solução!", "Sistema Impossivel",JOptionPane.ERROR_MESSAGE);
							break;
						}
					}
					b[l] = b[l] - c * b[i];

					for (int j = 0; j < b.length; j++) {

						for (int k = 0; k < b.length; k++) {

						}

					}


				}
			}

		}

		
		g.setMatr(matr);
		g.setB(b);

	}

	public void fatoracaoLU(double[][] matr, double[] b) {
		try {
		int m = matr.length;
		int n = matr[0].length;
		double soma = 0;
		double[][] l = new double[m][m];
		double[][] u = matr;
		double[] x = new double[n];
		double[] y = new double[m];

		for (int j = 0; j < n; j++) {
			for (int i = j + 1; i < m; i++) {
				l[i][j] = u[i][j] / u[j][j];
				for (int k = j + 1; k < n; k++) {
					u[i][k] = u[i][k] - l[i][j] * u[j][k];
				}
				u[i][j] = 0;
			}
		}
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < m; j++) {
				if (i == j) {
					l[i][j] = 1;
				}
			}
		}
		
	

		y[0] = b[0] / l[0][0];

		for (int i = 1; i < m; i++) {
			soma = 0;
			for (int j = 0; j < i; j++) {
				soma = soma + l[i][j] * y[j];
			}
			y[i] = (b[i] - soma) / l[i][i];
		}

		x[n - 1] = y[m - 1] / u[m - 1][n - 1];
		for (int i = n - 1; i >= 0; i--) {
			soma = 0;
			for (int j = i + 1; j < n; j++) {
				soma = soma + u[i][j] * x[j];
			}
			x[i] = (y[i] - soma) / u[i][i];
		}

		
		falu.setL(l);
		falu.setU(u);
		falu.setX(x);
		falu.setY(y);
		
		} catch (Exception e) {
		}
	}

	public String[] estudoSistema(double[][] matr, double[] b) {
		int m = matr.length;
		int n = matr[0].length;
		String[] retorno = new String[3];
		int nulo = 0, lnula1 = 0, lnula2 = 0;
		double mult = 0;

		for (int k = 0; k < n; k++) {
			for (int i = k + 1; i < m; i++) {
				mult = matr[i][k] / matr[k][k];
				for (int j = k; j < n; j++) {
					matr[i][j] = matr[i][j] - mult * matr[k][j];
				}
				b[i] = b[i] - mult * b[k];
			}
		}

		for (int i = 0; i < m; i++) {
			nulo = 0;
			for (int j = 0; j < n; j++) {
				if (matr[i][j] == 0) {
					nulo++;
				}
			}
			if (nulo == n) {
				lnula1++;
			}

		}
		for (int i = 0; i < m; i++) {
			nulo = 0;
			for (int j = 0; j < n; j++) {
				if (matr[i][j] == 0 && b[i] == 0) {
					nulo++;
				}
			}
			if (nulo == n) {
				lnula2++;
			}

		}

		int posto1 = m - lnula1, posto2 = m - lnula2;

		if (posto1 == posto2 && posto1 == n) {
			retorno[0]=  "Sistema Possível e Determinado";
		} else if (posto1 == posto2 && posto1 < n) {
			retorno[0]= "Sistema Possível e Indeterminado";
		} else if (posto1 != posto2) {
			retorno[0]= "Sistema Impossível";
		}
		retorno[1] = String.valueOf(posto1);
		retorno[2] = String.valueOf(posto2);
		return retorno;
	}


	public void auxExibir(double[][] mtr) {
		for (int i = 0; i < mtr.length; i++) {
			for (int j = 0; j < mtr[0].length; j++) {
				System.out.print(mtr[i][j] + " ");

			}
			System.out.println();
		}

	}
	
	public boolean isNaN(double x){return x != x;}

}
