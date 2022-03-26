package backEnd;

public class backEnd {

        // graph: 0=no input. 1=linear. 2=annuity.  startM, endM - starting and ending month. post - postpone how many months
    private int months, graph, startM, endM, post;

    //YearInterestRates. postponeProcentage
    private double amount, YIR, postP;

    public void setAmount(double amount){
        this.amount = amount;
    }

    public void setMonths(int months){
        this.months = months;
    }

    public void setYears(int years){
        months += 12*years;
    }

    public void setGraph(int graph){
        this.graph = graph;
    }

    public void setInterestRate(double YIR){
        this.YIR = YIR;
    }

    public void setStartM(int startM){ this.startM = startM; }

    public void setEndM(int endM){ this.endM = endM; }

    public int getMonths(){ return months; }

    public int getSM(){ return startM; }

    public void setPost(int post){ this.post = post; }

    public void setPostProc(double postP){ this.postP = postP; }



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

    //  annuity calculator

    //  public void printChart(){}

}
