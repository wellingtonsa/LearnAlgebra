package br.ufc.crateus.algebra.util;

public class FatoracaoLU {

	private static double[][] l;
	private static double[][] u;
	private static double[] y;
	private static double[] x;

	public double[][] getL() {
		return l;
	}

	public void setL(double[][] l) {
		FatoracaoLU.l = l;
	}

	public double[][] getU() {
		return u;
	}

	public void setU(double[][] u) {
		FatoracaoLU.u = u;
	}

	public double[] getY() {
		return y;
	}

	public void setY(double[] y) {
		FatoracaoLU.y = y;
	}

	public double[] getX() {
		return x;
	}

	public void setX(double[] x) {
		FatoracaoLU.x = x;
	}

}
