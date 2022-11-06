package components.battleClasses.pokemon;

import java.util.*;

import components.Player;
import components.Trainer;
import components.battleClasses.*;

public abstract class Pokemon {

    private String pokemonName;
    private String nickname;
    private int nationalDexNumber;
    private Type[] type;
    private Statistics statistics;
    //private Ability ability;
    //private Nature nature;
    //private Gender gender;
    private HashMap<Move, Integer> powerPointsSet;
    private Move[] moveset;
    private ArrayList<Move> learnset = new ArrayList<>();
    private int idNumber;
    private int catchRate;
    private int thresholdLevel; //The level it evolves
    private int baseExperienceYield;
    private int[] levels;

    private int battleAttack;
    private int battleDefense;
    private int battleSpecial;
    private int battleSpeed;
    private double battleAccuracy;
    private double battleEvasiveness;

    /*
    NOTES:
    Add variables for pre-evolved form, evolved form
     */

    private static Scanner scanner = new Scanner(System.in);

    public Pokemon(String pokemonName, String newNickname, int nationalDexNumber,
                   int level, int baseHealthPoints, int baseAttack,
                   int baseDefense, int baseSpecial, int baseSpeed,
                   ExperienceType experienceType, int catchRate, int thresholdLevel,
                   int baseExperienceYield, int[] levels, Type ... type) {
        this.pokemonName = pokemonName;
        this.nickname = newNickname;
        this.nationalDexNumber = nationalDexNumber;
        this.statistics = new Statistics(baseHealthPoints, baseAttack,
                baseDefense, baseSpecial, baseSpeed,
                level, experienceType);
        //this.ability = ability;
        //this.gender = gender;
        this.catchRate = catchRate;
        this.thresholdLevel = thresholdLevel;
        this.baseExperienceYield = baseExperienceYield;
        this.levels = levels;
        this.type = type;
        powerPointsSet = new HashMap<>();

    }

    public abstract void createLearnset();

    public void createMoveset(int level) {
        int index = 0;
        int count = 0;
        Move[] moves = new Move[] {Move.BLANK, Move.BLANK, Move.BLANK, Move.BLANK};
        while(count <= this.levels.length - 1) {
            if(level >= this.levels[count]) {
                moves[index] = this.learnset.get(count);
                index = (index + 1) % 4;
            }
            count++;
        }
        this.moveset = moves.clone();
        this.powerPointsSet.put(moveset[0], moveset[0].getPowerPoints());
        this.powerPointsSet.put(moveset[1], moveset[1].getPowerPoints());
        this.powerPointsSet.put(moveset[2], moveset[2].getPowerPoints());
        this.powerPointsSet.put(moveset[3], moveset[3].getPowerPoints());
    }

    public boolean hasUsableMoves() { //Needs to be updated for torment, taunt, etc
        boolean hasmoves = false;
        for (int i = 0; i < 4; i++) {
            if (powerPointsSet.get(moveset[i]) > 0) {
                hasmoves = true;
            }
        }
        return hasmoves;
    }

    public boolean useMove(int i, int p) {
        int pp = powerPointsSet.get(moveset[i]);
        if (pp > 0) {
            if (pp - p < 0) {
                powerPointsSet.replace(moveset[i], 0);
            } else {
                powerPointsSet.replace(moveset[i], pp - p);
            }
            return true;
        }
        return false; //THERE'S NO PP LEFT FOR THIS MOVE!
    }

    public boolean alreadyHasMove(Move move) {
        if (powerPointsSet.containsKey(move)) {
            return true;
        }
        return false;
    }

    /*public Move[] updateMoveset() {
        for (int i = 0; i < this.learnset.size(); i++) {
            if (this.levels[i] == this.statistics.getLevel()) {
                int howManyMoves = 0;
                Move moveToLearn = learnset.get(i);
                for (int k = 0; k < 4; k++) {
                    if (this.moveset[k] != null) {
                        howManyMoves++;
                    }
                }
                if (howManyMoves == 4) {
                    boolean decisionNotMade = true;
                    while (decisionNotMade) {
                        System.out.println(this.nickname + " is trying to learn "
                                + moveToLearn.getName() + ".");
                        System.out.println("But " + this.nickname + " can only "
                                + "learn four moves.");
                        System.out.println("Delete a move to learn " + moveToLearn
                                .getName() + "? (yes/no)");
                        String answer = scanner.nextLine();
                        if (answer.equalsIgnoreCase("yes")) {
                            System.out.println();
                            System.out.println("Press any other key to exit.");
                            for (int j = 0; j < 4; j++) {
                                System.out.println(j + ": " + this.moveset[j]
                                        .getName());
                            }
                            try {
                                int choice = 10;
                                while (choice == 10) {
                                    choice = scanner.nextInt();
                                    if (choice >= 0 && choice <= 3 && this.moveset
                                            [choice] != null) {
                                        System.out.print("One...two...and...");
                                        System.out.println("poof!");
                                        System.out.println(this.nickname + " forgot "
                                                + this.moveset[choice].getName()
                                                + ".");
                                        System.out.println("And...");
                                        System.out.println(this.nickname + " learned "
                                                + moveToLearn.getName() + "!");
                                        this.moveset[choice] = moveToLearn;
                                    } else if (this.moveset[choice] == null) {
                                        choice = 10;
                                    } else {
                                        System.out.println();
                                        System.out.println("Stop trying to learn "
                                                + moveToLearn.getName() + "?");
                                        answer = scanner.nextLine();
                                        if (answer.equalsIgnoreCase("yes")) {
                                            System.out.println();
                                            System.out.println(this.nickname + " did "
                                                    + "not learn " + moveToLearn
                                                    .getName()
                                                    + ".");
                                            decisionNotMade = false;
                                        }
                                    }
                                }
                            } catch (InputMismatchException ex) {
                                System.out.println();
                                System.out.println("Stop trying to learn "
                                        + moveToLearn.getName() + "?");
                                answer = scanner.nextLine();
                                if (answer.equalsIgnoreCase("yes")) {
                                    System.out.println(this.nickname + " did not "
                                            + "learn " + moveToLearn.getName() + ".");
                                    decisionNotMade = false;
                                }
                            }
                        } else {
                            System.out.println("Stop trying to learn " + moveToLearn
                                    .getName() + "?");
                            answer = scanner.nextLine();
                            if (answer.equalsIgnoreCase("yes")) {
                                System.out.println(this.nickname + " did not learn "
                                        + moveToLearn.getName() + ".");
                                decisionNotMade = false;
                            }
                        }
                    }
                } else {
                    System.out.println(this.nickname + " learned " + moveToLearn
                            .getName() + "!");
                    this.moveset[howManyMoves] = moveToLearn;
                }
            }
        }
        return this.moveset;
    }*/

    public String getPokemonName() {
        return this.pokemonName;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String name) {
        this.nickname = name;
    }

    public int getNationalDexNumber() {
        return this.nationalDexNumber;
    }

    public Type[] getType() {
        return this.type;
    }

    /*public Nature getNature() {
        return this.nature;
    }*/

    public Statistics getStatistics() {
        return this.statistics;
    }

    public Move[] getMoveset() {
        return this.moveset;
    }

    public HashMap<Move, Integer> getPowerPointsSet() {
        return this.powerPointsSet;
    }

    public ArrayList<Move> getLearnset() {
        return this.learnset;
    }

    public void setLearnset(ArrayList<Move> learnset) {
        this.learnset = learnset;
    }

    public int getIDNumber() {
        return this.idNumber;
    }

    public void setIDNumber() {
        this.idNumber = Player.getIdNo();
    }

    public int getCatchRate() {
        return this.catchRate;
    }

    public int getThresholdLevel() {
        return this.thresholdLevel;
    }

    public int getBaseExperienceYield() {
        return this.baseExperienceYield;
    }

    @Override
    public boolean equals(Object that) {
        if (null == that) {
            return false;
        }
        if (this == that) {
            return true;
        }
        if (!(that instanceof Pokemon)) {
            return false;
        }

        Pokemon pokemon = (Pokemon) that;

        return this.nickname.equals(pokemon.getNickname()) && this.statistics
                .equals(pokemon.getStatistics()) && this.idNumber == pokemon
                .getIDNumber() && this.powerPointsSet.equals(pokemon.getPowerPointsSet());
    }

	/*public int hashCode() {
        int result = 10;
        result = 31 * result + this.pokemonName.hashCode(); MAYBE SAVE THIS FOR LATER
        return result;
    }*/


    public class Statistics {

        private int baseHealthPoints;
        private int baseAttack;
        private int baseDefense;
        //private int baseSpecialAttack; Will be updated in GEN II
        //private int baseSpecialDefense;
        private int baseSpecial; //Only for GEN I
        private int baseSpeed;
        private int healthPoints;
        private int currentHealthPoints;
        private int attack;
        private int defense;
       // private int specialAttack; Will be updated in GEN II
        //private int specialDefense;
        private int special;
        private int speed;
        private int battleAttack;
        private int battleDefense;
        //private int battleSpecialAttack;
        //private int battleSpecialDefense;
        private int battleSpecial; //Only for GEN I
        private int battleSpeed;
        private double battleAccuracy;
        private double battleEvasiveness;
        private static final int accuracy = 1;
        private static final int evasiveness = 1;
        private int[] individualValues;
        private int[] effortValues;
        private int level;
        private ExperienceType experienceType;
        private int experiencePoints;
        private int experienceNeededForNextLevel;

        private boolean isFainted = false;
        private boolean isAsleep = false;
        private boolean isParalyzed = false;
        private boolean isFrozen = false;
        private boolean isBurned = false;
        private boolean isPoisoned = false;
        private boolean isBadlyPoisoned = false;
        private boolean isConfused = false;
        private boolean isInLove = false;
        private boolean isFlinching;

        //NEED TO ACCOUNT FOR IVS AND EVS <<<<<-----

        public Statistics(int baseHealthPoints, int baseAttack, int
                baseDefense, int baseSpecial, int baseSpeed,
                          int level, ExperienceType
                                  experienceType) {
            this.baseHealthPoints = baseHealthPoints;
            this.baseAttack = baseAttack;
            this.baseDefense = baseDefense;
            this.baseSpecial = baseSpecial;
            //this.baseSpecialAttack = baseSpecialAttack;
            //this.baseSpecialDefense = baseSpecialDefense;
            this.baseSpeed = baseSpeed;
            individualValues = new int[]{0, 0, 0, 0, 0};
            for (int i = 1; i < 4; i++) {
                individualValues[i] = new Random().nextInt(15) + 1;
            }
            individualValues[0] = calculateHPIV();
            effortValues = new int[]{0, 0, 0, 0, 0};
            for (int j = 0; j < 5; j++) {
                effortValues[j] = 0;
            }
            this.level = level;
            this.experienceType = experienceType;
            this.healthPoints = calculateHealthPoints();
            this.currentHealthPoints = healthPoints;
            this.attack = calculateStatistics(baseAttack, 1);
            this.defense = calculateStatistics(baseDefense, 2);
            this.special = calculateStatistics(special, 3);
            //this.specialAttack = calculateStatistics(baseSpecialAttack, 3);
            //this.specialDefense = calculateStatistics(baseSpecialDefense, 4);
            this.speed = calculateStatistics(baseSpeed, 4);
            this.experiencePoints = calcEXP(this.level);
            this.experienceNeededForNextLevel = calcExperienceNeeded();
        }

        public int calculateHPIV() {
            int hp = 0;
            int bit3 = individualValues[1] & 1;
            int bit2 = individualValues[2] & 1;
            int bit1 = individualValues[3] & 1;
            int bit0 = individualValues[4] & 1;
            hp = bit3 << 3 | bit2 << 2 | bit1 << 1 | bit0;
            return hp;
        }

        public int calculateHealthPoints() {
            /*return (int) Math.floor(((2 * (baseHealthPoints + individualValues[0]) +
                    Math.floor(effortValues[0] / 4)) * this.level) / 100) + this.level + 10;*/
            double factorOne = Math.floor(Math.ceil(Math.sqrt(effortValues[0]) / 4.0));
            double factorTwo = ((this.baseHealthPoints + individualValues[0]) * 2
                    + factorOne) * this.level;
            double factorThree = Math.floor(factorTwo / 100.0) + this.level + 10;
            return (int) factorThree;
        }

        public int calculateStatistics(int base, int index) {
            double factorOne = 2 * (base + individualValues[index]);
            double factorTwo = Math.floor(Math.ceil(Math.sqrt(effortValues[index]))
                    / 4.0);
            double factorThree = factorOne + factorTwo;
            double factorFour = factorThree * this.level;
            double factorFive = factorFour / 100.0;
            return (int) Math.floor(factorFive) + 5;
        }

        public int[] getIndividualValues() {
            return this.individualValues;
        }

        public int[] getEffortValues() {
            return this.effortValues;
        }

        public int getBaseHealthPoints() {
            return this.baseHealthPoints;
        }

        public int getBaseAttack() {
            return this.baseAttack;
        }

        public int getBaseDefense() {
            return this.baseDefense;
        }

        public int getBaseSpecial() {
            return this.baseSpecial;
        }

        /*public int getBaseSpecialAttack() {
            return this.baseSpecialAttack;
        }

        public int getBaseSpecialDefense() {
            return this.baseSpecialDefense;
        }*/

        public int getBaseSpeed() {
            return this.baseSpeed;
        }

        public int getHealthPoints() {
            return this.healthPoints;
        }

        public int getCurrentHealthPoints() {
            return this.currentHealthPoints;
        }

        public int getAttack() {
            return this.attack;
        }

        public int getDefense() {
            return this.defense;
        }

        /*public int getSpecialAttack() { //Will be updated in GEN II
            return this.specialAttack;
        }

        public int getSpecialDefense() {
            return this.specialDefense;
        }*/

        public int getSpecial() { //For GEN I
            return this.special;
        }

        public int getSpeed() {
            return this.speed;
        }

        public int getBattleAttack() {
            return this.battleAttack;
        }

        public int getBattleDefense() {
            return this.battleDefense;
        }

        public int getBattleSpecial() {
            return this.battleSpecial;
        }

        /*public int getBattleSpecialAttack() {
            return this.battleSpecialAttack;
        }

        public int getBattleSpecialDefense() {
            return this.battleSpecialDefense;
        }*/

        public int getBattleSpeed() {
            return this.battleSpeed;
        }

        public double getBattleAccuracy() {
            return this.battleAccuracy;
        }

        public double getBattleEvasiveness() {
            return this.battleEvasiveness;
        }

        public int getLevel() {
            return this.level;
        }

        public boolean getIsFainted() {
            return this.isFainted;
        }

        public boolean getIsAsleep() {
            return this.isAsleep;
        }

        public boolean getIsParalyzed() {
            return this.isParalyzed;
        }

        public boolean getIsFrozen() {
            return this.isFrozen;
        }

        public boolean getIsBurned() {
            return this.isBurned;
        }

        public boolean getIsPoisoned() {
            return this.isPoisoned;
        }

        public boolean getIsBadlyPoisoned() {
            return this.isBadlyPoisoned;
        }

        public boolean getIsConfused() {
            return this.isConfused;
        }

        public boolean getIsInLove() {
            return this.isInLove;
        }

        public boolean getIsFlinching() {return this.isFlinching; }

        public void setIsParalyzed(boolean b) {
            this.isParalyzed = b;
        }

        public void setIsFrozen(boolean b) {
            this.isFrozen = b;
        }

        public void setIsBurned(boolean b) {
            this.isBurned = b;
        }

        public void setIsPoisioned(boolean b) {
            this.isPoisoned = b;
        }

        public void setIsConfused(boolean b) {
            this.isConfused = b;
        }

        public void setIsBadlyPoisoned(boolean b) {
            this.isBadlyPoisoned = b;
        }

        public void setIsInLove(boolean b) {
            this.isInLove = b;
        }

        public void setIsFlinching(boolean b) {this.isFlinching = b;}

        public void setEffortValues(int value, int i) {
            if(this.effortValues[i] + value >= 65535) {
                this.effortValues[i] += value;
                this.attack = calculateStatistics(this.baseAttack, 1);
                this.defense = calculateStatistics(this.baseDefense, 2);
                this.special = calculateStatistics(this.baseSpecial, 3);
                this.speed = calculateStatistics(this.baseSpeed, 4);
                this.healthPoints = calculateHealthPoints();
            } else {
                System.out.println("It won't have any effect.");
            }

        }

        //ONLY FOR TESTING
        public void setIndividualValues(int value, int i) {
            this.individualValues[i] = value;
            this.individualValues[0] = calculateHPIV();
            this.attack = calculateStatistics(this.baseAttack, 1);
            this.defense = calculateStatistics(this.baseDefense, 2);
            this.special = calculateStatistics(this.baseSpecial, 3);
            this.speed = calculateStatistics(this.speed, 4);
            this.healthPoints = calculateHealthPoints();
        }

        public int getExperiencePoints() {
            return this.experiencePoints;
        }

        public int calcEXP(int e) {
            int cube = (int) Math.pow(e, 3);
            int exp = 0;
            switch (this.experienceType) {
				/*case ERRATIC:
				if (this.level <= 50) {
					this.experiencePoints = (cube * (100 - this.level)) / 50;
				} else if (this.level >= 50 && this.level <= 68) {
					this.experiencePoints = (cube * (150 - this.level)) / 100;
				} else if (this.level >= 68 && this.level <= 98) {
					this.experiencePoints = (cube * (int) (Math.floor((1911 - (10 * this.level
						)) / 3))) / 500;
				} else if (this.level >= 98 && this.level <= 100) {
					this.experiencePoints = (cube * (160 - this.level)) / 100;
				}
				break;*/
                case FAST:
                    exp = (int) Math.round((4 * cube) / 5.0);
                    break;
                case MEDIUM_FAST:
                    exp = cube;
                    break;
                case MEDIUM_SLOW:
                    exp = (int) Math.round(((6 / 5.0) * cube)) - (15
                            * this.level ^ 2) + (100 * this.level) - 140;
                    break;
                case SLOW:
                    exp = (int) Math.round((5 / 4.0) * cube);
                    break;
				/*case FLUCTUATING:
				if (this.level <= 15) {
					this.experiencePoints = cube * (((Math.floor((
						this.level + 1) / 3) + 24)) / 50);
				} else if (this.level >= 15 && this.level <= 36) {
					this.experiencePoints = cube * (((this.level + 14)) / 50);
				} else if (this.level >= 36 && this.level <= 100) {
					this.experiencePoints = Math.round(cube * ((Math.floor(this.level
						/ 2) + 32) / 50));
				}
				break;*/
            }
            return exp;
        }

        public int calcExperienceNeeded() {
            return calcEXP(this.level + 1) - this.experiencePoints;
        }

        public int getExperienceNeededForNextLevel() {
            return this.experienceNeededForNextLevel;
        }

        public void gainExperience(int i) {
            this.experiencePoints += i;
            this.experienceNeededForNextLevel = calcExperienceNeeded();
            if (experienceNeededForNextLevel <= 0) {
                levelUp();
                this.experienceNeededForNextLevel = calcExperienceNeeded();
            }
        }

        public void levelUp() {
            if (this.level < 100) {
                switch (experienceType) {
                    case FAST:
                        while (this.experiencePoints >= (int) Math.round(4 * (Math.pow(
                                (this.level + 1), 3)) / 5.0)) {
                            this.level++;
                            System.out.println(Pokemon.this.nickname + " grew to level "
                                    + this.level + "!");
                            calculateHealthPoints();
                            calculateStatistics(this.baseAttack, 1);
                            calculateStatistics(this.baseDefense, 2);
                            calculateStatistics(this.baseSpecial, 3);
                            //calculateStatistics(this.baseSpecialDefense, 4);
                            calculateStatistics(this.baseSpeed, 4);
                            //Pokemon.this.updateMoveset();
                        }
                        break;
                    case MEDIUM_FAST:
                        while (this.experiencePoints >= (int) Math.round(
                                Math.pow((this.level + 1), 3))) {
                            this.level++;
                            System.out.println(Pokemon.this.nickname + " grew to level "
                                    + this.level + "!");
                            calculateHealthPoints();
                            calculateStatistics(this.baseAttack, 1);
                            calculateStatistics(this.baseDefense, 2);
                            calculateStatistics(this.baseSpecial, 3);
                            //calculateStatistics(this.baseSpecialDefense, 4);
                            calculateStatistics(this.baseSpeed, 4);
                            //Pokemon.this.updateMoveset();
                        }
                        break;
                    case MEDIUM_SLOW:
                        while (this.experiencePoints >= (int) Math.round(((6 / 5.0) * Math.pow(
                                (this.level + 1), 3)) - (15 * (this.level + 1) ^ 2)
                                + (100 * (this.level + 1)) - 140)) {
                            this.level++;
                            System.out.println(Pokemon.this.nickname + " grew to level "
                                    + this.level + "!");
                            calculateHealthPoints();
                            calculateStatistics(this.baseAttack, 1);
                            calculateStatistics(this.baseDefense, 2);
                            calculateStatistics(this.baseSpecial, 3);
                            //calculateStatistics(this.baseSpecialDefense, 4);
                            calculateStatistics(this.baseSpeed, 4);
                            //Pokemon.this.updateMoveset();
                        }
                        break;
                    case SLOW:
                        while (this.experiencePoints >= (int) Math.round(5 * Math.pow(
                                (this.level + 1), 3) / 4.0)) {
                            this.level++;
                            System.out.println(Pokemon.this.nickname + " grew to level "
                                    + this.level + "!");
                            calculateHealthPoints();
                            calculateStatistics(this.baseAttack, 1);
                            calculateStatistics(this.baseDefense, 2);
                            calculateStatistics(this.baseSpecial, 3);
                            //calculateStatistics(this.baseSpecialDefense, 4);
                            calculateStatistics(this.baseSpeed, 4);
                            //Pokemon.this.updateMoveset();
                        }
                        break;
                }
            }
        }

        public void setCurrentHealthPoints(int i) {
            if (this.currentHealthPoints + i > this.healthPoints) {
                this.currentHealthPoints = this.healthPoints;
            } else if (this.currentHealthPoints + i <= 0) {
                this.currentHealthPoints = 0;
                this.setIsFainted(true);
            } else {
                this.currentHealthPoints += i;
            }
        }

        public void setIsFainted(boolean isFainted) {
            this.isFainted = isFainted;
        }

        public void setIsAsleep(boolean isAsleep) {
            this.isAsleep = isAsleep;
        }

        public boolean equals(Object that) {
            if (null == that) {
                return false;
            }
            if (this == that) {
                return true;
            }
            if (!(that instanceof Statistics)) {
                return false;
            }
            Statistics statistics = (Statistics) that;
            return this.healthPoints == statistics.getHealthPoints() && this.attack
                    == statistics.getAttack() && this.defense == statistics.getDefense()
                    && this.special == statistics.getSpecial() && this.speed == statistics.
                    getSpeed() && this.effortValues == statistics.getEffortValues() &&
                    this.individualValues == statistics.getIndividualValues();
        }
    }

    public void setStats(byte attack, byte defense, byte special, byte speed,
                                byte accuracy, byte evasiveness) {
        if (attack >= 0) {
            this.battleAttack = this.statistics.attack * (int) (1.0 + (attack
                    / 2.0));
        } else {
            this.battleAttack = this.statistics.attack * (int) (1.0 + (attack
                    / (Math.abs(attack) + 2.0)));
        }
        if (defense >= 0) {
            this.battleDefense = this.statistics.defense * (int) (1.0 + (defense
                    / 2.0));
        } else {
            this.battleDefense = this.statistics.defense * (int) (1.0 + (defense
                    / (Math.abs(defense) + 2.0)));
        }
            /*if (specialAttack >= 0) {
                this.battleSpecialAttack = this.specialAttack * (int) (1.0 + (
                        specialAttack / 2.0));
            } else {
                this.battleSpecialAttack = this.specialAttack * (int) (1.0 + (
                        specialAttack / (Math.abs(specialAttack) + 2.0)));
            }
            if (specialDefense >= 0) {
                this.battleSpecialDefense = this.specialDefense * (int) (1.0 + (
                        specialDefense / 2.0));
            } else {
                this.battleSpecialDefense = this.specialDefense * (int) (1.0 + (
                        specialDefense / (Math.abs(specialDefense) + 2.0)));
            }*/
        if (special >= 0) {
            this.battleSpecial = this.statistics.special * (int) (1.0 + (
                    special / 2.0));
        } else {
            this.battleSpecial = this.statistics.special * (int) (1.0 + (
                    special / (Math.abs(special) + 2.0)));
        }
        if (speed >= 0) {
            this.battleSpeed = this.statistics.speed * (int) (1.0 + (speed / 2.0));
        } else {
            this.battleSpeed = this.statistics.speed * (int) (1.0 + (speed / (Math.abs(speed)
                    + 2.0)));
        }
        if (accuracy >= 0) {
            this.battleAccuracy = ((Math.abs(accuracy)+ 3.0)
                    / 3.0);
        } else {
            this.battleAccuracy = (3.0 / ((Math.abs(accuracy) + 3.0)));
        }
        if (evasiveness >= 0) {
            this.battleEvasiveness = (3.0 / (evasiveness + 3.0));
        } else {
            this.battleEvasiveness = ((3.0 + Math.abs(evasiveness)) / 3.0);
        }
    }

    public int getBattleAttack() { return this.battleAttack; }
    public int getBattleDefense() { return this.battleDefense; }
    public int getBattleSpecial() { return this.battleSpecial; }
    public int getBattleSpeed() { return this.battleSpeed; }
    public double getAccuracy() { return this.battleAccuracy; }
    public double getEvasiveness() { return this.battleEvasiveness; }


}
