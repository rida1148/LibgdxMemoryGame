package com.auticiel.memory.input;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.input.GestureDetector;

public class TouchUpGestureDetector extends GestureDetector
{
	private Game game;
	
	public TouchUpGestureDetector(float halfTapSquareSize, float tapCountInterval, float longPressDuration,	float maxFlingDelay, GestureListener listener, Game game)
	{
		super(halfTapSquareSize, tapCountInterval, longPressDuration, maxFlingDelay, listener);
		this.game = game;
	}
	
	public TouchUpGestureDetector(GestureListener listener, Game game)
	{
		super(listener);
		this.game = game;
	}
	
	@Override
	public boolean touchUp(int x, int y, int pointer, int button)
	{
		((ITouchListener) game.getScreen()).onTouchUp(x, y);
		return super.touchUp(x, y, pointer, button);
	}
}
