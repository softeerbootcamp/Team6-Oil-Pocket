import { eventToIDRegisterBtn, eventToIDValidateBtn } from "./event.js";
import { getRegisterTemplate } from "./template.js";

const registerView = () => {
    const $registerContainer = document.createElement("section");
    $registerContainer.classList.add("main");
    $registerContainer.innerHTML = getRegisterTemplate();

    eventToIDValidateBtn($registerContainer);
    eventToIDRegisterBtn($registerContainer)

    return $registerContainer;
}

export { registerView }