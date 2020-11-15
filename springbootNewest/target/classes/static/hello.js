$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/greeting"
    }).then(function(data, status, jqxhr) {
        $('.greeting-id').append(data.id);
        $('.greeting-content').append(data.content);
        console.log(jqxhr);
    });
});

function onSubmit() {
    var id = document.getElementById("my_id").value;
    var content = document.getElementById("my_content").value;
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    const json = {
        "id":id,
        "content":content
    };

    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "http://localhost:8080/greeting", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.setRequestHeader(header, token);
    xhttp.send(JSON.stringify(json));

    xhttp.onreadystatechange = function(){
        if (this.readyState == 4 && this.status == 200){
            document.getElementById("demo").innerHTML = this.responseText;
        }
    };
}

function loadDoc() {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    console.log(token);
    console.log(header);

    const json = {
        "id":25,
        "content":"alter before"
    };
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("demo").innerHTML = this.responseText;
        }
    };
    xhttp.open("POST", "http://localhost:8080/greeting", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.setRequestHeader(header, token);
    xhttp.send(JSON.stringify(json));
}