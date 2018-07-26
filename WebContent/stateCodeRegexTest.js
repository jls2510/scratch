
// state code regex test

var weatherState = '   Maxxx   ';
weatherState = weatherState.toString().trim().substring(0, 2).toUpperCase();
var stateCodeRegex = /^AL|AK|AZ|AR|CA|CO|CT|DE|FL|GA|HI|ID|IL|IN|IA|KS|KY|LA|ME|MD|MA|MI|MN|MS|MO|MT|NE|NV|NH|NJ|NM|NY|NC|ND|OH|OK|OR|PA|RI|SC|SD|TN|TX|UT|VT|VA|WA|WV|WI|WY$/;
//var isValidState = /^[a-zA-Z]{2}$/.test(weatherState);
var isValidState = stateCodeRegex.test(weatherState);

var message = "weatherState = " + weatherState + "; isValidState = " + isValidState;

console.log(message);

