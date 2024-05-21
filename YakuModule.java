import java.util.ArrayList;
import javafx.util.Pair;
import java.util.List;


public class YakuModule {
    Hand hand;
    List<Pair<String,Integer>> List_of_yakus;
    
    public YakuModule(Hand h){
        hand = h;
        List_of_yakus = new ArrayList<>(0);
    }

    public void calculateYakus(){

    }

    public boolean hasYaku(){
        return false;
    }



    public boolean hasTanyao(){
        return true;
    }
}
