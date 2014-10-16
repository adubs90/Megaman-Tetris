/*
 * TCSS 305 - Autumn 2011 
 * Project Tetris 
 * Author: Alex Stringham 
 * UWNetID: ats3216
 */

package tetrisgame;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * JUnit Test class to test my board class.
 * 
 * @author Alex
 * @version Nov. 16 2011
 */
public class TetrisBoardTest
{
  /**
   * Constant for my board width.
   */
  private static final int MY_TEST_WIDTH = 20;
  /**
   * Constant for my board height.
   */
  private static final int MY_TEST_HEIGHT = 20;

  /**
   * Tests the constructor to see if the board can be built.
   */
  @Test
  public void testTetrisBoard()
  {
    int expected_x;
    int expected_y;
    expected_x = 20;
    expected_y = 20;
    final TetrisBoard board = new TetrisBoard(MY_TEST_HEIGHT, MY_TEST_WIDTH);
    assertEquals(expected_x, board.getRows(), 0);
    assertEquals(expected_y, board.getColumns(), 0);
    
    expected_x = 294;
    expected_y = 17;
    final TetrisBoard board_two = new TetrisBoard(17, 294);
    assertEquals(expected_x, board_two.getRows(), 0);
    assertEquals(expected_y, board_two.getColumns(), 0);
  
  }

  /**
   * This class tests 30 pieces on a board, if lines can be deleted, 
   * and if multiple lines are full, they will all be deleted. All non
   * deleted lines will be moved down one square. 
   * 
   * Tetris board should have the following pieces on it.
   * 
   *  T  T  T L
   * TTTTTTTTTL
   *  T  T  T L
   * TTTTTTTTTL
   *  T  T  T L
   * TTTTTTTTTL
   *  T  T  T L
   * TTTTTTTTTL
   *  T  T  T L
   * TTTTTTTTTL
   *  T  T  T L
   * TTTTTTTTTL
   *  T  T  T L
   * TTTTTTTTTL
   *  T  T  T L
   * TTTTTTTTTL
   *  T  T  T L
   * TTTTTTTTTL
   *  T  T  T L
   * TTTTTTTTTL
   */
  @Test
  public void testThirtyPieces()
  {
//    int x_one = 0;
//    int y_one = 0;
//    TetrisBoard board = new TetrisBoard(MY_TEST_HEIGHT, 10);
//    TetrisPieces piece = new TetrisPieces(y_one, board);
//    board.add(the_piece)
    fail();
  }

}
