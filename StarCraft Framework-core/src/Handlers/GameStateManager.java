package Handlers;

import java.util.Stack;

import Main.Game;
import States.GameStates;
import States.Play;

public class GameStateManager {
	
	private Game game;
	private Stack<GameStates> gameStates;
	
	public static final int PLAY = 4984;
	
	public GameStateManager(Game game){
		this.setGame(game);
		gameStates = new Stack<GameStates>();
		pushState(PLAY);
	}
	
	public void update(float delta){
		gameStates.peek().update(delta);
	}
	
	public void render(){
		gameStates.peek().render();
	}
	
	public void tick() {
		gameStates.peek().tick();
	}
	
	private GameStates getState(int state){
		if(state == PLAY) return new Play(this);
		return null;
	}
	
	public void setState(int state){
		popState();
		pushState(state);
	}
	
	
	private void popState() {
		GameStates g = gameStates.pop();
		g.dispose();
	}

	private void pushState(int state) {
		gameStates.push(getState(state));
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}


}
