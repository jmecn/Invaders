package mygame;

import com.jme3.app.SimpleApplication;

public class Main extends SimpleApplication {
	
	public Main() {
		super(new VisualAppState(),
				new ExplosionAppState(),
				new DecayAppState(),
				new ControlAppState(),
				new InvadersAIAppState(),
				new CollisionAppState(),
				new BulletAppState(),
				new GameAppState(),
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