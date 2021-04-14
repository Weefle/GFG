package com.company;
//Nomprocessus	DateArrive	DureeExecution	DateDebutIO	DureeIO	Priorite

import java.util.Comparator;

class Processus implements Comparable<Processus>{

		String nameProc; //Process name
		float arrive_t; //Process arrive in the system at
		float remain_t; //remain execution time
		float ioAt_t; //I/O cycle execution at
		float ioLastF_t;		//I/O last for
		int priority_l; // Process priority level
		int elapsed_time;
		boolean activable;
		boolean isRunning;
		boolean just_finished;
		
		
		Processus(String name, float ar, float rt, float iot, float iolast, int prio, boolean activ, boolean running, int elapsed){
			nameProc=name;
			arrive_t=ar;
			remain_t=rt;
			ioAt_t=iot;
			ioLastF_t=iolast;
			priority_l=prio;
			activable=activ;
			isRunning=running;
			elapsed_time = elapsed;
			just_finished = false;
		}



	@Override
	public int compareTo(Processus o) {
		return Comparators.TIME.compare(this, o);
	}

	public static class Comparators {



		public static Comparator<Processus> TIME = new Comparator<Processus>() {
			@Override
			public int compare(Processus o1, Processus o2) {
				return (int) (o1.arrive_t - o2.arrive_t);
			}
		};

	}
}