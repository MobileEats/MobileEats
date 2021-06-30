const btn = document.querySelector("button");
const post = document.querySelector(".post");
const widget = document.querySelector(".star-widget");
const editButton = document.querySelector(".edit");
const textarea = document.querySelector(".textarea");
const h1 = document.querySelector("h1");

btn.onclick = ()=> {
    widget.style.display = "none";
    post.style.display = "block";
    textarea.style.display = "none";
    h1.style.display = "none";

    editButton.onclick = ()=> {
        widget.style.display = "block";
        post.style.display = "none";
    }
    return false;
}