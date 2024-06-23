//package Server;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.Socket;
//
//public static class ClientHandler implements Runnable {
//    private Socket clientSocket;
//
//    public ClientHandler(Socket socket) {
//        clientSocket = socket;
//    }
//
//    @Override
//    public void run() {
//        try {
//            // Get input and output streams from the client socket
//            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
//
//            // Read username and password from the client
//            String username = in.readLine();
//            String password = in.readLine();
//
//            // Authenticate user (perform database query)
//            boolean isAuthenticated = authenticate(username, password);
//
//            // Send authentication result back to the client
//            if (isAuthenticated) {
//                out.println("SUCCESS");
//            } else {
//                out.println("FAILURE");
//            }
//
//            // Close the streams and the socket
//            in.close();
//            out.close();
//            clientSocket.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
