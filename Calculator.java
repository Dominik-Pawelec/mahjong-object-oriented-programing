public class Calculator {
    
    public Calculator(){

    }

    public int shanten(Hand h){

        if(h.size() != 14){System.out.println("WRONG SIZE OF HAND");return -1;}


        int res = 0;
        Hand hand = new Hand(h);

        res += 2 * hand.getOpened().size();

        
        return 8 - res - shantenPoints_rec(h, hand.getOpened().size(), false);
    }


    //powinien podzielic na rodziny jeszcze
    public int shantenPoints_rec(TileGroup tg, int nr_of_block, boolean pair){//ocaml has bestowned upon me the knowledge of recuring functions and I shall not refuse to use it.
        if(tg.size() == 0){
            int output = 0;
            if(!pair){output -=1;}
            if(nr_of_block > 5){output -= nr_of_block - 5;}
            return output;
        }

        Tile t = tg.get(0);


        //przepisz używając list geniuszu

        TileGroup tg_copy = new TileGroup(tg);
        tg_copy.remove(t);
        tg_copy.sort();

        //part of AAA block
        TileGroup tgAAA = new TileGroup(tg_copy);
        int outAAA = 0;
        if(tg.nrOfElem(t) >= 2){
            for(int i = 0; i < 2; i++){
                tgAAA.remove(t);
            }
            outAAA = 2 + shantenPoints_rec(tgAAA,nr_of_block + 1, pair);
        }
        //part of AA block
        TileGroup tgAA = new TileGroup(tg_copy);
        int outAA = 0;
        if(tg.nrOfElem(t) >= 1){
            tgAA.remove(t);
            outAA = 1 + shantenPoints_rec(tgAA, nr_of_block + 1, true);
        }

        int outAB = 0;int outAC = 0;int outABC = 0;
        if(t.getFamily() != "wind" && t.getFamily() != "dragon"){
            //part of AB block
            TileGroup tgAB = new TileGroup(tg_copy);
            if(tg.nrOfElem(new Tile(t.getNr() + 1, t.getFamily())) > 0){
                tgAB.remove(new Tile(t.getNr() + 1, t.getFamily()));
                outAB = 1 + shantenPoints_rec(tgAB,nr_of_block + 1,pair);
            }
            //part of AC block
            TileGroup tgAC = new TileGroup(tg_copy);
            if(tg.nrOfElem(new Tile(t.getNr() + 2, t.getFamily())) > 0){
                tgAC.remove(new Tile(t.getNr() + 2, t.getFamily()));
                outAC = 1 + shantenPoints_rec(tgAC,nr_of_block + 1,pair);
            }
            //part of ABC block
            TileGroup tgABC = new TileGroup(tg_copy);
            if(tg.nrOfElem(new Tile(t.getNr() + 1, t.getFamily())) > 0 && tg.nrOfElem(new Tile(t.getNr() + 2, t.getFamily())) > 0){
                tgABC.remove(new Tile(t.getNr() + 1, t.getFamily()));
                tgABC.remove(new Tile(t.getNr() + 2, t.getFamily()));
                outABC = 2 + shantenPoints_rec(tgABC,nr_of_block + 1,pair);
            }
        }
        int out = shantenPoints_rec(tg_copy, nr_of_block, pair);
        //System.out.println(Math.max(Math.max(Math.max(outAAA,outAA),Math.max(outAB,outAC)),outABC));
        return Math.max(Math.max(Math.max(outAAA,outAA),Math.max(outAB,outAC)),Math.max(outABC,out));
    } 
}
