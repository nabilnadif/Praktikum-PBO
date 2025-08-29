public class PBO_2 { // Nama kelas
    // Konstanta
    public static final int JUMLAH_SEMESTER = 2;
    public static void main(String[] args) {

        // Biodata Mahasiswa
        String nama = "Raka Nadwa";
        long nim = 2407113886l;
        String alamat = "Jl. Adi Sucipto, Pekanbaru";
        int umur = 19;
        char golonganDarah = 'A';

        // IP tiap semester yang disimpan dalam array berjenis double bernama ipSemester
        double[] ipSemester = {3.95, 3.69};

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