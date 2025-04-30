public class stockpile {
    int wood;
    int stone;
    int food;
    int population;
    public stockpile(int wood, int stone, int food, int population) {
        this.wood = wood;
        this.stone = stone;
        this.food = food;
        this.population = population;
    }

    public int getStone() {
        return stone;
    }
    public int getWood() {
        return wood;
    }
    public int getFood() {
        return food;
    }
    public int getManpower() {
        return population;
    }
}
