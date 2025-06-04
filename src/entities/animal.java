package entities;

import Game.Pos;

public class animal extends entity{
    public int health;
    public int food;
    boolean isHunting = false;
    boolean isReproducing = false;
    char[] targets;
    public int progress = 0;


    /**
     * ustawia progress zwierzęcia
     * @param progress liczba progresu
     */
    public void setProgress(int progress) {
        this.progress = progress;
    }





    /**
     * rozmnażanie zwierzęcia gdy znajdzie takie same zwierze i zacznie iśc w jego kierunku
     * @param type typ zwierzęcia
     */
    public void populate(char type) {
        //System.out.println("rozmnaza sie");
        Pos pos = check(new char[]{this.type} ,1);

        if(pos != null ){
            //System.out.println(name +" ("+x+ ", " + y+ ") rozmnaza sie z ("+ pos.x + ", " + pos.y+")");
            //game.setMapentity(pos.x, pos.y, ' ');

            pos = check(new char[]{' '} ,1);
            game.spawnEntity(pos.x,pos.y,type); //do zmiany
            for (animal e: game.getEntities()) {
                if (e != this && pos.x == e.x && pos.y == e.y && e.type == type) {
                   // System.out.println(this.name + " zjada " + pos.x + " " + pos.y );
                    e.setProgress(0);
                    this.isReproducing = false;
                    food -= 40;
                    progress = 0;
                    System.out.println(name +" ("+x+ ", " + y+ ") rozmnaza sie z "+ e.name +" (" + x + ", " + pos.y+")");
                    e.isHunting = false;
                    break;
                }
            }
            return;
        }
        pos = check(new char[]{this.type},Math.max(game.getTerrain()[0].length, game.getTerrain().length));

        int ax = 0, ay =0;
        if (pos == null) {
            //System.out.println("nie znaleziono zwierze " + this.type);
            return;
        }       //System.out.println(" znaleziono zwierze do populacji "+ pos.x + " " + pos.y);
        if ( x  == pos.x) {
            if (y  -pos.y > 0) {
                ay = -2;
            }else ay =2;
        }else if( y == pos.y ) {
            if (x  -pos.x > 0) {
                ax = -2;
            }else ax = 2;
        }else {
            if (x > pos.x){
                ax = -2;
            }else {
                ax = 2;
            }
            if (y > pos.y){
                ay = -2;
            } else {
                ay = 2;
            }
        }
        //System.out.println("ruch w kiuerunku ofiary " +ax +" " +ay+ " "+ type + " " + pos.x + " " + pos.y);
        move(ax,ay,type);
    }


    /**
     * poruszanie sie o ax  i ay i podanie type
     * @param ax o ile ma sie poruszyc w osi x
     * @param ay o ile ma sie poruszyc w osi y
     * @param type typ zwierzęcia
     */
    public void move(int ax, int ay, char type) {

        if (game.checkborder(x +ax,y+ay) && game.getEntityMap()[x +ax][y+ay] == ' ') {
            System.out.printf(this.name + " porusza  z (%d,%d) na (%d,%d)\n",x,y,x + ax, y + ay);
            game.setMapentity(x, y, ' ');

            this.x += ax;
            this.y += ay;
            game.setMapentity(x , y , type);
        }
    }

    //

    /**
     * podaje losowwe koordynaty(wektor) x,y zwierzęcia w zakresie -2,2
     * @return Pos klase pos z wartoscią x i y
     */
    public Pos randpos(){
        Pos pos;
        for (int i=0; i< 15;i++){
            pos = new Pos((int)(Math.random()*4)-1, (int)(Math.random()*4)-1);
            if (game.checkborder(x+ pos.x,y+ pos.y)) return pos;
        }
        return new Pos(0,0);
    }

    /**
     * zwierze zacznie polowac na ofiare, szuka ją po czym zaczyna do niej iść
     * @param target  podajemy tablice ceów polowania
     */
    public void hunt(char[] target) {
        Pos pos = check(target,1);

        if(pos != null ){
            //System.out.println(" znaleziono ofiare "+ pos.x + " " + pos.y);
            //game.setMapentity(pos.x, pos.y, ' ');
            this.isHunting = false;
            if(this instanceof sheep){
                System.out.println(this.name + " zjada trawe " + pos.x + " " + pos.y );
                game.grasseaten++;
                grass.ammount--;
                game.getTerrain()[pos.x][pos.y] = ' ';
                this.food += 60;
                return;
            }
            for (entity e: game.getEntities()) {
                if (e != this && pos.x == e.x && pos.y == e.y) {
                    System.out.println(this.name + "" +
                            " zjada " + e.name+" "+ pos.x + " " + pos.y );
                    e.die();
                    this.food += 60;
                    break;
                }
            }


            return;
        }
        pos = check(target,Math.max(game.getTerrain()[0].length, game.getTerrain().length));
        int ax = 0, ay =0;
        if (pos == null) {
            return;
        }

        if ( x  == pos.x) {
            if (y  -pos.y > 0) {
                ay = -2;
            }else ay =2;
        }else if( y == pos.y ) {
            if (x  -pos.x > 0) {
                ax = -2;
            }else ax = 2;
        }else {
            if (x > pos.x){
                ax = -2;
            }else {
                ax = 2;
            }
            if (y > pos.y){
                ay = -2;
            } else {
                ay = 2;
            }
        }
        //System.out.println("ruch w kiuerunku ofiary " +ax +" " +ay+ " "+ type + " " + pos.x + " " + pos.y);
        move(ax,ay,type);
    }

    //
    /**
     * sprawdza tablice i miejsce na około entity o zasiegu (range)
     * @param target  podajemy tablice ceów polowania
     * @param range zasieg poszukiwania
     */
    public Pos check(char[] target, int range) {


        //System.out.println(range);
        for(int r=0;r<range ; r++){
            for (int dx = -1-r; dx <= 1+r; dx++) {
                int ax = x + dx;
                    for (int dy = -1-r; dy <= 1+r; dy++) {
                        int ay = y + dy;
                        if (dx==0 && dy==0) continue;
                        //System.out.println(ax + " " + ay);
                        if(Math.abs(dx)<r||Math.abs(dy) <r) continue;
                        if (game.checkborder(ax, ay)) {
                            for (char t : target) {
                                if (game.getEntityMap()[ax][ay] == t || game.getTerrain()[ax][ay] == t) {
                                    //System.out.print(game.getEntityMap()[ax][ay]);
                                    return new Pos(ax, ay);
                                }
                            }
                        }
                    }
            }
        }
        return null;
    }

    /**
     * logika zwierzęcia
     */
    @Override
    public void update() {
        if(!game.getEntities().contains(this)) return;
        //System.out.println(this.name + " " +health + " " + food);
        if (health <= 0) {
            System.out.println("umarł prze glod " + this.name + " " + this.x + " " + this.y);
            this.die();
            return;
        }
        if (food >0) {
            food = food - 8;
        }

        if (food < 10) health = health - 10;
        if (food >70 && Math.random() < 0.5 ) {
            progress++;
        }
        if (progress >= 2) {
            isReproducing=true;
            populate(type);
        }
        if (food < 40){
            isReproducing=false;
            isHunting = true;
        }
        if (isHunting) {
            hunt(this.targets);
            return;
            //wyszukiwanie owcy i ruch w jego strone i return
        }

        Pos pos = randpos();
        move(pos.x,pos.y, type);

    }
}

