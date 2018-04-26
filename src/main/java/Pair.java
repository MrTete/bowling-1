public class Pair<K, V> {

    private final K item1;
    private final V item2;

    public static <K, V> Pair<K, V> createPair(K item1, V item2) {
        return new Pair<K, V>(item1, item2);
    }

    public Pair(K item1, V item2) {
        this.item1 = item1;
        this.item2 = item2;
    }

    public K getItem1() {
        return item1;
    }

    public V getItem2() {
        return item2;
    }
}
