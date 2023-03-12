import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class MineBoardFrame extends JFrame implements MouseListener, Game{

    ArrayList<JButton> board;
    MineBoard bacBoard;
    JFrame frame= new JFrame("MineSweeper");
    int size;
    JLabel label;
    JButton reset;
    JPanel panel = new JPanel();

    public MineBoardFrame(int size){
        this.size = size;
        bacBoard = new MineBoard(size);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        panel.setLayout(new GridLayout(size,size));
        label = new JLabel("Bombs: " + bacBoard.getBombs(),SwingConstants.CENTER);
        reset = new JButton("reset");
        reset.addMouseListener(this);
        frame.add(reset,BorderLayout.PAGE_END);
        frame.add(label, BorderLayout.PAGE_START);
        board = new ArrayList<JButton>();
        for(int i = 0; i < (size*size); i ++){
            
            board.add(new JButton());
            board.get(i).setBackground(Color.GREEN);
            board.get(i).setActionCommand(i + "");
            board.get(i).addMouseListener(this);
            panel.add(board.get(i));
        }
        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(500,500);
        frame.setVisible(true);
           
    }

    public void reset(){
        for(int i = 0; i < (size*size); i ++){
            board.get(i).setBackground(Color.GREEN);
            board.get(i).setEnabled(true);
            board.get(i).setText("");
        }   
        label.setText("Bombs: " + bacBoard.getBombs());
        bacBoard.reset();
    }

    public void mousePressed(MouseEvent me) {
        JButton pressed = (JButton)me.getSource();
        //pressed.setText(board.indexOf(pressed)+"");

        if(pressed == reset){
            reset();
        }

        else{
            if(me.getButton() == MouseEvent.BUTTON1 && pressed.isEnabled()) {
                int num = bacBoard.getBoard()[board.indexOf(pressed)/size][board.indexOf(pressed)%size];
                if(num == 10){
                    pressed.setBackground(Color.BLACK);
                    lose();
                }
                    
                else if(num == 0){
                    pressed.setBackground(Color.YELLOW);
                    checksurround(board.indexOf(pressed)/size,board.indexOf(pressed)%size);
                }
                    
                else{
                    pressed.setBackground(Color.YELLOW);
                    pressed.setText(num+"");
                }
    
                pressed.setEnabled(false);
            }
    
            else if(me.getButton() == MouseEvent.BUTTON3 && pressed.isEnabled()) {
                if(pressed.getBackground() == Color.RED )
                    pressed.setBackground(Color.GREEN);
                else
                    pressed.setBackground(Color.RED);
            }
    
            if(checkWin())
                win();
        }

        
     }
 
    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
     
    public void lose(){
        label.setText("YOU LOSE");
        for (JButton b : board) {
            b.setEnabled(false);
            if(bacBoard.getBoard()[board.indexOf(b)/size][board.indexOf(b)%size] == 10)
                b.setBackground(Color.BLACK);
        }
    }

    public void win(){
        label.setText("YOU WIN");
        for (JButton b : board) {
            b.setEnabled(false);
            if(bacBoard.getBoard()[board.indexOf(b)/size][board.indexOf(b)%size] == 10)
                b.setBackground(Color.BLUE);
        }
    }

    public boolean checkWin(){
        for (JButton b : board) {
            if(b.getBackground() == Color.GREEN || b.getBackground() == Color.BLACK)
                return false;
            if(b.getBackground() == Color.RED && bacBoard.getValue(board.indexOf(b)/size, board.indexOf(b)%size) !=10)
                return false;
        }

        return true;
    }

    public void checksurround(int x, int y){
        if(x-1>=0){
            if(board.get((x-1)*size+y).isEnabled()){
                board.get((x-1)*size+y).setBackground(Color.YELLOW);
                board.get((x-1)*size+y).setEnabled(false);
                if(bacBoard.getBoard()[x-1][y] == 0)
                    checksurround(x-1, y);
                else
                    board.get((x-1)*size+y).setText(bacBoard.getValue(x-1, y)+"");
            }     
        }

        if(x+1<size){
            if(board.get((x+1)*size+y).isEnabled()){
                board.get((x+1)*size+y).setBackground(Color.YELLOW);
                board.get((x+1)*size+y).setEnabled(false);
                if(bacBoard.getBoard()[x+1][y] == 0)
                    checksurround(x+1, y);
                else
                    board.get((x+1)*size+y).setText(bacBoard.getValue(x+1, y)+"");
            }
            
        }

        if(y-1>=0){
            if(board.get((x)*size+y-1).isEnabled()){
                board.get((x)*size+y-1).setBackground(Color.YELLOW);
                board.get((x)*size+y-1).setEnabled(false);
                if(bacBoard.getBoard()[x][y-1] == 0)
                    checksurround(x, y-1);
                else
                    board.get((x)*size+y-1).setText(bacBoard.getValue(x, y-1)+"");
            }
            
            
        }

        if(y+1<size){
            if(board.get((x)*size+y+1).isEnabled()){
                board.get((x)*size+y+1).setBackground(Color.YELLOW);
                board.get((x)*size+y+1).setEnabled(false);
                if(bacBoard.getBoard()[x][y+1] == 0)
                    checksurround(x, y+1);
                else
                    board.get((x)*size+y+1).setText(bacBoard.getValue(x, y+1)+"");
            }
        
        }

    }
    
    //essentially creates the board layout of the game

}