const url='http://localhost:803';

function getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
}

function DocList(){
    let api = '/Hos/doc'

    const Http = new XMLHttpRequest();
    Http.open("GET", url+api+`?auth=${getCookie('HosAuth')}`,true);
    Http.onload = function(){
        
        console.log(Http.responseText)
        resp =JSON.parse(Http.responseText);
        if (resp.code == "success"){
            const str  = "Auth=" +resp.auth
            document.cookie = str
            window.location.href="doclist.html"
        }
    };
    Http.send();
}