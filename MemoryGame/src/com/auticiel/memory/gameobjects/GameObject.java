package com.auticiel.memory.gameobjects;

import com.badlogic.gdx.math.Vector2;

public abstract class GameObject
{
	protected Vector2 position;
	
	public GameObject(Vector2 position)
	{
		this.position = position;
	}
	
	public void setPosition(float x, float y)
	{
		position.x = x;
		position.y = y;
	}
	
	protected void update(float delta)
	{
	}
	
	public Vector2 getPosition()
	{
		return position;
	}
	
	public void setPosition(Vector2 position)
	{
		this.position = position;
	}
}