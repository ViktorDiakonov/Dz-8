package treeMap;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.Map;

public class TreeMap<K, V> implements Map<K, V> {
    public Entry<K, V> root;
    public int mapSize;
    private Object key;
    public Set<K> keySet = null;

    @Override
    public void clear() {

    }

    @Override
    public boolean isEmpty() {
        return mapSize != 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return null != getEntry((K) key);
    }

    private Entry<K, V> getEntry(K key) {
        Entry<K, V> entry = root;
        var result = 0;
        if (entry != null) {
            do {
                result = ((Comparable<K>) entry.getKey()).compareTo(key);
                if (result == 0) {
                    return entry;
                } else if (result > 0) {
                    entry = entry.getLeft();
                } else {
                    entry = entry.getRight();
                }
            } while (entry != null);
        }

        return null;

    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public Set<java.util.Map.Entry<K, V>> entrySet() {
        return null;
    }

    @Override
    public V get(Object key) {
        Entry<K, V> entry = getEntry((K) key);
        if (entry != null) {
            return entry.getValue();
        }
        return null;
    }

    @Override
    public Set<K> keySet() {
        if (keySet == null) {
            keySet = new Set<K>() {

                @Override
                public void clear() {
                }

                @Override
                public boolean contains(Object o) {

                    return TreeMap.this.containsKey(o);
                }

                @Override
                public boolean containsAll(Collection<?> c) {
                    for (Object o : c) {
                        K key = (K) o;
                        if (TreeMap.this.containsKey(key)) {
                            return true;
                        }
                    }
                    return false;
                }

                @Override
                public boolean addAll(Collection<? extends K> c) {
                    return false;
                }

                @Override
                public boolean isEmpty() {
                    return TreeMap.this.size() != 0;
                }

                @Override
                public Iterator<K> iterator() {
                    return null;
                }


                @Override
                public boolean retainAll(Collection<?> c) {
                    return false;
                }

                @Override
                public boolean removeAll(Collection<?> c) {
                    return false;
                }

                @Override
                public int size() {
                    return TreeMap.this.size();
                }

                @Override
                public Object[] toArray() {
                    return null;
                }

                @Override
                public <T> T[] toArray(T[] a) {
                    return null;
                }

                @Override
                public boolean add(K k) {
                    return false;
                }

                @Override
                public boolean remove(Object o) {
                    return false;
                }
            };
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        Entry<K, V> entry = root, parent = null, newNode;
        int result = 0;
        if (entry != null) {
            do {
                parent = entry;
                result = ((Comparable<K>) entry.getKey()).compareTo(key);
                if (0 == result) {
                    entry.setValue(value);
                    return value;
                } else if (result > 0) {
                    entry = entry.getLeft();
                } else {
                    entry = entry.getRight();
                }
            } while (entry != null);
            newNode = new Entry<K, V>(key, value, parent);
        } else {
            newNode = new Entry<K, V>(key, value, parent);
        }

        if (parent == null) {
            root = newNode;
        } else if (result > 0) {
            parent.setLeft(newNode);
        } else {
            parent.setRight(newNode);
        }
        mapSize++;
        return value;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
    }

    @Override
    public V remove(Object key) {
        this.key = key;
        Entry<K, V> entry = getEntry((K) key);
        return remove(entry);
    }

    @Override
    public int size() {
        return mapSize;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    private static class Entry<K, V> implements Map.Entry<K, V> {
        private final K key;
        private V value;
        private Entry<K, V> left;
        private Entry<K, V> right;

        public Entry(K key, V value, Entry<K, V> parent) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        @Override
        public V setValue(V value) {

            return this.value = value;
        }

        public Entry<K, V> getLeft() {
            return left;
        }

        public void setLeft(Entry<K, V> left) {
            this.left = left;
        }

        public Entry<K, V> getRight() {
            return right;
        }

        public void setRight(Entry<K, V> right) {
            this.right = right;
        }

    }
}