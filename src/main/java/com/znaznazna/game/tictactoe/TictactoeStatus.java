package com.znaznazna.game.tictactoe;

public class TictactoeStatus {
	
	private static final int FIELD_WIDTH = 3;
	private static final int FIELD_HEIGHT = 3;
	
	
	private TictactoeGameStatus gameStatus;
	private TictactoeCellStatus[][] gameField;
	
	public TictactoeStatus()
	{
		gameStatus = TictactoeGameStatus.STATUS_PLAYER_X_MOVE;
		gameField = new TictactoeCellStatus[FIELD_WIDTH][FIELD_HEIGHT];
		for(int rowIdx = 0; rowIdx < FIELD_WIDTH; rowIdx++)
		{
			for (int colIdx = 0; colIdx < FIELD_HEIGHT; colIdx++)
			{
				gameField[colIdx][rowIdx] = TictactoeCellStatus.CELL_EMPTY;
			}
		}
	}
	
	public TictactoeGameStatus getGameStatus()
	{
		return gameStatus;
	}
	
	public TictactoeCellStatus[][] getGameField()
	{
		return gameField;
	}
	
	public TictactoeCellStatus getCellStatus(int row, int column) throws IllegalArgumentException
	{
		if (
				   (row < 0) || (column < 0)
				|| (row >= FIELD_HEIGHT) || (column >= FIELD_WIDTH)
			)
		{
			throw new IllegalArgumentException("Invalid cell");
		}
		return gameField[column][row];
	}
	
	public void setCellStatus(int row, int column, TictactoeCellStatus newStatus) throws IllegalArgumentException
	{
		if (
				   (row < 0) || (column < 0)
				|| (row >= FIELD_HEIGHT) || (column >= FIELD_WIDTH)
			)
		{
			throw new IllegalArgumentException("Invalid cell: " + row + " : " + column);
		}
		
		if ( (gameStatus != TictactoeGameStatus.STATUS_PLAYER_X_MOVE) && (gameStatus != TictactoeGameStatus.STATUS_PLAYER_0_MOVE))
		{
			throw new IllegalArgumentException("Game is over");
		}

		if (gameField[row][column] != TictactoeCellStatus.CELL_EMPTY)
		{
			throw new IllegalArgumentException("Cell is not empty");
		}
		
		if ( ((gameStatus == TictactoeGameStatus.STATUS_PLAYER_X_MOVE) && (newStatus != TictactoeCellStatus.CELL_X))
			|| ((gameStatus == TictactoeGameStatus.STATUS_PLAYER_0_MOVE) && (newStatus != TictactoeCellStatus.CELL_0)))
		{
			throw new IllegalArgumentException("Invalid player order");
		}
		
		gameField[row][column] = newStatus;
		
		int linesDone = 0;
		if ((gameField[0][column] == gameField[1][column]) && (gameField[1][column] == gameField[2][column]))
		{
			linesDone++;
		}
		if ((gameField[row][0] == gameField[row][1]) && (gameField[row][1] == gameField[row][2]))
		{
			linesDone++;
		}
		if ((column == row) && (gameField[0][0] == gameField[1][1]) && (gameField[1][1] == gameField[2][2]))
		{
			linesDone++;
		}
		if (((column + row) == FIELD_HEIGHT - 1) && (gameField[2][0] == gameField[1][1]) && (gameField[1][1] == gameField[0][2]))
		{
			linesDone++;
		}
		
		int emptyCells = 0;
		for (int rowIdx = 0; rowIdx < FIELD_HEIGHT; rowIdx++)
		{
			for (int colIdx = 0; colIdx < FIELD_HEIGHT; colIdx++)
			{
				if (gameField[rowIdx][colIdx] == TictactoeCellStatus.CELL_EMPTY)
				{
					emptyCells++;
					break;
				}
			}
		}
		
		if ((emptyCells == 0) && (linesDone == 0))
		{
			gameStatus = TictactoeGameStatus.STATUS_PLAYER_DEAD_HEAT;
		}
		else if (gameStatus == TictactoeGameStatus.STATUS_PLAYER_X_MOVE)
		{
			if (linesDone > 0)
			{
				gameStatus = TictactoeGameStatus.STATUS_PLAYER_X_WIN;
			}
			else
			{
				gameStatus = TictactoeGameStatus.STATUS_PLAYER_0_MOVE;
			}
		}
		else 
		{
			if (linesDone > 0)
			{
				gameStatus = TictactoeGameStatus.STATUS_PLAYER_0_WIN;
			}
			else
			{
				gameStatus = TictactoeGameStatus.STATUS_PLAYER_X_MOVE;
			}
		}
	}
	
	public void setGameStatus(TictactoeGameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}

	public void setGameField(TictactoeCellStatus[][] gameField) {
		this.gameField = gameField;
	}
}
