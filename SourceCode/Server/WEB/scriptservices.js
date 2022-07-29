var addbutton = document.getElementById('addbutton');
addbutton.addEventListener("click", addNewRow);

var edit1row = true;

const url='http://localhost:803';

function myFunction() {
  // Declare variables
  var input, filter, table, tr, td, i, txtValue;
  input = document.getElementById("myInput");
  filter = input.value.toUpperCase();
  table = document.getElementById("service");
  tr = table.getElementsByTagName("tr");

  // Loop through all table rows, and hide those who don't match the search query
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[2];    
      if(td) {
        if (td.innerText.toUpperCase().indexOf(filter) > -1)
          tr[i].style.display = "";
        else
          tr[i].style.display = "none";
      }
  }
}


function addNewRow() {
  if(edit1row == false){
    alert('Please finish editting the previous row')
    return;
  }

  edit1row = false;
  var table = document.getElementById("service");
  var countrow = table.rows.length;
  var id = "itcouldbesame" + countrow
  var row = table.insertRow(countrow);
  row.setAttribute("id", "row_"+id);
  row.setAttribute("class", "edit");

  var cell1 = row.insertCell(0);
  var cell2 = row.insertCell(1);
  var cell3 = row.insertCell(2);
  var cell4 = row.insertCell(3);

  let saveid = `save${id}`;
  let cancelid = `cancel${id}`;
  
  cell1.innerHTML =`<div class="iconsedit" style="background-color: rgb(217, 247, 225)">
                      <i class="fas fa-check" id="${saveid}" style="text-align: center; color:rgb(85, 140, 221); background-color: rgb(217, 247, 225)"></i>
                      <i class="fas fa-ban" id="${cancelid}" style="text-align: center; color:rgb(255,0,0); background-color: rgb(217, 247, 225)"></i>
                      </div>`
  let textx = row.cells[1].innerHTML;
  row.cells[1].innerHTML= `<input id='edit${1}' type ='text' maxlength="8" style = "background-color: rgb(217, 247, 225)" value='${textx}'></input>`;
  
  textx = row.cells[2].innerHTML;
  row.cells[2].innerHTML= `<input id='edit${2}' type ='text'  style = "background-color: rgb(217, 247, 225)" value='${textx}'></input>`;

  cell4.innerHTML = `<div class="icons" style="background-color: rgb(217, 247, 225)">
                      <i class="fas fa-check" id="isOn_${id}" style="text-align: center; color:#04AA6D; background-color: rgb(217, 247, 225)"></i>
                    </div>` ; 
  

  document.getElementById(`isOn_${id}`).addEventListener('click',act);

  let temp = document.getElementById(saveid);
  temp.addEventListener('click',function(){add(row, 1)});

  let remove = document.getElementById(cancelid);
  remove.addEventListener('click',function(){cancelAdd(countrow)});
  }


  function act(){
    if (this.className=="fas fa-slash")
      this.className  ="fas fa-check"
    else
      this.className  ="fas fa-slash"
  }

function edit(event){
  if(edit1row == false){
    alert('Please finish editting the previous row')
    return;
  }

  edit1row=false;
  var rownum= event.target.id
  var table = document.getElementById("row_"+rownum);
  var editrow = table.cells.length;

  table.setAttribute("class", "edit");

  var clone = table.cloneNode(true);
  clone.cells[3].innerHTML = document.getElementById(`isOn_${rownum}`).className
  clone.id = `clone${rownum}`;

  table.cells[0].innerHTML= `<div class="iconsedit" style="background-color: rgb(217, 247, 225)">
                              <i class="fas fa-check" id="save${rownum}" style="text-align: center; color:rgb(85, 140, 221); background-color: rgb(217, 247, 225)"></i>
                              <i class="fas fa-ban" id="cancel${rownum}" style="text-align: center; color:rgb(255,0,0); background-color: rgb(217, 247, 225)"></i>
                              </div>`

  for (let i = 2;i<editrow-1;i++){
    let textx = table.cells[i].innerHTML;
    table.cells[i].innerHTML= `<input id='edit${i}' type ='text' style="background-color: rgb(217, 247, 225)" value='${textx}'></input>`;
  }
  table.cells[1].id = `edit1`;

  document.getElementById(`isOn_${rownum}`).addEventListener('click',act);
  document.getElementById(`isOn_${rownum}`).setAttribute("style", "background-color: rgb(217, 247, 225); color: #04AA6D")
  table.cells[3].children[0].setAttribute("style", "background-color: rgb(217, 247, 225);")

  let canceltemp=document.getElementById(`cancel${rownum}`);
  canceltemp.addEventListener('click',function(){cancel(clone)});

  savetemp=document.getElementById(`save${rownum}`);
  savetemp.addEventListener('click',function(){save(table, 2)});


}


function getCookie(name) {
  const value = `; ${document.cookie}`;
  const parts = value.split(`; ${name}=`);
  if (parts.length === 2) return parts.pop().split(';').shift();
}


(() =>{
  let api = '/Hos/service'

  const Http = new XMLHttpRequest();
  Http.open("GET", url+api+`?auth=${getCookie('HosAuth')}`,true);
  Http.onload = function(){
      resp = JSON.parse(Http.responseText);
      if(resp.code == "success"){


        var table = document.getElementById("service");
        for(let i = 0 ;i < resp.data.length;i++){
          var row = table.insertRow(table.rows.length);
          row.setAttribute("id", `row_${resp.data[i].serviceID}`);
          var cell1 = row.insertCell(0);
          var cell2 = row.insertCell(1);
          var cell3 = row.insertCell(2);
          var cell4 = row.insertCell(3);

          cell1.innerHTML =`<div class="iconsedit" style="background-color: white;">
                      <i class="fas fa-pen" id='${resp.data[i].serviceID}' style="text-align: center"></i>
                  </div>` ;

          cell2.innerHTML = resp.data[i].serviceID;
          cell3.innerHTML = resp.data[i].serviceName;
          const ison = resp.data[i].isOn == '1' ? "fas fa-check": "fas fa-slash" 
          cell4.innerHTML = `<div class="icons" style="background-color: white;">
                      <i class="${ison}", id = "isOn_${resp.data[i].serviceID}" style="text-align: center; color:#04AA6D"></i>
                    </div>` ;
          
          var temp = document.getElementById(resp.data[i].serviceID);
          temp.addEventListener('click',edit);
        }
      }
      
      console.log(resp)
  };
  Http.send();
})()

function cancelAdd(row){
  edit1row = true;
  document.getElementById("service").deleteRow(row);
}


function cancel(clone){
  const id = clone.id.slice(5);
  let editrow = clone.cells.length;
  let table = document.getElementById("row_"+id);
  table.cells[2].innerHTML = clone.cells[2].innerHTML;
  table.cells[0].innerHTML = `<div class="iconsedit" style="background-color: white;">
                                <i class="fas fa-pen" id='${id}' style="text-align: center"></i>
                              </div>` ;
  
  document.getElementById(`isOn_${id}`).className=clone.cells[3].innerHTML
  edit1row = true;

  table.classList.remove("edit");
  document.getElementById(`isOn_${id}`).setAttribute("style", "background-color: white; color: #04AA6D");
  table.cells[3].children[0].setAttribute("style", "background-color: white;");

  document.getElementById(`isOn_${id}`).removeEventListener('click', act);
  let temp = document.getElementById(id);
  temp.addEventListener('click',edit);

  console.log('cancle')
}


function save(table, num){
  const id = table.id.slice(4);
  let editrow = table.cells.length;

  for (let i = num; i<editrow-1; i++){
    if (document.getElementById( `edit${i}`).value == ""){
      alert('You must input something here');
      return;
    }
  }

  let serviceID = table.cells[1].innerHTML;
  const serviceName = document.getElementById( `edit2`).value
  const isOn = document.getElementById( `isOn_${id}`).className=="fas fa-check"
  // gui API , neu thanh cong thi lam cai nay
  let api = '/Hos/service/edit'
  const Http = new XMLHttpRequest();
  Http.open("GET", url+api+`?auth=${getCookie('HosAuth')}&serviceID=${serviceID}&servicename=${serviceName}&isOn=${isOn}`,true);
  Http.onload = function(){
      resp = JSON.parse(Http.responseText);
      if (resp.code == "success"){
        for (let i = num;i<editrow-1;i++){
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
  Http.send();
  // neu khong bao loi bat ng dung nhap lai

}


function add(table, num){
  const id = table.id.slice(4);
  let editrow = table.cells.length;

  for (let i = num; i<editrow-1; i++){
    if (document.getElementById( `edit${i}`).value == ""){
      alert('You must input something here');
      return;
    }
  }

  let serviceID =  document.getElementById( `edit1`).value;
    // kiem tra ID co trung ko
  const serviceName = document.getElementById( `edit2`).value
  const isOn = document.getElementById( `isOn_${id}`).className=="fas fa-check"
  // gui API , neu thanh cong thi lam cai nay
  let api = '/Hos/service/add'
  const Http = new XMLHttpRequest();
  Http.open("GET", url+api+`?auth=${getCookie('HosAuth')}&serviceID=${serviceID}&servicename=${serviceName}&isOn=${isOn}`,true);
  Http.onload = function(){
      resp = JSON.parse(Http.responseText);
      if (resp.code == "success"){
        for (let i = num;i<editrow-1;i++){
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
  Http.send();
  // neu khong bao loi bat ng dung nhap lai

}