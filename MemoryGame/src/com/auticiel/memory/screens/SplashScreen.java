package com.auticiel.memory.screens;

import com.auticiel.memory.MemoryGame;
import com.auticiel.memory.resources.ResourcePaths;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SplashScreen extends BaseScreen
{	
	private static final int SPLASH_SCREEN_DURATION = 3;
	
	// Sprites
	private Sprite spr_background = null;
	private float timeCounter = 0.0f;
	
	public SplashScreen(MemoryGame game)
	{
		super(game);
		
		timeCounter = 0.0f;
		
		// Load and move sprites
		loadSprites();
		moveSprites();
	}
	
	private void loadSprites()
	{
		Texture tx_background = new Texture(Gdx.files.internal(ResourcePaths.BG_SPLASHSCREEN));
		spr_background = new Sprite(tx_background);
	}
	
	private void moveSprites()
	{
		spr_background.setOrigin(spr_background.getWidth() / 2, spr_background.getHeight() / 2);
		spr_background.setPosition(-spr_background.getWidth() / 2, -spr_background.getHeight() / 2);
	}
	
	@Override
	public void render(float delta)
	{
		super.render(delta);
		
		timeCounter += delta;
		
		// Draw background
		sprites.begin();
		spr_background.draw(sprites);
		sprites.end();
		
		// The splashscreen should disapear after n seconds
		if (timeCounter < SPLASH_SCREEN_DURATION) return;
		
		// Display the menu screen
		BaseScreen menuScreen = new MenuScreen(game);
		game.setScreen(menuScreen);
		game.getScreensMap().put(getClass(), menuScreen);
	}
	
	@Override
	public void hide()
	{
	}
	
	@Override
	public void pause()
	{
	}
	
	@Override
	public void resume()
	{
	}
	
	@Override
	public void dispose()
	{
	}
	
	@Override
	protected void onTapConfirmed(float x, float y)
	{
	}
	
	@Override
	public void show()
	{
	}
}
