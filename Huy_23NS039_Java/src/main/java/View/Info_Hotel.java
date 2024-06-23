//package View;
//
//import java.awt.Frame;
//
//public class Info_Hotel extends Frame {
//	public Info_Hotel() {
//	} // mới dùng được windowbuider //
//
//}
package View;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.net.URI;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class Info_Hotel extends JFrame {

    private JLabel hotelInfoLabel;
    private JLabel youtubeIcon;
    private JLabel facebookIcon;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel_2;
    private JLabel lblNewLabel_3;
    
    private JLabel websiteIcon; // New icon for the website
    private JLabel Map;  // thay  lblNewLabel_4 = Map nhé //
    private JLabel lblNewLabel_4;
    private JTextPane txtpnKhchSnHuy;
    private JLabel lblNewLabel_5;

    public Info_Hotel() {
        setTitle("Hotel Information");
        setSize(1072, 758);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Hotel information label
        hotelInfoLabel = new JLabel("Information about the hotel Huy Nguyen");
        hotelInfoLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        hotelInfoLabel.setBounds(656, 26, 340, 29);
        hotelInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(hotelInfoLabel);

        // Add panel to frame
        getContentPane().add(panel);
        
                // YouTube icon
                youtubeIcon = new JLabel(new ImageIcon("D:\\4375133_logo_youtube_icon (1).png"));
                youtubeIcon.setBounds(654, 492, 48, 48);
                panel.add(youtubeIcon);
                youtubeIcon.setToolTipText("Visit our YouTube channel");
                youtubeIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
                
                        // Facebook icon
                        facebookIcon = new JLabel(new ImageIcon("D:\\5296499_fb_facebook_facebook logo_icon.png"));
                        facebookIcon.setBounds(734, 492, 48, 48);
                        panel.add(facebookIcon);
                        facebookIcon.setToolTipText("Visit our Facebook page");
                        facebookIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
                        
                        lblNewLabel = new JLabel("New label");
                        lblNewLabel.setIcon(new ImageIcon("D:\\Chứa dự án nộp bài tập_đồ án _ 23NS039_vâng vâng\\23NS039.jpg"));
                        lblNewLabel.setBounds(0, 0, 570, 721);
                        panel.add(lblNewLabel);
                        
                        lblNewLabel_1 = new JLabel("©Copyright CEO Quang Huy. All Rights Reserved");
                        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
                        lblNewLabel_1.setBounds(654, 621, 342, 29);
                        panel.add(lblNewLabel_1);
                        
                        lblNewLabel_2 = new JLabel("Design By CEO Quang Huy");
                        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
                        lblNewLabel_2.setBounds(721, 592, 199, 19);
                        panel.add(lblNewLabel_2);
                        
                        // Đưa vào web _ cách 1 

                        // Web icon
                        lblNewLabel_3 = new JLabel("");
                        lblNewLabel_3.setIcon(new ImageIcon("D:\\4417110_builder_building site_crane_website_computer_icon.png"));
                        lblNewLabel_3.setBounds(804, 492, 48, 48);
                        lblNewLabel_3.setToolTipText("Visit our Website");
                        lblNewLabel_3.setCursor(new Cursor(Cursor.HAND_CURSOR));
                        lblNewLabel_3.addMouseListener(new IconClickListener("file:///D:/Travel%20Web/index.html")); // Local HTML file
                        panel.add(lblNewLabel_3);
                        
                        Map = new JLabel("");
                        Map.setIcon(new ImageIcon("D:\\299087_marker_map_icon.png"));
                        Map.setBounds(862, 483, 55, 57);
                        Map.setToolTipText("Location Hotel");
                        Map.setCursor(new Cursor(Cursor.HAND_CURSOR));
                        Map.addMouseListener(new IconClickListener("https://maps.app.goo.gl/aTw9RhTPcpoAUhHi6"));
                        panel.add(Map);
                        
                        lblNewLabel_4 = new JLabel("");
                        lblNewLabel_4.setIcon(new ImageIcon("D:\\1298754_pinterest_icon.png"));
                        lblNewLabel_4.setBounds(936, 483, 60, 57);
                        lblNewLabel_4.setToolTipText("Hotel Beautiful");
                        lblNewLabel_4.setCursor(new Cursor(Cursor.HAND_CURSOR));
                        lblNewLabel_4.addMouseListener(new IconClickListener("https://www.pinterest.com/search/pins/?rs=ac&len=2&q=hotel%20beautiful&eq=Hotel%20beaut&etslf=9973"));
                        panel.add(lblNewLabel_4);
                        
                        JTextPane txtpnThngTinV = new JTextPane();
                        txtpnThngTinV.setBackground(new Color(208, 255, 255));
                        txtpnThngTinV.setText("\"Khách sạn Huy Nguyễn - Đích đến lý tưởng cho những chuyến du lịch và kỳ nghỉ của bạn. Được thành lập bởi CEO Nguyễn Quang Huy, một doanh nhân trẻ tuổi với tâm huyết và tầm nhìn sâu rộng, khách sạn mang đến cho du khách một trải nghiệm lưu trú đẳng cấp và tiện nghi.");
                        txtpnThngTinV.setBounds(656, 186, 340, 69);
                        panel.add(txtpnThngTinV);
                        
                        JTextPane txtpnViMcTiu = new JTextPane();
                        txtpnViMcTiu.setBackground(new Color(208, 255, 255));
                        txtpnViMcTiu.setText("Với mục tiêu mang đến không gian nghỉ ngơi thoải mái và dịch vụ chuyên nghiệp, khách sạn Huy Nguyễn không ngừng nâng cao chất lượng và đa dạng hóa các dịch vụ để đáp ứng mọi nhu cầu của khách hàng.");
                        txtpnViMcTiu.setBounds(656, 254, 340, 69);
                        panel.add(txtpnViMcTiu);
                        
                        JTextPane txtpnCeoNguynQuang = new JTextPane();
                        txtpnCeoNguynQuang.setBackground(new Color(208, 255, 255));
                        txtpnCeoNguynQuang.setText("CEO Nguyễn Quang Huy, dù chỉ mới 19 tuổi, nhưng đã thể hiện tầm nhìn và sự quyết đoán trong việc xây dựng và phát triển khách sạn. Với sự tận tâm và nỗ lực không ngừng, ông đã đưa khách sạn Huy Nguyễn trở thành một trong những điểm đến hàng đầu trong ngành du lịch và nghỉ dưỡng.\n");
                        txtpnCeoNguynQuang.setBounds(656, 321, 340, 91);
                        panel.add(txtpnCeoNguynQuang);
                        
                        txtpnKhchSnHuy = new JTextPane();
                        txtpnKhchSnHuy.setBackground(new Color(208, 255, 255));
                        txtpnKhchSnHuy.setText("Khách sạn Huy Nguyễn cam kết mang lại trải nghiệm lưu trú tuyệt vời nhất cho quý khách hàng, với không gian sang trọng, tiện nghi hiện đại và dịch vụ chu đáo từ đội ngũ nhân viên chuyên nghiệp và nhiệt tình.\"");
                        txtpnKhchSnHuy.setBounds(656, 406, 340, 69);
                        panel.add(txtpnKhchSnHuy);
                        
                        lblNewLabel_5 = new JLabel("");
                        lblNewLabel_5.setIcon(new ImageIcon("D:\\hinh-nen-dien-thoai-mat-nuoc-trong-suot-4-20-14-05-22.jpg"));
                        lblNewLabel_5.setBounds(567, 0, 491, 721);
                        panel.add(lblNewLabel_5);
                      
                        
                        // Website icon  _ Cachs 2 // 
//                        websiteIcon = new JLabel(new ImageIcon("D:\\path_to_website_icon.png")); // Update the path to your website icon
//                        websiteIcon.setBounds(656, 554, 48, 48);
//                        websiteIcon.setToolTipText("Visit our Website");
//                        websiteIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
//                        websiteIcon.addMouseListener(new IconClickListener("file:///D:/Travel%20Web/index.html")); // Local HTML file
//                        panel.add(websiteIcon);
                        
                        
                   // phần gắn sự kiện      
                facebookIcon.addMouseListener(new IconClickListener("https://www.facebook.com"));
                youtubeIcon.addMouseListener(new IconClickListener("https://www.youtube.com/@thiennhienofficial6413"));

        setVisible(true);
    }

    private class IconClickListener extends java.awt.event.MouseAdapter {
        String url;

        public IconClickListener(String url) {
            this.url = url;
        }

        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Info_Hotel());
    }
}

