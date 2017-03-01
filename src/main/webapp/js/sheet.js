/**
 * Created by Joe on 12/5/2016.
 */

var saveInProgress = false;

var app = angular.module('sheetApp', ['ui.bootstrap']);

//TODO figure out why mdDialog is mad
app.controller('sheetController', function($scope, $http, $location, $uibModal) {

    /**
     * on page load retrieve sheet information for given id
     */
    angular.element(document).ready(function() {
        $scope.loaded = false;
        var url = $location.absUrl();
        console.log(url);
        var id = url.substring(url.lastIndexOf("=") + 1);
        console.log("sending sheet request to /sheet?id=" + id);
        $http({
            method : "GET",
            url : "sheet?id=" + id
        }).then(function(response) {
            $scope.sheet = response.data;
            $scope.loaded = true;
            console.log("sheet retrieved");
            console.log($scope.sheet);
            $scope.evalCode();
        });
    });

    /**
     * Confirmation pop-up when deleting something
     */
    $scope.confirmDelete = function(collection, object) {
        /*
        $mdDialog.show(
            $mdDialog.confirm()
                .clickOutsideToClose(true)
                .title('Confirm deletion')
                .textContent('Delete this item?')
                .ok('Delete it!')
                .cancel('Cancel')
        ).then(function() {
            collection.splice(index, 1);
        });
        */
        var index = collection.indexOf(object);
        collection.splice(index, 1);
    };

    /**
     * calculate the base attack bonus using class information
     * @param displayPosNeg boolean option to add "+"
     * @return {*} returns a string with "+" attached if displayPosNeg
     *  is given, otherwise returns the bonus as an int
     */
    $scope.bab = function(displayPosNeg) {
        if ($scope.sheet == undefined) return "";
        var bonus = 0;
        var thisClass;
        var prog = {};
        for (var i = 0; i < $scope.sheet.sheetClasses.length; i++) {
            thisClass = $scope.sheet.sheetClasses[i];
            prog = thisClass.babProgression;
            if (prog == "full")
                bonus += Number(thisClass.level);
            else if (prog == "3/4")
                bonus += Math.floor(Number(thisClass.level) * 0.75);
            else
                bonus += Math.floor(Number(thisClass.level) * 0.5);
        }

        if (displayPosNeg)
            return "+" + bonus;
        else return bonus;
    };

    $scope.initiative = function(displayPosNeg) {
        if ($scope.sheet == undefined) return "";
        var bonus = $scope.abilityMod('dex') + $scope.sheet.sheetGeneral.initMisc;
        if (displayPosNeg && bonus >= 0)
            return "+" + bonus;
        return bonus;
    };

    /**
     * calculate max hp based on classes
     * @return int returns the maximum hit points
     */
    $scope.maxHp = function() {
        if ($scope.sheet == undefined) return 0;
        var max = 0;
        for (var i = 0; i < $scope.sheet.sheetClasses.length; i++) {
            max += $scope.sheet.sheetClasses[i].hitPoints;
            max += $scope.abilityMod('con', false) * $scope.sheet.sheetClasses[i].level;
        }
        return max;
    };

    /**
     * returns max skill points based on classes and intelligence
     */
    $scope.maxSkillRanks = function() {
        if ($scope.sheet == undefined) return 0;
        var max = 0;
        var c;
        for (var i = 0; i < $scope.sheet.sheetClasses.length; i++) {
            c = $scope.sheet.sheetClasses[i];
            max+= (c.skillsPerLevel + abilityMod("int", false)) * c.level;
        }
    };

    /**
     * get the total ability score using the ability score columns
     * @param ability the 3-letter string representation of the ability
     * @return int the totaled score
     */
    $scope.abilityScoreSum = function(ability) {
        if ($scope.sheet == undefined) return 0;
        var sum = 0;
        var cols = $scope.sheet.sheetAbilityScoreColumns;
        for (var i = 0; i < cols.length; i++) {
            if (cols[i].enabled)
                sum += Number(cols[i][ability + "Row"]);
        }
        return sum;
    };

    /**
     * calculates the ability modifier of a given ability score
     * @param ability the 3-letter representation of the ability
     * @param displayPosNeg if true then it adds on a "+" to the number when positive
     * @return string returns a string when displayPosNeg is true, otherwise a number
     */
    $scope.abilityMod = function(ability, displayPosNeg) {
        if ($scope.sheet == undefined) return 0;
        var mod = Math.floor(($scope.abilityScoreSum(ability) - 10) / 2);
        if (displayPosNeg && mod >= 0)
            return "+" + mod;
        else return mod;
    };

    $scope.savingThrow = function(save, displayPosNeg) {
        if ($scope.sheet == undefined) return 0;
        var value = 0;
        if (save == 'fort')
            value += $scope.abilityMod('con');
        else if (save == 'ref')
            value += $scope.abilityMod('dex');
        else
            value += $scope.abilityMod('wis');

        var classes = $scope.sheet.sheetClasses;
        for (var i = 0; i < classes.length; i++) {
            if (classes[i][save + 'Progression'] == 'fast')
                value += Math.floor((Number(classes[i].level) + 4)/2);
            else if (classes[i][save + 'Progression'] == 'slow')
                value += Math.floor(Number(classes[i].level)/3);
        }

        if (displayPosNeg && value >= 0)
            return "+" + value;
        return value;
    };

    $scope.armorClass = function(type) {
        if ($scope.sheet == undefined) return 0;
        var base = 10;
        var armorBonus = 0;
        var maxDex = 99;
        var armors = $scope.sheet.sheetArmors
        for (var i = 0; i < armors.length; i++) {
            armorBonus += Number(armors[i].acBonus);
            if (Number(armors[i].maxDexBonus) < maxDex)
                maxDex = Number(armors[i].maxDexBonus);
        }

        var dexMod = $scope.abilityMod('dex');
        var dexBonus = dexMod <= maxDex ? dexMod : maxDex;

        if (type == 'flat-footed')
            return base + armorBonus;
        if (type == 'touch')
            return base + dexBonus;
        else return base + armorBonus + dexBonus;
    };

    $scope.addCol = function() {
        var newCol = {
            sheetId : $scope.sheet.sheetId,
            columnName : "new",
            strRow : 0,
            dexRow : 0,
            conRow : 0,
            intRow : 0,
            wisRow : 0,
            chaRow : 0,
            enabled : false
        };
        $scope.sheet.sheetAbilityScoreColumns.push(newCol);
    };

    /**
     * Used for labeling class tabs
     * @param c class object
     */
    $scope.classHeading = function(c) {
        if ($scope.sheet == undefined) return "";
        if (c.className == "")
            return "Class " + ($scope.sheet.sheetClasses.indexOf(c) + 1);
        else {
            var heading = "";
            if (c.archetype != "")
                heading = c.archetype + " ";
            heading += c.className + " " + c.level;
            return heading;
        }
    };

    /**
     * returns the sheet's classes formatted into one string
     * format : archetype1 class1 level1/archetype2 class2 level2/ ...
     * @return string returns the formatted string
     */
    $scope.classesString = function() {
        if ($scope.sheet == undefined) return "";
        var outputString = "";
        var thisClass;
        for (var i = 0; i < $scope.sheet.sheetClasses.length; i++) {
            thisClass = $scope.sheet.sheetClasses[i];
            if (thisClass.archetype != undefined && thisClass.archetype != "")
                outputString += thisClass.archetype + " ";
            outputString += thisClass.className + " " + thisClass.level + "/";
        }
        return outputString.substring(0, outputString.length - 1);
    };

    $scope.selectedClass = 0;

    /**
     * returns the caster level for a class using the misc bonus and the class level
     * @param thisClass the class object
     * @return int the caster level
     */
    $scope.casterLevel = function(thisClass) {
        if ($scope.sheet == undefined) return "";
        if (thisClass.spellCap == "6th" || thisClass.spellCap == "9th")
            return Number(thisClass.level) + Number(thisClass.casterBonusMisc);
        if (thisClass.spellCap == "4th" && thisClass.level > 3)
            return Number(thisClass.level) - 3 + Number(thisClass.casterBonusMisc);
        else return thisClass.casterBonusMisc;
    };

    /**
     * Returns an array containing all spells from all classes
     */
    $scope.allSpells = function() {
        if ($scope.sheet == undefined) return [];
        var spells = [];
        var classes = $scope.sheet.sheetClasses;
        for (var i = 0; i < classes.length; i++) {
            if (classes[i].sheetSpells != undefined)
                spells = spells.concat(classes[i].sheetSpells);
        }
        return spells;
    };

    //TODO figure out if this is useful?
    $scope.spellsPerDay = function(thisClas, spellLevel) {
        var spells = 0;

        if (thisClass.spellCap == "4th" && spellLevel < 5) {

        } else if (thisClass.spellCap == "6th" && spellLevel < 7) {

        } else if (thisClass.spellCap == "9th") {
            if (spellLevel == 0) {
                if (thisClass.level == 1)
                    spells = 3;
                else
                    spells = 4;
            } else {
                //return 0 if they can't cast this level yet
                if (Math.floor((thisClass.level + 1) / 2) < spellLevel)
                    return 0;

                var caster9th = [1, 2, 2, 3, 3, 3, 4];
                if (thisClass.level == 20 || thisClass.level >= 2 * spellLevel + 5)
                    spells = 4;
                else if (spellLevel == 9)
                    spells = thisClass.level - 16;
                else
                    spells = caster9th[thisClass.level + 1 - 2 * spellLevel]
            }
        } else
            return 0;

        //bonus spells per day = 2 + spell level + abilityMod / 4 rounded down
        var bonusSpells = Math.floor(($scope.abilityMod(thisClass.casterAbility) + spellLevel + 2)/4);
        if (bonusSpells > 0)
            spells += bonusSpells;

        return spells;

    };

    //TODO figure out if this is useful...
    $scope.spellsKnown = function(thisClass, spellLevel) {

    };

    /**
     * returns the sum of skill modifiers for a skill
     */
    $scope.skillMod = function(skill) {
        if ($scope.sheet == undefined) return 0;
        var total = 0;
        total += $scope.abilityMod(skill.skillAbility);
        total += Number(skill.skillRanks);
        if (skill.classSkill && skill.skillRanks > 0)
            total += 3;
        total += Number(skill.skillMisc);
        total += $scope.armorCheckPenalty(skill);
        return total;
    };

    /**
     * Returns the skillsPerLevel + INT multiplied by class levels
     * @returns {number} max number of skill ranks
     */
    $scope.totalSkillRanks = function() {
        if ($scope.sheet == undefined) return 0;
        var total = 0;
        var skills = $scope.sheet.sheetSkills;
        for (var i = 0; i < skills.length; i++) {
            total += Number(skills[i].skillRanks);
        }
        return total;
    };

    $scope.maxSkillRanks = function() {
        if ($scope.sheet == undefined) return 0;
        var total = 0;
        var classes = $scope.sheet.sheetClasses;
        for (var i = 0; i < classes.length; i++) {
            total += classes[i].level * (Number(classes[i].skillsPerLevel) + $scope.abilityMod('int', false));
        }
        return total;
    };

    /**
     * returns the penalty from armor on dex and str based checks
     * @return int total armor check penalty
     */
    $scope.armorCheckPenalty = function(skill) {
        if ($scope.sheet == undefined) return 0;
        var acp = 0;
        for (var i = 0; i < $scope.sheet.sheetArmors.length; i++) {
            if ($scope.sheet.sheetArmors[i].equipped) {
                acp += Math.abs(Number($scope.sheet.sheetArmors[i].skillPenalty));
                if ($scope.sheet.sheetArmors[i].masterwork && Number($scope.sheet.sheetArmors[i].skillPenalty) < 0)
                    acp--;
            }
        }

        if (["con", "int", "wis", "cha"].indexOf(skill.skillAbility) != -1)
            return 0;
        return -1 * acp;
    };

    /**
     * returns a gold piece value of platinum, gold, silver, and copper pieces
     * @returns {number} rounded to 2 decimal places
     */
    $scope.amountOfGold = function() {
        if ($scope.sheet == undefined) return 0;
        return $scope.sheet.sheetMoney.pp * 10
            + Number($scope.sheet.sheetMoney.gp)
            + $scope.sheet.sheetMoney.sp * 0.1
            + $scope.sheet.sheetMoney.cp * 0.01;
    };

    /**
     * returns the total of all item values (in gold)
     * @returns {number} rounded to 2 decimal places
     */
    $scope.totalItemValue = function() {
        if ($scope.sheet == undefined) return 0;
        var items = $scope.sheet.sheetItems;
        var total = 0;
        for (var i = 0; i < items.length; i++) {
            total += Number(items[i].itemQuantity) * Number(items[i].unitValue);
        }
        return Number(parseFloat(total).toFixed(2));
    };

    $scope.removeFrom = function(collection, object) {
        var index = collection.indexOf(object);
        console.log(index);
        collection.splice(index, 1);
    };

    $scope.addItem = function() {
        console.log("adding item");
        var newItem = {
            sheetId : $scope.sheet.sheetId,
            itemName : "",
            itemDescription : "",
            itemQuantity : 0,
            unitWeight : 0,
            unitValue : 0
        };
        $scope.sheet.sheetItems.push(newItem);
    };

    $scope.addArmor = function() {
        var newArmor = {
            sheetId : $scope.sheet.sheetId,
            armorName : "",
            masterwork : false,
            acBonus : 0,
            maxDexBonus : 0,
            skillPenalty : 0,
            equipped : false,
            spellFailureChance : 0,
            weight : 0,
            proficient : true,
            type : ""
        };
        $scope.sheet.sheetArmors.push(newArmor);
    };

    $scope.addWeapon = function() {
        var newWeapon = {
            sheetId : $scope.sheet.sheetId,
            weaponName : "",
            masterwork : false,
            enhancementBonus : 0,
            damageRoll : null,
            criticalRange : "20",
            criticalMultiplier : "x2",
            attackAbility : "str",
            damageAbility : "none",
            range : 0,
            twoHand : false,
            bludgeoning : false,
            piercing : false,
            slashing : false,
            weight : 0,
            proficient : true,
            worth : 0
        };
        $scope.sheet.sheetWeapons.push(newWeapon);
    };

    $scope.addClass = function() {
        var classes = $scope.sheet.sheetClasses;
        var newClass = {
            sheetId : $scope.sheet.sheetId,
            className : "New Class",
            archetype : "",
            level : 1,
            hitPoints : 0,
            hitDie : 6,
            babProgression : "1/2",
            fortProgression : "slow",
            refProgression : "slow",
            willProgression : "slow",
            skillsPerLevel : 0,
            casterAbility : "---",
            spellCap : "None",
            castingType : "None",
            preparedCaster : false,
            casterBonusMisc : 0
        };
        classes.push(newClass);
        $scope.selectedClass = classes.length - 1;
    };

    $scope.addSpell = function(thisClass) {
        var newSpell = {
            classId : thisClass.classId,
            spellLevel : 0,
            spellName : "New Spell",
            school : "",
            subschool : "",
            bloodline : "",
            patron : "",
            spellDescription : "",
            target : "",
            range : "",
            castingTime : "",
            verbal : false,
            somatic : false,
            material : false,
            focus : false,
            divineFocus : false,
            prepared : false
        };
        thisClass.sheetSpells.push(newSpell);
    };

    $scope.addClassFeature = function(thisClass) {
        var newClassFeature = {
            classId : thisClass.classId,
            featureName : "New Feature",
            featureDescription : "",
            activeLevel : 1,
            evalText : "",
            evalPriority : 0,
            enabled : false
        };
        thisClass.sheetClassFeatures.push(newClassFeature);
        /*      TODO do regex so you can put this as default!
        "/**" +
        "* the following keywords will be replaced with their " +
        "* values in the model: " +
        "*      thisClass : replaced with the parent class of the feature" +
        "*      this : replaced with the feature object for this feature" +
        "**" */
    };

    $scope.addAbility = function() {
        console.log("works");
        var newAbility = {
            sheetId : $scope.sheet.sheetId,
            abilityName : "New Ability",
            abilityDescription : "",
            type : "feat",
            evalText : "",
            evalPriority : 0,
            activeLevel : 1,
            enabled : false
        };
        $scope.sheet.sheetAbilities.push(newAbility);
    };

    $scope.weaponAttackBonus = function(weapon) {
        //calculate the attack bonus of the weapon
        var bonus = weapon.enhancementBonus;
        if (weapon.attackAbility == 'dex')
            bonus += $scope.abilityMod('dex');
        else
            bonus += $scope.abilityMod('str');

        //add enhancement or masterwork bonus
        if (weapon.enhancementBonus > 0)
            bonus += weapon.enhancementBonus;
        else if (weapon.masterwork)
            bonus++;

        if (!weapon.proficient)
            bonus -= 4;
        return bonus;
    };

    $scope.weaponDamageBonus = function(weapon) {
        var damageBonus = 0;
        if (weapon.damageAbility != 'none') {
            if (weapon.twoHand && weapon.damageAbility == 'str') {
                if ($scope.abilityMod('str') < 0)
                    damageBonus = Math.floor($scope.abilityMod('str') / 2);
                else damageBonus = Math.floor(1.5 * $scope.abilityMod('str'));
            } else
                damageBonus = Math.floor($scope.abilityMod(weapon.damageAbility));
        }
        damageBonus += Number(weapon.enhancementBonus);
        return damageBonus;
    };

    $scope.weaponCritical = function(weapon) {
        var critical = "";
        if (weapon.criticalRange != "20")
            critical += "/" + weapon.criticalRange;
        if (weapon.criticalMultiplier != "x2")
            critical += "/" + weapon.criticalMultiplier;
        return critical;
    };

    $scope.weaponAttack = function(weapon) {
        if ($scope.sheet == undefined) return "";
        var attack = "";

        //calculate the attack bonus
        var bonus = $scope.weaponAttackBonus(weapon);

        //add masterwork or enhancement bonus to front of string
        if (weapon.enhancementBonus > 0) {
            attack += "+" + weapon.enhancementBonus + " ";
        } else if (weapon.masterwork) {
            attack += "mwk ";
        }

        //add the name of the weapon ex: Heavy Mace
        attack += weapon.weaponName;

        //add attack bonus ex: +12/+7/+2/-3, +3, or no bonus
        attack += " ";
        var bonusWithBab;
        if ($scope.bab(false) > 0)
            for (var i = $scope.bab(false); i > 0; i-=5) {
                bonusWithBab = bonus + i;
                if (bonusWithBab >= 0)
                    attack += "+" + bonusWithBab;
                else
                    attack += bonusWithBab;
                if (i > 5)
                    attack += "/";
            }
        else {
            bonusWithBab = bonus + $scope.bab(false);
            if (bonusWithBab > 0)
                attack += "+" + bonusWithBab;
            else if (bonusWithBab < 0)
                attack += bonusWithBab;
        }

        //add the damage roll ex: ( '1d8'
        attack += " (" + weapon.damageRoll;

        //get the damage bonus (1d8 '+5'
        var damageBonus = $scope.weaponDamageBonus(weapon);
        if (damageBonus > 0)
            attack += "+" + damageBonus;
        if (damageBonus < 0)
            attack += damageBonus;

        //add critical modifier
        attack += $scope.weaponCritical(weapon);
        attack += ")";

        return attack;
    };

    $scope.editWindow = function(type, object) {
        var template;
        if (type == "feature")
            template = "class-feature.html";

        $ctrl.open = function (size, parentSelector) {
            var modalInstance = $uibModal.open({
                animation: $ctrl.animationsEnabled,
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: 'myModalContent.html',
                controller: 'ModalInstanceCtrl',
                controllerAs: '$ctrl',
                size: '',
                resolve: {
                    items: function () {

                    }
                }
            });

            modalInstance.result.then(function (selectedItem) {
                $ctrl.selected = selectedItem;
            }, function () {
                $log.info('Modal dismissed at: ' + new Date());
            });
        };

    };

    $scope.originalFunctions = {
        bab : angular.copy($scope.bab),
        skillMod : angular.copy($scope.skillMod),
        maxSkillRanks: angular.copy($scope.maxSkillRanks),
        abilityMod : angular.copy($scope.abilityMod),
        savingThrow : angular.copy($scope.savingThrow),
        weaponAttackBonus : angular.copy($scope.weaponAttackBonus),
        weaponDamageBonus : angular.copy($scope.weaponDamageBonus),
        initiative : angular.copy($scope.initiative)
    };

    /**
     * resets all functions to their original values
     */
    $scope.resetFunctions = function() {
        $scope.bab = angular.copy($scope.originalFunctions.bab);
        $scope.skillMod = angular.copy($scope.originalFunctions.skillMod);
        $scope.maxSkillRanks = angular.copy($scope.originalFunctions.maxSkillRanks);
        $scope.abilityMod = angular.copy($scope.originalFunctions.abilityMod);
        $scope.savingThrow = angular.copy($scope.originalFunctions.savingThrow);
        $scope.weaponAttackBonus = angular.copy($scope.originalFunctions.weaponAttackBonus);
        $scope.weaponDamageBonus = angular.copy($scope.originalFunctions.weaponDamageBonus);
        $scope.initiative = angular.copy($scope.originalFunctions.initiative);
    };

    $scope.makeSafe = function(code) {
        code = code.replace('makeSafe', '')
            .replace(/\$http/g, 'DONT')
            .replace(/\$location/g, 'DONT')
            .replace(/eval/g, 'DONT');
        return code;
    };

    /**
     *  TODO add regex for comments i guess?
     */
    $scope.evalCode = function() {
        $scope.resetFunctions();
        var codeArray = [];
        var classes = $scope.sheet.sheetClasses;
        var classFeatures;
        var code;
        var tempString;
        var characterLevel = 0;
        for (var i = 0; i < classes.length; i++) {
            classFeatures = classes[i].sheetClassFeatures;
            characterLevel += classes[i].level;
            for (var j = 0; j < classFeatures.length; j++) {
                //replace keywords thisClass and this with
                //corresponding scope values
                code = classFeatures[j].evalText;
                if (code != "" && code != undefined && classFeatures[j].enabled) {
                    tempString = '$scope.sheet.sheetClasses[' + i + ']';
                    code = code.replace(/thisClass/g, tempString);
                    tempString = tempString + '.sheetClassFeatures[' + j + ']';
                    code = code.replace(/thisFeature/g, tempString);
                    code = $scope.makeSafe(code);
                    codeArray.push({
                        priority: classFeatures[j].evalPriority,
                        evalText: code
                    });
                    //TODO need to add hover-notes
                }
            }
        }

        var abilities = $scope.sheet.sheetAbilities;
        for (i = 0; i < abilities.length; i++) {
            code = abilities[i].evalText;
            if (code != "" && code != undefined && abilities[i].enabled) {
                code = code.replace(/characterLevel/g, characterLevel);
            }
            code = $scope.makeSafe(code);
            codeArray.push({
                priority: abilities[i].evalPriority,
                evalText: code
            });
            //TODO add hover-notes!
        }

        //TODO add in feats, racial traits, traits, spells, and abilities
        //for (var i = 0;

        //sort the array in order of priority
         codeArray.sort(function(a,b) {
             return a.priority - b.priority;
         });

        //run the code
        for (var k = 0; k < codeArray.length; k++) {
            eval(codeArray[k].evalText);
        }
    };

    /**
     * sends the sheet object to the save servlet
     */
    $scope.saveSheet = function() {
        console.log("saving sheet ");
        console.log($scope.sheet);
        //console.log(JSON.stringify($scope.sheet));
        saveInProgress = true;
        $http({
            method : "POST",
            url : "saveSheet",
            data : angular.toJson($scope.sheet)
        }).then(function(response) {
            console.log(response.message);
        });
    };

});



/**  TODO possibly get rid of this unused directive
 * this directive limits all inputs to digits
 */
app.directive('onlyDigits', function() {
    return {
        require : "ngModel",
        restrict : "A",
        link : function(scope, element, attr, ctrl) {
            function inputValue(val) {
                val = "0" + val;
                var digits = Number(val.replace(/[^0-9]/g, ""));
                if (digits !== val) {
                    ctrl.$setViewValue(digits);
                    ctrl.$render();
                }
                return parseInt(digits, 10);
            }
            ctrl.$parsers.push(inputValue);
        }
    };
});

/**
 * when unfocusing from an input field, forces the value to become an integer
 *      optionally forces it to become positive or negative
 */
app.directive('forceInteger', function() {
    return {
        require : "ngModel",
        restrict : "A",
        scope : {forceInteger : "="},
        link : function(scope, element, attr, ctrl) {
            var savedValue;

            element.on('focus', function() {
                savedValue = Number(element.val());
            });

            element.on('blur', function() {
                var newValue = parseInt(element.val());
                if (newValue == element.val()) {
                    if (attr.forceInteger == 'pos')
                        savedValue = Math.abs(newValue);
                    else if (attr.forceInteger == 'neg')
                        savedValue = -1 * Math.abs(newValue);
                    else
                        savedValue = Number(newValue);
                    ctrl.$setViewValue(savedValue);
                    element.val(savedValue);
                } else {
                    ctrl.$setViewValue(savedValue);
                    element.val(savedValue);
                }
            });
        }
    }
});

app.directive('forceDecimal', function() {
    return {
        require : "ngModel",
        restrict : "A",
        scope : {forceDecimal : "="},
        link : function(scope, element, attr, ctrl) {
            var savedValue;

            element.on('focus', function() {
                savedValue = Number(element.val());
            });

            element.on('blur', function() {
                var newValue = parseFloat(element.val());
                if (newValue == element.val()) {
                    if (attr.forceDecimal != '')
                        savedValue = Number(newValue.toFixed(Number(attr.forceDecimal)));
                    else
                        savedValue = Number(newValue);
                    ctrl.$setViewValue(savedValue);
                    element.val(savedValue);
                } else {
                    ctrl.$setViewValue(savedValue);
                    element.val(savedValue);
                }
            });
        }
    }
});

app.directive('infoHover', function() {
    return {
        require : "ngModel",
        restrict : "A",
        scope : {},
        link : function(scope, element, attr, ctrl) {
            element.on('hover', function() {
                //need to display a tiny pop-up of modifiers
            });
        }
    }
});