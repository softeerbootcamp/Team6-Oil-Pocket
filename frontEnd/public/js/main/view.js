import { addEvent } from "../../common/function.js";
import { navBarView } from "../navbar/view.js";
import { getChatBotTemplate, getMainViewContentTemplate } from "./template.js";

const mainView = () => {
    const $mainViewContainer = document.createElement("section");
    const $mainViewContent = document.createElement("section");

    $mainViewContent.innerHTML = getMainViewContentTemplate();

    $mainViewContainer.appendChild(navBarView());
    $mainViewContainer.appendChild($mainViewContent);

    return $mainViewContainer;
}

const chatBotView = () => {
    const $chatBotContainer = document.createElement("section");
    $chatBotContainer.classList.add("chatBotArea");
    $chatBotContainer.innerHTML = getChatBotTemplate();

    return $chatBotContainer;
}

export { mainView, chatBotView }