package com.auticiel.memory.gameobjects;

import com.auticiel.memory.Constants;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Star extends GameObject
{
	public static final int STAR_TYPE_1 = 1;
	public static final int STAR_TYPE_0 = 0;
	private Vector2 velocity;
	private Vector2 acceleration;
	private float mMaxX;
	private float mMinX;
	private float mMaxY;
	private float mMinY;
	
	//there are 2 resources for bubble 
	private int type = -1;
	
	public Star(Vector2 position)
	{
		super(position);
	}
	
	public Star(Vector2 position, Vector2 velocity, Vector2 acceleration)
	{
		super(position);
		this.velocity = velocity;
		this.acceleration = acceleration;
	}
	
	public Star(float targetWidth, float targetHeight)
	{
		super(new Vector2());
		
		// create vectors
		velocity = new Vector2(0, 0);
		acceleration = new Vector2(0, 0);
		
		mMaxX = targetWidth / 2;
		mMinX = -mMaxX;
		
		mMaxY = targetHeight / 2;
		mMinY = -mMaxY;
		
		init();
	}
	
	private void init()
	{
		//random Type
		type = MathUtils.random(STAR_TYPE_0, STAR_TYPE_1);
		
		// between 0 and middle of the screen
		position.x = MathUtils.random(mMinX, mMaxX);
		position.y = MathUtils.random(mMinY, 0);
		
		// always positive
		velocity.y = MathUtils.random(0, Constants.STAR_MAX_VELOCITY);
		acceleration.y = MathUtils.random(0, Constants.STAR_MAX_ACCELERATION);
	}
	
	@Override
	public void update(float delta)
	{
		// update position
		position.x += (delta * velocity.x);
		position.y += (delta * velocity.y);
		
		//reset the position
		if(position.y > mMaxY) init();
		
		// update velocity
		velocity.x += (delta * acceleration.x);
		velocity.y += (delta * acceleration.y);
	}
	
	public Vector2 getVelocity()
	{
		return velocity;
	}
	
	public void setVelocity(Vector2 velocity)
	{
		this.velocity = velocity;
	}
	
	public Vector2 getAcceleration()
	{
		return acceleration;
	}
	
	public void setAcceleration(Vector2 acceleration)
	{
		this.acceleration = acceleration;
	}
	
	public int getType()
	{
		return type;
	}
}