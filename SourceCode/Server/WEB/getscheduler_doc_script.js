const url='http://localhost:80';

function getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
}

function GetScheduler_doc(){
    let api = '/Doc/scheduler'
    let auth = getCookie('auth')
    const Http = new XMLHttpRequest();

    Http.open("POST", url+api+`?auth=${auth}&`,true);
    Http.onload = function(){
        resp = JSON.parse(Http.responseText);
        if (resp.code != "none"){
            const str  = "Auth=" +resp.auth
            document.cookie = str
            window.location.href="waitlits.html"
        }
    };
    Http.send();
};