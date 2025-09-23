package com.universitas;

public class Universitas {

    String nama;
    String lokasi;
    int tahunBerdiri;
    int mahasiswa;
    double luas;

    //constructor pertama
    public Universitas(String nama, String lokasi) {
        this.nama = nama;
        this.lokasi = lokasi;
        this.mahasiswa = 0;
        this.luas = 0.0;
    }

    //constructor kedua
    public Universitas(String nama, String lokasi, int tahunBerdiri) {
        this.nama = nama;
        this.lokasi = lokasi;
        this.tahunBerdiri = tahunBerdiri;
        this.mahasiswa = 0;
        this.luas = 0.0;
    }
    
    //constructor ketiga
    public Universitas(String nama, String lokasi, int tahunBerdiri, int mahasiswa, double luas) {
        this.nama = nama;
        this.lokasi = lokasi;
        this.tahunBerdiri = tahunBerdiri;
        this.mahasiswa = mahasiswa;
        this.luas = luas;
    }

    public void tampilkanInfo() {
        System.out.println(nama + " berada di Kota " + lokasi + " dan berdiri pada Tahun " + tahunBerdiri + "\n");
    }

    public void tampilkanInfoDetail() {
            System.out.println("============ Daftar Universitas ==========");
            System.out.println("Nama Universitas : " + nama);
            System.out.println("Lokasi           : Kota " + lokasi);
            System.out.println("Tahun Berdiri    : " + tahunBerdiri);
            System.out.println("Jumlah Mahasiswa : " + mahasiswa);
            System.out.println("Luas Kampus      : " + luas + " meter kuadrat");
            System.out.println("==========================================\n");
        }
    
    // overload lain 
    public void tampilkanInfo(String catatan) {
        System.out.println( nama + catatan );
    }
    public static void main(String[] args) {
        Universitas A = new Universitas("Universitas Riau", "Pekanbaru", 1962, 35000, 3450000.0);
        Universitas B = new Universitas("Universitas Indonesia", "Depok", 1950, 46000, 3200000.0);
        Universitas C = new Universitas("Universitas Sriwijaya", "Palembang", 1960,40000, 7120000.0);
    
        A.tampilkanInfo(" dikenal sebagai kampus hijau");
        A.tampilkanInfoDetail();      
        B.tampilkanInfo(" adalah tempat perpustakaan terbesar di Asia Tenggara");
        B.tampilkanInfoDetail();  
        C.tampilkanInfo(" adalah universitas terluas no 1 di Indonesia");
        C.tampilkanInfo();       
    }
} 
