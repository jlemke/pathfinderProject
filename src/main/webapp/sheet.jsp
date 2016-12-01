<%@include file="head.jsp"%>
<html>
    <head>
        <link href="css/sheet_style.css" rel="stylesheet" type="text/css">
    </head>
<body>
<p>This will be the actual view of the sheet</p>

<form action="" method="get"> <!-- TODO add in ajax request here -->

    <table class="score-table">
        <tr>
            <th class="score-header">ABI</th>
            <th class="score-header">SUM</th>
            <th class="score-header">BAS</th>
            <th class="score-header">RAC</th>
            <th class="score-header">MIS</th>
            <th class="score-header">MOD</th>
        </tr>
        <tr>
            <td>STR</td>
            <td><div id="str-score-total" class="number-box">10</div></td>

            <td><div>
                <input class="underline number-box" type="text" id="str-base"/>
            </div></td>

            <td><div>
                <input class="underline number-box" id="str-race-bonus" />
            </div></td>

            <td><div>
                <input class="underline number-box" id="str-misc-bonus" />
            </div></td>

            <td><div id="str-mod" class="number-box">0</div></td>
        </tr>
        <tr>
            <td>DEX</td>
            <td><div id="dex-score-total" class="number-box">10</div></td>

            <td><div>
                <input class="underline number-box" type="text" id="dex-base"/>
            </div></td>

            <td><div>
                <input class="underline number-box" id="dex-race-bonus" />
            </div></td>

            <td><div>
                <input class="underline number-box" id="dex-misc-bonus" />
            </div></td>

            <td><div id="dex-mod" class="number-box">0</div></td>
        </tr>
        <tr>
            <td>CON</td>
            <td><div id="con-score-total" class="number-box">10</div></td>

            <td><div>
                <input class="underline number-box" type="text" id="con-base"/>
            </div></td>

            <td><div>
                <input class="underline number-box" id="con-race-bonus" />
            </div></td>

            <td><div>
                <input class="underline number-box" id="con-misc-bonus" />
            </div></td>

            <td><div id="con-mod" class="number-box">0</div></td>
        </tr>
        <tr>
            <td>INT</td>
            <td><div id="int-score-total" class="number-box">10</div></td>

            <td><div>
                <input class="underline number-box" type="text" id="int-base"/>
            </div></td>

            <td><div>
                <input class="underline number-box" id="int-race-bonus" />
            </div></td>

            <td><div>
                <input class="underline number-box" id="int-misc-bonus" />
            </div></td>

            <td><div id="int-mod" class="number-box">0</div></td>
        </tr>
        <tr>
            <td>WIS</td>
            <td><div id="wis-score-total" class="number-box">10</div></td>

            <td><div>
                <input class="underline number-box" type="text" id="wis-base"/>
            </div></td>

            <td><div>
                <input class="underline number-box" id="wis-race-bonus" />
            </div></td>

            <td><div>
                <input class="underline number-box" id="wis-misc-bonus" />
            </div></td>

            <td><div id="wis-mod" class="number-box">0</div></td>
        </tr>
        <tr>
            <td>CHA</td>
            <td><div id="cha-score-total" class="number-box">10</div></td>

            <td><div>
                <input class="underline number-box" type="text" id="cha-base"/>
            </div></td>

            <td><div>
                <input class="underline number-box" id="cha-race-bonus" />
            </div></td>

            <td><div>
                <input class="underline number-box" id="cha-misc-bonus" />
            </div></td>

            <td><div id="cha-mod" class="number-box">0</div></td>
        </tr>
    </table>



</form>

</body>
</html>