public class SynchRunner implements Runnable {
    SynchronizedProcess jointProcess;
    String myOpponent;//second thread, should be changed for new threads

    public SynchRunner(String opponent, SynchronizedProcess table) {
        jointProcess = table;
        myOpponent = opponent;
    }

    public void run() {
        while (jointProcess.execute(myOpponent))
            ;
    }
}