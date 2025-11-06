import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LatihanPraktikum9 extends JFrame {
    private final List<Mahasiswa> mahasiswaList = new ArrayList<>();

    // === Model Data ===
    class Mahasiswa {
        private String nim, nama, prodi, kelamin;
        private boolean aktif;

        public Mahasiswa(String nim, String nama, String prodi, String kelamin, boolean aktif) {
            this.nim = nim;
            this.nama = nama;
            this.prodi = prodi;
            this.kelamin = kelamin;
            this.aktif = aktif;
        }

        public String getNim() { return nim; }
        public String getNama() { return nama; }
        public String getProdi() { return prodi; }
        public String getKelamin() { return kelamin; }
        public boolean isAktif() { return aktif; }

        public void setNama(String nama) { this.nama = nama; }
        public void setProdi(String prodi) { this.prodi = prodi; }
        public void setKelamin(String kelamin) { this.kelamin = kelamin; }
        public void setAktif(boolean aktif) { this.aktif = aktif; }
    }

    // === Komponen UI ===
    private JTextField tfNIM, tfNama;
    private JComboBox<String> cbProdi;
    private JRadioButton rbLaki, rbPerempuan;
    private JCheckBox chkAktif;
    private JLabel lblTitle;
    private JButton btnKirim;
    private JTable table;
    private DefaultTableModel tableModel;
    private final JPanel contentPanel = new JPanel(new CardLayout());

    // === Variabel kontrol ===
    private boolean isUpdateMode = false;
    private Mahasiswa mhsDipilih = null;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LatihanPraktikum9().setVisible(true));
    }

    public LatihanPraktikum9() {
        setTitle("Aplikasi Data Mahasiswa");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(850, 520);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(initSidebar(), BorderLayout.WEST);
        contentPanel.add(initPanelTambah(), "FORM");
        contentPanel.add(initPanelLihat(), "TABEL");
        add(contentPanel, BorderLayout.CENTER);
        showCard("FORM");
    }

    private JPanel initSidebar() {
        JPanel sidebar = new JPanel(new GridLayout(0, 1, 20, 20));
        sidebar.setPreferredSize(new Dimension(220, 0));
        sidebar.setBackground(new Color(245, 247, 252));
        sidebar.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));

        JButton btnTambah = createButton("Tambah Data", new Color(63, 81, 181));
        JButton btnLihat = createButton("Lihat Data", new Color(0, 150, 136));

        btnTambah.addActionListener(e -> {
            clearForm();
            showCard("FORM");
        });

        btnLihat.addActionListener(e -> {
            refreshTable();
            showCard("TABEL");
        });

        sidebar.add(btnTambah);
        sidebar.add(btnLihat);
        return sidebar;
    }

    // 🔹 createButton (pengganti createLargeButton)
    private JButton createButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color.darker(), 2, true),
                BorderFactory.createEmptyBorder(15, 25, 15, 25)
        ));
        btn.setPreferredSize(new Dimension(180, 65));
        btn.setHorizontalAlignment(SwingConstants.CENTER);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        return btn;
    }
    // === Panel Form Tambah/Update ===
    private JPanel initPanelTambah() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        panel.setBackground(Color.WHITE);

        lblTitle = new JLabel("Tambah Data Mahasiswa", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(new Color(40, 53, 147));
        panel.add(lblTitle, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        tfNama = new JTextField(20);
        tfNIM = new JTextField(20);
        cbProdi = new JComboBox<>(new String[]{
                "-- Pilih Prodi --",
                "S1 Teknik Informatika",
                "S1 Teknik Elektro",
                "D3 Teknik Elektro"
        });
        rbLaki = new JRadioButton("Laki-laki");
        rbPerempuan = new JRadioButton("Perempuan");
        ButtonGroup bgKelamin = new ButtonGroup();
        bgKelamin.add(rbLaki);
        bgKelamin.add(rbPerempuan);
        JPanel kelaminPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        kelaminPanel.setBackground(Color.WHITE);
        kelaminPanel.add(rbLaki);
        kelaminPanel.add(rbPerempuan);

        chkAktif = new JCheckBox("Aktif");
        chkAktif.setBackground(Color.WHITE);

        btnKirim = new JButton("Kirim");
        btnKirim.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnKirim.setBackground(new Color(63, 81, 181));
        btnKirim.setForeground(Color.WHITE);
        btnKirim.setFocusPainted(false);
        btnKirim.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        btnKirim.addActionListener(e -> simpanData());

        int y = 0;
        addField(form, gbc, y++, "Nama:", tfNama);
        addField(form, gbc, y++, "NIM:", tfNIM);
        addField(form, gbc, y++, "Prodi:", cbProdi);
        addField(form, gbc, y++, "Jenis Kelamin:", kelaminPanel);
        addField(form, gbc, y++, "Status:", chkAktif);

        gbc.gridx = 1; gbc.gridy = y; gbc.anchor = GridBagConstraints.CENTER;
        form.add(btnKirim, gbc);

        panel.add(form, BorderLayout.CENTER);
        return panel;
    }

    private void addField(JPanel panel, GridBagConstraints gbc, int y, String label, Component input) {
        gbc.gridx = 0; gbc.gridy = y; gbc.anchor = GridBagConstraints.WEST;
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(lbl, gbc);
        gbc.gridx = 1; panel.add(input, gbc);
    }

    // === Panel Tabel Lihat Data ===
    private JPanel initPanelLihat() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.WHITE);

        tableModel = new DefaultTableModel(new Object[]{"Nama", "NIM", "Prodi", "Kelamin", "Status"}, 0) {
            public boolean isCellEditable(int row, int col) { return false; }
        };
        table = new JTable(tableModel);
        table.setRowHeight(25);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.setBackground(Color.WHITE);
        JButton btnUpdate = new JButton("Update");
        JButton btnHapus = new JButton("Hapus");

        styleSmallButton(btnUpdate, new Color(0, 150, 136));
        styleSmallButton(btnHapus, new Color(211, 47, 47));

        btnUpdate.addActionListener(e -> loadDataUntukUpdate());
        btnHapus.addActionListener(e -> hapusBaris());

        bottom.add(btnUpdate);
        bottom.add(btnHapus);
        panel.add(bottom, BorderLayout.SOUTH);

        return panel;
    }

    private void styleSmallButton(JButton btn, Color color) {
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    private void simpanData() {
        String nim = tfNIM.getText().trim();
        String nama = tfNama.getText().trim();
        String prodi = (String) cbProdi.getSelectedItem();
        String kelamin = rbLaki.isSelected() ? "Laki-laki" : rbPerempuan.isSelected() ? "Perempuan" : "";
        boolean aktif = chkAktif.isSelected();

        if (nim.isEmpty() || nama.isEmpty() || prodi.equals("-- Pilih Prodi --") || kelamin.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field wajib diisi.", "Validasi", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!nim.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "NIM harus berupa angka.", "Validasi", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (isUpdateMode) {
            mhsDipilih.setNama(nama);
            mhsDipilih.setProdi(prodi);
            mhsDipilih.setKelamin(kelamin);
            mhsDipilih.setAktif(aktif);
            JOptionPane.showMessageDialog(this, "Data berhasil diperbarui.");
        } else {
            for (Mahasiswa m : mahasiswaList)
                if (m.getNim().equals(nim)) {
                    JOptionPane.showMessageDialog(this, "NIM sudah terdaftar.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            mahasiswaList.add(new Mahasiswa(nim, nama, prodi, kelamin, aktif));
            JOptionPane.showMessageDialog(this, "Data berhasil disimpan.");
        }
        clearForm();
    }

    private void clearForm() {
        tfNIM.setText("");
        tfNama.setText("");
        cbProdi.setSelectedIndex(0);
        rbLaki.setSelected(false);
        rbPerempuan.setSelected(false);
        chkAktif.setSelected(false);
        isUpdateMode = false;
        mhsDipilih = null;
        tfNIM.setEditable(true);
        btnKirim.setText("Kirim");
        lblTitle.setText("Tambah Data Mahasiswa");
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        mahasiswaList.forEach(m -> tableModel.addRow(new Object[]{
                m.getNama(), m.getNim(), m.getProdi(), m.getKelamin(), m.isAktif() ? "Aktif" : "Tidak Aktif"
        }));
    }

    private void loadDataUntukUpdate() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih baris terlebih dahulu.");
            return;
        }

        String nim = (String) tableModel.getValueAt(row, 1);
        mhsDipilih = mahasiswaList.stream()
                .filter(m -> m.getNim().equals(nim))
                .findFirst().orElse(null);

        if (mhsDipilih != null) {
            isUpdateMode = true;
            tfNIM.setText(mhsDipilih.getNim());
            tfNIM.setEditable(false);
            tfNama.setText(mhsDipilih.getNama());
            cbProdi.setSelectedItem(mhsDipilih.getProdi());
            if (mhsDipilih.getKelamin().equals("Laki-laki")) rbLaki.setSelected(true);
            else rbPerempuan.setSelected(true);
            chkAktif.setSelected(mhsDipilih.isAktif());
            btnKirim.setText("Update");
            lblTitle.setText("Update Data Mahasiswa");
            showCard("FORM");
        }
    }

    private void hapusBaris() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih baris terlebih dahulu.");
            return;
        }

        int konfirmasi = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus data ini?",
                "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (konfirmasi != JOptionPane.YES_OPTION) return;

        String nim = (String) tableModel.getValueAt(row, 1);
        mahasiswaList.removeIf(m -> m.getNim().equals(nim));
        refreshTable();
    }

    private void showCard(String name) {
        ((CardLayout) contentPanel.getLayout()).show(contentPanel, name);
    }
}
