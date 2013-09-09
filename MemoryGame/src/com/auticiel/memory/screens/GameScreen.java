package com.auticiel.memory.screens;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.auticiel.memory.Constants;
import com.auticiel.memory.MemoryGame;
import com.auticiel.memory.gameobjects.Card;
import com.auticiel.memory.gameobjects.Card.CardState;
import com.auticiel.memory.resources.ResourcePaths;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class GameScreen extends BaseScreen
{
	// Cards
	private List<Card> cardsList = null;
	private List<Card> selectedCards = null;
	
	// Sprites
	private Sprite spr_background = null;
	private Sprite spr_card_default = null;
	private Sprite spr_card_1 = null;
	private Sprite spr_card_2 = null;
	private Sprite spr_card_3 = null;
	private Sprite spr_card_4 = null;
	private Sprite spr_card_5 = null;
	private Sprite spr_card_6 = null;
	
	public GameScreen(MemoryGame game)
	{
		super(game);
		
		// Load and move sprites
		loadSprites();
		moveSprites();
		
		// Create cards (6 different sets)
		cardsList = new ArrayList<Card>();
		int k = 0;
		for (int i=0; i<3; i++)
		{
			for (int j=0; j<4; j++)
			{
				cardsList.add(new Card(i, j, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, k));
				k = k < 5 ? k + 1 : 0;
			}
		}
		
		// Shuffle the list
		Collections.shuffle(cardsList);
		
		// Init selected cards array
		selectedCards = new ArrayList<Card>(2);
	}
	
	private void loadSprites()
	{
		// Background
		Texture tx_background = new Texture(Gdx.files.internal(ResourcePaths.BG_GAMESCREEN));
		spr_background = new Sprite(tx_background);
		
		// Cards
		Texture tx_card_default = new Texture(Gdx.files.internal(ResourcePaths.IC_CARD_DEFAULT));
		spr_card_default = new Sprite(tx_card_default);
		Texture tx_card_1 = new Texture(Gdx.files.internal(ResourcePaths.IC_CARD_TYPE_1));
		spr_card_1 = new Sprite(tx_card_1);
		Texture tx_card_2 = new Texture(Gdx.files.internal(ResourcePaths.IC_CARD_TYPE_2));
		spr_card_2 = new Sprite(tx_card_2);
		Texture tx_card_3 = new Texture(Gdx.files.internal(ResourcePaths.IC_CARD_TYPE_3));
		spr_card_3 = new Sprite(tx_card_3);
		Texture tx_card_4 = new Texture(Gdx.files.internal(ResourcePaths.IC_CARD_TYPE_4));
		spr_card_4 = new Sprite(tx_card_4);
		Texture tx_card_5 = new Texture(Gdx.files.internal(ResourcePaths.IC_CARD_TYPE_5));
		spr_card_5 = new Sprite(tx_card_5);
		Texture tx_card_6 = new Texture(Gdx.files.internal(ResourcePaths.IC_CARD_TYPE_6));
		spr_card_6 = new Sprite(tx_card_6);
	}
	
	private void moveSprites()
	{
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
		for (Card card : cardsList)
		{
			// Get sprite
			Sprite cardSprite = null;
			switch (card.getType())
			{
				case 0:
					if (card.getState() == CardState.DEFAULT) cardSprite = spr_card_default;
					else if (card.getState() == CardState.SELECTED) cardSprite = spr_card_1;
					else continue;
					break;
					
				case 1:
					if (card.getState() == CardState.DEFAULT) cardSprite = spr_card_default;
					else if (card.getState() == CardState.SELECTED) cardSprite = spr_card_2;
					else continue;
					break;
					
				case 2:
					if (card.getState() == CardState.DEFAULT) cardSprite = spr_card_default;
					else if (card.getState() == CardState.SELECTED) cardSprite = spr_card_3;
					else continue;
					break;
					
				case 3:
					if (card.getState() == CardState.DEFAULT) cardSprite = spr_card_default;
					else if (card.getState() == CardState.SELECTED) cardSprite = spr_card_4;
					else continue;
					break;
					
				case 4:
					if (card.getState() == CardState.DEFAULT) cardSprite = spr_card_default;
					else if (card.getState() == CardState.SELECTED) cardSprite = spr_card_5;
					else continue;
					break;
					
				case 5:
					if (card.getState() == CardState.DEFAULT) cardSprite = spr_card_default;
					else if (card.getState() == CardState.SELECTED) cardSprite = spr_card_6;
					else continue;
					break;
			}
			
			// Move and draw
			cardSprite.setPosition(card.getPosition().x - Constants.SCREEN_WIDTH / 2 + 30, card.getPosition().y - Constants.SCREEN_HEIGHT / 2 + 65);
			cardSprite.draw(sprites);
			card.setSprite(cardSprite);
		}
		
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
		for (Card card : cardsList)
		{
			if (card.getSprite().getBoundingRectangle().contains(x, y))
			{
				if (card.getState() == CardState.DEFAULT)
				{
					card.setState(CardState.SELECTED);
					if ( selectedCards.size() < 2) selectedCards.add(card);
					else
					{
						// Check selected cards type
						if (selectedCards.get(0).getType() == selectedCards.get(1).getType())
						{
							// We can hide the both cards
							selectedCards.get(0).setState(CardState.HIDDEN);
							selectedCards.get(1).setState(CardState.HIDDEN);
							
							// Is there any cards left ?
							boolean left = false;
							for (Card tmpCard : cardsList)
							{
								if (tmpCard.getState() == CardState.DEFAULT) left = true;
							}
							
							// No card left, we can show up the success screen
							if (!left)
							{
								SuccessScreen successScreen = new SuccessScreen(game);
								game.setScreen(successScreen);
								game.getScreensMap().put(getClass(), successScreen);
							}
						}
						
						selectedCards.clear();
					}
				}
			}
		}
	}
}
