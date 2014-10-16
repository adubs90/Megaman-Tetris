/*
 * TCSS 305 - Autumn 2011 
 * Project Tetris 
 * Author: Alex Stringham 
 * UWNetID: ats3216
 */

package tetrisgame;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Point;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Game logic for Tetris.
 * 
 * @author Alex Stringham
 * @version Nov. 2011
 */
public class TetrisGame
{

  /**
   * Height of the board.
   */
  private static final int WIDTH = 10;
  /**
   * Width of the board.
   */
  private static final int HEIGHT = 24;
  /**
   * The amount decremented for each level.
   */
  private static final int DECREMENT = 50;
  /**
   * Start time for the pieces falling.
   */
  private static final int START_TIME = 500;

  /**
   * Checks to see if the game is playing.
   */
  private boolean my_check_playing;
  /**
   * Checks to see if the game is paused.
   */
  private boolean my_check_paused;

  /**
   * Counts the total lines erased.
   */
  private int my_total_lines;
  /**
   * Keeps track of how long it takes a piece to fall.
   */
  private int my_delay_time;
  /**
   * Counts a players score.
   */
  private int my_score;
  /**
   * Thread that the game runs on.
   */
  private Thread my_game_thread;
  /**
   * Object for the event handler.
   */
  private final EventHandler my_event_handler;
  /**
   * Object to create a board.
   */
  private final TetrisBoard my_board;
  /**
   * Object to get the current piece.
   */
  private TetrisPieces my_current_piece;
  /**
   * Object to get the next piece.
   */
  private TetrisPieces my_next_piece;

  /**
   * Create a TetrisGame.
   */
  public TetrisGame()
  {
    my_event_handler = new EventHandler();
    my_board = new TetrisBoard(WIDTH , HEIGHT);
    my_check_playing = false;
  }

  /**
   * Start a Tetris game if one is not already playing.
   */
  public void startGame()
  {
    if (my_check_playing == false)
    {
      my_board.boardReset();
      my_total_lines = 0;
      my_score = 0;
      my_delay_time = START_TIME;
      my_check_playing = true;
      my_check_paused = false;
      my_current_piece = null;
      my_event_handler.generateScoreEvent();
      my_event_handler.generateGameEvent(new GameEvent(GameEvent.START));

      my_game_thread = new GameThread();
      my_game_thread.start();
      
    }
  }

  /**
   * Stop the current game.
   */
  public void stopGame()
  {
    my_check_playing = false;
    my_event_handler.generateGameEvent(new GameEvent(GameEvent.END));
  }

  /**
   * Gets the next piece.
   * 
   * @return the next piece.
   */
  public TetrisPieces getNextPiece()
  {
    return my_next_piece;
  }
  
  /**
   * Sets the next piece.
   * 
   * @param the_next_piece next tetris piece.
   */
  public void setNextPiece(final TetrisPieces the_next_piece)
  {
    my_next_piece = the_next_piece;
  }
  /**
   * Returns a copy of the current piece.
   * 
   * @return A copy of the current piece.
   */
  public TetrisPieces getCurrentPiece()
  {
    return my_current_piece;
  }

  /**
   * Sets the current piece.
   * 
   * @param the_current_piece Current piece in use.
   */
  public void setCurrentPiece(final TetrisPieces the_current_piece)
  {
    my_current_piece = the_current_piece;
  }

  /**
   * Checks if the game is paused.
   * 
   * @return game is pased if true, playing if false.
   */
  public boolean isPaused()
  {
    return my_check_paused;
  }

  /**
   * Pauses the game.
   * 
   * @param the_pause pause button.
   */
  public void setPaused(final boolean the_pause)
  {
    if (my_check_playing)
    {
      my_check_paused = the_pause;
    }
  }

  /**
   * Checks whether the game is playing or not.
   * 
   * @return game is playing if true, not playing if false.
   */
  public boolean isPlaying()
  {
    return my_check_playing;
  }

  /**
   * Sets whether the game is being played or not.
   * 
   * @param the_play_check starts the game if true, stops the 
   * game if false
   */
  public void setPlaying(final boolean the_play_check)
  {
    if (the_play_check)
    {
      my_check_playing = false;
    }
    else
    {
      startGame();
    }
  }

  /**
   * If possible, it makes the current piece move.
   * 
   * @param the_direction moves the direction the user indicates.
   * @return true only if the piece is capable of moving.
   */
  public boolean move(final int the_direction)
  {
    boolean result = false;

    if (my_current_piece != null && my_check_playing == true && my_check_paused == false)
    {
      if (the_direction == TetrisPieces.DROP || the_direction == TetrisPieces.HARD_DROP)
      {
      
        if (my_current_piece.move(the_direction) == false)
        {
          my_current_piece = null;
        }
        else
        {
          result = true;
        }
      }
      else
      {
        result = my_current_piece.move(the_direction);
      }
    }

    return result;
  }

  /**
   * Returns the current score.
   * 
   * @return The current score.
   */
  public int getScore()
  {
    return my_score;
  }

  /**
   * Sets the current score.
   * 
   * @param the_score The current score.
   */
  public void setScore(final int the_score)
  {
    my_score = the_score;
  }

  /**
   * Returns the number of completed lines so far in the game.
   * 
   * @return Total number of completed lines.
   */
  public int getTotalLines()
  {
    return my_total_lines;
  }

  /**
   * Sets the number of lines deleted.
   * 
   * @param the_total_lines The number of total lines.
   */
  public void setTotalLines(final int the_total_lines)
  {
    my_total_lines = the_total_lines;
  }

  /**
   * Adds the board listener.
   * 
   * @param the_listener BoardListener
   */
  public void addBoardListener(final BoardListener the_listener)
  {
    my_event_handler.addBoardListener(the_listener);
  }

  /**
   * Removes the board listener.
   * 
   * @param the_listener BoardListener
   */
  public void removeBoardListener(final BoardListener the_listener)
  {
    my_event_handler.removeBoardListener(the_listener);
  }

  /**
   * Adds the score listener. 
   * 
   * @param the_listener ScoreListener
   */
  public void addScoreListener(final ScoreListener the_listener)
  {
    my_event_handler.addScoreListener(the_listener);
  }

  /**
   * Removes the score listener.
   * 
   * @param the_listener ScoreListener
   */
  public void removeScoreListener(final ScoreListener the_listener)
  {
    my_event_handler.removeScoreListener(the_listener);
  }

  /**
   * @author Alex
   * @version Nov. 2011
   */
  private class GameThread extends Thread
  {
    /**
     * 
     */
    private AudioClip my_song;
    /**
     * 
     */
    private AudioClip my_death;

    
    /**
     * Method to run the game.
     */
    @SuppressWarnings("deprecation")
    public void run()
    {
  
      final java.io.File game_music = new java.io.File("src/tetrisgame/Wily1.wav");
      try
      {
        my_song = Applet.newAudioClip(game_music.toURL());
      }
      catch (final MalformedURLException e)
      {
        
        e.printStackTrace();
      }
      
      final java.io.File deathfile = new java.io.File("src/tetrisgame/death.wav");
      try
      {
        my_death = Applet.newAudioClip(deathfile.toURL());
      }
      catch (final MalformedURLException e)
      {
        
        e.printStackTrace();
      }
      my_song.loop();
      while (my_check_playing)
      {
        
        if (!my_check_paused)
        {
          if (my_current_piece == null)
          {
            int complete_lines = 0;

            for (int rows = my_board.getRows() - 1; rows >= 0; rows--)
            {
              boolean equal_squares = true;

              for (int columns = 0; columns < my_board.getColumns(); columns++)
              {
                if (my_board.getPiece(columns, rows) == TetrisBoard.BLANK_SQUARE)
                {
                  equal_squares = false;
                }
              }
              
              if (equal_squares)
              {

                my_board.clearRow(rows);
                rows++;
                my_death.play();
                complete_lines++;
                setTotalLines();
            
              }
            }

            if (complete_lines > 0)
            {

              my_score += complete_lines * (DECREMENT * 2);
              my_event_handler.generateScoreEvent();
            }
            
            setCurrent();
            
            if (my_board.fitsOnBoard(my_current_piece))
            {

              my_board.add(my_current_piece, true);
            }
            else
            {

              my_board.add(my_current_piece, false);
              stopGame();
              break;
            }
          }
          else
          {
            move(TetrisPieces.DROP);
          }
        }

        if (my_current_piece != null)
        {
          try
          {
            sleep(my_delay_time);
          }
          catch (final InterruptedException exception)
          {
            System.err.println("Exception e: " + exception);
          }
        }

      }
      my_song.stop();
    } 
   
    /**
     * Sets the total lines cleared and increases the difficulty of the game.
     */
    public void setTotalLines()
    {
     
      my_total_lines++;

      if (my_total_lines == HEIGHT)
      {
        my_delay_time = START_TIME - DECREMENT;
      }
      else if (my_total_lines == HEIGHT * 2)
      {
        my_delay_time = START_TIME - DECREMENT * 2;
      }
      else if (my_total_lines == HEIGHT * 2 + HEIGHT)
      {
        my_delay_time = START_TIME - (DECREMENT * 2 + DECREMENT);
      }
      else if (my_total_lines == (HEIGHT * 2) + (HEIGHT * 2))
      {
        my_delay_time = START_TIME / 2 + DECREMENT;
      }
      else if (my_total_lines == (HEIGHT * 2) + (HEIGHT * 2) + HEIGHT)
      {
        my_delay_time = START_TIME / 2;
      }
    }
    /**
     * Sets up the current and next piece.
     */
    public void setCurrent()
    {
      my_next_piece = TetrisPieces.getRandomPiece(my_board);
      my_current_piece = my_next_piece;
      my_current_piece.setCenter(new Point(my_board.getColumns() / 2, 1));
    }
  }

  /**
   * Class full of event listeners.
   * 
   * @author Alex
   * @version Nov. 2011
   */
  private class EventHandler
  {
    /**
     * List of game listeners.
     */
    private final List<GameListener> my_game_listeners = new ArrayList<GameListener>();

    /**
     * List of score listeners.
     */
    private final List<ScoreListener> my_score_listener = new ArrayList<ScoreListener>();

    /**
     * Adds the game listener.
     * 
     * @param the_listener game listener.
     */
    @SuppressWarnings("unused")
    public void addGameListener(final GameListener the_listener)
    {
      my_game_listeners.add(the_listener);
    }

    /**
     * Removes the game listener.
     * 
     * @param the_listener game listener.
     */
    @SuppressWarnings("unused")
    public void removeGameListener(final GameListener the_listener)
    {
      my_game_listeners.remove(the_listener);
    }

    /**
     * Generates the game event.
     * 
     * @param the_event game event.
     */
    public void generateGameEvent(final GameEvent the_event)
    {
      for (int count = 0; count < my_game_listeners.size(); count++)
      {
        ((GameListener) my_game_listeners.get(count)).gameChange(the_event);
      }
    }

    /**
     * Adds the board listener.
     * 
     * @param the_listener board listener.
     */
    public void addBoardListener(final BoardListener the_listener)
    {
      my_board.addBoardListener(the_listener);
    }

    /**
     * Removes the board listener.
     * 
     * @param the_listener board listener.
     */
    public void removeBoardListener(final BoardListener the_listener)
    {
      my_board.removeBoardListener(the_listener);
    }

    /**
     * Adds the SCore listener.
     * 
     * @param the_listener score listener.
     */
    public void addScoreListener(final ScoreListener the_listener)
    {
      my_score_listener.add(the_listener);
    }

    /**
     * Removes the score listener.
     * 
     * @param the_listener score listener.
     */
    public void removeScoreListener(final ScoreListener the_listener)
    {
      my_score_listener.remove(the_listener);
    }

    /**
     * Gets a score event.
     */
    public void generateScoreEvent()
    {
      for (int count = 0; count < my_score_listener.size(); count++)
      {
        final ScoreEvent event = new ScoreEvent(my_score);

        ((ScoreListener) my_score_listener.get(count)).scoreChange(event);
      }
    }
  }
}
