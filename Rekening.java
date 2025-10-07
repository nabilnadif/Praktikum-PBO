package LatihanPraktikum_6;

class Rekening {
    private String namaNasabah;
    private double saldoNasabah;

    public Rekening(String namaNasabah, double saldoNasabah) {
        this.namaNasabah = namaNasabah;
        this.saldoNasabah = saldoNasabah;
    }

    // Getter & Setter
    public String getNama() {
        return namaNasabah;
    }
    public void setNama(String namaNasabah) {
        System.out.println("Nasbah mengubah nama menjadi " + namaNasabah);
        this.namaNasabah = namaNasabah;
    }

    public double getSaldo() {
        return saldoNasabah;
    }
    public void setSaldo(double saldoNasabah) {
        System.out.println("Saldo nasabah bertambah menjadi " + saldoNasabah);
        this.saldoNasabah = saldoNasabah;
    }
}

