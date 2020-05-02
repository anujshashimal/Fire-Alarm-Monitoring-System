package DesktopClient;


import Rmi_Server.Rmi_Interface;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.Timer;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;
import org.json.JSONObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lahiru Lakruwan
 */
public class Edit_Sensor_Details extends javax.swing.JFrame {

DB db;
DBCollection  table;
public static int i=0;
public static  String dataarray ="";
public static String getuniquesensordetails ="";

    /**
     * Creates new form Edit_Sensor_Details
     */
    public Edit_Sensor_Details() {
         initComponents();
         
         
           Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
			       	Display_data_to_table();
                               
			}
		},1000,15000);     
         
    }

    private  void Display_data_to_table()
    {
    
                ArrayList<Integer>  smokelevel = new ArrayList<>(); 
                ArrayList<Integer>   Co2level = new ArrayList<Integer> ();
                ArrayList<String>   floorno = new ArrayList<>();
                ArrayList<String>   roomno = new ArrayList<>();     
                        
                    Registry reg=null;                                       
        try {
            reg = LocateRegistry.getRegistry("localhost",1022);        //  Service Lookup
         } catch (RemoteException ex) {
              Logger.getLogger(Add_Sensor_Details.class.getName()).log(Level.SEVERE, null, ex);
         }
       try {
             Rmi_Interface rmiinterface =  (Rmi_Interface) reg.lookup("rmiserver");  //  Service Lookup
        
             
            if(i == 0)
            {  Edit_Sensor_Details.dataarray =   rmiinterface.Get_Sensor_Behaviour_Details(); // Accessing Service
              i++;
            } 
            else
            {
              Edit_Sensor_Details.dataarray =   rmiinterface.Get_Latest_Update_Sensor_Status_Details(); // Accessing Service
            }
              JSONArray jsonArr = new JSONArray(dataarray);
              String[] columnNames = {"Sensor_Id", "Floor_no","Room_no","Smoke_level","Co2_Level","active"};
               DefaultTableModel model = new DefaultTableModel(columnNames, 0); 
              for (int i = 0; i < jsonArr.length(); i++) 
              {
                       JSONObject object = jsonArr.getJSONObject(i);
                       int Sensor_Id = object.getInt("id");
                       String Floor_no = object.getString("floorNo");
                       String Room_no = object.getString("roomNo");
                       int Smoke_level = object.getInt("smokeLevel");
                       int Co2_Level = object.getInt("co2Level");
                       String active = object.getString("status");
                       
                       smokelevel.add(Smoke_level);
                       Co2level.add(Co2_Level);
                       floorno.add(Floor_no);
                       roomno.add(Room_no);
                       
                     model.addRow(new Object[] { Sensor_Id ,Floor_no,Room_no,Smoke_level, Co2_Level,active });
                 
               }
                jTable1.setModel(model);
   
              
               for(int i=0;i<smokelevel.size();i++)
                   {
                     if(smokelevel.get(i) > 5 )  
                     {
                         String floornos =  floorno.get(i);
                         String roomnos = roomno.get(i);
                         
                         String settitle = "WARNING";
                         
                         String infoMessage = "FloorNo:"+" "+floornos+" ,,"+"RoomNo:"+" "+roomnos+",, "+"Smoke level is Greater than 5";
                         
                         JOptionPane.showConfirmDialog(this,infoMessage,"WARNING",JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
                         
                     }
                      if(Co2level.get(i) > 5 )  
                     {
                         String floornos =  floorno.get(i);
                         String roomnos = roomno.get(i);
                         
                         String settitle = "WARNING";
                         
                         String infoMessage = "FloorNo:"+" "+floornos+" ,,"+"RoomNo:"+" "+roomnos+",, "+"Co2 level is Greater than 5";
                         
                         JOptionPane.showConfirmDialog(this,infoMessage,"WARNING",JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
                         
                     }   
                   
                   }
                       
             
    } catch (RemoteException ex) {
        Logger.getLogger(Add_Sensor_Details.class.getName()).log(Level.SEVERE, null, ex);
    }
       catch (NotBoundException ex) {
        Logger.getLogger(Add_Sensor_Details.class.getName()).log(Level.SEVERE, null, ex);
         }
       
         
    } 
    
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        roomno = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        floorno = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        sensorid = new javax.swing.JTextField();
        Search = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Sensor_Id");

        roomno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roomnoActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Sensor Details");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Floor_No");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Room_No");

        floorno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                floornoActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("UPDATE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sensor_Id", "Floor_no", "Room_no", "Smoke_level", "Co2_Level", "active"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        sensorid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sensoridActionPerformed(evt);
            }
        });

        Search.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Search.setText("SEARCH");
        Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Edit Sensor Details");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(sensorid, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(roomno, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(73, 73, 73)
                                    .addComponent(floorno, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(245, 245, 245))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(184, 184, 184)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(719, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sensorid, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Search))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(floorno, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roomno, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addComponent(jButton1)
                .addGap(75, 75, 75))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(346, Short.MAX_VALUE)))
        );

        jButton1.getAccessibleContext().setAccessibleName("jButton1");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void roomnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roomnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_roomnoActionPerformed

    private void floornoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_floornoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_floornoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int id  = Integer.parseInt(sensorid.getText());
        String floorno1 = floorno.getText().toString();
        String roomno1 = roomno.getText().toString();
        
        
               
                    Registry reg=null;
        try {
            reg = LocateRegistry.getRegistry("localhost",1022);  //  Service Lookup
         } catch (RemoteException ex) {
              Logger.getLogger(Add_Sensor_Details.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        
          Rmi_Interface rmiinterface = null;
    try {
        rmiinterface = (Rmi_Interface) reg.lookup("rmiserver");   //  Service Lookup
    } catch (RemoteException ex) {
        Logger.getLogger(Edit_Sensor_Details.class.getName()).log(Level.SEVERE, null, ex);
    } catch (NotBoundException ex) {
        Logger.getLogger(Edit_Sensor_Details.class.getName()).log(Level.SEVERE, null, ex);
    }
    try {
         rmiinterface.Edit_Sensor_Details(id,floorno1,roomno1); // Accessing Service
        
         sensorid.setText("");
         floorno.setText("");
         roomno.setText("");
    
    
    } catch (RemoteException ex) {
        Logger.getLogger(Edit_Sensor_Details.class.getName()).log(Level.SEVERE, null, ex);
    }
        
        
        
        
        
        
        
        
        
        
       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void sensoridActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sensoridActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sensoridActionPerformed

    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
        // TODO add your handling code here:
                      
                    Registry reg=null;
        try {
            reg = LocateRegistry.getRegistry("localhost",1022);    //  Service Lookup
         } catch (RemoteException ex) {
              Logger.getLogger(Add_Sensor_Details.class.getName()).log(Level.SEVERE, null, ex);
         }
        
          Rmi_Interface rmiinterface = null;
         try {
               rmiinterface = (Rmi_Interface) reg.lookup("rmiserver");   //  Service Lookup
            } catch (RemoteException ex) {
                  Logger.getLogger(Edit_Sensor_Details.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NotBoundException ex) {
            Logger.getLogger(Edit_Sensor_Details.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                  Edit_Sensor_Details.getuniquesensordetails =   rmiinterface.Get_Sensor_Information_Details();  // Accessing Service
               } catch (RemoteException ex) {
                    Logger.getLogger(Edit_Sensor_Details.class.getName()).log(Level.SEVERE, null, ex);
               }
         
                   JSONArray jsonArr = new JSONArray(getuniquesensordetails);
                  for (int i = 0; i < jsonArr.length(); i++)
                {
                    JSONObject object = jsonArr.getJSONObject(i);
                    int Sensor_Id = object.getInt("Id");
                     if(Sensor_Id == Integer.parseInt(sensorid.getText()))
                     {
                                floorno.setText(object.getString("floorNo"));
                                roomno.setText(object.getString("roomNo"));
                   
                     }
                  }
        
    }//GEN-LAST:event_SearchActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
         /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Edit_Sensor_Details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Edit_Sensor_Details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Edit_Sensor_Details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Edit_Sensor_Details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
         
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Edit_Sensor_Details().setVisible(true);
               
            }
        });
    }
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Search;
    private javax.swing.JTextField floorno;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField roomno;
    private javax.swing.JTextField sensorid;
    // End of variables declaration//GEN-END:variables
}
