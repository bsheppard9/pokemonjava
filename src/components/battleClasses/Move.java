package components.battleClasses;

public enum Move {

    	/*
	hasEffect:

	0 - binding
	1 - burns
	2 - confuses
	3 - criticalHitRatio
	4 - drainingMove
	5 - fainting
	6 - flinching
	7 - freezes
	8 - multiStrike
	9 - neverMisses
	10 - newMove (Moves like mirror move, metronome, copycat)
	11 - oneHitKO
	12 - paralyzes
	13 - poisons
	14 - recoils
	15 - retaliation (Moves like counter, bide, mirror coat)
	16 - sleep
	17 - statisticMove
	18 - statusMove (Not referring to paralysis, poison, etc.; light screen, guard)
	19 - switched
	20 - weatherMove
	21 - temporary (Accounts for focus energy, haze, reflect, light screen, )

	Consider just using 1 string to identify the effect.

	*/
    BLANK("", null, -1, -1, -1, null, -1, ""),
    //All move names should be capitalized except blanks
    //ABSORB("Absorb", Type.GRASS, 20, 100, 20, new boolean[] {}, 0, "Special"),
    //ACID(),
    //ACID_ARMOR(),
    AGILITY("Agility", Type.PSYCHIC, 0, 100, 30, "Statistic", 0, "Status"),
    //AMNESIA(),
    //AURORA_BEAM(),
    //BARRAGE(),
    //BARRIER(),
    //BIDE(), //Power of 1
    //BIND(),
    BITE("Bite", Type.NORMAL, 60, 100, 25, "Flinching", 0, "Physical"),
    //BLIZZARD(),
    //BODY_SLAM(),
    //BONE_CLUB(),
    //BONEMERANG(),
    BUBBLE("Bubble", Type.WATER, 20, 100, 30, "Statistic-Opp", 0, "Special"),
    //BUBBLE_BEAM(),
    //CLAMP(),
    //COMET_PUNCH(),
    //CONFUSE_RAY(),
    //CONFUSION(),
    //CONSTRICT(),
    //CONVERSION(),
    //COUNTER(), //Power of 1
    //CRABHAMMER(),
    //CUT(),
    //DEFENSE_CURL(),
    //DIG(),
    //DISABLE(),
    //DIZZY_PUNCH(),
    //DOUBLE_KICK(),
    //DOUBLE_SLAP(),
    DOUBLE_TEAM("Double Team", Type.NORMAL, 0, 100, 15, "Statistic", 0, "Status"),
    //DOUBLE_EDGE(),
    //DRAGON_RAGE(), //Power of 1
    //DREAM_EATER(),
    //DRILL_PECK(),
    //EARTHQUAKE(),
    //EGG_BOMB(),
    EMBER("Ember", Type.FIRE, 40, 100, 25, "Burns", 0, "Special"),
    //EXPLOSION(),
    //FIRE_BLAST(),
    //FIRE_PUNCH(),
    FIRE_SPIN("Fire Spin", Type.FIRE, 15, 70, 15, "Binding", 0, "Special"),
    //FISSURE(),
    FLAMETHROWER("Flamethrower", Type.FIRE, 95, 100, 15, "Burns", 0, "Special"),
    //FLASH(),
    //FLY(),
    FOCUS_ENERGY("Focus Energy", Type.NORMAL, 0, 0, 30, "Temporary", 0, "Status"),
    //FURY_ATTACK(),
    //FURY_SWIPES(),
    //GLARE(),
    GROWL("Growl", Type.NORMAL, 0, 100, 40, "Statistic", 0, "Status"), //Check this <- There may be a problem
    GROWTH("Growth", Type.GRASS, 0, 0, 20, "Statistic", 0, "Status"),
    //GUILLOTINE(),
    GUST("Gust", Type.NORMAL, 40, 100, 30, "", 0, "Physical"),
    //HARDEN(),
    //HAZE(),
    //HEADBUTT(),
    //HIGH_JUMP_KICK(),
    //HORN_ATTACK(),
    //HORN_DRILL(),
    HYDRO_PUMP("Hydro Pump", Type.WATER, 120, 80, 5, "", 0, "Special"),
    //HYPER_BEAM(),
    //HYPER_FANG(),
    //HYPNOSIS(),
    //ICE_BEAM(),
    //ICE_PUNCH(),
    //JUMP_KICK(),
    //KARATE_CHOP(),
    //KINESIS(),
    //LEECH_LIFE(),
    LEECH_SEED("Leech Seed", Type.GRASS, 0, 90, 10, "", 0, "Status"), //Read up for authenticity
    LEER("Leer", Type.NORMAL, 0, 100, 30, "Statistic", 0, "Status"),
    //LICK(),
    LIGHT_SCREEN("Light Screen", Type.PSYCHIC, 0, 100, 30, "Temporary", 0, "Status"),
    //LOVELY_KISS(),
    //LOW_KICK(),
    //MEDITATE(),
    //MEGA_DRAIN(),
    //MEGA_KICK(),
    //MEGA_PUNCH(),
    //METRONOME(),
    //MIMIC(),
    //MINIMIZE(),
    MIRROR_MOVE("Mirror Move", Type.FLYING, 0, 100, 20, "New Move", 0, "Status"),
    //MIST(),
    //NIGHT_SHADE(), //Power of 1
    //PAY_DAY(),
    //PECK(),
    //PETAL_DANCE(),
    //PIN_MISSLE(),
    //POISON_GAS(),
    POISON_POWDER("PoisonPowder", Type.POISON, 0, 75, 35, "Poisons", 0, "Status"),
    POISON_STING("Poison Sting", Type.POISON, 15, 100, 35, "Poisons", 0, "Physical"),
    //POUND(),
    //PSYBEAM(),
    //PSYCHIC(),
    //PSYWAVE(), //Power of 1
    QUICK_ATTACK("Quick Attack", Type.NORMAL, 40, 100, 30, "", 1, "Physical"),
    RAGE("Rage", Type.NORMAL, 20, 100, 20, "", 0, "Physical"),
    RAZOR_LEAF("Razor Leaf", Type.GRASS, 55, 95, 25, "Critical Hit", 0, "Special"),
    //RAZOR_WIND(),
    //RECOVER(),
    //REFLECT(),
    //REST(),
    //ROAR(),
    //ROCK_SLIDE(),
    //ROCK_THROW(),
    //ROLLING_KICK(),
    SAND_ATTACK("Sand Attack", Type.NORMAL, 0, 100, 15, "Statistic", 0, "Status"),
    SCRATCH("Scratch", Type.NORMAL, 40, 100, 35, "", 0, "Physical"),
    //SCREECH(),
    //SEISMIC_TOSS(), //Power of 1
    //SELF_DESTRUCT(),
    //SHARPEN(),
    //SING(),
    SKULL_BASH("Skull Bash", Type.NORMAL, 100, 100, 10, "Two-Turn", 0, "Physical"),
    //SKY_ATTACK(),
    SLAM("Slam", Type.NORMAL, 80, 75, 20, "", 0, "Physical"),
    SLASH("Slash", Type.NORMAL, 70, 100, 20, "Critical Hit", 0, "Physical"),
    SLEEP_POWDER("Sleep Powder", Type.GRASS, 0, 75, 15, "Sleep", 0, "Status"),// Works with substitute Gen I
    //SLUDGE(),
    //SMOG(),
    //SMOKESCREEN(),
    //SOFT_BOILED(),
    SOLAR_BEAM("SolarBeam", Type.GRASS, 120, 100, 10, "Two-Turn", 0, "Special"),
    //SONIC_BOOM(), //Power of 1
    //SPIKE_CANNON(),
    //SPLASH(),
    //SPORE(),
    //STOMP(),
    //STRENGTH(),
    STRING_SHOT("String Shot", Type.BUG, 0, 95, 35, "Statistic", 0, "Status"),
    STRUGGLE("Struggle", Type.NORMAL, 10, 100, 1, "Recoils", 0, "Physical"),
    //STUN_SPORE(),
    //SUBMISSION(),
    //SUBSTITUTE(),
    //SUPER_FANG(), //Power of 1
    //SUPERSONIC(),
    //SURF(),
    //SWIFT(),
    //SWORDS_DANCE(),
    TACKLE("Tackle", Type.NORMAL, 35, 95, 35, "", 0, "Physical"),
    TAIL_WHIP("Tail Whip", Type.NORMAL, 0, 100, 30, "Statistic", 0, "Status"),
    TAKE_DOWN("Take Down", Type.NORMAL, 90, 85, 20, "Recoils", 0, "Physical"),
    //TELEPORT(),
    //THRASH(),
    THUNDER("Thunder", Type.ELECTRIC, 120, 70, 10, "Paralyzes", 0, "Special"),
    //THUNDER_PUNCH(),
    THUNDER_SHOCK("Thunder Shock", Type.ELECTRIC, 40, 100, 30,"Paralyzes", 0,
            "Special"),
    THUNDER_WAVE("Thunder Wave", Type.ELECTRIC, 0, 90, 20, "Paralyzes", 0, "Status"),
    THUNDERBOLT("Thunderbolt", Type.ELECTRIC, 95, 100, 15, "Paralyzes", 0, "Special"),
    //TOXIC(),
    //TRANSFORM(),
    //TRI_ATTACK(),
    //TWINEEDLE(),
    //VICE_GRIP(),
    VINE_WHIP("Vine Whip", Type.GRASS, 35, 100, 10, "", 0, "Special"),
    WATER_GUN("Water Gun", Type.WATER, 40, 100, 25, "", 0, "Special"),
    //WATERFALL(),
    WHIRLWIND("Whirlwind", Type.NORMAL, 0, 85, 20, "Switched", 0, "Status"),
    WING_ATTACK("Wing Attack", Type.FLYING, 35, 100, 35, "", 0, "Physical"),
    WITHDRAW("Withdraw", Type.WATER, 0, 100, 40, "Statistic", 0, "Status");
    //WRAP();


    private String name;
    private Type type;
    private int power;
    private int accuracy;
    private int powerPoints;
    private String effect;
    private int priority;
    private String category; //Physical, Special, Status

    Move(String name, Type type, int power, int accuracy, int powerPoints,
         String effect, int priority, String category) {
        this.name = name;
        this.type = type;
        this.power = power;
        this.accuracy = accuracy;
        this.powerPoints = powerPoints;
        this.effect = effect;
        this.priority = priority;
        this.category = category;
    }

    public String getName() {
        return this.name;
    }

    public Type getType() {
        return this.type;
    }

    public int getPower() {
        return this.power;
    }

    public int getAccuracy() {
        return this.accuracy;
    }

    public int getPowerPoints() {
        return this.powerPoints;
    }

    public String getEffect() {
        return this.effect;
    }

    public int getPriority() {
        return this.priority;
    }

    public String getCategory() {
        return this.category;
    }
}
