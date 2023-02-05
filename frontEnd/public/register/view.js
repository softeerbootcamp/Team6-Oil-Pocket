import { getRegisterTemplate } from "./template";

const registerView = () => {
    const $registerContainer = document.createElement("section");
    $registerContainer.classList.add("main");

    $registerContainer.innerHTML = getRegisterTemplate();

    return $registerContainer;
}

export { registerView }