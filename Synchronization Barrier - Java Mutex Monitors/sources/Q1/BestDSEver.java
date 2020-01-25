package Q1;

/**
 * Unthread safe ds class
 */
public class BestDSEver implements IBestDSEver {

    /**
     * Unthreadsafe insert method
     * @param o
     */
    @Override
    public void insert(Object o) {
        System.out.println("BestDSEver insert");
    }

    /**
     * Unthreadsafe remove method
     * @param o
     */
    @Override
    public void remove(Object o) {
        System.out.println("BestDSEver remove");
    }

    /**
     * Unthreadsafe get method
     * @param index
     * @return
     */
    @Override
    public Object get(int index) {
        System.out.println("BestDSEver get");
        return null;
    }
}
