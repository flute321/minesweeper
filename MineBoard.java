public class MineBoard{
    int[][] board;
    int bombs;
    int size;
    /**
     * 
     * @param size by one side of a square board
     */
    public MineBoard(int size){
        this.size = size;
        reset();
    }

    /**
     * 
     * @return the array contain the board values
     */
    public int[][] getBoard(){
        return board;
    }
    /**
     * 
     * @return
     */
    public int getBombs(){
        return bombs;
    }

    public void reset(){
        board = new int[size][size];
        bombs = size*size/7;
        int count = 0; 

        //creates the mine array where 0 indicates black space and 10 represents the bombs
        for(int i = 0; i < size; i ++){
            for(int j = 0; j < size; j ++){
                board[i][j] = 0;
                if(Math.random() > 0.85 && count < bombs){
                    board[i][j] = 10;
                    count ++;
                }

            }
        } 
        //ensuring the correct number of bombs are placed in case the math.random didn't place all of them
        if(count != bombs){
            
            for(int i = 0; i < size; i ++){
                if(count == bombs)
                        break;
                for(int j = 0; j < size; j ++){
                    if(count == bombs)
                        break;

                    if(board[i][j] != 10){
                        board[i][j] = 10;
                        count ++;
                    }

                }
            }
            
        }

        for(int i = 0; i < size; i ++){
            for(int j = 0; j < size; j ++){
                if(board[i][j] == 10)
                    assignNumbers(i, j);
            
            }
        }

    
    }

    public void assignNumbers(int i, int j){

        if(i-1 >= 0){
            if(board[i-1][j] != 10)
                board[i-1][j] += 1;
            if(j-1 >= 0)
                if(board[i-1][j-1] != 10) 
                    board[i-1][j-1] += 1;
            if(j+1 < board.length)
                if(board[i-1][j+1] != 10) 
                    board[i-1][j+1] += 1;
        }

        if(i+1 < board.length){
            if(board[i+1][j] != 10)
                board[i+1][j] += 1;
            if(j-1 >= 0)
                if(board[i+1][j-1] != 10) 
                    board[i+1][j-1] += 1;
            if(j+1 < board.length)
                if(board[i+1][j+1] != 10) 
                    board[i+1][j+1] += 1;
        }

        if(j-1 >= 0)
                if(board[i][j-1] != 10) 
                    board[i][j-1] += 1;
        if(j+1 < board.length)
            if(board[i][j+1] != 10) 
                board[i][j+1] += 1;
    }



    public int getValue(int i, int j){
        return board[i][j];
    }

    public String toString(){
        String s = "";
        for(int i = 0; i < board.length; i ++){
            for(int j = 0; j < board.length; j ++){
                s += board[i][j] + " ";

            }
            s+= '\n';
        } 
        
        return s;
    }

}
