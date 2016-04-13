package com.znaznazna.game.tictactoe;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class TictactoeGameTest {
	
	private final int FIELD_SIZE = 3;

	@Test
	public void initialTest() {
		TictactoeGame game = new TictactoeGame();
		
		TictactoeGameStatus status = game.getStatus().getGameStatus();
		assertEquals(status, TictactoeGameStatus.STATUS_PLAYER_X_MOVE);
		
		TictactoeCellStatus[][] gameField = game.getStatus().getGameField();
		assertEquals(gameField.length, FIELD_SIZE);
		for (int rowIdx = 0; rowIdx < FIELD_SIZE; rowIdx++)
		{
			assertEquals(gameField[rowIdx].length, FIELD_SIZE);
			for (int colIdx = 0; colIdx < FIELD_SIZE; colIdx++)
			{
				assertEquals(gameField[rowIdx][colIdx], TictactoeCellStatus.CELL_EMPTY);
			}
			
		}
	}
	
	@Test
	public void putMoveTest() {
		TictactoeGame game = new TictactoeGame();
		TictactoeMove move = new TictactoeMove();
		
		move.setCellColum(1);
		move.setCellRow(1);
		move.setCellStatus(TictactoeCellStatus.CELL_X);
		
		game.putMove(move);
		
		TictactoeGameStatus status = game.getStatus().getGameStatus();
		assertEquals(status, TictactoeGameStatus.STATUS_PLAYER_0_MOVE);
		
		TictactoeCellStatus gameCell = game.getStatus().getCellStatus(1, 1);
		assertEquals(gameCell, TictactoeCellStatus.CELL_X);
	}
	
	@Test
	public void verticalWinTest() {
		TictactoeGame game = new TictactoeGame();
		TictactoeMove move = new TictactoeMove();
		TictactoeGameStatus status = TictactoeGameStatus.STATUS_UNDEFINED;
		
		for (int rowIdx = 0; rowIdx < FIELD_SIZE; rowIdx++)
		{
			status = game.getStatus().getGameStatus();
			assertEquals(status, TictactoeGameStatus.STATUS_PLAYER_X_MOVE);
			move.setCellColum(0);
			move.setCellRow(rowIdx);
			move.setCellStatus(TictactoeCellStatus.CELL_X);
			game.putMove(move);
			
			status = game.getStatus().getGameStatus();
			if (status == TictactoeGameStatus.STATUS_PLAYER_0_MOVE)
			{
				move.setCellColum(1);
				move.setCellStatus(TictactoeCellStatus.CELL_0);
				game.putMove(move);
			}
		}
		assertEquals(status, TictactoeGameStatus.STATUS_PLAYER_X_WIN);
	}
	
	@Test
	public void horizontalWinTest() {
		TictactoeGame game = new TictactoeGame();
		TictactoeMove move = new TictactoeMove();
		TictactoeGameStatus status = TictactoeGameStatus.STATUS_UNDEFINED;
		
		move.setCellColum(0);
		move.setCellRow(2);
		move.setCellStatus(TictactoeCellStatus.CELL_X);
		game.putMove(move);
		
		for (int colIdx = 0; colIdx < FIELD_SIZE; colIdx++)
		{
			status = game.getStatus().getGameStatus();
			assertEquals(status, TictactoeGameStatus.STATUS_PLAYER_0_MOVE);
			
			move.setCellColum(colIdx);
			move.setCellRow(1);
			move.setCellStatus(TictactoeCellStatus.CELL_0);
			game.putMove(move);

			status = game.getStatus().getGameStatus();
			if (status == TictactoeGameStatus.STATUS_PLAYER_X_MOVE)
			{
				move.setCellColum(colIdx);
				move.setCellRow(0);
				move.setCellStatus(TictactoeCellStatus.CELL_X);
				game.putMove(move);
			}
		}
		assertEquals(status, TictactoeGameStatus.STATUS_PLAYER_0_WIN);
	}
	
	@Test
	public void diagonalWinTest() {
		TictactoeGame game = new TictactoeGame();
		TictactoeMove move = new TictactoeMove();
		TictactoeGameStatus status = TictactoeGameStatus.STATUS_UNDEFINED;
		
		for (int cellIdx = 0; cellIdx < FIELD_SIZE; cellIdx++)
		{
			status = game.getStatus().getGameStatus();
			assertEquals(status, TictactoeGameStatus.STATUS_PLAYER_X_MOVE);
			move.setCellColum(cellIdx);
			move.setCellRow(cellIdx);
			move.setCellStatus(TictactoeCellStatus.CELL_X);
			game.putMove(move);
			
			status = game.getStatus().getGameStatus();
			if (status == TictactoeGameStatus.STATUS_PLAYER_0_MOVE)
			{
				move.setCellRow(2);
				move.setCellStatus(TictactoeCellStatus.CELL_0);
				game.putMove(move);
			}
		}
		assertEquals(status, TictactoeGameStatus.STATUS_PLAYER_X_WIN);
	}
	
	@Test
	public void backDiagonalWinTest() {
		TictactoeGame game = new TictactoeGame();
		TictactoeMove move = new TictactoeMove();
		TictactoeGameStatus status = TictactoeGameStatus.STATUS_UNDEFINED;
		
		move.setCellColum(0);
		move.setCellRow(0);
		move.setCellStatus(TictactoeCellStatus.CELL_X);
		game.putMove(move);
		
		for (int cellIdx = 0; cellIdx < FIELD_SIZE; cellIdx++)
		{
			status = game.getStatus().getGameStatus();
			assertEquals(status, TictactoeGameStatus.STATUS_PLAYER_0_MOVE);
			
			move.setCellColum(FIELD_SIZE - cellIdx - 1);
			move.setCellRow(cellIdx);
			move.setCellStatus(TictactoeCellStatus.CELL_0);
			game.putMove(move);

			status = game.getStatus().getGameStatus();
			if (status == TictactoeGameStatus.STATUS_PLAYER_X_MOVE)
			{
				move.setCellRow(2);
				move.setCellStatus(TictactoeCellStatus.CELL_X);
				game.putMove(move);
			}
		}
		assertEquals(status, TictactoeGameStatus.STATUS_PLAYER_0_WIN);
	}

}
