package edu.gmu.swe622.lab4;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IHeartbeatServer extends Remote {

	public int registerClient() throws RemoteException;

	public void heartbeat(int client) throws RemoteException;
	
	public void disconnect(int client) throws RemoteException;

	public static final String HEARTBEAT_SERVER_RMI_NAME = "Lab2HeartbeatServer";
}
