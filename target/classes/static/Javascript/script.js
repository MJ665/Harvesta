
let crops;
let cropUrl="http://localhost:8088/viewCrops";
crops=displayGetApi(cropUrl).then(function display(val){
    crops=val;
    console.log(val);
    val.forEach(crop => {
        console.log(crop.cropName);
        document.getElementById(crop.cropName).innerText=crop.cropPrice;


    });

});

var a=document.getElementById("Mung dal").innerText;
console.log(a);

function submitData(){
    let Admin={};
    let url="http://localhost:8088/viewAdmin";
    Admin.adminEmail=document.getElementById("adminEmail").value;
    Admin.adminPassword=document.getElementById("adminPassword").value;
    displayPostApiText(Admin,url).then(function display(val){
        if(val=="Successfully Logged In"){
            window.location.href="/html/Form.html";
        }else{
            alert("Invalid Email or password");
        }
    })
    
}

function Subscribe(){
    let user={};
    let url="http://localhost:8088/adduser";
    user.userEmail=document.getElementById("userEmail").value;
    displayPostApiText(user,url).then(function display(val){
        if(val=="Email already Exists"){
            alert("Email already Exists");
        }else{
            alert("User Added Successfully");
        }
    })
}




function displayPostApi(input,url) {
    async function callApi() {
         result = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(input)
        });
        result= await result.json();
        // music=result;
        return result;
    }
    return callApi();
}

function displayGetApi(url){
    async function callApi() {
        result = await fetch(url);
        result=await result.json();
       return result 
    }
    return callApi();
}

function displayPostApiText(input,url) {
    async function callApi() {
         result = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(input)
        });
        result= await result.text();
        return result;
    }
    return callApi();
}