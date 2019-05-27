/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javafx.concurrent.Task;
import javax.swing.JLabel;

/**
 *
 * @author ULUZMAN
 */
public class TimerController1{
    private static int counter = 0;
    private boolean  isDuraklat = false;
    private Task timerTask;
    JLabel sureSayac;
    Timer timer;
    public TimerController1(){                  
        timer = new Timer();
    }  
    public void timerBasla(final JLabel sureSayac){
        this.sureSayac = sureSayac;        
        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run() {
                //if(!isIsDuraklat()){
                sureSayac.setText(Integer.toString(counter));
                counter++;                    
                //}               
            }
        }, new Date(), 1000);      
    }
    
    public void duraklat() {
        this.timer.cancel();
    }
    public void devamEt() {
        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run() {                
                sureSayac.setText(Integer.toString(counter));
                counter++;                                    
            }
        }, new Date(), 1000); 
    }
    /**
     * @return the isDuraklat
     */
    public boolean isIsDuraklat() {
        return isDuraklat;
    }

    /**
     * @param isDuraklat the isDuraklat to set
     */
    public void setIsDuraklat(boolean isDuraklat) {
        this.isDuraklat = isDuraklat;
    }
    
}
