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

var addbutton = document.getElementById('addbutton');
addbutton.addEventListener("click", addNewRow);
var edit1row = true;

function addNewRow(){
    var table = document.getElementById("doctor");
    var countrow = table.rows.length;
    var id = "itcouldbesame" + countrow
    var row = table.insertRow(countrow);
    row.setAttribute("id", "row_"+id);
    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    var cell3 = row.insertCell(2);
    var cell4 = row.insertCell(3);
    var cell5 = row.insertCell(4);

    cell1.innerHTML =`<div class="iconsedit" style="background-color: white;">
                            <i class="fas fa-pen" id='${id}' style="text-align: center"></i>
                        </div>` ;
    cell2.innerHTML = countrow;
    cell3.innerHTML = "NULL";
    cell4.innerHTML = "NULL";
    cell5.innerHTML = "NULL";

    var temp = document.getElementById(id);
    temp.addEventListener('click',edit);
}