public class River extends TileGroup{
    Tile recent_tile;
    TileGroup stolen_tiles;

    //Pair <TileGroup,Player> stolen_tiles;

    public River(){
        super();
        stolen_tiles = new TileGroup();
    }

    public Tile getRecent(){
        return recent_tile;
    }

    @Override
    public Tile add(Tile tile){
        group.add(tile);
        recent_tile = tile;
        return tile;
    }
    public void remove(){
        group.remove(group.size()-1);
    }
    
    public String toString(){
        String output = "";
        for(int i = 0; i < group.size(); i++){
            if(i%6 == 0){
                output += "\n";
            }
            output += "|" + group.get(i) + "|";
        }
        return output;
    }
}
