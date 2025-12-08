public class Operator {
    public static void main(String[] args) {
        //Operasi Aritmatika
        int a = 10, b = 3;
        System.out.println("Hasil a + b = " + (a + b)); // Penjumlahan: 13
        System.out.println("Hasil a - b = " + (a - b)); // Pengurangan: 7
        System.out.println("Hasil a * b = " + (a * b)); // Perkalian: 30
        System.out.println("Hasil a / b = " + (a / b)); // Pembagian: 3
        System.out.println("Hasil a % b = " + (a % b)); // Modulus: 1
        System.out.println("Hasil ++a = " + (++a)); // Pre-increment: 11
        System.out.println("Hasil b-- = " + (b--)); // Post-decrement: 

        //Operator Perbandingan
        System.out.println("Hasil a = y = " + (a == b));   
        System.out.println("Hasil x != y = " + (a != b)); 
        System.out.println("Hasil x > y = " + (a > b));   
        System.out.println("Hasil x < y = " + (a < b));   
        System.out.println("Hasil x >= y = " + (a >= b)); 
        System.out.println("Hasil x <= y = " + (a <= b)); 

        //Operator Logika
        boolean p = true, q = false;
        System.out.println("Hasil p && q = " + (p && q)); // AND: false
        System.out.println("Hasil p || q = " + (p || q)); // OR: true
        System.out.println("Hasil !p = " + (!p));         // NOT: false

        //short-circuit evaluation
        boolean result = (a != 0) && ((a / b) > 2); 
        System.out.println("Result: " + result);

        //Operator Assignment
        a += 5;
        a -= 3;
        a *= 2;
        a /= 4;
        a %= 4; 
        System.out.println("Nilai akhir: " + a);

        //Operator Ternary
        int score = 85;
        String grade = (score >= 80) ? "A" : (score >= 70) ? "B" : "C";
        System.out.println("Nilai: " + grade);
        int max = (10 > 5) ? 10 : 5;
        System.out.println("Nilai maksimum: " + max); 

        //Operator Bitwise
        System.out.println("Hasil a & b = " + (a & b)); 
        System.out.println("Hasil a | b = " + (a | b)); 
        System.out.println("Hasil a ^ b = " + (a ^ b)); 
        System.out.println("Hasil ~a = " + (~a));    
        System.out.println("Hasil a << 2 = " + (a << 2));
        System.out.println("Hasil a >> 2 = " + (a >> 2)); 
    }
}
