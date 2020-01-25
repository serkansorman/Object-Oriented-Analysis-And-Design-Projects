package sample.Q2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;


public class GUI extends JFrame{
    private TextField[][] aTextFields;
    JComboBox solutionVersionCombo;
    SolutionVersion solutionVersion;
    JLabel resultTime;
    JPanel AmatrixP;
    long estimatedTimeMultiThreaded;
    long mainThreadTime;

    private JPanel panel;
    private JComboBox<Integer> matrixSizeCombo;
    private JComboBox<Integer> threadSizeCombo;

    private JPanel downP;
    private int n;
    private List<Thread> threadList;
    private Thread mainDFT;
    private int numberOfThreads;
    private Complex[][] DFTResult;
    int counter = 0;
    boolean isStartClicked = false;

    public static void main(String... args) throws InterruptedException {
        
        GUI gui = new GUI();
        
    }

    /**
     * Runs dft when start clicking
     * @throws InterruptedException
     */
    void runDFT() throws InterruptedException {


        Complex[][] m1 = new Complex[n][n];
        Complex[][] m2 = new Complex[n][n];
        Complex[][] sumResult = new Complex[n][n];
        DFTResult = new Complex[n][n];

        Random r = new Random();
        for(int i=0; i<n; ++i){
            for(int j=0; j<n; ++j){
                m1[i][j] = new Complex(1 + (10) * r.nextDouble(),1 + (10) * r.nextDouble());
                m2[i][j] = new Complex(1 + (10) * r.nextDouble(),1 + (10) * r.nextDouble());
            }
        }

        threadList = new ArrayList<>();
        List<DFT> dftList = new ArrayList<>();
        switch (numberOfThreads){
            case 1:
                dftList.add(new DFT(solutionVersion,new Parameters(DFTResult,sumResult,numberOfThreads,0,0,m1,m2,n),0));
                break;
            case 2:
                dftList.add(new DFT(solutionVersion,new Parameters(DFTResult,sumResult,numberOfThreads,0,0,m1,m2,n),0));
                dftList.add(new DFT(solutionVersion,new Parameters(DFTResult,sumResult,numberOfThreads,n/2,0,m1,m2,n),1));
                break;
            case 4:
                dftList.add(new DFT(solutionVersion,new Parameters(DFTResult,sumResult,numberOfThreads,0,0,m1,m2,n),0));
                dftList.add(new DFT(solutionVersion,new Parameters(DFTResult,sumResult,numberOfThreads,0,n/2,m1,m2,n),1));
                dftList.add(new DFT(solutionVersion,new Parameters(DFTResult,sumResult,numberOfThreads,n/2,0,m1,m2,n),2));
                dftList.add(new DFT(solutionVersion,new Parameters(DFTResult,sumResult,numberOfThreads,n/2,n/2,m1,m2,n),3));
                break;
            case 8:
                dftList.add(new DFT(solutionVersion,new Parameters(DFTResult,sumResult,numberOfThreads,0,0,m1,m2,n),0));
                dftList.add(new DFT(solutionVersion,new Parameters(DFTResult,sumResult,numberOfThreads,0,n/2,m1,m2,n),1));
                dftList.add(new DFT(solutionVersion,new Parameters(DFTResult,sumResult,numberOfThreads,n/4,0,m1,m2,n),2));
                dftList.add(new DFT(solutionVersion,new Parameters(DFTResult,sumResult,numberOfThreads,n/4,n/2,m1,m2,n),3));

                dftList.add(new DFT(solutionVersion,new Parameters(DFTResult,sumResult,numberOfThreads,n/2,0,m1,m2,n),4));
                dftList.add(new DFT(solutionVersion,new Parameters(DFTResult,sumResult,numberOfThreads,n/2,n/2,m1,m2,n),5));
                dftList.add(new DFT(solutionVersion,new Parameters(DFTResult,sumResult,numberOfThreads,3*n/4,0,m1,m2,n),6));
                dftList.add(new DFT(solutionVersion,new Parameters(DFTResult,sumResult,numberOfThreads,3*n/4,n/2,m1,m2,n),7));
                break;
        }

        for(DFT dft : dftList)
            threadList.add(new Thread(dft));

        long startTimeMultiThreaded = System.currentTimeMillis();
        for(Thread thread : threadList)
            thread.start();
        for(Thread thread : threadList)
            thread.join();
        estimatedTimeMultiThreaded = System.currentTimeMillis() - startTimeMultiThreaded;
        if(numberOfThreads != 1)
            mainThreadTime = estimatedTimeMultiThreaded;

        System.out.println("Multithreaded time: " + estimatedTimeMultiThreaded+"ms");

    }

    public GUI(){
        super();
        this.setTitle("Discreet Fourier Transform");
        downP = new JPanel();
        GUIInit();
    }

    /**
     * Init up and down panel
     */
    private void GUIInit() {
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(600, 400));
        panel.setBackground(Color.LIGHT_GRAY); 
        this.add(panel);
        panel.setLayout(new FlowLayout());
        panel.add(upPanel());

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     * Panel that has buttons and combo boxes
     * @return
     */
    private JPanel upPanel(){
        JPanel retPanel = new JPanel(new FlowLayout());
        retPanel.setPreferredSize(new Dimension(900, 60));
        retPanel.setBackground(Color.GREEN);
        Integer[] matrixSizes = new Integer[12];
        Integer[] threadSize = new Integer[4];

        int x = 2;
        for(int i = 0; i < 12; i++){
            matrixSizes[i] = (int)Math.pow(2,x);
            ++x;
        }


        for(int i = 0; i < 4; i++)
            threadSize[i] = (int) Math.pow(2,i);

        matrixSizeCombo = new JComboBox<>(matrixSizes);
        threadSizeCombo = new JComboBox<>(threadSize);

        JLabel firstTag = new JLabel("Matrix size: ");
        firstTag.setForeground(Color.black);
        JLabel secondTag = new JLabel("Number of thread: ");
        secondTag.setForeground(Color.black);

        JLabel thirdTag = new JLabel("Solution Version: ");
        thirdTag.setForeground(Color.black);
        solutionVersionCombo = new JComboBox<>();
        solutionVersionCombo.addItem("OldVersion");
        solutionVersionCombo.addItem("NewVersion");

        JButton generateMatrix = new JButton("Start");

        resultTime = new JLabel();
        /**
         * generateMatrix butonunun aktif dinleyici(ActionListener) metodu.
         * Matislerin girilecegi metin alanlarini, matris boyutlarina gore olusturur ve panele ekler.
         */
        generateMatrix.addActionListener((ActionEvent ae) -> {
            isStartClicked = true;
            n = (int) matrixSizeCombo.getSelectedItem();
            numberOfThreads = (int) threadSizeCombo.getSelectedItem();

            String solutionVersion = solutionVersionCombo.getSelectedItem().toString();
            if(solutionVersion.equals("OldVersion"))
                this.solutionVersion = new OldVersion();
            else
                this.solutionVersion = new NewVersion();



            DFT.completedPartial = 0;
            mainDFT = new Thread(() -> {
                try {
                    resultTime.setText("Calculating DFT...");
                    runDFT();

                    resultTime.setText("Total time:"+estimatedTimeMultiThreaded+"ms");
                    if(n == 4){
                        for(int i = 0; i < n; i++){
                            for(int j = 0; j < n; j++){
                                aTextFields[i][j].setText(DFTResult[i][j].toString());
                            }
                        }
                    }


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            mainDFT.start();

            if(counter==0 && n == 4){
                panel.remove(downP);
                downP = downPanel();
                panel.add(downP);
                setContentPane(panel);
                ++counter;
            }

        });

        JButton cancel = new JButton("Cancel");
        cancel.addActionListener((ae) -> {
            if(threadList != null)
                for(Thread thread : threadList)
                    thread.stop();
            if(mainDFT != null){
                mainDFT.stop();
                System.out.println("Calculation is cancelled");
                resultTime.setText("Calculation is cancelled");
            }

        });

        JButton compare = new JButton("Compare With Single Thread");
        compare.addActionListener((ae) -> {


            if(!isStartClicked){
                resultTime.setText("Click Start before the compare!!!");
            }
            else{
                Thread compareThread = new Thread(() -> {
                    try {

                        resultTime.setText("DFT is calculating...");
                        DFT.completedPartial = 0;
                        numberOfThreads = 1;
                        runDFT();
                        resultTime.setText("Time gain:"+ (estimatedTimeMultiThreaded - mainThreadTime) +"ms");


                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });

                compareThread.start();
            }



        });

        retPanel.add(firstTag);
        retPanel.add(matrixSizeCombo);
        retPanel.add(secondTag);
        retPanel.add(threadSizeCombo);
        retPanel.add(thirdTag);
        retPanel.add(solutionVersionCombo);
        retPanel.add(generateMatrix);
        retPanel.add(cancel);
        retPanel.add(compare);
        retPanel.add(resultTime);


        return retPanel;
    }

    /**
     * Panel shows result matrix
     * @return
     */
    private JPanel downPanel(){
        JPanel retPanel = new JPanel(new BorderLayout());
        retPanel.setPreferredSize(new Dimension(800, 600));

        AmatrixP = new JPanel();
        createTextFields();

        AmatrixP.setLayout(null);
        AmatrixP.setPreferredSize(new Dimension(n * 50, n * 35));

        int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS ;
        int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS ;

        JScrollPane aScroll = new JScrollPane(AmatrixP,v,h);
        aScroll.setBorder(new LineBorder(Color.red,5));
        aScroll.setPreferredSize(new Dimension(800, 600));
        retPanel.add(aScroll, BorderLayout.WEST);
        return retPanel;
    }

    /**
     * Create text fields for result matrix
     */
    private void createTextFields(){
        JLabel[][] matrixLabel = new JLabel[n][n];
        aTextFields = new TextField[n][n];
        for(int i = 0; i < n; i++){
            matrixLabel[0][i] = new JLabel("A"+i);
            matrixLabel[0][i].setBounds(48*i+45, 0, 30, 25);
            AmatrixP.add(matrixLabel[0][i]);
        }
        for(int i = 0; i < n; i++){
            matrixLabel[i][0] = new JLabel("A"+i);
            matrixLabel[i][0].setBounds(5, 33*i+30, 30, 25);
            AmatrixP.add(matrixLabel[i][0]);
            for(int j = 0; j < n; j++){
                aTextFields[i][j] = new TextField();
                aTextFields[i][j].setBounds(48*j+45, 33*i+30, 60, 25);
                AmatrixP.add(aTextFields[i][j]);
            }
        }

    }

    public static void printMatrix(Complex[][] m,int n){
        for(int i=0; i<n; ++i){
            for(int j=0; j<n; ++j){
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }
    }
}