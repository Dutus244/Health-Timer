// api url
const api_url =
    "example.json?user=Hà Quốc Anh";


// Defining async function
async function getapi(url) {
    // Storing response
    const response = await fetch(url);
    // Storing data in form of JSON
    var data = await response.json();
    console.log(data);
    if (response) {
        hideloader();
    }
    show(data);
}
// Calling that async function
getapi(api_url);

// Function to hide the loader
function hideloader() {
}
// Function to define innerHTML for HTML table
function show(data) {
    let tab =
        `<tr>
          <th>name</th>
          <th>office</th>
          <th>position</th>
          <th>salary</th>
         </tr>`;

    // Loop to access all rows 
    for (let r of data.list) {
        tab += `<tr> 
        <td>${r.name} </td>
        <td>${r.office}</td>
        <td>${r.position}</td> 
        <td>${r.salary}</td>          
        </tr>`;
    }
    // Setting innerHTML as tab variable
    document.getElementById("123").innerHTML = tab;
}

document.cookie = "username=John Doe; expires=18 Dec 2023 12:00:00 UTC; path=/";