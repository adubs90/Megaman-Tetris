/*
 * TCSS 305 - Autumn 2011 
 * Project Tetris 
 * Author: Alex Stringham 
 * UWNetID: ats3216
 */

package tetrisgame;

/**
 * Interface for board listener objects.
 * 
 * @author Alex Stringham
 * @version Nov. 2011
 */
public interface BoardListener
{
  /**
   * Methd thats called every time the board changes.
   * 
   * @param the_event a BoardEvent.
   */
  void boardChange(final BoardEvent the_event);
}
