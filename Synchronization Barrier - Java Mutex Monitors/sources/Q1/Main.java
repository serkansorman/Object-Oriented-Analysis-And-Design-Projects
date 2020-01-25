package Q1;

public class Main {
    public static void main(String... args){

        BestDSEver bestDSEver = new BestDSEver();
        ThreadSafeBestDSEver threadSafeBestDSEver = new ThreadSafeBestDSEver();

        IBestDSEver threadSafeBesDSEverAdapter = new ThreadSafeBestDSEverAdapter(threadSafeBestDSEver);

        threadSafeBestDSEver.insertThreadSafe("");
        threadSafeBestDSEver.removeThreadSafe("");
        threadSafeBestDSEver.getThreadSafe(0);

        testBestDSEver(bestDSEver);
        testBestDSEver(threadSafeBesDSEverAdapter);

    }

    static void testBestDSEver(IBestDSEver bestDSEver){
        bestDSEver.insert("");
        bestDSEver.remove("");
        bestDSEver.get(0);
    }
}
