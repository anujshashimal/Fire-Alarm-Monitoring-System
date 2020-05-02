const mongoose = require("mongoose");

const Schema = mongoose.Schema;

const sensorSchema = new Schema({
    Id: { type: Number, required: true, unique: true },
    floorNo: { type: String, required: true },
    roomNo: { type: String, required: true }
});

module.exports = mongoose.model("SensorsAddDet", sensorSchema);