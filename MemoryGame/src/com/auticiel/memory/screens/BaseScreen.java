package com.auticiel.memory.screens;

import com.auticiel.memory.MemoryGame;
import com.auticiel.memory.input.ITouchListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class BaseScreen implements Screen, ITouchListener
{	
	protected OrthographicCamera camera = null;
	protected SpriteBatch sprites = null;
	protected MemoryGame game = null;
	
	public BaseScreen(MemoryGame game)
	{
		this.game = game;
		this.camera = game.getCamera();
		this.sprites = game.getSprites();
	}
	
	@Override
	public void resize(int width, int height)
	{
	}
	
	@Override
	public void render(float delta)
	{
		// Clear the screen
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		// set Projection Matrix
		sprites.setProjectionMatrix(camera.combined);
	}
	
	@Override
	public void onTap(float x, float y)
	{
		// translate to world coordinates
		Vector3 vec = new Vector3(x, y, 0);
		camera.unproject(vec);
		
		// delegate to derived screen
		onTapConfirmed(vec.x, vec.y);
	}
	
	@Override
	public void onTouchDown(float x, float y)
	{
		// translate to world coordinates
		Vector3 vec = new Vector3(x, y, 0);
		camera.unproject(vec);
		
		// delegate to derived screen
		ononTouchDownConfirmed(vec.x, vec.y);
	}
	
	@Override
	public void onTouchUp(float x, float y)
	{
		// translate to world coordinates
		Vector3 vec = new Vector3(x, y, 0);
		camera.unproject(vec);
		
		// delegate to derived screen
		onTouchUpConfirmed(vec.x, vec.y);
	}
	
	protected void onTouchUpConfirmed(float x, float y)
	{
	}
	
	protected void ononTouchDownConfirmed(float x, float y)
	{
	}
	
	protected abstract void onTapConfirmed(float x, float y);
}