package com.auticiel.memory.screens;

import java.util.ArrayList;
import java.util.List;

import com.auticiel.memory.Constants;
import com.auticiel.memory.MemoryGame;
import com.auticiel.memory.gameobjects.Button;
import com.auticiel.memory.gameobjects.Star;
import com.auticiel.memory.gameobjects.Button.ButtonState;
import com.auticiel.memory.resources.ResourcePaths;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class MenuScreen extends BaseScreen
{	
	private List<Star> starsList = null;
	
	// Sprites
	private Sprite spr_background = null;
	private Sprite spr_star1 = null;
	private Sprite spr_star2 = null;
	private Sprite spr_title = null;
	private Sprite spr_btn_start = null;
	private Sprite spr_btn_exit = null;
	private Sprite spr_btn_volume = null;
	private Sprite spr_btn_start_pressed = null;
	private Sprite spr_btn_exit_pressed = null;
	private Sprite spr_btn_volume_pressed = null;
	
	// Buttons
	private Button button_start = null;
	private Button button_exit = null;
	private Button button_volume = null;
	
	public MenuScreen(MemoryGame game)
	{
		super(game);
		
		// Load and move sprites
		loadSprites();
		moveSprites();
		
		// Create stars
		starsList = new ArrayList<Star>();
		for (int i=0; i<Constants.STARS_COUNT; i++)
		{
			starsList.add(new Star(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
		}
		
		// Create buttons
		button_start = new Button(new Vector2(spr_btn_start.getX(), spr_btn_start.getY()));
		button_exit = new Button(new Vector2(spr_btn_exit.getX(), spr_btn_exit.getY()));
		button_volume = new Button(new Vector2(spr_btn_volume.getX(), spr_btn_volume.getY()));
	}
	
	private void loadSprites()
	{
		// Background
		Texture tx_background = new Texture(Gdx.files.internal(ResourcePaths.BG_MENUSCREEN));
		spr_background = new Sprite(tx_background);
		
		// Stars
		Texture tx_star1 = new Texture(Gdx.files.internal(ResourcePaths.IC_STAR_1));
		spr_star1 = new Sprite(tx_star1);
		Texture tx_star2 = new Texture(Gdx.files.internal(ResourcePaths.IC_STAR_2));
		spr_star2 = new Sprite(tx_star2);
		
		// Title
		Texture tx_title = new Texture(Gdx.files.internal(ResourcePaths.IC_TITLE));
		spr_title = new Sprite(tx_title);
		
		// Buttons
		Texture tx_btn_start = new Texture(Gdx.files.internal(ResourcePaths.IC_BTN_START));
		spr_btn_start = new Sprite(tx_btn_start);
		
		Texture tx_btn_exit = new Texture(Gdx.files.internal(ResourcePaths.IC_BTN_EXIT));
		spr_btn_exit = new Sprite(tx_btn_exit);
		
		Texture tx_btn_volume = new Texture(Gdx.files.internal(ResourcePaths.IC_BTN_VOLUME));
		spr_btn_volume = new Sprite(tx_btn_volume);
		
		Texture tx_btn_start_pressed = new Texture(Gdx.files.internal(ResourcePaths.IC_BTN_START_CLICKED));
		spr_btn_start_pressed = new Sprite(tx_btn_start_pressed);
		
		Texture tx_btn_exit_pressed = new Texture(Gdx.files.internal(ResourcePaths.IC_BTN_EXIT_CLICKED));
		spr_btn_exit_pressed = new Sprite(tx_btn_exit_pressed);
		
		Texture tx_btn_volume_pressed = new Texture(Gdx.files.internal(ResourcePaths.IC_BTN_VOLUME_CLICKED));
		spr_btn_volume_pressed = new Sprite(tx_btn_volume_pressed);
	}
	
	private void moveSprites()
	{
		spr_background.setPosition(-spr_background.getWidth() / 2, -spr_background.getHeight() / 2);
		spr_title.setPosition(0 - spr_title.getWidth() / 2, (float) ((Constants.SCREEN_HEIGHT / 2) - (spr_title.getHeight() * 1.1)));
		spr_btn_start.setPosition((float) (-spr_btn_start.getWidth() / 2), 0);
		spr_btn_exit.setPosition((float) (-spr_btn_exit.getWidth() / 2), (float) (spr_btn_start.getY() - spr_btn_exit.getHeight() - 10));
		spr_btn_volume.setPosition((float) (-spr_btn_volume.getWidth() / 2), (float) (spr_btn_exit.getY() - spr_btn_volume.getHeight() - 10));
		spr_btn_start_pressed.setPosition(spr_btn_start.getX(), spr_btn_start.getY());
		spr_btn_exit_pressed.setPosition(spr_btn_exit.getX(), spr_btn_exit.getY());
		spr_btn_volume_pressed.setPosition(spr_btn_volume.getX(), spr_btn_volume.getY());
	}
	
	@Override
	public void render(float delta)
	{
		super.render(delta);
		sprites.begin();
		
		// Draw background
		spr_background.draw(sprites);
		
		// Draw stars
		for (Star star : starsList)
		{
			Sprite starSprite = star.getType() == Star.STAR_TYPE_0 ? spr_star1 : spr_star2;
			star.update(delta);
			starSprite.setPosition(star.getPosition().x, star.getPosition().y);
			starSprite.draw(sprites);
		}
		
		// Draw title
		spr_title.draw(sprites);
		
		// Draw buttons
		Sprite startSprite = (button_start.getButtonState() == ButtonState.DEFAULT) ? spr_btn_start : spr_btn_start_pressed;
		Sprite exitSprite = (button_exit.getButtonState() == ButtonState.DEFAULT) ? spr_btn_exit : spr_btn_exit_pressed;
		Sprite volumeSprite = (button_volume.getButtonState() == ButtonState.DEFAULT) ? spr_btn_volume : spr_btn_volume_pressed;
		startSprite.draw(sprites);
		exitSprite.draw(sprites);
		volumeSprite.draw(sprites);
		
		sprites.end();
	}
	
	@Override
	protected void onTapConfirmed(float x, float y)
	{
		// Clear all buttons state
		button_start.changeState(ButtonState.DEFAULT);
		button_exit.changeState(ButtonState.DEFAULT);
		if (spr_btn_start.getBoundingRectangle().contains(x, y))
		{
			button_start.toggleState();
			// Load game screen
			GameScreen gameScreen = new GameScreen(game);
			game.setScreen(gameScreen);
			game.getScreensMap().put(getClass(), gameScreen);
		}
		else if (spr_btn_exit.getBoundingRectangle().contains(x, y))
		{
			button_exit.toggleState();
			Gdx.app.exit();
		}
		else if (spr_btn_volume.getBoundingRectangle().contains(x, y))
		{
			button_volume.toggleState();
			// TODO Enable/disable sound
		}
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
}
