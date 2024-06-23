////package Chat; // Như trước khi bấm server thì tắt mà bấm client thì công 1 //
////
////import java.awt.Frame;
////import client.Client ; 
////import server.Server;
////import server.ServerThread;
////import server.ServerThreadBus;
////
////
////import java.awt.BorderLayout;
////import java.awt.event.ActionEvent;
////import java.awt.event.ActionListener;
////import javax.swing.JButton;
////import javax.swing.JFrame;
////import javax.swing.JPanel;
////
////import client.Client;
////import server.Server;
////
////public class ButtonChat extends JFrame {
////    private JButton serverButton;
////    private JButton clientButton;
////
////    public ButtonChat() {
////        super("Ứng dụng Chat");
////        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
////        setSize(300, 200);
////        setLocationRelativeTo(null);
////        
////        serverButton = new JButton("Khởi động Server");
////        clientButton = new JButton("Khởi động Client");
////
////        serverButton.addActionListener(new ActionListener() {
////            @Override
////            public void actionPerformed(ActionEvent e) {
////                startServer();
////            }
////        });
////
////        clientButton.addActionListener(new ActionListener() {
////            @Override
////            public void actionPerformed(ActionEvent e) {
////                startClient();
////            }
////        });
////
////        JPanel panel = new JPanel();
////        panel.add(serverButton);
////        panel.add(clientButton);
////
////        getContentPane().add(panel, BorderLayout.CENTER);
////    }
////
////    private void startServer() {
////        Thread serverThread = new Thread(() -> {
////            try {
////                Server.main(null);
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
////        });
////        serverThread.setDaemon(true); // Đảm bảo rằng luồng server không ngăn JVM tắt
////        serverThread.start();
////    }
////
////    private void startClient() {
////        Thread clientThread = new Thread(() -> {
////            try {
////                Client.main(null);
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
////        });
////        clientThread.setDaemon(true); // Đảm bảo rằng luồng client không ngăn JVM tắt
////        clientThread.start();
////    }
////
////    public static void main(String[] args) {
////        ButtonChat frame = new ButtonChat();
////        frame.setVisible(true);
////    }
////}
//
//
//
//
//
//
//
//
////   Có chỉnh chút 
//package Chat;
//
//import java.awt.BorderLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//
//import client.Client;
//import server.Server;
//
//public class ButtonChat extends JFrame {
//    private JButton serverButton;
//    private JButton clientButton;
//
//    private Thread serverThread;
//    private boolean serverStarted; // Biến để kiểm tra xem server đã được khởi động chưa
//
//    private static int clientID = 0; // Biến để quản lý ID của client
//
//    public ButtonChat() {
//        super("Ứng dụng Chat");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Khi đóng cửa sổ, ứng dụng sẽ tắt hoàn toàn
//        setSize(300, 200);
//        setLocationRelativeTo(null);
//        
//        serverButton = new JButton("Khởi động Server");
//        clientButton = new JButton("Khởi động Client");
//
//        serverStarted = false;
//
//        serverButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (!serverStarted) {
//                    startServer();
//                    serverStarted = true; // Đánh dấu là server đã được khởi động
//                    serverButton.setEnabled(false); // Vô hiệu hóa nút Khởi động Server
//                } else {
//                    JOptionPane.showMessageDialog(ButtonChat.this, "Server đã được khởi động.");
//                }
//            }
//        });
//
//        clientButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                startClient();
//            }
//        });
//
//        JPanel panel = new JPanel();
//        panel.add(serverButton);
//        panel.add(clientButton);
//
//        getContentPane().add(panel, BorderLayout.CENTER);
//    }
//
//    private void startServer() {
//        serverThread = new Thread(() -> {
//            try {
//                Server a = new Server(); // Gọi phương thức startServer từ lớp Server
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//        serverThread.setDaemon(true); // Đảm bảo rằng luồng server không ngăn JVM tắt
//        serverThread.start();
//    }
//
//    private void startClient() {
//        int currentClientID = ++clientID; // Tăng ID client lên và lưu vào biến
//        Thread clientThread = new Thread(() -> {
//            try {
//                Client a = new Client(currentClientID); // Truyền ID vào client khi khởi tạo
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//        clientThread.setDaemon(true); // Đảm bảo rằng luồng client không ngăn JVM tắt
//        clientThread.start();
//    }
//
//    public static void main(String[] args) {
//        ButtonChat frame = new ButtonChat();
//        frame.setVisible(true);
//    }
//}
//














//
//package Chat;   SAI NHÉ LỖI CLIENT
//
//import java.awt.BorderLayout;
//import java.awt.GridLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JTextArea;
//import javax.swing.SwingUtilities;
//import client.Client;
//import server.Server;
//
//public class ButtonChat extends JFrame {
//    private JButton serverButton;
//    private JButton clientButton;
//    private JLabel serverStatusLabel;
//    private JLabel clientStatusLabel;
//    private JTextArea logArea;
//
//    private Thread serverThread;
//    private boolean serverStarted;
//    private static int clientID = 0;
//
//    public ButtonChat() {
//        super("Ứng dụng Chat");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(400, 300);
//        setLocationRelativeTo(null);
//
//        serverButton = new JButton("Khởi động Server");
//        clientButton = new JButton("Khởi động Client");
//        serverStatusLabel = new JLabel("Server Status: Stopped");
//        clientStatusLabel = new JLabel("Client Status: No clients connected");
//        logArea = new JTextArea(10, 30);
//        logArea.setEditable(false);
//
//        serverStarted = false;
//
//        serverButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (!serverStarted) {
//                    startServer();
//                    serverStarted = true;
//                    serverButton.setEnabled(false);
//                    serverStatusLabel.setText("Server Status: Running");
//                } else {
//                    JOptionPane.showMessageDialog(ButtonChat.this, "Server đã được khởi động.");
//                }
//            }
//        });
//
//        clientButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                startClient();
//            }
//        });
//
//        JPanel panel = new JPanel(new GridLayout(3, 1));
//        JPanel buttonPanel = new JPanel();
//        buttonPanel.add(serverButton);
//        buttonPanel.add(clientButton);
//
//        panel.add(serverStatusLabel);
//        panel.add(clientStatusLabel);
//        panel.add(buttonPanel);
//
//        getContentPane().add(panel, BorderLayout.NORTH);
//        getContentPane().add(logArea, BorderLayout.CENTER);
//    }
//
//    private void startServer() {
//        serverThread = new Thread(() -> {
//            try {
//                Server a = new Server();
//                log("Server started.");
//            } catch (Exception e) {
//                log("Error starting server: " + e.getMessage());
//                e.printStackTrace();
//            }
//        });
//        serverThread.setDaemon(true);
//        serverThread.start();
//    }
//
//    private void startClient() {
//        int currentClientID = ++clientID;
//        Thread clientThread = new Thread(() -> {
//            try {
//                Client a = new Client(currentClientID);
//                log("Client " + currentClientID + " connected.");
//                SwingUtilities.invokeLater(() -> clientStatusLabel.setText("Client Status: " + clientID + " clients connected"));
//            } catch (Exception e) {
//                log("Error starting client: " + e.getMessage());
//                e.printStackTrace();
//            }
//        });
//        clientThread.setDaemon(true);
//        clientThread.start();
//    }
//
//    private void log(String message) {
//        SwingUtilities.invokeLater(() -> {
//            logArea.append(message + "\n");
//            logArea.setCaretPosition(logArea.getDocument().getLength());
//        });
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            ButtonChat frame = new ButtonChat();
//            frame.setVisible(true);
//        });
//    }
//}






//
//   Chạy rất ok 
//
//package Chat;
//
//import java.awt.BorderLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//
//import client.Client;
//import server.Server;
//
//public class ButtonChat extends JFrame {
//    private JButton serverButton;
//    private JButton clientButton;
//
//    private Thread serverThread;
//    private boolean serverStarted; // Biến để kiểm tra xem server đã được khởi động chưa
//
//    private static int clientID = 0; // Biến để quản lý ID của client
//
//    public ButtonChat() {
//        super("Ứng dụng Chat");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Khi đóng cửa sổ, ứng dụng sẽ tắt hoàn toàn
//        setSize(300, 200);
//        setLocationRelativeTo(null);
//
//        serverButton = new JButton("Khởi động Server");
//        clientButton = new JButton("Khởi động Client");
//
//        serverStarted = false;
//
//        serverButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (!serverStarted) {
//                    startServer();
//                    serverStarted = true; // Đánh dấu là server đã được khởi động
//                    serverButton.setEnabled(false); // Vô hiệu hóa nút Khởi động Server
//                } else {
//                    JOptionPane.showMessageDialog(ButtonChat.this, "Server đã được khởi động.");
//                }
//            }
//        });
//
//        clientButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                startClient();
//            }
//        });
//
//        JPanel panel = new JPanel();
//        panel.add(serverButton);
//        panel.add(clientButton);
//
//        getContentPane().add(panel, BorderLayout.CENTER);
//    }
//
//    private void startServer() {
//        serverThread = new Thread(() -> {
//            try {
//                Server.startServer(); // Gọi phương thức startServer từ lớp Server
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//        serverThread.setDaemon(true); // Đảm bảo rằng luồng server không ngăn JVM tắt
//        serverThread.start();
//    }
//
//    private void startClient() {
//        int currentClientID = ++clientID; // Tăng ID client lên và lưu vào biến
//        Thread clientThread = new Thread(() -> {
//            try {
//                Client a = new Client(currentClientID); // Truyền ID vào client khi khởi tạo
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//        clientThread.setDaemon(true); // Đảm bảo rằng luồng client không ngăn JVM tắt
//        clientThread.start();
//    }
//
//    public static void main(String[] args) {
//        ButtonChat frame = new ButtonChat();
//        frame.setVisible(true);
//    }
//}
//


package Chat;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import server.Server;

public class ButtonChat extends JFrame {
    private JButton serverButton;
    private JButton clientButton;

    private Thread serverThread;
    private boolean serverStarted; // Biến để kiểm tra xem server đã được khởi động chưa

    public ButtonChat() {
        super("Ứng dụng Chat");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Khi đóng cửa sổ, ứng dụng sẽ tắt hoàn toàn
        setSize(300, 200);
        setLocationRelativeTo(null);

        serverButton = new JButton("Khởi động Server");
        clientButton = new JButton("Khởi động Client");

        serverStarted = false;

        serverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!serverStarted) {
                    startServer();
                    serverStarted = true; // Đánh dấu là server đã được khởi động
                    serverButton.setEnabled(false); // Vô hiệu hóa nút Khởi động Server
                } else {
                    JOptionPane.showMessageDialog(ButtonChat.this, "Server đã được khởi động.");
                }
            }
        });

        clientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientStarter.startClient();
            }
        });

        JPanel panel = new JPanel();
        panel.add(serverButton);
        panel.add(clientButton);

        getContentPane().add(panel, BorderLayout.CENTER);
    }

    private void startServer() {
        serverThread = new Thread(() -> {
            try {
                Server.startServer(); // Gọi phương thức startServer từ lớp Server
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        serverThread.setDaemon(true); // Đảm bảo rằng luồng server không ngăn JVM tắt
        serverThread.start();
    }

    public static void main(String[] args) {
        ButtonChat frame = new ButtonChat();
        frame.setVisible(true);
    }
}
