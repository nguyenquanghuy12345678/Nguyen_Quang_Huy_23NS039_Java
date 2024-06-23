package Server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class ServerGUI extends JFrame {
    private static final int PORT = 12345;
    private JTextArea logTextArea;
    private JLabel userInfoLabel;

    public ServerGUI() {
        setTitle("Login Server");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 400);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setLayout(new BorderLayout());

        // Panel for displaying server status
        JPanel statusPanel = new JPanel(new BorderLayout());
        statusPanel.setPreferredSize(new Dimension(0, 50));
        statusPanel.setBackground(Color.WHITE);
        JLabel statusLabel = new JLabel("Server is running on port " + PORT);
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusPanel.add(statusLabel, BorderLayout.CENTER);
        panel.add(statusPanel, BorderLayout.NORTH);

        // Text area for logging server events
        logTextArea = new JTextArea();
        logTextArea.setEditable(false);
        logTextArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(logTextArea);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("D:\\3763170_access_connection_internet_sever_universal_icon.png"));
        scrollPane.setRowHeaderView(lblNewLabel);

        // Panel for user info
        JPanel userInfoPanel = new JPanel(new BorderLayout());
        userInfoPanel.setBorder(BorderFactory.createTitledBorder("User Info"));
        userInfoLabel = new JLabel("No user logged in");
        userInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        userInfoPanel.add(userInfoLabel, BorderLayout.CENTER);
        panel.add(userInfoPanel, BorderLayout.SOUTH);

        getContentPane().add(panel, BorderLayout.CENTER);
        setVisible(true);

        startServer(statusLabel);
    }

    private void startServer(JLabel statusLabel) {
        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(PORT);
                appendToLog("Server started on port " + PORT);

                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    appendToLog("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                    // Start a new thread to handle client communication
                    LoginServer.ClientHandler clientHandler = new LoginServer.ClientHandler(clientSocket);
                    clientHandler.setUserLoggedInListener(username -> updateUserInfo(username));
                    clientHandler.start();
                }
            } catch (IOException e) {
                e.printStackTrace();
                statusLabel.setText("Error: Server could not start on port " + PORT);
            }
        }).start();
    }

    private void updateUserInfo(String username) {
        SwingUtilities.invokeLater(() -> {
            userInfoLabel.setText("Logged in user: " + username);
        });
    }

    private void appendToLog(String message) {
        SwingUtilities.invokeLater(() -> {
            logTextArea.append(message + "\n");
            logTextArea.setCaretPosition(logTextArea.getDocument().getLength());
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ServerGUI::new);
    }
}
