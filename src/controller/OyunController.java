/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Model.OyunModel;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

/**
 *
 * @author uluzm
 */
public class OyunController {
    private OyunModel oyunModel;
    private JPanel jpanelContainer;
    private int basarisizPuanDegeri = 0;
    private int basariliPuanDegeri = 0;
    JLabel puanLabel;
    TimerController timerController;
    public OyunController(OyunModel oyunModel,JPanel jpanelContainer,final JLabel puanLabel,TimerController timerController){
        this.oyunModel=oyunModel;
        this.jpanelContainer = jpanelContainer;
        this.puanLabel = puanLabel;
        this.timerController = timerController;
     }
    
    public void modelVeriDoldur(int adet,String selectedLayouth){
        oyunModel.setAdet(adet);
        oyunModel.setSelectedLayouth(selectedLayouth);
    }
    public int sayac = 0;
    public JToggleButton[] buttonList;
    public void buttonUret(){
       
        final int kutuAdedi = this.oyunModel.getAdet();
         int[] intListe = new int[kutuAdedi];
         final JToggleButton[] degerTut = new JToggleButton[kutuAdedi];
         buttonList = new JToggleButton[kutuAdedi];
         
         LayoutManager layout = new GridLayout();//FlowLayout
         if("FlowLayout".equals(this.oyunModel.getSelectedLayouth()))layout = new FlowLayout();
         this.jpanelContainer.setLayout(layout);
     
         
         SayiUret sayUret = new SayiUret();
         intListe = sayUret.sayiUretListe(kutuAdedi);
          
         
         int index;
        Random random = new Random();
        for (int i = intListe.length - 1; i > 0; i--)
        {
            index = random.nextInt(i + 1);
            if (index != i)
            {
                intListe[index] ^= intListe[i];
                intListe[i] ^= intListe[index];
                intListe[index] ^= intListe[i];
            }
        }            
        for(int i=1;i<=kutuAdedi;i++){
            JToggleButton toggleButton = new JToggleButton(Integer.toString(intListe[i-1]));
                toggleButton.setVisible(true);  
                 toggleButton.setActionCommand(Integer.toString(intListe[i-1]));   
                 buttonList[i-1]=toggleButton;                 
                ItemListener itemListener = new ItemListener() { 

                    public void itemStateChanged(ItemEvent itemEvent) 
                    { 
                        JToggleButton button = (JToggleButton) itemEvent.getSource();
                        //Tiklanilan buton hangi sayiyi tutorsa ona erisiliyor
                        String tiklanilanSayi = button.getActionCommand();
                        int state = itemEvent.getStateChange(); 

                        if (state == ItemEvent.SELECTED) {                    

                            button.setText(tiklanilanSayi);                            
                            if(sayac % 2 == 0){
                                button.setEnabled(false);
                                degerTut[sayac]= button;
                                sayac = sayac + 1;                                
                            }
                            else{                                
                                if(kontrolEt(tiklanilanSayi,degerTut)){                                  
                                    button.setEnabled(false);
                                    degerTut[sayac]= button;
                                    sayac = sayac + 1;
                                    basariliPuanDegeri = basariliPuanDegeri + 50;
                                }else{ 
                                    basarisizPuanDegeri = basarisizPuanDegeri + 20;
                                    button.setSelected(false);  
                                    button.setEnabled(true);
                                    sayac = sayac - 1;
                                    degerTut[sayac].setSelected(false); 
                                    degerTut[sayac].setEnabled(true);
                                    degerTut[sayac]= null;                                    
                                }
                            }
                        } 
                        else { 
                             button.setText("");
                        } 
                        if(kontrolEtOyunSon(degerTut)){
                             int sonucPuan = (basariliPuanDegeri - basarisizPuanDegeri)*kutuAdedi;
                             System.out.println("Sonuc Puan : " + sonucPuan);
                             puanLabel.setText(Integer.toString(sonucPuan));                            
                             timerController.duraklat();
                             //JOptionPane.showMessageDialog(null, "Oyun Bitti");
                        }
                    }                
                }; 
            // Attach Listeners 
            toggleButton.addItemListener(itemListener);   

            toggleButton.setPreferredSize(new Dimension(100, 100));
             this.jpanelContainer.add(toggleButton);
             this.jpanelContainer.revalidate();
        }
    }
    public boolean kontrolEt(String secilenSayi,JToggleButton secilenDizi[]){
            boolean kontrol = false;
        for (JToggleButton btn : secilenDizi) {
            if (btn != null){
                String command = btn.getActionCommand();
                if (command.equals(secilenSayi)) {
                    kontrol = true;
                }
            }
        }
            return kontrol;
    }
     public boolean kontrolEtOyunSon(JToggleButton secilenDizi[]){
       boolean isOyunSon = true;
       for (JToggleButton btn : secilenDizi) {
            if (btn == null){
                isOyunSon = false;
            }
       }
       return isOyunSon;
     }
     public void buttonTextTemizle(){     
         for (JToggleButton btn : buttonList) {
             if(btn.isEnabled()){
                 btn.setText("");
             }            
         }
     }
}
