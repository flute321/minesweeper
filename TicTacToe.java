import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class TicTacToe extends JFrame implements Game, MouseListener{
    ArrayList<JButton> board = new ArrayList<JButton>(9);
    JFrame frame= new JFrame("TicTacToe");
    JPanel panel = new JPanel();
    JButton reset;
    JLabel label;
    byte turn = 1;

    public TicTacToe(){
        frame.setLayout(new BorderLayout());
        panel.setLayout(new GridLayout(3,3));
        reset = new JButton("reset");
        reset.addMouseListener(this);
        label = new JLabel("Player X goes first");
        for(int i = 0; i <9; i ++){ 
            board.add(new JButton());
            board.get(i).addMouseListener(this);
            panel.add(board.get(i));
        }
        
        frame.add(reset,BorderLayout.PAGE_END);
        frame.add(label, BorderLayout.PAGE_START);
        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(500,500);
        frame.setVisible(true);
    }
    
    public void reset(){
        for(int i = 0; i < board.size(); i ++){
            board.get(i).setEnabled(true);
            board.get(i).setText("");
        }
        turn = 1;
        label.setText("new game!");
    }

    public void mousePressed(MouseEvent e){
        JButton pressed = (JButton)e.getSource();
        //pressed.setText(board.indexOf(pressed)+"");

        if(pressed == reset){
            reset();
        }

        else if(pressed != reset && pressed.isEnabled()){
            pressed.setText(markerforPlayer());
            pressed.setEnabled(false);
            if(checkDraw())
                label.setText("DRAW!");
            
            if(checkWin()){
                for(int i = 0; i < board.size(); i ++){
                    board.get(i).setEnabled(false);
                    label.setText("Player " + markerforPlayer() + " WINS");
                }
            }

            turn++;
                
        }
    }


    public int countBlanks(){
        int count = 0; 
        for(int i = 0; i < board.size(); i ++){
            if(board.get(i).getText().equals(""))
                count++;
        }
        return count;

    }

    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}

    public String markerforPlayer(){
        if(turn%2 == 0)
            return XO.O.toString();
        return XO.X.toString();
    }

    public boolean checkWin(){
        String marker = markerforPlayer();

        //check horizantal and verticle
        for(int i =0; i < 3; i ++){
            if(board.get(i).getText() == marker &&  board.get(i+3).getText() == marker && board.get(6+i).getText() == marker)
                return true;
        }

        for(int i =0; i < 9; i+=3){
            if(board.get(i).getText() == marker &&  board.get(i+1).getText() == marker && board.get(2+i).getText() == marker)
                return true;
        }

        //check diagonal 
        if(board.get(0).getText() == marker &&  board.get(4).getText() == marker && board.get(8).getText()== marker)
            return true;            
        
        if(board.get(2).getText() == marker &&  board.get(4).getText() == marker && board.get(6).getText() == marker)
            return true;  

        return false;

    }

    public boolean checkDraw(){
        
        if(!checkWin() && countBlanks() == 0)
            return true;
        

        return false;
    }
}
