package Main;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Desktop {
	
	public static void main(String[] args){
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = Game.V_WIDTH * Game.SCALE;

		config.height = Game.V_HEIGHT * Game.SCALE;
		
		new LwjglApplication(new Game(), config);
	}
}
