import java.io.Console;
import java.util.ArrayList;
import java.util.List;


public class Hand extends TileGroup{
    List<TileGroup> opened_blocks;

    public Hand(){
        super();
        opened_blocks = new ArrayList<TileGroup>(0);
    }

    public Boolean ContainsTile(Tile t){
        for(int i = 0; i < group.size(); i++){
            if (group.get(i) == t){return true; }
        }
        return false;
    }

    public void OpenBlock(TileGroup tile_g){
        for (int i = 0; i < tile_g.group.size(); i++){
            if (!this.ContainsTile(tile_g.group.get(i))){return;}
        }
        opened_blocks.add(tile_g);
        System.out.println(opened_blocks);
        for (int i = 0; i < tile_g.group.size(); i++){
            super.group.remove(tile_g.group.get(i));
        }
    }

    public boolean IsOpen(){
        return (opened_blocks.size()==0);
    }
}
