package com.znaznazna.game.tictactoe;

public class TictactoeMove 
{
	int cellRow;
	int cellColum;
	TictactoeCellStatus cellStatus;

	public int getCellRow() 
	{
		return cellRow;
	}
	
	public int getCellColum() 
	{
		return cellColum;
	}
	
	public TictactoeCellStatus getCellStatus() 
	{
		return cellStatus;
	}
	public void setCellRow(int cellRow) 
	{
		this.cellRow = cellRow;
	}
	
	public void setCellColum(int cellColum) 
	{
		this.cellColum = cellColum;
	}
	
	public void setCellStatus(TictactoeCellStatus cellStatus) 
	{
		this.cellStatus = cellStatus;
	}
}
