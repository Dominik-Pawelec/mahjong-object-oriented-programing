import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TileGroup{
    List<Tile> group;

    public TileGroup(){
        group = new ArrayList<Tile>(0);
    }

    public TileGroup(Tile...args){
        group = new ArrayList<Tile>(0);
        for(Tile arg : args){
            group.add(arg);
        }
    }
    public TileGroup(TileGroup copy){
        this.group = new ArrayList<Tile>(0);
        for(int i = 0; i < copy.size(); i++){
            this.add(copy.get(i));
        }
    }

    public TileGroup(String x){
        group = new ArrayList<Tile>(0);
        if(x == "all"){
            String[] families = new String[]{"man","pin","sou","wind","dragon"};
            for(int j = 0; j < 3; j++){
                for(int k = 1; k < 10; k++){
                    this.add(new Tile(k, families[j]));
                }
            }
            for(int k = 1; k < 5; k++){
                this.add(new Tile(k, families[3]));
            }
            for(int k = 1; k < 4; k++){
                this.add(new Tile(k, families[4]));
            }
        }
    }

    public void add(Tile tile){
        group.add(tile);
    }
    public void remove(){
        group.remove(group.size()-1);
    }
    public void remove(Tile t){
        for(int i = 0; i < this.size(); i++){
            if (group.get(i).compareTo(t) == 0){
                group.remove(i);
                return;
            }
        }
    }

    public void sort(){
        group.sort(null);
    }
    public void shuffle(){
        Collections.shuffle(group);
    }

    public int size(){
        return group.size();
    }
    protected int nrOfElem(Tile tile){
        int output = 0;

        for(int i = 0; i < this.size(); i++){
            if (group.get(i).compareTo(tile) == 0){output ++; }
        }
        return output;
    }
    public Tile get(int n){
        return group.get(n);
    }
    
    @Override
    public String toString(){
        return group.toString();
    }
}
