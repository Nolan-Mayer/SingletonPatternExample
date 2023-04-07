import java.awt.event.*;
import java.sql.Timestamp;
import java.util.*;
import javax.swing.*;

//** import data stucture type
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Singleton Logger class. Accepts string messages from
 * clients, and prints them to the screen. 
 */
public class Logger implements ActionListener {
    /** Timer for the logger. Do not modify this
     *  field or its related code. 
     */
    private javax.swing.Timer timer;

    /** TODO - add field to make this a singleton. */
    private volatile static Logger instance;

    /** TODO - add reference to your thread-safe linear
     *  data structure. Call it "buffer"
     */
    private BlockingQueue<String> buffer;
    
    // TODO - how does this need to be changed?
  // changed the constructor to private
    private Logger() {
            buffer = new LinkedBlockingQueue<String>();
            // TODO - instantiate your buffer data structure here
            timer = new javax.swing.Timer(1000, this);
            timer.start();
    }

    /**
     * TODO - add your getInstance() method 
     */
    public static Logger getInstance(){
      if(instance == null) {
        synchronized (Logger.class){
          if(instance == null){
            instance = new Logger();
          }
        }
      }
      return instance;
    }
    /**
     * Method called by clients to log a message.
     * @param message
     */
    public void writeMessage(String message) {
        // Get a timestamp 
        Timestamp t = new Timestamp(System.currentTimeMillis());
        // TODO - this code assumes your data structure supports
        // the add method, to stick the timestamped message to 
        // the end of the structure
        buffer.add(t + ":" + message); // changed .add to .offer to support buffer
    }

    /**
     * Print contents of the buffer data structure to the 
     * console. 
     */
    private void dumpBuffer() {
        // TODO - this assumes your buffer supports isEmpty()
        // and remove() methods
        while (!buffer.isEmpty()) {
            System.out.println(buffer.remove());
        }
    }

    /**
     * Method called periodically by the timer, to cause the 
     * data structure contents to be printed. 
     */
    public void actionPerformed(ActionEvent ae) {
        dumpBuffer();
    }

    /** 
     * Method called when we want to stop logging. It empties
     * the buffer structure and stops the timer. 
     */
    public void shutDown() {
        dumpBuffer();
        timer.stop();
    }
}