import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(1.5%1);
        MainWindow mw = new MainWindow( );
        mw.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        mw.setSize(800,600);
        mw.setVisible(true);
    }
}