public class SynchronizedProcess {
    // state variable identifying whose turn it is.
    private String whoseTurn = null;

    public synchronized boolean execute(String opponent) {
        String x = Thread.currentThread().getName();
        if (whoseTurn == null) {
            whoseTurn = x;
            return true;
        }

        //catch InterruptedException, which would be thrown if the thread in the wait() call stops prematurely
        if (whoseTurn.compareTo("DONE") == 0)
            return false;
        if (opponent.compareTo("DONE") == 0) {
            whoseTurn = opponent;
            notifyAll();
            return false;
        }

        if (x.compareTo(whoseTurn) == 0) {
            System.out.println(x + " time to execute");
            whoseTurn = opponent;
            notifyAll();
        } else {
            try {
                long t1 = System.currentTimeMillis();
                wait(2500);
                if ((System.currentTimeMillis() - t1) > 2500) {
                    System.out.println("TIMEOUT! " + x + " is waiting for " + whoseTurn + " to work.");
                }
            } catch (InterruptedException ignored) {
            }
        }
        return true;
    }
}
