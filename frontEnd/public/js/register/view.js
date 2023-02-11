import { 
    eventToIDValidateBtn, eventToTermOfUseBtn, 
    eventToSelects, eventToSelectValues, eventToRegisterBtn,
} from "./event.js";
import { getRegisterTemplate } from "./template.js";

const registerView = () => {
    const $registerContainer = document.createElement("section");
    $registerContainer.classList.add("main");
    $registerContainer.innerHTML = getRegisterTemplate();

    eventToTermOfUseBtn($registerContainer);
    eventToSelects($registerContainer);
    eventToSelectValues($registerContainer);
    eventToIDValidateBtn($registerContainer);
    eventToRegisterBtn($registerContainer);

    return $registerContainer;
}

export { registerView }