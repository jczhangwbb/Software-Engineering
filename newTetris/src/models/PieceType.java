package models;

import java.util.Random;

public class PieceType {
	public enum AllType {
		Empty,// 空白座標
		Piece_Z, // S形狀座標
		Piece_S, // Z形狀座標
		Piece_I, // I形狀座標
		Piece_T, // T形狀座標
		Piece_O, // 方形座標
		Piece_L, // L形狀座標
		Piece_ReverseL
	}
	//7種形狀的piece 和 一種空的。
	
	private AllType pieceShape;
	private int[][] Coordinate; // 一個形狀方塊的座標
	private int[][][] PieceTable; // 所有方塊的集合表

	public PieceType() {
		Coordinate = new int[4][2];
		PieceTable = new int[][][] { 
				{ { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0 } },// 空白座標
				{ { 0, -1 }, { 0, 0 }, { -1, 0 }, { -1, 1 } }, // S形狀座標
				{ { 0, -1 }, { 0, 0 }, { 1, 0 }, { 1, 1 } }, // Z形狀座標
				{ { 0, -1 }, { 0, 0 }, { 0, 1 }, { 0, 2 } }, // I形狀座標
				{ { -1, 0 }, { 0, 0 }, { 1, 0 }, { 0, 1 } }, // T形狀座標
				{ { 0, 0 }, { 1, 0 }, { 0, 1 }, { 1, 1 } }, // 方形座標
				{ { -1, -1 }, { 0, -1 }, { 0, 0 }, { 0, 1 } }, // 反L形狀座標
				{ { 1, -1 }, { 0, -1 }, { 0, 0 }, { 0, 1 } }// L形狀座標
				
		};
		setPieceType(AllType.Empty);
	}

	public void setPieceType(AllType pieceShape) {
		for (int i = 0; i < 4; i++) {
			System.arraycopy(PieceTable[pieceShape.ordinal()][i], 0, Coordinate[i], 0, 2);
		}
		this.pieceShape = pieceShape;
	}

}
