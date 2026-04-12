import cache.CacheNode;
import cache.DistributedCache;
import distribution.ModuloDistributionStrategy;
import eviction.LRUEvictionPolicy;
import storage.InMemoryDatabase;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        int nodesCount = 3;
        int capacityPerNode = 2;

        List<CacheNode<String, String>> nodes = new ArrayList<>();

        for (int i = 0; i < nodesCount; i++) {
            nodes.add(new CacheNode<>(capacityPerNode, new LRUEvictionPolicy<>()));
        }

        DistributedCache<String, String> cache =
                new DistributedCache<>(
                        nodes,
                        new ModuloDistributionStrategy<>(),
                        new InMemoryDatabase<>()
                );

        cache.put("A", "Apple");
        cache.put("B", "Ball");
        cache.put("C", "Cat");

        System.out.println(cache.get("A"));
        System.out.println(cache.get("B"));
        System.out.println(cache.get("C"));

        System.out.println(cache.get("D"));
    }
}
