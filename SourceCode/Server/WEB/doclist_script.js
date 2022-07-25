const url='http://localhost:803';

const services = [
  { ServiceId: 1 },
  { ServiceId: 2},
  { ServiceId: 3},
];

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

function myFunction() {
    // Declare variables
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("myInput");
    filter = input.value.toUpperCase();
    table = document.getElementById("doctor");
    tr = table.getElementsByTagName("tr");
  
    // Loop through all table rows, and hide those who don't match the search query
    for (i = 0; i < tr.length; i++) {
      for (j = 1; j <= 4; j++){
        td = tr[i].getElementsByTagName("td")[j];
        if (td) {
          txtValue = td.textContent || td.innerText;
          if (txtValue.toUpperCase().indexOf(filter) > -1) {
            tr[i].style.display = "";
          } else {
            tr[i].style.display = "none";
          }
        }
      }
    }
  }

var addbutton = document.getElementById('addbutton');
addbutton.addEventListener("click", addNewRow);
var edit1row = true;

function addNewRow(){
    if(edit1row == false){
      alert('Please finish editting the previous row')
      return;
    }

    edit1row = false;

    var table = document.getElementById("doctor");
    var countrow = table.rows.length;
    var id = "itcouldbesame" + countrow
    var row = table.insertRow(countrow);
    row.setAttribute("id", "row_"+id);
    row.setAttribute("class", "edit");
    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    var cell3 = row.insertCell(2);
    var cell4 = row.insertCell(3);
    var cell5 = row.insertCell(4);
    var cell6 = row.insertCell(5);
    var cell7 = row.insertCell(6);

    let saveid = `save${id}`;
    let cancelid = `cancel${id}`;  

    cell1.innerHTML =`<div class="iconsedit" style="background-color: rgb(217, 247, 225)">
                        <i class="fas fa-check" id="${saveid}" style="text-align: center; color:rgb(85, 140, 221); background-color: rgb(217, 247, 225)"></i>
                        <i class="fas fa-ban" id="${cancelid}" style="text-align: center; color:rgb(255,0,0); background-color: rgb(217, 247, 225)"></i>
                        </div>`;
    cell7.innerHTML = `<div class="icons" style="background-color: rgb(217, 247, 225)">
                        <i class="fas fa-check" id="isOn_${id}" style="text-align: center; color:#04AA6D; background-color: rgb(217, 247, 225)"></i>
                      </div>` ; 
    cell6.innerHTML = `<input type="button" class = "smallbutton" onclick = "AddDropDownList()" value = "Add" />
                        <hr />
                        <div id = "dvContainer"></div>`
    for (let i = 2;i<5;i++){
        let textx = row.cells[i].innerHTML;
        row.cells[i].innerHTML= `<input id='edit${i}' type ='text' style = "background-color: rgb(217, 247, 225)" value='${textx}'></input>`;
      }
    
    row.cells[1].innerHTML = `<input id='edit${1}' type ='text' style = "background-color: rgb(217, 247, 225)" value="" maxlength = "8"></input>`

    let remove = document.getElementById(cancelid);
    remove.addEventListener('click',function(){cancelAdd(countrow)});

    let temp = document.getElementById(saveid);
    temp.addEventListener('click',function(){add(row, 1)});

    document.getElementById(`isOn_${id}`).addEventListener('click',act);
}

function act(){
  if (this.className=="fas fa-slash")
    this.className  ="fas fa-check"
  else
    this.className  ="fas fa-slash"
}

function AddDropDownList(){
  var ddlServices = document.createElement("SELECT");

  for (var i = 0; i < services.length; i++) {
    var option = document.createElement("OPTION");

    //Set ID in Text part.
    option.innerHTML = services[i].ServiceId;
    //Set ID in Value part.
    option.value = services[i].ServiceId;

    //Add the Option element to DropDownList.
    ddlServices.options.add(option);
}

    var dvContainer = document.getElementById("dvContainer");

    var div = document.createElement("DIV");
    div.appendChild(ddlServices);
    div.setAttribute("style", "background-color: rgb(217, 247, 225)");

    var btnRemove = document.createElement("INPUT");
    btnRemove.value = "Remove";
    btnRemove.type = "button";
    btnRemove.setAttribute("class", "smallbutton");
    btnRemove.onclick = function () {
      dvContainer.removeChild(this.parentNode);
    };

    div.appendChild(btnRemove);

    dvContainer.appendChild(div);
}

function cancelAdd(row){
  edit1row = true;
  document.getElementById("doctor").deleteRow(row);
}