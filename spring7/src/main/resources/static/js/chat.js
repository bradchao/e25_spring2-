window.onload = function(){
    let start = document.getElementById("start");
    let chatDiv = document.getElementById("chatDiv");
    let mesg = document.getElementById("mesg");
    let send = document.getElementById("send");
    let log = document.getElementById("log");

    let webSocket;
    let url = "ws://10.0.101.218:8080/BradWeb2/myserver"

    start.style.display = "block";
    chatDiv.style.display = "none";


    start.addEventListener("click", function(){
        connect(url);
    });

    send.addEventListener("click", function(){
        let message = {
            message: mesg.value
        };
        webSocket.send(JSON.stringify(message));
    });

    function connect(url){
        console.log("Connecting...");
        webSocket = new WebSocket(url);

        webSocket.onopen = function(event){
            //console.log("Open");
            start.style.display = "none";
            chatDiv.style.display = "block";
        }

        webSocket.onmessage = function(event){
            console.log(event);
            let mesgObj = JSON.parse(event.data);
            log.innerHTML += mesgObj.message + "<br />";

        }

        webSocket.onclose = function(event){
            console.log("Close");
        }

        webSocket.onerror = function(event){
            console.log("Error");
        }


    }


}