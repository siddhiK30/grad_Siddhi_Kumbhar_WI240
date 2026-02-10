import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class BikeRide {

    static final int PLAYERS = 10;

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        List<Rider> riders = new ArrayList<>();

        System.out.println("Enter the name of 10 Riders:");
        for (int i = 0; i < PLAYERS; i++) {
            riders.add(new Rider(sc.next()));
        }

        System.out.print("Enter distance in KM: ");
        int km = sc.nextInt();
        int totalMeters = km * 1000;

        CyclicBarrier startLine = new CyclicBarrier(PLAYERS, () ->
                System.out.println("\n🚦 All riders ready... GO!\n")
        );

        ExecutorService service = Executors.newFixedThreadPool(PLAYERS);
        List<Future<Rider>> results = new ArrayList<>();

        for (Rider r : riders) {
            r.setDistance(totalMeters);
            r.setBarrier(startLine);
            results.add(service.submit(r));
        }

        service.shutdown();
        service.awaitTermination(1, TimeUnit.HOURS);

        List<Rider> finished = new ArrayList<>();
        for (Future<Rider> f : results) {
            finished.add(f.get());
        }

        finished.sort(Comparator.comparingInt(r -> r.timeTaken));

        System.out.println("\nFINAL DASHBOARD");
        System.out.println("Rank | Name       | Start Time | End Time | Time Taken (ticks)");
        System.out.println("--------------------------------------");

        int rank = 1;
        for (Rider r : finished) {
            System.out.printf(
                    "%-4d | %-10s | %-5d | %-5d | %-5d\n ",
                    rank++, r.name, r.startTick , r.endTick , r.timeTaken
            );
        }
    }
}
class Rider implements Callable<Rider> {

    String name;
    int totalMeters;
    CyclicBarrier barrier;
    Random random = new Random();

    int startTick;
    int endTick;
    int timeTaken;  

    Rider(String name) {
        this.name = name;
    }

    void setDistance(int meters) {
        this.totalMeters = meters;
    }

    void setBarrier(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public Rider call() throws Exception {

        System.out.println(name + " reached starting line");
        barrier.await(); 

        startTick = Shared.clock.get();

        for (int covered = 100; covered <= totalMeters; covered += 100) {
            Thread.sleep(random.nextInt(200) + 100); 
            System.out.println(name + " covered " + covered + " mtrs");

            
            Shared.clock.incrementAndGet();
        }

      
        endTick = Shared.clock.get();
        timeTaken = endTick - startTick;

        int position = Shared.finishOrder.incrementAndGet();
        System.out.println(name + " finished at position " + position);

        return this;
    }
}
class Shared {
    static AtomicInteger clock = new AtomicInteger(0);        // logical time
    static AtomicInteger finishOrder = new AtomicInteger(0);  // ranking
}
