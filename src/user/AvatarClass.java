package user;

import java.awt.*;

import static definitions.ViridianDriveColors.*;

public enum AvatarClass {
    //todo - lots here, probably specify available and restricted skill trees, level up benefits, bonuses, etc.
    BEASTMASTER,
    BIOENGINEER,
    ENGINEER,
    MERCENARY,
    PSION,
    SYMBIANT;

    @Override
    public String toString() {
        switch (this) {
            case BEASTMASTER: return "Beastmaster";
            case BIOENGINEER: return "Bioengineer";
            case ENGINEER: return "Engineer";
            case MERCENARY: return "Mercenary";
            case PSION: return "Psion";
            case SYMBIANT: return "Symbiant";
            default:
                throw new IllegalStateException("Unhandled AvatarClass: " + super.toString());
        }
    }

    public Color getColor() {
        switch (this) {
            case BEASTMASTER: return CLASS_BEASTMASTER;
            case BIOENGINEER: return CLASS_BIOENGINEER;
            case ENGINEER: return CLASS_ENGINEER;
            case MERCENARY: return CLASS_MERCENARY;
            case PSION: return CLASS_PSION;
            case SYMBIANT: return CLASS_SYMBIANT;
            default:
                throw new IllegalStateException("Unhandled AvatarClass: " + super.toString());
        }
    }
}
