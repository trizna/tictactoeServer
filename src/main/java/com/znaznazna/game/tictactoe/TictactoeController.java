package com.znaznazna.game.tictactoe;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import com.znaznazna.storage.StorageApi;



@Controller
public class TictactoeController implements TictactoeSvcApi{
	
	final private StorageApi<TictactoeGame> gameStorage;
	
	@Autowired
	public TictactoeController(StorageApi<TictactoeGame> gameStorage)
	{
		this.gameStorage = gameStorage;
	}
	
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Game not found")  // 404
    public class GameNotFoundException extends RuntimeException 
    {
		private static final long serialVersionUID = 1L;
    }
	
	@ExceptionHandler
	void handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response) throws IOException 
	{
	    response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
	}
	
	@Override
	public String createGame()
	{
		TictactoeGame newGame = new TictactoeGame();
		
		Integer newGameUid = gameStorage.store(newGame);
		
		return newGameUid.toString();
	}
	
	@Override
	public TictactoeStatus getGameStatus(@PathVariable("uid") int uid)
	{
		TictactoeGame game = gameStorage.get(uid);
		if (game == null)
		{
			throw new GameNotFoundException();
		}
		
		return game.getStatus();
	}
	
	@Override
	public TictactoeStatus takeGameMove(@PathVariable("uid") int uid, @RequestBody TictactoeMove move )
	{
		TictactoeGame game = gameStorage.get(uid);
		if (game == null)
		{
			throw new GameNotFoundException();
		}
		
		game.putMove(move);
		
		gameStorage.update(uid, game);
		return game.getStatus();
	}

}
