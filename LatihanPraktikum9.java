package com.praktikum9;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LatihanPraktikum9 extends JFrame {
    private List<Mahasiswa> mahasiswaList = new ArrayList<>();

    class Mahasiswa {
        private String nim, nama, prodi, kelamin;
        private boolean isActive;

        // constructor
        public Mahasiswa(String nim, String nama, String prodi, String kelamin, boolean isActive) {
            this.nim = nim;
            this.nama = nama;
            this.prodi = prodi;
            this.kelamin = kelamin;
            this.isActive = isActive;
        }

        // Getter 
        public String getNim(){ 
            return nim;
        }
        public String getNama(){
             return nama;
            }
        public String getProdi(){ 
            return prodi;
        }
        public String getKelamin(){ 
            return kelamin;
        }
        public boolean isActive(){ 
            return isActive;
        }

        // Setter 
        public void setNama(String nama) { 
            this.nama = nama;
         }
        public void setProdi(String prodi) { 
            this.prodi = prodi; 
        }
        public void setKelamin(String kelamin) { 
            this.kelamin = kelamin; 
        }
        public void setActive(boolean active) { 
            isActive = active; 
        }
    }

    private JPanel sidebar, contentPanel;
    private JButton btnTambah, btnLihat;
    
    // Komponen form dipindahkan menjadi variabel instance 
    // Agar bisa diakses dari method lain (seperti clearForm dan loadDataUntukUpdate)
    private JTextField tfNIM, tfNama;
    private JComboBox<String> cbProdi;
    private JRadioButton rbLaki, rbPerempuan;
    private ButtonGroup bgKelamin;
    private JCheckBox chkAktif;
    private JTable table;
    private DefaultTableModel tableModel;
    private JLabel lblTitle; // Label judul di panel tambah
    private JButton btnKirim; // Tombol kirim di panel tambah
    
    // Variabel untuk status update 
    private boolean isUpdateMode = false;
    private Mahasiswa mhsDipilih = null; // Menyimpan objek mhs yang akan di-update


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LatihanPraktikum9().setVisible(true));
    }

    public LatihanPraktikum9() {
        setTitle("Aplikasi Data Mahasiswa");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        sidebar = new JPanel(new GridLayout(0, 1, 5, 5));
        sidebar.setPreferredSize(new Dimension(150, 0));
        btnTambah = new JButton("Tambah Data");
        btnLihat = new JButton("Lihat Data");
        sidebar.add(btnTambah);
        sidebar.add(btnLihat);
        add(sidebar, BorderLayout.WEST);
        contentPanel = new JPanel(new CardLayout());
        contentPanel.add(panelTambah(), "TAMBAH");
        contentPanel.add(panelLihat(), "LIHAT");
        add(contentPanel, BorderLayout.CENTER);

        //  Action listener btnTambah juga me-reset form 
        btnTambah.addActionListener(e -> {
            clearForm(); // Reset form jika user klik "Tambah Data" saat mode update
            showCard("TAMBAH");
        });
        
        btnLihat.addActionListener(e -> {
            refreshTable();
            showCard("LIHAT");
        });
        showCard("TAMBAH");
    }

    private JPanel panelTambah() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Komponen yang dideklarasi di atas, sekarang diinisialisasi di sini
        lblTitle = new JLabel("Tambah Data Mahasiswa");
        lblTitle.setFont(new Font(lblTitle.getFont().getName(), Font.BOLD, 18));
        panel.add(lblTitle, BorderLayout.NORTH);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        JLabel lblNama = new JLabel("Nama:");
        JLabel lblNIM = new JLabel("NIM:");
        JLabel lblProdi = new JLabel("Prodi:");
        JLabel lblKelamin = new JLabel("Jenis Kelamin:");
        JLabel lblStatus = new JLabel("Status:");

        tfNIM = new JTextField(20);
        tfNama = new JTextField(20);
        cbProdi = new JComboBox<>(new String[]{
                "-- Pilih Prodi --",
                "S1 Teknik Informatika",
                "S1 Teknik Elektro",
                "D3 Teknik Elektro",
                "S1 Teknik Kehutanan"
        });

        rbLaki = new JRadioButton("Laki Laki");
        rbPerempuan = new JRadioButton("Perempuan");
        bgKelamin = new ButtonGroup();
        bgKelamin.add(rbLaki);
        bgKelamin.add(rbPerempuan);
        JPanel kelaminPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        kelaminPanel.add(rbLaki);
        kelaminPanel.add(rbPerempuan);

        chkAktif = new JCheckBox("Aktif");

        btnKirim = new JButton("Kirim"); // Inisialisasi tombol

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
        form.add(btnKirim, gbc);

        panel.add(form, BorderLayout.CENTER);
        btnKirim.addActionListener(e -> simpanData());
        return panel;
    }


    private JPanel panelLihat() {
        JPanel panel = new JPanel(new BorderLayout(10,10));
        tableModel = new DefaultTableModel(new Object[]{"Nama", "NIM", "Prodi", "Jenis Kelamin", "Status"}, 0) {
            public boolean isCellEditable(int row, int column) { return false; }
        };
        table = new JTable(tableModel);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        // Tambah Tombol Update 
        JButton btnUpdate = new JButton("Update Baris Terpilih");
        bottom.add(btnUpdate);
        btnUpdate.addActionListener(e -> loadDataUntukUpdate());
        
        JButton btnHapus = new JButton("Hapus Baris Terpilih");
        bottom.add(btnHapus);
        panel.add(bottom, BorderLayout.SOUTH);
        btnHapus.addActionListener(e -> hapusBaris());
        return panel;
    }

    private void showCard(String name) {
        CardLayout cl = (CardLayout) contentPanel.getLayout();
        cl.show(contentPanel, name);
    }

    // Method simpanData() dipecah untuk mode Update dan Tambah 
    private void simpanData() {
        // Ambil data dari form
        String nim = tfNIM.getText().trim();
        String nama = tfNama.getText().trim();
        String prodi = (String) cbProdi.getSelectedItem();
        String kelamin = rbLaki.isSelected() ? "Laki-laki" : rbPerempuan.isSelected() ? "Perempuan" : "";
        boolean status = chkAktif.isSelected();

        // Validasi Input 
        if (nim.isEmpty() || nama.isEmpty() || prodi.equals("-- Pilih Prodi --") || kelamin.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field wajib diisi.");
            return;
        }
        if (!nim.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "NIM harus angka.");
            return;
        }

        // Update vs Tambah
        if (isUpdateMode) {
            
            // Update data pada objek mhsDipilih (yang sudah dipilih sebelumnya)
            mhsDipilih.setNama(nama);
            mhsDipilih.setProdi(prodi);
            mhsDipilih.setKelamin(kelamin);
            mhsDipilih.setActive(status);
            
            JOptionPane.showMessageDialog(this, "Data berhasil di-update.");
            clearForm(); // Reset form dan kembali ke mode tambah
            
        } else {
            
            // Validasi Duplikat NIM 
            for (Mahasiswa m : mahasiswaList) {
                if (m.getNim().equals(nim)) {
                    JOptionPane.showMessageDialog(this, "NIM " + nim + " sudah terdaftar.");
                    return;
                }
            }
            
            // Jika lolos semua validasi, tambahkan data baru
            mahasiswaList.add(new Mahasiswa(nim, nama, prodi, kelamin, status));
            JOptionPane.showMessageDialog(this, "Data berhasil disimpan.");
            clearForm(); // Kosongkan form setelah simpan
        }
    }

    // clearForm() juga me-reset status update 
    private void clearForm() {
        tfNIM.setText("");
        tfNama.setText("");
        cbProdi.setSelectedIndex(0);
        bgKelamin.clearSelection();
        chkAktif.setSelected(false);
        tfNIM.requestFocus();
        
        // Reset status update
        isUpdateMode = false;
        mhsDipilih = null;
        tfNIM.setEditable(true); // NIM bisa diedit lagi
        btnKirim.setText("Kirim"); // Kembalikan teks tombol
        lblTitle.setText("Tambah Data Mahasiswa"); // Kembalikan judul panel
    }

    private void refreshTable() {
        tableModel.setRowCount(0); // Kosongkan tabel
        for (Mahasiswa m : mahasiswaList) { // Isi ulang dari list
            tableModel.addRow(new Object[]{
                    m.getNama(),
                    m.getNim(),
                    m.getProdi(),
                    m.getKelamin(),
                    m.isActive() ? "Aktif" : "Tidak Aktif"
            });
        }
    }

    // Method untuk memuat data ke form saat update 
    private void loadDataUntukUpdate() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih baris terlebih dahulu.");
            return;
        }

        // Dapatkan NIM dari baris tabel yang dipilih
        String nim = (String) tableModel.getValueAt(row, 1);
        
        // Cari objek Mahasiswa di list berdasarkan NIM
        mhsDipilih = null;
        for (Mahasiswa m : mahasiswaList) {
            if (m.getNim().equals(nim)) {
                mhsDipilih = m;
                break;
            }
        }
        
        if (mhsDipilih != null) {
            // Set status ke mode update
            isUpdateMode = true;

            // Isi semua field form
            tfNIM.setText(mhsDipilih.getNim());
            tfNIM.setEditable(false); // NIM tidak boleh diubah saat update
            tfNama.setText(mhsDipilih.getNama());
            cbProdi.setSelectedItem(mhsDipilih.getProdi());
            
            if (mhsDipilih.getKelamin().equals("Laki-laki")) {
                rbLaki.setSelected(true);
            } else {
                rbPerempuan.setSelected(true);
            }
            chkAktif.setSelected(mhsDipilih.isActive());
            
            // Ubah tampilan panel tambah
            lblTitle.setText("Update Data Mahasiswa");
            btnKirim.setText("Update");
            
            // Pindahkan tampilan ke panel form
            showCard("TAMBAH");
        }
    }
 

    // fitur Hapus 
    private void hapusBaris() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih baris terlebih dahulu.");
            return;
        }
        int opt = JOptionPane.showConfirmDialog(this, "Yakin hapus data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (opt != JOptionPane.YES_OPTION) return;

        // Hapus data dari list berdasarkan NIM
        String nim = (String) tableModel.getValueAt(row, 1);
        mahasiswaList.removeIf(m -> m.getNim().equals(nim));
        
        // Refresh tabel untuk menampilkan perubahan
        refreshTable();
    }
}
