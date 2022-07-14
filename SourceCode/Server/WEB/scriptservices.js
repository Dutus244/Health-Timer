var addbutton = document.getElementById('addbutton');
addbutton.addEventListener("click", addNewRow);

var edit1row = true;

const url='http://localhost:80';

GetHosService()

function addNewRow() {
  var table = document.getElementById("service");
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
  cell5.innerHTML = `<div class="icons" style="background-color: white;">
                      <i class="fas fa-check" style="text-align: center; color:#04AA6D"></i>
                    </div>` ;

  var temp = document.getElementById(id);
  temp.addEventListener('click',edit);
  }

function edit(event){
  if(edit1row == false){
    return;
  }

  edit1row=false;
  var rownum= event.target.id
  var table = document.getElementById("row_"+rownum);
  var editrow = table.cells.length;

  var clone = table.cloneNode(true);
  clone.id = `clone${rownum}`;

  table.cells[0].innerHTML= `<div class="iconsedit" style="background-color: white; ">
                              <i class="fas fa-check" id="save${rownum}" style="text-align: center; color:rgb(85, 140, 221)"></i>
                              <i class="fas fa-ban" id="cancel${rownum}" style="text-align: center; color:rgb(255,0,0)"></i>
                              </div>`

  for (let i = 1;i<editrow-1;i++){
    var textx = table.cells[i].innerHTML;
    table.cells[i].innerHTML= "<input type ='text'  value='" +textx+ "'></input>";
  }

  document.getElementById(`isOn_${rownum}`).addEventListener('click',function(){
    console.log(this.className)
    if (this.className=="fas fa-slash")
      this.className  ="fas fa-check"
    else
      this.className  ="fas fa-slash"
  });

  var canceltemp=document.getElementById(`cancel${rownum}`);
  canceltemp.addEventListener('click',function(){cancel(clone)});

  var canceltemp=document.getElementById(`save${rownum}`);
  canceltemp.addEventListener('click',function(){save(clone)});


}


function getCookie(name) {
  const value = `; ${document.cookie}`;
  const parts = value.split(`; ${name}=`);
  if (parts.length === 2) return parts.pop().split(';').shift();
}


function GetHosService(){
  let api = '/Hos/service'

  const Http = new XMLHttpRequest();
  Http.open("POST", url+api+`?auth=${getCookie('HosAuth')}`,true);
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
          var cell5 = row.insertCell(4);

          cell1.innerHTML =`<div class="iconsedit" style="background-color: white;">
                      <i class="fas fa-pen" id='${resp.data[i].serviceID}' style="text-align: center"></i>
                  </div>` ;

          cell2.innerHTML = 'nono';
          cell3.innerHTML = resp.data[i].serviceID;
          cell4.innerHTML = resp.data[i].serviceName;
          const ison = resp.data[i].isOn == '1' ? "fas fa-check": "fas fa-slash" 
          cell5.innerHTML = `<div class="icons" style="background-color: white;">
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
  console.log('cancle')
}


function save(clone){
  console.log('save')
}
