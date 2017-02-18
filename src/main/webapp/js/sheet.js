/**
 * Created by Joe on 12/5/2016.
 */

var saveInProgress = false;

var app = angular.module('sheetApp', []);

//TODO figure out why mdDialog is mad
app.controller('sheetController', function($scope, $http, $location) {

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
        });
    });

    /**
     * Confirmation pop-up when deleting something
     */
    $scope.confirmDelete = function(collection, index) {
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
        collection.splice(index, 1);
    };

    /**
     * returns functions to default settings, called when applying eval()
     */
    $scope.resetFunctions = function() {

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

    /**
     * calculate max hp based on classes
     * @return int returns the maximum hit points
     */
    $scope.maxHp = function() {
        if ($scope.sheet == undefined) return 0;
        var max = 0;
        for (var i = 0; i < $scope.sheet.sheetClasses.length; i++) {
            max += $scope.sheet.sheetClasses[i].hitPoints;
            max += $scope.abilityMod('con', false);
        }
        return max;
    };

    /**
     * returns max skill points based on classes and intelligence
     */
    $scope.maxSkillPoints = function() {
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

    $scope.addCol = function() {
        var newCol = {
            sheetId : $scope.sheet.sheetId,
            columnName : "new",
            strRow : 0,
            dexRow : 0,
            conRow : 0,
            intRow : 0,
            wisRow : 0,
            chaRow : 0
        };
        $scope.sheet.sheetAbilityScoreColumns.push(newCol);
    };

    $scope.deleteCol = function(col) {
        console.log(col);
        console.log($scope.sheet.sheetAbilityScoreColumns[col]);
        $scope.sheet.sheetAbilityScoreColumns.splice(col, 1);
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

    /**
     * returns the caster level for a class using the misc bonus and the class level
     * @param c the class object
     * @return int the caster level
     */
    $scope.casterLevel = function(c) {
        if ($scope.sheet == undefined) return "";
        if (c.spellCap == "6th" || c.spellCap == "9th")
            return Number(c.level) + Number(c.casterBonusMisc);
        if (c.spellCap == "4th" && c.level > 3)
            return Number(c.level) - 3 + Number(c.casterBonusMisc);
        else return c.casterBonusMisc;
    };


    /**
     * returns the sum of skill modifiers for a skill
     */
    $scope.skillMod = function(skill) {
        var total = 0;
        total += $scope.abilityMod(skill.skillAbility);
        total += skill.skillRanks;
        if (skill.classSkill && skill.skillRanks > 0)
            total += 3;
        total += skill.skillMisc;
        total += $scope.armorCheckPenalty(skill);
        return total;
    };

    $scope.totalSkillRanks = function() {
        var total = 0;
        var skills = $scope.sheet.sheetSkills;
        for (var i = 0; i < skills.length; i++) {
            total += skills[i].skillRanks;
        }
        return total;
    };

    $scope.maxSkillRanks = function() {
        var total = 0;
        var classes = $scope.sheet.sheetClasses;
        for (var i = 0; i < classes.length; i++) {
            total += classes[i].level * (classes[i].skillsPerLevel + $scope.abilityMod('int', false));
        }
        return total;
    };

    /**
     * returns the penalty from armor on dex and str based checks
     * @return int total armor check penalty
     */
    $scope.armorCheckPenalty = function(skill) {
        var acp = 0;
        for (var i = 0; i < $scope.sheet.sheetArmors.length; i++) {
            acp += Math.abs(Number($scope.sheet.sheetArmors[i].skillPenalty));
            if ($scope.sheet.sheetArmors[i].masterwork && Number($scope.sheet.sheetArmors[i].skillPenalty) < 0)
                acp--;
        }

        if (["con", "int", "wis", "cha"].indexOf(skill.skillAbility) != -1)
            return 0;
        return -1 * acp;
    };

    $scope.removeFrom = function(collection, index) {
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
            "sheetId": $scope.sheet.sheetId,
            "weaponName":"",
            "masterwork":false,
            "enhancementBonus":0,
            "damageRoll":null,
            "criticalRange":"20",
            "criticalMultiplier":"x2",
            "attackAbility":"str",
            "damageAbility":"none",
            "range":0,
            "twoHand":false,
            "bludgeoning":false,
            "piercing":false,
            "slashing":false,
            "weight":0,
            "proficient":true,
            "worth":0
        };
        $scope.sheet.sheetWeapons.push(newWeapon);
    };

    $scope.weaponAttack = function(weapon) {
        var attack = "";

        //calculate the attack bonus
        var bonus = weapon.enhancementBonus;
        if (weapon.attackAbility == 'dex')
            bonus += $scope.abilityMod('dex');
        else
            bonus += $scope.abilityMod('str');

        //add masterwork or enhancement bonus
        if (weapon.enhancementBonus > 0) {
            attack += "+" + weapon.enhancementBonus + " ";
            bonus += weapon.enhancementBonus;
        } else if (weapon.masterwork) {
            attack += "mwk ";
            bonus++;
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

        //add the damage roll ex: (1d8+5
        attack += " (" + weapon.damageRoll;
        var damageBonus;
        if (weapon.damageAbility != 'None') {
            if (weapon.twoHand && weapon.damageAbility == 'str') {
                damageBonus = Math.floor(1.5 * $scope.abilityMod('str'));
                if (damageBonus < 0)
                    damageBonus = 0;
            } else
                damageBonus = Math.floor($scope.abilityMod(weapon.damageAbility));
            damageBonus += weapon.enhancementBonus;
            if (damageBonus > 0)
                attack += "+" + damageBonus;
            if (damageBonus < 0)
                attack += damageBonus;
        }

        //add critical modifier
        if (weapon.criticalRange != "20")
            attack += "/" + weapon.criticalRange;
        if (weapon.criticalMultiplier != "x2")
            attack += "/" + weapon.criticalMultiplier;
        attack += ")";

        return attack;
    };


    /**
     * sends the sheet object to the save servlet
     */
    $scope.saveSheet = function() {
        console.log("saving sheet ");
        console.log($scope.sheet);
        console.log(JSON.stringify($scope.sheet));
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



/**  TODO replace this with a function that just changes the input into an integer
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
