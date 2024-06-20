import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;


public class Calculator {
    
    public Calculator(){
        //potencjalnie przepisać na: getInstance()
    }

    public int shanten(Hand h){
        int opened = 0;
        Hand hand = new Hand(h);

        opened += hand.getOpened().size();

        int max_shanten = 6;
        List<Integer[]> all_shapes = possibleShapes(hand, 0, 0, false);

        int groups = 0; int not_full = 0; int not_pair;
        for(int i = 0; i < all_shapes.size(); i++){
            groups = opened + all_shapes.get(i)[0];
            not_full = all_shapes.get(i)[1];
            not_pair = 1 - all_shapes.get(i)[2];

            int temp = 8 - 2 * groups - not_full//Math.max(not_full, hand.size()/3 - groups)
                    + Math.min(1,Math.max(0,not_full - (4 - groups))) + not_pair;
            if(max_shanten > temp){
                max_shanten = temp;
            }
        }

        //7 pairs:
        Hand hand_copy = new Hand(h);//kopiuje tylko część zamkniętą ręki (czyli zamierzony efekt)
        
        TileGroup pair_tiles = new TileGroup();

        TileGroup all_tiles = new TileGroup("all");

        for(int i = 0; i < all_tiles.size(); i++){
            if(hand_copy.nrOfElem(all_tiles.get(i)) > 1 && pair_tiles.nrOfElem(all_tiles.get(i)) == 0){
                pair_tiles.add(all_tiles.get(i));
            }
        }

        int chitoi_shanten = 6 - pair_tiles.size();


        return Math.min(max_shanten, chitoi_shanten);
    }
    
    public List<Integer []> possibleShapes(TileGroup tg,int complete, int not_complete, boolean contains_pair){
        if(tg.size() == 0){
            List<Integer []> output = new ArrayList<>(0);
            if(contains_pair){output.add(new Integer[]{complete,not_complete, 1});}
            else{ output.add(new Integer[]{complete,not_complete, 0});}
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
            out_aaa = possibleShapes(tg_temp, complete + 1, not_complete, contains_pair);
        }
        List<Integer []> out_aa = new ArrayList<>(0);
        if(tg_copy.nrOfElem(t) > 0){
            tg_temp = new TileGroup(tg_copy);
            tg_temp.remove(t);
            out_aa = possibleShapes(tg_temp, complete, not_complete + 1, true);
        }
        List<Integer []> out_ab = new ArrayList<>(0);
        if(tg_copy.nrOfElem(new Tile(t.getNr() + 1, t.getFamily())) > 0){
            tg_temp = new TileGroup(tg_copy);
            tg_temp.remove(new Tile(t.getNr() + 1, t.getFamily()));
            out_ab = possibleShapes(tg_temp, complete, not_complete + 1, contains_pair);
        }
        List<Integer []> out_ac = new ArrayList<>(0);
        if(tg_copy.nrOfElem(new Tile(t.getNr() + 2, t.getFamily())) > 0){
            tg_temp = new TileGroup(tg_copy);
            tg_temp.remove(new Tile(t.getNr() + 2, t.getFamily()));
            out_ab = possibleShapes(tg_temp, complete, not_complete + 1, contains_pair);
        }
        List<Integer []> out_abc = new ArrayList<>(0);
        if((tg_copy.nrOfElem(new Tile(t.getNr() + 1, t.getFamily())) > 0)&&(tg_copy.nrOfElem(new Tile(t.getNr() + 2, t.getFamily())) > 0)){
            tg_temp = new TileGroup(tg_copy);
            tg_temp.remove(new Tile(t.getNr() + 1, t.getFamily()));
            tg_temp.remove(new Tile(t.getNr() + 2, t.getFamily()));
            out_abc = possibleShapes(tg_temp, complete + 1, not_complete, contains_pair);
        }

        List<Integer []> out_none = possibleShapes(tg_copy, complete, not_complete, contains_pair);

        List<Integer []> out_merged = new ArrayList<>(0);
        out_merged.addAll(out_aaa);
        out_merged.addAll(out_aa);
        out_merged.addAll(out_ab);
        out_merged.addAll(out_ac);
        out_merged.addAll(out_abc);
        out_merged.addAll(out_none);

        return out_merged;
    }

    public List<Pair<Tile, Integer>> remainingTiles(Hand h,Game g){
        List<Pair<Tile, Integer>> output = new ArrayList<>(0);
        TileGroup all_tiles = new TileGroup("all");

        int counter = 0;
        for(int i = 0; i < all_tiles.size(); i++){
            counter = 0;
            counter += h.nrOfElem(all_tiles.get(i));
            for(int ii = 0; ii < 4; ii++){
                Player temp_player = g.players.get(ii);
                counter += temp_player.getRiver().nrOfElem(all_tiles.get(i));

                for(int iii = 0; iii < temp_player.getHand().opened_blocks.size(); iii++){
                    counter += temp_player.getHand().opened_blocks.get(iii).nrOfElem(all_tiles.get(i));
                }
            }
            output.add(new Pair<Tile,Integer>(all_tiles.get(i), (4 - counter) ));
        }
        return output;
    }
    public TileGroup improvingTiles(Hand h){//h ma 13 kamieni
        TileGroup all_tiles = new TileGroup("all");

        int shanten = shanten(h);
        Hand temp_hand;
        TileGroup output = new TileGroup();
        for(int i = 0; i < all_tiles.size(); i++){
            temp_hand = new Hand(h);
            temp_hand.add(all_tiles.get(i));
            if(shanten(temp_hand) < shanten){
                output.add(all_tiles.get(i));
            }
        }
        return output;
    }
}

