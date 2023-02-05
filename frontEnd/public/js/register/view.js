import { getRegisterTemplate } from "./template.js";

const registerView = () => {
    const $registerContainer = document.createElement("section");
    $registerContainer.classList.add("main");

    $registerContainer.innerHTML = getRegisterTemplate();

    return $registerContainer;
}

export { registerView }