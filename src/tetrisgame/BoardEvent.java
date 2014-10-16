/*
 * TCSS 305 - Autumn 2011 
 * Project Tetris 
 * Author: Alex Stringham 
 * UWNetID: ats3216
 */

package tetrisgame;

import java.awt.Event;

/**
 * Class for board events.
 * 
 * @author Alex Stringham
 * @version Nov. 2011
 */
@SuppressWarnings("serial")
public class BoardEvent extends Event
{
  /**
   * The source for an object.
   */
  private final Object my_source;

  /**
   * Constructor for board events.
   * 
   * @param the_source The source of the event.
   */
  public BoardEvent(final Object the_source)
  {
    super(null, 0, the_source);
    my_source = the_source;
  }

  /**
   * Gets an events source.
   * 
   * @return Source Event.
   */
  public Object getSource()
  {
    return my_source;
  }
}
