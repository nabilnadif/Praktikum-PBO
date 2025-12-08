/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pa;

import com.mysql.cj.protocol.Resultset;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Administrator
 */
public class Supplier extends javax.swing.JFrame {
    String id;
    private JLabel notifikasiLabel;

    /**
     * Creates new form Supplier
     */
    public Supplier() {
        initComponents();
        piechart();
        cekbarang();
        setTitle("Supplier");

        this.setLocationRelativeTo(null);
    }

    //Anip
    public void setID(String id) {
        this.id = id;
    }
    public String getID() {
        return id;
    }
    public void piechart() {

        try {
            String sql1 = "SELECT SUM(jumlah_stok) FROM barang WHERE id_kategori = 1";
            String sql2 = "SELECT SUM(jumlah_stok) FROM barang WHERE id_kategori = 2";
            String sql3 = "SELECT SUM(jumlah_stok) FROM barang WHERE id_kategori = 3";

            java.sql.Connection conn = (Connection) Database.configDB();

            java.sql.PreparedStatement stmtcat1 = conn.prepareStatement(sql1);
            java.sql.PreparedStatement stmtcat2 = conn.prepareStatement(sql2);
            java.sql.PreparedStatement stmtcat3 = conn.prepareStatement(sql3);

            ResultSet rscat1 = stmtcat1.executeQuery();
            ResultSet rscat2 = stmtcat2.executeQuery();
            ResultSet rscat3 = stmtcat3.executeQuery();

            if (rscat1.next() && rscat2.next() && rscat3.next()) {
                DefaultPieDataset PieChart = new DefaultPieDataset();
                PieChart.setValue("Komponen PC = " + rscat1.getInt("SUM(jumlah_stok)"), rscat1.getInt("SUM(jumlah_stok)"));
                PieChart.setValue("Laptop = " + rscat2.getInt("SUM(jumlah_stok)"), rscat2.getInt("SUM(jumlah_stok)"));
                PieChart.setValue("Gaming Gear = " + rscat3.getInt("SUM(jumlah_stok)"), rscat3.getInt("SUM(jumlah_stok)"));
                JFreeChart chart = ChartFactory.createPieChart("Total Barang Per-Kategori", PieChart);
                PiePlot p = (PiePlot) chart.getPlot();

                ChartPanel piechart = new ChartPanel(chart);
                Panel_Chart.add(piechart, BorderLayout.CENTER);
            }

            rscat1.close();
            rscat2.close();
            rscat3.close();
            stmtcat1.close();
            stmtcat2.close();
            stmtcat3.close();
            conn.close();

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    public void cekbarang() {
        try {
            String sql = "SELECT nama_barang FROM barang WHERE jumlah_stok <3";
            String sql2 = "SELECT COUNT(nama_barang) FROM barang WHERE jumlah_stok <3";

            java.sql.Connection conn = (Connection) Database.configDB();
            java.sql.PreparedStatement pstmcekstok = conn.prepareStatement(sql);
            java.sql.PreparedStatement pstmjmlh = conn.prepareStatement(sql2);

            ResultSet cekstok = pstmcekstok.executeQuery();
            ResultSet jumlah = pstmjmlh.executeQuery();

            Panel_Notifikasi.setLayout(new FlowLayout(FlowLayout.LEFT));

            while (cekstok.next()) {
                String namaBarang = cekstok.getString("nama_barang");
                String pesan = "Barang " + namaBarang + " Kurang Dari 3, Silahkan Stok Ulang \n";
                JLabel notifLabel = new JLabel();
                Panel_Notifikasi.add(notifLabel);
                notifLabel.setText(pesan);
            }

            jumlah.close();
            cekstok.close();
            pstmjmlh.close();
            pstmcekstok.close();
            conn.close();

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    //Tia
    public void TampilData() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("ID Transaksi Masuk");
        model.addColumn("Tanggal Transaksi Masuk");
        model.addColumn("Jumlah Barang Masuk");
        model.addColumn("ID Barang");
        model.addColumn("ID Supplier");

        //Menampilkan data pada database ke dalam tabel
        try {
            int no = 1;
            String sql = "SELECT * FROM transaksi_masuk";
            java.sql.Connection conn = (Connection) Database.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);

            while (res.next()) {
                model.addRow(new Object[]{
                    no++,
                    res.getString(1),
                    res.getString(2),
                    res.getString(3),
                    res.getString(4),
                    res.getString(5),});
            }
            Tabel_Category.setModel(model);

        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    //Zaki
    public class Kategori {

        private int idKategori;
        private String namaKategori;

        public Kategori(int idKategori, String namaKategori) {
            this.idKategori = idKategori;
            this.namaKategori = namaKategori;
        }

        public int getIdKategori() {
            return idKategori;
        }

        public void setIdKategori(int idKategori) {
            this.idKategori = idKategori;
        }

        public String getNamaKategori() {
            return namaKategori;
        }

        public void setNamaKategori(String namaKategori) {
            this.namaKategori = namaKategori;
        }

        @Override
        public String toString() {
            return namaKategori; // Berguna untuk menampilkan nama kategori jika diperlukan
        }
    }

    public class Barang {

        private String idBarang;
        private String namaBarang;
        private String harga;
        private int stok;
        private Kategori kategori; // Asosiasi: Barang memiliki sebuah Kategori

        public Barang(String idBarang, String namaBarang, String harga, int stok, Kategori kategori) {
            this.idBarang = idBarang;
            this.namaBarang = namaBarang;
            this.harga = harga;
            this.stok = stok;
            this.kategori = kategori;
        }

        // Getter methods
        public String getIdBarang() {
            return idBarang;
        }

        public String getNamaBarang() {
            return namaBarang;
        }

        public String getHarga() {
            return harga;
        }

        public int getStok() {
            return stok;
        }

        public Kategori getKategori() {
            return kategori;
        }

        // Setter methods (jika diperlukan)
        public void setIdBarang(String idBarang) {
            this.idBarang = idBarang;
        }

        public void setNamaBarang(String namaBarang) {
            this.namaBarang = namaBarang;
        }

        public void setHarga(String harga) {
            this.harga = harga;
        }

        public void setStok(int stok) {
            this.stok = stok;
        }

        public void setKategori(Kategori kategori) {
            this.kategori = kategori;
        }
    }

    public void tampilData(int id_kategori) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Harga");
        model.addColumn("Stok");
        model.addColumn("Kategori");

        try {

            String sql = "SELECT b.id_barang, b.nama_barang, b.harga, b.jumlah_stok, k.id_kategori, k.nama_kategori FROM barang b JOIN kategori k ON b.id_kategori = k.id_kategori WHERE b.id_kategori = " + id_kategori;
            java.sql.Connection conn = (Connection) Database.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);

            while (res.next()) {
                //membuat objek kategori
                int kategoriId = res.getInt("id_kategori");
                String namaKategori = res.getString("nama_kategori");
                Kategori kat = new Kategori(kategoriId, namaKategori);

                //membuat objek barang dengan asosiasi ke kategori
                String idBarang = res.getString("id_barang");
                String namaBrg = res.getString("nama_barang");
                String hargaBrg = res.getString("harga");
                int stok = res.getInt("jumlah_stok");
                Barang brg = new Barang(idBarang, namaBrg, hargaBrg, stok, kat);

                //menambahkan data objek ke tabel
                model.addRow(new Object[]{
                    brg.getIdBarang(),
                    brg.getNamaBarang(),
                    brg.getHarga(),
                    brg.getStok(),
                    brg.getKategori().getNamaKategori()});
            }
            Tabel_Category.setModel(model);
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }

    }

    public void KosongFormTambah() {
        jTextField11.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        
    }

    public void KosongFormInput() {
        jTextField5.setText(null);
        jTextField7.setText(null);
        jTextField8.setText(null);
        jTextField10.setText(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        Label_nama = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        Panel_Dashboard = new javax.swing.JPanel();
        Panel_Chart = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Panel_Notifikasi = new javax.swing.JPanel();
        Panel_Category = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tabel_Category = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        Panel_Input_Barang_Baru = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        Panel_Update_Barang = new javax.swing.JPanel();
        jTextField11 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(700, 540));
        setPreferredSize(new java.awt.Dimension(600, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setText("Dashboard");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setText("Input Barang Baru");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setText("Category");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton4.setText("Sign Out");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        Label_nama.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        Label_nama.setForeground(new java.awt.Color(255, 255, 255));
        Label_nama.setMaximumSize(new java.awt.Dimension(39, 16));

        jButton13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton13.setText("Update Barang");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                .addComponent(Label_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 80));

        Panel_Dashboard.setBackground(new java.awt.Color(102, 102, 102));

        Panel_Chart.setBackground(new java.awt.Color(204, 204, 204));
        Panel_Chart.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Notifikasi");

        Panel_Notifikasi.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(268, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(Panel_Notifikasi, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Panel_Notifikasi, javax.swing.GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
                .addGap(169, 169, 169))
        );

        javax.swing.GroupLayout Panel_DashboardLayout = new javax.swing.GroupLayout(Panel_Dashboard);
        Panel_Dashboard.setLayout(Panel_DashboardLayout);
        Panel_DashboardLayout.setHorizontalGroup(
            Panel_DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_DashboardLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Panel_Chart, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(161, 161, 161))
        );
        Panel_DashboardLayout.setVerticalGroup(
            Panel_DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_DashboardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(Panel_DashboardLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(Panel_Chart, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 507, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab2", Panel_Dashboard);

        Panel_Category.setBackground(new java.awt.Color(102, 102, 102));

        jPanel5.setBackground(new java.awt.Color(51, 51, 51));
        jPanel5.setPreferredSize(new java.awt.Dimension(600, 500));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("KOMPONEN PC");

        jButton8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton8.setText("Pilih");
        jButton8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("LAPTOP");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("GAMING GEAR");

        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton9.setText("Pilih");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton10.setText("Pilih");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(102, 102, 102));
        jPanel6.setPreferredSize(new java.awt.Dimension(200, 230));

        Tabel_Category.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Tabel_Category.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID Barang", "Nama Barang", "Harga", "Stok", "Kategori"
            }
        ));
        jScrollPane2.setViewportView(Tabel_Category);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("INVENTARIS BARANG");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jButton8))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton9, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton10, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(59, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8)
                .addGap(51, 51, 51)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton9)
                .addGap(53, 53, 53)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton10)
                .addGap(102, 102, 102))
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout Panel_CategoryLayout = new javax.swing.GroupLayout(Panel_Category);
        Panel_Category.setLayout(Panel_CategoryLayout);
        Panel_CategoryLayout.setHorizontalGroup(
            Panel_CategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_CategoryLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 678, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 42, Short.MAX_VALUE))
        );
        Panel_CategoryLayout.setVerticalGroup(
            Panel_CategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_CategoryLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 449, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab3", Panel_Category);

        jLabel7.setText("Harga");

        jLabel8.setText("Id Kategori");

        jLabel12.setText("Jumlah Stok");

        jLabel15.setText("Nama Barang");

        jLabel17.setText("Tanggal Transaksi");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1. Komponen PC", "2. Laptop", "3. Gaming Gear" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jButton14.setText("Input Barang");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setText("Batal");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setText("Keluar");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Panel_Input_Barang_BaruLayout = new javax.swing.GroupLayout(Panel_Input_Barang_Baru);
        Panel_Input_Barang_Baru.setLayout(Panel_Input_Barang_BaruLayout);
        Panel_Input_Barang_BaruLayout.setHorizontalGroup(
            Panel_Input_Barang_BaruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_Input_Barang_BaruLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(Panel_Input_Barang_BaruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addGroup(Panel_Input_Barang_BaruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(Panel_Input_Barang_BaruLayout.createSequentialGroup()
                            .addComponent(jButton14)
                            .addGap(84, 84, 84)
                            .addComponent(jButton15))
                        .addGroup(Panel_Input_Barang_BaruLayout.createSequentialGroup()
                            .addGroup(Panel_Input_Barang_BaruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel15)
                                .addComponent(jLabel12))
                            .addGap(90, 90, 90)
                            .addGroup(Panel_Input_Barang_BaruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField7)
                                .addComponent(jTextField8)
                                .addComponent(jTextField10)))
                        .addGroup(Panel_Input_Barang_BaruLayout.createSequentialGroup()
                            .addGroup(Panel_Input_Barang_BaruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7)
                                .addComponent(jLabel8))
                            .addGap(103, 103, 103)
                            .addGroup(Panel_Input_Barang_BaruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton16)
                .addGap(113, 113, 113))
        );
        Panel_Input_Barang_BaruLayout.setVerticalGroup(
            Panel_Input_Barang_BaruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_Input_Barang_BaruLayout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addGroup(Panel_Input_Barang_BaruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(Panel_Input_Barang_BaruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(Panel_Input_Barang_BaruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(Panel_Input_Barang_BaruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(Panel_Input_Barang_BaruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(148, 148, 148)
                .addGroup(Panel_Input_Barang_BaruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton14)
                    .addComponent(jButton15)
                    .addComponent(jButton16))
                .addContainerGap(412, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab3", Panel_Input_Barang_Baru);

        Panel_Update_Barang.setBackground(new java.awt.Color(153, 153, 153));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Tanggal Transaksi");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Jumlah Barang Masuk");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("ID Barang");

        jButton5.setText("Tambah");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Batal");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Keluar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Panel_Update_BarangLayout = new javax.swing.GroupLayout(Panel_Update_Barang);
        Panel_Update_Barang.setLayout(Panel_Update_BarangLayout);
        Panel_Update_BarangLayout.setHorizontalGroup(
            Panel_Update_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_Update_BarangLayout.createSequentialGroup()
                .addGroup(Panel_Update_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(Panel_Update_BarangLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(Panel_Update_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(50, 50, 50)
                        .addGroup(Panel_Update_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                            .addComponent(jTextField2)
                            .addComponent(jTextField11)))
                    .addGroup(Panel_Update_BarangLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jButton5)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 208, Short.MAX_VALUE)
                        .addComponent(jButton7)))
                .addContainerGap(242, Short.MAX_VALUE))
        );
        Panel_Update_BarangLayout.setVerticalGroup(
            Panel_Update_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_Update_BarangLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(Panel_Update_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(Panel_Update_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(Panel_Update_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(189, 189, 189)
                .addGroup(Panel_Update_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton7)
                    .addComponent(jButton6))
                .addContainerGap(489, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab4", Panel_Update_Barang);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 720, 890));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.setVisible(false);
        Login ltab = new Login();
        ltab.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        tampilData(1);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        tampilData(2);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        tampilData(3);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int idB = Integer.parseInt(jTextField3.getText());
        int jB = Integer.parseInt(jTextField2.getText());
        
        String id;
        
        try {
            Login carinama = new Login();
            
            java.sql.Connection conn = (Connection) Database.configDB();
            
            String checkIDbrg = "SELECT COUNT(id_barang) FROM barang WHERE id_barang= " + idB;
            java.sql.PreparedStatement cekId = conn.prepareStatement(checkIDbrg);
            ResultSet cek = cekId.executeQuery();
            
            cek.next();
            int totalId = cek.getInt("COUNT(id_barang)");
            if(totalId >idB){
                JOptionPane.showMessageDialog(null, "ID Barang Tidak Tersedia");
            }
            
            String sql1 = "SELECT harga FROM barang where id_barang = " + idB;

            String sql2 = "SELECT jumlah_stok FROM barang where id_barang = " + idB;

            

            java.sql.PreparedStatement hrg = conn.prepareStatement(sql1);
            java.sql.PreparedStatement jmlh = conn.prepareStatement(sql2);
            java.sql.PreparedStatement hitungid = conn.prepareStatement("SELECT COUNT(id_transaksiMasuk) FROM transaksi_masuk");

            ResultSet hitung = hrg.executeQuery();
            ResultSet banding = jmlh.executeQuery();
            ResultSet hitungidB = hitungid.executeQuery();

            if (hitungidB.next()) {
                if (hitungidB.getInt("COUNT(id_transaksiMasuk)") != idB) {
                    if (banding.next()) {
                        int sisa = banding.getInt("jumlah_stok") + jB;

                        String sql = "INSERT INTO transaksi_masuk VALUES('"
                                + (hitungidB.getInt("COUNT(id_transaksiMasuk)") + 1) + "','"
                                + jTextField11.getText() + "','"
                                + jTextField2.getText() + "','"
                                + jTextField3.getText() + "','"
                                + getID()+ "')";

                        java.sql.PreparedStatement pstl = conn.prepareStatement("UPDATE barang SET jumlah_stok = " + sisa + " WHERE id_barang = " + idB);
                        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                        pst.execute();
                        pstl.execute();

                        JOptionPane.showMessageDialog(null, "Simpan Data Baru Berhasil");

                    }
                } else {
                    int jumlah = hitungidB.getInt("COUNT(id_transaksiMasuk)") + 1;
                    JOptionPane.showMessageDialog(null, "ID Transaksi Masuk (" + hitungidB.getInt("COUNT(id_transaksiMasuk)") + ") Terjadi Duplikat, Silahkan Gunakan ID Transaksi Masuk = " + jumlah);
                }
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        KosongFormTambah();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        System.exit(0); // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        String id;
        try {
            Login carinama = new Login();
            String[] kategori = {"1. Komponen PC", "2. Laptop", "3. Gaming Gear"};
            if (jComboBox2.getSelectedItem().toString().equalsIgnoreCase(kategori[0])) {
                String sqlB = "SELECT COUNT(id_barang) FROM barang";
                String sqlT = "SELECT COUNT(id_transaksiMasuk) FROM transaksi_masuk";
                
                java.sql.Connection conn = (Connection) Database.configDB();

                
                java.sql.PreparedStatement inputB = conn.prepareStatement(sqlB);
                java.sql.PreparedStatement inputT = conn.prepareStatement(sqlT);
                ResultSet inputbarang = inputB.executeQuery();
                ResultSet inputtransaksi = inputT.executeQuery();

                while (inputbarang.next() && inputtransaksi.next()) {
                    String sql2 = "INSERT INTO barang VALUES('"
                            + (inputbarang.getInt("COUNT(id_barang)") + 1) + "','"
                            + jTextField8.getText() + "','"
                            + jTextField5.getText() + "','"
                            + jTextField7.getText() + "','"
                            + 1 + "')";

                    String sql3 = "INSERT    INTO transaksi_masuk VALUES('"
                            + (inputtransaksi.getInt("COUNT(id_transaksiMasuk)") + 1) + "','"
                            + jTextField10.getText() + "','"
                            + jTextField7.getText() + "','"
                            + inputbarang.getInt("COUNT(id_barang)") + "','"
                            +  getID() + "')";

                    java.sql.PreparedStatement inputB1 = conn.prepareStatement(sql2);
                    java.sql.PreparedStatement inputT1 = conn.prepareStatement(sql3);
                    inputB1.execute();
                    inputT1.execute();
                    
                    JOptionPane.showMessageDialog(null, "Input Data Barang Baru Berhasil");
                }

            } else if (jComboBox2.getSelectedItem().toString().equalsIgnoreCase(kategori[1])) {
                String sqlB = "SELECT COUNT(id_barang) FROM barang";
                String sqlT = "SELECT COUNT(id_transaksiMasuk) FROM transaksi_masuk";
                
                java.sql.Connection conn = (Connection) Database.configDB();

                
                
                
                
                java.sql.PreparedStatement inputB = conn.prepareStatement(sqlB);
                java.sql.PreparedStatement inputT = conn.prepareStatement(sqlT);
                ResultSet inputbarang = inputB.executeQuery();
                ResultSet inputtransaksi = inputT.executeQuery();

                while (inputbarang.next() && inputtransaksi.next()) {
                    String sql2 = "INSERT INTO barang VALUES('"
                            + (inputbarang.getInt("COUNT(id_barang)") + 1) + "','"
                            + jTextField8.getText() + "','"
                            + jTextField5.getText() + "','"
                            + jTextField7.getText() + "','"
                            + 2 + "')";

                    String sql3 = "INSERT INTO transaksi_masuk VALUES('"
                            + (inputtransaksi.getInt("COUNT(id_transaksiMasuk)") + 1) + "','"
                            + jTextField10.getText() + "','"
                            + jTextField7.getText() + "','"
                            + inputbarang.getInt("COUNT(id_barang)") + "','"
                            +  getID() + "')";

                    java.sql.PreparedStatement inputB1 = conn.prepareStatement(sql2);
                    java.sql.PreparedStatement inputT1 = conn.prepareStatement(sql3);
                    inputB1.execute();
                    inputT1.execute();
                    
                    JOptionPane.showMessageDialog(null, "Input Data Barang Baru Berhasil");
                }
            } else if (jComboBox2.getSelectedItem().toString().equalsIgnoreCase(kategori[2])) {
                String sqlB = "SELECT COUNT(id_barang) FROM barang";
                String sqlT = "SELECT COUNT(id_transaksiMasuk) FROM transaksi_masuk";
                
                java.sql.Connection conn = (Connection) Database.configDB();

                java.sql.PreparedStatement inputB = conn.prepareStatement(sqlB);
                java.sql.PreparedStatement inputT = conn.prepareStatement(sqlT);
                ResultSet inputbarang = inputB.executeQuery();
                ResultSet inputtransaksi = inputT.executeQuery();

                while (inputbarang.next() && inputtransaksi.next()) {
                    String sql2 = "INSERT INTO barang VALUES('"
                            + (inputbarang.getInt("COUNT(id_barang)") + 1) + "','"
                            + jTextField8.getText() + "','"
                            + jTextField5.getText() + "','"
                            + jTextField7.getText() + "','"
                            + 3 + "')";

                    String sql3 = "INSERT INTO transaksi_masuk VALUES('"
                            + (inputtransaksi.getInt("COUNT(id_transaksiMasuk)") + 1) + "','"
                            + jTextField10.getText() + "','"
                            + jTextField7.getText() + "','"
                            + inputbarang.getInt("COUNT(id_barang)") + "','"
                            +  getID() + "')";

                    java.sql.PreparedStatement inputB1 = conn.prepareStatement(sql2);
                    java.sql.PreparedStatement inputT1 = conn.prepareStatement(sql3);
                    inputB1.execute();
                    inputT1.execute();
                    
                    JOptionPane.showMessageDialog(null, "Input Data Barang Baru Berhasil");
                }
            }

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        KosongFormInput();// TODO add your handling code here:
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        System.exit(0);// TODO add your handling code here:
    }//GEN-LAST:event_jButton16ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Supplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Supplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Supplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Supplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Supplier().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel Label_nama;
    private javax.swing.JPanel Panel_Category;
    private javax.swing.JPanel Panel_Chart;
    private javax.swing.JPanel Panel_Dashboard;
    private javax.swing.JPanel Panel_Input_Barang_Baru;
    private javax.swing.JPanel Panel_Notifikasi;
    private javax.swing.JPanel Panel_Update_Barang;
    private javax.swing.JTable Tabel_Category;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    // End of variables declaration//GEN-END:variables
}
