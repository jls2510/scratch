document.getElementById("aList").addEventListener("click", function(event) {
    console.log("clicked on " + event.target.id);
});


for (var index = 1; index <= 100; index++) {

    var newline = false;

    document.write("index = " + index + ": ");

    if (index % 3 == 0) {
        document.write("fizz");
    }

    if (index % 5 == 0) {
        document.write("buzz");
    }

    document.write("<br>");
}

