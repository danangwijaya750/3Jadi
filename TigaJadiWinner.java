public class TigaJadiWinner{
    int playing;
    int [][] playSpace;
    public TigaJadiWinner(int playing,int [][] playSpace) {
        this.playing=playing;
        this.playSpace=playSpace;     
    }
    public boolean checkWinner(){
        boolean result=false;
        if(cekBaris())result=true;
        else if(cekKolom())result=true; 
        else if(cekDiagonal())result=true;
        return result;
    }
    boolean cekBaris(){
        boolean result=false;
        for (int i = 0; i < 3; i++) {
            if (playSpace[i][0]==playing&&playSpace[i][1]==playing&&playSpace[i][2]==playing) {
                result=true;
                break;
            }
        }
        return result;
    }
    boolean cekKolom(){
        boolean result=false;
        for (int i = 0; i < 3; i++) {
            if (playSpace[0][i]==playing&&playSpace[1][i]==playing&&playSpace[2][i]==playing) {
                result=true;
                break;
            }
        }
        return result;
    }
    boolean cekDiagonal(){
        boolean result=false;
        if (playSpace[0][0]==playing&&playSpace[1][1]==playing&&playSpace[2][2]==playing) {
                result=true;
        }
        else if (playSpace[2][0]==playing&&playSpace[1][1]==playing&&playSpace[0][2]==playing) {
            result=true;
        }
        return result;
    }
}