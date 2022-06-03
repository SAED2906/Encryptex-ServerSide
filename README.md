# Encryptex-ServerSide

Client Side: https://github.com/SAED2906/Encryptex-ClientSide

Encryptex is a Java program designed to create a platform for end-to-end encrypted file transfers between registered users of the program, users can register and login to a server version of the project that handles all the requests on a different computer.

This is the server side version of the project, it is a threaded server to allow multiple clients to connect at the same time, each client is assigned their own thread when they connect. The server acts as a middle man to hold files for a client that is offline, if the destination client is online they can select the receive button after adding a source UUID the send a request to the server for any outstanding files, if there are, the server then returns the files.


When Exported as a .jar it can be run in CMD (the recommended way), given it was exported with an older jdk version.
