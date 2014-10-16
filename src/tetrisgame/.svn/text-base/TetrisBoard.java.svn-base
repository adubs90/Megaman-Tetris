/*
 * TCSS 305 - Autumn 2011 
 * Project Tetris 
 * Author: Alex Stringham 
 * UWNetID: ats3216
 */

package tetrisgame;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Creates a Board to play Tetris.
 * 
 * @author Alex Stringham
 * @version Nov. 15, 2011
 */
public class TetrisBoard extends Observable
{
  
  
  /**
   * Constant for the max amount of rotations a piece can have.
   */
  public static final int MAX_ROTATIONS = 4;
  /**
   * Constant for empty squares.
   */
  public static final int BLANK_SQUARE = -1;
  
  /**
   * 
   */
  private static List<BoardListener> my_listeners = new ArrayList<BoardListener>();

  /**
   * Holds the coordinates for pieces.
   */
  private int[][] my_board_matrix;
  /**
   * Number of columns on the board.
   */
  private int my_columns;
  /**
   * Number of rows on the board.
   */
  private int my_rows;
  /**
   * 
   */
 

  /**
   * Create a TetrisBoard with the desired number of columns and rows.
   * 
   * @param the_columns The number of columns.
   * @param the_rows The number of rows.
   */
  public TetrisBoard(final int the_columns, final int the_rows)
  {
    super();

    my_columns = the_columns;
    my_rows = the_rows;
    boardReset();
  }

  /**
   * Resets the board so that all of its squares are blank.
   */
  public void boardReset()
  {
    my_board_matrix = new int[my_columns][my_rows];

    for (int i = 0; i < my_columns; i++)
    {
      for (int j = 0; j < my_rows; j++)
      {
        my_board_matrix[i][j] = BLANK_SQUARE;
      }
    }
  }

  /**
   * Adds a given piece to the current board.
   * 
   * @param the_piece The piece to add.
   * @param the_check checks for piece.
   */
  public void add(final TetrisPieces the_piece, final boolean the_check)
  {
    if (the_piece != null)
    {
      final Point center = the_piece.getCenter();
      final Point[] pieces = the_piece.getRelativeCenter();

      for (int i = 0; i < MAX_ROTATIONS; i++)
      {
        final int x = center.x + pieces[i].x;
        final int y = center.y + pieces[i].y;

        my_board_matrix[x][y] = the_piece.getPiece();
      }
      if (the_check)
      {
        generateBoardEvent();
      }
    }
  }

  /**
   * Removes the piece from the board.
   * 
   * @param the_piece The piece to remove.
   */
  public void deletePiece(final TetrisPieces the_piece)
  {
    if (the_piece != null)
    {
      final Point center = the_piece.getCenter();
      final Point[] pieces = the_piece.getRelativeCenter();

      for (int i = 0; i < MAX_ROTATIONS; i++)
      {
        final int x = center.x + pieces[i].x;
        final int y = center.y + pieces[i].y;

        my_board_matrix[x][y] = BLANK_SQUARE;
      }
    }
  }

  /**
   * Deletes the row at a given index and moves all rows above that index down
   * one.
   * 
   * A BoardEvent will be fired after the rows are moved.
   * 
   * @param the_row The index of the row to remove.
   */
  public void clearRow(final int the_row)
  {

    int new_row;
    int new_column;

    for (new_row = the_row; new_row > 0; new_row--)
    {
      for (new_column = 0; new_column < my_columns; new_column++)
      {
        my_board_matrix[new_column][new_row] = my_board_matrix[new_column][new_row - 1];
      }
    }

    for (new_column = 0; new_column < my_columns; new_column++)
    {
      my_board_matrix[new_column][0] = BLANK_SQUARE;
    }
  }

  /**
   * Checks to see if the piece will fit on the board.
   * 
   * @param the_piece The piece that will be tested.
   * @return true if the piece fits, false otherwise.
   */
  public boolean fitsOnBoard(final TetrisPieces the_piece)
  {
    boolean check = true;

    if (the_piece != null)
    {
      final Point center = the_piece.getCenter();
      final Point[] pieces = the_piece.getRelativeCenter();

      for (int i = 0; i < MAX_ROTATIONS; i++)
      {
        final int x_coord = center.x + pieces[i].x;
        final int y_coord = center.y + pieces[i].y;

        if (x_coord < 0 || x_coord >= my_columns || y_coord < 0 || y_coord >= my_rows)
        {
          check = false;
        }

        if (check && my_board_matrix[x_coord][y_coord] != BLANK_SQUARE)
        {
          check = false;
        }
      }
    }

    return check;
  }

  /**
   * Gets a random piece to be placed on the board.
   * 
   * @param the_board The tetris board for the piece.
   * @return A random piece.
   */
  public TetrisPieces getRandomPiece(final TetrisBoard the_board)
  {
    return new TetrisPieces((int) (Math.random() * (MAX_ROTATIONS * 2 - 1)), the_board);
  }

  /**
   * Gets how many columns are designated on the board.
   * 
   * @return The number of columns in the board.
   */
  public int getColumns()
  {
    return my_columns;
  }

  /**
   * Sets how many columns are on the board, and resets the board afterwards.
   * 
   * @param the_columns The number of desired columns.
   */
  public void setColumns(final int the_columns)
  {
    my_columns = the_columns;
    boardReset();
  }

  /**
   * Gets how many rows are designated on the board.
   * 
   * @return The number of rows in the board.
   */
  public int getRows()
  {
    return my_rows;
  }

  /**
   * Sets how many rows are on the board, and resets the board afterwards.
   * 
   * @param the_rows The number of desired rows.
   */
  public void setRows(final int the_rows)
  {
    my_rows = the_rows;
    boardReset();
  }

  /**
   * Gets the value of the piece at the current coordinates.
   * 
   * @param the_x The x coordinate of the piece.
   * @param the_y The y coordinate of the piece.
   * @return The value at the given coordinates.
   */
  public int getPiece(final int the_x, final int the_y)
  {
    return my_board_matrix[the_x][the_y];
  }

  /**
   * Sets pieces at given coordinates to the current value.
   * 
   * @param the_x The x coordinate of the piece.
   * @param the_y The y coordinate of the piece.
   * @param the_value The value to be set.
   */
  public void setPiece(final int the_x, final int the_y, final int the_value)
  {
    my_board_matrix[the_x][the_y] = the_value;
  }


  /**
   * Adds a BoardListener to this board.
   * 
   * @param the_listener The listener to add.
   */
  public void addBoardListener(final BoardListener the_listener)
  {
    
    my_listeners.add(the_listener);
  }

  /**
   * Remove a BoardListener from this board.
   * 
   * @param the_listener The listener to remove.
   */
  public void removeBoardListener(final BoardListener the_listener)
  {
    my_listeners.remove(the_listener);
  }

  /**
   * 
   */
  private void generateBoardEvent()
  {
    // Generate the event.
    final BoardEvent event = new BoardEvent(this);

    // Now tell everyone.
    for (int count = 0; count < my_listeners.size(); count++)
    {
      ((BoardListener) my_listeners.get(count)).boardChange(event);
    }
  }

}
