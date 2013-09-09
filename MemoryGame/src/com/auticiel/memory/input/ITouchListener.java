package com.auticiel.memory.input;

public interface ITouchListener
{
	public void onTap(float x, float y);
	public void onTouchDown(float x, float y);
	public void onTouchUp(float x, float y);
}