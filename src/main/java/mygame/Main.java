package mygame;

import com.jme3.app.SimpleApplication;

public class Main extends SimpleApplication {
	
	public Main() {
		super(new VisualAppState(),
				new EntityDataState());
	}

	@Override
	public void simpleInitApp() {
		// TODO Auto-generated method stub
	}

	public static void main(String[] args) {
		Main app = new Main();
		app.start();
	}

}