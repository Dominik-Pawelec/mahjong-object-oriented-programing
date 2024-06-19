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
    public Hand(Hand copy){
        super(copy);
        opened_blocks = new ArrayList<TileGroup>(0);
        for(int i = 0; i < copy.opened_blocks.size(); i++){
            opened_blocks.add(new TileGroup(copy.opened_blocks.get(i)));
        }
    }
    public List<TileGroup> getOpened(){
        return opened_blocks;
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
            this.remove(tile_g.group.get(i));
        }
    }

    public boolean isOpen(){
        return (opened_blocks.size()!=0);
    }

    public boolean isWinning(){
        Hand hand_copy = new Hand(this);//kopiuje tylko część zamkniętą ręki (czyli zamierzony efekt)
        
        TileGroup pair_tiles = new TileGroup();

        TileGroup all_tiles = new TileGroup("all");

        for(int i = 0; i < all_tiles.size(); i++){
            if(hand_copy.nrOfElem(all_tiles.get(i)) > 1 && pair_tiles.nrOfElem(all_tiles.get(i)) == 0){
                pair_tiles.add(all_tiles.get(i));
            }
        }

        if(pair_tiles.size() == 7){//7 par in my heart
            return true;
        }
        hand_copy.sort();

        TileGroup tgroup;

        //uwzględniamy że można było otworzyć np. 2 grupy, wtedy tylko 2 do sprawdzenia na ręce:
        int blocks_to_find = 4 - opened_blocks.size();

        for(int i = 0; i < pair_tiles.size(); i++){
            //Algorytm: szukam dla 1 elementu czy można stworzyć pon, jeśli tak to usuwam 3 elementy i powtarzam aż nie puste
            //jeśli nie to sprawdzam czy chi, jeśli tak to usuwam 3 elementy z chi i powstarzam ...
            //jeśli nie to przechodze do kolejnej pary
            //jesli pusta to zwracam prawda
            //pozniejszy rozwoj o liste wszystkich wygranych z podziałem na bloki łatwe do zmienienia, po prostu do isty dodajesz i wykonujesz wszystkie przejscia.
            tgroup = new TileGroup(hand_copy);
            tgroup.remove(pair_tiles.get(i));
            tgroup.remove(pair_tiles.get(i));

            for(int k = 0; k < blocks_to_find; k++){
                Tile temp = tgroup.get(0);
                tgroup.remove(temp);
                if(tgroup.nrOfElem(temp) >= 2){
                    tgroup.remove(temp);
                    tgroup.remove(temp);
                }
                else if((temp.getFamily() != "wind")&&(temp.getFamily() != "dragon")&&
                    (tgroup.nrOfElem(new Tile(temp.getNr()+1, temp.getFamily())) != 0) && (tgroup.nrOfElem(new Tile(temp.getNr()+2, temp.getFamily())) !=0)){
                    tgroup.remove(new Tile(temp.getNr()+1, temp.getFamily()));
                    tgroup.remove(new Tile(temp.getNr()+2, temp.getFamily()));
                }
            }
            if (tgroup.size() == 0) {return true;}
        }


        return false;
    }
    

    public TileGroup winningTiles(){
        TileGroup output = new TileGroup();
        TileGroup all_tiles = new TileGroup("all");
        Hand temp_hand;
        for(int i = 0; i < all_tiles.size(); i++){
            temp_hand = new Hand(this);
            temp_hand.sort();
            temp_hand.add(all_tiles.get(i));
            if(temp_hand.isWinning()){
                output.add(all_tiles.get(i));
            }
        }
        return output;
    }
    public boolean inTenpai(){//yaku sprawdza w innej czesci, potem todo
        return(this.winningTiles().size() != 0);
    }

    public void sort2(){
        TileGroup temp_group = new TileGroup(this);
        Tile t = temp_group.get(temp_group.size()-1);
        temp_group.remove();
        temp_group.sort();
        temp_group.add(t);
        this.group = temp_group.group;
    }

    @Override
    public String toString(){
        String output = "closed:" + super.toString() + "| opened:" + isOpen()+ " :" + this.opened_blocks.toString();
        return output;
    }
}
