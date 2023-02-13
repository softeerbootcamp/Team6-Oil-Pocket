import { _$ } from "../common/function";
import { eventToLoginBtn } from "./event";
import { getLoginTemplate } from "./template";

const loginView = () => { 
    const $loginContainer = document.createElement("section");
    $loginContainer.classList.add("loginArea");
    $loginContainer.innerHTML = getLoginTemplate();

    eventToLoginBtn($loginContainer);

    return $loginContainer;
}

export { loginView }