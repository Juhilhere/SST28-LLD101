package storage;

import java.util.HashMap;
import java.util.Map;

public class InMemoryDatabase<K, V> implements DataStore<K, V> {

    private Map<K, V> db = new HashMap<>();

    @Override
    public V get(K key) {
        return db.get(key);
    }

    @Override
    public void put(K key, V value) {
        db.put(key, value);
    }
}
