import java.util.ArrayList;
import java.util.List;


public class TileGroup {
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
    public void Add(Tile tile){
        group.add(tile);
    }

    public void Remove(){
        group.remove(group.size()-1);
    }

    @Override
    public String toString(){
        return group.toString();
    }
}
