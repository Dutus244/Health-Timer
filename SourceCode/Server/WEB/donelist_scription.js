const url='http://localhost:803';

function getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
}

(() => {
    let api = '/Doc/scheduler'
    const Http = new XMLHttpRequest();

    Http.open("GET", url+api+`?auth=${getCookie('Auth')}`,true);
    Http.onload = function(){
        resp = JSON.parse(Http.responseText);
        if(resp.code == "success"){
            var table = document.getElementById("donelist");
            for( let i = 0 ; i < resp.data.length; i++){
                if (resp.data[i].isDone == "1") {
                    const row = table.insertRow(-1);
                    for(let j = 0; j < 4; j++)
                        row.insertCell(j)
                    
                    row.setAttribute("id",`row_${resp.data[i].orderID}`);
                    row.cells[0].innerHTML = resp.data[i].a_Time
                    row.cells[1].innerHTML = resp.data[i].name
                    row.cells[2].innerHTML = resp.data[i].serviceID
                    row.cells[3].innerHTML = `<div class="iconsedit" style="background-color: white;">
                    <i class="fas fa-pen" id='${resp.data[i].orderID}' style="text-align: center"></i>
                    </div>` 
            
                    /* var temp = document.getElementById(resp.data[i].orderID);
                    temp.addEventListener('click', more); */
                }
            }
          }
          console.log(resp);
    };
    Http.send();
})();