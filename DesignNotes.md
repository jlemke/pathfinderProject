##TODO
 * ~~draw out website design - needs more work~~
 * Look up event listeners
 * make database design pictures ???
 * pictures of pages
 * Need to speed up sheet load times
 
 
 ###Add more fields:
   * Temp ability score columns
   * Armor Class fields (Dodge, Misc, ) ???
     (possibly make sheet_armor_class table, after some database revisions)
   * 
   
 ###Autofill skills !
 
	
##CURRENT WORK
 * Add rest of fields to sheet
 * ~~Need to create a scripting language for class features/race features/spells~~
	
##IDEAS
 * gm being able to give their players XP
 * commands typed in chat or small interface on page
 * gm view of sheets!  viewSheet.html?id=123
	
	
	
#DESIGN NOTES
	
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

	FUNCTION TEXT
	
		Fighter
		
			Bravery (Ex), 2nd Level, Passive
				add conditional bonus to will saves
					Will save, willSave + Math.floor((LVL + 2)/4), text="Saves against fear."
					
			Armor Training (Ex), 3rd Level, Passive
				reduces armor check penalty on armor and increases maximum dexterity bonus
					armor.acp += Math.floor((LVL + 1)/4)
					armor.maxDex += Math.floor((LVL + 1)/4)
					
			
	class features and other user js stuff
	
	1. reset all functions to their original js
	
	2. loop through list of features/racial traits/spells
	    eval() the code
	    
	    
	$scope.evalCode() {
	    var codeArray = [];
	    
	    var classes = $scope.sheet.sheetClasses;
	    var classFeatures;
	    var code;
	    var tempString;
	    for (var i = 0; i < classes.length; i++) {
	        classFeatures = classes[i].sheetClassFeatures;
	        for (var j = 0; j < classFeatures.length; j++) {
	            if (classFeatures[j].enabled) {
	                //replace keywords thisClass, activeLevel, and enabled with
	                //corresponding scope values
	                tempString = '$scope.sheet.sheetClasses[' + i + ']';
	                code = classFeatures.evalText.replace('thisClass', tempString));
	                tempString = tempString + '.sheetClassFeatures[' + j + ']';
	                code = code.replace('activeLevel', tempString + '.activeLevel');
                    code = code.replace('enabled', tempString + '.enabled');
                    codeArray.push({
                        priority : classFeatures[j].evalPriority,
                        evalText : code
                    };
                }
	        }
	    }
	    
	    for (var i = 0;
	    
	    codeArray.sort(function(a,b) {
	        return a.priority - b.priority;
	    });
	    
	    for (var i = 0; i < codeArray.length; i++) {
	        eval(codeArray[i]);
	    }
	}
	    
    example code
					
		$scope.skillMod = (function(skill) {
		    var orignal_skillMod = $scope.skillMod;
		    
		    //need to add this modification to the hover-popup
		    // "Stern Gaze : (
		    
		    if (thisClass.level >= activeLevel && enabled &&
		           (skill.skillName.toLowerCase() == 'intimidate' || skill.skillName.toLowerCase() == 'sense motive")) {
		        var bonus = (thisClass.level == 1 ? 1 : Math.floor(1/2 * thisClass.level);
		        return original_skillMod(skill) + bonus;
		            
		    } else
		        return original_skillMod(skill);
		})();
				


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
		Sheet interface:
			Attacks, Spells
			<tab>
			</tab>
		
		Modularity:
			allow user to add div's containing fields and add tabs

		GM interface:
			Notes
			Encouters
				Create encounters using monster stats from a database
			
			

			SHEET DEPENDENCIES
				strRow[]
					strScore
						strMod
							cmb
							cmd
							weaponAttackBonus where:attack-ability=this
							weaponDamageBonus where:damage-ability=this
							skillBonus where:skill-ability=this
				dexRow[]
					dexScore
						dexMod
							refSave
							aC
							flatFooted
							touch
							cmd
							weaponAttackBonus where:attack-ability=this
							weaponDamageBonus where:damage-ability=this
							skillBonus where:skill-ability=this
				conRow[]
					conScore
						conMod
							maxHealth
							fortSave
							skill-bonus where:skill-ability=this
				intRow[]
					intScore
						spellDC where:caster-ability=this
						maxSpellLevel where:caster-ability=this
						intMod
							bonusSpellsPerDay classes[].spells[] where:caster-ability=this
							skillBonus where:skill-ability=this
							maxSkillRanks
				wisRow[]
					wisScore
						spellDC where:caster-ability=this
						maxSpellLevel where:caster-ability=this
						wisMod
							willSave
							bonusSpellsPerDay classes[].spells[] where:caster-ability=this
								
							skillBonus where:skill-ability=this
				chaRow[]
					chaScore
						spellDC where:caster-ability=this
						maxSpellLevel where:caster-ability=this
						chaMod
							bonusSpellsPerDay classes[].spells[] where:caster-ability=this
							skillBonus where:skill-ability=this
				classes[]
					level
						maxHealth
						this.casterLevel
						maxSkillRanks
						bab
							cmb
							cmd
						this.fortBonus
							fortSave
						this.refBonus
							refSave
						this.willBonus
							willSave
						this.spellsKnown
						this.spellsPerDay
					hitPoints
						maxHealth
					casterAbility
						this.spellsKnown
					spellCap
						this.spellsKnown
						this.spellsPerDay
					preparedCaster
						this.spells[].spellFailureChance
					casterBonusMisc
						this.casterLevel
					babProgression
						this.babBonus
							bab
					fortProgression
						this.fortBonus
							fortSave
					refProgression
						this.refBonus
							refSave
					willProgression
						this.willBonus
							willSave
					
		
	JSP -> SERVLET NOTES
		use angularjs or jQuery to note which tables will be changed
		when you click save, or when it auto saves:
			send only the fields that changed, and update only those
		when a change happens, send a something or other to a listener?



	SHEET FIELDS:
				
		CREATE TABLE user (
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
			FOREIGN KEY (owner) REFERENCES user(username)
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
			hp_current int DEFAULT 0,
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
			archetype varchar(30) DEFAULT '',
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
			skill_ability ENUM('str', 'dex', 'con', 'int', 'wis', 'cha') DEFAULT 'str',
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
		
			INSERT INTO sheet_main (owner, date_created, last_accessed) VALUES
				(owner, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
			
			INSERT INTO sheet_description (sheet_id) VALUES 
				(sheet_id);
		
			INSERT INTO sheet_general VALUES 
				(sheet_id, hp_current, hp_max, init_misc, fort_misc, ref_misc, will_misc);

			//This table will hold the information for the different classes
			INSERT INTO sheet_classes VALUES 
				(sheet_id, "unnamed class 1", null, 1, 0, 6, 'full', 'slow', 'slow', 'slow', 0, '---');

			INSERT INTO sheet_ability_score_columns VALUES
				(sheet_id, 0, 'BASE', 10, 10, 10, 10, 10, 10),
				(sheet_id, 1, 'RACE', 0, 0, 0, 0, 0, 0),
				(sheet_id, 2, 'MISC', 0, 0, 0, 0, 0, 0),
				(sheet_id, 3, 'TEMP', 0, 0, 0, 0, 0, 0);

			INSERT INTO sheet_skills (sheet_id, skill_order, skill_name, skill_ability, skill_ranks, skill_misc, is_class_skill, req_trained) VALUES
  (120, 1, 'Acrobatics', 'dex', 0, 0, false, false),
  (120, 2, 'Appraise', 'int', 0, 0, false, false),
  (120, 3, 'Bluff', 'cha', 0, 0, false, false),
  (120, 4, 'Climb', 'int', 0, 0, false, false),
  (120, 5, 'Craft', null, 0, 0, false, false),
  (120, 6, 'Diplomacy', 'int', 0, 0, false, false),
  (120, 7, 'Disable Device', 'dex', 0, 0, false, true),
  (120, 8, 'Disguise', 'cha', 0, 0, false, false),
  (120, 9, 'Escape Artist', 'dex', 0, 0, false, false),
  (120, 10, 'Fly', 'dex', 0, 0, false, false),
  (120, 11, 'Handle Animal', 'cha', 0, 0, false, true),
  (120, 12, 'Heal', 'wis', 0, 0, false, false),
  (120, 13, 'Intimidate', 'cha', 0, 0, false, false),
  (120, 14, 'Knowledge (Arcana)', 'int', 0, 0, false, true),
  (120, 15, 'Knowledge (Dungeoneering)', 'int', 0, 0, false, true),
  (120, 16, 'Knowledge (Engineering)', 'int', 0, 0, false, true),
  (120, 17, 'Knowledge (Geography)', 'int', 0, 0, false, true),
  (120, 18, 'Knowledge (History)', 'int', 0, 0, false, true),
  (120, 19, 'Knowledge (Local)', 'int', 0, 0, false, true),
  (120, 20, 'Knowledge (Nature)', 'int', 0, 0, false, true),
  (120, 21, 'Knowledge (Nobility)', 'int', 0, 0, false, true),
  (120, 22, 'Knowledge (Planes)', 'int', 0, 0, false, true),
  (120, 23, 'Knowledge (Religion)', 'int', 0, 0, false, true),
  (120, 24, 'Linguistics', 'int', 0, 0, false, true),
  (120, 25, 'Perception', 'wis', 0, 0, false, false),
  (120, 26, 'Perform', null, 0, 0, false, false),
  (120, 27, 'Profession', null, 0, 0, false, true),
  (120, 28, 'Ride', 'dex', 0, 0, false, false),
  (120, 29, 'Sense Motive', 'wis', 0, 0, false, false),
  (120, 30, 'Sleight of Hand', 'dex', 0, 0, false, true),
  (120, 31, 'Spellcraft', 'int', 0, 0, false, true),
  (120, 32, 'Stealth', 'dex', 0, 0, false, false),
  (120, 33, 'Survival', 'wis', 0, 0, false, false),
  (120, 34, 'Swim', 'str', 0, 0, false, false),
  (120, 35, 'Use Magic Device', 'cha', 0, 0, false, true);

			INSERT INTO sheet_speeds (sheet_id, speed_base) VALUES
				(sheet_id, 30);

			INSERT INTO sheet_money (sheet_id) VALUES 
				(sheet_id);
				
			test sheet
			
			INSERT INTO sheet_main (owner, date_created, last_accessed) VALUES
				('jlemke', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
			
			
			
			INSERT INTO sheet_description (sheet_id) VALUES 
				(3);
		
			INSERT INTO sheet_general VALUES 
				(3, 0, 0, 0, 0, 0);

			//This table will hold the information for the different classes
			INSERT INTO sheet_classes VALUES 
				(3, "class 1", null, 1, 0, 6, 'full', 'slow', 'slow', 'slow', 0, '---'),
				(3, "class 2", null, 1, 0, 6, 'full', 'slow', 'slow', 'slow', 0, '---');
			
			INSERT INTO sheet_ability_score_columns VALUES
				(3, 0, 'BASE', 10, 10, 10, 10, 10, 10),
				(3, 1, 'RACE', 0, 0, 0, 0, 0, 0),
				(3, 2, 'MISC', 0, 0, 0, 0, 0, 0),
				(3, 3, 'TEMP', 0, 0, 0, 0, 0, 0);

			INSERT INTO sheet_skills VALUES
				(3, 'Acrobatics', 'dex', 0, 0, false, false),
				(3, 'Appraise', 'int', 0, 0, false, false),
				(3, 'Bluff', 'cha', 0, 0, false, false),
				(3, 'Climb', 'int', 0, 0, false, false),
				(3, 'Craft', null, 0, 0, false, false),
				(3, 'Diplomacy', 'int', 0, 0, false, false),
				(3, 'Disable Device', 'dex', 0, 0, false, true),
				(3, 'Disguise', 'cha', 0, 0, false, false),
				(3, 'Escape Artist', 'dex', 0, 0, false, false),
				(3, 'Fly', 'dex', 0, 0, false, false),
				(3, 'Handle Animal', 'cha', 0, 0, false, true),
				(3, 'Heal', 'wis', 0, 0, false, false),
				(3, 'Intimidate', 'cha', 0, 0, false, false),
				(3, 'Knowledge (Arcana)', 'int', 0, 0, false, true),
				(3, 'Knowledge (Dungeoneering)', 'int', 0, 0, false, true),
				(3, 'Knowledge (Engineering)', 'int', 0, 0, false, true),
				(3, 'Knowledge (Geography)', 'int', 0, 0, false, true),
				(3, 'Knowledge (History)', 'int', 0, 0, false, true),
				(3, 'Knowledge (Local)', 'int', 0, 0, false, true),
				(3, 'Knowledge (Nature)', 'int', 0, 0, false, true),
				(3, 'Knowledge (Nobility)', 'int', 0, 0, false, true),
				(3, 'Knowledge (Planes)', 'int', 0, 0, false, true),
				(3, 'Knowledge (Religion)', 'int', 0, 0, false, true),
				(3, 'Linguistics', 'int', 0, 0, false, true),
				(3, 'Perception', 'wis', 0, 0, false, false),
				(3, 'Perform', null, 0, 0, false, false),
				(3, 'Profession', null, 0, 0, false, true),
				(3, 'Ride', 'dex', 0, 0, false, false),
				(3, 'Sense Motive', 'wis', 0, 0, false, false),
				(3, 'Sleight of Hand', 'dex', 0, 0, false, true),
				(3, 'Spellcraft', 'int', 0, 0, false, true),
				(3, 'Stealth', 'dex', 0, 0, false, false),
				(3, 'Survival', 'wis', 0, 0, false, false),
				(3, 'Swim', 'str', 0, 0, false, false),
				(3, 'Use Magic Device', 'cha', 0, 0, false, true);

			INSERT INTO sheet_skills_specialized VALUES
				(3, 0, 'Craft', 'Pottery', 'int', 0, 0, false, false),
				(3, 1, 'Perform', 'Dance', 'cha', 0, 0, false, false),
				(3, 2, 'Profession', 'Tailor', 'wis', 0, 0, false, true);

			INSERT INTO sheet_speeds (sheet_id, speed_base) VALUES
				(3, 30);

			INSERT INTO sheet_money (sheet_id) VALUES 
				(3);
			
			
			
			
			
			
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
					