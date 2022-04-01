package GUI;

import backEnd.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

import static GUI.LoanGUI.a;

public class Table extends JFrame{

    private int startM = a.getSM();
    private int endM = a.getEM();
    private JPanel panel;
    private JOptionPane table;

    public Table(double[] TableBase, double[] TableRate, double[] TableTotal) {

        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(new FlowLayout());
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });

        panel = new JPanel(new GridLayout(0, 1, 5, 5));

        for(int i = startM; i <= endM; ++i) {
            panel.add(new JLabel("" + (i + 1) + " months payment:"));
            panel.add(new JLabel("   Payed = " + (new DecimalFormat("##.##").format(TableBase[i] + TableRate[i])) + "; Left to pay = " + (new DecimalFormat("##.##").format(TableTotal[i]))));
            panel.add(new JLabel("   Base payment = " + (new DecimalFormat("##.##").format(TableBase[i])) + "; Rate payment = " + (new DecimalFormat("##.##").format(TableRate[i])) ));
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(600, 600));
        table = new JOptionPane(scrollPane);
        JDialog dialog = table.createDialog("Payment table");
        dialog.setVisible(true);

    }

}