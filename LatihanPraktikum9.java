import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class LatihanPraktikum9 extends JFrame {
    private Set<Mahasiswa> mahasiswaSet = new LinkedHashSet<>(); // Set untuk menyimpan data mahasiswa
    class Mahasiswa {
        private String nim, nama, prodi, kelamin;
        private boolean isActive;
        public Mahasiswa(String nim, String nama, String prodi, String kelamin, boolean isActive) {
            this.nim = nim;
            this.nama = nama;
            this.prodi = prodi;
            this.kelamin = kelamin;
            this.isActive = isActive;
        }

        public String getNim(){ return nim;}
        public String getNama(){ return nama;}
        public String getProdi(){ return prodi;}
        public String getKelamin(){ return kelamin;}
        public boolean isActive(){ return isActive;}

        public void setNim(String nim) { this.nim = nim; }
        public void setNama(String nama) { this.nama = nama; }
        public void setProdi(String prodi) { this.prodi = prodi; }
        public void setKelamin(String kelamin) { this.kelamin = kelamin; }
        public void setActive(boolean active) { isActive = active; }
        // Override equals dan hashCode berdasarkan NIM
        @Override 
        public boolean equals(Object o) { 
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Mahasiswa mahasiswa = (Mahasiswa) o;
            return Objects.equals(nim, mahasiswa.nim);
        }
        // Override hashCode berdasarkan NIM
        @Override
        public int hashCode() { 
            return Objects.hash(nim);
        }
    }

    // deklarasi warna kustom
    private static final Color WARNA_PRIMER = new Color(52, 152, 219);
    private static final Color WARNA_SEKUNDER = new Color(46, 204, 113);
    private static final Color WARNA_BAHAYA = new Color(231, 76, 60);
    private static final Color WARNA_TEKS_PUTIH = Color.WHITE;
    private static final Color WARNA_GARIS = new Color(220, 220, 220);
    private static final Color WARNA_SIDEBAR_BG = new Color(44, 62, 80);
    private static final Color WARNA_SIDEBAR_HOVER = new Color(52, 73, 94);
    private static final Color WARNA_SIDEBAR_AKTIF = new Color(WARNA_PRIMER.getRed(), 
    WARNA_PRIMER.getGreen(), WARNA_PRIMER.getBlue(), 180);
    private static final Color WARNA_SIDEBAR_TEKS = Color.WHITE;

    // deklarasi komponen GUI
    private JPanel sidebar, contentPanel;
    private JButton btnTambah, btnLihat;
    private JTextField tfNIM, tfNama;
    private JComboBox<String> cbProdi;
    private JRadioButton rbLaki, rbPerempuan;
    private ButtonGroup bgKelamin;
    private JCheckBox chkAktif;
    private JTable table;
    private DefaultTableModel tableModel;
    private JLabel lblTitle;
    private JButton btnKirim;
    private boolean isUpdateMode = false;
    private Mahasiswa mhsDipilih = null;

    // Konstruktor utama untuk menginisialisasi GUI
    public LatihanPraktikum9() { 
        setTitle("Aplikasi Data Mahasiswa");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        sidebar = new JPanel(new BorderLayout()); 
        sidebar.setPreferredSize(new Dimension(200, 0));
        sidebar.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        sidebar.setBackground(WARNA_SIDEBAR_BG);

        JPanel buttonContainer = new JPanel(new GridLayout(0, 1, 0, 8));
        buttonContainer.setOpaque(false);
        buttonContainer.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        btnTambah = new JButton("Tambah Data");
        btnLihat = new JButton("Lihat Data");
        styleSidebarButton(btnTambah);
        styleSidebarButton(btnLihat);
        buttonContainer.add(btnTambah);
        buttonContainer.add(btnLihat);
        
        sidebar.add(buttonContainer, BorderLayout.NORTH);
        add(sidebar, BorderLayout.WEST);

        contentPanel = new JPanel(new CardLayout());
        contentPanel.add(panelTambah(), "TAMBAH");
        contentPanel.add(panelLihat(), "LIHAT");
        add(contentPanel, BorderLayout.CENTER);

        btnTambah.addActionListener(e -> {
            clearForm();
            showCard("TAMBAH");
            setActiveButton(btnTambah);
        });
        
        btnLihat.addActionListener(e -> {
            refreshTable();
            showCard("LIHAT");
            setActiveButton(btnLihat);
        });
        
        showCard("TAMBAH");
        setActiveButton(btnTambah);
    }
    
    // Panel untuk tambah data
    private JPanel panelTambah() { 
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        lblTitle = new JLabel("Tambah Data Mahasiswa");
        lblTitle.setFont(new Font(lblTitle.getFont().getName(), Font.BOLD, 22));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        
        Border garisBawah = new MatteBorder(0, 0, 1, 0, WARNA_GARIS);
        Border paddingBawah = new EmptyBorder(0, 0, 15, 0);
        lblTitle.setBorder(BorderFactory.createCompoundBorder(garisBawah, paddingBawah));
        
        panel.add(lblTitle, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        JLabel lblNama = new JLabel("Nama:");
        lblNama.setFont(new Font(lblNama.getFont().getName(), Font.PLAIN, 14));
        JLabel lblNIM = new JLabel("NIM:");
        lblNIM.setFont(new Font(lblNIM.getFont().getName(), Font.PLAIN, 14));
        JLabel lblProdi = new JLabel("Prodi:");
        lblProdi.setFont(new Font(lblProdi.getFont().getName(), Font.PLAIN, 14));
        JLabel lblKelamin = new JLabel("Jenis Kelamin:");
        lblKelamin.setFont(new Font(lblKelamin.getFont().getName(), Font.PLAIN, 14));
        JLabel lblStatus = new JLabel("Status:");
        lblStatus.setFont(new Font(lblStatus.getFont().getName(), Font.PLAIN, 14));

        tfNIM = new JTextField(25);
        tfNIM.setPreferredSize(new Dimension(tfNIM.getPreferredSize().width, 30));
        tfNama = new JTextField(25);
        tfNama.setPreferredSize(new Dimension(tfNama.getPreferredSize().width, 30));
        cbProdi = new JComboBox<>(new String[]{
                "--- Pilih Prodi ---",
                "S1 Teknik Informatika",
                "S1 Teknik Elektro",
                "D3 Teknik Elektro",
                "S1 Teknik Kehutanan"
        });
        cbProdi.setPreferredSize(new Dimension(cbProdi.getPreferredSize().width, 30));

        rbLaki = new JRadioButton("Laki Laki");
        rbPerempuan = new JRadioButton("Perempuan");
        bgKelamin = new ButtonGroup();
        bgKelamin.add(rbLaki);
        bgKelamin.add(rbPerempuan);
        JPanel kelaminPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        kelaminPanel.setOpaque(false);
        kelaminPanel.add(rbLaki);
        kelaminPanel.add(rbPerempuan);
        chkAktif = new JCheckBox("Aktif");
        
        btnKirim = new JButton("Kirim") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int width = getWidth();
                int height = getHeight();
                Color color1 = isUpdateMode ? WARNA_SEKUNDER.darker() : WARNA_PRIMER.darker();
                Color color2 = isUpdateMode ? WARNA_SEKUNDER.brighter() : WARNA_PRIMER.brighter();

                GradientPaint gp = new GradientPaint(0, 0, color1, 0, height, color2);
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, width, height, 15, 15);
                super.paintComponent(g);
                g2.dispose();
            }
        };
        btnKirim.setText("Kirim");
        btnKirim.setForeground(WARNA_TEKS_PUTIH);
        btnKirim.setFont(new Font(btnKirim.getFont().getName(), Font.BOLD, 14));
        btnKirim.setPreferredSize(new Dimension(120, 40));
        btnKirim.setFocusPainted(false);
        btnKirim.setBorderPainted(false);
        btnKirim.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnKirim.setContentAreaFilled(false);

        gbc.gridx = 0; gbc.gridy = 0; form.add(lblNama, gbc);
        gbc.gridx = 1; gbc.gridy = 0; form.add(tfNama, gbc);
        gbc.gridx = 0; gbc.gridy = 1; form.add(lblNIM, gbc);
        gbc.gridx = 1; gbc.gridy = 1; form.add(tfNIM, gbc);
        gbc.gridx = 0; gbc.gridy = 2; form.add(lblProdi, gbc);
        gbc.gridx = 1; gbc.gridy = 2; form.add(cbProdi, gbc);
        gbc.gridx = 0; gbc.gridy = 3; form.add(lblKelamin, gbc);
        gbc.gridx = 1; gbc.gridy = 3; form.add(kelaminPanel, gbc);
        gbc.gridx = 0; gbc.gridy = 4; form.add(lblStatus, gbc);
        gbc.gridx = 1; gbc.gridy = 4; form.add(chkAktif, gbc);
        gbc.gridx = 1; gbc.gridy = 5;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        form.add(btnKirim, gbc);

        panel.add(form, BorderLayout.CENTER);
        btnKirim.addActionListener(e -> simpanData());
        return panel;
    }

    // Panel untuk lihat data
    private JPanel panelLihat() {
        JPanel panel = new JPanel(new BorderLayout(15,15));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel lblTitleLihat = new JLabel("Daftar Data Mahasiswa");
        lblTitleLihat.setFont(new Font(lblTitleLihat.getFont().getName(), Font.BOLD, 22));
        lblTitleLihat.setHorizontalAlignment(SwingConstants.CENTER);
        Border garisBawah = new MatteBorder(0, 0, 1, 0, WARNA_GARIS);
        Border paddingBawah = new EmptyBorder(0, 0, 15, 0);
        lblTitleLihat.setBorder(BorderFactory.createCompoundBorder(garisBawah, paddingBawah));
        panel.add(lblTitleLihat, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"Nama", "NIM", "Prodi", "Jenis Kelamin", "Status"}, 0) {
            public boolean isCellEditable(int row, int column) { return false; }
        };
        table = new JTable(tableModel);
        
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font(table.getFont().getName(), Font.BOLD, 13));
        table.getTableHeader().setBackground(new Color(230, 230, 230));
        table.setSelectionBackground(WARNA_PRIMER.brighter());
        table.setSelectionForeground(WARNA_TEKS_PUTIH);

        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        bottom.setOpaque(false);
        
        JButton btnUpdate = new JButton("Update Baris Terpilih") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int width = getWidth();
                int height = getHeight();
                GradientPaint gp = new GradientPaint(0, 0, WARNA_SEKUNDER.darker(), 0, height, WARNA_SEKUNDER.brighter());
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, width, height, 15, 15);
                super.paintComponent(g);
                g2.dispose();
            }
        };
        btnUpdate.setText("Update Baris Terpilih");
        btnUpdate.setForeground(WARNA_TEKS_PUTIH);
        btnUpdate.setFont(new Font(btnUpdate.getFont().getName(), Font.BOLD, 12));
        btnUpdate.setPreferredSize(new Dimension(180, 35));
        btnUpdate.setFocusPainted(false);
        btnUpdate.setBorderPainted(false);
        btnUpdate.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnUpdate.setContentAreaFilled(false);

        bottom.add(btnUpdate);
        btnUpdate.addActionListener(e -> loadDataUntukUpdate());
        
        JButton btnHapus = new JButton("Hapus Baris Terpilih") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int width = getWidth();
                int height = getHeight();
                GradientPaint gp = new GradientPaint(0, 0, WARNA_BAHAYA.darker(), 0, height, WARNA_BAHAYA.brighter());
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, width, height, 15, 15);
                super.paintComponent(g);
                g2.dispose();
            }
        };
        btnHapus.setText("Hapus Baris Terpilih");
        btnHapus.setForeground(WARNA_TEKS_PUTIH);
        btnHapus.setFont(new Font(btnHapus.getFont().getName(), Font.BOLD, 12));
        btnHapus.setPreferredSize(new Dimension(180, 35));
        btnHapus.setFocusPainted(false);
        btnHapus.setBorderPainted(false);
        btnHapus.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnHapus.setContentAreaFilled(false);

        bottom.add(btnHapus);
        panel.add(bottom, BorderLayout.SOUTH);
        btnHapus.addActionListener(e -> hapusBaris());
        return panel;
    }

    // Method untuk menampilkan card tertentu
    private void showCard(String name) {
        CardLayout cl = (CardLayout) contentPanel.getLayout();
        cl.show(contentPanel, name);
    }

    // Method untuk simpan data
    private void simpanData() {
        String nim = tfNIM.getText().trim();
        String nama = tfNama.getText().trim();
        String prodi = (String) cbProdi.getSelectedItem();
        String kelamin = rbLaki.isSelected() ? "Laki-laki" : rbPerempuan.isSelected() ? "Perempuan" : "";
        boolean status = chkAktif.isSelected();

        if (nim.isEmpty() || nama.isEmpty() || prodi.equals("-- Pilih Prodi --") || kelamin.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field wajib diisi.");
            return;
        }
        if (!nim.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "NIM harus angka.");
            return;
        }

        if (isUpdateMode) {
            String nimLama = mhsDipilih.getNim();
            String nimBaru = nim; 

            if (!nimLama.equals(nimBaru)) {
                Mahasiswa tempCheck = new Mahasiswa(nimBaru, "", "", "", false);
                
                if (mahasiswaSet.contains(tempCheck)) {
                    JOptionPane.showMessageDialog(this, "NIM " + nimBaru + " sudah terdaftar.");
                    return; 
                }
            }

            mahasiswaSet.remove(mhsDipilih);  
            mhsDipilih.setNim(nimBaru); 
            mhsDipilih.setNama(nama);
            mhsDipilih.setProdi(prodi);
            mhsDipilih.setKelamin(kelamin);
            mhsDipilih.setActive(status);
            mahasiswaSet.add(mhsDipilih);
            
            JOptionPane.showMessageDialog(this, "Data berhasil di-update.");
            clearForm();  
        } else {
            Mahasiswa mhsBaru = new Mahasiswa(nim, nama, prodi, kelamin, status);
            
            if (mahasiswaSet.add(mhsBaru)) {
                JOptionPane.showMessageDialog(this, "Data berhasil disimpan.");
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "NIM " + nim + " sudah terdaftar.");
            }
        }
    }

    // Method untuk mengosongkan form
    private void clearForm() {
        tfNIM.setText("");
        tfNama.setText("");
        cbProdi.setSelectedIndex(0);
        bgKelamin.clearSelection();
        chkAktif.setSelected(false);
        tfNIM.requestFocus();
        
        isUpdateMode = false;
        mhsDipilih = null;
        tfNIM.setEditable(true);
        
        btnKirim.setText("Kirim");
        
        lblTitle.setText("Tambah Data Mahasiswa");
        btnKirim.repaint(); 
    }

    // Method untuk refresh tabel
    private void refreshTable() {
        tableModel.setRowCount(0); 
        for (Mahasiswa m : mahasiswaSet) { 
            tableModel.addRow(new Object[]{
                    m.getNama(),
                    m.getNim(),
                    m.getProdi(),
                    m.getKelamin(),
                    m.isActive() ? "Aktif" : "Tidak Aktif"
            });
        }
    }

    // Method untuk load data ke form untuk diupdate
    private void loadDataUntukUpdate() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih baris terlebih dahulu.");
            return;
        }
        String nim = (String) tableModel.getValueAt(row, 1);
        
        mhsDipilih = null;
        for (Mahasiswa m : mahasiswaSet) {
            if (m.getNim().equals(nim)) {
                mhsDipilih = m;
                break;
            }
        }
        
        if (mhsDipilih != null) {
            isUpdateMode = true;
            tfNIM.setText(mhsDipilih.getNim());
            
            tfNama.setText(mhsDipilih.getNama());
            cbProdi.setSelectedItem(mhsDipilih.getProdi());
            
            if (mhsDipilih.getKelamin().equals("Laki-laki")) {
                rbLaki.setSelected(true);
            } else {
                rbPerempuan.setSelected(true);
            }
            chkAktif.setSelected(mhsDipilih.isActive());
            
            lblTitle.setText("Update Data Mahasiswa");
            
            btnKirim.setText("Update");
            
            showCard("TAMBAH");
            btnKirim.repaint(); 
        }
    }

    // Method untuk hapus baris
    private void hapusBaris() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih baris terlebih dahulu.");
            return;
        }
        
        int opt = JOptionPane.showConfirmDialog(this, 
            "Yakin hapus data NIM " + tableModel.getValueAt(row, 1) + "?", 
            "Konfirmasi Hapus", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.WARNING_MESSAGE); 
        if (opt != JOptionPane.YES_OPTION) return;
        String nim = (String) tableModel.getValueAt(row, 1);
        mahasiswaSet.removeIf(m -> m.getNim().equals(nim));
        refreshTable();
    }

    // Method untuk mengatur gaya tombol sidebar
    private void styleSidebarButton(JButton btn) {
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        btn.setOpaque(true);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setBackground(WARNA_SIDEBAR_BG);
        btn.setForeground(WARNA_SIDEBAR_TEKS);
        btn.setFont(new Font(btn.getFont().getName(), Font.BOLD, 14));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                if (!btn.isContentAreaFilled()) {
                    btn.setBackground(WARNA_SIDEBAR_HOVER);
                }
            }

            public void mouseExited(MouseEvent evt) {
                if (!btn.isContentAreaFilled()) {
                    btn.setBackground(WARNA_SIDEBAR_BG);
                }
            }
        });
    }

    // Method untuk mengatur tombol aktif pada sidebar
    private void setActiveButton(JButton activeBtn) {
        btnTambah.setContentAreaFilled(false);
        btnTambah.setBackground(WARNA_SIDEBAR_BG);
        
        btnLihat.setContentAreaFilled(false);
        btnLihat.setBackground(WARNA_SIDEBAR_BG);

        activeBtn.setContentAreaFilled(true);
        activeBtn.setBackground(WARNA_SIDEBAR_AKTIF);
    }
    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Gagal menerapkan, tampilan menggunakan default.");
        }
        SwingUtilities.invokeLater(() -> new LatihanPraktikum9().setVisible(true)); 
    }
}
