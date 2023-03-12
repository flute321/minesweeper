import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class MatchingFrame extends JFrame implements MouseListener, Game{
    ArrayList<JButton> board;
    MineBoard bacBoard;
    JFrame frame= new JFrame("Matching Game");
    JLabel label;
    JButton reset;
    JPanel panel = new JPanel();
    Colors colors = new Colors();

    public MatchingFrame(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        panel.setLayout(new GridLayout(6,6));
        label = new JLabel("Play!",SwingConstants.CENTER);
        reset = new JButton("reset");
        reset.addMouseListener(this);
        frame.add(reset,BorderLayout.PAGE_END);
        frame.add(label, BorderLayout.PAGE_START);
        board = new ArrayList<JButton>();
        for(int i = 0; i < (36); i ++){
            board.add(new JButton());
            board.get(i).setBackground(Color.GRAY);
            board.get(i).addMouseListener(this);
            panel.add(board.get(i));
        }
        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(500,500);
        frame.setVisible(true);
           
    }

    public void reset(){
        for(int i = 0; i < (36); i ++){
            board.get(i).setBackground(Color.GRAY);
            board.get(i).setEnabled(true);
        }   
        label.setText("New Game!");
        colors.reset();
    }

    public void mousePressed(MouseEvent me) {
        JButton pressed = (JButton)me.getSource();
        Color c1 = Color.BLACK;
        Color c2 = Color.BLACK;
        int index = 0;
        if(pressed == reset){
            reset();
        }

        else{
            if(me.getButton() == MouseEvent.BUTTON1 && pressed.isEnabled()) {
                 c1 = colors.getColors().get(board.indexOf(pressed));
                 index = board.indexOf(pressed);
                pressed.setBackground(c1);
            }

            else if(me.getButton() == MouseEvent.BUTTON3 && pressed.isEnabled()) {
                 c2 = colors.getColors().get(board.indexOf(pressed));
                pressed.setBackground(c2);
            }

            if(c1 == c2){
                pressed.setEnabled(false);
                board.get(index).setEnabled(false);
            }

            else if(c1 != c2){
                pressed.setBackground(Color.GRAY);
                board.get(index).setBackground(Color.GRAY);
            }

    
            if(checkWin())
                label.setText("You Win!");
        }   
     }

    public boolean checkWin(){
        for (JButton jButton : board) {
            if(jButton.isEnabled())
                return false;
        }

        return true;
    }
 
    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
     
}
