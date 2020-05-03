const express = require("express");
const https = require('http');
const router = express.Router();
const Sensor = require("../models/sensor-det-models");
const Sensor_Det = require("../models/Senor-ret");
process.env['NODE_TLS_REJECT_UNAUTHORIZED'] = 0;


require("dotenv").config();

//SMS Service Configuration
const TeleSignSDK = require('telesignsdk');
const customerId = "A4DE0A77-2FB9-4E61-ADF4-772CF3A7A7D4";
const apiKey = "ApEiMnftQSsVakTWhRFZYQIBk5oRtfrAUN1OU7+3AFpW6LpA8vpP1PZqFL5h0unuYXrMtd5rlcV3jz6AokAj8w==";
const rest_endpoint = "https://rest-api.telesign.com";
const timeout = 10*1000; // 10 secs


//SMS SERVICE CONFIGURATION
const client = new TeleSignSDK( customerId,
    apiKey,
    rest_endpoint,
    timeout
);


//CONSTANTS THAT HELP TO SEND THE SMS TO THE MOBILES
const phoneNumber = "+94772181220";
const message = "co2 and Smoke Level is Increased";
const message2 = "co2 Level is Increased";
const message3 = "smoke Level is Increased";
const messageType = "ARN";

//EMAIL CONFIGURATION USING nodemailer
const nodeailer = require("nodemailer");

//EMAIL CONFIGURATION USING nodemailer
let transporter = nodeailer.createTransport({
    service: "gmail",
    auth: {
        user: "sanduntharaka258@gmail.com",
        pass: "sandun258"
    },
});


//GET SENSOR APP DETAILS USING THIS API
//CREATED THE ASYNC FUNCTION TO GET ANOTHER HOSTED JSON DUMMY VALUES

router.get("/sensors", (req, res, next) => {
    console.log("response sent");
    res.setHeader('Access-Control-Allow-Origin', '*');
    res.setHeader('Access-Control-Allow-Credentials', 'true');
    res.setHeader('Access-Control-Allow-Headers', '*');

    const customerq = https.get({hostname: "127.0.0.1",port: 4000, path: '/Sensors',method: 'GET' },
        res1 => {
            res1.on('data', d => {
                //console.log(JSON.parse(d));
                res.send(JSON.parse(d));
            })
        });
});


//GET THE PARTICULAR SENSOR DETAILS EX: FLOOR NO, AND ROOM NO CONSIST OF SENSOR
router.get("/sensor/:id", (req, res, next) => {
    res.setHeader('Access-Control-Allow-Origin', '*');
    res.setHeader('Access-Control-Allow-Credentials', 'true');
    res.setHeader('Access-Control-Allow-Headers', '*');

    Sensor.find({},{ id: req.params.id }, (err, sensors) => {
        var sensorMap = {};

        sensors.forEach((sensor) => {
            sensorMap[sensor.id] = sensor;
        });

        res.send(sensorMap);
    }).catch(next);
});


//DELETE SENSOR DETAILS THROUGH THIS API
router.delete("/sensor/:id", (req, res, next) => {
    Sensor.deleteOne({ id: req.params.id }, (err, result) => {
        if (result.deletedCount) {
            res.json({
                message: `deleted sensor ${req.params.id}`,
            });
        } else {
            res.json({
                message: `Fail to delete ${req.params.id}`,
            });
        }
    }).catch(next);
});




//ADD SENSOR DETAILS THROUGH THIS API
router.post("/sensor", (req, res, next) => {

    Sensor.create(req.body)
        .then((sensor) => {
            res.send(sensor);
        })
        .catch(next);
});


//GET TIME TO TIME UPDATED SENSOR APP DETAILS TO DESKTOP APPLICATION
//WHEN CO2 AND SMOKE LEVEL GOES UP THIS WILL FIRE
//EMAIL AND SMS WILL SEND THROUGH THIS API WHEN THE CO2 AND SMOKE LEVEL GOES UP
//CALLED BY BOTH DESKTOP AND WEB APPLICATION
router.get("/sensorret", (req, res, next) => {

    res.setHeader('Access-Control-Allow-Origin', '*');
    res.setHeader('Access-Control-Allow-Credentials', 'true');
    res.setHeader('Access-Control-Allow-Headers', '*');

    const customerq = https.get({hostname: "127.0.0.1",port: 4000, path: '/Sensors',method: 'GET' },
        res1 => {
            res1.on('data', d => {
               // console.log(JSON.parse(d));
                var json = JSON.parse(d);
                res.send(JSON.parse(d))
                json.forEach(function (object) {
                    console.log(object.co2Level )
                    if(object.co2Level >5 && object.smokeLevel >5){

                        //EMAIL CONFIGURATION WHEN ACTION FIRED
                        let sentinfo = {
                            from: 'sanduntharaka258@gmail.com',
                            to: "anujshashimal456@gmail.com",
                            subject: "SmokeLevel and CO2level Increased",
                            text: `SmokeLevel of the sensor ${object.id} 
                             is increased to ${object.smokeLevel}  
                             and CO2 Level of the sensor ${object.id} 
                             increased to ${object.co2Level}`,
                        };

                        //SMS CONFIGURATION WHEN ACTION GET FIRED
                        transporter.sendMail(sentinfo, (err, object) => {
                            if (err) {
                                console.log(err);
                            } else {
                                function messageCallback(error, res) {
                                    if (error === null) {
                                        console.log(`Messaging response for messaging phone number: ${phoneNumber}` +
                                            ` => code: ${res['status']['code']}` +
                                            `, description: ${res['status']['description']}`);
                                    } else {
                                        console.error("Unable to send message. " + error);
                                    }
                                }
                                client.sms.message(messageCallback, phoneNumber, message, messageType);
                            }
                        });
                    } else if (object.smokeLevel > 5) {

                        //EMAIL CONFIGURATION WHEN ACTION FIRED

                        let sentinfo = {
                            from: 'sanduntharaka258@gmail.com',
                            to: "anujshashimal456@gmail.com",
                            subject: "SmokeLevel and CO2 level Increased",
                            text: `SmokeLevel of the sensor ${object.id} increased to ${object.smokeLevel}`,
                        };
 
                        transporter.sendMail(sentinfo, (err, data) => {
                            if (err) {
                                console.log(err);
                            } else {
                                console.log("sent");

                                //SMS CONFIGURATION WHEN ACTION GET FIRED

                                function messageCallback(error, responseBody) {
                                    if (error === null) {
                                        console.log(`Messaging response for messaging phone number: ${phoneNumber}` +
                                            ` => code: ${responseBody['status']['code']}` +
                                            `, description: ${responseBody['status']['description']}`);
                                    } else {
                                        console.error("Unable to send message. " + error);
                                    }
                                }
                                client.sms.message(messageCallback, phoneNumber, message3, messageType);
 
                            }
                        });
 
                        console.log(object.smokeLevel);
                    }else if (object.co2Level > 5) {

                        //EMAIL CONFIGURATION WHEN ACTION FIRED

                        let sentinfo = {
                            from: 'sanduntharaka258@gmail.com',
                            to: "anujshashimal456@gmail.com",
                            subject: "SmokeLevel and CO2 level Increased",
                            text: `CO2 Level of the sensor ${object.id} increased to ${object.co2Level}`,
                        };
 
                        transporter.sendMail(sentinfo, (err, data) => {
                            if (err) {
                                console.log(err);
                            } else {
 
                                console.log("sent");

                                //SMS CONFIGURATION WHEN ACTION GET FIRED

                                function messageCallback(error, responseBody) {
                                    if (error === null) {
                                        console.log(`Messaging response for messaging phone number: ${phoneNumber}` +
                                            ` => code: ${responseBody['status']['code']}` +
                                            `, description: ${responseBody['status']['description']}`);
                                    } else {
                                        console.error("Unable to send message. " + error);
                                    }
                                }
                                client.sms.message(messageCallback, phoneNumber, message2, messageType);
                            }
                        });
 
                    } 
 
                })
                 console.log('eriush')
            })
        });
});


//REGISTER SENSORS THROUGH THIS API
router.post("/sensor/Add", (req, res, next) => {

    Sensor_Det.create(req.body)
        .then((sensor) => {
            res.send(sensor);
        })
        .catch(next);
});


//GET ALL REGISTERED DETAILS
router.get("/sensor", (req, res, next) => {

    Sensor_Det.find({}, (err, sensors) => {
        res.setHeader('Access-Control-Allow-Origin', '*');
        res.setHeader('Access-Control-Allow-Credentials', 'true');
        res.setHeader('Access-Control-Allow-Headers', '*');

        res.send(sensors);

    }).catch(next);
});


//UPDATE THE REGISTERED DETAILS
router.post("/sensor/update", (req, res, next) => {

    Sensor_Det.findOneAndUpdate(req.params.Id, req.body, (err, user) => {
        if (err) {
            return res
                .status(500)
                .send({error: "unsuccessful"})
        };
        res.send({success: "success"});
    });

});

module.exports = router;