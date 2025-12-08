public class Main {

    public static void main(String[] args) {
        // Object Instantiation
        Mobil mobil1 = new Mobil();
        Mobil mobil2 = new Mobil();

        // Constructor overloading
        Mobil mobil4 = new Mobil();
        Mobil mobil3 = new Mobil("hitam","Toyota", 2025);

        // Method Overloading
        Kalkulator calc = new Kalkulator();
        System.out.println(calc.tambah(5, 3));
        System.out.println(calc.tambah(5.5, 3.2));
        System.out.println(calc.tambah("Hello", "World"));
    }
}

