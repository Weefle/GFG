package com.company;

import java.util.ArrayList;
import java.util.Comparator;

public class API {

    static int findStartProcessus(ArrayList<Processus> ps)
    {

        return (int) ((ArrayList<Processus>) ps.clone()).stream().findFirst().get().arrive_t;
    }

    public static String setMessage(ArrayList<Processus> pro){
        boolean stop = false;
        String msg = "";
        for (Processus prc : pro) {

            if(!prc.isRunning && prc.activable && !stop){
                Algorithme.pr_actif = prc;
                Algorithme.pr_actif.activable = false;
                Algorithme.pr_actif.isRunning = true;
                stop = true;
            }else{
                // pr_actif = processAllAvailable(pro);
                // if(pr_actif==null){
                prc.isRunning = false;
                //  }
            }

            String s = stateToString(prc);
            msg = msg.concat(s + addSpace(9-s.length()));
        }
        msg = msg.concat("\n");
        return msg;

    }

    public static boolean processAvailable(ArrayList<Processus> ps)
    {
        for (Processus p : ps) {
            if (p.activable){
                //System.out.println("->" + p.nameProc);
                return true;
            }
        }

        return false;
    }


    public static Processus processAllAvailable(ArrayList<Processus> psa)
    {

        ArrayList<Processus> ps = (ArrayList<Processus>) psa.clone();
        ps.sort(Comparator.comparingInt(o -> o.priority_l));

        for (Processus p : ps) {
            if (p.activable){
                p.isRunning = true;
                //System.out.println("->" + p.nameProc);
                return p;
            }/*else if(p.priority_l == ps.stream().findFirst().get().priority_l){
                p.isRunning = false;
                p.activable = true;
                //System.out.println("->" + p.nameProc);
                return p;
            }*/
        }

        return null;
    }

    public static String stateToString(Processus prg){

        String msge;


        if(prg.just_finished){
            msge = "A(" + prg.elapsed_time + ")";
            prg.just_finished = false;
        }else if(prg.activable){
            msge = "a";
        }else if(prg.isRunning){
            msge = "A(" + prg.elapsed_time + ")";
        }else{
            msge = "-";
        }

        return msge;

    }

    static String addSpace(int nb)
    {
        StringBuilder line = new StringBuilder();
        for(int i = 0 ; i < nb ; i++)
        {
            line.append(" ");
        }
        return line.toString();
    }

}
