import java.awt.*;
import java.util.ArrayList;

public class Colors {
    ArrayList<Color> colors = new ArrayList<>();
    ArrayList<Color> colorset = new ArrayList<>();

    ArrayList<Color> getColors(){
        return colors;
    }

    public Colors(){
        for(int j = 0; j < (36); j ++){
           colors.add(Color.BLACK);
        }
        
        colorset.add(Color.BLACK);
        colorset.add(Color.LIGHT_GRAY);
        colorset.add(Color.BLUE);
        colorset.add(Color.RED);
        colorset.add(Color.ORANGE);
        colorset.add(Color.YELLOW);
        colorset.add(Color.GREEN);
        colorset.add(Color.PINK);
        colorset.add(Color.CYAN);
        colorset.add(Color.MAGENTA);
        colorset.add(new Color(100, 100, 255));
        colorset.add(new Color(255, 100, 255));
        colorset.add(new Color(100, 255, 255));
        colorset.add(new Color(100, 100, 100));
        colorset.add(new Color(100, 100, 0));
        colorset.add(new Color(100, 0, 255));
        colorset.add(new Color(250, 0, 200));
        colorset.add(new Color(10, 200, 98));
         

        for(int i = 0; i < (18); i ++){ 
            for(int m = 0; m < 2; m++){
                int index = (int)Math.random()*36;
                if(colors.get(index).equals(Color.BLACK)){
                    colors.set(index, colorset.get(i));
                }
                else{
                    while(!colors.get(index).equals(Color.BLACK)){
                        index = (int)Math.random()*36;
    
                    }
                    colors.set(index, colorset.get(i));
                }
            }        
        }
    }

    public void reset(){
        for(int i = 0; i < (36); i ++){
            colors.add(Color.BLACK);
         }

        for(int i = 0; i < (18); i ++){
            int index = (int)Math.random()*36;
            for(int m = 0; m < 2; m++){
                if(colors.get(index).equals(Color.BLACK)){
                    colors.set(index, colorset.get(i));
                }
                else{
                    while(!colors.get(index).equals(Color.BLACK)){
                        index = (int)Math.random()*36;
    
                    }
                    colors.set(index, colorset.get(i));
                }

            }
            
        }
    }
}
