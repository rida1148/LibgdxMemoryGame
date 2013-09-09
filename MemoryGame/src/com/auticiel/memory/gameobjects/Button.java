package com.auticiel.memory.gameobjects;

import com.badlogic.gdx.math.Vector2;

public class Button extends GameObject
{
	private ButtonState state = null;
	
	public enum ButtonState
	{
		DEFAULT, PRESSED
	}
	
	public Button(Vector2 position)
	{
		super(position);
		state = ButtonState.DEFAULT;
	}
	
	public ButtonState getButtonState()
	{
		return state;
	}
	
	public void toggleState()
	{
		state = (state == ButtonState.DEFAULT) ? ButtonState.PRESSED : ButtonState.DEFAULT;
	}
	
	public void changeState(ButtonState newButtonState)
	{
		if (state == newButtonState) return;
		state = newButtonState;
	}
	
	public void setButtonState(ButtonState buttonState)
	{
		state = buttonState;
	}
}