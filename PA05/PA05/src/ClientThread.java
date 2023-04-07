import java.util.Random;

/**
 * Simple thread class that accesses the singleton Logger
 * object.
 */
class ClientThread extends Thread implements Runnable {
    /** Name of this thread. */
    private String threadName;

    /**
     * Build a new ClientThread object.
     * 
     * @param name String name of the thread.
     */
    public ClientThread(String name) {
        threadName = name;
        Logger log = Logger.getInstance();
        log.writeMessage("Created " + threadName);
    }

    /**
     * Execute the thread's work: write 10 messages to the log,
     * pausing for 1 to 5 seconds between each message.
     */
    public void run() {
        Logger log = Logger.getInstance();
        log.writeMessage(threadName + " running");

        Random prng = new Random();

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000 + prng.nextInt(4000));
                log.writeMessage(threadName + " message " + (i + 1));
            } catch (InterruptedException ie) {
                log.writeMessage(threadName + " interrupted");
            }
        }
        log.writeMessage(threadName + " exiting");
    }
}