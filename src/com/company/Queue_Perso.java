package com.company;
import java.util.*;


public class Queue_Perso {

    ArrayList<Processus> _currentQueue;
    int _currentQueueSize;
    AlgorithmeType _algorithmeType;

    Queue_Perso(ArrayList<Processus> processList, AlgorithmeType algorithmeType ){
        processList.sort((o1, o2) -> (int) (o1.arrive_t - o2.arrive_t));
        _algorithmeType = algorithmeType;
        int queueLenght = processList.size();
        _currentQueue = new ArrayList<>();
        for(Processus currentProcess : processList)
            _currentQueue.add(currentProcess);
        _currentQueueSize = queueLenght;
    }

    public Processus findFirst(){
        return _currentQueue.stream().findFirst().get();
    }

    public boolean isEmpty(){
        boolean result=false;
        int size = _currentQueue.size();
        result = size != 0;
        return result;
    }

    public boolean add(Processus process){
        boolean result = false;
        try{
            _currentQueue.add(process);
            result = true;
        }
        catch(Exception ex){

            ex.printStackTrace();
        }
        return result;
    }

    public void remove(int processID){
        boolean result = false;
        Processus processNumber = null;
        try{
            for(int i=0; i<_currentQueueSize; i++){

            }
        }catch(Exception ex){
            ex.printStackTrace();
        }


    }

    public boolean remove(Processus process){
        boolean result = false;
        try{
            _currentQueue.remove(process);
            result = true;
        }catch(Exception ex){

            ex.printStackTrace();
        }
        return result;
    }
}
