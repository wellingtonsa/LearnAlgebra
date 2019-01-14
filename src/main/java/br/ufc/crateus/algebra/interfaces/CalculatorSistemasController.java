package br.ufc.crateus.algebra.interfaces;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import br.ufc.crateus.algebra.controle.Sistemas;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import br.ufc.crateus.algebra.util.FXMLResources;
import br.ufc.crateus.algebra.util.FatoracaoLU;
import br.ufc.crateus.algebra.util.Gauss;

public class CalculatorSistemasController extends Application implements Initializable {
	public static Stage myStage;
	Gauss g = new Gauss();
	FatoracaoLU falu = new FatoracaoLU();
	Sistemas opSis = new Sistemas();
	DecimalFormat formato = new DecimalFormat("#.#");

	@FXML
	private Pane pAB;
	@FXML
	private Pane pC;
	@FXML
	private Pane pE;
	@FXML
	private ScrollPane spA1;
	@FXML
	private ScrollPane spA2;
	@FXML
	private GridPane gpA;
	@FXML
	private GridPane gpB;
	@FXML
	private GridPane gpC;
	@FXML
	private GridPane gpD;
	@FXML
	private GridPane gpL;
	@FXML
	private GridPane gpU;
	@FXML
	private JFXButton btnMaAA;
	@FXML
	private JFXButton btnMeAA;
	@FXML
	private JFXButton btnMaLA;
	@FXML
	private JFXButton btnMeLA;
	@FXML
	private ImageView iBack;
	@FXML
	private JFXRadioButton rbGauss;
	@FXML
	private JFXRadioButton rbGaussJordan;
	@FXML
	private JFXRadioButton rbEstudo;
	@FXML
	private JFXRadioButton rbFalu;
	@FXML
	private JFXRadioButton rbPosto;
	@FXML
	private JFXButton btnCalcular;
	@FXML
	private JFXTextField tfX;
	@FXML
	private JFXTextField tfFX;
	@FXML
	private JFXTextField tfFY;
	@FXML
	private Label lbX;
	@FXML
	private Label lbY;
	@FXML
	private Label lbL;
	@FXML
	private Label lbU;

	private TextField cell;

	private int linhaA = 0;

	private int colunaA = 0;

	private int linhaB = 0;

	private int colunaB = 0;

	@Override
	public void start(Stage primaryStage) throws Exception {
		CalculatorSistemasController.myStage = primaryStage;
		Pane pane = (Pane) FXMLLoader.load(FXMLResources.CALCULATORSISTEMAS);
		Scene scene = new Scene(pane);
		scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		primaryStage.show();

	}

	public void initialize(URL location, ResourceBundle resources) {
		Settings();

		iBack.setOnMousePressed(new EventHandler<Event>() {

			public void handle(Event arg0) {
				myStage.close();
			}
		});

		btnMaAA.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent arg0) {
				if (colunaA == 0 && colunaB == 0) {
					colunaA = 1;
					colunaB = linhaB = 1;
				}
				linhaA++;
				GerarSistema();
				GerarTermos();
			}
		});

		btnMeAA.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				if (linhaA > 0)
					linhaA--;
				GerarSistema();
			}
		});

		btnMaLA.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				if (linhaA == 0) {
					linhaA = 1;
					linhaB = 1;
				}
				colunaA++;
				colunaB++;
				GerarSistema();
				GerarTermos();
			}
		});

		btnMeLA.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				if (colunaA > 0 && colunaB > 0) {
					colunaA--;
					colunaB--;
				}
				GerarSistema();
				GerarTermos();

			}
		});

		rbGauss.setOnMousePressed(new EventHandler<Event>() {

			public void handle(Event arg0) {
				pAB.setVisible(true);
				pC.setVisible(false);
				pE.setVisible(false);
				tfX.setText("");
				zerarAllMatriz();
			}
		});

		rbGaussJordan.setOnMousePressed(new EventHandler<Event>() {

			public void handle(Event arg0) {
				pAB.setVisible(true);
				pC.setVisible(false);
				pE.setVisible(false);
				tfX.setText("");
				zerarAllMatriz();
			}
		});

		rbFalu.setOnMousePressed(new EventHandler<Event>() {

			public void handle(Event arg0) {
				pAB.setVisible(true);
				pC.setVisible(false);
				pE.setVisible(false);
				tfX.setText("");
				zerarAllMatriz();
			}
		});

		rbPosto.setOnMousePressed(new EventHandler<Event>() {

			public void handle(Event arg0) {
				pAB.setVisible(true);
				pC.setVisible(false);
				pE.setVisible(false);
				tfX.setText("");
				zerarAllMatriz();
			}
		});

		rbEstudo.setOnMousePressed(new EventHandler<Event>() {

			public void handle(Event arg0) {
				pAB.setVisible(true);
				pC.setVisible(false);
				pE.setVisible(false);
				tfX.setText("");
				zerarAllMatriz();
			}
		});

		btnCalcular.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				if (rbGauss.isSelected()) {
					double[][] matr = montarSistema(linhaA, colunaA, gpA);
					if (matr[0].length == matr.length) {
						opSis.gauss(matr, montarVetor(linhaB, colunaB, gpB));
						pAB.setVisible(false);
						pC.setVisible(true);
						tfX.setVisible(true);
						lbX.setVisible(true);
						exibirSistema(g.getMatr(), gpC);
						exibirVetor(g.getB(), gpD);
						exibirSolucao(g.getX(), tfX);
					} else {
						JOptionPane.showMessageDialog(null,
								"A matriz não é NxN. \n Verifique os valores da matriz e tente novamente!");
					}

				} else if (rbGaussJordan.isSelected()) {
					double[][] matr = montarSistema(linhaA, colunaA, gpA);
					if (matr[0].length == matr.length) {
						opSis.gaussJordan(matr, montarVetor(linhaB, colunaB, gpB));
						pAB.setVisible(false);
						pC.setVisible(true);
						tfX.setVisible(false);
						lbX.setVisible(false);
						exibirSistema(g.getMatr(), gpC);
						exibirVetor(g.getB(), gpD);
					} else {
						JOptionPane.showMessageDialog(null,
								"A matriz não é NxN. \n Verifique os valores da matriz e tente novamente!");
					}
				}

				else if (rbEstudo.isSelected()) {
					double[][] matr = montarSistema(linhaA, colunaA, gpA);
					if (matr[0].length == matr.length) {
						JOptionPane.showMessageDialog(null,
								opSis.estudoSistema(matr, montarVetor(linhaB, colunaB, gpB))[0]);
					} else {
						JOptionPane.showMessageDialog(null,
								"A matriz não é NxN. \n Verifique os valores da matriz e tente novamente!");
					}

				} else if (rbFalu.isSelected()) {
					double[][] matr = montarSistema(linhaA, colunaA, gpA);
					if (matr[0].length == matr.length) {
						opSis.fatoracaoLU(matr, montarVetor(linhaB, colunaB, gpB));
						pAB.setVisible(false);
						pE.setVisible(true);
						lbX.setVisible(true);
						spA1.setVisible(true);
						spA2.setVisible(true);
						lbL.setVisible(true);
						lbU.setVisible(true);
						exibirSistema(falu.getL(), gpL);
						exibirSistema(falu.getU(), gpU);
						exibirSolucao(falu.getX(), tfFX);
						exibirSolucao(falu.getY(), tfFY);
					} else {
						JOptionPane.showMessageDialog(null,
								"A matriz não é NxN. \n Verifique os valores da matriz e tente novamente!");
					}
				} else if (rbPosto.isSelected()) {
					double[][] matr = montarSistema(linhaA, colunaA, gpA);
					if (matr[0].length == matr.length) {
						String[] postos = opSis.estudoSistema(matr, montarVetor(linhaB, colunaB, gpB));
						pAB.setVisible(false);
						pE.setVisible(true);
						lbX.setVisible(true);
						spA1.setVisible(false);
						spA2.setVisible(false);
						lbL.setVisible(false);
						lbU.setVisible(false);
						lbY.setText("MATRIZ DOS COEFICIENTE");
						lbX.setText("MATRIZ AMPLIADA");
						exibirPosto(postos[1], tfFX);
						exibirPosto(postos[2], tfFY);
					} else {
						JOptionPane.showMessageDialog(null,
								"A matriz não é NxN. \n Verifique os valores da matriz e tente novamente!");
					}
				}

			}
		});

	}

	public double[][] montarSistema(int linha, int coluna, GridPane gp) {

		double[][] sistema = new double[linha][coluna];

		for (int j = 0; j < coluna; j++) {
			for (int i = 0; i < linha; i++) {
				if (!((TextField) gp.getChildren().get(j * linha + i)).getText().isEmpty())
					sistema[i][j] = Double.parseDouble(((TextField) gp.getChildren().get(j * linha + i)).getText());
			}
		}

		return sistema;
	}

	public double[] montarVetor(int linha, int coluna, GridPane gp) {

		double[] vetor = new double[coluna];

		for (int i = 0; i < coluna; i++) {
			if (!((TextField) gp.getChildren().get(i)).getText().isEmpty())
				vetor[i] = Double.parseDouble(((TextField) gp.getChildren().get(i)).getText());

		}
		return vetor;
	}

	public void exibirSistema(double[][] sistema, GridPane gp) {
		gp.getChildren().clear();
		for (int i = 0; i < sistema.length; i++) {
			for (int j = 0; j < sistema[0].length; j++) {
				cell = new TextField();
				cell.setPrefWidth(50);
				cell.setPrefHeight(30);
				cell.setText(String.valueOf(formato.format(sistema[i][j])));
				gp.add(cell, j, i);
			}

		}

	}

	public void exibirVetor(double[] vetor, GridPane gp) {
		gp.getChildren().clear();
		for (int i = 0; i < vetor.length; i++) {
			cell = new TextField();
			cell.setPrefWidth(50);
			cell.setPrefHeight(30);
			cell.setText(String.valueOf(formato.format(vetor[i])));
			gp.add(cell, 0, i);
		}

	}

	public void exibirSolucao(double[] solucao, TextField tfS) {
		tfS.setText("");
		String s = "{";
		for (int i = 0; i < solucao.length; i++) {
			s += formato.format(solucao[i]) + ",";
		}
		s += "}";

		tfS.setText(s);
		s = "";
	}

	public void exibirPosto(String p, TextField tfS) {
		tfS.setText("");
		tfS.setText(p);
	}

	public void GerarSistema() {
		gpA.getChildren().clear();
		for (int i = 0; i < linhaA; i++) {
			for (int j = 0; j < colunaA; j++) {
				cell = new TextField();
				cell.setPrefWidth(50);
				cell.setPrefHeight(30);
				gpA.add(cell, i, j);
			}

		}

	}

	public void GerarTermos() {
		gpB.getChildren().clear();
		for (int i = 0; i < linhaB; i++) {
			for (int j = 0; j < colunaB; j++) {
				cell = new TextField();
				cell.setPrefWidth(50);
				cell.setPrefHeight(30);
				gpB.add(cell, i, j);
			}

		}

	}

	public void Settings() {
		gpA.setHgap(2);
		gpA.setVgap(2);
		gpB.setHgap(2);
		gpB.setVgap(2);
		rbGauss.setSelectedColor(Color.rgb(241, 87, 86));
		rbGaussJordan.setSelectedColor(Color.rgb(241, 87, 86));
		rbEstudo.setSelectedColor(Color.rgb(241, 87, 86));
		rbFalu.setSelectedColor(Color.rgb(241, 87, 86));
		rbPosto.setSelectedColor(Color.rgb(241, 87, 86));

	}

	public void zerarAllMatriz() {
		linhaA = linhaB = colunaA = colunaB = 0;
		gpA.getChildren().clear();
		gpB.getChildren().clear();
		gpC.getChildren().clear();
		gpD.getChildren().clear();
	}

}
