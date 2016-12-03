#### Project Plan


## Pathfinder Character Sheet Organizer
#### Project Statement

My project is going to be used while playing the table-top RPG Pathfinder.  It is aimed at helping myself and others keep track of their character sheets and game-master notes.  At minimum, my website will function similarly to myth-weavers.com and roll20.net.  Myth-weavers is a site that lets you edit your character sheets and save them online.  Roll20 is an online service that acts as a sort of chatroom combined with resources for the GM to use.

Things I want to accomplish in order of priority:
  * Editable sheets that are saved in a database
  * Page where GM can keep notes for their campaign
  * Page where GM can view player sheets
  * Have sheets dynamically update when you edit fields
  * Have sheets change from the GM's view
  * Chat-room
  * Commands or buttons in chat-room for rolling dice and changing values

I have chosen this project because the current services are either designed for just players, or designed entirely for online use.  When I play Pathfinder, we tend to keep our character sheets on our computers but like using real dice and figures during combat.

#### Technologies
  
  * Log in system with authentication
  * Hibernate to access my database
  * Log4j to help debug
  * JUnit to test
  * I will need to research ways to make my pages dynamically update
  
#
	
	Project Statement:
		My project will be a website for storing character-sheet information
		for the table-top RPG Pathfinder.
		
		There are already a number of good sites for keeping track of character
		sheets
	
		
	
	
	TECHNOLOGY:
		JQuery to send ajax requests to servlet on sheet.jsp page
		
		NEED TO LOOK UP LISTENER FOR GM PAGE
		need to figure out how to do an interpreting system
		
	
		
	PAGES AND COMPONENTS:
	
		JSPs:
			index.jsp
			sheet.jsp
				with id
			game.jsp
				with id
			
		SERVLETS:
			
			createSheet -----
				create button 
					> jquery validation
						> send to servlet
							> add new entry to DB
							
			saveSheet -------
				save button in sheet.jsp 
					> jquery validation 
						> send to servlet 
							> update DB
							
			updateGame ------
				when any field changes in sheet.jsp 
					> send to servlet 
						> send to GM Game page somehow
			
		


	
	IDEAS:
		sheet should be able to check if its "in game",
		if it is in game, whenever you change a field it will do an ajax request
		sent to the servlet that is helping the GM's page
		
		>need some way to make the page 'wait' for a request
		
		when you change a field it gets set to 'changed', so when you click save it 
		will update just those fields, and if you try to close out it will ask you 
		if you want to save.
		
		when a field is changed, check if it is valid, put an error message by it if it is not
				example: having multiples of the same crafting skill
				
		FLY and STEALTH are modified by size, +/-2 for fly and +/-4 for stealth
				put a hover-over note for things like these
		
		When building sheet
			Send request to servlet with ID
			object is returned
			
		Using commands in feats and abilities
			skill.intimidate+str
			
		object structure for fields
			FIELD
				dependentFields[]
				
				.update() {
					update this field based on other fields
					call .update() on dependentFields[]
				}
				
				.updateAbility(String newAbility) {
					
				}

			DEPENDENCY
				STRENGTH SCORE
					STR MODIFIER
						CMB
						CMD
				DEXTERITY SCORE
					DEX MODIFIER
						AC
						CMD
				INTELLIGENCE SCORE
					INT MODIFIER
						int-based skills
				WISDOM SCORE
					WIS MODIFIER
						wis-based skills
			
			
		
	JSP -> SERVLET NOTES
		use angularjs or jQuery to note which tables will be changed
		when you click save, or when it auto saves:
			send only the fields that changed, and update only those
		when a change happens, send a something or other to a listener?



	SHEET FIELDS:
				
		CREATE TABLE users (
			username varchar(30) NOT NULL,
			password varchar(128) NOT NULL,
			email varchar(255),
			PRIMARY KEY (username)
		);
				
		CREATE TABLE sheet_main (
			sheet_id int NOT NULL AUTO_INCREMENT,
			owner varchar(30) NOT NULL,
			character_name varchar(50),
			character_race varchar(50),
			date_created DATETIME,
			last_accessed DATETIME,
			campaign varchar(50),
			PRIMARY KEY (sheet_id),
			FOREIGN KEY (owner) REFERENCES users(username)
		);
		
		//contains character description
		CREATE TABLE sheet_description (
			sheet_id int NOT NULL,
			gender varchar(20),
			alignment varchar(10),
			deity varchar(50),
			age int,
			height varchar(25),
			weight varchar(25),
			visual_description text,
			biography text,
			languages varchar(255),
			PRIMARY KEY (sheet_id),
			FOREIGN KEY (sheet_id) REFERENCES sheet_main(sheet_id)
		);
		
		
		//contains general statistics
		CREATE TABLE sheet_general (
			sheet_id int NOT NULL,
			hp_current int,
			hp_max int,
			init_misc int DEFAULT 0,
			fort_misc int DEFAULT 0,
			ref_misc int DEFAULT 0,
			will_misc int DEFAULT 0,
			PRIMARY KEY (sheet_id),
			FOREIGN KEY (sheet_id) REFERENCES sheet_main(sheet_id)
		);
		
		//This table will hold the information for the different classes
		CREATE TABLE sheet_classes (
			sheet_id int NOT NULL,
			class_name varchar(25) NOT NULL,
			archetype varchar(30),
			level int DEFAULT 0,
			hit_points int DEFAULT 0,
			hit_die int DEFAULT 6,
			bab_progression ENUM('full', '1/2', '1/4'),
			fort_progression ENUM('fast', 'slow'),
			ref_progression ENUM('fast', 'slow'),
			will_progression ENUM('fast', 'slow'),
			skills_per_level int DEFAULT 0,
			caster_ability ENUM('---', 'int', 'wis', 'cha'),
			PRIMARY KEY (sheet_id, class_name),
			FOREIGN KEY (sheet_id) REFERENCES sheet_main(sheet_id)
		);
		
		CREATE TABLE sheet_ability_score_columns (
			sheet_id int NOT NULL,
			column_id int NOT NULL,
			column_name varchar(30),
			str_column int,
			dex_column int,
			con_column int,
			int_column int,
			wis_column int,
			cha_column int,
			PRIMARY KEY (sheet_id, column_id),
			FOREIGN KEY (sheet_id) REFERENCES sheet_main(sheet_id)
		);
		
		CREATE TABLE sheet_skills (
			sheet_id int NOT NULL,
			skill_name varchar(30) NOT NULL,
			skill_ability ENUM('str', 'dex', 'con', 'int', 'wis', 'cha'),
			skill_ranks int DEFAULT 0,
			skill_misc int DEFAULT 0,
			is_class_skill boolean DEFAULT FALSE,
			req_trained boolean DEFAULT FALSE,
			PRIMARY KEY (sheet_id, skill_name),
			FOREIGN KEY (sheet_id) REFERENCES sheet_main(sheet_id)
		);
		
		CREATE TABLE sheet_specialized_skills (
			sheet_id int NOT NULL,
			skill_id int NOT NULL,
			skill_name varchar(30) NOT NULL,
			specialization varchar(30),
			skill_ability ENUM('str', 'dex', 'con', 'int', 'wis', 'cha') DEFAULT 'cha',
			skill_ranks int DEFAULT 0,
			skill_misc int DEFAULT 0,
			is_class_skill boolean DEFAULT FALSE,
			req_trained boolean DEFAULT FALSE,
			PRIMARY KEY (sheet_id, skill_id),
			FOREIGN KEY (sheet_id) REFERENCES sheet_main(sheet_id),
			FOREIGN KEY (sheet_id, skill_name) REFERENCES sheet_skills(sheet_id, skill_name)
		);
	
		
		CREATE TABLE sheet_speeds (
			sheet_id int NOT NULL,
			speed_base int DEFAULT 30,
			speed_fly int,
			speed_swim int,
			speed_climb int,
			speed_burrow int,
			calculate_by varchar(50),
			PRIMARY KEY (sheet_id),
			FOREIGN KEY (sheet_id) REFERENCES sheet_main(sheet_id)
		);
		
		CREATE TABLE sheet_armors (
			sheet_id int NOT NULL,
			armor_id int NOT NULL,
			armor_name varchar(50),
			masterwork BOOLEAN DEFAULT FALSE,
			ac_bonus int DEFAULT 0,
			max_dex_bonus int,
			skill_penalty int DEFAULT 0,
			type varchar(50),
			spell_failure_chance int DEFAULT 0,
			weight int DEFAULT 0,
			proficient BOOLEAN DEFAULT TRUE,
			equipped BOOLEAN DEFAULT FALSE,
			value int DEFAULT 0,
			PRIMARY KEY (sheet_id, armor_id),
			FOREIGN KEY (sheet_id) REFERENCES sheet_main(sheet_id)
		);
		
		CREATE TABLE sheet_weapons (
			sheet_id int NOT NULL,
			weapon_id int NOT NULL,
			weapon_name varchar(50),
			masterwork BOOLEAN DEFAULT FALSE,
			enhancement_bonus int DEFAULT 0,
			damage_roll varchar(10),
			critical_range int DEFAULT 20,
			critical_multiplier int DEFAULT 2,
			attack_ability ENUM('str','dex') DEFAULT 'str',
			damage_ability ENUM('none', 'str', 'dex') DEFAULT 'str',
			`range` int,
			two_hand BOOLEAN DEFAULT FALSE,
			bludgeoning BOOLEAN DEFAULT FALSE,
			piercing BOOLEAN DEFAULT FALSE,
			slashing BOOLEAN DEFAULT FALSE,
			weight int DEFAULT 0,
			proficient BOOLEAN DEFAULT TRUE,
			value int DEFAULT 0,
			PRIMARY KEY (sheet_id, weapon_id),
			FOREIGN KEY (sheet_id) REFERENCES sheet_main(sheet_id)
		);
		
		CREATE TABLE sheet_feats (
			sheet_id int NOT NULL,
			feat_id int NOT NULL,
			feat_name varchar(50) NOT NULL,
			feat_description text,
			PRIMARY KEY (sheet_id, feat_id),
			FOREIGN KEY (sheet_id) REFERENCES sheet_main(sheet_id)
		);
		
		CREATE TABLE sheet_abilities (
			sheet_id int NOT NULL,
			ability_id int NOT NULL,
			ability_name varchar(50),
			ability_description text,
			PRIMARY KEY (sheet_id, ability_id),
			FOREIGN KEY (sheet_id) REFERENCES sheet_main(sheet_id)
		);
		
		CREATE TABLE sheet_racial_traits (
			sheet_id int NOT NULL,
			trait_id int NOT NULL,
			trait_name varchar(50),
			trait_description text,
			PRIMARY KEY (sheet_id, trait_id),
			FOREIGN KEY (sheet_id) REFERENCES sheet_main(sheet_id)
		);
		
		CREATE TABLE sheet_class_features (
			sheet_id int NOT NULL,
			class_name varchar(25) NOT NULL,
			feature_id int NOT NULL,
			feature_name varchar(30),
			feature_description text,
			PRIMARY KEY (sheet_id, class_name, feature_id),
			FOREIGN KEY (sheet_id) REFERENCES sheet_main(sheet_id),
			FOREIGN KEY (sheet_id, class_name) REFERENCES sheet_classes(sheet_id, class_name)
		);
		
		CREATE TABLE sheet_spells (
			sheet_id int NOT NULL,
			class_name varchar(25) NOT NULL,
			spell_id int NOT NULL,
			spell_level int DEFAULT 0,
			spell_name varchar(50),
			school varchar(20),
			subschool varchar(25),
			domain varchar(20),
			subdomain varchar(30),
			bloodline varchar(20),
			patron varchar(15),
			spell_description text,
			target varchar(255),
			`range` varchar(60),
			casting_time varchar(25),
			verbal BOOLEAN DEFAULT FALSE,
			somatic BOOLEAN DEFAULT FALSE,
			material BOOLEAN DEFAULT FALSE,
			focus BOOLEAN DEFAULT FALSE,
			divine_focus BOOLEAN DEFAULT FALSE,
			prepared BOOLEAN DEFAULT FALSE,
			PRIMARY KEY (sheet_id, class_name, spell_id),
			FOREIGN KEY (sheet_id) REFERENCES sheet_main(sheet_id),
			FOREIGN KEY (sheet_id, class_name) REFERENCES sheet_classes(sheet_id, class_name)
		);
		
		CREATE TABLE sheet_money (
			sheet_id int NOT NULL,
			pp int DEFAULT 0,
			gp int DEFAULT 0,
			sp int DEFAULT 0,
			cp int DEFAULT 0,
			PRIMARY KEY (sheet_id),
			FOREIGN KEY (sheet_id) REFERENCES sheet_main(sheet_id)
		);
		
		CREATE TABLE sheet_items (
			sheet_id int NOT NULL,
			item_id int NOT NULL,
			item_name varchar(50),
			item_description text,
			item_quantity int DEFAULT 0,
			unit_weight int DEFAULT 0,
			unit_value int DEFAULT 0,
			PRIMARY KEY (sheet_id, item_id),
			FOREIGN KEY (sheet_id) REFERENCES sheet_main(sheet_id)
		);
		
		
		
		
		sheet_settings table
		
		base attack bonus
		
			SKILLS NOTE:   when filling in from a class
				UPDATE skills SET is_class_skill = true
				WHERE skill_name IN ('Acrobatics', 'Appraise', ...);
		
		
		specialized_skills table
		INSERT INTO sheet_skills_specialized VALUES
			(sheet_id, 'Craft', 'Pottery', 'int', 0, 0, false, false),
			(sheet_id, 'Perform', 'Dance', 'cha', 0, 0, false, false),
			(sheet_id, 'Profession', 'Tailor', 'wis', 0, 0, false, true);
		
		
		
		ON CHARACTER CREATION ----
		
			INSERT INTO sheet_main VALUES (
				sheet_id int NOT NULL AUTO_INCREMENT,
				owner,
				character_name varchar(50),
				character_race varchar(50),
				date_created DATETIME,
				last_accessed DATETIME,
				campaign varchar(50),
				PRIMARY KEY (sheet_id)
			);
			
			//contains character description
			INSERT INTO sheet_description VALUES (
				sheet_id int NOT NULL,
				gender varchar(20),
				alignment varchar(10),
				deity varchar(50),
				age int,
				height varchar(25),
				weight varchar(25),
				visual_description text,
				biography text,
				languages varchar(255),
				PRIMARY KEY (sheet_id)
			);
			
			
			//contains general statistics
			INSERT INTO sheet_general VALUES (
				sheet_id int NOT NULL,
				hp_current int,
				hp_max int,
				init_misc int DEFAULT 0,
				fort_misc int DEFAULT 0,
				ref_misc int DEFAULT 0,
				will_misc int DEFAULT 0,
				PRIMARY KEY (sheet_id)
			);
			
			//This table will hold the information for the different classes
			INSERT INTO sheet_classes VALUES (
				sheet_id,
				"unnamed class",
				null,
				1,
				0,
				6,
				'full',
				'slow',
				'slow',
				'slow',
				0,
				'---'
			);
			
			INSERT INTO sheet_ability_score_columns VALUES
				(sheet_id, 0, 'BASE', 10, 10, 10, 10, 10, 10),
				(sheet_id, 1, 'RACE', 0, 0, 0, 0, 0, 0),
				(sheet_id, 2, 'MISC', 0, 0, 0, 0, 0, 0),
				(sheet_id, 3, 'TEMP', 0, 0, 0, 0, 0, 0);
			
			INSERT INTO sheet_skills VALUES
				(sheet_id, 0, 'Acrobatics', 'dex', 0, 0, false, false),
				(sheet_id, 1, 'Appraise', 'int', 0, 0, false, false),
				(sheet_id, 2, 'Bluff', 'cha', 0, 0, false, false),
				(sheet_id, 3, 'Climb', 'int', 0, 0, false, false),
				(sheet_id, 4, 'Craft', null, 0, 0, false, false),
				(sheet_id, 5, 'Diplomacy', 'int', 0, 0, false, false),
				(sheet_id, 6, 'Disable Device', 'dex', 0, 0, false, true),
				(sheet_id, 7, 'Disguise', 'cha', 0, 0, false, false),
				(sheet_id, 8, 'Escape Artist', 'dex', 0, 0, false, false),
				(sheet_id, 9, 'Fly', 'dex', 0, 0, false, false),
				(sheet_id, 10, 'Handle Animal', 'cha', 0, 0, false, true),
				(sheet_id, 11, 'Heal', 'wis', 0, 0, false, false),
				(sheet_id, 12, 'Intimidate', 'cha', 0, 0, false, false),
				(sheet_id, 13, 'Knowledge (Arcana)', 'int', 0, 0, false, true),
				(sheet_id, 14, 'Knowledge (Dungeoneering)', 'int', 0, 0, false, true),
				(sheet_id, 15, 'Knowledge (Engineering)', 'int', 0, 0, false, true),
				(sheet_id, 16, 'Knowledge (Geography)', 'int', 0, 0, false, true),
				(sheet_id, 17, 'Knowledge (History)', 'int', 0, 0, false, true),
				(sheet_id, 18, 'Knowledge (Local)', 'int', 0, 0, false, true),
				(sheet_id, 19, 'Knowledge (Nature)', 'int', 0, 0, false, true),
				(sheet_id, 20, 'Knowledge (Nobility)', 'int', 0, 0, false, true),
				(sheet_id, 21, 'Knowledge (Planes)', 'int', 0, 0, false, true),
				(sheet_id, 22, 'Knowledge (Religion)', 'int', 0, 0, false, true),
				(sheet_id, 23, 'Linguistics', 'int', 0, 0, false, true),
				(sheet_id, 24, 'Perception', 'wis', 0, 0, false, false),
				(sheet_id, 25, 'Perform', null, 0, 0, false, false),
				(sheet_id, 26, 'Profession', null, 0, 0, false, true),
				(sheet_id, 27, 'Ride', 'dex', 0, 0, false, false),
				(sheet_id, 28, 'Sense Motive', 'wis', 0, 0, false, false),
				(sheet_id, 29, 'Sleight of Hand', 'dex', 0, 0, false, true),
				(sheet_id, 30, 'Spellcraft', 'int', 0, 0, false, true),
				(sheet_id, 31, 'Stealth', 'dex', 0, 0, false, false),
				(sheet_id, 32, 'Survival', 'wis', 0, 0, false, false),
				(sheet_id, 33, 'Swim', 'str', 0, 0, false, false),
				(sheet_id, 34, 'Use Magic Device', 'cha', 0, 0, false, true);
			
			INSERT INTO sheet_speeds (sheet_id, speed_base) VALUES
				(sheet_id, 30);
			
			INSERT INTO sheet_armors VALUES 
			(
				sheet_id int NOT NULL,
				armor_id int NOT NULL,
				armor_name varchar(50),
				masterwork BOOLEAN DEFAULT FALSE,
				ac_bonus int DEFAULT 0,
				max_dex_bonus int,
				skill_penalty int DEFAULT 0,
				type varchar(50),
				spell_failure_chance int DEFAULT 0,
				weight int DEFAULT 0,
				proficient BOOLEAN DEFAULT TRUE,
				equipped BOOLEAN DEFAULT FALSE,
				value int DEFAULT 0,
				PRIMARY KEY (sheet_id, armor_id)
			);
			
			INSERT INTO sheet_weapons VALUES (
				sheet_id int NOT NULL,
				weapon_id int NOT NULL,
				weapon_name varchar(50),
				masterwork BOOLEAN DEFAULT FALSE,
				enhancement_bonus int DEFAULT 0,
				damage_roll varchar(10),
				critical_range int DEFAULT 20,
				critical_multiplier int DEFAULT 2,
				attack_ability ENUM('str','dex') DEFAULT 'str',
				damage_ability ENUM('none', 'str', 'dex') DEFAULT 'str',
				range int,
				two_hand BOOLEAN DEFAULT FALSE,
				bludgeoning BOOLEAN DEFAULT FALSE,
				piercing BOOLEAN DEFAULT FALSE,
				slashing BOOLEAN DEFAULT FALSE,
				weight int DEFAULT 0,
				proficient BOOLEAN DEFAULT TRUE,
				value int DEFAULT 0,
				PRIMARY KEY (sheet_id, weapon_id)
			);
			
			INSERT INTO sheet_feats VALUES (
				sheet_id int NOT NULL,
				feat_id int NOT NULL,
				feat_name varchar(50) NOT NULL,
				feat_description text,
				PRIMARY KEY (sheet_id, feat_id)
			);
			
			INSERT INTO sheet_abilities VALUES (
				sheet_id int NOT NULL,
				ability_id int NOT NULL,
				ability_name varchar(50),
				ability_description text,
				PRIMARY KEY (sheet_id, ability_id)
			);
			
			INSERT INTO class_features VALUES (
				sheet_id int NOT NULL,
				class_name varchar(25) NOT NULL,
				feature_id int NOT NULL,
				feature_name varchar(30),
				feature_description text,
				PRIMARY KEY (sheet_id, class_name, feature_id)
			);
			
			INSERT INTO sheet_spells VALUES (
				sheet_id int NOT NULL,
				class_name varchar(25) NOT NULL,
				spell_id int NOT NULL,
				spell_level int DEFAULT 0,
				spell_name varchar(50),
				school varchar(20),
				subschool varchar(25),
				domain varchar(20),
				subdomain varchar(30),
				bloodline varchar(20),
				patron varchar(15),
				spell_description text,
				target varchar(255),
				range varchar(60),
				casting_time varchar(25),
				verbal BOOLEAN DEFAULT FALSE,
				somatic BOOLEAN DEFAULT FALSE,
				material BOOLEAN DEFAULT FALSE,
				focus BOOLEAN DEFAULT FALSE,
				divine_focus BOOLEAN DEFAULT FALSE,
				prepared BOOLEAN DEFAULT FALSE,
				PRIMARY KEY (sheet_id, class_name, spell_id)
			);
			
			INSERT INTO sheet_money VALUES (
				sheet_id int NOT NULL,
				pp int DEFAULT 0,
				gp int DEFAULT 0,
				sp int DEFAULT 0,
				cp int DEFAULT 0,
				PRIMARY KEY (sheet_id)
			);
			
			INSERT INTO sheet_items VALUES (
				sheet_id int NOT NULL,
				item_id int NOT NULL,
				item_name varchar(50),
				item_description text,
				item_quantity int DEFAULT 0,
				unit_weight int DEFAULT 0,
				unit_value int DEFAULT 0,
				PRIMARY KEY (sheet_id, item_id)
			);
			
			//check-mark all of their class skills
			UPDATE skills SET is_class_skill = true
			WHERE skill_name IN this.classSkills;
			
			
		
		
		
		SELECT * FROM skills
		WHERE sheet_id = this.sheetId
		ORDER BY skill_name;

		
		PAGE LAYOUT
		
			TABS 
				General
					ability scores
				Classes
					Tabs for each class
						Delete button
						Archetype 
						ClassName
						Level
						HitDie
						babProgression
						fortProgression
						refProgression
						willProgression
						
						Class Features
						
						if caster_ability != '---' 
							Spells
						
				Skills
					skills
				
		
		JAVASCRIPT
		
			save progression
				bad: 1/3 * Hit die
				good: 1/2 * hit die + 2
			Seperate 
		
			When save button is clicked --
				Validate all fields that need to be validated
				after all fields are validated, if any are wrong
					then highlight those fields and put an error message at the top of the screen
						"Error: One or more fields have invalid information. Hover over fields for more information."
					
TODO
	draw out website design
	create project folder in intellij
	create jsp pages
	design database
	set up logging

	Look up event listeners
	make database design pictures
	pictures of pages

		joe's enterprise java project
	Pathfinder Website
	
	Save sheets
	Edit sheets
	
	gm being able to give their players XP
		- commands typed in chat or small interface on page
	
	
	
	
	PROFILE PAGE
		shows you a list of character sheets + the games they are in
		maybe let you set an avatar for use in the chat room(?)
		password reset system
		
	
	
	CHARACTER CREATION PAGE
		slowly make your way through a step-by-step process
		navigable by tabs
		this is the part that uses the database
	
	IN-GAME PAGE
		gm view of sheets as they are being edited
			-maybe have a tiny notification when anything is changed
		
		some sort of invite system;
			allows you to 'join' a game
			possibly a chat room too?
			lets you give a sheet to a gm
			
		Problems to solve with game page:
			how to show each player a different version
			how to show the gm the sheets as the values are changing

			
		SELECT sm.sheet_id, sm.owner, sm.character_name, sm.character_race, GROUP_CONCAT(TRIM(CONCAT(sc.archetype, CONCAT(' ', CONCAT(sc.class_name, CONCAT(' ', CONCAT(sc.level)))))) SEPARATOR '/') 
		FROM sheet_main sm JOIN sheet_classes sc ON sm.sheet_id = sc.sheet_id GROUP BY sheet_id;
			
			
		select group_concat(CONCAT(first_name, CONCAT(' ', CONCAT(last_name))) SEPARATOR ', ') from users;
			
		
			
			
			
			
			temp sheet main save (just in case):
			
package entity.sheet;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Joe on 10/26/2016.
 */

@Entity
@Table(name = "sheet_main")
public class SheetMain {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment", strategy="increment")
    @Column(name="sheet_id")
    private int sheetId;

    @Column(name="owner")
    private String username;

    @Column(name="character_name")
    private String characterName;

    @Column(name="character_race")
    private String characterRace;

    @Column(name = "date_created", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @Column(name = "last_accessed", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastAccessed;

    @Column(name="campaign")
    private String campaign;


    public int getSheetId() {
        return sheetId;
    }

    public void setSheetId(int sheetId) {
        this.sheetId = sheetId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getCharacterRace() {
        return characterRace;
    }

    public void setCharacterRace(String characterRace) {
        this.characterRace = characterRace;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastAccessed() {
        return lastAccessed;
    }

    public void setLastAccessed(Date lastAccessed) {
        this.lastAccessed = lastAccessed;
    }

    public String getCampaign() {
        return campaign;
    }

    public void setCampaign(String campaign) {
        this.campaign = campaign;
    }

}






package entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Joe on 10/23/2016.
 */

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    public User(){}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

