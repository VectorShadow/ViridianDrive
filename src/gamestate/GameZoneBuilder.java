package gamestate;

public class GameZoneBuilder {
    public GameZone build(int zoneID, int depth) {
        return getGenerator(zoneID, depth).generate();
    }
    public GameZoneGenerator getGenerator(int zoneID, int depth) {
        //todo - different Generators for different IDs and even depths
        return new TestGameZoneGenerator();
    }
}
