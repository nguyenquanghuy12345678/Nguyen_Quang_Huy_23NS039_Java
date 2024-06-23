package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import DAO.DoanhThu;
import DAO.DoanhThuDAO;

public class BieuDo_PhanTich extends JFrame {

    private DoanhThuDAO doanhThuDAO;

    public BieuDo_PhanTich() {
        doanhThuDAO = new DoanhThuDAO();

        setTitle("Biểu Đồ Doanh Thu");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel chartPanel = createChartPanel();
        add(chartPanel, BorderLayout.CENTER);
    }

    private JPanel createChartPanel() {
        String chartTitle = "Biểu Đồ Doanh Thu Theo Tháng";
        String categoryAxisLabel = "Tháng";
        String valueAxisLabel = "Doanh Thu ($)";

        DefaultCategoryDataset dataset = createDataset();
        JFreeChart chart = ChartFactory.createLineChart(
                chartTitle, categoryAxisLabel, valueAxisLabel,
                dataset, PlotOrientation.VERTICAL, true, true, false);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(700, 500));
        return chartPanel;
    }

    private DefaultCategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        List<DoanhThu> doanhThuList = doanhThuDAO.getAllDoanhThu();

        if (doanhThuList != null) {
            for (DoanhThu doanhThu : doanhThuList) {
                Timestamp createdAt = doanhThu.getCreatedAt();
                if (createdAt != null) {
                    String month = String.valueOf(createdAt.getMonth() + 1); // Lấy tháng từ trường created_at
                    BigDecimal revenue = doanhThu.getTongTien();

                    if (revenue != null) { // Kiểm tra revenue không null trước khi sử dụng
                        dataset.addValue(revenue.doubleValue(), "Doanh Thu", month);
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Lỗi khi truy vấn cơ sở dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

        return dataset;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BieuDo_PhanTich doanhThuChart = new BieuDo_PhanTich();
            doanhThuChart.setVisible(true);
        });
    }
}
