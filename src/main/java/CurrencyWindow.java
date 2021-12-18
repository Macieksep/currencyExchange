import javax.swing.*;

public class CurrencyWindow extends JFrame {

    ImageIcon icon = new ImageIcon("img\\convertionIcon.png");

    CurrencyWindow(String title){

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(300,300,495,120);
        this.setIconImage(icon.getImage());
        this.setResizable(false);
        this.setTitle(title);

    }

}
