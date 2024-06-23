package Chat;

import client.Client;

import javax.swing.*;
public class ClientStarter {
    private static int clientID = 0; // Biến để quản lý ID của client

    public static void startClient() {
        int currentClientID = ++clientID; // Tăng ID client lên và lưu vào biến
        Thread clientThread = new Thread(() -> {
            try {
                Client a = new Client(currentClientID); // Truyền ID vào client khi khởi tạo
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        clientThread.setDaemon(true); // Đảm bảo rằng luồng client không ngăn JVM tắt
        clientThread.start();
    }
}