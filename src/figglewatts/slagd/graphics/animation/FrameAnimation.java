package figglewatts.slagd.graphics.animation;

import com.badlogic.gdx.math.Rectangle;

/**
 * A class to deal with a single animation
 */
public class FrameAnimation implements Cloneable
{
	/**
	 * First frame of the animation
	 */
	private Rectangle initialFrame;
	
	/**
	 * Number of frames in the animation
	 */
	private int frameCount;
	
	/**
	 * The current frame the animation is on
	 */
	private int currentFrame;
	
	/**
	 * Amount of time to show each frame - in milliseconds
	 */
	private float frameLength;
	
	/**
	 * The amount of time since last animation
	 */
	private float frameTimer;
	
	/**
	 * No. of times the animation has been played
	 */
	private int playCount = 0;
	
	/**
	 * The animation that should be played after this animation
	 */
	private String nextAnimation;

	/**
	 * @return the nextAnimation
	 */
	public String getNextAnimation() 
	{
		return nextAnimation;
	}

	/**
	 * @param nextAnimation the nextAnimation to set
	 */
	public void setNextAnimation(String nextAnimation) 
	{
		this.nextAnimation = nextAnimation;
	}

	/**
	 * @return the playCount
	 */
	public int getPlayCount() 
	{
		return playCount;
	}

	/**
	 * @param playCount the playCount to set
	 */
	public void setPlayCount(int playCount) 
	{
		this.playCount = playCount;
	}

	/**
	 * @return the frameCount
	 */
	public int getFrameCount() 
	{
		return frameCount;
	}

	/**
	 * @param frameCount the frameCount to set
	 */
	public void setFrameCount(int frameCount)
	{
		this.frameCount = frameCount;
	}

	/**
	 * @return the currentFrame
	 */
	public int getCurrentFrame() 
	{
		return currentFrame;
	}

	/**
	 * @param currentFrame the currentFrame to set
	 */
	public void setCurrentFrame(int currentFrame) 
	{
		this.currentFrame = currentFrame;
	}

	/**
	 * @return the frameLength
	 */
	public float getFrameLength() 
	{
		return frameLength;
	}

	/**
	 * @param frameLength the frameLength to set
	 */
	public void setFrameLength(float frameLength) 
	{
		this.frameLength = frameLength;
	}
	
	/**
	 * 
	 * @return Height of the frame
	 */
	public float getFrameHeight()
	{
		return this.initialFrame.height;
	}
	
	/**
	 * 
	 * @return Width of the frame
	 */
	public float getFrameWidtht()
	{
		return this.initialFrame.width;
	}
	
	/**
	 * 
	 * @return current frame rectangle
	 */
	public Rectangle getFrameRectangle()
	{
		return new Rectangle(this.initialFrame.x + (initialFrame.width * currentFrame),
				this.initialFrame.y, initialFrame.width, initialFrame.height);
	}
	
	/**
	 * 
	 * @param firstFrame The first frame of the animation
	 * @param frames Number of frames in the animation
	 */
	public FrameAnimation(Rectangle firstFrame, int frames)
	{
		this(firstFrame, frames, 200);
	}
	
	/**
	 * @param firstFrame The first frame of the animation
	 * @param frames Number of frames in the animation
	 * @param frameLength Interval between frames
	 */
	public FrameAnimation(Rectangle firstFrame, int frames, float frameLength)
	{
		this(firstFrame, frames, frameLength, null);
	}
	
	/**
	 * 
	 * @param firstFrame The first frame of the animation
	 * @param frames Number of frames in the animation
	 * @param frameLength Interval between frames
	 * @param nextAnimation The string reference to the next animation
	 */
	public FrameAnimation(Rectangle firstFrame, int frames, float frameLength, String nextAnimation)
	{
		this.initialFrame = firstFrame;
		this.frameCount = frames;
		this.frameLength = frameLength;
		this.nextAnimation = nextAnimation;
	}
	
	public void update(double delta)
	{
		this.frameTimer += delta;

		if (this.frameTimer > this.frameLength)
		{
			this.frameTimer = 0.0F;
			this.currentFrame = (this.currentFrame + 1) % this.frameCount;
			if (this.currentFrame == 0)
				this.playCount = Math.min(this.playCount + 1, Integer.MAX_VALUE);
		}
	}
	
	@Override
	public Object clone()
	{
		return new FrameAnimation(this.initialFrame,
				this.frameCount, this.frameLength, this.nextAnimation);
	}
}
