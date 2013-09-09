package com.auticiel.memory.input;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;

public class TouchGestureListener implements GestureListener
{
	private Game game;

	public TouchGestureListener(Game game)
	{
		this.game = game;
	}

	@Override
	public boolean tap(float x, float y, int count, int button)
	{
		((ITouchListener) game.getScreen()).onTap(x, y);
		return true;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button)
	{
		((ITouchListener) game.getScreen()).onTouchDown(x, y);
		return true;
	}

	@Override
	public boolean longPress(float x, float y)
	{
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button)
	{
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY)
	{
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance)
	{
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2)
	{
		return false;
	}
}
