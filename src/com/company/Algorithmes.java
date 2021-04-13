package com.company;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.HashMap;

public  class Algorithmes  implements Pile {

    HashMap<Integer, Queue_Perso> _queueList = new HashMap<>();
    Integer _currentElements = 0;



    class FIFO {
        // Function to find page faults using FIFO
        // Method to find page faults using FIFO

        FIFO(Queue_Perso queue_perso) {
            _currentElements++;
            _queueList.put(_currentElements, queue_perso);
        }

        int organize() {
            _queueList.forEach((integer, queue_perso) -> {
                if(queue_perso._algorithmeType == AlgorithmeType.FIFO){
                    //TRAITEMENT FIFO DES QUEUES DANS LA LISTES

                    float time_slice = 0;
                    boolean doing = true;
                    Processus firstPosition=null;

                    do{

                        if(queue_perso.findFirst().arrive_t == time_slice){
                            firstPosition= queue_perso.findFirst();
                            for(float i = 0; i< firstPosition.remain_t; i++){
                                //ON DECREMENTE LE TIMESLACE
                                firstPosition.remain_t= firstPosition.remain_t - time_slice;
                                if(firstPosition.ioAt_t == firstPosition.remain_t){

                                }
                            }
                        }

                        time_slice+=5;

                    }while(doing);

            }


        });
            return 0;
    }


    class RoundRobinWithoutPriority {
    }

    class RoundRobinWithPriority {
    }
}
}

