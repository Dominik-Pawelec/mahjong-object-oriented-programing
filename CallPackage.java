

public class CallPackage {
    Tile tile;
    String wind;
    boolean is_calling;
    String call_type;
    TileGroup tile_group;

    public CallPackage(Tile t, String w){
        tile = t;
        wind = w;
    }
    public CallPackage(CallPackage copy){
        tile = copy.tile;
        wind = copy.wind;
        is_calling = copy.is_calling;
        call_type = copy.call_type;
        tile_group = new TileGroup(copy.tile_group);
    }

    public Tile getTile(){
        return tile;
    }
    public String getWind(){
        return wind;
    }

    public void preparePackage(boolean is_call, String call_t, TileGroup whole_block){
        is_calling = is_call;
        if(!is_calling){return;}
        call_type = call_t;
        tile_group = whole_block;
    }


    public boolean isCalling(){
        return is_calling;
    }
    public String callType(){
        return call_type;
    }
    public TileGroup tileGroup(){
        return tile_group;
    }

}
