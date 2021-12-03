public class Main {

    public static void main(String[] args) {
        SynchronizedProcess jointLogic = new SynchronizedProcess();
        Thread firstThread = new Thread(new SynchRunner("thr2", jointLogic));
        Thread secondThread = new Thread(new SynchRunner("thr1", jointLogic));

        firstThread.setName("thr1");
        secondThread.setName("thr2");
        firstThread.start();
        secondThread.start();

        // Wait 5 seconds to allow threads to execute before finish main
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //cause the threads to quit run() .
        jointLogic.execute("DONE");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}