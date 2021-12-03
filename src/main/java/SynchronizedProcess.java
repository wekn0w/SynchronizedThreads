public class SynchronizedProcess {
    // state variable identifying whose turn it is.
    private String whoseTurn = null;

    public synchronized boolean execute(String opponent) {
        String x = Thread.currentThread().getName();

        //solve the problem of whose turn it is before anyone has gone
        //the first thread to invoke this method will get the honor of going first
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

        //the thread updates the state variable with the next thread's turn
        //before the notify, as the notify may cause another thread to start running immediately before its turn
        if (x.compareTo(whoseTurn) == 0) {
            System.out.println(x + " time to execute");
            whoseTurn = opponent;
            notifyAll();
        } else {
            //it isn't the current thread's turn to go, so - wait
            try {
                long t1 = System.currentTimeMillis();
                wait(2500);
                //when execution continues after the wait call returns, the reason for continuing could be either
                //the wait timed out or our thread was awakened with a call to notify()
                if ((System.currentTimeMillis() - t1) > 2500) {
                    System.out.println("TIMEOUT! " + x + " is waiting for " + whoseTurn + " to work.");
                }
            } catch (InterruptedException ignored) {
            }
        }
        return true;
    }
}
