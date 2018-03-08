/**
 * Things I need to study from the Trans World interview.
 */

// reduce()
// dom idioms - lots of  $(this) and $(that)
// function(...args) - type conversion
// get better with class definitions
// "this" usage
// parseInt(010)
// function.apply

// reducer() exploration
var array = [0,1,2,3,4,5,6,7,8,9];
var reducer = function(currentValue, newValue) {
	return currentValue + newValue;
}
var reducerSum = array.reduce(reducer);
var directSum = 0;
for (var x in array) {
	directSum += parseInt(x);
}
console.log("directSum: ", directSum);
console.log("reducerSum: ", reducerSum);

// passing invalid arrays as arguments
var notAnArray = (0,1,2,3,4,5,6,7,8,9);
//var notAnArrayReducerSum = notAnArray.reduce(reducer);
//console.log("notAnArrayReducerSum: ", notAnArrayReducerSum);

console.log("parseInt(010) = " + parseInt(010));

