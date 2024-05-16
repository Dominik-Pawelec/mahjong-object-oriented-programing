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
            System.out.println("xxxx"); 
        }
    }
    public void Add(Tile tile){
        group.add(tile);
    }

    public void Remove(Tile tile){
        group.remove(group.size()-1);
    }

    @Override
    public String toString(){
        return group.toString();
    }
}
