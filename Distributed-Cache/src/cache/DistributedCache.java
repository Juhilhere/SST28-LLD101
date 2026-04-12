package cache;

import distribution.DistributionStrategy;
import storage.DataStore;

import java.util.List;

public class DistributedCache<K, V> {

    private List<CacheNode<K, V>> nodes;
    private DistributionStrategy<K> strategy;
    private DataStore<K, V> database;

    public DistributedCache(List<CacheNode<K, V>> nodes,
                            DistributionStrategy<K> strategy,
                            DataStore<K, V> database) {
        this.nodes = nodes;
        this.strategy = strategy;
        this.database = database;
    }

    public V get(K key) {

        int index = strategy.getNodeIndex(key, nodes.size());
        CacheNode<K, V> node = nodes.get(index);

        V value = node.get(key);

        if (value != null) {
            return value;
        }

        value = database.get(key);

        if (value != null) {
            node.put(key, value);
        }

        return value;
    }

    public void put(K key, V value) {

        int index = strategy.getNodeIndex(key, nodes.size());
        CacheNode<K, V> node = nodes.get(index);

        node.put(key, value);

        database.put(key, value);
    }
}
