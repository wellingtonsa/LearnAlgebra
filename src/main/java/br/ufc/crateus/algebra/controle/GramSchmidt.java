package br.ufc.crateus.algebra.controle;

import java.text.DecimalFormat;

public class GramSchmidt {

	public double produtoInternoUsual(double[] v, double[] w) {
		double soma = 0;

		for (int i = 0; i < v.length; i++) {
			soma += v[i] * w[i];
		}
		return soma;
	}

	public void gramSchmidt(double[][] v) {
		DecimalFormat formato = new DecimalFormat("#.#########");
		int n = v.length;
		double[][] w = new double[n][n];
		double[][] aux = new double[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				w[i][j] = 0;
			}
		}

		for (int i = 0; i < n; i++) {
			w[0][i] = v[0][i];
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(" " + formato.format(w[i][j]));
			}
			System.out.println();
		}

	}
}
