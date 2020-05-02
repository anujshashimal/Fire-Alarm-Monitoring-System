package Rmi_Server;


import java.rmi.Remote;
import java.rmi.RemoteException;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lahiru Lakruwan
 */
public interface Rmi_Interface extends Remote
{
  public  void  Add_Sensor_Details(int id,String floorno1,String roomno1) throws RemoteException;      //Add sensor details  to Db through Rest Api
  
  public  void  Edit_Sensor_Details(int sensor_id,String floorno,String roomno) throws RemoteException;     //Edit sensor details  through Rest Api
  
  
  public String   Get_Sensor_Information_Details()throws RemoteException;   //Get sensor information details[sensor id,floor no,room no details]
    
  public  String Get_Sensor_Behaviour_Details() throws RemoteException;  //Get Sensor Active   status  Details including sensor id,floor no ,room no,Sensor Active or not details
    
  public String Get_Latest_Update_Sensor_Status_Details() throws RemoteException;  //Asynchronous method ,, Get latest update sensor update details through rest api 

 

}
