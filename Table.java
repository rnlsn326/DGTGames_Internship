/*
    Digitoy Games Staj Basvuru Projesi için gerekli table classı

    masaEkle fonksiyonu ile masa eklenebilir
    masaFiltrele fonksiyonu ile masa filtreleme yapılabilir
    toString fonksiyonu ile masa bilgileri yazdırılabilir

    masa özellikleri
    bahis_MIN = masaya girmek için gerekli minimum bahis, default değeri 0, minimum giriş ücreti olmayan masalarda kullanılabilir
    bahis_MAX= masaya girmek için gerekli maximum bahis, default değeri Integer.MAX_VALUE, maksimum giriş ücreti olmayan masalarda kullanılabilir
    masaTur= masanın türü, bitflag ile kodlanarak yapıldı, bu yüzden bonus olarak istendiği üzere tek bir integer değeri ile tutulabiliyor
    masaID= masa numarası


*/

import java.util.ArrayList;
import java.util.List;

public class Table {
    // masa özellikleri
    private int minimumBet = 0;
    private int maximumBet = Integer.MAX_VALUE;
    private int tableType;
    private int tableID;
    // masa türü bitflag ile kodlanacak
    public static final int HIZLI = 0b00000001;
    public static final int TEKETEK = 0b00000010;
    public static final int ROVANS = 0b00000100;

    // masa listesi
    private static final List<Table> tableList = new ArrayList<>();

    // boş constructor, table nesnesi oluşturulurken kullanmak için
    public Table() {
    }

    // dolu constructor, masa listesine masayı ve özellikleri eklerken kullanmak
    // için var
    public Table(int minimumBet, int maximumBet, int tableType, int tableID) {
        this.minimumBet = minimumBet;
        this.maximumBet = maximumBet;
        this.tableType = tableType;
        this.tableID = tableID;
    }

    // getter/setter
    public int getMinimumBet() {
        return minimumBet;
    }

    public void setMinimumBet(int minimumBet) {
        this.minimumBet = minimumBet;
    }

    public int getMaximumBet() {
        return maximumBet;
    }

    public void setMaximumBet(int maximumBet) {
        this.maximumBet = maximumBet;
    }

    public int getTableType() {
        return tableType;
    }

    public void setTableType(int tableType) {
        this.tableType = tableType;
    }

    public int getTableID() {
        return tableID;
    }

    public void setTableID(int tableID) {
        this.tableID = tableID;
    }

    // toString methodu
    @Override
    public String toString() {
        return "Table [minimumBet=" + minimumBet + ", maximumBet=" + maximumBet + ", tableType=" + tableType
                + ", tableID=" + tableID + "]";
    }

    // masa ekleme fonksiyonu
    public void addTable(int minimumBet, int maximumBet, int tableType, int tableID) {
        tableList.add(new Table(minimumBet, maximumBet, tableType, tableID));
    }

    // masa filtreleme fonksiyonu
    public void filterTable(int bet, int tableType) {
        boolean found = false;
        for (Table table : tableList) {
            if (table.getMinimumBet() <= bet && table.getMaximumBet() >= bet && table.getTableType() == tableType) {
                int id = table.getTableID();
                System.out.println(id + " numaralı masaya oturabilirsiniz.");
                found = true;
            }
        }
        if (!found) {
            System.out.println("Uygun masa bulunamadı.");
        }
    }
}