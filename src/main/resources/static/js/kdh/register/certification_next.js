const certnext = document.querySelector(".cert-next");

certnext.addEventListener('click', function(e){
    e.preventDefault();
    location.href = "/register/signup";
});