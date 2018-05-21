<?php
?>

<!DOCTYPE html>
<html>
<head>
<title>Simple Weather Demo</title>

<!--
<link rel="stylesheet" href="css/weather.css" type="text/css" media="screen" />
-->

<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/jquery.simpleWeather.min.js"></script>

<script>

//v3.1.0
//Docs at http://simpleweatherjs.com
$(document).ready(function() {  
  getWeather(); //Get the initial weather.
  //setInterval(getWeather, 600000); //Update the weather every 10 minutes.
});

var zipCode = 12572;

function getWeather() {
  $.simpleWeather({
    location: zipCode,
    unit: 'f',
    success: function(weather) {
      html = '<h2>'+weather.temp+'&deg;'+weather.units.temp+'</h2>';
      html += '<ul><li>'+weather.city+', '+weather.region+'</li>';
      html += '<li class="currently">'+weather.currently+'</li>';
      html += '<li>'+weather.alt.temp+'&deg;C</li></ul>';
  
      $("#weatherDisplay").html(html);
    },
    error: function(error) {
      $("#weatherDisplay").html('<p>'+error+'</p>');
    }
  });
}

function displayWeather() {
	getWeather();
}

function setZipCode(enteredZipCode) {
	zipCode = enteredZipCode;
	$("#showZipCode").html(zipCode);
}




</script>

</head>
<body>
	<div id="weather">
		<span id="weatherDisplay">
		</span>
	<input id="hiddenZipCode" type="hidden" />
	<span id="showZipCode"></span>
	</div>

	<br />
	<br />
	<button onclick=setZipCode($("#enterZipCode").val()) width=100 height=100>Set Zip Code</button>
	<input id="enterZipCode" type="text" />
	<br />
	<br />
	<button onclick=displayWeather() width=100 height=100>Display Weather</button>
</body>
</html>

