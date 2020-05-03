package Rmi_Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lahiru Lakruwan
 */
public class Rmi_Server extends UnicastRemoteObject implements Rmi_Interface 
{

    
  
   private static HttpURLConnection con;
   public static String getdetails;
   public static String get_latest_update_details;  
   public static String getsensordetails;
     
     public Rmi_Server() throws RemoteException{
     
       super();
     
     }
     
     //Add sensor details to the database through rest api
     @Override
      public void Add_Sensor_Details(int id,String floorno1,String roomno1)  {  
       try {
         
           
           final String POST_PARAMS = "{\n"+"    \"Id\":"+"\""+id+"\",\r\n" +
                   "    \"floorNo\":"+"\""+floorno1+"\",\r\n"+
                   "    \"roomNo\":"+"\""+roomno1+"\"" + "\n}";
           
           
           System.out.println(POST_PARAMS);
           URL obj = new URL("http://localhost:5000/api/sensor/Add");
           HttpURLConnection postConnection;
           
           postConnection = (HttpURLConnection) obj.openConnection();
           postConnection.setRequestMethod("POST");
          
           postConnection.setRequestProperty("Content-Type", "application/json");
           postConnection.setDoOutput(true);
           OutputStream os = postConnection.getOutputStream();
           os.write(POST_PARAMS.getBytes());
           os.flush();
           os.close();
           int responseCode = postConnection.getResponseCode();
           System.out.println("POST Response Code :  " + responseCode);
           System.out.println("POST Response Message : " + postConnection.getResponseMessage());
           if (responseCode == HttpURLConnection.HTTP_CREATED) { //success
               BufferedReader in = new BufferedReader(new InputStreamReader(
                       postConnection.getInputStream()));
               String inputLine;
               StringBuffer response = new StringBuffer();
               while ((inputLine = in .readLine()) != null) {
                   response.append(inputLine);
               } in .close();
               // print result
               System.out.println(response.toString());
           } else {
               System.out.println("POST NOT WORKED");
           }
       } catch (MalformedURLException ex) {
           Logger.getLogger(Rmi_Server.class.getName()).log(Level.SEVERE, null, ex);
       } catch (IOException ex) {
           Logger.getLogger(Rmi_Server.class.getName()).log(Level.SEVERE, null, ex);
       }
  
    }
      
      //Edit sensor details are stored in db 
    @Override
    public void Edit_Sensor_Details(int sensor_id,String floorno,String roomno) {
       try {
           final String POST_PARAMS = "{\n"+"    \"Id\":"+"\""+sensor_id+"\",\r\n" +
                   "    \"floorNo\":"+"\""+floorno+"\",\r\n"+
                   "    \"roomNo\":"+"\""+roomno+"\"" + "\n}";
           
           
           System.out.println(POST_PARAMS);
           URL obj = new URL("http://localhost:5000/api/sensor/update");
           HttpURLConnection putConnection;
           
           putConnection = (HttpURLConnection) obj.openConnection();
           putConnection.setRequestMethod("POST");
           putConnection.setRequestProperty("Content-Type", "application/json");
           putConnection.setDoOutput(true);
           OutputStream os = putConnection.getOutputStream();
           os.write(POST_PARAMS.getBytes());
           os.flush();
           os.close();
           int responseCode = putConnection.getResponseCode();
           System.out.println("PUTT Response Code :  " + responseCode);
           System.out.println("PUT Response Message : " + putConnection.getResponseMessage());
           if (responseCode == HttpURLConnection.HTTP_CREATED) { //success
               BufferedReader in = new BufferedReader(new InputStreamReader(
                       putConnection.getInputStream()));
               String inputLine;
               StringBuffer response = new StringBuffer();
               while ((inputLine = in .readLine()) != null) {
                   response.append(inputLine);
               } in .close();
               // print result
               System.out.println(response.toString());
           } else {
               System.out.println("Put NOT WORKED");
           }
       } catch (MalformedURLException ex) {
           Logger.getLogger(Rmi_Server.class.getName()).log(Level.SEVERE, null, ex);
       } catch (IOException ex) {
           Logger.getLogger(Rmi_Server.class.getName()).log(Level.SEVERE, null, ex);
       }
     
 
        
    }

    //Get sensor information details[sensor id,floor no,room no details]
    @Override
    public String Get_Sensor_Information_Details() {
        
   try {
                         
                         
                         URL urlForGetRequest = new URL("http://localhost:5000/api/sensor");
                         String readLine = null;
                         HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
                         conection.setRequestMethod("GET");
                         conection.setRequestProperty("userId", "a1bcdef"); // set userId its a sample here
                         int responseCode = conection.getResponseCode();
                         if (responseCode == HttpURLConnection.HTTP_OK) {
                             BufferedReader in = new BufferedReader(
                                     new InputStreamReader(conection.getInputStream()));
                             StringBuffer response = new StringBuffer();
                             while ((readLine = in .readLine()) != null) {
                                 response.append(readLine);
                             } in .close();
                             // print result
                             Rmi_Server.getsensordetails = response.toString();
                             System.out.println("JSON String Result " + response.toString());
                              //GetAndPost.POSTRequest(response.toString());
                         } else {
                             System.out.println("GET NOT WORKED");
                         }                } catch (MalformedURLException ex) {
                         Logger.getLogger(Rmi_Server.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IOException ex) {
                         Logger.getLogger(Rmi_Server.class.getName()).log(Level.SEVERE, null, ex);
                     }
                     return Rmi_Server.getsensordetails;

    }
    
    //Get Sensor Active   status  Details including sensor id,floor no ,room no,Sensor Active status details
    //These Sensor details are displayed in the  Table [JTable]
    public    String Get_Sensor_Behaviour_Details() throws RemoteException
    {
       try {
                       
                         
                         URL urlForGetRequest = new URL("http://localhost:5000/api/sensorret");
                         String readLine = null;
                         HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
                         conection.setRequestMethod("GET");
                         conection.setRequestProperty("userId", "a1bcdef");
                         int responseCode = conection.getResponseCode();
                         if (responseCode == HttpURLConnection.HTTP_OK) {
                             BufferedReader in = new BufferedReader(
                                     new InputStreamReader(conection.getInputStream()));
                             StringBuffer response = new StringBuffer();
                             while ((readLine = in .readLine()) != null) {
                                 response.append(readLine);
                             } in .close();
                             // print result
                            Rmi_Server.getdetails = response.toString();
  
                           System.out.println("JSON String Result " + response.toString());
                              //GetAndPost.POSTRequest(response.toString());
                         } else {
                             System.out.println("GET NOT WORKED");
                         }                } catch (MalformedURLException ex) {
                         Logger.getLogger(Rmi_Server.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IOException ex) {
                         Logger.getLogger(Rmi_Server.class.getName()).log(Level.SEVERE, null, ex);
                     }
                                
       return Rmi_Server.getdetails;
       
    }
    
    
    
    
      //Asynchronous method ,, Get latest update sensor update details[sensor id,floor no ,room no,Sensor Active status details] through rest api 
     // Table data update every 15 seconds .
    //This Asynchronous method call every 15 seconds.
     public String Get_Latest_Update_Sensor_Status_Details() throws RemoteException
     {
           
               Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
                 
                      try {
                        
                         URL urlForGetRequest = new URL("http://localhost:5000/api/sensorret");
                         String readLine = null;
                         HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
                         conection.setRequestMethod("GET");
                         conection.setRequestProperty("userId", "a1bcdef"); 
                         int responseCode = conection.getResponseCode();
                         if (responseCode == HttpURLConnection.HTTP_OK) {
                             BufferedReader in = new BufferedReader(
                                     new InputStreamReader(conection.getInputStream()));
                             StringBuffer response = new StringBuffer();
                             while ((readLine = in .readLine()) != null) {
                                 response.append(readLine);
                             } in .close();
                             // print result
                            Rmi_Server.getdetails = response.toString();
  
                           System.out.println("JSON latest update result Result " + response.toString());
                              //GetAndPost.POSTRequest(response.toString());
                         } else {
                             System.out.println("GET NOT WORKED");
                         }                } catch (MalformedURLException ex) {
                         Logger.getLogger(Rmi_Server.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IOException ex) {
                         Logger.getLogger(Rmi_Server.class.getName()).log(Level.SEVERE, null, ex);
                     }
                                
			}
                       
                       
		},1000,15000);
       
          return Rmi_Server.getdetails;
     }
     
    
    
    
    
     
    public static void main(String args[]) throws RemoteException, IOException
    {
       
             Rmi_Server Rmiserver = new Rmi_Server();          //Instantiating & Binding the Service 
            
            
          try
          {
            Registry reg = LocateRegistry.createRegistry(1022);     // Instantiating & Binding the Service 
             reg.rebind("rmiserver",Rmiserver);
          }
          catch(Exception e)
          {
          
          
          }
          

    }
    
   
   
    

    }
       
    
   
// In main()

   
    

