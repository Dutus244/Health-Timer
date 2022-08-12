const url='http://localhost:803';

const services = [
];

function getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
}

function DocListAPI() {
  const api = '/Hos/doc'

  const Http = new XMLHttpRequest();
  Http.open("GET", url+api+`?auth=${getCookie('HosAuth')}`,true);
  Http.onload = function(){
      let resp = JSON.parse(Http.responseText);
      if(resp.code == "success"){
        var table = document.getElementById("doctor");
        for( let i = 0 ; i < resp.data.doclist.length;i++){
          const row = table.insertRow(-1);
          for(let j = 0; j < 7; j++){
            row.insertCell(j)
          }
          row.setAttribute("id",`row_${resp.data.doclist[i].docID}`);
          row.cells[0].innerHTML = `<div class="iconsedit" style="background-color: white;">
          <i class="fas fa-pen" id='${resp.data.doclist[i].docID}' style="text-align: center"></i>
        </div>` 
          row.cells[1].innerHTML = resp.data.doclist[i].docID
          row.cells[2].innerHTML = resp.data.doclist[i].name
          row.cells[3].innerHTML = resp.data.doclist[i].address
          row.cells[4].innerHTML = resp.data.doclist[i].citizenID
          row.cells[5].innerHTML = resp.data.doclist[i].birthday

          var temp = document.getElementById(resp.data.doclist[i].docID);
          temp.addEventListener('click',edit); 
        }

        for (let i = 0 ; i< resp.data.service.length;i++){
          let doc = resp.data.service[i].DocID
          let ser = resp.data.service[i].serviceID

          temp = document.createElement('div')
          temp.innerHTML = ser
          document.getElementById(`row_${doc}`).cells[6].appendChild(temp)
        }
      }
      console.log(resp);
  };
  Http.send();
};

(()=>{
  let api = '/Hos/service'

  const HttpAPI = new XMLHttpRequest();
  HttpAPI.open("GET", url+api+`?auth=${getCookie('HosAuth')}`,true);
  HttpAPI.onload = function(){
      let resp = JSON.parse(HttpAPI.responseText);
      if(resp.code == "success"){
        for (let i = 0 ; i <resp.data.length;i++){
          obj = {ServiceId:`${resp.data[i].serviceID}`}
          services.push(obj)
        }
      }
      DocListAPI()  
  };
  HttpAPI.send();
})();

function DocList(){
    let api = '/Hos/doc'

    const Http = new XMLHttpRequest();
    Http.open("GET", url+api+`?auth=${getCookie('HosAuth')}`,true);
    Http.onload = function(){
        let resp =JSON.parse(Http.responseText);
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
      td = tr[i].getElementsByTagName("td")[2];
        if (td) {
          if (td.innerText.toUpperCase().indexOf(filter) > -1)
            tr[i].style.display = "";
          else
            tr[i].style.display = "none";
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
    let bttaddid = `bttadd${id}`;

    cell1.innerHTML =`<div class="iconsedit" style="background-color: rgb(217, 247, 225)">
                        <i class="fas fa-check" id="${saveid}" style="text-align: center; color:rgb(85, 140, 221); background-color: rgb(217, 247, 225)"></i>
                        <i class="fas fa-ban" id="${cancelid}" style="text-align: center; color:rgb(255,0,0); background-color: rgb(217, 247, 225)"></i>
                        </div>`;

    cell7.innerHTML = `<input type="button" id = "${bttaddid}" class = "smallbutton" value = "Add" />
                        <hr />
                        <div id = "dvContainer${countrow}"></div>`
    for (let i = 2;i<6;i++){
        let textx = row.cells[i].innerHTML;
        row.cells[i].innerHTML= `<input id='edit${i}' type ='text' style = "background-color: rgb(217, 247, 225);text-align: left " value='${textx}'></input>`;
      }
    
    row.cells[1].innerHTML = `<input id='edit${1}' type ='text' style = "background-color: rgb(217, 247, 225);text-align: left" value="" maxlength = "8"></input>`
    dateinput = row.cells[5].firstChild
    dateinput.setAttribute('type','date')
    dateinput.setAttribute('placeholder','dd-mm-yyyy')

    let btta = document.getElementById(bttaddid);
    btta.addEventListener('click', function(){AddDropDownList(countrow)});

    let remove = document.getElementById(cancelid);
    remove.addEventListener('click',function(){cancelAdd(countrow)});

    let temp = document.getElementById(saveid);
    temp.addEventListener('click',function(){add(row, 1)});

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
  let bttaddid = `bttadd${rownum}`;

  table.setAttribute("class", "edit");

  var clone = table.cloneNode(true);
  clone.id = `clone${rownum}`;

  table.cells[0].innerHTML= `<div class="iconsedit" style="background-color: rgb(217, 247, 225)">
                              <i class="fas fa-check" id="save${rownum}" style="text-align: center; color:rgb(85, 140, 221); background-color: rgb(217, 247, 225)"></i>
                              <i class="fas fa-ban" id="cancel${rownum}" style="text-align: center; color:rgb(255,0,0); background-color: rgb(217, 247, 225)"></i>
                              </div>`

  for (let i = 2;i<editrow-1;i++){
    let textx = table.cells[i].innerHTML;
    table.cells[i].innerHTML= `<input id='edit${i}' type ='text' style="background-color: rgb(217, 247, 225)" value='${textx}'></input>`;
  }
  
  dateinput = table.cells[5].firstChild
  dateinput.setAttribute('type','date')
  dateinput.setAttribute('placeholder','dd-mm-yyyy')

  PastService(table, bttaddid, rownum);

  let canceltemp=document.getElementById(`cancel${rownum}`);
  canceltemp.addEventListener('click',function(){cancel(clone)});

  savetemp=document.getElementById(`save${rownum}`);
  savetemp.addEventListener('click',function(){save(table, 2)});
}

function PastService(tablecell, bttaddid, rownum){
  //tablecell.innerHTML = "";


  //let btta = document.getElementById(bttaddid);
  //btta.addEventListener('click', function(){AddDropDownList(rownum)});

  let pserarr = [];
  let numOfpSer = tablecell.cells[6].childElementCount;
  for (let i = 0; i<numOfpSer; i++){
    pserarr.push(tablecell.cells[6].children[i])
  }

  tablecell.cells[6].innerHTML = "";
  var divcont = document.createElement("DIV");
  divcont.setAttribute("id",`dvContainer${rownum}`)
  divcont.setAttribute("style", "background-color: rgb(217, 247, 225);");
  for (let j = 0; j<numOfpSer; j++){
    var div = document.createElement("DIV");
    div.appendChild(pserarr[j]);
    div.setAttribute("style", "background-color: rgb(217, 247, 225);");

    var btnRemove = document.createElement("INPUT");
    btnRemove.value = "Remove";
    btnRemove.type = "button";
    btnRemove.setAttribute("class", "smallbutton");
    btnRemove.onclick = function () {
      let api="/Hos/doc/serviceremove"
      auth=getCookie('HosAuth');
      let Http = new XMLHttpRequest();
      Http.open("GET", url+api+`?auth=${auth}&docID=${rownum}&serviceID=${pserarr[j].innerHTML}`,true);
      let tempNode = this.parentNode;
      Http.onload= function()
      {
        let resp = JSON.parse(Http.responseText)
        if (resp.code == "success"){
          divcont.removeChild(tempNode);
        }
        console.log(resp)
      }
      Http.send()
    };
    div.appendChild(btnRemove);
    divcont.appendChild(div);
  }

  tablecell.cells[6].appendChild(divcont);

  let button = document.createElement('input');
  button.type = "button";
  button.value = "Add";
  button.setAttribute("id", bttaddid);
  button.setAttribute("class", "smallbutton");
  divcont.appendChild(button);

  let btta = document.getElementById(bttaddid);
  btta.addEventListener('click', function(){AddDropDownList(rownum)});
}

function AddDropDownList(countrow){
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

    var dvContainer = document.getElementById(`dvContainer${countrow}`);

    var div = document.createElement("DIV");
    div.appendChild(ddlServices);
    div.setAttribute("style", "background-color: rgb(217, 247, 225)");
    div.setAttribute("class", "Add");

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

function updateservice(docid, uniqueser, table){
  let api="/Hos/doc/serviceadd"
  auth=getCookie('HosAuth');
  table.cells[6].innerHTML = "";
  uniqueser.forEach(function(services)
  {
    let Http = new XMLHttpRequest();
    Http.open("GET", url+api+`?auth=${auth}&docID=${docid}&serviceID=${services}`,true);
    Http.onload= function()
    {
      let resp = JSON.parse(Http.responseText)
      if (resp.code == "success"){
        let dvl = document.createElement("DIV");
        dvl.innerHTML = services;
        table.cells[6].appendChild(dvl);
      }
    }
    Http.send()
  })

}

function updateservice2(docid, uniqueser, p_serarr, table){
  alert("Edit Service")
  let api="/Hos/doc/serviceadd"
  auth=getCookie('HosAuth');
  table.cells[6].innerHTML = "";
  p_serarr.forEach(function(pser)
  {
    table.cells[6].appendChild(pser);
  })
  uniqueser.forEach(function(services)
  {
    let Http = new XMLHttpRequest();
    Http.open("GET", url+api+`?auth=${auth}&docID=${docid}&serviceID=${services}`,true);
    Http.onload= function()
    {
      let resp = JSON.parse(Http.responseText)
      if (resp.code == "success"){
        let dvl = document.createElement("DIV");
        dvl.innerHTML = services;
        table.cells[6].appendChild(dvl);
      }
    }
    Http.send()
  })

}

function add(table, num){
  let id = table.id.slice(4);
  const row = table.id.slice(17);
  let editrow = table.cells.length;

  for (let i = num; i<5; i++){
    if (document.getElementById( `edit${i}`).value == ""){
      alert('You must input something here');
      return;
    }
  }

  let DocID =  document.getElementById( `edit1`).value;
  let DocName =  document.getElementById( `edit2`).value;
  let Docaddr =  document.getElementById( `edit3`).value;
  let DocSocialID =  document.getElementById( `edit4`).value;
  let bthd = document.getElementById( `edit5`).value;

  let serarr = [];
  let numOfSer = document.getElementById(`dvContainer${row}`).childElementCount;
  let dvser = document.getElementById(`dvContainer${row}`);

  for (let j = 0; j < numOfSer; j++){
    dvserfirstChild= dvser.children[j].firstChild;
    dvserfirstChild.classList.remove("Add");
    serarr.push(dvserfirstChild.options[dvserfirstChild.selectedIndex].text);
  }

  let uniqueser = serarr.filter((item, i, ar) => ar.indexOf(item) === i);
  console.log(uniqueser);

  // kiem tra ID co trung ko
  // gui API , neu thanh cong thi lam cai nay
  let api = '/Hos/doc/add'
  const Http = new XMLHttpRequest();
  Http.open("GET", url+api+`?auth=${getCookie('HosAuth')}&docID=${DocID}&citizenID=${DocSocialID}&name=${DocName}&bthday=${bthd.replaceAll('-','/')}&addr=${Docaddr}`,true);
  id = DocID
  Http.onload = function(){
      resp = JSON.parse(Http.responseText);
      if (resp.code == "success"){
        table.setAttribute('id',`row_${DocID}`)
        for (let i = num;i<editrow-1;i++){
          table.cells[i].innerHTML = document.getElementById(`edit${i}`).value;
        }
        table.cells[0].innerHTML = `<div class="iconsedit" style="background-color: white;">
                                    <i class="fas fa-pen" id='${id}' style="text-align: center"></i>
                                  </div>` ;
        edit1row = true;

        let temp = document.getElementById(id);
        temp.addEventListener('click',edit);  

        table.classList.remove("edit");
        // last ?    
        
        updateservice(DocID, uniqueser, table)
      }
      else{
        alert('fail')
      }


      // chỉnh lại service của BS

  };
  Http.send();
  // neu khong bao loi bat ng dung nhap lai
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
  
  let DocName =  document.getElementById( `edit2`).value;
  let Docaddr =  document.getElementById( `edit3`).value;
  let DocSocialID =  document.getElementById( `edit4`).value;
  let bthd = document.getElementById( `edit5`).value;

  let serarr = [];
  let p_serarr = [];
  let numOfSer = document.getElementById(`dvContainer${id}`).childElementCount;
  let dvser = document.getElementById(`dvContainer${id}`);

  for (let j = 0; j < numOfSer; j++){
    if(dvser.children[j].className == "Add"){
      dvserfirstChild= dvser.children[j].firstChild;
      dvserfirstChild.classList.remove("Add");
      serarr.push(dvserfirstChild.options[dvserfirstChild.selectedIndex].text);
    }
    else if (dvser.children[j].className != "smallbutton"){
      pserfirstChild = dvser.children[j].firstChild;
      alert(1)
      alert(pserfirstChild)
      p_serarr.push(pserfirstChild);
    }
  }

  let uniqueser = serarr.filter((item, i, ar) => ar.indexOf(item) === i);
  console.log(uniqueser);
 
    // kiem tra ID co trung ko
  // gui API , neu thanh cong thi lam cai nay
  let api = '/Hos/doc/edit'
  const Http = new XMLHttpRequest();
  Http.open("GET", url+api+`?auth=${getCookie('HosAuth')}&docID=${id}&citizenID=${DocSocialID}&name=${DocName}&bthday=${bthd.replaceAll('-','/')}&addr=${Docaddr}`,true);
  Http.onload = function(){
      resp = JSON.parse(Http.responseText);
      if (resp.code == "success"){
        table.setAttribute('id',`row_${id}`)
        for (let i = num;i<editrow-1;i++){
          table.cells[i].innerHTML = document.getElementById(`edit${i}`).value;
        }
        table.cells[0].innerHTML = `<div class="iconsedit" style="background-color: white;">
                                    <i class="fas fa-pen" id='${id}' style="text-align: center"></i>
                                  </div>` ;
        edit1row = true;

        let temp = document.getElementById(id);
        temp.addEventListener('click',edit);  

        table.classList.remove("edit");
        // last ?    
        
        updateservice2(id, uniqueser, p_serarr, table)
      }
      else{
        alert('fail')
      }


      // chỉnh lại service của BS

  };
  Http.send();
  // neu khong bao loi bat ng dung nhap lai
}

function cancel(clone){
  edit1row = true;
  const id = clone.id.slice(5);
  let editrow = clone.cells.length;
  let table = document.getElementById("row_"+id);
  for (let i=0; i<clone.cells.length; i++){
    table.cells[i].innerHTML = clone.cells[i].innerHTML;
  }
  
  table.cells[0].innerHTML = `<div class="iconsedit" style="background-color: white;">
                                <i class="fas fa-pen" id='${id}' style="text-align: center"></i>
                              </div>` ;

  table.classList.remove("edit");
}