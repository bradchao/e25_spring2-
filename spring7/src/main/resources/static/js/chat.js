window.onload = function(){
    let start = document.getElementById("start");
    let chatDiv = document.getElementById("chatDiv");
    let mesg = document.getElementById("mesg");
    let send = document.getElementById("send");
    let log = document.getElementById("log");

    let webSocket;
    //let url = "/ws-chat?token=" + encodeURIComponent('Bearer ' + token);
    let url = "/ws-chat";
    let client = null;

    start.style.display = "block";
    chatDiv.style.display = "none";


    start.addEventListener("click", function(){
        connect(url);
    });

    send.addEventListener("click", function(){
        client.send("/app/chat/send",{}, mesg.value.trim());
        mesg.value = "";
    });

    function connect(url){
        console.log("Connecting...");
        webSocket = new SocketJS(url);
        client = Stomp.over(webSocket);
        client.connect({}, function(frame){
            start.style.display = "none";
            chatDiv.style.display = "block";

            client.subscribe("/topic/public", function (message) {
                console.log(message);
                let body = JSON.parse(message);
                receiveMessage(body);
            });
        });

    }

    function receiveMessage(mesg) {
        chatDiv.innerHTML = `${mesg.content}`;
    }

}