'use strict';

const userRegistrationForm = document.querySelector('#userRegistrationForm');
const userLoginForm = document.querySelector('#userLoginForm');

let firstname = null;
let lastname = null;
let username = null;
let password = null;

function registration(event) {
    firstname = document.querySelector('#firstname').value.trim();
    lastname = document.querySelector('#lastname').value.trim();
    username = document.querySelector('#username').value.trim();
    password = document.querySelector('#password').value.trim();

    console.log(firstname +"-"+lastname+"-"+username+"-"+password)
    fetch("/chatapp/addUser", {
        method: "POST",
        body: JSON.stringify({
            firstName: firstname,
            lastName: lastname,
            userName: username,
            password: password,
            status : 1
        }),
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        }
    }).then(r =>console.log(r));

    event.preventDefault();
}

function login(event) {
    console.log("to login page")
    location.replace('http://localhost:8080');
    event.preventDefault();
}

userRegistrationForm.addEventListener('submit', registration, true);
userLoginForm.addEventListener('submit', login, true);
