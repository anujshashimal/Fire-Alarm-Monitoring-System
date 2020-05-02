const express = require("express");
const mongoose = require("mongoose");
const bodyParser = require("body-parser");
const app = express();
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());


app.use("/api", require("./router/sensor-routes"));

app.use(function (err, req, res) {
    // res.status(422).send({ error: err.message });
});


mongoose.connect(
        "mongodb://localhost:27017/restDB?readPreference=primary&appname=MongoDB%20Compass%20Community&ssl=false"
    )
    .then(() => {
        app.listen(5000);
    })
    .catch((error) => {
        console.log(error);
    });
