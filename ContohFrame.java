import javax.swing.JFrame;
public class ContohFrame {
        public static void main(String args) {
        // Membuat JFrame
        JFrame frame = new JFrame("Judul Window");
        // Mengatur ukuran (lebar, tinggi)
        frame.setSize(400, 300);
        // Mengatur operasi saat window ditutup
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        // Membuat frame terlihat
        frame.setVisible(true);
        // Opsional: menempatkan frame di tengah layar
        frame.setLocationRelativeTo(null);
    }
}