var contactPlugin = {
    addName: function(name, number,successCallback, errorCallback) {
        cordova.exec(
            successCallback, // success callback function
            errorCallback, // error callback function
            'SamplePlugin', // mapped to our native Java class called "CalendarPlugin"
            'addContact', // with this action name
            [{                  // and this array of custom arguments to create our entry
                "name": name,
                "number": number                
            }]
        ); 
    }
};


function addData(){
var name=$("#name").val();

var number=$("#number").val();
var success = function() { alert("Success"); };
var error = function(message) { alert("Oopsie! " + message); };
contactPlugin.addName(name,number,success,error);

}

function getData(){
	alert("hiiii")
//var name=document.getElementById("ename").value;
	//var number=document.getElementById("number").value;
	//alert(name)

//var number=$("#number").val();
var success = function() { alert("Success"); };
var error = function(message) { alert("Oopsie! " + message); };
contactPlugin.addName("Ramee","564525",success,error);

}