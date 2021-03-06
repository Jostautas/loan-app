//Jostautas Sakas
package GUI;

import backEnd.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoanGUI extends JFrame{
    private JPanel mainPanel;
    private JTextField amount;
    private JLabel amountLabel;
    private JTextField payingTimeM;
    private JLabel PTMLabel;
    private JRadioButton AGButton;
    private JRadioButton LGButton;
    private JTextField payingTimeY;
    private JLabel PTYLabel;
    private JTextField interestRate;
    private JLabel IRLabel;
    private JButton printTable;
    private JTextField startMonth;
    private JTextField endMonth;
    private JButton printChart;
    private JTextField postpone;
    private JTextField postponeProc;
    private JButton Check;
    private JLabel status;
    private JCheckBox postButton;
    private JLabel startMLabel;
    private JLabel endMLabel;
    private JLabel postTLabel;
    private JLabel postPLabel;
    private JCheckBox customButton;
    private JButton displTable;

    static public backEnd a = new backEnd();

    public LoanGUI(String title){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        Check.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                boolean OK = true;

                //amount:
                double amountParsed;
                String text = amount.getText();
                try{
                    amountParsed = Double.parseDouble(text);
                    a.setAmount(amountParsed);
                }
                catch(Exception exception){
                    amount.setText("ERROR");
                    OK = false;
                }

                //months:
                int monthsParsed;
                text = payingTimeM.getText();
                try{
                    monthsParsed = Integer.parseInt(text);
                    a.setMonths(monthsParsed);
                }
                catch(Exception exception){
                    payingTimeM.setText("ERROR");
                    OK = false;
                }

                //year:
                int yearsParsed;
                text = payingTimeY.getText();
                try{
                    yearsParsed = Integer.parseInt(text);
                    a.setYears(yearsParsed);
                }
                catch(Exception exception){
                    payingTimeY.setText("ERROR");
                    OK = false;
                }

                //yearly interest rates:
                double rate;
                text = interestRate.getText();
                try{
                    rate = Double.parseDouble(text);
                    a.setInterestRate(rate);
                }
                catch(Exception exception){
                    interestRate.setText("ERROR");
                    OK = false;
                }

                // if user sets custom range of months to print out
                if(customButton.isSelected()){

                    //Starting month:
                    monthsParsed = 0;
                    text = startMonth.getText();
                    try{
                        monthsParsed = Integer.parseInt(text);
                        if(monthsParsed >= 1 && monthsParsed <= a.getMonths()){
                            a.setStartM(monthsParsed-1);
                        }
                        else{
                            startMonth.setText("ERROR");
                            OK = false;
                        }
                    }
                    catch(Exception exception){
                        startMonth.setText("ERROR");
                        OK = false;
                    }

                    //Ending month:
                    monthsParsed = 0;
                    text = endMonth.getText();
                    try{
                        monthsParsed = Integer.parseInt(text);
                        if(monthsParsed-1 >= a.getSM() && monthsParsed <= a.getMonths()){
                            a.setEndM(monthsParsed-1);
                        }
                        else{
                            endMonth.setText("ERROR");
                            OK = false;
                        }
                    }
                    catch(Exception exception){
                        endMonth.setText("ERROR");
                        OK = false;
                    }
                }

                // if user wants to postpone payments
                if(postButton.isSelected()){

                    //months to mostpone:
                    monthsParsed = 0;
                    text = postpone.getText();
                    try{
                        monthsParsed = Integer.parseInt(text);
                        if(monthsParsed >= 1){
                            a.setPost(monthsParsed);
                        }
                        else{
                            postpone.setText("ERROR");
                            OK = false;
                        }
                    }
                    catch(Exception exception){
                        postpone.setText("ERROR");
                        OK = false;
                    }

                    //Procentage:
                    double procentage = 0;
                    text = postponeProc.getText();
                    try{
                        procentage = Double.parseDouble(text);
                        if(procentage > 0){
                            a.setPostProc(procentage);
                        }
                        else{
                            postponeProc.setText("ERROR");
                            OK = false;
                        }
                    }
                    catch(Exception exception){
                        postponeProc.setText("ERROR");
                        OK = false;
                    }
                }

                //radio buttons:
                if(AGButton.isSelected()){
                    a.setGraph(2);
                }
                else if(LGButton.isSelected()){
                    a.setGraph(1);
                }
                else{
                    status.setText("Error: please select a graph");
                    a.setGraph(0);
                }

                // validation
                if(OK == false){
                    status.setText("There are errors");
                }
                else{
                    status.setText("Data entered successfully!");
                    a.printData();
                }

            }
        });
        printTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("graph=" + a.getGraph());
                if(a.getGraph() == 1)
                    a.linear(false, false); // linear(graph(true/false))
                else if(a.getGraph() == 2){
                    a.annuity(false, false);
                }
                else{
                    System.out.println("ERROR - select a graph");
                }
            }
        });

        printChart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(a.getGraph() == 1)
                    a.linear(true, false); // linear(graph(true/false))
                else if(a.getGraph() == 2)
                    a.annuity(true, false);
                else{
                    System.out.println("ERROR - select a graph2");
                }

            }
        });
        displTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(a.getGraph() == 1)
                    a.linear(false, true); // linear(no linear graph, display table)
                else if(a.getGraph() == 2){
                    a.annuity(false, true);
                }
                else{
                    System.out.println("ERROR3 - select a graph");
                }
            }
        });
    }

    public static void main(String[] args){
        JFrame frame = new LoanGUI("Loan calculator");
        frame.setVisible(true);

    }

}
