var mysql = require("mysql");
var app = require("express")();

const port = 3000;


app.listen(port, () => {
    console.log("Server started on port " + port);
});

app.get("/", (req, res) => {
    console.log("User connected");
    res.send("Yeeee");
});

app.get("/register", (req, res) => {
    res.send("Register")
});

var con = mysql.createConnection({
    host: "localhost",
    user: "tbelmar",
    password: "password",
    database: "quiz_server_id1212"
});


con.connect(function (err) {
    if (err) throw err;
    console.log("Connected!");
});