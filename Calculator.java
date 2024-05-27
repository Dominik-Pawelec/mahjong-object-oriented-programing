import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.util.Pair;


public class Calculator {
    
    public Calculator(){

    }

    public int shanten(Hand h){

        if(h.size() != 14){System.out.println("WRONG SIZE OF HAND");return -1;}


        int opened = 0;
        Hand hand = new Hand(h);

        opened += 2 * hand.getOpened().size();

        int max_shanten = 6;
        List<Integer[]> all_shapes = possibleShapes(hand, 0, 0);

        int groups = 0; int not_full = 0; 
        for(int i = 0; i < all_shapes.size(); i++){
            groups = opened + all_shapes.get(i)[0];
            not_full = all_shapes.get(i)[1];

            int temp = 8 - 2 * groups - not_full//Math.max(not_full, hand.size()/3 - groups)
                    + Math.min(1,Math.max(0,not_full - (4 - groups)));
            if(max_shanten > temp){
                max_shanten = temp;
            }
        }
        return max_shanten;
    }
    
    public List<Integer []> possibleShapes(TileGroup tg,int complete, int not_complete){
        if(tg.size() == 0){
            List<Integer []> output = new ArrayList<>(0);
            output.add(new Integer[]{complete,not_complete});
            return output;
        }
        TileGroup tg_copy = new TileGroup(tg);
        Tile t = tg_copy.get(0);
        tg_copy.remove(t);
        tg_copy.sort();//nie konieczne?

        TileGroup tg_temp = new TileGroup(tg_copy);

        List<Integer []> out_aaa = new ArrayList<>(0);
        if(tg_copy.nrOfElem(t) > 1){
            tg_temp = new TileGroup(tg_copy);
            tg_temp.remove(t);
            tg_temp.remove(t);
            out_aaa = possibleShapes(tg_temp, complete + 1, not_complete);
        }
        List<Integer []> out_aa = new ArrayList<>(0);
        if(tg_copy.nrOfElem(t) > 0){
            tg_temp = new TileGroup(tg_copy);
            tg_temp.remove(t);
            out_aa = possibleShapes(tg_temp, complete, not_complete + 1);
        }
        List<Integer []> out_ab = new ArrayList<>(0);
        if(tg_copy.nrOfElem(new Tile(t.getNr() + 1, t.getFamily())) > 0){
            tg_temp = new TileGroup(tg_copy);
            tg_temp.remove(new Tile(t.getNr() + 1, t.getFamily()));
            out_ab = possibleShapes(tg_temp, complete, not_complete + 1);
        }
        List<Integer []> out_ac = new ArrayList<>(0);
        if(tg_copy.nrOfElem(new Tile(t.getNr() + 2, t.getFamily())) > 0){
            tg_temp = new TileGroup(tg_copy);
            tg_temp.remove(new Tile(t.getNr() + 2, t.getFamily()));
            out_ab = possibleShapes(tg_temp, complete, not_complete + 1);
        }
        List<Integer []> out_abc = new ArrayList<>(0);
        if((tg_copy.nrOfElem(new Tile(t.getNr() + 1, t.getFamily())) > 0)&&(tg_copy.nrOfElem(new Tile(t.getNr() + 2, t.getFamily())) > 0)){
            tg_temp = new TileGroup(tg_copy);
            tg_temp.remove(new Tile(t.getNr() + 1, t.getFamily()));
            tg_temp.remove(new Tile(t.getNr() + 2, t.getFamily()));
            out_abc = possibleShapes(tg_temp, complete + 1, not_complete);
        }

        List<Integer []> out_none = possibleShapes(tg_copy, complete, not_complete);

        List<Integer []> out_merged = new ArrayList<>(0);
        out_merged.addAll(out_aaa);
        out_merged.addAll(out_aa);
        out_merged.addAll(out_ab);
        out_merged.addAll(out_ac);
        out_merged.addAll(out_abc);
        out_merged.addAll(out_none);

        return out_merged;
    }

   

    
    
    
}

