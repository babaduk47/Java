package lib.l3;

import java.util.ArrayList;

public class SignalProcessing {
    /**
     * processed signals
     */
    private ArrayList<Integer> list=null;

    /**
     * initializes class fields
     * @param _list new processed signals
     */
    public SignalProcessing(ArrayList<Integer> _list) {
        this.list = _list;
    }

    /**
     * set new processed signals
     * @param list new processed signals
     */
    public void setList(ArrayList<Integer> list) {
        this.list = list;
    }

  //  public ArrayList<Integer> getList() {
  //      return list;
  //  }

    /**
     * calculates dynamic range for signal
     * @return DynamicRange
     */
    public int getDynamicRange() {

        int max = list.get(0);
        int min = list.get(0);

        for(int num : list){
            if(max<num)
                max=num;
            if(min>num)
                min=num;
        }

        return max-min;
    }
    /**
     * calculates signal energy
     * @return Signal Energy
     */
    public int getSignalEnergy() {
        int result =0;

        for (int num : list)
            result+=num*num;

        return result;
    }

    /**
     * calculates average tug on signal
     * @return Average Tug On Signal
     */
    public double getAverageTugOnSignal() {
        double result =0;

        for (int num : list)
            result+=num*num;

        result/=(double)list.size();
        return result;
    }


    /**
     * calculates average value of the signal readings
     * @return average value of the signal readings
     */
    public double getAverageValueOfTheSignalReadings(){
        double result =0;

        for (int num : list)
            result+=num;

        result/=(double)list.size();
        return result;
    }

    /**
     * calculates dispersion of processed signals
     * @return dispersion of processed signals
     */
    public double getDispersionOfProcessedSignals() {
        double result = 0;
        double m = getAverageValueOfTheSignalReadings();

        for (Integer i : list)
            result += ((i - m) * (i - m));

        result/=(double)list.size();
        return result;
    }

    /**
     * calculates autocorrelation of a discrete signal
     * @param r
     * @return autocorrelation of a discrete signal
     */
    public double getAutocorrelationOfADiscreteSignal(int r) {
        double result = 0;
        double m = getAverageValueOfTheSignalReadings();
        r = (r > 0) ? r : -r;

        for (int i = 0; i < list.size() - r; i++)
            result += (list.get(i + r) - m) * (list.get(i) - m);

        result/=(double)(list.size() - r);
        return result;
    }

    /**
     * calculates correlation interval
     * @return autocorrelation of a discrete signal
     */
    public double getCorrelationInterval() {
        double result = 0;

        for (int i = 0; i < list.size(); i++)
            result += getAutocorrelationOfADiscreteSignal(i);

        result/=(double)getAutocorrelationOfADiscreteSignal(0);
        return result;
    }
}
