
const url = "http://localhost:803"
document.getElementById('bt1').addEventListener('click',function(){
    Login(document.getElementById('arg1').value,document.getElementById('arg2').value)
})

document.getElementById("arg2").addEventListener('keydown',press);

function press(event){
    if(event.code == "Enter"){
        Login(document.getElementById('arg1').value,document.getElementById('arg2').value)
    }
}

function Login(id,pass){
    var api = '/Home/account/log'

    var paragram  = `?id=${id}&password=${pass}`
    const Http = new XMLHttpRequest();
    Http.open("GET", url+api+paragram,true);
    Http.onload  = function(){
        let resp = JSON.parse(Http.responseText);
        if (resp.code == "success"){
            if (resp.type=="doctor"){
                const str  = "Auth=" +resp.auth
                document.cookie = str
                window.location.href="Home.html"
            }
            else if (resp.type == "hospital" ){
                const str  = "HosAuth=" +resp.auth
                document.cookie = str
                window.location.href="HomeAd.html"
            }
        }
        else{
            document.getElementById("logfail").setAttribute("style","color:red;display:")
        }
    };
    Http.send();
}