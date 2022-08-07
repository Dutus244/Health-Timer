const url='http://localhost:803';

var addbutton = document.getElementById('addbutton');
addbutton.addEventListener("click", addNewRow);

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
            var table = document.getElementById("waitlist");
            for( let i = 0 ; i < resp.data.length;i++){
                if (resp.data[i].isDone == "0") {
                    const row = table.insertRow(-1);
                    for(let j = 0; j < 4; j++)
                        row.insertCell(j)
                    
                    row.setAttribute("id",`row_${resp.data[i].usID}`);
                    row.cells[0].innerHTML = resp.data[i].a_Time
                    row.cells[1].innerHTML = resp.data[i].hosID
                    row.cells[2].innerHTML = resp.data[i].serviceID
                    row.cells[3].innerHTML = `<div class="iconsedit" style="background-color: white;">
                    <i class="fas fa-pen" id='${resp.data[i].usID}' style="text-align: center"></i>
                    </div>` 
            
                    var temp = document.getElementById(resp.data[i].usID);
                    temp.addEventListener('click', addNewRow);
                }
            }
          }
        console.log(resp);
    };
    Http.send();
})();

var edit1row = true;

function addNewRow(event){
    if(edit1row == false){
      alert('Please finish editting the previous row')
      return;
    }

    edit1row = false;

    var table = document.getElementById("waitlist");
    var countrow = event.target.id
    var id = "itcouldbesame" + countrow
    var row = table.insertRow(countrow);
    row.setAttribute("class", "edit");
    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    var cell3 = row.insertCell(2);
    var cell4 = row.insertCell(3);

    let saveid = `save${id}`;
    let cancelid = `cancel${id}`;

    cell4.innerHTML =`<div class="iconsedit" style="background-color: rgb(217, 247, 225)">
                        <i class="fas fa-check" id="${saveid}" style="text-align: center; color:rgb(85, 140, 221); background-color: rgb(217, 247, 225)"></i>
                        <i class="fas fa-ban" id="${cancelid}" style="text-align: center; color:rgb(255,0,0); background-color: rgb(217, 247, 225)"></i>
                        </div>`;

    row.cells[0].innerHTML = `Prescription`

    let textx = row.cells[1].innerHTML;
    row.cells[1].innerHTML = `Drug name: <input id='edit${1}' type ='text' style = "background-color: rgb(217, 247, 225);text-align: left " value='${textx}'></input>`;

    textx = row.cells[2].innerHTML;
    row.cells[2].innerHTML = `Amount: <input id='edit${2}' type ='text' style = "background-color: rgb(217, 247, 225);text-align: left " value='${textx}'></input>`;

    let remove = document.getElementById(cancelid);
    remove.addEventListener('click',function(){cancelAdd(countrow)});
}

function cancelAdd(row){
    edit1row = true;
    document.getElementById("waitlist").deleteRow(row);
  }


function addPrescription(table, num) {
    let id = table.id.slice(4);
    let editrow = table.cells.length;

    for (let i = num; i < editrow-1; i++){
        if (document.getElementById( `edit${i}`).value == ""){
        alert('You must input something here');
        return;
        }
    }

    // gui API , neu thanh cong thi lam cai nay
    /* let api = '/Doc/scheduler/givep'
    const Http = new XMLHttpRequest();
    Http.open("GET", url+api+`?auth=${getCookie('Auth')}&&orderID=${orderID}&name=${drugname}&amount=${amount}`,true);
    Http.onload = function() {
        resp = JSON.parse(Http.responseText);
        if (resp.code == "success") {
            id = orderID
            table.setAttribute("orderID", `row_${id}`);

            for (let i = num; i < editrow - 1; i++){
                table.cells[i].innerHTML = document.getElementById(`edit${i}`).value;
            }

            table.cells[0].innerHTML = `<div class="iconsedit" style="background-color: white;">
                                        <i class="fas fa-pen" id='${id}' style="text-align: center"></i>
                                    </div>` ;
            edit1row = true;
            table.classList.remove("edit");

            document.getElementById(`isOn_${id}`).setAttribute("style", "background-color: white; color: #04AA6D");
            table.cells[3].children[0].setAttribute("style", "background-color: white;");
        
            document.getElementById(`isOn_${id}`).removeEventListener('click', act);
            let temp = document.getElementById(id);
            temp.addEventListener('click',edit);                          
            console.log('save')
        }
        else{
            alert('fail')
        }
        console.log(resp)
    };
    Http.send(); */
    // neu khong bao loi bat ng dung nhap lai
}

/* function GetSchedulerDetail(orderID=""){ // lấy ra thông tin chi tiết của buổi khám
    let api= '/Doc/scheduler/detailget'
    const Http = new XMLHttpRequest();
    Http.open("GET", url+api+`?orderID=${orderID}`,true);
    Http.onload = function(){
        resp = JSON.parse(Http.responseText);
        // do sthg here
        
        console.log(resp)
    };
    Http.send();

} */