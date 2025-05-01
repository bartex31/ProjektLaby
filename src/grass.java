public class grass {
    int x;
    int y;
    int growth = 0;

    public grass(int x, int y, char[][] grass) {
        this.x = x;
        this.y = y;
        grass[x][y] = 't';
    }

    public void update(char[][] grass) {
        int random;
        random = (int)(Math.random()*2);
        if(random == 1){
            growth++;
        }

        if(growth == 4){
            growth = 0;
            random = (int)(Math.random()*4);
            if(random == 0 && y+1 < grass.length){
                if (grass[x][y+1] != 't'){
                    grass[x][y+1] = 'z';
                }
            }else if (random == 1 && x+1 < grass[x].length){
                if (grass[x+1][y] != 't'){
                    grass[x+1][y] = 'z';
                }
            }else if (random == 2 && y-1 >= 0) {
                if (grass[x][y-1] != 't'){
                    grass[x][y-1] = 'z';
                }
            }else if (random == 3 && x-1 >=0){
                if (grass[x-1][y] != 't'){
                    grass[x-1][y] = 'z';
                }
            }
        }
    }








}
