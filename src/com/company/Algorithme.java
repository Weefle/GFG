package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class Algorithme {

    public static Processus pr_actif = null;
    //public static Processus pr_wait = null;

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
        int time = API.findStartProcessus(pro);

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

                        msg = msg.concat(time + API.addSpace(8-String.valueOf(time).length()));

                        for (Processus pp : pro) {

                            String ss = API.stateToString(pp);
                            msg = msg.concat(ss + API.addSpace(9-ss.length()));

                        }
                        msg = msg.concat("\n");
                    }
                    assert pr_actif != null;
                    if(pr_actif.remain_t == pr_actif.elapsed_time){
                        pr_actif.isRunning = false;
                        pr_actif.activable = false;
                        pr_actif.just_finished = true;
                        if(!API.processAvailable(pro)){
                            msg = msg.concat(time + API.addSpace(8-String.valueOf(time).length()));
                            msg = msg.concat(API.setMessage(pro));
                            pr_actif = null;
                            break;
                        }
                        msg = msg.concat(time + API.addSpace(8-String.valueOf(time).length()));
                        msg = msg.concat(API.setMessage(pro));
                    }

                }

                time++;

                if(pr_actif==null){
                    run = false;
                }

            }

        }else if(type.equals("FIFO_")){

            while (run) {

                if(pr_actif!=null){

                    pr_actif.elapsed_time++;
                    //System.out.println(pr_actif.nameProc + " : " + time + " : " + pr_actif.elapsed_time);

                }

                for (Processus p : pro) {


                    if (p.arrive_t == time) {
                        if (pr_actif == null) {
                            /*pr_actif = API.processAllAvailable(pro);
                            if(pr_actif==null) {
                                if(pr_wait!=null) {
                                    pr_actif = pr_wait;
                                    pr_wait=null;
                                }*/
                                p.isRunning = true;
                                pr_actif = p;
                            } else{
                                p.activable = true;
                        }

                        if(pr_actif.priority_l > p.priority_l){
                            //pr_wait = p;
                            p.isRunning = true;
                            pr_actif = p;
                        }

                        msg = msg.concat(time + API.addSpace(8-String.valueOf(time).length()));

                        for (Processus pp : pro) {

                            String ss = API.stateToString(pp);
                            msg = msg.concat(ss + API.addSpace(9-ss.length()));

                        }
                        msg = msg.concat("\n");
                    }
                    assert pr_actif != null;
                    if(pr_actif.remain_t == pr_actif.elapsed_time){
                        pr_actif.isRunning = false;
                        pr_actif.activable = false;
                        pr_actif.just_finished = true;
                        //System.out.println(pr_actif.nameProc);
                        if(!API.processAvailable(pro)){
                            msg = msg.concat(time + API.addSpace(8-String.valueOf(time).length()));
                            msg = msg.concat(API.setMessage(pro));
                            pr_actif = null;
                            break; }
                        msg = msg.concat(time + API.addSpace(8-String.valueOf(time).length()));
                        msg = msg.concat(API.setMessage(pro));
                    }

                }

                time++;

                if(pr_actif==null){
                    run = false;
                }

            }

        }else if(type.equals("ROUND")){

        }else if(type.equals("ROUND_")){

        }

        System.out.print("Date	");
        myIO.ecrireEcran(head + "\n");
        myIO.ecrireEcran(msg);

    }
}

