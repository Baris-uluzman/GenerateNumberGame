/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author uluzm
 */
public class SayiUret {
    
    public int[] sayiUretListe(int kutuAdedi){
        Random random = new Random();
        ArrayList<Integer> intListe;
        intListe = new ArrayList<>();
        while (intListe.size() < kutuAdedi) { // how many numbers u need - it will 6
            int a = random.nextInt(kutuAdedi)+1; // this will give numbers between 1 and 50.
            if (!intListe.contains(a)) {
                intListe.add(a);
                intListe.add(a);
            }
        }
             
     //  Collections.shuffle(intListe);

        int[] arr = new int[intListe.size()];
        for(int i = 0; i < intListe.size(); i++) {
            arr[i] = intListe.get(i);
        }       
        return arr;
    }
  
    private boolean controlUretilenSayi(int uretilenSayi,int[] listeSayi){
        boolean result = true;
        for(int j=0;j<listeSayi.length;j++){
            if(uretilenSayi == listeSayi[j]){
                result = false;
            }
        }
        return result;
    }
}
