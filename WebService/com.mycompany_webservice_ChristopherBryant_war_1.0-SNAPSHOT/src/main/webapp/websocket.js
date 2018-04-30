/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
window.onload = init;
var socket = new WebSocket("ws://localhost:8080/WebSocket/actions");
socket.onmessage = onMessage;

function onMessage(event) {
    var sensor = JSON.parse(event.data);
    if (sensor.action === "add") {
        printSensorElement(sensor);
    }
    if (sensor.action === "remove") {
        document.getElementById(sensor.id).remove();
        //sensor.parentNode.removeChild(sensor);
    }
    if (sensor.action === "toggle") {
        var node = document.getElementById(sensor.id);
        var statusText = node.children[2];
        if (sensor.status === "On") {
            statusText.innerHTML = "Status: " + sensor.status + " (<a href=\"#\" OnClick=toggleSensor(" + sensor.id + ")>Turn off</a>)";
        } else if (sensor.status === "Off") {
            statusText.innerHTML = "Status: " + sensor.status + " (<a href=\"#\" OnClick=toggleSensor(" + sensor.id + ")>Turn on</a>)";
        }
    }
}

function addSensor(name, type, description) {
    var SensorAction = {
        action: "add",
        name: name,
        type: type,
        description: description
    };
    socket.send(JSON.stringify(SensorAction));
}

function removeSensor(element) {
    var id = element;
    var SensorAction = {
        action: "remove",
        id: id
    };
    socket.send(JSON.stringify(SensorAction));
}

function toggleSensor(element) {
    var id = element;
    var SensorAction = {
        action: "toggle",
        id: id
    };
    socket.send(JSON.stringify(SensorAction));
}

function printSensorElement(sensor) {
    var content = document.getElementById("content");
    
    var sensorDiv = document.createElement("div");
    sensorDiv.setAttribute("id", sensor.id);
    sensorDiv.setAttribute("class", "sensor " + sensor.type);
    content.appendChild(sensorDiv);

    var sensorName = document.createElement("span");
    sensorName.setAttribute("class", "sensorName");
    sensorName.innerHTML = sensor.name;
    sensorDiv.appendChild(sensorName);

    var sensorType = document.createElement("span");
    sensorType.innerHTML = "<b>Type:</b> " + sensor.type;
    sensorDiv.appendChild(sensorType);

    var sensorStatus = document.createElement("span");
    if (sensor.status === "On") {
        sensorStatus.innerHTML = "<b>Status:</b> " + sensor.status + " (<a href=\"#\" OnClick=toggleSensor(" + sensor.id + ")>Turn off</a>)";
    } else if (sensor.status === "Off") {
        sensorStatus.innerHTML = "<b>Status:</b> " + sensor.status + " (<a href=\"#\" OnClick=toggleSensor(" + sensor.id + ")>Turn on</a>)";
        //sensorDiv.setAttribute("class", "sensor off");
    }
    sensorDiv.appendChild(sensorStatus);

    var sensorDescription = document.createElement("span");
    sensorDescription.innerHTML = "<b>Comments:</b> " + sensor.description;
    sensorDiv.appendChild(sensorDescription);

    var removeSensor = document.createElement("span");
    removeSensor.setAttribute("class", "removeSensor");
    removeSensor.innerHTML = "<a href=\"#\" OnClick=removeSensor(" + sensor.id + ")>Remove sensor</a>";
    sensorDiv.appendChild(removeSensor);
}

function showForm() {
    document.getElementById("addSensorForm").style.display = '';
}

function hideForm() {
    document.getElementById("addSensorForm").style.display = "none";
}

function formSubmit() {
    var form = document.getElementById("addSensorForm");
    var name = form.elements["sensor_name"].value;
    var type = form.elements["sensor_type"].value;
    var description = form.elements["sensor_description"].value;
    hideForm();
    document.getElementById("addSensorForm").reset();
    addSensor(name, type, description);
}

function init() {
    hideForm();
}

