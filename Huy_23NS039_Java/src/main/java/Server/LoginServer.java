package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginServer {
    private static final int PORT = 12345;
    private static String loggedInUsername; // Biến tĩnh để lưu tên đăng nhập
    
    public static String getLoggedInUsername() {
        return loggedInUsername;
    }

    public static void setLoggedInUsername(String username) {
        loggedInUsername = username;
    }
    public static void main(String[] args) {
        // Start the server
        ServerGUI serverGUI = new ServerGUI();
        serverGUI.setVisible(true);
    }
    
    public static class ClientHandler extends Thread {
        private Socket clientSocket;
        private UserLoggedInListener userLoggedInListener;

        public ClientHandler(Socket socket) {
            clientSocket = socket;
        }

        public void setUserLoggedInListener(UserLoggedInListener listener) {
            this.userLoggedInListener = listener;
        }

        public void run() {
            try {
                // Get input and output streams from the client socket
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                // Read username and password from the client
                String username = in.readLine();
                String password = in.readLine();

                // Authenticate user (perform database query)
                boolean isAuthenticated = authenticate(username, password);

                // Send authentication result back to the client
                if (isAuthenticated) {
                    out.println("SUCCESS");
                    if (userLoggedInListener != null) {
                        userLoggedInListener.onUserLoggedIn(username);
                    }
                } else {
                    out.println("FAILURE");
                }

                // Close the streams and the socket
                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private boolean authenticate(String username, String password) {
            String url = "jdbc:mysql://localhost:3306/quanlykhachsan";
            String user = "root";
            String dbPassword = "";

            try (Connection connection = DriverManager.getConnection(url, user, dbPassword)) {
                String query = "SELECT * FROM taikhoan WHERE username = ? AND password = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, username);
                    preparedStatement.setString(2, password);

                    ResultSet resultSet = preparedStatement.executeQuery();

                    return resultSet.next();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    public interface UserLoggedInListener {
        void onUserLoggedIn(String username);
    }

//	public static boolean authenticate(String username, String password) {
//		// TODO Auto-generated method stub
//		return false;
//	}
}


//   CÒN DÙNG RÁT OK //
//package Server;
//
//import java.io.*;
//import java.net.*;
//import java.sql.*;
//
//public class LoginServer {
//    private static final int PORT = 12345;
////    private static int port; // Sửa giá trị cổng thành biến để lưu cổng đã được gán tự động
//    public static void main(String[] args) {
//        try {
//            ServerSocket serverSocket = new ServerSocket(PORT);
//            System.out.println("Server is running...");
////    	  try (ServerSocket serverSocket = new ServerSocket(0)) {
////              port = serverSocket.getLocalPort(); // Lấy cổng đã được gán tự động
////              System.out.println("Server is running on port " + port + "...");
//            while (true) {
//                Socket clientSocket = serverSocket.accept();
//                new ClientHandler(clientSocket).start();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
////    public static int getPort() {
////        return port; // Phương thức getter cho biến port
////    }
//   /*private*/ public static class ClientHandler extends Thread {
//        private Socket clientSocket;
//
//        public ClientHandler(Socket socket) {
//            clientSocket = socket;
//        }
//
//        public void run() {
//            try {
//                // Get input and output streams from the client socket
//                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
//
//                // Read username and password from the client
//                String username = in.readLine();
//                String password = in.readLine();
//
//                // Authenticate user (perform database query)
//                boolean isAuthenticated = authenticate(username, password);
//
//                // Send authentication result back to the client
//                if (isAuthenticated) {
//                    out.println("SUCCESS");
//                } else {
//                    out.println("FAILURE");
//                }
//
//                // Close the streams and the socket
//                in.close();
//                out.close();
//                clientSocket.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        private boolean authenticate(String username, String password) {
//            String url = "jdbc:mysql://localhost:3306/quanlykhachsan";
//            String user = "root";
//            String dbPassword = "";
//
//            try (Connection connection = DriverManager.getConnection(url, user, dbPassword)) {
//                String query = "SELECT * FROM taikhoan WHERE username = ? AND password = ?";
//                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//                    preparedStatement.setString(1, username);
//                    preparedStatement.setString(2, password);
//
//                    ResultSet resultSet = preparedStatement.executeQuery();
//
//                    return resultSet.next();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//                return false;
//            }
//        }
//    }
//}
//
//

























//package khachsangpt;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class LoginServer {
//    private static final int PORT = 12345;
//
//    public static void main(String[] args) {
//        try {
//            ServerSocket serverSocket = new ServerSocket(PORT);
//            System.out.println("Server is running...");
//            while (true) {
//                Socket clientSocket = serverSocket.accept();
//                new ClientHandler(clientSocket).start();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static class ClientHandler extends Thread {
//        private Socket clientSocket;
//
//        public ClientHandler(Socket socket) {
//            clientSocket = socket;
//        }
//
//        public void run() {
//            try {
//                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
//
//                String username = in.readLine();
//                String password = in.readLine();
//
//                boolean isAuthenticated = authenticate(username, password);
//
//                if (isAuthenticated) {
//                    out.println("SUCCESS");
//                } else {
//                    out.println("FAILURE");
//                }
//
//                in.close();
//                out.close();
//                clientSocket.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        private boolean authenticate(String username, String password) {
//            String url = "jdbc:mysql://localhost:3306/quanlykhachsan";
//            String user = "root";
//            String dbPassword = "";
//
//            try (Connection connection = DriverManager.getConnection(url, user, dbPassword)) {
//                String query = "SELECT * FROM taikhoan WHERE username = ? AND password = ?";
//                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//                    preparedStatement.setString(1, username);
//                    preparedStatement.setString(2, PasswordUtils.hashPassword(password));
//
//                    ResultSet resultSet = preparedStatement.executeQuery();
//
//                    return resultSet.next();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//                return false;
//            }
//        }
//    }
//}
//
//
