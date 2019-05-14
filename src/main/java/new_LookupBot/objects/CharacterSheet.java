package new_LookupBot.objects;

public class CharacterSheet {
    private String roworder;
    private String rowID;
    private String sheetID;
    private String sheetURL;
    private String timestamp;
    private String mailAddress;
    private String playerID;
    private String playerName;
    private String characterName;
    private String characterImages;
    private String settings;
    private String universeOfOrigin;
    private String gender;
    private String height;
    private String weight;
    private String hairColor;
    private String eyeColor;
    private String age;
    private String bodyType;
    private String dob;
    private String pob;
    private String occupation;
    private String rankLevel;
    private String background;
    private String alignmentAuthority;
    private String alignmentPeople;
    private String personality;
    private String abilities;
    private String skills;
    private String equipment;
    String documentUpload;
    private String race;

    public String toPage() {
        StringBuilder sb = new StringBuilder();
        sb.append("{{Character Box");
        if (getPlayerID() != null) {
            sb.append("|player=").append(getPlayerID());
        }
        if (getCharacterName() != null) {
            sb.append("|fullname = ").append(getCharacterName());
        }
        if (getRace() != null) {
            sb.append("|race=").append(getRace());
        }
        if (getGender() != null) {
            sb.append("|gender=").append(getGender());
        }
        if (getHeight() != null) {
            sb.append("|height=").append(getHeight());
        }
        if (getWeight() != null) {
            sb.append("|weight=").append(getWeight());
        }
        if (getHairColor() != null) {
            sb.append("|haircolor=").append(getHairColor());
        }
        if (getEyeColor() != null) {
            sb.append("|eyecolor=").append(getEyeColor());
        }
        if (getAge() != null) {
            sb.append("|age=").append(getAge());
        }
        if (getPob() != null) {
            sb.append("|birthplace=").append(getPob());
        }
        if (getOriginUniverse() != null) {
            sb.append("|universe=").append(getOriginUniverse());
        }
        if (getOccupation() != null) {
            sb.append("|occ=").append(getOccupation());
        }
        if (getBodyType() != null) {
            sb.append("|figure=").append(getBodyType());
        }
        if (getRankLevel() != null) {
            sb.append("|rank=").append(getRankLevel());
        }
        if (getDob() != null) {
            sb.append("|dob=").append(getDob());
        }
        if (getRace() != null) {
            sb.append("|race=").append(getRace());
        }
        sb.append("|status=Unapproved");
        sb.append("}}\n\n");
        if (getPersonality() != null) sb.append(getPersonality()).append("\n\n");
        if (getAbilities() != null) {
            sb.append("==Special Abilities==\n");
            sb.append(getAbilities());
        }
        if (getSkills() != null) {
            sb.append("\n\n==Special Skills==\n");
            sb.append(getSkills());
        }
        if (getBackground() != null) {
            sb.append("\n\n==Background==\n");
            sb.append(getBackground());
        }
        if(getEquipment() != null) {
            sb.append("\n\n==Equipment==\n");
            sb.append(getEquipment());
        }
        if (getSettings()!=null)
        {
            String settings = getSettings();
            if(settings.contains("Blazing Umbra"))
            {
                sb.append("[[Category:Blazing Umbra Characters]]");
            }
            if(settings.contains("Angelic Sins"))
            {
                sb.append("[[Categoery:Angelic Sins Characters]]");
            }
            if(settings.contains("Embers of Soteria"))
            {
                sb.append("[[Category:Embers of Soteria Characters]]");
            }
        }

        return sb.toString();
    }

    public String getPlayerID() {
        return playerID;
    }

    public String getCharacterName() {
        return characterName;
    }

    public String getRace() {
        return race;
    }

    public String getGender() {
        return gender;
    }

    public String getHeight() {
        return height;
    }

    public String getWeight() {
        return weight;
    }

    public String getHairColor() {
        return hairColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public String getAge() {
        return age;
    }

    public String getDob() {
        return dob;
    }

    public String getPob() {
        return pob;
    }

    public String getOriginUniverse() {
        return universeOfOrigin;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getRankLevel() {
        return rankLevel;
    }

    public String getBodyType() {
        return bodyType;
    }

    public String getPersonality() {
        return personality;
    }

    public String getAbilities() {
        return abilities;
    }

    public String getSkills() {
        return skills;
    }

    private String getBackground() {
        return background;
    }
    public String getCharacterImages() {
        return characterImages;
    }
    public String getSettings() {
        return settings;
    }
    public String getAlignmentAuthority() {
        return alignmentAuthority;
    }
    public String getAlignmentPeople() {
        return alignmentPeople;
    }
    public String getEquipment() {
        return equipment;
    }
    public String getDocumentUpload() {
        return documentUpload;
    }
}