package Part1;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import static java.lang.Thread.sleep;

public class AlbumClient {

//    protected static final AtomicInteger SUCCESSFUL_REQ = new AtomicInteger(0);
//    protected static final AtomicInteger FAILED_REQ = new AtomicInteger(0);
    protected static final AtomicLong TIME_EACH_REQUEST = new AtomicLong(0);
    protected static CountDownLatch totalThreadsLatch;


    private static final int INITIAL_THREAD_COUNT = 10;
    private static final int INITIAL_CALLS_PER_THREAD = 100;

    public AlbumClient() {
    }

    public static void main(String[] args) throws InterruptedException {
        // Define starting constants
        int threadGroupSize = 10;
        int numThreadGroups = 10;
        long delay = 2;
        String serverURL = "http://ec2-34-221-94-160.us-west-2.compute.amazonaws.com:8080/Server_Web";
//        String serverURL = "http://ec2-34-221-94-160.us-west-2.compute.amazonaws.com:8080/go";

        int callsPerThread = 100;
        int maxThreads = threadGroupSize * numThreadGroups;
        int totalCalls = maxThreads * callsPerThread * 2;

        // Initialize Executor service
        ExecutorService service = Executors.newFixedThreadPool(maxThreads);
        totalThreadsLatch = new CountDownLatch(threadGroupSize);

        // Run initializing threads
        long start = System.currentTimeMillis();
        for (int i = 0; i < INITIAL_THREAD_COUNT; i++) {
            service.execute(new AlbumThreadRunnable(INITIAL_CALLS_PER_THREAD, serverURL));
        }

        totalThreadsLatch.await();
        long end = System.currentTimeMillis();
        System.out.println("Wall time: " + (end - start) * .001 + " s");
        System.out.println("Throughput: " + (10 * 100) / ((end - start) * 0.001) + " reqs per second (total calls / wall time)");
        long avgTimeRequest = (TIME_EACH_REQUEST.get() / (10 * 100));
        System.out.println("Throughput: " + 10 / (avgTimeRequest * 0.001) + " threads per second (calculated)");
//        CountDownLatch tempLatch = new CountDownLatch(1);
//        printResults("Loading Initialization", INITIAL_THREAD_COUNT * INITIAL_CALLS_PER_THREAD * 2, INITIAL_THREAD_COUNT, end, start, tempLatch);
//        tempLatch.await();

        // Redefining variables for loading the server
        totalThreadsLatch = new CountDownLatch(maxThreads);
//        SUCCESSFUL_REQ.set(0);
//        FAILED_REQ.set(0);
        TIME_EACH_REQUEST.set(0);

        // Load Server
        start = System.currentTimeMillis();
        for (int i = 0; i < numThreadGroups; i++) {
            for (int j = 0; j < threadGroupSize; j++) {
                service.execute(new AlbumThreadRunnable(callsPerThread, serverURL));
            }

            // Sleep for delay amount of time, converted to seconds
            sleep(delay * 1000L);
        }

//      Shutdown the executor and wait for all tasks to complete
        totalThreadsLatch.await();
        service.shutdown();

        // Print results
        end = System.currentTimeMillis();
        System.out.println("Wall time: " + (end - start) * .001 + " s");
        System.out.println("Throughput: " + totalCalls / ((end - start) * 0.001) + " reqs per second (total calls / wall time)");
        avgTimeRequest = (TIME_EACH_REQUEST.get() / totalCalls);
        System.out.println("Throughput: " + maxThreads / (avgTimeRequest * 0.001) + " threads per second (calculated)");
//        System.out.println("Throughput: " + maxThreads / (avgTimeRequest * 0.001) + " threads per second");
//        long avgTimeRequest = ((end - start) / totalCalls);
//        printResults("Loading Server", totalCalls, maxThreads, end, start, new CountDownLatch(0));
    }

    /**
     * Simple method to print the results of the initialization phase and loading phase to the CL.
     *
     * @param totalCalls - The total requests made to the api.
     * @param maxThreads - The maximum amount of threads that could be running at once.
     * @param end        - The end time of the current phase.
     * @param start      - The start time of the current phase.
     */
    protected static void printResults(String currentPhase, int totalCalls, int maxThreads, long end, long start, CountDownLatch tempLatch) throws InterruptedException {
//        System.out.println("Successful req: " + SUCCESSFUL_REQ);
//        System.out.println("Failed req: " + FAILED_REQ);
//        long avgTimeRequest = (TIME_EACH_REQUEST.get() / totalCalls);

//        System.out.println("Avg time each request: " + avgTimeRequest + "ms");
//        System.out.println("Throughput: " + maxThreads / (avgTimeRequest * 0.001) + " threads per second");
        System.out.println("Wall time: " + (end - start) * .001 + " s");

//        new WriteToCsv(currentPhase, maxThreads, totalCalls, SUCCESSFUL_REQ.get(), FAILED_REQ.get(), avgTimeRequest, maxThreads / (avgTimeRequest * 0.001), (end - start) * .001).writeTestResults();
        tempLatch.countDown();
    }
}