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
			username varchar(30) NOT NULL,
			character_name varchar(50),
			character_race varchar(50),
			date_created DATETIME,
			last_accessed DATETIME,
			PRIMARY KEY (sheet_id),
			FOREIGN KEY (username) REFERENCES users(username)
		);
		
		//contains general sheet information,
		CREATE TABLE sheet_general (
			sheet_id int NOT NULL,
			hp_current int,
			hp_max int,
			init_misc int,
		);
		
		//This table will hold the information for the different classes
		CREATE TABLE sheet_classes (
			sheet_id int NOT NULL,
			class_name varchar(50) NOT NULL,
			archetype varchar(50),
			level int,
			bab_progression ENUM('full', '1/2', '1/4'),
			fort_progression ENUM('fast', 'slow'),
			ref_progression ENUM('fast', 'slow'),
			will_progression ENUM('fast', 'slow'),
			caster_ability ENUM('---', 'int', 'wis', 'cha'),
			PRIMARY KEY (sheet_id, class_name),
			FOREIGN KEY (sheet_id) REFERENCES sheet_main(sheet_id)
		);
		
		CREATE TABLE sheet_combat (
			sheet_id int NOT NULL,
			FOREIGN KEY (sheet_id) REFERENCES sheet_main(sheet_id)
		);
		
		//these will tell the combat tab which skills to display, and can be edited
		CREATE TABLE sheet_combat_skills (
			sheet_id int NOT NULL,
			FOREGIN KEY (sheet_id) REFERENCES sheet_main(sheet_id)
		);
		
		CREATE TABLE sheet_ability_scores (
			sheet_id int,
			column_name varchar(30) NOT NULL,
			str_column int,
			dex_column int,
			con_column int,
			int_column int,
			wis_column int,
			cha_column int,
			PRIMARY KEY (sheet_id, column_name),
			FOREIGN KEY (sheet_id) REFERENCES sheet_main(sheet_id)
		);
		
		CREATE TABLE sheet_skills (
			sheet_id int NOT NULL,
			skill_name varchar(30),
			skill_ability ENUM('str', 'dex', 'con', 'int', 'wis', 'cha'),
			skill_ranks int,
			skill_misc int,
			is_class_skill boolean,
			req_trained boolean,
			PRIMARY KEY (sheet_id, skill_name),
			FOREIGN KEY (sheet_id) REFERENCES sheet_main(sheet_id)
		);
		
		CREATE TABLE sheet_skills_specialized (
			sheet_id int NOT NULL,
			skill_name varchar(30),
			specialization varchar(30),
			skill_ability varchar(3),
			skill_ranks int,
			skill_misc int,
			is_class_skill boolean,
			req_trained boolean,
			PRIMARY KEY (sheet_id, skill_name, specialization),
			FOREIGN KEY (sheet_id) REFERENCES sheet_main(sheet_id)
		);
	
		
		CREATE TABLE sheet_speeds {
			sheet_id int NOT NULL,
			speed_land int,
			speed_fly int,
			speed_swim int,
			speed_climb int,
			speed_burrow int,
			PRIMARY KEY (sheet_id)
		}
		
		CREATE TABLE sheet_armors {
			sheet_id int NOT NULL,
			armor_id int NOT NULL,
			armor_name varchar(50),
			masterwork BOOLEAN DEFAULT FALSE,
			ac_bonus int DEFAULT 0,
			max_dex_bonus int,
			skill_penalty int DEFAULT 0,
			type ENUM('shield', 'light', 'medium', 'heavy'),
			spell_failure_chance int DEFAULT 0,
			weight int DEFAULT 0,
			proficient BOOLEAN DEFAULT TRUE,
			equipped BOOLEAN DEFAULT FALSE,
			PRIMARY KEY (sheet_id, armor_id)
		}
		
		CREATE TABLE sheet_weapons {
			sheet_id int NOT NULL,
			weapon_id int NOT NULL,
			weapon_name varchar(50),
			masterwork BOOLEAN DEFAULT FALSE,
			enhancement_bonus int DEFAULT 0,
			damage_roll varchar(6),
			critical_range int DEFAULT 20,
			critical_multiplier int DEFAULT 2,
			attack_ability ENUM('str','dex') DEFAULT 'str',
			damage_ability ENUM('none', 'str', 'dex') DEFAULT 'str',
			range int,
			two_hand BOOLEAN DEFAULT FALSE,
			damage_type ENUM('B','P','S','B/P','B/S','P/S','B/P/S');
			weight int DEFAULT 0,
			proficient BOOLEAN DEFAULT TRUE,
			PRIMARY KEY (sheet_id, weapon_id)
		}
		
		CREATE TABLE sheet_feats {
			sheet_id int NOT NULL,
			feat_name varchar(50) NOT NULL,
			feat_description text,
			script varchar(50),
			PRIMARY KEY (sheet_id, featname)
		}
		
		CREATE TABLE sheet_spells {
			sheet_id int NOT NULL,
			spell_name varchar(50),
		}
		
		sheet_settings table
		
		weapons table
			sheet_id, weapon_id, weapon_name, attack_bonus, critical_range, critical_multiplier, damage_type,
			range, damage, ability_bonus, two_hand
			
		armor table
			sheet_id, armor_id, armor_name, armor_bonus, weight, 
		
		
		fort_base, fort_ability, fort_misc
		
		base attack bonus
		
			SKILLS NOTE:   when filling in from a class
				UPDATE skills SET is_class_skill = true
				WHERE skill_name IN ('Acrobatics', 'Appraise', ...);
		
		
		skills_specialized table
		INSERT INTO sheet_skills_specialized VALUES
			(sheet_id, 'Craft', 'Pottery', 'int', 0, 0, false, false),
			(sheet_id, 'Perform', 'Dance', 'cha', 0, 0, false, false),
			(sheet_id, 'Profession', 'Tailor', 'wis', 0, 0, false, true);
		
		
		
		
		
		ON CHARACTER CREATION ----
		
			INSERT INTO sheet_ability_scores 
				(sheet_id,'BASE', 10, 10, 10, 10, 10, 10),
				(sheet_id,'RACE', 0, 0, 0, 0, 0, 0),
				(sheet_id,'MISC', 0, 0, 0, 0, 0, 0),
				(sheet_id,'TEMP', 0, 0, 0, 0, 0, 0);
			
			INSERT INTO skills VALUES
				(sheet_id, 'Acrobatics', 'dex', 0, 0, false, false),
				(sheet_id, 'Appraise', 'int', 0, 0, false, false),
				(sheet_id, 'Bluff', 'cha', 0, 0, false, false),
				(sheet_id, 'Climb', 'int', 0, 0, false, false),
				(sheet_id, 'Diplomacy', 'int', 0, 0, false, false),
				(sheet_id, 'Disable Device', 'dex', 0, 0, false, true),
				(sheet_id, 'Disguise', 'cha', 0, 0, false, false),
				(sheet_id, 'Escape Artist', 'dex', 0, 0, false, false),
				(sheet_id, 'Fly', 'dex', 0, 0, false, false),
				(sheet_id, 'Handle Animal', 'cha', 0, 0, false, true),
				(sheet_id, 'Heal', 'wis', 0, 0, false, false),
				(sheet_id, 'Intimidate', 'cha', 0, 0, false, false),
				(sheet_id, 'Knowledge (Arcana)', 'int', 0, 0, false, true),
				(sheet_id, 'Knowledge (Dungeoneering)', 'int', 0, 0, false, true),
				(sheet_id, 'Knowledge (Engineering)', 'int', 0, 0, false, true),
				(sheet_id, 'Knowledge (Geography)', 'int', 0, 0, false, true),
				(sheet_id, 'Knowledge (History)', 'int', 0, 0, false, true),
				(sheet_id, 'Knowledge (Local)', 'int', 0, 0, false, true),
				(sheet_id, 'Knowledge (Nature)', 'int', 0, 0, false, true),
				(sheet_id, 'Knowledge (Nobility)', 'int', 0, 0, false, true),
				(sheet_id, 'Knowledge (Planes)', 'int', 0, 0, false, true),
				(sheet_id, 'Knowledge (Religion)', 'int', 0, 0, false, true),
				(sheet_id, 'Linguistics', 'int', 0, 0, false, true),
				(sheet_id, 'Perception', 'wis', 0, 0, false, false),
				(sheet_id, 'Ride', 'dex', 0, 0, false, false),
				(sheet_id, 'Sense Motive', 'wis', 0, 0, false, false),
				(sheet_id, 'Sleight of Hand', 'dex', 0, 0, false, true),
				(sheet_id, 'Spellcraft', 'int', 0, 0, false, true),
				(sheet_id, 'Stealth', 'dex', 0, 0, false, false),
				(sheet_id, 'Survival', 'wis', 0, 0, false, false),
				(sheet_id, 'Swim', 'str', 0, 0, false, false),
				(sheet_id, 'Use Magic Device', 'cha', 0, 0, false, true);
			
			//check-mark all of their class skills
			UPDATE skills SET is_class_skill = true
			WHERE skill_name IN this.classSkills;
			
			
		
		
		
		SELECT * FROM skills
		WHERE sheet_id = this.sheetId
		ORDER BY skill_name DESC;


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
			
	