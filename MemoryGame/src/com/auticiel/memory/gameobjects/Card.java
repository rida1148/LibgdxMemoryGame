package com.auticiel.memory.gameobjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Card extends GameObject
{
	private int type = -1;
	private CardState state = null;
	private Sprite sprite = null;
	
	public enum CardState
	{
		DEFAULT, SELECTED, HIDDEN
	}
	
	public Card(Vector2 position, int type)
	{
		super(position);
		this.type = type;
		this.state = CardState.DEFAULT;
	}

	public Card(int row, int column, float targetWidth, float targetHeight, int type)
	{
		super(new Vector2(column * (targetWidth / 4), row * (targetHeight / 3)));
		this.type = type;
		this.state = CardState.DEFAULT;
	}

	public int getType()
	{
		return type;
	}

	public CardState getState()
	{
		return state;
	}

	public void setState(CardState state)
	{
		this.state = state;
	}

	public Sprite getSprite()
	{
		return sprite;
	}

	public void setSprite(Sprite sprite)
	{
		this.sprite = sprite;
	}
	
}
