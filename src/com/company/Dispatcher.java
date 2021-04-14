package com.company;

import java.io.File;


class Dispatcher{


	public static void main(String[] args) {

		File f;
		IOCommandes myIO=new IOCommandes();
		
		if(args.length!=1){
			myIO.ecrireEcran("Nombre d'arguments incorrects !\nEntrez le nom du ficher des processus à traiter :)");

		} else {
			myIO.ecrireEcran("\n");
			myIO.ecrireEcran("********************* Welcome to our DISPATCHER Menu *********************");
			myIO.ecrireEcran("\n");

			f = new File(args[0]);
			String allP = myIO.lireFile(f);

			myIO.afficheProcess(allP);

			myIO.ecrireEcran("Veuillez entrer un algorithme de traitement:\n");
			myIO.ecrireEcran("1) FIFO");
			myIO.ecrireEcran("2) FIFO avec priorites");
			myIO.ecrireEcran("3) Round Robin");
			myIO.ecrireEcran("4) Round Robin avec priorites");

			String lecteur = myIO.lireEcran();

			if(lecteur.equals("1")){
				new Algorithme("FIFO", allP);
			}else if(lecteur.equals("2")){
				new Algorithme("FIFO_", allP);
			}else if(lecteur.equals("3")){
				new Algorithme("ROUND", allP);
			}else if(lecteur.equals("4")){
				new Algorithme("ROUND_", allP);
			}


		}
		
	 }
	 
	
	
}