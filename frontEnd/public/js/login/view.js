import { _$ } from "../common/utils";
import { eventToLoginBtn } from "./event";
import { routerCheckLogin } from "./fetch";
import { getLoginTemplate } from "./template";

const loginView = async () => {
    await routerCheckLogin();

    const $loginContainer = document.createElement("section");
    $loginContainer.classList.add("loginArea");
    $loginContainer.innerHTML = getLoginTemplate();

    eventToLoginBtn($loginContainer);

    return $loginContainer;
}

export { loginView }