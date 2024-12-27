package com.paresh.practice.data.structure;

import java.util.LinkedList;

public class Map<K,V> {

    //Initial Size of MAP buckets
    private static int SIZE = 10;

    //Array of Linked List For Buckets - using linked list as items can
    // be added without allocating space at initialization
    private LinkedList<Entry<K,V>>[] table;

    static class Entry<K,V>{
        K key ;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    public Map(){
        table = new LinkedList[SIZE];
        for(int i = 0; i < SIZE ; i++ ){
            table[i] = new LinkedList<>();
        }
    }

    public int getIndex(K key){
        return key.hashCode() % SIZE;
    }

    public void put (K key, V value){
        int index = getIndex(key);
        LinkedList<Entry<K, V>> bucket = table[index];

        // Check if the key already exists and update its value
        for (Entry<K, V> entry : bucket) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }

        bucket.add(new Entry<>(key , value));

    }

    public V get (K key){
        int index = getIndex(key);
        LinkedList<Entry<K,V>> bucket = table[index];

        for(Entry<K,V> entry : bucket){
            if(entry.key.equals(key)){
                return entry.value;
            }
        }
        return null;
    }

    // Method to check if the map contains a key
    public boolean containsKey(K key) {
        int index = getIndex(key);
        LinkedList<Entry<K, V>> bucket = table[index];

        for (Entry<K, V> entry : bucket) {
            if (entry.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    // Method to remove a key-value pair from the map
    public void remove(K key) {
        int index = getIndex(key);
        LinkedList<Entry<K, V>> bucket = table[index];

        bucket.removeIf(entry -> entry.key.equals(key));
    }

    // Method to check the size of the map
    public int size() {
        int size = 0;
        for (LinkedList<Entry<K, V>> bucket : table) {
            size += bucket.size();
        }
        return size;
    }

    // Method to check if the map is empty
    public boolean isEmpty() {
        return size() == 0;
    }

    public static void main(String[] args) {
        Map<String, Integer> map = new Map<>();

        // Add some key-value pairs
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        // Get and print values
        System.out.println("Value for key 'one': " + map.get("one"));
        System.out.println("Value for key 'two': " + map.get("two"));
        System.out.println("Value for key 'three': " + map.get("three"));

        // Check if key exists
        System.out.println("Contains key 'two': " + map.containsKey("two"));

        // Remove a key
        map.remove("two");
        System.out.println("Contains key 'two' after removal: " + map.containsKey("two"));

        // Print size
        System.out.println("Size of map: " + map.size());
    }
}
