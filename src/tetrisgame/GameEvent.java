/*
 * TCSS 305 - Autumn 2011 
 * Project Tetris 
 * Author: Alex Stringham 
 * UWNetID: ats3216
 */

package tetrisgame;

import java.awt.Event;

/**
 * Class to control game events.
 * 
 * @author Alex Stringham
 * @version Nov. 2011
 */
@SuppressWarnings("serial")
public class GameEvent extends Event
{
  /**
   * Constant to start a game.
   */
  public static final int START = 0;
  /**
   * Constant to end a game.
   */
  public static final int END = 1;

  /**
   * Chooses whether the game is started or ended.
   */
  private final int my_action;

  /**
   * Constructor for making a game event.
   * 
   * @param the_action Tells whether the game has started or ended.
   */
  public GameEvent(final int the_action)
  {
    super(null, 0, null);
    my_action = the_action;
  }

  /**
   * Gets whether the game started or ended.
   * 
   * @return start or end constant.
   */
  public int getType()
  {
    return my_action;
  }
}
