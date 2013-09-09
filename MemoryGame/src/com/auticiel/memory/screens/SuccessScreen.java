package com.auticiel.memory.screens;

import com.auticiel.memory.MemoryGame;
import com.auticiel.memory.resources.ResourcePaths;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SuccessScreen extends BaseScreen
{
	// Sprites
	private Sprite spr_background = null;
	
	public SuccessScreen(MemoryGame game)
	{
		super(game);
		
		// Load and move sprites
		loadSprites();
		moveSprites();
	}
	
	private void loadSprites()
	{
		Texture tx_background = new Texture(Gdx.files.internal(ResourcePaths.BG_SUCCESSSCREEN));
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
		sprites.begin();
		
		// Draw background
		spr_background.draw(sprites);
		
		// Draw cards
		
		sprites.end();
	}

	@Override
	public void dispose()
	{
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
	public void show()
	{
	}

	@Override
	protected void onTapConfirmed(float x, float y)
	{
		BaseScreen menuScreen = new MenuScreen(game);
		game.setScreen(menuScreen);
		game.getScreensMap().put(getClass(), menuScreen);
	}
}
