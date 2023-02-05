import { getLoginTemplate } from "./template";

const loginView = () => { 
    const $loginContainer = document.createElement("section");
    $loginContainer.classList.add("loginArea");
    
    $loginContainer.innerHTML = getLoginTemplate();

    return $loginContainer;
}

export { loginView }