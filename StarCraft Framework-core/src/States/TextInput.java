package States;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Input.TextInputListener;

public class TextInput extends ApplicationAdapter implements TextInputListener {
	String input;
	public void create(){
		
	}
	
	public void render(){
		
	}
	
	@Override
	public void input(String text) {
		input = text;
	}

	@Override
	public void canceled() {
		
	}

}
