/*
Bu program, DigiToy Games Stajyerlik için hazırlanmıştır.
Eren Alsan
11.05.2023

programın amacı;
kullanıcıdan alınan bahis miktarı ve masa özelliklerine göre, uygun masaları ekrana yazdırmak.

programın çalışma mantığı;
-kullanıcı belli bir form içerisinde, bahis miktarı ve masa özelliklerini girer, ardından takeInput methoduna yollanan bu input,
string operasyonları ile parse edilir ve uygun masaları ekrana yazdırmak için uygun masaları bulan masaFiltrele methoduna yollanır.

-masaFiltrele methodu, masa özelliklerini bitflag ile kodlar ve kullanıcının girdiği özellikler ile karşılaştırır.Eğer masa bulunamazsa, bulunamadığına dair uyarı döner.

-ekrana yazdırma işlemi, masaFiltrele methodu içerisinde yapılır.
-program kullanıcı quit yazana kadar veya programı termine edene kadar çalışmaya devam eder


programın çalıştırılması;
-program terminal üzerinden çalıştırılır. Kullanıcıdan girdi almak için terminali kullanması gerekmektedir.
-programın çalışması için, masa özelliklerinin girilmesi zorunludur. Bahis miktarı girilmezse, program hata verir.

programın eksikleri
-program türkçe karakterleri işleyemediğinden, yalnızca ingilizce karakterler kullanarak input alabiliyor ve bu büyük bir sorun , 
-programda arayüz eksiği var, arayüz kullanılabilirse input almak daha kolay olacaktır ve türkçe karakter sorunu da çözülecektir.



*/

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println(
                "Lütfen bahsinizi, ve oynamak istediğiniz masanın özelliklerini : \"Bahis:2000$, Hızlı:Hayır, TekeTek:Evet, Rovanş:Evet\" şeklinde giriniz.\nÇıkmak için \"quit\" yazınız.");
        Scanner scan = new Scanner(System.in);
        boolean devam = true;
        Table table = new Table();
        table.addTable(100, 1000, 0b00000001, 1);
        table.addTable(200, 5000, 0b00000010, 2);
        table.addTable(500, 10000, 0b00000100, 3);
        table.addTable(1000, 50000, 0b00000001 | 0b00000010, 4);
        table.addTable(5000, 100000, 0b00000010 | 0b00000100, 5);
        table.addTable(1000, 500000, 0b00000001 | 0b00000010 | 0b00000100, 6);
        table.addTable(5000, 1000000, 0b00000001 | 0b00000010 | 0b00000100, 7);

        while (devam) {
            String input = scan.nextLine();
            if (input.equalsIgnoreCase("quit")) {
                devam = false;
                break;
            }
            takeInput(input, table);
            System.out.println("Yeni bahsi ve özellikleri giriniz.");

        }
        scan.close();

    }

    public static void takeInput(String input, Table table) {
        int bahis = 0;
        int masaTur = 0;
        boolean hizli = false;
        boolean tekeTek = false;
        boolean rovans = false;

        String BAHIS_PREFIX = "Bahis:";
        String HIZLI_PREFIX = "Hizli:";
        String TEKETEK_PREFIX = "TekeTek:";
        String ROVANS_PREFIX = "Rovans:";

        String[] tokens = input.split(", ");
        for (String token : tokens) {
            if (token.startsWith(BAHIS_PREFIX)) {
                String bahisStr = token.substring(BAHIS_PREFIX.length(), token.length() - 1);
                bahis = Integer.parseInt(bahisStr);
            } else if (token.startsWith(HIZLI_PREFIX)) {
                String hizliStr = token.substring(HIZLI_PREFIX.length());
                hizli = hizliStr.equalsIgnoreCase("Evet");
            } else if (token.startsWith(TEKETEK_PREFIX)) {
                String tekeTekStr = token.substring(TEKETEK_PREFIX.length());
                tekeTek = tekeTekStr.equalsIgnoreCase("Evet");
            } else if (token.startsWith(ROVANS_PREFIX)) {
                String rovansStr = token.substring(ROVANS_PREFIX.length());
                rovans = rovansStr.equalsIgnoreCase("Evet");
            }
        }
        if (hizli) {
            masaTur |= 0b00000001;
        }
        if (tekeTek) {
            masaTur |= 0b00000010;
        }
        if (rovans) {
            masaTur |= 0b00000100;
        }

        table.filterTable(bahis, masaTur);
    }
}
