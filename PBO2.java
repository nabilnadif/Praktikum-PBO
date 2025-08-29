public class PBO2 { // Nama kelas
    // Konstanta
    public static final int JUMLAH_SEMESTER = 2;
    public static void main(String[] args) {

        // Biodata Mahasigma
        String nama = "Khumaira Azzahra Yarman";
        String nim = "2407135529";
        String alamat = "Jl. Wijaya, Pekanbaru";
        int umur = 18;
        char golonganDarah = 'A';

        // IP tiap semester yang disimpan dalam array berjenis double bernama ipSemester
        double[] ipSemester = {3.44, 3.65};

        // Konversi IP semester 1 ke byte
        byte ipByte = (byte) (ipSemester[0]);

        // Mencetak biodata Mahasigma
        System.out.println("===== DATA MAHASISWA =====");
        System.out.println("Nama           : " + nama);
        System.out.println("NIM            : " + nim);
        System.out.println("Alamat         : " + alamat);
        System.out.println("Umur           : " + umur + " tahun");
        System.out.println("Golongan Darah : " + golonganDarah);

        System.out.println("\nIP Tiap Semester:");
        System.out.println("Semester 1 : " + ipSemester[0]);
        System.out.println("Semester 2 : " + ipSemester[1]);
        

        // Menecetak IP semester setelah dikonversi ke byte
        System.out.println("\nIP Semester 1 setelah dikonversi ke byte : " + ipByte);
    }
}
    
