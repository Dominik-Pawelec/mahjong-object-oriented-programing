import java.util.Collection;
import java.util.Collections;

public class Wall extends TileGroup{
    
    public Wall(){
        super();
    }

    public void Build(){
        //generowanie 
        for(int i = 0; i < 4; i++){
            String[] families = new String[]{"man","pin","sou","wind","dragon"}; 
            for(int j = 0; j < 3; j++){
                for(int k = 1; k < 10; k++){
                    this.Add(new Tile(k, families[j]));
                }
            }
            for(int k = 1; k < 5; k++){
                this.Add(new Tile(k, families[3]));
            }
            for(int k = 1; k < 4; k++){
                this.Add(new Tile(k, families[4]));
            }
        }
        Collections.shuffle(group);
    }

    public Tile DrawTile(){
        return super.group.get(super.group.size()-1);
    }

    @Override
    public String toString(){
        return super.toString();
    }


}
