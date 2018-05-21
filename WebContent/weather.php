<?php
?>

<!DOCTYPE html>
<html>
<head>
    <title>Simple Weather Demo</title>

    <link rel="stylesheet" href="css/weather.css" type="text/css" media="screen" />

    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="js/jquery.simpleWeather.min.js"></script>

    <script>

        //v3.1.0
        //Docs at http://simpleweatherjs.com
        $(document).ready(function () {
            getWeather(); //Get the initial weather.
            //setInterval(getWeather, 600000); //Update the weather every 10 minutes.
        });

        var zipCode = 12572;

        function getWeather() {
            $.simpleWeather({
                location: zipCode,
                unit: 'f',
                success: function (weather) {
                    html = '<h2><i class="icon-' + weather.code + '"></i>' + weather.temp + '&deg;' + weather.units.temp + '</h2>';
                    html += '<ul><li>' + weather.city + ', ' + weather.region + '</li>';
                    html += '<li class="currently">' + weather.currently + '</li>';
                    html += '<li>' + weather.alt.temp + '&deg;C</li></ul>';

                    $("#weatherDisplay").html(html);
                },
                error: function (error) {
                    $("#weatherDisplay").html('<p>' + error + '</p>');
                }
            });
        }

        function getWeather2() {
            $.simpleWeather({
                location: 'Austin, TX',
                woeid: '',
                unit: 'f',
                success: function (weather) {
                    html = '<h2><i class="icon-' + weather.code + '"></i> ' + weather.temp + '&deg;' + weather.units.temp + '</h2>';
                    html += '<ul><li>' + weather.city + ', ' + weather.region + '</li>';
                    html += '<li class="currently">' + weather.currently + '</li>';
                    html += '<li>' + weather.wind.direction + ' ' + weather.wind.speed + ' ' + weather.units.speed + '</li></ul>';

                    $("#weather").html(html);
                },
                error: function (error) {
                    $("#weather").html('<p>' + error + '</p>');
                }
            });

        }

        function setZipCode(enteredZipCode) {
            //alert(enteredZipCode);
            zipCode = enteredZipCode;
            getWeather();
            //$("#showZipCode").html(zipCode);
        }


    </script>

</head>
<body>
<div id="weather">
		<span id="weatherDisplay">
		</span>
    <input id="hiddenZipCode" type="hidden"/>
    <span id="showZipCode"></span>
</div>

<br/>
<br/>
<button onclick=setZipCode($("#enterZipCode").val()) width=100 height=100>Set Weather Zip Code</button>
<input id="enterZipCode" type="text"/>
</body>
</html>

