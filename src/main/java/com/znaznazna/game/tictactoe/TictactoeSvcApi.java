package com.znaznazna.game.tictactoe;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

public interface TictactoeSvcApi {
	
	public static final String GAME_SVC_PATH = "/tictactoe";
	
	
	@RequestMapping(value = GAME_SVC_PATH + "/new", method = RequestMethod.GET)
	public @ResponseBody String createGame();
	
	@RequestMapping(value = GAME_SVC_PATH + "/{uid}", method = RequestMethod.GET)
	
	public @ResponseBody TictactoeStatus getGameStatus(@PathVariable("uid") int uid);
	
	@RequestMapping(value = GAME_SVC_PATH + "/{uid}", method = RequestMethod.POST)
	
	public  @ResponseBody TictactoeStatus takeGameMove(@PathVariable("uid") int uid, @RequestBody TictactoeMove move );

}
