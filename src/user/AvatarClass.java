package user;

import java.awt.*;

import static definitions.ViridianDriveColors.*;

public enum AvatarClass {
    //todo - lots here, probably specify available and restricted skill trees, level up benefits, bonuses, etc.
    BEASTMASTER,
    CYBERNAUT,
    ENGINEER,
    GUNNER,
    PSION,
    SCOUT;

    @Override
    public String toString() {
        switch (this) {
            case BEASTMASTER: return "Beastmaster";
            case CYBERNAUT: return "Cybernaut";
            case ENGINEER: return "Engineer";
            case GUNNER: return "Gunner";
            case PSION: return "Psion";
            case SCOUT: return "Scout";
            default:
                throw new IllegalStateException("Unhandled AvatarClass: " + super.toString());
        }
    }

    public Color getColor() {
        switch (this) {
            case BEASTMASTER: return CLASS_BEASTMASTER;
            case CYBERNAUT: return CLASS_CYBERNAUT;
            case ENGINEER: return CLASS_ENGINEER;
            case GUNNER: return CLASS_GUNNER;
            case PSION: return CLASS_PSION;
            case SCOUT: return CLASS_SCOUT;
            default:
                throw new IllegalStateException("Unhandled AvatarClass: " + super.toString());
        }
    }
}
