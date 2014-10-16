/*
 * TCSS 305 - Autumn 2011 
 * Project Tetris 
 * Author: Alex Stringham 
 * UWNetID: ats3216
 */

package tetrisgame;

import java.awt.Event;

/**
 * Class for score event actions.
 * 
 * @author Alex Stringham
 * @version Nov. 2011
 */
@SuppressWarnings("serial")
public class ScoreEvent extends Event
{
  /**
   * Stores a players score.
   */
  private final int my_score;

  /**
   * Constructor to create a score event.
   * 
   * @param the_score A players score.
   */
  public ScoreEvent(final int the_score)
  {
    super(null, 0, null);
    my_score = the_score;
  }

  /**
   * Gets the players school.
   * 
   * @return Players score.
   */
  public int getScore()
  {
    return my_score;
  }
}
