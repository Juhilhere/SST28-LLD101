package cache;

import eviction.EvictionPolicy;

import java.util.HashMap;
import java.util.Map;

public class CacheNode<K, V> {

    private int capacity;
    private Map<K, V> map;
    private EvictionPolicy<K> evictionPolicy;

    public CacheNode(int capacity, EvictionPolicy<K> evictionPolicy) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.evictionPolicy = evictionPolicy;
    }

    public V get(K key) {

        synchronized (map) {
            if (!map.containsKey(key)) {
                return null;
            }

            evictionPolicy.keyAccessed(key);
            return map.get(key);
        }
    }

    public void put(K key, V value) {

        synchronized (map) {

            if (map.containsKey(key)) {
                map.put(key, value);
                evictionPolicy.keyAccessed(key);
                return;
            }

            if (map.size() >= capacity) {
                K evict = evictionPolicy.evictKey();
                if (evict != null) {
                    map.remove(evict);
                }
            }

            map.put(key, value);
            evictionPolicy.keyAccessed(key);
        }
    }
}
