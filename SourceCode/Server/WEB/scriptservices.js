var addbutton = document.getElementById('addbutton');
addbutton.addEventListener("click", addNewRow);

var edit1row = true;

const url='http://localhost:803';

GetHosService()

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

  var cell1 = row.insertCell(0);
  var cell2 = row.insertCell(1);
  var cell3 = row.insertCell(2);
  var cell4 = row.insertCell(3);

  let saveid = `save${id}`;
  
  cell1.innerHTML =`<div class="iconsedit" style="background-color: white;">
                      <i class="fas fa-check" id="${saveid}" style="text-align: center; color:rgb(85, 140, 221)"></i>
                  </div>` ;
  for (let i = 1;i<3;i++){
    let textx = row.cells[i].innerHTML;
    row.cells[i].innerHTML= `<input id='edit${i}' type ='text'  value='${textx}'></input>`;
  }
  cell4.innerHTML = `<div class="icons" style="background-color: white;">
                      <i class="fas fa-check" id="isOn_${id}" style="text-align: center; color:#04AA6D"></i>
                    </div>` ; 
  

  document.getElementById(`isOn_${id}`).addEventListener('click',act);

  let temp = document.getElementById(saveid);
  temp.addEventListener('click',function(){save(row, 1)});
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

  var clone = table.cloneNode(true);
  clone.cells[3].innerHTML = document.getElementById(`isOn_${rownum}`).className
  clone.id = `clone${rownum}`;

  table.cells[0].innerHTML= `<div class="iconsedit" style="background-color: white; ">
                              <i class="fas fa-check" id="save${rownum}" style="text-align: center; color:rgb(85, 140, 221)"></i>
                              <i class="fas fa-ban" id="cancel${rownum}" style="text-align: center; color:rgb(255,0,0)"></i>
                              </div>`

  for (let i = 2;i<editrow-1;i++){
    let textx = table.cells[i].innerHTML;
    table.cells[i].innerHTML= `<input id='edit${i}' type ='text'  value='${textx}'></input>`;
  }
  table.cells[1].id = `edit1`;

  document.getElementById(`isOn_${rownum}`).addEventListener('click',act);

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


function GetHosService(){
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

  let serviceID = ""
  if(num == 1){
    serviceID =  document.getElementById( `edit1`).value;
  }
  else{
    serviceID = table.cells[1].innerHTML;
  }
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
