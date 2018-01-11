package models;

import java.util.Random;

import models.Shape.Tetrominoes;

public class Shape {
	public enum Tetrominoes {
		Empty, ZShape, SShape, LineShape, TShape, SquareShape, LShape, MirroredLShape
	}
	//7種形狀的piece 和 一種空的。
	
	private Tetrominoes pieceShape;
	private int[][] coords; // 一個形狀方塊的座標
	private int[][][] coordsTable; // 座標表

	public Shape() {
		coords = new int[4][2];
		coordsTable = new int[][][] 
				{ { { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0 } }, // 空白座標
				{ { 0, -1 }, { 0, 0 }, { -1, 0 }, { -1, 1 } }, // S形狀座標
				{ { 0, -1 }, { 0, 0 }, { 1, 0 }, { 1, 1 } }, // Z形狀座標
				{ { 0, -1 }, { 0, 0 }, { 0, 1 }, { 0, 2 } }, // I（Line）形狀座標
				{ { -1, 0 }, { 0, 0 }, { 1, 0 }, { 0, 1 } }, // T形狀座標
				{ { 0, 0 }, { 1, 0 }, { 0, 1 }, { 1, 1 } }, // 方形座標
				{ { -1, -1 }, { 0, -1 }, { 0, 0 }, { 0, 1 } }, // 反L形狀座標
				{ { 1, -1 }, { 0, -1 }, { 0, 0 }, { 0, 1 } } // L形狀座標
		};
		setPieceShape(Tetrominoes.Empty);
	}

	public void setPieceShape(Tetrominoes pieceShape) {
		for (int i = 0; i < 4; i++) {
			System.arraycopy(coordsTable[pieceShape.ordinal()][i], 0, coords[i], 0, 2);
		}
		this.pieceShape = pieceShape;
	}
}