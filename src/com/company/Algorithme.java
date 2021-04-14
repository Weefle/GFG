package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class Algorithme {

    private static Processus pr_actif = null;

    public Algorithme(String type, String allP) {

        String msg="";
        String head="";
        IOCommandes myIO=new IOCommandes();

        Processus[] process = myIO.tableProcess(allP);

        ArrayList<Processus> pro = new ArrayList<>(Arrays.asList(process));


        pro.sort((o1, o2) -> (int) (o1.arrive_t - o2.arrive_t));

        myIO.ecrireEcran("---------------Traitement :)---------------\n\n");


        for (Processus pp : pro) {

            head = head + pp.nameProc + "       ";


        }



        boolean run = true;
        int time = findFirstExecutionTime(process);

        if(type.equals("FIFO")){

            while (run) {

                if(pr_actif!=null){

                    pr_actif.elapsed_time++;

                }

                for (Processus p : pro) {


                    if (p.arrive_t == time) {
                        if (pr_actif == null) {
                            p.isRunning = true;
                            pr_actif = p;
                        }else{
                            p.activable = true;
                        }

                        msg = msg.concat(time + getSpace(8-String.valueOf(time).length()));

                        for (Processus pp : pro) {

                            String ss = stateToString(pp);
                            msg = msg.concat(ss + getSpace(9-ss.length()));

                        }
                        msg = msg.concat("\n");
                    }
                    assert pr_actif != null;
                    if(pr_actif.remain_t == pr_actif.elapsed_time){
                        pr_actif.isRunning = false;
                        pr_actif.activable = false;
                        pr_actif.just_finished = true;
                        if(!processAvailable(pro)){
                            msg = msg.concat(time + getSpace(8-String.valueOf(time).length()));
                            msg = msg.concat(setMessage(pro));
                            pr_actif = null;
                            break;
                        }
                        msg = msg.concat(time + getSpace(8-String.valueOf(time).length()));
                        msg = msg.concat(setMessage(pro));
                    }

                }

                time++;

                if(pr_actif==null){
                    run = false;
                }

            }

        }else if(type.equals("FIFO_")){

        }else if(type.equals("ROUND")){

        }else if(type.equals("ROUND_")){

        }

        System.out.print("Date	");
        myIO.ecrireEcran(head + "\n");
        myIO.ecrireEcran(msg);

    }
    static int findFirstExecutionTime(Processus[] ps)
    {
        int min = (int) ps[0].arrive_t;

        for(int i = 0 ; i < ps.length ; i++)
        {
            if(ps[i].arrive_t < min) min = (int) ps[i].arrive_t;
        }

        return min;
    }

    public static String setMessage(ArrayList<Processus> pro){
        boolean stop = false;
        String msg = "";
        for (Processus prc : pro) {

            if(!prc.isRunning && prc.activable && !stop){
                pr_actif = prc;
                pr_actif.activable = false;
                pr_actif.isRunning = true;
                stop = true;
            }
            String s = stateToString(prc);
            msg = msg.concat(s + getSpace(9-s.length()));
        }
        msg = msg.concat("\n");
        return msg;

    }

    public static boolean processAvailable(ArrayList<Processus> ps)
    {
        for (Processus p : ps) {
            if (p.activable) return true;
        }

        return false;
    }

    public static String stateToString(Processus prg){

        String msge="";

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

    static String getSpace(int nb)
    {
        StringBuilder line = new StringBuilder();
        for(int i = 0 ; i < nb ; i++)
        {
            line.append(" ");
        }
        return line.toString();
    }
}

