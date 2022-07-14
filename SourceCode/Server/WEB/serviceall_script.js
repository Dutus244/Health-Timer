const url='http://localhost:80';

function ServiceAll(){
    let api = '/Service/all'

    const Http = new XMLHttpRequest();
    Http.open("POST", url+api,true);
    Http.onload = function(){
        resp = JSON.parse(Http.responseText);
        if (resp.code == "success"){
            const str  = "Auth=" +resp.auth
            document.cookie = str
            window.location.href="services.html"
        }
    };
    Http.send();
}