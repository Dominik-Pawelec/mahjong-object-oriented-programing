import java.util.ArrayList;
import java.util.List;


public class TileGroup{
    List<Tile> group;

    TileGroup(){
        group = new ArrayList<Tile>(0);
    }

    TileGroup(Tile...args){
        group = new ArrayList<Tile>(0);
        for(Tile arg : args){
            group.add(arg);
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

    protected int size(){
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
