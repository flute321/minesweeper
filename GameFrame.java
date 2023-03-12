import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GameFrame extends JFrame implements ActionListener{
    JButton submit;
    JTextField file;
    JFrame frame;
    JComboBox gamesBox;
    public GameFrame(){
        frame = new JFrame("Minesweeper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4,1));
        JLabel label = new JLabel("Please choose a game to play");
        String[] petStrings = { "TicTacToe", "Minesweeper"};
        gamesBox = new JComboBox<>(petStrings);
        //gamesBox.addActionListener(this);
        file = new JTextField("Please enter the size");
        submit = new JButton("Submit");
        submit.addActionListener(this);
        frame.add(label);
        frame.add(gamesBox);
        frame.add(file); 
        frame.add(submit);
        frame.setSize(200,200);
        frame.setVisible(true);
        
    }
    public void actionPerformed(ActionEvent e){
        String gametype = (String)(gamesBox.getSelectedItem());
        
        if(gametype == "TicTacToe"){
            TicTacToe t = new TicTacToe();
            frame.setVisible(false);
        }
        
        
        if(gametype == "Minesweeper"){
            try{
                int size = Integer.parseInt(file.getText());
                if(size < 3 || size > 20)
                    throw new Exception("Please choose a size between 3 & 20");
                MineBoardFrame board = new MineBoardFrame(size);  
                frame.setVisible(false);       
            }
    
            catch(Exception ex){
                file.setText(ex.getMessage());
            }
        }
        
    }
    
    //essentially creates the main gui consisting of board, time, number of flags

}