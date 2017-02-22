# SWE 622 Spring 2017 - Lab 4
## Consistency Questions

In this lab, you'll have some more fun with RMI, this time developing a protocol to keep track of which clients are alive. In this repository, you'll find the usual RMI setup: a shared project that defines the RMI interface, a client and a server. The interface has the following methods:

```
public int registerClient() throws RemoteException;
public void heartbeat(int client) throws RemoteException;
public void disconnect(int client) throws RemoteException;

```

Client:
When a client comes online, it will call registerClient and receive an ID. Every 5 seconds it will call heartbeat(clientId) passing that clientID back to the server. Before exiting, the client will call disconnect.

Server:
Every 10 seconds it will print out the IDs of all clients that have contacted in the past 10 seconds. For each client, it will print out its ID and also when it last heard from the client.

The server will generate IDs sequentially (the first person to call register gets 1, then 2, then 3, etc.).
