package com.auticiel.memory;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.auticiel.memory.input.TouchGestureListener;
import com.auticiel.memory.input.TouchUpGestureDetector;
import com.auticiel.memory.screens.BaseScreen;
import com.auticiel.memory.screens.SplashScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MemoryGame extends Game
{		
	private Map<Class<? extends BaseScreen>, BaseScreen> screensMap = null;
	private OrthographicCamera camera = null;
	private SpriteBatch sprites = null;
	
	public MemoryGame()
	{
		super();
		
		// Init the class variables
		screensMap = new HashMap<Class<? extends BaseScreen>, BaseScreen>();
	}

	@Override
	public void create()
	{
		// Create a new camera
		camera = new OrthographicCamera(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		
		// Init the sprites
		sprites = new SpriteBatch();
		
		// Display the splashscreen
		setScreen(new SplashScreen(this));
		
		// Init the new touch detector
		Gdx.input.setInputProcessor(new TouchUpGestureDetector(new TouchGestureListener(this), this));
	}
	
	@Override
	public void dispose()
	{
		super.dispose();
		
		// Deallocate all screens
		for (Entry<Class<? extends BaseScreen>, BaseScreen> entry : screensMap.entrySet())
		{
			entry.getValue().dispose();
		}
	}

	public Map<Class<? extends BaseScreen>, BaseScreen> getScreensMap()
	{
		return screensMap;
	}

	public OrthographicCamera getCamera()
	{
		return camera;
	}

	public SpriteBatch getSprites()
	{
		return sprites;
	}	
}
