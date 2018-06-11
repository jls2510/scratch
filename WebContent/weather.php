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

        var weatherZipCode = 10011;

        //v3.1.0
        //Docs at http://simpleweatherjs.com
        $(document).ready(function () {
            displayWeather(); //Get the initial weather.
            //setInterval(getWeather, 600000); //Update the weather every 10 minutes.
            //setInterval(function () {weatherZipCode += 312;  displayWeather(); }, 10000);
        });

        function displayWeatherX() {
            $.simpleWeather({
                location: weatherZipCode,
                unit: 'f',
                success: function (weather) {
                    html = '<h2><i class="icon-' + weather.code + '"></i>' + weather.temp + '&deg;' + weather.units.temp + '</h2>';
                    html += '<ul>';
                    html += '<li>' + weather.city + ', ' + weather.region + '</li>';
                    html += '<li>' + weather.currently + '</li>';
                    html += '<li>' + weather.alt.temp + '&deg;C</li></ul>';
                    html += '<li>' + weather.wind.direction + ' ' + weather.wind.speed + ' ' + weather.units.speed + '</li>';
                    html += '</ul>';

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
            weatherZipCode = enteredZipCode;
            displayWeather();
            //$("#showZipCode").html(weatherZipCode);
        }


        // display the weather widget via simpleWeather > yahoo weather api
        function displayWeather() {

            // do a little checking on the zip code
            console.log('weatherZipCode (raw) = ' + weatherZipCode);
            weatherZipCode = weatherZipCode.toString().substring(0,5);
            console.log('weatherZipCode (after) = ' + weatherZipCode);
            var isValidZip = /^\d{5}$/.test(weatherZipCode);
            if (! isValidZip) {
                $("#weatherWidget").html('');
                return;
            }

            $.simpleWeather({
                location: weatherZipCode,
                unit: 'f',
                success: function (weather) {

                    var html = "";
                    html += '<span style="background-color: white;border: 1px solid lightgrey;padding: 5px;border-radius: 5px;">';
                    //html += '<span style="float: right; margin-right: 5px;background-color: white;border: 1px solid lightgrey;padding: 10px; display: inline-block;border-radius: 5px;">';
                    html += '<span style="color: blue;">Current Weather: </span>';
                    //html += '<span>';
                    html += weather.city + ', ' + weather.region + ': ';
                    html += weather.temp + '&deg;' + weather.units.temp + '&nbsp;';
                    html += '<span class="currently">' + weather.currently + '</span>';
                    //html += weather.alt.temp + '&deg;C';
                    html += '</span>';
                    //html += '</span>';

                    html += '<a href="https://www.yahoo.com/?ilc=401" target="_blank">' +
                        ' <img src="https://poweredby.yahoo.com/purple.png" width="100" height="22"/> </a>';

                    $("#weatherWidget").html(html);
                },
                error: function (error) {
                    $("#weatherWidget").html('');
                }
            });
        } // end displayWeather()


    </script>

</head>
<body>
<div id="weather">
		<div id="weatherWidget">
		</div>
    <input id="hiddenZipCode" type="hidden"/>
    <span id="showZipCode"></span>
</div>

<br/>
<br/>
<button onclick=setZipCode($("#enterZipCode").val()) width=100 height=100>Set Weather Zip Code</button>
<input id="enterZipCode" type="text"/>
</body>
</html>

