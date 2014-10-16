/*
 * TCSS 305 - Autumn 2011 
 * Project Tetris 
 * Author: Alex Stringham 
 * UWNetID: ats3216
 */



package tetrisgame;

import java.awt.Point;

/**
 * Class that creates pieces that are to be placed on a Tetris Board.
 * 
 * @author Alex Stringham
 * @version Nov. 15, 2011
 */
public class TetrisPieces
{
  /**
   * Max amount of rotations possible.
   */
  public static final int MAX_ROTATIONS = 4;
  /**
   * Constant for creating the L shaped piece.
   */
  public static final int L_PIECE = 0;
  /**
   * Constant for creating the J shaped piece.
   */
  public static final int J_PIECE = 1;
  /**
   * Constant for creating the I shaped piece.
   */
  public static final int I_PIECE = 2;
  /**
   * Constant for creating the Z shaped piece.
   */
  public static final int Z_PIECE = 3;
  /**
   * Constant for creating the S shaped piece.
   */
  public static final int S_PIECE = 4;
  /**
   * Constant for creating the O shaped piece.
   */
  public static final int O_PIECE = 5;
  /**
   * Constant for creating the T shaped piece.
   */
  public static final int T_PIECE = 6;
  /**
   * Constant for the number of pieces there are.
   */
  public static final int NUM_PIECES = 7;

  /**
   * Constant that tells a piece to go left.
   */
  public static final int LEFT = 10;
  /**
   * Constant that tells a piece to go right.
   */
  public static final int RIGHT = 11;
  /**
   * Constant that tells a piece to rotate.
   */
  public static final int FLIP = 12;
  /**
   * Constant that tells a piece to drop.
   */
  public static final int DROP = 13;
  /**
   * Constant that tells a piece to drop as far down as it possibly can.
   */
  public static final int HARD_DROP = 14;

  /**
   * A point that holds the center points of a piece.
   */
  private Point my_center_point = new Point();
  /**
   * An array of points that holds a pieces coordinates.
   */
  private Point[] my_coordinates = new Point[MAX_ROTATIONS];
  /**
   * A board object that creates an instance of the Tetris Board.
   */
  private final TetrisBoard my_board;

  /**
   * Tells which piece is being used by calling a certain pieces constant.
   */
  private int my_piece_number;
  /**
   * Tells which rotation state the piece is in.
   */
  private int my_rotation_number;
  /**
   * Tells the max amount of rotations a certain piece can do.
   */
  private int my_max_rotate;

  /**
   * Create a TetrisPiece object.
   * 
   * @param the_piece The type/shape of the piece.
   * @param the_board The board the piece is going to move around in.
   */
  public TetrisPieces(final int the_piece, final TetrisBoard the_board)
  {
    my_piece_number = the_piece;
    my_board = the_board;
    createPieces();
  }

  /**
   * 
   */
  private void createPieces()
  {
    switch (my_piece_number)
    {
      case I_PIECE:
        my_coordinates[0] = new Point(0, 0);
        my_coordinates[1] = new Point(0, -1);
        my_coordinates[2] = new Point(0, 1);
        my_coordinates[MAX_ROTATIONS - 1] = new Point(0, 2);
        my_max_rotate = 2;
        break;

      case L_PIECE:
        my_coordinates[0] = new Point(0, 0);
        my_coordinates[1] = new Point(-1, 0);
        my_coordinates[2] = new Point(-1, 1);
        my_coordinates[MAX_ROTATIONS - 1] = new Point(1, 0);
        my_max_rotate = MAX_ROTATIONS;
        break;

      case J_PIECE:
        my_coordinates[0] = new Point(0, 0);
        my_coordinates[1] = new Point(-1, 0);
        my_coordinates[2] = new Point(1, 0);
        my_coordinates[MAX_ROTATIONS - 1] = new Point(1, 1);
        my_max_rotate = MAX_ROTATIONS;
        break;

      case Z_PIECE:
        my_coordinates[0] = new Point(0, 0);
        my_coordinates[1] = new Point(-1, 0);
        my_coordinates[2] = new Point(0, 1);
        my_coordinates[MAX_ROTATIONS - 1] = new Point(1, 1);
        my_max_rotate = 2;
        break;

      case S_PIECE:
        my_coordinates[0] = new Point(0, 0);
        my_coordinates[1] = new Point(1, 0);
        my_coordinates[2] = new Point(0, 1);
        my_coordinates[MAX_ROTATIONS - 1] = new Point(-1, 1);
        my_max_rotate = 2;
        break;

      case O_PIECE:
        my_coordinates[0] = new Point(0, 0);
        my_coordinates[1] = new Point(0, 1);
        my_coordinates[2] = new Point(-1, 0);
        my_coordinates[MAX_ROTATIONS - 1] = new Point(-1, 1);
        my_max_rotate = 1;
        break;

      case T_PIECE:
        my_coordinates[0] = new Point(0, 0);
        my_coordinates[1] = new Point(-1, 0);
        my_coordinates[2] = new Point(1, 0);
        my_coordinates[MAX_ROTATIONS - 1] = new Point(0, 1);
        my_max_rotate = MAX_ROTATIONS;
        break;
      default:
        break;
    }
  }
  
  /**
   * Returns a random piece to use in the 
   * given board.
   * 
   * @param the_board  The board the piece will be in.
   * @return A random piece.
   */
  public static TetrisPieces getRandomPiece(final TetrisBoard the_board)
  {
    return new TetrisPieces((int) (Math.random() * NUM_PIECES), the_board);
  }

  /**
   * Move this piece in the given direction.
   * 
   * @param the_direction The direction of the move. This should be of the form:
   *          TetrisPiece.LEFT TetrisPiece.RIGHT TetrisPiece.ROTATE
   *          TetrisPiece.DOWN TetrisPiece.HARD_DROP
   * @return true if the move was completed.
   */
  public boolean move(final int the_direction)
  {
    boolean check = true;

    if (the_direction == HARD_DROP)
    {
      boolean loop = true;

      while (loop)
      {
        my_board.deletePiece(this);
        my_center_point.y++;

        if (my_board.fitsOnBoard(this))
        {
          my_board.add(this, true);
        }
        else
        {
          my_center_point.y--;
          my_board.add(this, true);
          loop = false;
          check = false;
        }
      }
    }
    else
    {
      my_board.deletePiece(this);

      switch (the_direction)
      {
        case LEFT:
          my_center_point.x--;
          break; // Move left
        case RIGHT:
          my_center_point.x++;
          break; // Move right
        case DROP:
          my_center_point.y++;
          break; // Drop
        case FLIP:
          rotateLeft();
          break;
        default:
          break;
      }

      if (my_board.fitsOnBoard(this))
      {
        my_board.add(this, true);
      }
      else
      // Undo the move
      {
        switch (the_direction)
        {
          case LEFT:
            my_center_point.x++;
            break; // Move right
          case RIGHT:
            my_center_point.x--;
            break; // Move left
          case DROP:
            my_center_point.y--;
            break;
          case FLIP:
            rotateRight();
            break;
          default:
            break;
        }

        my_board.add(this, true);
        check = false;
      }
    }

    return check;
  }

  /**
   * Checks and rotates left if it can.
   */
  private void checkLeftRotation()
  {
    if (my_max_rotate > 1)
    {
      my_rotation_number++;

      if (my_max_rotate == 2 && my_rotation_number == 2)
      {

        rotateLeft();
        rotateLeft();
        rotateLeft();
      }
      else
      {
        rotateLeft();
      }
    }

    my_rotation_number = my_rotation_number % my_max_rotate;
  }

  /**
   * Rotates the piece 90 degrees to the right.
   */
  private void rotateRight()
  {
    checkLeftRotation();
    checkLeftRotation();
    checkLeftRotation();
  }

  /**
   * Rotates the piece 90 degrees to the left.
   */
  private void rotateLeft()
  {
    for (int count = 0; count < MAX_ROTATIONS; count++)
    {
      final int old_x = my_coordinates[count].x;

      my_coordinates[count].x = -my_coordinates[count].y;
      my_coordinates[count].y = old_x;
    }
  }

  /**
   * Gets the center point for a piece.
   * 
   * @return The piece's center point.
   */
  public Point getCenter()
  {
    return my_center_point;
  }

  /**
   * Sets the center point for a piece.
   * 
   * @param the_point given points for the center of the piece.
   */
  public void setCenter(final Point the_point)
  {
    my_center_point = the_point;
  }

  /**
   * Gets an array that holds the points for the center of a piece.
   * 
   * @return An array with center points.
   */
  public Point[] getRelativeCenter()
  {
    return my_coordinates;
  }

  /**
   * Sets the relative center points for a piece.
   * 
   * @param the_square Relative center points for a piece.
   */
  public void setRelativeCenter(final Point[] the_square)
  {
    if (the_square != null)
    {
      my_coordinates = the_square;
    }
  }

  /**
   * Gets what kind of piece this one is.
   * 
   * @return A piece.
   */
  public int getPiece()
  {
    return my_piece_number;
  }

  /**
   * Sets what piece this is.
   * 
   * @param the_piece The type of this piece.
   */
  public void setPiece(final int the_piece)
  {
    my_piece_number = the_piece;
    createPieces();
  }
}
