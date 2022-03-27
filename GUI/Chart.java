package GUI;

import backEnd.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class Chart extends ApplicationFrame{
    int months, startM, endM;
    private void setM(int months){ this.months = months;}
    private void setSM(int startM){ this.startM = startM;}
    private void setEM(int endM){ this.endM = endM;}
    double[] TableBase = new double[months];
    double[] TableRate = new double[months];
    private void setTB(double TableBase[]){this.TableBase = TableBase;}
    private void setTR(double TableRate[]){this.TableRate = TableRate;}


    public Chart( String applicationTitle , String chartTitle, int months, int startM, int endM, double TableBase[], double TableRate[]) {
        super(applicationTitle);

        setM(months); setSM(startM); setEM(endM); setTB(TableBase); setTR(TableRate);
        System.out.println("startM in chart=" + this.startM);

        JFreeChart lineChart = ChartFactory.createLineChart(
                chartTitle,
                "Months","Amount payed",
                createDataset(),
                PlotOrientation.VERTICAL,
                true,true,false);

        ChartPanel chartPanel = new ChartPanel( lineChart );
        //chartPanel.setPreferredSize( new java.awt.Dimension( 560, 367 ) );
        setContentPane( chartPanel );
    }

    private DefaultCategoryDataset createDataset(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
        for(int i = startM; i <= endM; ++i){
            dataset.addValue((TableBase[i]+TableRate[i]), "Amount", (""+(i+1)));
        }

        return dataset;
    }

}