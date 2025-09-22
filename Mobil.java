public class Mobil {
    // Atribut
    private String nama;
    private String warna;
    private String merek;
    private int tahun;
    // Method
    public void nyalakanMesin() {
        System.out.println("Mesin dinyalakan");
    }
    public void matikanMesin() {
        System.out.println("Mesin dimatikan");
    }

    // Default constructor
    public Mobil(){
        this.merek = "Unknown";
        this.warna = "Unknown";
    }

    // Constructor dengan parameter
    public Mobil(String warna, String merek, int tahun){
        this.merek = merek;
        this.warna = warna;
        this.tahun = tahun;
    }
}
