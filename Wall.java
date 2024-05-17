import java.util.Collections;

public class Wall extends TileGroup{
    
    public Wall(){
        super();
    }
    public Wall(Tile...args){
        super(args);
    }
    public void build(){
        //generowanie 
        for(int i = 0; i < 4; i++){
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
        Collections.shuffle(group);
    }

    public Tile drawTile(){
        Tile temp = super.group.get(this.size()-1);
        super.group.remove(this.size()-1);
        return temp;
    }

    @Override
    public String toString(){
        return super.toString();
    }
}
