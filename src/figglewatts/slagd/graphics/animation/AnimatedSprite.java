package figglewatts.slagd.graphics.animation;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import figglewatts.slagd.graphics.Sprite;

/**
 * A class to deal with all the ins and outs of having an animated sprite
 */
public class AnimatedSprite extends Sprite
{
	private boolean animating;
	private Map<String, FrameAnimation> animations = new Hashtable<String, FrameAnimation>();
	private String currentAnimation;

	/**
	 * @return whether or not it is currently animating
	 */
	public boolean isAnimating() 
	{
		return animating;
	}

	/**
	 * @param animating the animating to set
	 */
	public void setAnimating(boolean animating) 
	{
		this.animating = animating;
	}

	/**
	 * @return the currentAnimation
	 */
	public String getCurrentAnimation() 
	{
		return currentAnimation;
	}

	/**
	 * @param currentAnimation the currentAnimation to set
	 */
	public void setCurrentAnimation(String currentAnimation) 
	{
		this.currentAnimation = currentAnimation;
	}
	
	public AnimatedSprite(int x, int y, int w, int h, Texture texture)
	{
		super(x, y, w, h, texture);
		this.animating = true;
		this.setDrawCol(Color.WHITE);
		this.setPosition(Vector2.Zero);
		this.animations = new Hashtable<String, FrameAnimation>();
		this.currentAnimation = null;
	}
	
	public FrameAnimation getCurrentFrameAnimation()
	{
		if(currentAnimation != null || currentAnimation != "")
		{
			return animations.get(currentAnimation);
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * Add an animation to the Dictionary of animations.
	 * @param name The name of the animation.
	 * @param rect The Rectangle to represent the start position and size of each frame.
	 * @param frames The number of frames in the animation.
	 * @param frameLength The amount of time each frame is drawn for before moving to the next frame.
	 */
	public void addAnimation(String name, Rectangle rect, int frames, float frameLength)
    {
        animations.put(name, new FrameAnimation(rect, frames, frameLength));
        this.setWidth((int) rect.width);
        this.setHeight((int) rect.height);
        this.setCentre(new Vector2(super.getWidth() / 2, super.getHeight() / 2));
    }
	/**
	 * Add an animation to the Dictionary of animations.
	 * @param name The name of the animation.
	 * @param rect The Rectangle to represent the start position and size of each frame.
	 * @param frames The number of frames in the animation.
	 * @param frameLength The amount of time each frame is drawn for before moving to the next frame.
	 * @param nextAnimation The name of the animation to play immediately after this one.
	 */
    public void addAnimation(String name, Rectangle rect, int frames,
       float frameLength, String nextAnimation)
    {
        animations.put(name, new FrameAnimation(rect, frames, frameLength, nextAnimation));
        this.setWidth((int) rect.width);
        this.setHeight((int) rect.height);
        this.setCentre(new Vector2(super.getWidth() / 2, super.getHeight() / 2));
    }
	
    public FrameAnimation getAnimationByName(String name)
    {
        if (animations.get(name) != null)
        {
            return animations.get(name);
        }
        else
        {
            return null;
        }
    }
    
	/**
	 * Updates the sprite
	 */
	public void update(double delta)
	{
		if(animating)
		{
			if(getCurrentAnimation() == null)
			{
				if(animations.size() > 0)
				{
					@SuppressWarnings("unused") // #yolo
					String[] keys = new String[animations.size()];
					Set<String> keysSet = new HashSet<String>(animations.keySet());
					keys = (String[])keysSet.toArray();
				}
				else
				{
					return;
				}
			}
			
			// run the animations update method
			this.getCurrentFrameAnimation().update(delta);

			// check to see if there is a followup animation for this animation
			if (this.getCurrentFrameAnimation().getNextAnimation() != null)
			{
				// if there is, see if the currently playing animation has completed a full loop
				if (this.getCurrentFrameAnimation().getPlayCount() > 0)
				{
					// if it has, set up the next animation
					getCurrentFrameAnimation().setPlayCount(0);
					currentAnimation = this.getCurrentFrameAnimation().getNextAnimation();
				}
			}
		}
	}

	/**
	 * Draws the sprite using a SpriteBatch along wth an x offset and a y offset.
	 * @param batch The SpriteBatch to use.
	 */
	public void Draw(SpriteBatch batch)
	{
		if(animating)
		{
			batch.setColor(this.getDrawCol());
			batch.draw(this.getTexture(), this.getXPos(), this.getYPos(),
					(int)this.getCurrentFrameAnimation().getFrameRectangle().width,
					(int)this.getCurrentFrameAnimation().getFrameRectangle().height,
					(int)this.getCurrentFrameAnimation().getFrameRectangle().x,
					(int)this.getCurrentFrameAnimation().getFrameRectangle().y,
					(int)this.getCurrentFrameAnimation().getFrameRectangle().width,
					(int)this.getCurrentFrameAnimation().getFrameRectangle().height,
					false, true);
		}
	}
}
