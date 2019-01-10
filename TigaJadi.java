import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TigaJadi extends JFrame{
    final int player2 = 2;
    final int player1 = 1;
    final int kosong = 0;
    int playing = 1;
    int player1Gacuk=0;
    int player2Gacuk=0;
    int fromRow=-1;
    int fromCol=-1;
    public TigaJadi() {
    add(canvas);
    setTitle("3 Jadi");
    setSize(350,350);
    setLocationRelativeTo(null);   
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    canvas.addMouseListener(new MouseListener(){
            @Override
            public void mouseReleased(MouseEvent e) {}
    
            @Override
            public void mousePressed(MouseEvent e) {}
    
            @Override
            public void mouseExited(MouseEvent e) {}
        
            @Override
            public void mouseEntered(MouseEvent e) {}
        
            @Override
            public void mouseClicked(MouseEvent e) {
                int offset = canvas.getParent().getSize().height-canvas.getSize().height;
                int row = (int)Math.floor((e.getY())/100);
                int col = (int)Math.floor((e.getX())/100);
                System.out.print("player: "+playing+" in row: "+row+", col: "+col+"\n");
                if(playing==player1){
                    if(player1Gacuk<=3){
                        player1Gacuk++;
                    }
                }else if(playing==player2){
                    if(player2Gacuk<=3){
                        player2Gacuk++;
                    }
                }
                System.out.println("player1 gacuk"+player1Gacuk);
                System.out.println("player2 gacuk"+player2Gacuk);
                actionOfPlayer(playing,row,col);
            }

    });
    JOptionPane.showMessageDialog(null, "Game 3 Jadi, Drop 3 Gacuk terlebih dahulu");
    JOptionPane.showMessageDialog(null, "Pemain ke-"+this.playing+" drop gacuk dahulu");
}
    public void actionOfPlayer(int playing,int row, int col){
        if(playSpace[row][col]==0){
            if(playing==1){
                if(player1Gacuk<=3){
                    changePlayer(playing, row, col);
                }else{
                    JOptionPane.showMessageDialog(null,"gacuk sudah 3");
                }
            }else if(playing==2){
                if(player2Gacuk<=3){
                    changePlayer(playing, row, col);
                }else{
                    JOptionPane.showMessageDialog(null,"gacuk sudah 3");
                }
            }
        
        }else if(playSpace[row][col]==playing){
            if(playing==1){
                player1Gacuk=player1Gacuk-2;
            }
            else if(playing==2){
                player2Gacuk=player2Gacuk-2;
            } 
            playSpace[row][col]=0;
            fromCol=col;
            fromRow=row;
            canvas.repaint();
            System.out.println("player1 gacuk"+player1Gacuk);
            System.out.println("player2 gacuk"+player2Gacuk);
            System.out.println("fromRow : "+fromCol);
            System.out.println("fromCol : "+fromRow);
        }else {
            if(playing==1){
                player1Gacuk=player1Gacuk-1;
            }else if(playing==2){
                player2Gacuk=player2Gacuk-1;
            }
            JOptionPane.showMessageDialog(null,"Ini bukan gacuk! mu");
        }
    }

    boolean trueMoving(int playing, int row, int col) {
        boolean result=false;
        if(playSpace[row][col]==kosong) {result= true;}
        if(fromRow>=0||fromCol>=0){
            if(col-fromCol==2||col-fromCol==-2){result= false;}
            if(row-fromRow==2||row-fromRow==-2){result=false;}
        }
        return result;
    }
    void changePlayer(int playing, int row, int col) {
        if(row>=0 && row<playSpace.length && col >= 0 && col < playSpace[row].length) {
            if(trueMoving(playing, row, col)) {
                playSpace[row][col] = playing;
                canvas.repaint();               
                TigaJadiWinner tJadiWinner= new TigaJadiWinner(playing,playSpace);
                if(tJadiWinner.checkWinner()){
                    JOptionPane.showMessageDialog(null,"pemenang adalah pemain ke-"+playing);
                    resetGame();
                }
                else{
                    this.playing = (playing==player2)?player1:player2;
                    JOptionPane.showMessageDialog(null,"ganti pemain ke-"+this.playing);
            }
        }else{
            System.out.println("not true move");
            playSpace[fromRow][fromCol] = playing;
            JOptionPane.showMessageDialog(null,"Pergerakan dilarang");
            canvas.repaint();
        }
        }
    }
    int[][] playSpace= new int[][]{
        {0,0,0},
        {0,0,0},
        {0,0,0}
    };

    public void resetGame(){
        playing = 1;
        fromRow=-1;
        fromCol=-1;
        player1Gacuk=0;
        player2Gacuk=0;
        playSpace= new int[][]{
            {0,0,0},
            {0,0,0},
            {0,0,0}};
        canvas.repaint();
    }    

    Canvas canvas = new Canvas() {

        public void paint(Graphics g) {
            g.setColor(java.awt.Color.decode("#FFFF"));
            g.fillRect(0,0,800,800);
            for(int row = 0; row < playSpace.length; row++) {
                for(int col = 0; col < playSpace[row].length; col++) {
                    g.setColor(java.awt.Color.black);
                    g.drawRect(row*100, col*100, 100,100);
                }
            }
            for(int row = 0; row < playSpace.length; row++) {
                for(int col = 0; col < playSpace[row].length; col++) {
                    if(playSpace[row][col]==1) {
                        g.setColor(Color.decode("#FFFFFF"));
                        g.fillOval(col*100+10, row*100+10, 80,80); 
                    } else if (playSpace[row][col]==2) {
                        g.setColor(Color.decode("#000000"));
                        g.fillOval(col*100+10, row*100+10, 80,80);
                    }
                }
            }
        }
    };

    public static void main(String[] args) {
        TigaJadi tJadi= new TigaJadi();
        tJadi.show();
    }    
}