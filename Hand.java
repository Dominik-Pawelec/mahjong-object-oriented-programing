import java.util.ArrayList;
import java.util.List;


public class Hand extends TileGroup{
    List<TileGroup> opened_blocks;

    public Hand(){
        super();
        opened_blocks = new ArrayList<TileGroup>(0);
    }
    public Hand(Tile...args){
        super(args);
        opened_blocks = new ArrayList<TileGroup>(0);
    }

    public Boolean containsTile(Tile t){
        return (this.nrOfElem(t) != 0);
    }
    public List<TileGroup> chiOptions(Tile t){
        List<TileGroup> output = new ArrayList<TileGroup>(0);
        
        if (t.getFamily() == "wind" || t.getFamily() == "dragon"){return output;}

        if (this.containsTile(new Tile(t.getNr()-1, t.getFamily()))){
            if (this.containsTile(new Tile(t.getNr()-2, t.getFamily()))){
                output.add(new TileGroup(new Tile(t.getNr()-2, t.getFamily()),new Tile(t.getNr()-1, t.getFamily()),new Tile(t.getNr(), t.getFamily())));
            }
            if (this.containsTile(new Tile(t.getNr()+1, t.getFamily()))){
                output.add(new TileGroup(new Tile(t.getNr()-1, t.getFamily()),new Tile(t.getNr(), t.getFamily()),new Tile(t.getNr()+1, t.getFamily())));
            }
        }
        if (this.containsTile(new Tile(t.getNr()+1, t.getFamily())) && this.containsTile(new Tile(t.getNr()+2, t.getFamily()))){
            output.add(new TileGroup(new Tile(t.getNr(), t.getFamily()),new Tile(t.getNr()+1, t.getFamily()),new Tile(t.getNr()+2, t.getFamily())));
        }
        return output;
    }
    public List<TileGroup> ponOptions(Tile t){
        List<TileGroup> output = new ArrayList<TileGroup>(0);
        if (this.nrOfElem(t) >= 2) {
            output.add(new TileGroup(t,t,t));
        }
        return output;
    }

    public void openBlock(TileGroup tile_g){ //assuming you CAN open this tileGroup
        opened_blocks.add(tile_g);
        for (int i = 0; i < tile_g.group.size(); i++){
            super.group.remove(tile_g.group.get(i));
        }
    }

    public boolean isOpen(){
        return (opened_blocks.size()==0);
    }
    

    @Override
    public String toString(){
        String output = "closed:" + super.toString() + "| opened:" + this.opened_blocks.toString();
        return output;
    }
}
