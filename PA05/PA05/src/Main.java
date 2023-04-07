/**
 * Main class for single logger test.
 */
public class Main {
    /**
     * Build 10 clients, let them access the logger.
     * 
     * @param args
     */
    public static void main(String[] args) {
        Logger log = Logger.getInstance();
        ClientThread[] threads = new ClientThread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new ClientThread("Thread " + i);
            threads[i].start();
        }
        for (int i = 0; i < 10; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException ie) {
                System.err.println("Thread " + i + " interrupted.");
            }
        }
        log.shutDown();
    }
}