package com.paresh.practice.data.structure;

import java.util.LinkedList;

public class Map2ndPractice<K,V> {

    private static Integer SIZE = 16 ;
    private LinkedList<Entry<K,V>> table[];

    static class Entry<K,V>{
        K key;
        V value;

        public Entry(K key , V value){
            this.key = key;
            this.value = value;
        }
    }

    public Map2ndPractice(){
        table = new LinkedList[SIZE];
        for(int i = 0 ; i < SIZE ; i++){
            table[i] = new LinkedList<>();
        }
    }

    public int getIndex(K key){
        return key.hashCode() % SIZE;
    }

    public void put(K key , V value){
        int index = getIndex(key);
        LinkedList<Entry<K,V>> bucket = table[index];

        for(Entry<K,V> entry : bucket){
            if(entry.key.equals(key)){
                entry.value = value;
            }
            return;
        }
        bucket.add(new Entry<>(key, value));
    }

    public V get(K key){
        int index = getIndex(key);
        LinkedList<Entry<K,V>> bucket = table[index];

        for(Entry<K,V> entry : bucket){
            if(entry.key.equals(key)){
                return entry.value;
            }
        }
        return null;

    }

    public boolean contains (K key){
        int index = getIndex(key);
        LinkedList<Entry<K,V>> bucket = table[index];

        for(Entry<K,V> entry : bucket){
            if(entry.key.equals(key)){
                return true;
            }
        }
        return false;
    }

    public void remove(K key){
        int index = getIndex(key);
        LinkedList<Entry<K,V>> bucket = table[index];
        //****************************************************
        bucket.removeIf(kvEntry -> kvEntry.key.equals(key));
        //*******************************************************
        bucket.removeIf(kvEntry -> kvEntry.key.equals(key));
//        for(Entry<K,V> entry : bucket){
//            if (entry.key.equals(key)) {
//                bucket.remove(entry);
//                break;
//            }
//        }
    }

    public int size(){
        int size = 0;
//        for (int i = 0; i< SIZE; i++){
//            size = size + table[i].size();
//        }
        for(LinkedList<Entry<K,V>> bucket : table){
            size+= bucket.size();
        }
        return size;
    }

    public boolean isEmpty(){
        return size() == 0;
    }
}
