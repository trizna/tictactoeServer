package com.znaznazna.game.tictactoe;

public class TictactoeGame 
{
	private TictactoeStatus status;
	
	public TictactoeGame() 
	{
		status = new TictactoeStatus();
	}
	
	public TictactoeStatus getStatus() 
	{
		return status;
	}

	public void putMove(TictactoeMove move) throws IllegalArgumentException 
	{
		
		TictactoeCellStatus newCellStatus=  move.getCellStatus();
		int cellRow = move.getCellRow();
		int cellColumn = move.getCellColum();
		
		status.setCellStatus(cellRow, cellColumn, newCellStatus);
	}
}
