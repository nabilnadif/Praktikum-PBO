public class Pesanan {
    private String nama;
    private String menu;

    public Pesanan(String nama, String menu) {
        this.nama = nama;
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "Nama: " + nama + "\n" +
               "Menu: " + menu;
    }
    
}
