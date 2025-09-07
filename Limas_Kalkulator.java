import java.util.Scanner;//import scanner

 public class Limas_Kalkulator 
{
    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in); //membuat objek scanner
        System.out.println("\n=== KALKULATOR VOLUME LIMAS KELOMPOK 3 ===");
        System.out.println("Kalkulator ini hanya menerima Limas Segiempat!");
        System.out.println("[Perhitungan dilakukan dengan satuan cm]");
        System.out.print("Masukkan panjang sisi alas: ");
        double sisi = input.nextDouble();//input sisi alas
        System.out.print("Masukkan tinggi limas: ");
        double tinggi = input.nextDouble();//input tinggi limas
        double volume = ((sisi * sisi)* tinggi)/3;//rumus volume limas segiempat
        System.out.println("Volume limas segiempat adalah: " + volume + " cm kubik "); 
    }
}