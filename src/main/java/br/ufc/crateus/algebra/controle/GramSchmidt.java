package br.ufc.crateus.algebra.controle;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class GramSchmidt {

	public double produtoInternoUsual(double[] v, double[] w) {
		double soma = 0;

		for (int i = 0; i < v.length; i++) {
			soma += v[i] * w[i];
		}
		return soma;
	}

	public double[][] ortogonalizar(double[][] v) {

		int n = v.length;
		double[][] w = new double[n][n];

		for (int i = 0; i < n; i++) {
			w[0][i] = v[0][i];
		}
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < i; j++) {
				for (int k = 0; k < n; k++) {
					w[i][k] += (produtoInternoUsual(v[i], w[j]) / produtoInternoUsual(w[j], w[j])) * w[j][k];

					if (j == i - 1) {
						w[i][k] = v[i][k] - w[i][k];
					}

				}

			}
		}
		return w;
	}

	public double[][] ortonormalizar(double[][] v) {

		int n = v.length;
		double[] w = new double[n];
		double[][] x = new double[n][n];
		BigDecimal bd;

		for (int i = 0; i < n; i++) {

			bd = new BigDecimal(produtoInternoUsual(v[i], v[i])).setScale(4, RoundingMode.HALF_EVEN);

			w[i] = Math.sqrt(bd.doubleValue());

		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (w[i] != 0)
					x[i][j] = v[i][j] / w[i];
			}
		}
		return x;
	}
}
