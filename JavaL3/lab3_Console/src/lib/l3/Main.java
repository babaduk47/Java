package lib.l3;

import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        ArrayList<Integer> num = new ArrayList<>();
        if (args.length < 1) {
            System.out.println("Ошибка параметров( Укажите путь )");
            return;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(args[0]);
            int res;
            do {
                res = fileInputStream.read();
                if (res != -1) {
                    num.add(res);
                }
            } while (res != -1);
            fileInputStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Файл не найден");
            return;
        } catch (IOException ex) {
            System.out.println("Ошибка файла");
            return;
        }
        num = new ArrayList<>();
        SignalProcessing signalProcessing = new SignalProcessing(num);
        System.out.println("(1) Динамічний діапазон сигналу : " + signalProcessing.getDynamicRange());
        System.out.println("(2) Енергія: " + signalProcessing.getSignalEnergy());
        System.out.println("(3) Середня потужність Рs сигналу: " + signalProcessing.getAverageTugOnSignal());
        System.out.println("(4) Середнє значення відліків сигналу ms: " + signalProcessing.getAverageValueOfTheSignalReadings());
        System.out.println("(5) Дисперсія значень відліків сигналу ds: " + signalProcessing.getDispersionOfProcessedSignals());
        System.out.println("(6) Автокорреляция:");
        for(int r = -4; r <= 4; r++)
        {
            System.out.println("[" + r + "]: " +
                    signalProcessing.getAutocorrelationOfADiscreteSignal(r));
        }
        System.out.println("(7) Інтервал кореляції: " + signalProcessing.getCorrelationInterval());
    }
}

