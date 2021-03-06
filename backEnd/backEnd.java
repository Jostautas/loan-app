package backEnd;

import GUI.Chart;
import GUI.Table;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.Math.*;

public class backEnd {
        // graph: 0=no input. 1=linear. 2=annuity.  startM, endM - starting and ending month. post - postpone how many months
    private int months, graph, startM, endM, post;

    //YearInterestRates. postponeProcentage
    private double amount, YIR, postP;
    public void setAmount(double amount){ this.amount = amount; }
    public void setMonths(int months){ this.months = months; }
    public void setYears(int years){ months += 12*years; }
    public void setGraph(int graph){ this.graph = graph; }
    public void setInterestRate(double YIR){ this.YIR = YIR; }
    public void setStartM(int startM){ this.startM = startM; }
    public void setEndM(int endM){ this.endM = endM; }
    public void setPost(int post){ this.post = post; }
    public void setPostProc(double postP){ this.postP = postP; }

    public int getMonths(){ return months; }
    public int getSM(){ return startM; }
    public int getEM(){ return endM; }
    public int getGraph(){ return graph; }

    private Table table;

    public void printData(){    // prints the payment data of all specified months (from x to y)
        System.out.println("amount=" + amount);
        System.out.println("months=" + months);
        System.out.println("graph=" + graph);
        System.out.println("YIR=" + YIR);

        if(startM != 0 && endM != 0){
            System.out.println("startM=" + startM + "  endM=" + endM);
        }

        if(post != 0 && postP != 0){
            System.out.println("post=" + post + "  postP=" + postP);
        }
    }


    // linear calculator
    public void linear(boolean g, boolean t){ // g-line graph, t-table
        int totalM = post + months;
        if(endM == 0){
            endM = totalM-1;
        }
        double baseMPayment = amount / (double) months;

        // monthly interest rate:
        double MIR = YIR/12;

        double[] TableBase = new double[totalM];
        double[] TableRate = new double[totalM];
        double[] TableTotal = new double[totalM];

        for(int i = 0; i < post; ++i){
            TableBase[i] = 0;
            TableRate[i] = baseMPayment*(postP*0.01);
        }
        for(int i = post; i < totalM; i++){
            TableBase[i] = baseMPayment;
            TableRate[i] = baseMPayment*(MIR*0.01);
        }

        double total=0; // how much is left to pay

        for(int i = 0; i < totalM; i++){
            total += TableBase[i] + TableRate[i];
        }

        for(int i = 0; i < totalM; i++){
            if(total > TableBase[i] + TableRate[i]){
                total -= TableBase[i] + TableRate[i];
                TableTotal[i] = total;
                if(total < 0.1){
                    total = 0;
                    TableTotal[i] = 0;
                }
            }
            else{
                total = 0; // nothing left to pay
                TableTotal[i] = 0;
            }
        }


        try{
            FileWriter of = new FileWriter("res.txt");

            for(int i = startM; i <= endM; ++i){
                System.out.println("" + (i+1) + " months payment:");
                System.out.println("   Payed = " + (TableBase[i]+TableRate[i]) + "; Left to pay = " + TableTotal[i]);
                System.out.println("   Base payment = " + TableBase[i] + "; Rate payment = " + TableRate[i]);

                of.write("" + (i+1) + " months payment:\n");
                of.write("   Payed = " + (TableBase[i]+TableRate[i]) + "; Left to pay = " + TableTotal[i] + '\n');
                of.write("   Base payment = " + TableBase[i] + "; Rate payment = " + TableRate[i] + "\n\n");
            }

            of.close();
        }catch(IOException exception){
            System.out.println("FILE OUTPUT ERROR");
        }

        if(g){  // print chart/graph
            Chart chart = new Chart("Line chart" , "Amount payed in specified months", months, startM, endM, TableBase, TableRate);
            chart.pack( );
            RefineryUtilities.centerFrameOnScreen( chart );
            chart.setVisible( true );
        }

        if(t){
            table = new Table(TableBase, TableRate, TableTotal);
        }

    }

    public void annuity(boolean g, boolean t){
        int totalM = post + months;
        if(endM == 0){
            endM = totalM-1;
        }
        double baseMPayment = amount / (double) months;

        // monthly interest rate:
        double MIR = YIR/12;

        double[] TableBase = new double[totalM];
        double[] TableRate = new double[totalM];
        double[] TableTotal = new double[totalM];

        for(int i = 0; i < post; ++i){
            TableBase[i] = 0;
            TableRate[i] = baseMPayment*(postP*0.01);
        }
        for(int i = post; i < totalM; i++){
            TableBase[i] = baseMPayment;
            TableRate[i] = amount/(((Math.pow((1+MIR), (i-post)))/MIR)-1);
        }

        double total=0; // how much is left to pay

        for(int i = 0; i < totalM; i++){
            total += TableBase[i] + TableRate[i];
        }

        for(int i = 0; i < totalM; i++){
            if(total > TableBase[i] + TableRate[i]){
                total -= TableBase[i] + TableRate[i];
                TableTotal[i] = total;
                if(total < 0.1){
                    total = 0;
                    TableTotal[i] = 0;
                }
            }
            else{
                total = 0; // nothing left to pay
                TableTotal[i] = 0;
            }
        }

        try{
            FileWriter of = new FileWriter("res.txt");

            for(int i = startM; i <= endM; ++i){
                System.out.println("" + (i+1) + " months payment:");
                System.out.println("   Payed = " + (TableBase[i]+TableRate[i]) + "; Left to pay = " + TableTotal[i]);
                System.out.println("   Base payment = " + TableBase[i] + "; Rate payment = " + TableRate[i]);

                of.write("" + (i+1) + " months payment:\n");
                of.write("   Payed = " + (TableBase[i]+TableRate[i]) + "; Left to pay = " + TableTotal[i] + '\n');
                of.write("   Base payment = " + TableBase[i] + "; Rate payment = " + TableRate[i] + "\n\n");
            }

            of.close();
        }catch(IOException exception){
            System.out.println("FILE OUTPUT ERROR");
        }

        if(g){  // print chart/graph
            Chart chart = new Chart("Line chart" , "Amount payed in specified months", months, startM, endM, TableBase, TableRate);
            chart.pack( );
            RefineryUtilities.centerFrameOnScreen( chart );
            chart.setVisible( true );
        }

        if(t){
            table = new Table(TableBase, TableRate, TableTotal);
        }
    }
}
